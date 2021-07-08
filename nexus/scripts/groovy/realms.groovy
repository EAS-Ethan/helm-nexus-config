import groovy.json.JsonSlurper
import org.sonatype.nexus.security.realm.RealmManager

parsed_args = new JsonSlurper().parseText(args)

realmManager = container.lookup(RealmManager.class.getName())

// enable/disable the NuGet API-Key Realm
realmManager.enableRealm("NuGetApiKey", parsed_args.nuget_api_key_realm)