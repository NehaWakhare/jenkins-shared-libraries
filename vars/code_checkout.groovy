// vars/code_checkout.groovy
def call(String repoUrl, String branch='main') {
    echo "Checking out code from ${repoUrl}, branch ${branch}"
    git url: repoUrl, branch: branch
}
