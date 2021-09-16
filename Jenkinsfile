pipeline {
    agent any 
    stages {
		stage ('Archive Apk')
		 steps {
            sh 'make' 
            archiveArtifacts artifacts: 'src/main/resources/CTAppium_1_2.apk', fingerprint: true 
        }
		 
		stage ('Firebase test') {
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