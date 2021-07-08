import org.sonatype.nexus.blobstore.api.BlobStoreManager
import org.sonatype.nexus.repository.config.WritePolicy
import org.sonatype.nexus.cleanup.storage.CleanupPolicyStorage


repository.createNugetHosted("nuget-hosted", BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true, WritePolicy.ALLOW_ONCE)

repository.createNugetProxy("nuget-proxy","https://api.nuget.org/v3/index.json", BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true)

repository.createNugetGroup("nuget-group", ["nuget-hosted", "nuget-proxy"], BlobStoreManager.DEFAULT_BLOBSTORE_NAME)




