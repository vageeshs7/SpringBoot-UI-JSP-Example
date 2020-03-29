pipeline {
    agent any
    tools { 
        maven 'Maven-3.6.3' 
        jdk 'OpenJDK-8' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true clean package install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
        stage ('Docker Image') {
            steps {
                script{
                    docker.build "springboot-ui-jsp-example:2.${env.BUILD_ID}"
                }
                
            }
        }
        stage ('Kubernetes Deployment') {
            steps {
                sh 'kubectl set image deployment/springboot-ui-jsp-ex springboot-ui-jsp-ex=springboot-ui-jsp-example:2.${env.BUILD_ID} --record'                
            }
        }
    }
}