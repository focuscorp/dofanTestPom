@Library('piper-lib-os') _

//piperPipeline script: this

node() {
   
    stage('prepare') {
       //cleanWs()
       checkout scm
       setupCommonPipelineEnvironment script:this
       
    }
   
   stage('init'){
      buildTool maven
      script: this
   }
   
   stage('build') {
      mavenBuild script: this
   }
   
   stage('Pull-Request Voting') {
      buildTool kaniko
      script: this
   }
   
   
   /*stage('Confirm'){
      script: this
   }*/
  
}
