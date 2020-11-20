pipeline{
    agent any
    stages{
        stage("run frontend") {
            steps {
                echo 'Executing Yarn ...'
                nodejs('')
            }
        }
        stage("run backend"){
            steps{
                echo 'Executing Gradle...'
            }
        }
    }
}
