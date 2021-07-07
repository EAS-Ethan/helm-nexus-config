
import org.sonatype.nexus.capability.CapabilityRegistry
import org.sonatype.nexus.repository.storage.WritePolicy

// create a npm repository backed by the default blob store.
repository.createNpmHosted("npm-hosted",  BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true, WritePolicy.ALLOW_ONCE)
// create a npm proxy repository backed by the default blob store.
// see https://help.sonatype.com/display/NXRM3/Node+Packaged+Modules+and+npm+Registries
repository.createNpmProxy("npmjs.org-proxy", "https://registry.npmjs.org",  BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true)
// create a npm group repository that merges the npm-host and npmjs.org-proxy together.
repository.createNpmGroup("npm-group", ["npm-hosted", "npmjs.org-proxy"],  BlobStoreManager.DEFAULT_BLOBSTORE_NAME)