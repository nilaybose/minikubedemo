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
                	docker.build "nilaybose/mkubedemo:latest"
      				withDockerRegistry([ credentialsId: "gitdocker", url: "" ]) {
      					 sh 'docker push nilaybose/mkubedemo:latest'
      				}
                }
           }
        }
    }
}