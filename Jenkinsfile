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
                echo "Building JAR without running tests..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Unit Tests') {
            steps {
                echo "Running unit tests (Mockito only, no DB needed)..."
                sh 'mvn test -Dspring.profiles.active=test'
            }
        }

        stage('Deploy') {
            steps {
                echo "Stopping previous containers if exist..."
                sh 'docker-compose down || true'

                echo "Building Docker images..."
                sh 'docker-compose build'

                echo "Starting containers..."
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Build, Tests or Deploy failed!"
        }
    }
}
