@Library('piper-lib-os') _

//piperPipeline script: this

node() {
   
    stage('init') {
       cleanWs()
       checkout scm
       setupCommonPipelineEnvironment script:this
       
    }
   
   /*stage('Pull-Request Voting') {
     
     /* mavenExecuteStaticCodeChecks(
         script: this,
         pmd: true, cpd: true, findbugs: true, checkstyle: true, spotbugs: false,
         
      ) */
     /* mavenExecute(
         script: this,
         goals: ['checkstyle:checkstyle']
      )
       checksPublishResults(
        script: this,
        // publish java results from pmd, cpd, checkstyle & findbugs
        //pmd: true, cpd: true, findbugs: true, checkstyle: true,*/
         //pmd: [pattern: '**/target/pmd.xml', qualityGates: [[threshold: 101, type: 'TOTAL_LOW', unstable: true]]],
       //   checkstyle: [pattern: '**/target/checkstyle-result.xml'],
     /*   tasks: true,
        archive: true
      )
   }*/
  
   
    /*stage('Pull-Request Voting') {
      mavenExecuteStaticCodeChecks(
         script: this,
         pmd: [maxAllowedViolations: 50]
      )
   }*/
   
   stage('build') {
      mavenBuild script: this
   }
  
  
   /*stage('Additional Unit Tests'){
      // publish test results with coverage
      testsPublishResults(*/
        // jacoco: [pattern: '**/target/*.exec']
    /*  )
   }*/
   
   /* stage('Integration') {
      mavenExecuteIntegration script: this
   }*/
   
   stage('deploy') {
      deployType: 'standard'
      deployTool: 'cf_native'
      cloudFoundryDeploy(
         script: this,
         cloudFoundry: [apiEndpoint: 'https://api.cf.eu10.hana.ondemand.com', appName: 'dofansecurity', manifest: './manifest.yml', org: '5955a6d8trial', space: 'dev', credentialsId: 'CF_NadimCredential']
        )
   }
   
    stage('nexusUpload'){
      nexusUpload(
         script: this,
         username: 'nexus_manvenuser',
         password: 'nexus_manvenuser',
         nexus: [mavenRepository: 'maven-public', url: 'http://artefact.focus.com.tn:8081', username: 'nexus_manvenuser', password: 'nexus_manvenuser', nexusCredentialsId: 'nexus_manvenuser'])
   }
 
   
}
