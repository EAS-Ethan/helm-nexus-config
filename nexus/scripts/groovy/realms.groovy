import groovy.json.JsonSlurper
import org.sonatype.nexus.security.realm.RealmManager

realmManager = container.lookup(RealmManager.class.getName())
// enable/disable the NuGet API-Key Realm
realmManager.enableRealm("NuGetApiKey")
realmManager.enableRealm("DockerBearerToken")
realmManager.enableRealm("npmBearerToken")