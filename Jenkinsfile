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
        )
      testsPublishResults(
         script: this,
         jacoco: true
      )
   
    stage('Integration Stage') {
      mavenExecuteIntegration script: this
   }

    stage('nexusUpload') {
        nexusUpload (
         script: this,
         url: 'artefact.focus.com.tn:8081',
         mavenRepository:'maven-snapshots',
         nexusCredentialsId:'nexus_manvenuser',
         format:'maven',
         globalSettingsFile:'.pipeline/global_settings.xml'
           )
  }
         
   stage('Release Stage') {
      deployType: 'standard'
      deployTool: 'cf_native'
      cloudFoundryDeploy(
         script: this,
         cloudFoundry: [apiEndpoint: 'https://api.cf.eu10.hana.ondemand.com', appName: 'dofansecurity', manifest: './manifest.yml', org: '5955a6d8trial', space: 'dev', credentialsId: 'CF_NadimCredential']
        )
   }
   
}
