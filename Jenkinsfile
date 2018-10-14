pipeline {
    agent {
        docker {
            image 'maven:3-jdk-8-alpine'
        }
    }
    options {
        buildDiscarder (logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        timeout (time: 1, unit: 'HOURS')
    }
    stages {
        stage('compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('test') {
            steps {
                sh 'mvn verify'
            }
        }
        stage('sonar') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn -DskipTests install'
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }
    post {
        always {
            junit (allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml')

            archiveArtifacts (allowEmptyArchive: true, artifacts: '**/target/coverage-reports/jacoco-unit.exec', fingerprint: true)
            archiveArtifacts (allowEmptyArchive: true, artifacts: '**/target/surefire-reports/*.xml', fingerprint: true)

            archiveArtifacts (allowEmptyArchive: true, artifacts: '**/target/*.jar', fingerprint: true)
            archiveArtifacts (allowEmptyArchive: true, artifacts: '**/target/*.amp', fingerprint: true)
            archiveArtifacts (allowEmptyArchive: true, artifacts: '**/target/*.war', fingerprint: true)

            archiveArtifacts (allowEmptyArchive: true, artifacts: 'target/sonar/report-task.txt', fingerprint: true)

            cleanWs ()
        }
        failure {
            mail (to: 'migalvcon@gmail.com',
                subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                body: "Something is wrong with ${env.BUILD_URL}")
        }
    }
}
