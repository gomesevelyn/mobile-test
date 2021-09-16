pipeline{
	stages{
		stage 'Checkout'
		 node('slave') {
		  checkout scm
		 }
		 
		 stage 'Archive Apk'
		 node('slave') {
		  step([$class: 'ArtifactArchiver', artifacts: 'src/main/resources/CTAppium_1_2.apk'])
		 }
		 
		 stage("Firebase test") {
	        steps {
	            firebase instrumentation(app: 'CTAppium_1_2.apk' test: 'CTAppium_1_2.apk')
	        }
	        post {
	            always {
	                junit testResults: '.firebase/*.xml'
	            }
	        }
	     }
	}     
}	 