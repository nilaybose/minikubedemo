pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'echo $PWD'
                sh './gradlew build'
            }
        }
        stage('Docker') {
            steps {
            	script {
                	docker.build "nilaybose/mkubedemo:latest"
      				withDockerRegistry([ credentialsId: "gitdocker", url: "" ]) {
      					 sh 'docker push nilaybose/mkubedemo:latest'
      				}
                }
           }
        }
    }
}