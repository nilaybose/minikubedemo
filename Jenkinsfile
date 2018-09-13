pipeline {
    agent any
	environment {
	    registry = “nilaybose/mkubedemo”
	    registryCredential = ‘gitdocker’
  	}

    stages {
        stage('Build') {
            steps {
                sh './gradlew build'
                sh 'echo $PWD'
            }
        }
        stage('Docker') {
            steps {
            	script {
                	docker.build "nilaybose/mkubedemo:latest"
       	            docker.withRegistry( ‘’, registryCredential ) {
        				dockerImage.push()
      				}
                }
            
            }
        }
    }
}