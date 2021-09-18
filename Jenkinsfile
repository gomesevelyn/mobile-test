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
		
		stage ('Init Appium') {
			steps{
				bat 'appium & --device_name Nexus_S' 
				bat 'mvn test'
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
