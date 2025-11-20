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
        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build JAR') {
            tools {
                jdk 'jdk17'
                maven 'maven3'
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
    }

    post {
        success {
            echo " Build & Tests passed successfully!"
        }
        failure {
            echo " Build or Tests failed!"
        }
    }
}