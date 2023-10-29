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
        stage('Deploy') {
            steps {
                sh 'java -jar target/my-app.jar'
            }
        }
    }
}
