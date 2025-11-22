pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
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


        stage('Deploy') {
            steps {
                sh 'docker-compose down || true'
                sh 'docker-compose build'
                sh 'docker-compose up -d'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

    }

    post {
        failure {
            echo "Build or Tests failed!"
        }
    }



}
