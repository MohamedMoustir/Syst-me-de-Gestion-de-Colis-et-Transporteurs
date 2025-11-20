pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
    }

    environment {
        NGROK_LOG = "ngrok.log"
        APP_PORT = "8081"
        DOCKER_IMAGE = "gestion-colis-app:latest"
        REPO_URL = "https://github.com/MohamedMoustir/Syst-me-de-Gestion-de-Colis-et-Transporteurs.git"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: "${REPO_URL}"
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Run Docker Container') {
            steps {
                sh """
                    docker stop ${DOCKER_IMAGE} || true
                    docker rm ${DOCKER_IMAGE} || true
                    docker run -d -p ${APP_PORT}:8080 --name ${DOCKER_IMAGE} ${DOCKER_IMAGE}
                """
            }
        }

        stage('Expose via ngrok') {
            steps {
                sh """
                    nohup ngrok http ${APP_PORT} > ${NGROK_LOG} 2>&1 &
                    sleep 5
                    echo "Public URL:"
                    cat ${NGROK_LOG} | grep -o "https://[a-z0-9.-]*\\.ngrok-free\\.dev"
                """
            }
        }


    }

    post {
        failure {
            echo "Build or Tests failed!"
        }
        always {
            sh "docker ps -a"
        }
    }
}
