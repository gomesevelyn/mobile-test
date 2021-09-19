pipeline {
    agent any 

	environment {
        APPIUM_PORT= 5555
    }

    stages {
		stage ('Archive Apk'){
			 steps {
	            archiveArtifacts artifacts: 'src/main/resources/CTAppium_1_2.apk', fingerprint: true 
	        }
		} 
		
		stage ('Init Appium') {
			steps{
				sh "appium --port ${APPIUM_PORT}"
			}
		}
		stage('Mobile Test') {
			steps{
				git 'https://github.com/gomesevelyn/mobile-test'
				bat 'mvn test'
			}
		}
		
	}
}		
