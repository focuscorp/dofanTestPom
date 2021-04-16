@Library('piper-lib-os') _

//piperPipeline script: this

node() {
   
    stage('init') {
       cleanWs()
       checkout scm
       setupCommonPipelineEnvironment script:this
       
    }
   
   stage('Pull-Request Voting') {
      script: this
      mavenExecuteStaticCodeChecks script: this
   }
   
   stage('build') {
      mavenBuild script: this
   }
   
   stage('Additional Unit Tests'){
      script: this
   }
   
   /*stage('deploy') {
      deployType: 'standard'
      deployTool: 'cf_native'
      cloudFoundryDeploy(
         script: this,
         cloudFoundry: [apiEndpoint: 'https://api.cf.eu10.hana.ondemand.com', appName: 'dofansecurity', manifest: './manifest.yml', org: '5955a6d8trial', space: 'dev', credentialsId: 'CF_NadimCredential']
        )
   }*/
 
   
}
