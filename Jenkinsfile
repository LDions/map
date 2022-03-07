#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }

    gitlabCommitStatus('build') {
        stage('check java') {
            sh "java -version"
        }

        stage('clean') {
            sh "chmod +x gradlew"
            sh "./gradlew clean --no-daemon"
        }

        stage('packaging') {
            sh "./gradlew bootJar -x test -Pdev --no-daemon"
        }

        stage('copying') {
            sh "scp build/libs/*.jar root@192.168.1.160:/data/projects/ruoweiApp/"
        }

        stage("restart") {
            sh "ssh root@192.168.1.160 fuser -k 6100/tcp || true"
            sh "ssh root@192.168.1.160 \"cd /data/projects/ruoweiApp && nohup java -jar ./*.jar >./log.log 2>&1 &\""
        }
    }
}
