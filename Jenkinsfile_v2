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
        stage('Deploy Green') {
            steps {
                sh 'kubectl create -f ./v2_deploy.yaml --record'
                sh 'sleep 3m'
            }
        }
        stage('ABTEST') {
            steps {
            	sh 'echo "Test the new service"'
                sh 'curl http://192.168.39.139:32255/app/ | grep V2'
                sh 'echo "Testing of the new service Successful"'
            }
        }
        stage('Blue2Green') {
            steps {
            	sh 'kubectl label svc prod-spboot-service app=spboot-v2 --overwrite'
            	sh 'kubectl patch svc prod-spboot-service -p \'{"spec":{"selector": {"app": "spboot-v2"}}}\''
                sh 'kubectl delete deployment spboot-v1'
            }
        }
    }
}


