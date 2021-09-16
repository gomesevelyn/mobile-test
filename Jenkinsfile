// -- Directory where the Project Files are located.  
def JOB_FILES_DIRECTORY
// -- Directory where the Platform Tools is located
def PLATFORM_TOOL_DIRECTORY
// -- Directory where the Android Emulator is located
def EMULATOR_DIRECTORY
// -- Path of the Suite to execute
def SUITE_PATH


pipeline {
    agent any 
    options{timestamps()}
    stages {
        stage("Initial Configuration") {
	        steps {
	            script {
	                
	            	if (JOB_PLATFORM_NAME == "android"){
	                    // -- Set the Platform Tool Directory. 
	                    PLATFORM_TOOL_DIRECTORY = "${env.ANDROID_HOME}"+"/platform-tools/"
	                    // -- Set the Emulator Directory.
	                    EMULATOR_DIRECTORY = "${env.ANDROID_HOME}"+"/emulator/"
	                }
	         	}
	      	}
  	  	}
  	  	
  	  	
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