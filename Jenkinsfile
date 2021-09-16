pipeline {
    stages {
        stage("Firebase test") {
            steps {
                firebase instrumentation(app: 'CTAppium_1_2.apk')
            }
            post {
                always {
                    junit testResults: '.firebase/*.xml'
                }
            }
        }
    }
}   