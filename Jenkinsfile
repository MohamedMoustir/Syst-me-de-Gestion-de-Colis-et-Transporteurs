pipeline {
    agent {
        docker {
            image 'maven:3.8.5-openjdk-17'
            args '-v /root/.m2:/root/.m2'
        }
    }

    environment {
        DOCKERHUB_USER = "moustir"
        APP_NAME = "gestion-colis"
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/MohamedMoustir/Syst-me-de-Gestion-de-Colis-et-Transporteurs.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    docker.build("${DOCKERHUB_USER}/${APP_NAME}:latest")
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        docker.image("${DOCKERHUB_USER}/${APP_NAME}:latest").push()
                    }
                }
            }
        }
    }
}
