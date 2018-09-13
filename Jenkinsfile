pipeline {
    agent any
	def app 
    environment {
        DISABLE_AUTH = 'true'
        DB_ENGINE    = 'sqlite'
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
                app = docker.build("nilaybose/mkubedemo")
                app.push('latest')
            }
        }
    }
}