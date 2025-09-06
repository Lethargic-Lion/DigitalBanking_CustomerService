🚀 Digital Banking – Customer Service Microservice

This repository contains the Customer Service microservice for the Digital Banking system.
It is a Spring Boot microservice built with Java, Spring Boot, H2 Database, and uses Jenkins for CI/CD.

📌 Project Setup
1️⃣ Clone the Repository
git clone https://github.com/<your-username>/DigitalBanking_CustomerService.git
cd DigitalBanking_CustomerService

2️⃣ Run Locally

Build and run the project:

mvn clean package
java -jar target/customer-service-0.0.1-SNAPSHOT.jar


Default Configuration (from application.properties):

spring.application.name=customer-service
server.servlet.context-path=/customer-service

# H2 Database
spring.datasource.url=jdbc:h2:mem:digital_banking_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console


✅ Open H2 console at: http://localhost:8080/customer-service/h2-console

🛠 CI/CD Pipeline with Jenkins

We have set up a local Jenkins instance to run a pipeline that automatically builds and tests this project on every push to main.

1️⃣ Run Jenkins with Docker

Make sure you have Docker
installed and running.
Then run Jenkins in a container:

docker run -d \
-p 8080:8080 -p 50000:50000 \
--name customer_service_ci \
-v customer_service_jenkins_home:/var/jenkins_home \
jenkins/jenkins:lts


Access Jenkins at: http://localhost:8080

2️⃣ Configure Jenkins

Install recommended plugins during first setup.

Create a Pipeline job in Jenkins.

Configure it to use your GitHub repository.

Make sure you have a Jenkinsfile in the root of this repo:

pipeline {
agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}

3️⃣ Connect GitHub → Jenkins (Webhooks)

We are using ngrok to expose local Jenkins to GitHub.

Install ngrok

Download ngrok from https://ngrok.com/download
and install it.

Authenticate ngrok
ngrok config add-authtoken <your-ngrok-token>

Start Tunnel
ngrok http 8080


Copy the generated https://xxxx.ngrok-free.app URL.

Add GitHub Webhook

Go to your repo → Settings → Webhooks → Add Webhook

Payload URL: https://xxxx.ngrok-free.app/github-webhook/

Content Type: application/json

Which events: Just the push event

Save the webhook → Push a commit → Jenkins will automatically start a build 🎉

✅ Current Workflow

Developer pushes code to main

GitHub sends webhook event to Jenkins (via ngrok tunnel)

Jenkins pulls latest code, runs mvn clean package, and shows build status

Build results visible in Jenkins UI

📌 Next Steps

Add Unit Tests and run them as part of pipeline

Add Dockerfile for this microservice and enable Jenkins to build & run the container

Deploy to a free service (Render, Railway, or Docker Desktop)