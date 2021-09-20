pipeline {
    agent any 

    stages {
		stage ('Archive Apk'){
			 steps {
	            archiveArtifacts artifacts: 'src/main/resources/CTAppium_1_2.apk', fingerprint: true 
	        }
		} 
		
		stage ('Init BrowserStack Mobile Test') {
			steps{
				bat 'adb install CTAppium_1_2.apk'
				bat 'mvn test'
			}
		}
	}
}		
