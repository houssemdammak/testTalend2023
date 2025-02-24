$fileDir = Split-Path -Parent $MyInvocation.MyCommand.Path
cd $fileDir
java '-Dtalend.component.manager.m2.repository=%cd%/../lib' '-Xms256M' '-Xmx1024M' '-Xshare:off' '-Djavaagent:../lib/talendagent-1.0.2.jar=bootjar=../lib/talendboot-1.0.9.jar' '--add-opens=java.base/java.net=ALL-UNNAMED' '--add-opens=java.base/sun.net.www.protocol.https=ALL-UNNAMED' -cp '.;../lib/routines.jar;../lib/log4j-slf4j-impl-2.17.1.jar;../lib/log4j-api-2.17.1.jar;../lib/log4j-core-2.17.1.jar;../lib/log4j-layout-template-json-2.17.1.jar;../lib/cxf-rt-frontend-jaxrs-3.5.9.jar;../lib/opensaml-profile-api-3.4.6.jar;../lib/wss4j-bindings-2.4.1.jar;../lib/wsdl4j-1.6.3.jar;../lib/aopalliance-1.0.jar;../lib/xmlsec-2.2.6.jar;../lib/cxf-rt-rs-security-oauth2-3.5.9.jar;../lib/ehcache-3.9.3.jar;../lib/jakarta.xml.soap-api-1.4.1.jar;../lib/jakarta.xml.bind-api-2.3.3.jar;../lib/wss4j-ws-security-common-2.4.1.jar;../lib/spring-context-5.3.39.jar;../lib/spring-expression-5.3.39.jar;../lib/cxf-rt-features-logging-3.5.9.jar;../lib/javax.activation-1.2.0.jar;../lib/wss4j-ws-security-policy-stax-2.4.1.jar;../lib/txw2-2.3.3.jar;../lib/opensaml-saml-api-3.4.6.jar;../lib/cxf-rt-rs-extension-providers-3.5.9.jar;../lib/commons-logging-1.2.jar;../lib/javax.ws.rs-api-2.1.jar;../lib/jakarta.jws-api-2.1.0.jar;../lib/spring-webmvc-5.3.39.jar;../lib/cxf-rt-security-saml-3.5.9.jar;../lib/opensaml-xacml-api-3.4.6.jar;../lib/stax-ex-1.8.3.jar;../lib/jakarta.annotation-api-1.3.5.jar;../lib/wss4j-ws-security-stax-2.4.1.jar;../lib/jasypt-1.9.3.jar;../lib/spring-core-5.3.39.jar;../lib/opensaml-xacml-saml-api-3.4.6.jar;../lib/opensaml-saml-impl-3.4.6.jar;../lib/spring-web-5.3.39.jar;../lib/cryptacular-1.2.5.jar;../lib/spring-beans-5.3.39.jar;../lib/cxf-rt-databinding-jaxb-3.5.9.jar;../lib/jettison-1.5.4.jar;../lib/opensaml-xacml-impl-3.4.6.jar;../lib/jaxb-runtime-2.3.2.jar;../lib/cxf-rt-wsdl-3.5.9.jar;../lib/commons-codec-1.15.jar;../lib/istack-commons-runtime-3.0.10.jar;../lib/cxf-core-3.5.9.jar;../lib/cxf-rt-ws-security-3.5.9.jar;../lib/cxf-rt-rs-client-3.5.9.jar;../lib/joda-time-2.10.10.jar;../lib/security-common-8.0.1.R2024-05-RT.jar;../lib/opensaml-xmlsec-api-3.4.6.jar;../lib/stax2-api-4.2.1.jar;../lib/cxf-rt-security-3.5.9.jar;../lib/cxf-rt-transports-http-3.5.9.jar;../lib/opensaml-security-impl-3.4.6.jar;../lib/wss4j-ws-security-dom-2.4.1.jar;../lib/opensaml-security-api-3.4.6.jar;../lib/spring-aop-5.3.39.jar;../lib/neethi-3.1.1.jar;../lib/bcprov-jdk18on-1.78.1.jar;../lib/org.apache.servicemix.specs.jaxws-api-2.2-2.9.0.jar;../lib/opensaml-core-3.4.6.jar;../lib/cxf-rt-bindings-soap-3.5.9.jar;../lib/opensaml-xmlsec-impl-3.4.6.jar;../lib/woodstox-core-6.4.0.jar;../lib/wss4j-policy-2.4.1.jar;../lib/opensaml-xacml-saml-impl-3.4.6.jar;../lib/xmlschema-core-2.2.5.jar;../lib/guava-32.0.1-jre.jar;../lib/opensaml-soap-api-3.4.6.jar;../lib/talend_file_enhanced-1.3.1.jar;../lib/json-smart-2.4.11.jar;../lib/job-audit-1.5.jar;../lib/system-routines-dq.jar;../lib/org.talend.dataquality.parser.jar;../lib/talendboot-1.0.9.jar;../lib/commons-lang3-3.10.jar;../lib/crypto-utils-7.1.20.jar;../lib/audit-common-1.16.1.jar;../lib/jboss-marshalling-2.0.12.Final.jar;../lib/javassist-3.30.2-GA.jar;../lib/dom4j-2.1.3.jar;../lib/slf4j-api-1.7.34.jar;../lib/accessors-smart-2.4.11.jar;../lib/antlr-runtime-3.5.2.jar;../lib/json-path-2.9.0.jar;../lib/talendagent-1.0.2.jar;../lib/audit-log4j2-1.16.1.jar;../lib/logging-event-layout-1.16.1.jar;../lib/asm-9.5.jar;../lib/system-routines.jar;../lib/talendCBP-1.1.5.jar;myrestservice_0_1.jar;' web_service_talend.myrestservice_0_1.MyRESTService --context=Default $args
