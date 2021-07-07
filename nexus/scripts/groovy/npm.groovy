import org.sonatype.nexus.capability.CapabilityRegistry
//right
import org.sonatype.nexus.repository.config.WritePolicy


repository.createNpmHosted("npm-hosted",  BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true, WritePolicy.ALLOW)

repository.createNpmProxy("npm-prxy", "https://registry.npmjs.org",  BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true)

repository.createNpmGroup("npm-group", ["npm-hosted", "npm-prxy"],  BlobStoreManager.DEFAULT_BLOBSTORE_NAME)