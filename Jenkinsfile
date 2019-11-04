pipeline {
    docker {
        image 'maven:3-alpine'
        args '-v /root/.m2:/root/.m2'
    }

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