#!groovy

pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2'
    }
  }
  stages {
    step('Checkout') {
      checkout scm
    }
    step('Test') {
      steps {
        sh 'mvn -test'
      }
    }
  }
}