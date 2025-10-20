def call() {
    echo "🔍 Running OWASP Dependency Check..."

    // Build-specific data directory
    def dataDir = "${env.WORKSPACE}/dependency-check-data"

    // Initialize database (update only once)
    sh """
        mkdir -p ${dataDir}
        dependency-check.sh --updateonly --data ${dataDir} || true
    """

    // Run scan without updating database
    sh """
        dependency-check.sh \
            --scan . \
            --format HTML \
            --out dependency-check-report.html \
            --noupdate \
            --data ${dataDir}
    """

    // Publish report in Jenkins
    dependencyCheckPublisher pattern: '**/dependency-check-report.html'
    echo "✅ OWASP Dependency Check completed successfully."
}
