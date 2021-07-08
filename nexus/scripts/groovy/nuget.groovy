import org.sonatype.nexus.blobstore.api.BlobStoreManager
import org.sonatype.nexus.repository.config.WritePolicy
import org.sonatype.nexus.cleanup.storage.CleanupPolicyStorage

// create a nuget repository backed by the default blob store.
repository.createNugetHosted("nuget-hosted", BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true, WritePolicy.ALLOW_ONCE)
// create a nuget proxy repository backed by the default blob store.
// see https://help.sonatype.com/display/NXRM3/Node+Packaged+Modules+and+npm+Registries
repository.createNugetProxy("nuget-proxy","https://api.nuget.org/v3/index.json", BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true)
// create a npm group repository that merges the npm-host and npmjs.org-proxy together.
// name - The name of the new Repository
// remoteUrl - The url of the external proxy for this Repository
// blobStoreName - The BlobStore the Repository should use
// strictContentTypeValidation - Whether or not the Repository should enforce strict content types

repository.createNugetGroup("nuget-group", ["nuget-hosted", "nuget-proxy"], BlobStoreManager.DEFAULT_BLOBSTORE_NAME)




def createPolicy (policyName, policyFormat) {
    try {
        def policyStorage = container.lookup(CleanupPolicyStorage.class.getName())
        def cleanupPolicy = policyStorage.newCleanupPolicy()
        cleanupPolicy.setName(policyName)
        cleanupPolicy.setNotes('')
        cleanupPolicy.setMode('deletion')
        cleanupPolicy.setFormat(policyFormat)
        cleanupPolicy.setCriteria(['regex': '.*SNAPSHOT'])
        policyStorage.add(cleanupPolicy)
    } catch (e) {
        log.info("Cleanup policy already exists, skipping...")
    }

}

def attachPolicy (policyName, repositoryName) {
    try {
        def repo = repository.repositoryManager.get(repositoryName)
        def cleanupPolicyAttribute = [policyName: [policyName].toSet()]
        def conf = repo.getConfiguration()
        conf.getAttributes().put("cleanup", cleanupPolicyAttribute)
        repo.stop()
        repo.update(conf)
        repo.start()
    } catch (e) {
        log.info("Attaching policy fail")
    }
}

createPolicy('nugetCleanupPolicy', 'nuget')
attachPolicy('nugetCleanupPolicy', 'nuget-hosted')



