pipeline {

    agent any
    stages {
        stage('Clone') {
            steps {
                    url: 'https://github.com/Eyad-Amer/Jenkins-Closing-App.git'
            }
        }
        stage('Build') {
            steps{
                sh 'mvn clean package'
            }
        }
        stage('Artifact') {
            steps{
                archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
            }
        }
    }
}