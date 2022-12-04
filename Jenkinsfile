pipeline {
    
agent any

    stages {
        stage('Clone') {
            steps {
                   url: 'https://github.com/Eyad-Amer/Jenkins-Closing-App.git'
            }
        }
        
        
    stage('Build Docker') {
      steps {
        sh 'docker build -t ynet:$BUILD_NUMBER .'
      }
    }
     stage('Build Tag') {
        steps{
            sh 'docker tag ynet:$BUILD_NUMBER eyaddocker/ynet:$BUILD_NUMBER'
        }
         
     }
    
    stage('Push Docker') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'duker-hub', passwordVariable: 'pass', usernameVariable: 'username')]) {
                    // the code here can access $pass and $user
                    sh 'docker login -u ${username} -p ${pass}'
                    sh 'docker push eyaddocker/ynet:$BUILD_NUMBER'
                }
            }
        }
    
        
}
}
