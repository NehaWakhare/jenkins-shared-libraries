def call() {
    echo "🔍 Running OWASP Dependency Check..."
    sh '''
        dependency-check.sh \
            --scan . \
            --format HTML \
            --out dependency-check-report.html
    '''
    dependencyCheckPublisher pattern: '**/dependency-check-report.html'
    echo "✅ OWASP Dependency Check completed successfully."
}
