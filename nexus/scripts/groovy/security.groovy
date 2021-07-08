
// docker

def dockerPrivileges = [
    'nx-repository-admin-docker-docker-all-*'
]
// add roles that uses the built in nx-anonymous role as a basis and adds more privileges
security.addRole('docker', 'docker', 'docker role', dockerPrivileges, ['nx-anonymous'])

// use the new role to create a user 
def dockerRoles = ['docker']
def dockerUser = security.addUser('docker', 'docker', 'docker', 'docker@example.com', true, 'Renegade187!', dockerRoles)



// npm

def npmPrivileges = [
    'nx-repository-admin-npm-npm-group-*' , 'nx-repository-admin-npm-npm-prxy-*' , 'nx-repository-admin-npm-npm-hosted-*'
]
// add roles that uses the built in nx-anonymous role as a basis and adds more privileges
security.addRole('npm', 'npm', 'npm role', npmPrivileges, ['nx-anonymous'])

// use the new role to create a user 
def npmRoles = ['npm']
def npmUser = security.addUser('npm', 'npm', 'npm', 'npm@example.com', true, 'Renegade187!', npmRoles)




// nuget

def nugetPrivileges = [
    'nx-repository-admin-nuget-nuget-group-*' , 'nx-repository-admin-nuget-nuget-hosted-*' , 'nx-repository-admin-nuget-nuget.org-proxy-*'
]
// add roles that uses the built in nx-anonymous role as a basis and adds more privileges
security.addRole('nuget', 'nuget', 'nuget role', nugetPrivileges, ['nx-anonymous'])

// use the new role to create a user 
def nugetRoles = ['nuget']
def nugetUser = security.addUser('nuget', 'nuget', 'nuget', 'nuget@example.com', true, 'Renegade187!', nugetRoles)