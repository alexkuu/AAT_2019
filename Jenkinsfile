#!groovy

node('master') {

  stage('Checkout') {
    checkout scm
  }

  try {
    stage('Tests') {
      sh "mvn test"
    }
  } catch (ignored) {
  }
  finally {
    stage('Report') {
      def targetDir = "/jenkins/jenkins_home/userContent/PR-check/${env.BRANCH_NAME}/${env.BUILD_ID}/report"
      def source = "/jenkins/jenkins_home/workspace/PR-check_${env.BRANCH_NAME}/target/surefire-reports/*"
      sh "mkdir -p ${targetDir}"
      sh "cp -r ${source} ${targetDir}"
    }
  }

}