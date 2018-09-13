pipeline {
    agent any
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
                	dockerImage = docker.build "nilaybose/mkubedemo:latest"
       	            docker.withRegistry( "docker.io/nilaybose", "gitdocker") {
        				dockerImage.push()
      				}
                }
            
            }
        }
    }
}