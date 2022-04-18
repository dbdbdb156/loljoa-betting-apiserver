pipeline {
    agent any

    tools{
        gradle "gradle-7.4.1"
    }
    stages{
        stage('Clone repository') {
                checkout scm
            }
        stage("Build image") {
            withGradle(){
                sh "gradle bootBuildImage --imageName=loljoa/betting_api:0.0.1-SNAPSHOT"
            }
        }

        stage("Docker login") {
            environment {
                  DOCKER_HUB_LOGIN = credentials('docker-hub')
            }
            sh 'docker login --username=$DOCKER_HUB_LOGIN_USR --password=$DOCKER_HUB_LOGIN_PSW'
        }

        stage("Image push") {
            sh "docker push loljoa/betting_api:0.0.1-SNAPSHOT"
        }

        stage("Resource cleanup") {
            sh "docker image prune -a"
        }
    }


}