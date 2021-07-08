import org.sonatype.nexus.blobstore.api.BlobStoreManager
import org.sonatype.nexus.cleanup.storage.CleanupPolicyStorage

// create hosted repo and expose via https to allow deployments
repository.createDockerHosted('docker-internal', 5000, null) 

// create proxy repo of Docker Hub and enable v1 to get search to work
// no ports since access is only indirectly via group
repository.createDockerProxy('docker-hub',                   // name
                             'https://registry-1.docker.io', // remoteUrl
                             'HUB',                          // indexType
                             null,                           // indexUrl
                             null,                           // httpPort
                             null,                           // httpsPort
                             BlobStoreManager.DEFAULT_BLOBSTORE_NAME, // blobStoreName 
                             true, // strictContentTypeValidation
                             true  // v1Enabled
                             )

// create group and allow access via https
def groupMembers = ['docker-hub', 'docker-internal']
repository.createDockerGroup('docker-all', null, 18443, groupMembers, true)


def createPolicy (dockerPolicy) {
    try {
        def policyStorage = container.lookup(CleanupPolicyStorage.class.getName())
        def cleanupPolicy = policyStorage.newCleanupPolicy()
        cleanupPolicy.setName(dockerPolicy)
        cleanupPolicy.setNotes('')
        cleanupPolicy.setMode('deletion')
        cleanupPolicy.setFormat('docker')
        cleanupPolicy.setCriteria(['regex': '.*SNAPSHOT'])
        policyStorage.add(cleanupPolicy)
    } catch (e) {
        log.info("Cleanup policy already exists, skipping...")
    }

}

def attachPolicy (dockerPolicy, docker) {
    try {
        def repo = repository.repositoryManager.get(docker)
        def cleanupPolicyAttribute = [dockerPolicy: [dockerPolicy].toSet()]
        def conf = repo.getConfiguration()
        conf.getAttributes().put("cleanup", cleanupPolicyAttribute)
        repo.stop()
        repo.update(conf)
        repo.start()
    } catch (e) {
        log.info("Attaching policy fail")
    }
}

createPolicy('dockerCleanupPolicy')
attachPolicy('dockerCleanupPolicy', 'docker')

log.info('Script dockerRepositories completed successfully')
