pipeline {
    agent any

    environment {
        DISABLE_AUTH = 'true'
        DB_ENGINE    = 'sqlite'
    }

    stages {
        stage('Build') {
            steps {
                sh './gradlew build &&  java -jar ./build/libs/nbose-minikube-demo-0.1.0.jar'
            }
        }
    }
}