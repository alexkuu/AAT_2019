#!groovy

node('master') {

  def targetPath = env.BRANCH_NAME == null ? "Master/${env.BUILD_ID}" : "Branch-check/${env.BRANCH_NAME}/${env.BUILD_ID}"
  def sourcePath = env.BRANCH_NAME == null ? "Master-trigger" : "Branch-check_${env.BRANCH_NAME}"

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
      def targetDir = "/var/lib/jenkins/userContent/${targetPath}/report"
      def source = "/var/lib/jenkins/workspace/${sourcePath}/target/surefire-reports/*"
      sh "mkdir -p ${targetDir}"
      sh "cp -r ${source} ${targetDir}"
      sh "echo Report can be found at: ${env.JENKINS_URL.toString().replace("8080", "1818")}userContent/${targetPath}/report/index.html"
    }
  }

}