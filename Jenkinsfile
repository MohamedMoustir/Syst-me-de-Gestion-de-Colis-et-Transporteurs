pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
    }

    stages {


        stage('Checkout') {
            steps {
                deleteDir()
                git branch: 'main',
                        url: 'https://github.com/MohamedMoustir/Syst-me-de-Gestion-de-Colis-et-Transporteurs.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh 'docker-compose -f docker-compose.yml down || true'
                    sh 'docker-compose -f docker-compose.yml build'
                    sh 'docker-compose -f docker-compose.yml up -d'
                }
            }
        }
    }

    post {
        failure {
            echo "❌ Build or Tests failed!"
        }
        success {
            echo "✅ Deployment done successfully!"
        }
    }
}
