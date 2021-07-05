
import org.sonatype.nexus.capability.CapabilityRegistry
import org.sonatype.nexus.repository.storage.WritePolicy
import org.sonatype.nexus.security.user.UserSearchCriteria
import org.sonatype.nexus.security.authc.apikey.ApiKeyStore
import org.sonatype.nexus.security.realm.RealmManager
import org.apache.shiro.subject.SimplePrincipalCollection
import org.sonatype.nexus.scheduling.TaskScheduler
import org.sonatype.nexus.scheduling.schedule.Daily

// create a npm repository backed by the default blob store.
repository.createNpmHosted("npm-hosted", "default", true, WritePolicy.ALLOW_ONCE)
// create a npm proxy repository backed by the default blob store.
// see https://help.sonatype.com/display/NXRM3/Node+Packaged+Modules+and+npm+Registries
repository.createNpmProxy("npmjs.org-proxy", "https://registry.npmjs.org", "default")
// create a npm group repository that merges the npm-host and npmjs.org-proxy together.
repository.createNpmGroup("npm-group", ["npm-hosted", "npmjs.org-proxy"], "default")