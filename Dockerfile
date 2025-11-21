# Dockerfile for Jenkins with Maven + Docker + Docker Compose
FROM jenkins/jenkins:lts

USER root

RUN apt-get update && \
    apt-get install -y sudo curl git docker.io docker-compose maven && \
    rm -rf /var/lib/apt/lists/*

RUN usermod -aG docker jenkins

USER jenkins

RUN jenkins-plugin-cli --plugins workflow-aggregator docker-workflow blueocean

# exposed ports
EXPOSE 8080 50000
