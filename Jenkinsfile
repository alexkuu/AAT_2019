#!groovy

node('master') {

  dir("${pwd().tokenize('@')[0]}@script") {

    stage('Checkout') {
      checkout scm
    }

    stage('Tests') {
      sh "mvn test"
    }

  }
}