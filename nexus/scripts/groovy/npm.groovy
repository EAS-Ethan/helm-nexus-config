import org.sonatype.nexus.blobstore.api.BlobStoreManager
import org.sonatype.nexus.repository.config.WritePolicy
import org.sonatype.nexus.cleanup.storage.CleanupPolicyStorage

repository.createNpmHosted("npm-hosted",  BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true, WritePolicy.ALLOW_ONCE)

repository.createNpmProxy("npm-prxy", "https://registry.npmjs.org",  BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true)

repository.createNpmGroup("npm-group", ["npm-hosted", "npm-prxy"],  BlobStoreManager.DEFAULT_BLOBSTORE_NAME)




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

createPolicy('npmCleanupPolicy', 'npm')
sleep(1000)
attachPolicy('npmCleanupPolicy', 'npm-hosted')


