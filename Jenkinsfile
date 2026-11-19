pipeline {
  agent any
  tools { jdk 'JDK17'; maven 'Maven3' }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Build and Unit Test') { steps { script { if (isUnix()) { sh 'mvn -B clean verify' } else { bat 'mvn -B clean verify' } } } }
    stage('Static Analysis') { steps { script { if (isUnix()) { sh 'mvn -B pmd:pmd spotbugs:spotbugs' } else { bat 'mvn -B pmd:pmd spotbugs:spotbugs' } } } }
  }
  post {
    always {
      junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
      archiveArtifacts allowEmptyArchive: true, artifacts: 'target/site/jacoco/**, target/site/pmd.html, target/spotbugsXml.xml'
    }
  }
}
