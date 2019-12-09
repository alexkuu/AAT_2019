#!groovy

node('master') {

  stage('Checkout') {
    checkout scm
  }

  stage('Tests') {
    sh "mvn test"
  }

}