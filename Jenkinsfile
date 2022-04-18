pipeline {
    agent any

    tools{
        jdk 'openjdk17'
        gradle 'gradle-7.4.1'
    }
    triggers{
        pollSCM('*/5 * * * *')
    }

    stages{
        stage("Build image") {
            steps{
                sh "gradle bootBuildImage --imageName=loljoa/betting_api:0.0.1-SNAPSHOT"
            }
        }
        stage("Docker login") {
            environment {
                DOCKER_HUB_LOGIN = credentials('docker-hub')
            }
            steps{
                sh 'docker login --username=$DOCKER_HUB_LOGIN_USR --password=$DOCKER_HUB_LOGIN_PSW'
            }
        }
        stage("Image push") {
            steps{
                sh "docker push loljoa/betting_api:0.0.1-SNAPSHOT"
            }
        }
        stage("Resource cleanup") {
            steps{
                sh "docker image prune -a"
            }
        }
    }
}