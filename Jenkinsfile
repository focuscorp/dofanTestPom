@Library('piper-lib-os') _

node() {
   
    stage('Init') {
       cleanWs()
       checkout scm
       setupCommonPipelineEnvironment script:this
       
    }
    stage('Build Stage') {
       mavenExecute(
         script: this,
         goals: ['install']
      )
    }
 
    stage('Unit Tests Stage') {
       mavenExecute(
           script: this,
           goals: ['test']
           )
       testsPublishResults(
           script: this,
           jacoco: true
       )
    }
      
    stage('Integration Stage') {
      mavenExecuteIntegration script: this
    }
}
