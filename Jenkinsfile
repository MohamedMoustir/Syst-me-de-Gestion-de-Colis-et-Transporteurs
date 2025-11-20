vpipeline {
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

    }

    post {
        failure {
            echo "Build or Tests failed!"
        }
    }



}
