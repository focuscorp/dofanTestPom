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

    stage('Nexus Upload Stage') {
        nexusUpload (
         script: this,
         url: 'artefact.focus.com.tn:8081',
         mavenRepository:'maven-snapshots',
         nexusCredentialsId:'nexus_manvenuser',
         format:'maven',
         globalSettingsFile:'.pipeline/global_settings.xml'
           )
    }
     
    stage('Deploy Stage') {
      deployType: 'standard'
      deployTool: 'cf_native'
      cloudFoundryDeploy(
         script: this,
         cloudFoundry: [apiEndpoint: 'https://api.cf.eu10.hana.ondemand.com', appName: 'rahmajdid', manifest: './manifest.yml', org: '5955a6d8trial', space: 'dev', credentialsId: 'CF_NadimCredential']
        )
    }
   }
