// -- Directory where the Project Files are located.  
def JOB_FILES_DIRECTORY
// -- Directory where the Platform Tools is located
def PLATFORM_TOOL_DIRECTORY
// -- Directory where the Android Emulator is located
def EMULATOR_DIRECTOR

pipeline {
    agent any 
    stages {
		stage ('Archive Apk'){
			 steps {
	            archiveArtifacts artifacts: 'src/main/resources/CTAppium_1_2.apk', fingerprint: true 
	        }
		} 
		
		stage ('Initial Configuration') {
			steps{
				script {
                // -- Set the Directory of the files in the workspace
	                JOB_FILES_DIRECTORY = "${env.JOB_DIRECTORY}"+"src/main/resources"
	
	                // -- Initial Configuration Only If the Platform is Android.
	                if (JOB_PLATFORM_NAME == "android"){
	                    // -- Set the Platform Tool Directory. 
	                    PLATFORM_TOOL_DIRECTORY = "${env.ANDROID_HOME}"+"/platform-tools/"
	                    // -- Set the Emulator Directory.
	                    EMULATOR_DIRECTORY = "${env.ANDROID_HOME}"+"/emulator/"
                	}
        		}	

	            // -- Clean Workspace
	            echo "Clean Workspace"
	            cleanWs()
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
