import org.sonatype.nexus.capability.CapabilityRegistry
import org.sonatype.nexus.repository.storage.WritePolicy

repository.createNpmHosted("npm-hosted",  BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true, WritePolicy.ALLOW_ONCE)

repository.createNpmProxy("npmjs.org-proxy", "https://registry.npmjs.org",  BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true)

repository.createNpmGroup("npm-group", ["npm-hosted", "npmjs.org-proxy"],  BlobStoreManager.DEFAULT_BLOBSTORE_NAME)