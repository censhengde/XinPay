pipeline {
  agent any
  environment {
    ENTERPRISE = "ringle"
    PROJECT = "xinpay"
    ARTIFACT = "xinpay-android"
    CODE_DEPOT = "xinpay-android"
    
    ARTIFACT_BASE = "${ENTERPRISE}-generic.pkg.coding.net"
    ARTIFACT_IMAGE = "${ARTIFACT_BASE}/${PROJECT}/${ARTIFACT}/${CODE_DEPOT}"
  }
  stages {
    stage('检出') {
      agent any
      steps {
        checkout([$class: 'GitSCM', branches: [[name: env.GIT_BUILD_REF]],
                          userRemoteConfigs: [[url: env.GIT_REPO_URL, credentialsId: env.CREDENTIALS_ID]]])
      }
    }
    stage('构建 APK') {
      agent any
      steps {
        sh './gradlew assemble'
      }
    }
    stage('归档 APK') {
      agent any
      steps {
        script {
          sh "curl -T app/build/outputs/apk/debug/*.apk -u ${PROJECT_TOKEN_GK}:${PROJECT_TOKEN} https://${ARTIFACT_IMAGE}-debug.apk?version=${GIT_BUILD_REF}"
        }
      }
    }
  }
}