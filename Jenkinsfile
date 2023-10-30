pipeline {

  options {
    ansiColor('xterm')
  }
    tools{
        maven 'maven'
    }

    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -DskipTests=true clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('SonarQube Analysis') {
          //def scannerHome = tool 'SonarQubeScanner';
            steps {
                withSonarQubeEnv('sonarqube-server'){
                //sh "${scannerHome}/bin/sonar-scanner"
                sh "mvn sonar:sonar"  
                }                  
            }
        }
        stage('Upload artifact') {
            steps {
                  nexusArtifactUploader(
                  nexusVersion: 'nexus3',
                  protocol: 'http',
                  nexusUrl: '192.168.0.15:8081',
                  groupId: 'com.example',
//                  version: "Version${env.BUILD_ID}_${env.BUILD_TIMESTAMP}",
                  version: "Version_${env.BUILD_ID}",  
                  repository: 'DemoApiRepository',
                  credentialsId: 'nexuslogin',
                  artifacts: [
                      [artifactId: 'demoApi',
                       classifier: '',
                       file: 'target/demoApi-0.0.1-SNAPSHOT.jar',
                       type: 'jar']
                  ]
               )
            }
        }
  //      stage('Deploy') {
  //          steps {
  //              sh 'java -jar target/demoApi-0.0.1-SNAPSHOT.jar'
  //          }
  //      }
    }
}
