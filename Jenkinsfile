pipeline {
    agent any
    stages {
        stage('Build compile'){
            steps{
                withMaven(){
                    echo 'Bulding and compile'
                    sh 'mvn compile'
                }
            }
        }

        stage('Testing'){
            steps{
                withMaven(){
                    echo 'Testin'
                    sh 'mvn test'
                }
            }
        }
    }
}