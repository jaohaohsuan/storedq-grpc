def podLabel = "${env.JOB_NAME}-${env.BUILD_NUMBER}".replace('/', '-').replace('.', '')
def scalaVersion = "2.12.3"

podTemplate(
        label: podLabel,
        containers: [
                containerTemplate(name: 'jnlp', image: env.JNLP_SLAVE_IMAGE, args: '${computer.jnlpmac} ${computer.name}', alwaysPullImage: true),
                containerTemplate(name: 'sbt', image: "${env.PRIVATE_REGISTRY}/library/sbt:${scalaVersion}-fabric8", ttyEnabled: true, command: 'cat', alwaysPullImage: true),
                containerTemplate(name: 'dind', image: 'docker:stable-dind', privileged: true, ttyEnabled: true, command: 'dockerd', args: '--host=unix:///var/run/docker.sock --host=tcp://0.0.0.0:2375 --storage-driver=vfs')
        ],
        volumes: [
                emptyDirVolume(mountPath: '/var/run', memory: false),
                hostPathVolume(mountPath: "/etc/docker/certs.d/${env.PRIVATE_REGISTRY}/ca.crt", hostPath: "/etc/docker/certs.d/${env.PRIVATE_REGISTRY}/ca.crt"),
                persistentVolumeClaim(claimName: env.JENKINS_IVY2, mountPath: '/home/jenkins/.ivy2', readOnly: false)
        ]) {

    node(podLabel) {
        ansiColor('xterm') {
            stage('build') {
                checkout scm
                container('sbt') {
                    sh "sbt compile"
                    sh "sbt publishLocal"
                }
            }
            stage('archive') {
                sh 'mkdir -p protobuf'
                sh "cp -r target/protobuf_external/* protobuf/"
                sh 'cp -r src/main/protobuf/* protobuf/'
                sh 'which tar'
                sh 'tar cfvz protobuf.tar.gz protobuf'

                archiveArtifacts artifacts: 'protobuf.tar.gz', defaultExcludes: false, onlyIfSuccessful: true
            }
        }
    }
}