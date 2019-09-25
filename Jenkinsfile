
def project = 'cryptobeer'
def appName = 'cryptobeer'
def property = "${params.PROPERTIES}"
def imageTag = "gcr.io/${project}/${appName}-telegram-bot:${env.BUILD_NUMBER}"

pipeline {
  agent {
    kubernetes {
      label 'jenkins-backend'
      defaultContainer 'jnlp'
      yaml """
            apiVersion: v1
            kind: Pod
            metadata:
            labels:
              component: ci
            spec:
              # Use service account that can deploy to all namespaces
              serviceAccountName: cd-jenkins
              containers:
              - name: gcloud
                image: gcr.io/cloud-builders/gcloud
                command:
                - cat
                tty: true
              - name: kubectl
                image: gcr.io/cloud-builders/kubectl
                command:
                - cat
                tty: true
          """
    }
  }
  stages {
    stage('Build and Push docker image to GCR') {
      steps {
        container('gcloud') {
          sh "sed -i.bak 's#--spring.profiles.active=#--spring.profiles.active=${property}#' Dockerfile"
          sh "gcloud builds submit -t ${imageTag} ."
        }
      }
    }
    stage('Deploy') {
      steps {
        container('kubectl') {
          sh("sed -i.bak 's#gcr.io/${project}/${appName}-telegram-bot:1#${imageTag}#' ./k8s/deploy/deploy-telegram-bot.yaml")
          sh("kubectl apply -f k8s/deploy/")
          sh("kubectl apply -f k8s/service/")
        }
      }
    }
  }
}