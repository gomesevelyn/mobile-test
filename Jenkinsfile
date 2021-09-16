pipeline {
    agent any 
    stages {
		stage ('Archive Apk'){
			 steps {
	            archiveArtifacts artifacts: 'src/main/resources/CTAppium_1_2.apk', fingerprint: true 
	        }
		} 
		
		stage ('Test Mobile'){
			 steps {
			 	git 'https://github.com/gomesevelyn/mobile-test'
			 	bat 'java -version'
			 	bat 'mvn test'
			 }
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