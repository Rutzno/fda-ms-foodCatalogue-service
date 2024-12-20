pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('DOCKER_HUB_CREDENTIAL')
        VERSION = "${env.BUILD_ID}"
        GIT_REPO = "git@github.com:Rutzno/fda-deployment.git"
        GIT_BRANCH = "main"
        SONAR_URL = "http://sonarqube2:9000/"
    }

    tools {
        maven "Maven"
    }

    stages {
        stage("Maven BUILD") {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage("Run TESTS") {
            steps {
                sh "mvn test"
            }
        }

        stage("Sonar Analysis QUALITY") {
            steps {
                sh "mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.host.url=${SONAR_URL} -Dsonar.login=squ_8f818a0cc7150c1085bc6e5a4bea49c226a93196"
            }
        }

        stage("Check code Coverage QUALITY") {
            steps {
                script {
                    def token = "squ_8f818a0cc7150c1085bc6e5a4bea49c226a93196"
                    def sonarQubeUrl = "${SONAR_URL}api"
                    def componentKey = "com.diarpy:foodCatalogue-service"
                    def coverageThreshold = 80.0
                    def response = sh (
                        script: "curl -H 'Authorization: Bearer ${token}' '${sonarQubeUrl}/measures/component?component=${componentKey}&metricKeys=coverage'",
                        returnStdout: true
                    ).trim()
                    def coverage = sh (
                        script: "echo '${response}' | jq -r '.component.measures[0].value'",
                        returnStdout: true
                    ).trim().toDouble()
                    echo "Coverage: ${coverage}"
                    if (coverage < coverageThreshold) {
                        error "Coverage is below the threshold of ${coverageThreshold}%. Aborting the pipeline."
                    }
                }
            }
        }

        stage("Docker Build & Push PACKAGE") {
            steps {
                sh "echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin"
                sh "docker build -t macktb/foodcatalogue-service:${VERSION} ."
                sh "docker push macktb/foodcatalogue-service:${VERSION}"
            }
        }

        stage("Cleanup Workspace") {
            steps {
                deleteDir()
            }
        }

        stage("Update Image Tag in GitOps") {
            steps {
                checkout scmGit(branches: [[name: "*/${GIT_BRANCH}"]], extensions: [], userRemoteConfigs: [[ credentialsId: 'git-ssh', url: "${GIT_REPO}"]])
                script {
                    // Set the new image tag with the Jenkins build number
                    sh '''
			git config user.email "d.rutzno@gmail.com"
                	git config user.name "Rutzno"
                        sed -i "s/image:.*/image: macktb\\/foodcatalogue-service:${VERSION}/" aws/foodcatalogue-manifest.yaml
			git checkout ${GIT_BRANCH}
                    	git add .
                    	git commit -m "Update image tag to ${VERSION}"
                    '''
                    sshagent(["git-ssh"]) {
                        sh("git push")
                    }
                }
            }
        }
    }
}