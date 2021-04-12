@Library('piper-lib-os') _

//piperPipeline script: this

node() {
   
    stage('prepare') {
       //cleanWs()
       checkout scm
       setupCommonPipelineEnvironment script:this
       
    }
stage('build') {
    mavenBuild script: this
}

}
