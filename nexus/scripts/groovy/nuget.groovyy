import org.sonatype.nexus.blobstore.api.BlobStoreManager
import org.sonatype.nexus.repository.config.WritePolicy

// create a nuget repository backed by the default blob store.
repository.createNugetHosted("nuget-hosted", BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true, WritePolicy.ALLOW_ONCE)
// create a nuget proxy repository backed by the default blob store.
// see https://help.sonatype.com/display/NXRM3/Node+Packaged+Modules+and+npm+Registries
createNugetProxy("nuget-proxy","https://api.nuget.org/v3/index.json", BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true)
// create a npm group repository that merges the npm-host and npmjs.org-proxy together.
// name - The name of the new Repository
// remoteUrl - The url of the external proxy for this Repository
// blobStoreName - The BlobStore the Repository should use
// strictContentTypeValidation - Whether or not the Repository should enforce strict content types

repository.createNugetGroup("npm-group", ["nuget-hosted", "nuget-proxy"], BlobStoreManager.DEFAULT_BLOBSTORE_NAME)