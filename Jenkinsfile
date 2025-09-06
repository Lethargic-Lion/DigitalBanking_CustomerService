pipeline {
    agent any

    tools {
        maven 'M3'  // We'll configure Maven tool in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Lethargic-Lion/DigitalBanking_CustomerService.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying customer-service...'
                // Later we can add docker build + docker run steps here
            }
        }
    }
}
