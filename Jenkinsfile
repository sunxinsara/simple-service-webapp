pipeline{
    agent any
    tools{
        maven "maven3"
    }
    
    environment {
        SONAR_TOKEN = credentials('SONAR_TOKEN')
        SONAR_HOST_URL = 'https://sonarcloud.io'
        SONAR_ORGANIZATION = 'sunxinsara'
        SONAR_PROJECT_KEY = 'sunxinsara_simple-service-webapp'
    }
    
    stages{
        stage('Build'){
            steps{
                git 'https://github.com/sunxinsara/simple-service-webapp.git'
                bat "mvn clean package -DskipTests"
            }
            
            post{
                always{
                    echo 'stage post always'
                }
                
                
                success{
                    echo 'pipeline post success'
                }
                
                failure{
                    echo 'pipeline post failure'
                }
                
            }
        }
        
        stage('Test') {
            steps {
                bat "mvn test jacoco:prepare-agent jacoco:report"
            }

            post {
                always {
                    jacoco(execPattern: '**/target/jacoco.exec')
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('SonarCloud Analysis') {
            steps {
                // Avoid redundant work
                bat "mvn sonar:sonar -Dsonar.token=%SONAR_TOKEN% -Dsonar.host.url=%SONAR_HOST_URL% -Dsonar.organization=%SONAR_ORGANIZATION% -Dsonar.projectKey=%SONAR_PROJECT_KEY% -Dsonar.language=java -Dsonar.sources=src/main/java -Dsonar.exclusions=**/*.css,**/*.js,**/*.jsp,**/*.xml"
            }
        }
        
    }
}