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
//                  version: "Version_${env.BUILD_ID}",  
                  version: "Version_latest",  
                  repository: 'DemoApiRepository',
                  credentialsId: 'nexuslogin',
                  artifacts: [
                      [artifactId: 'demoApi',
                       classifier: '',
                       file: 'target/demo-api-1.jar',
                       type: 'jar']
                  ]
               )
            }
        }
      
        stage('Download Artifact from Nexus') {
            steps {
                script {
                    def nexusUrl = 'http://192.168.0.15:8081/repository/DemoApiRepository/'
                    def artifactPath = 'com/example/demoApi/Version_latest/demo-api-1.jar'
                    def downloadLocation = 'demo-api-1.jar'
                    
                    sh "curl -o ${downloadLocation} ${nexusUrl}${artifactPath}"
                }
            }
        }
      
        stage('Docker Build') {
            steps {
                script {
                    def dockerImageName = 'demo-api-1.jar'
                    //def dockerfileLocation = './Dockerfile'

                    sh "docker --version --privileged -v /var/run/docker.sock:/var/run/docker.sock"
                    sh "docker build -t ${dockerImageName} ."
                }
            }
        }
      
        stage('Docker Run') {
            steps {
                script {
                    def dockerImageName = 'demo-api-1.jar'

                    sh "	docker run -p 9090:8090 ${dockerImageName}"
                }
            }
        }
        
  //      stage('Deploy') {
  //          steps {
  //              sh 'java -jar target/demoApi-0.0.1-SNAPSHOT.jar'
  //          }
  //      }
    }
}
