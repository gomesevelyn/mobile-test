pipeline {
    agent any 

    stages {
		stage ('Archive Apk'){
			 steps {
	            archiveArtifacts artifacts: 'src/main/resources/CTAppium_1_2.apk', fingerprint: true 
	        }
		} 
		
		stage ('Init BrowserStack') {
			steps{
				bat 'mvn test -P <android-first-test>'
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
