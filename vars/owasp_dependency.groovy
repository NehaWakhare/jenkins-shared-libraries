def call() {
    echo "🔍 Running OWASP Dependency Check..."

    // Ensure database is initialized
    sh '''
        mkdir -p ~/.dependency-check-data
        dependency-check.sh --updateonly || true
    '''

    // Run the actual scan
    sh '''
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
