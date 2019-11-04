pipeline {
    agent any

    stages {
        stage('Build compile'){
            steps{
                echo 'Bulding and compile'
                sh 'mvn compile'
            }
        }

        stage('Testing'){
            steps{
                echo 'Testin'
                sh 'mvn test'
            }
        }
    }
}