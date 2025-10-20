def call() {
    echo "🔍 Running OWASP Dependency Check..."

    // Use already downloaded database; skip update in CI pipeline
    sh '''
        mkdir -p ~/.dependency-check-data
        dependency-check.sh \
            --scan . \
            --format HTML \
            --out dependency-check-report.html \
            --noupdate
    '''

    // Publish report in Jenkins
    dependencyCheckPublisher pattern: '**/dependency-check-report.html'
    echo "✅ OWASP Dependency Check completed successfully."
}
