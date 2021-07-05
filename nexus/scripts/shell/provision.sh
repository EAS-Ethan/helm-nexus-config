set -e
set -u

apt-get install -y unzip & curl -s "https://get.sdkman.io" | sh & chmod a+x "/opt/sonatype/nexus/.sdkman/bin/sdkman-init.sh"  & source "/opt/sonatype/nexus/.sdkman/bin/sdkman-init.sh" & sdk install groovy &


username=admin
password=`cat /nexus-data/admin.password`
host=http://localhost:8081

function addAndRunScript {
  name=$1
  file=$2
  /opt/sonatype/nexus/.sdkman/candidates/groovy/current/bin/groovy -Dgroovy.grape.report.downloads=true -Dgrape.config=/config/helpers/grapeConfig.xml /config/helpers/addUpdateScript.groovy -u "$username" -p "$password" -n "$name" -f "$file" -h "$host"
  printf "\nPublished $file as $name\n\n"
  curl -v -X POST -u $username:$password --header "Content-Type: text/plain" "$host/service/rest/v1/script/$name/run"
  printf "\nSuccessfully executed $name script\n\n\n"
}

printf "Provisioning Integration API Scripts Starting \n\n" 
printf "Publishing and executing on $host\n"

for f in /config/scripts/*.groovy; do
    s=${f%.*}
    filename=${s##*/}
    filepath="$f"

    addAndRunScript "$filename" "$filepath"
done

printf "\nProvisioning Scripts Completed\n\n"

printf "\n\n\nAdmin Password: $password\n\n\n\n"