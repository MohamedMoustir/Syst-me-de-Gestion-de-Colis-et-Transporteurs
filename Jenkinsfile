pipeline {
    agent any

    environment {
        DOCKERHUB_USER = "moustir"
        APP_NAME = "gestion-colis"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/MohamedMoustir/Syst-me-de-Gestion-de-Colis-et-Transporteurs.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKERHUB_USER}/${APP_NAME}:latest ."
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh "echo $PASS | docker login -u $USER --password-stdin"
                    sh "docker push ${DOCKERHUB_USER}/${APP_NAME}:latest"
                }
            }
        }
    }
}
