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
		
		stage("Appium Test") {
        steps {
            script {
                // -- Android Appium Test
                if (JOB_PLATFORM_NAME == "android"){
                    echo "Launching Appium Test on Android"
                }
                // -- iOS Appium Test
                if (JOB_PLATFORM_NAME == "ios"){
                    echo "Launching Appium Test on iOS"
                    //TODO: Complete
                }
                
                // -- Script to launch Appium Test
                script {
                    try {
                        if (JOB_APP_NAME) {
                            sh """
                                mvn clean -DdeviceName="${JOB_DEVICE_NAME}" -DdevicePlatformName="${JOB_PLATFORM_NAME}" -DdevicePlatformVersion="${JOB_EMULATOR_PLATFORM_VERSION}" -DdeviceApp="${JOB_APP_NAME}" -DtestSuite="${SUITE_PATH}" test
                            """
                        }
                        else {
                            sh """
                                mvn clean -DdeviceName="${JOB_DEVICE_NAME}" -DdevicePlatformName="${JOB_PLATFORM_NAME}" -DdevicePlatformVersion="${JOB_EMULATOR_PLATFORM_VERSION}" -DtestSuite="${SUITE_PATH}" test
                            """
                        }
                        echo "Publishing Junit Results"
                        junit "**/target/surefire-reports/junitreports/*.xml"

                    } catch (err) { 
                        echo "Archiving Screenshot of the Failed Tests"
                        archiveArtifacts "**/screenshot/*.png"
                        echo "Publishing Junit Results"
                        junit "**/target/surefire-reports/junitreports/*.xml"
                    	}
                	}   
            	}
        	}
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