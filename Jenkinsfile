node {

    stage('Clone repository') {
        checkout scm
    }

    stage("Build image") {
        sh "gradle bootBuildImage --imageName=loljoa/betting_api:0.0.1-SNAPSHOT"
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