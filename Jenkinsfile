pipeline {
    agent {
        docker {
            image 'maven:3.8.5-openjdk-17'
            args '--rm -v $PWD:/app -w /app -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
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


    }
}
