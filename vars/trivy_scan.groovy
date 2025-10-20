def call() {
    echo "🔍 Running Trivy Filesystem Scan..."
    sh '''
        trivy fs --exit-code 0 --severity HIGH,CRITICAL . || true
        echo "✅ Trivy scan completed successfully. Check above logs for any vulnerabilities."
    '''
}

