#!groovy

node('master') {

  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2'
    }
  }

  dir("${pwd().tokenize('@')[0]}@script") {

    stage('Checkout') {
      checkout scm
    }

    stage('Tests') {
      sh "mvn test"
    }

  }
}