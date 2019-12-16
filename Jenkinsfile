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
      def targetDir = "/var/lib/jenkins/userContent/Branch-check/${env.BRANCH_NAME}/${env.BUILD_ID}/report"
      def source = "/var/lib/jenkins/workspace/Branch-check_${env.BRANCH_NAME}/target/surefire-reports/*"
      sh "mkdir -p ${targetDir}"
      sh "cp -r ${source} ${targetDir}"
      sh "echo Report can be found at: ${env.JENKINS_URL}userContent/Branch-check/${env.BRANCH_NAME}/${env.BUILD_ID}/report/index.html"
    }
  }

}