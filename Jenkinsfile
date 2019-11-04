pipeline {
    agent any
    stages {
        stage('Build compile'){
            steps{
                withMaven(maven: "maven"){
                    echo 'Bulding and compile'
                    sh 'mvn compile'
                }
            }
        }

        stage('Testing'){
            steps{
                 withMaven(maven: "maven"){
                    echo 'Testin'
                    sh 'mvn test'
                }
            }
        }
    }
}