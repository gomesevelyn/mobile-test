pipeline {
    agent any 
    stages {
        stage("Initial Configuration") {
	        steps {
	            script {
	            	 // -- Set the suite name and route parameter
	                SUITE_PATH = "src/test/resources/suites/"+"${JOB_APPIUM_SUITE}"+".xml" 
	                
	            	if (JOB_PLATFORM_NAME == "android"){
	                    // -- Set the Platform Tool Directory. 
	                    PLATFORM_TOOL_DIRECTORY = "${env.ANDROID_HOME}"+"/platform-tools/"
	                    // -- Set the Emulator Directory.
	                    EMULATOR_DIRECTORY = "${env.ANDROID_HOME}"+"/emulator/"
	                }
	            }
	        }
  	  	}
  	  	
  	  	stage("Execute ADB Server") {
	        when {
	                expression { return JOB_PLATFORM_NAME == "android" }
	        }
	        steps {
	            echo "Executing ADB Server"
	            // -- First, you need to go to the Platform Tool Directory.
	            // -- Then run the ADB Server
	            script {
	                try {
	                    sh """
	                        cd ${PLATFORM_TOOL_DIRECTORY}
	                        ./adb start-server&
	                    """
	                } catch (err) { 
	                    echo "The ADB Server is not running"                                          
	                }
	            }  
	        }
	    }
	    
		stage ('Archive Apk'){
			 steps {
	            archiveArtifacts artifacts: 'src/main/resources/CTAppium_1_2.apk', fingerprint: true 
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