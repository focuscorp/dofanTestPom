@Library('piper-lib-os') _

//piperPipeline script: this

node() {
   
    stage('init') {
       //cleanWs()
       checkout scm
       setupCommonPipelineEnvironment script:this
       
    }
   
   stage('Pull-Request Voting') {
      buildTool: 'kaniko'
      script: this
   }
   
   stage('build') {
      mavenBuild script: this
   }
   
   stage('deploy') {
      cloudFoundryDeploy(
         script: this,
         cloudFoundry: [apiEndpoint: 'https://api.cf.eu10.hana.ondemand.com', org: '5955a6d8trial', space: 'dev', credentialsId: 'CF_NadimCredential'],
         deployTool: 'cf_native',
         deployType: 'standard')
   }
  
}
