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
        stage('Expose via ngrok') {
            steps {
                sh '''
        nohup ngrok http 8081 > ngrok.log 2>&1 &
        sleep 5
        echo "Public URL:"
        cat ngrok.log | grep -o "https://[a-z0-9.-]*\\.ngrok-free\\.dev"
        '''
            }
        }
    }

    post {
        failure {
            echo "Build or Tests failed!"
        }
    }



}
