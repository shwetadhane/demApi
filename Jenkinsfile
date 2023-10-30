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
                 echo "Upload artifact Successful" 
            }
        }
        stage('Deploy') {
            steps {
                sh 'java -jar target/my-app.jar'
            }
        }
    }
}
