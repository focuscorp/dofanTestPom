@Library('piper-lib-os') _

//piperPipeline script: this

node() {
   
    stage('init') {
       cleanWs()
       checkout scm
       setupCommonPipelineEnvironment script:this
       
    }
   
   stage('Pull-Request Voting') {
      mavenExecute(
         script: this,
         goals: ['findbugs:findbugs','pmd:pmd','checkstyle:checkstyle']
      )
  /*    checksPublishResults(
        script: this,
        // publish java results from pmd, cpd, checkstyle & findbugs
        pmd: true, findbugs: true, checkstyle: true
      )*/
   }
  
   
   stage('build') {
      mavenExecute(
         script: this,
         goals: ['install']
      )
   }
      
   stage('Unit tests') {
   mavenExecute(
       script: this,
       goals: ['test']
       // publish test results with coverage
        )
      testsPublishResults(
         script: this,
         jacoco: true
      )
   }
   
   stage('nexusUpload') {
      mavenExecute(
         script: this,
         goals: ['deploy']
      )
  }
   
 
   
    stage('Integration') {
      mavenExecuteIntegration script: this
   }
   
   stage('deploy') {
      deployType: 'standard'
      deployTool: 'cf_native'
      cloudFoundryDeploy(
         script: this,
         cloudFoundry: [apiEndpoint: 'https://api.cf.eu10.hana.ondemand.com', appName: 'dofansecurity', manifest: './manifest.yml', org: '5955a6d8trial', space: 'dev', credentialsId: 'CF_NadimCredential']
        )
   }
   
}
