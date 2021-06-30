import org.sonatype.nexus.repository.storage.WritePolicy
import org.sonatype.nexus.blobstore.api.BlobStoreManager

repository.createGolangHosted("Golang-hosted", "default", true, WritePolicy.ALLOW_ONCE)
repository.createGolangProxy("Golang-proxy", "https://gonexus.dev", BlobStoreManager.DEFAULT_BLOBSTORE_NAME)
repository.createGolangGroup("Golang-group", ["Golang-hosted", "Golang-proxy"], "default")