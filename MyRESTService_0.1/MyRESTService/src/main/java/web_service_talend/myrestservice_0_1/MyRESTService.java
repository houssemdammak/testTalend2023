
package web_service_talend.myrestservice_0_1;

import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.DataQuality;
import routines.Relational;
import routines.Mathematical;
import routines.DataQualityDependencies;
import routines.SQLike;
import routines.Numeric;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.MDM;
import routines.StringHandling;
import routines.DQTechnical;
import routines.TalendDate;
import routines.DataMasking;
import routines.DqStringHandling;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;
 





@SuppressWarnings("unused")

/**
 * Job: MyRESTService Purpose: <br>
 * Description:  <br>
 * @author Dammak, Houssem
 * @version 8.0.1.20250126_0750-patch
 * @status 
 */
public class MyRESTService implements TalendJob {
	static {System.setProperty("TalendJob.log", "MyRESTService.log");}

	

	
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(MyRESTService.class);
	

protected static void logIgnoredError(String message, Throwable cause) {
       log.error(message, cause);

}


	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}
	
	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	
	private final static String utf8Charset = "UTF-8";

	public static String taskExecutionId = null;

	public static String jobExecutionId = java.util.UUID.randomUUID().toString();;
	

	//contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String,String> propertyTypes = new java.util.HashMap<>();
		
		public PropertiesWithType(java.util.Properties properties){
			super(properties);
		}
		public PropertiesWithType(){
			super();
		}
		
		public void setContextType(String key, String type) {
			propertyTypes.put(key,type);
		}
	
		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}	
	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();
		

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties){
			super(properties);
		}
		public ContextProperties(){
			super();
		}

		public void synchronizeContext(){
			
			if(db_host != null){
				
					this.setProperty("db_host", db_host.toString());
				
			}
			
			if(db_port != null){
				
					this.setProperty("db_port", db_port.toString());
				
			}
			
			if(db_name != null){
				
					this.setProperty("db_name", db_name.toString());
				
			}
			
			if(db_user != null){
				
					this.setProperty("db_user", db_user.toString());
				
			}
			
			if(db_password != null){
				
					this.setProperty("db_password", db_password.toString());
				
			}
			
			if(additional_variables != null){
				
					this.setProperty("additional_variables", additional_variables.toString());
				
			}
			
		}
		
		//if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if(NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

public String db_host;
public String getDb_host(){
	return this.db_host;
}
public String db_port;
public String getDb_port(){
	return this.db_port;
}
public String db_name;
public String getDb_name(){
	return this.db_name;
}
public String db_user;
public String getDb_user(){
	return this.db_user;
}
public String db_password;
public String getDb_password(){
	return this.db_password;
}
public String additional_variables;
public String getAdditional_variables(){
	return this.additional_variables;
}
	}
			
	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.
	public ContextProperties getContext() {
		return this.context;
	}

	protected java.util.Map<String, String> defaultProperties = new java.util.HashMap<String, String>();
	protected java.util.Map<String, String> additionalProperties = new java.util.HashMap<String, String>();

	public java.util.Map<String, String> getDefaultProperties() {
	    return this.defaultProperties;
	}

	public java.util.Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

	private final String jobVersion = "0.1";
	private final String jobName = "MyRESTService";
	private final String projectName = "WEB_SERVICE_TALEND";
	public Integer errorCode = null;
	private String currentComponent = "";
	public static boolean isStandaloneMS = Boolean.valueOf("false");
	
	private void s(final String component) {
		try {
			org.talend.metrics.DataReadTracker.setCurrentComponent(jobName, component);
		} catch (Exception | NoClassDefFoundError e) {
			// ignore
		}
	}
	
	
	private void mdc(final String subJobName, final String subJobPidPrefix) {
		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", subJobName);
		org.slf4j.MDC.put("_subJobPid", subJobPidPrefix + subJobPidCounter.getAndIncrement());
	}
	
	
	private void sh(final String componentId) {
		ok_Hash.put(componentId, false);
		start_Hash.put(componentId, System.currentTimeMillis());
	}

	{
	s("none");
	}

	
	private String cLabel =  null;
	
		private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
        private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();
	
		private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
		public  final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();
	

private final JobStructureCatcherUtils talendJobLog = new JobStructureCatcherUtils(jobName, "_ZkuUkO8MEe-KIvSEiRouvQ", "0.1");
private org.talend.job.audit.JobAuditLogger auditLogger_talendJobLog = null;

private RunStat runStat = new RunStat(talendJobLog, System.getProperty("audit.interval"));

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";
	
	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(), new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}
	
	public void setDataSourceReferences(List serviceReferences) throws Exception{
		
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();
		
		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils.getServices(serviceReferences,  javax.sql.DataSource.class).entrySet()) {
                    dataSources.put(entry.getKey(), entry.getValue());
                    talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}


private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

public String getExceptionStackTrace() {
	if ("failure".equals(this.getStatus())) {
		errorMessagePS.flush();
		return baos.toString();
	}
	return null;
}

private Exception exception;

public Exception getException() {
	if ("failure".equals(this.getStatus())) {
		return this.exception;
	}
	return null;
}

private class TalendException extends Exception {

	private static final long serialVersionUID = 1L;

	private java.util.Map<String, Object> globalMap = null;
	private Exception e = null;
	
	private String currentComponent = null;
	private String cLabel =  null;
	
	private String virtualComponentName = null;
	
	public void setVirtualComponentName (String virtualComponentName){
		this.virtualComponentName = virtualComponentName;
	}

	private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
		this.currentComponent= errorComponent;
		this.globalMap = globalMap;
		this.e = e;
	}
	
	private TalendException(Exception e, String errorComponent, String errorComponentLabel, final java.util.Map<String, Object> globalMap) {
		this(e, errorComponent, globalMap);
		this.cLabel = errorComponentLabel;
	}

	public Exception getException() {
		return this.e;
	}

	public String getCurrentComponent() {
		return this.currentComponent;
	}

	
    public String getExceptionCauseMessage(Exception e){
        Throwable cause = e;
        String message = null;
        int i = 10;
        while (null != cause && 0 < i--) {
            message = cause.getMessage();
            if (null == message) {
                cause = cause.getCause();
            } else {
                break;          
            }
        }
        if (null == message) {
            message = e.getClass().getName();
        }   
        return message;
    }

	@Override
	public void printStackTrace() {
		if (!(e instanceof TalendException || e instanceof TDieException)) {
			if(virtualComponentName!=null && currentComponent.indexOf(virtualComponentName+"_")==0){
				globalMap.put(virtualComponentName+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			}
			globalMap.put(currentComponent+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
		}
		if (!(e instanceof TDieException)) {
			if(e instanceof TalendException){
				e.printStackTrace();
			} else {
				e.printStackTrace();
				e.printStackTrace(errorMessagePS);
				MyRESTService.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(MyRESTService.this, new Object[] { e , currentComponent, globalMap});
					break;
				}
			}

			if(!(e instanceof TDieException)){
		if(enableLogStash) {
			talendJobLog.addJobExceptionMessage(currentComponent, cLabel, null, e);
			talendJobLogProcess(globalMap);
		}
			}
		} catch (Exception e) {
			this.e.printStackTrace();
		}
		}
	}
}

			public void tRESTClient_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tExtractJSONFields_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tLogRow_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void talendJobLog_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					talendJobLog_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tRESTClient_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void talendJobLog_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	








public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
    final static byte[] commonByteArrayLock_WEB_SERVICE_TALEND_MyRESTService = new byte[0];
    static byte[] commonByteArray_WEB_SERVICE_TALEND_MyRESTService = new byte[0];

	
			    public Integer id;

				public Integer getId () {
					return this.id;
				}

				public Boolean idIsNullable(){
				    return true;
				}
				public Boolean idIsKey(){
				    return false;
				}
				public Integer idLength(){
				    return null;
				}
				public Integer idPrecision(){
				    return null;
				}
				public String idDefault(){
				
					return null;
				
				}
				public String idComment(){
				
				    return "";
				
				}
				public String idPattern(){
				
					return "";
				
				}
				public String idOriginalDbColumnName(){
				
					return "id";
				
				}

				
			    public String name;

				public String getName () {
					return this.name;
				}

				public Boolean nameIsNullable(){
				    return true;
				}
				public Boolean nameIsKey(){
				    return false;
				}
				public Integer nameLength(){
				    return null;
				}
				public Integer namePrecision(){
				    return null;
				}
				public String nameDefault(){
				
					return null;
				
				}
				public String nameComment(){
				
				    return "";
				
				}
				public String namePattern(){
				
					return "";
				
				}
				public String nameOriginalDbColumnName(){
				
					return "name";
				
				}

				
			    public String company;

				public String getCompany () {
					return this.company;
				}

				public Boolean companyIsNullable(){
				    return true;
				}
				public Boolean companyIsKey(){
				    return false;
				}
				public Integer companyLength(){
				    return null;
				}
				public Integer companyPrecision(){
				    return null;
				}
				public String companyDefault(){
				
					return null;
				
				}
				public String companyComment(){
				
				    return "";
				
				}
				public String companyPattern(){
				
					return "";
				
				}
				public String companyOriginalDbColumnName(){
				
					return "company";
				
				}

				
			    public String username;

				public String getUsername () {
					return this.username;
				}

				public Boolean usernameIsNullable(){
				    return true;
				}
				public Boolean usernameIsKey(){
				    return false;
				}
				public Integer usernameLength(){
				    return null;
				}
				public Integer usernamePrecision(){
				    return null;
				}
				public String usernameDefault(){
				
					return null;
				
				}
				public String usernameComment(){
				
				    return "";
				
				}
				public String usernamePattern(){
				
					return "";
				
				}
				public String usernameOriginalDbColumnName(){
				
					return "username";
				
				}

				
			    public String email;

				public String getEmail () {
					return this.email;
				}

				public Boolean emailIsNullable(){
				    return true;
				}
				public Boolean emailIsKey(){
				    return false;
				}
				public Integer emailLength(){
				    return null;
				}
				public Integer emailPrecision(){
				    return null;
				}
				public String emailDefault(){
				
					return null;
				
				}
				public String emailComment(){
				
				    return "";
				
				}
				public String emailPattern(){
				
					return "";
				
				}
				public String emailOriginalDbColumnName(){
				
					return "email";
				
				}

				
			    public String address;

				public String getAddress () {
					return this.address;
				}

				public Boolean addressIsNullable(){
				    return true;
				}
				public Boolean addressIsKey(){
				    return false;
				}
				public Integer addressLength(){
				    return null;
				}
				public Integer addressPrecision(){
				    return null;
				}
				public String addressDefault(){
				
					return null;
				
				}
				public String addressComment(){
				
				    return "";
				
				}
				public String addressPattern(){
				
					return "";
				
				}
				public String addressOriginalDbColumnName(){
				
					return "address";
				
				}

				
			    public String zip;

				public String getZip () {
					return this.zip;
				}

				public Boolean zipIsNullable(){
				    return true;
				}
				public Boolean zipIsKey(){
				    return false;
				}
				public Integer zipLength(){
				    return null;
				}
				public Integer zipPrecision(){
				    return null;
				}
				public String zipDefault(){
				
					return null;
				
				}
				public String zipComment(){
				
				    return "";
				
				}
				public String zipPattern(){
				
					return "";
				
				}
				public String zipOriginalDbColumnName(){
				
					return "zip";
				
				}

				
			    public String state;

				public String getState () {
					return this.state;
				}

				public Boolean stateIsNullable(){
				    return true;
				}
				public Boolean stateIsKey(){
				    return false;
				}
				public Integer stateLength(){
				    return null;
				}
				public Integer statePrecision(){
				    return null;
				}
				public String stateDefault(){
				
					return null;
				
				}
				public String stateComment(){
				
				    return "";
				
				}
				public String statePattern(){
				
					return "";
				
				}
				public String stateOriginalDbColumnName(){
				
					return "state";
				
				}

				
			    public String country;

				public String getCountry () {
					return this.country;
				}

				public Boolean countryIsNullable(){
				    return true;
				}
				public Boolean countryIsKey(){
				    return false;
				}
				public Integer countryLength(){
				    return null;
				}
				public Integer countryPrecision(){
				    return null;
				}
				public String countryDefault(){
				
					return null;
				
				}
				public String countryComment(){
				
				    return "";
				
				}
				public String countryPattern(){
				
					return "";
				
				}
				public String countryOriginalDbColumnName(){
				
					return "country";
				
				}

				
			    public String phone;

				public String getPhone () {
					return this.phone;
				}

				public Boolean phoneIsNullable(){
				    return true;
				}
				public Boolean phoneIsKey(){
				    return false;
				}
				public Integer phoneLength(){
				    return null;
				}
				public Integer phonePrecision(){
				    return null;
				}
				public String phoneDefault(){
				
					return null;
				
				}
				public String phoneComment(){
				
				    return "";
				
				}
				public String phonePattern(){
				
					return "";
				
				}
				public String phoneOriginalDbColumnName(){
				
					return "phone";
				
				}

				
			    public String photo;

				public String getPhoto () {
					return this.photo;
				}

				public Boolean photoIsNullable(){
				    return true;
				}
				public Boolean photoIsKey(){
				    return false;
				}
				public Integer photoLength(){
				    return null;
				}
				public Integer photoPrecision(){
				    return null;
				}
				public String photoDefault(){
				
					return null;
				
				}
				public String photoComment(){
				
				    return "";
				
				}
				public String photoPattern(){
				
					return "";
				
				}
				public String photoOriginalDbColumnName(){
				
					return "photo";
				
				}

				


	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_WEB_SERVICE_TALEND_MyRESTService.length) {
				if(length < 1024 && commonByteArray_WEB_SERVICE_TALEND_MyRESTService.length == 0) {
   					commonByteArray_WEB_SERVICE_TALEND_MyRESTService = new byte[1024];
				} else {
   					commonByteArray_WEB_SERVICE_TALEND_MyRESTService = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_WEB_SERVICE_TALEND_MyRESTService, 0, length);
			strReturn = new String(commonByteArray_WEB_SERVICE_TALEND_MyRESTService, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_WEB_SERVICE_TALEND_MyRESTService.length) {
				if(length < 1024 && commonByteArray_WEB_SERVICE_TALEND_MyRESTService.length == 0) {
   					commonByteArray_WEB_SERVICE_TALEND_MyRESTService = new byte[1024];
				} else {
   					commonByteArray_WEB_SERVICE_TALEND_MyRESTService = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_WEB_SERVICE_TALEND_MyRESTService, 0, length);
			strReturn = new String(commonByteArray_WEB_SERVICE_TALEND_MyRESTService, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_WEB_SERVICE_TALEND_MyRESTService) {

        	try {

        		int length = 0;
		
						this.id = readInteger(dis);
					
					this.name = readString(dis);
					
					this.company = readString(dis);
					
					this.username = readString(dis);
					
					this.email = readString(dis);
					
					this.address = readString(dis);
					
					this.zip = readString(dis);
					
					this.state = readString(dis);
					
					this.country = readString(dis);
					
					this.phone = readString(dis);
					
					this.photo = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_WEB_SERVICE_TALEND_MyRESTService) {

        	try {

        		int length = 0;
		
						this.id = readInteger(dis);
					
					this.name = readString(dis);
					
					this.company = readString(dis);
					
					this.username = readString(dis);
					
					this.email = readString(dis);
					
					this.address = readString(dis);
					
					this.zip = readString(dis);
					
					this.state = readString(dis);
					
					this.country = readString(dis);
					
					this.phone = readString(dis);
					
					this.photo = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.id,dos);
					
					// String
				
						writeString(this.name,dos);
					
					// String
				
						writeString(this.company,dos);
					
					// String
				
						writeString(this.username,dos);
					
					// String
				
						writeString(this.email,dos);
					
					// String
				
						writeString(this.address,dos);
					
					// String
				
						writeString(this.zip,dos);
					
					// String
				
						writeString(this.state,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.phone,dos);
					
					// String
				
						writeString(this.photo,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.id,dos);
					
					// String
				
						writeString(this.name,dos);
					
					// String
				
						writeString(this.company,dos);
					
					// String
				
						writeString(this.username,dos);
					
					// String
				
						writeString(this.email,dos);
					
					// String
				
						writeString(this.address,dos);
					
					// String
				
						writeString(this.zip,dos);
					
					// String
				
						writeString(this.state,dos);
					
					// String
				
						writeString(this.country,dos);
					
					// String
				
						writeString(this.phone,dos);
					
					// String
				
						writeString(this.photo,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("id="+String.valueOf(id));
		sb.append(",name="+name);
		sb.append(",company="+company);
		sb.append(",username="+username);
		sb.append(",email="+email);
		sb.append(",address="+address);
		sb.append(",zip="+zip);
		sb.append(",state="+state);
		sb.append(",country="+country);
		sb.append(",phone="+phone);
		sb.append(",photo="+photo);
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(id == null){
        					sb.append("<null>");
        				}else{
            				sb.append(id);
            			}
            		
        			sb.append("|");
        		
        				if(name == null){
        					sb.append("<null>");
        				}else{
            				sb.append(name);
            			}
            		
        			sb.append("|");
        		
        				if(company == null){
        					sb.append("<null>");
        				}else{
            				sb.append(company);
            			}
            		
        			sb.append("|");
        		
        				if(username == null){
        					sb.append("<null>");
        				}else{
            				sb.append(username);
            			}
            		
        			sb.append("|");
        		
        				if(email == null){
        					sb.append("<null>");
        				}else{
            				sb.append(email);
            			}
            		
        			sb.append("|");
        		
        				if(address == null){
        					sb.append("<null>");
        				}else{
            				sb.append(address);
            			}
            		
        			sb.append("|");
        		
        				if(zip == null){
        					sb.append("<null>");
        				}else{
            				sb.append(zip);
            			}
            		
        			sb.append("|");
        		
        				if(state == null){
        					sb.append("<null>");
        				}else{
            				sb.append(state);
            			}
            		
        			sb.append("|");
        		
        				if(country == null){
        					sb.append("<null>");
        				}else{
            				sb.append(country);
            			}
            		
        			sb.append("|");
        		
        				if(phone == null){
        					sb.append("<null>");
        				}else{
            				sb.append(phone);
            			}
            		
        			sb.append("|");
        		
        				if(photo == null){
        					sb.append("<null>");
        				}else{
            				sb.append(photo);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row2Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
    final static byte[] commonByteArrayLock_WEB_SERVICE_TALEND_MyRESTService = new byte[0];
    static byte[] commonByteArray_WEB_SERVICE_TALEND_MyRESTService = new byte[0];

	
			    public Integer statusCode;

				public Integer getStatusCode () {
					return this.statusCode;
				}

				public Boolean statusCodeIsNullable(){
				    return true;
				}
				public Boolean statusCodeIsKey(){
				    return false;
				}
				public Integer statusCodeLength(){
				    return 0;
				}
				public Integer statusCodePrecision(){
				    return 0;
				}
				public String statusCodeDefault(){
				
					return "";
				
				}
				public String statusCodeComment(){
				
				    return null;
				
				}
				public String statusCodePattern(){
				
				    return null;
				
				}
				public String statusCodeOriginalDbColumnName(){
				
					return "statusCode";
				
				}

				
			    public routines.system.Document body;

				public routines.system.Document getBody () {
					return this.body;
				}

				public Boolean bodyIsNullable(){
				    return true;
				}
				public Boolean bodyIsKey(){
				    return false;
				}
				public Integer bodyLength(){
				    return 0;
				}
				public Integer bodyPrecision(){
				    return 0;
				}
				public String bodyDefault(){
				
					return "";
				
				}
				public String bodyComment(){
				
				    return null;
				
				}
				public String bodyPattern(){
				
				    return null;
				
				}
				public String bodyOriginalDbColumnName(){
				
					return "body";
				
				}

				
			    public String string;

				public String getString () {
					return this.string;
				}

				public Boolean stringIsNullable(){
				    return true;
				}
				public Boolean stringIsKey(){
				    return false;
				}
				public Integer stringLength(){
				    return 4048;
				}
				public Integer stringPrecision(){
				    return 0;
				}
				public String stringDefault(){
				
					return null;
				
				}
				public String stringComment(){
				
				    return null;
				
				}
				public String stringPattern(){
				
				    return null;
				
				}
				public String stringOriginalDbColumnName(){
				
					return "string";
				
				}

				


	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_WEB_SERVICE_TALEND_MyRESTService.length) {
				if(length < 1024 && commonByteArray_WEB_SERVICE_TALEND_MyRESTService.length == 0) {
   					commonByteArray_WEB_SERVICE_TALEND_MyRESTService = new byte[1024];
				} else {
   					commonByteArray_WEB_SERVICE_TALEND_MyRESTService = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_WEB_SERVICE_TALEND_MyRESTService, 0, length);
			strReturn = new String(commonByteArray_WEB_SERVICE_TALEND_MyRESTService, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_WEB_SERVICE_TALEND_MyRESTService.length) {
				if(length < 1024 && commonByteArray_WEB_SERVICE_TALEND_MyRESTService.length == 0) {
   					commonByteArray_WEB_SERVICE_TALEND_MyRESTService = new byte[1024];
				} else {
   					commonByteArray_WEB_SERVICE_TALEND_MyRESTService = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_WEB_SERVICE_TALEND_MyRESTService, 0, length);
			strReturn = new String(commonByteArray_WEB_SERVICE_TALEND_MyRESTService, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_WEB_SERVICE_TALEND_MyRESTService) {

        	try {

        		int length = 0;
		
						this.statusCode = readInteger(dis);
					
						this.body = (routines.system.Document) dis.readObject();
					
					this.string = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		
			} catch(ClassNotFoundException eCNFE) {
				 throw new RuntimeException(eCNFE);
		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_WEB_SERVICE_TALEND_MyRESTService) {

        	try {

        		int length = 0;
		
						this.statusCode = readInteger(dis);
					
						this.body = (routines.system.Document) dis.readObject();
					
					this.string = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		
			} catch(ClassNotFoundException eCNFE) {
				 throw new RuntimeException(eCNFE);
		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.statusCode,dos);
					
					// Document
				
       			    	dos.writeObject(this.body);
					
					// String
				
						writeString(this.string,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// Integer
				
						writeInteger(this.statusCode,dos);
					
					// Document
				
						dos.clearInstanceCache();
						dos.writeObject(this.body);
					
					// String
				
						writeString(this.string,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("statusCode="+String.valueOf(statusCode));
		sb.append(",body="+String.valueOf(body));
		sb.append(",string="+string);
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(statusCode == null){
        					sb.append("<null>");
        				}else{
            				sb.append(statusCode);
            			}
            		
        			sb.append("|");
        		
        				if(body == null){
        					sb.append("<null>");
        				}else{
            				sb.append(body);
            			}
            		
        			sb.append("|");
        		
        				if(string == null){
        					sb.append("<null>");
        				}else{
            				sb.append(string);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row1Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public void tRESTClient_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tRESTClient_1_SUBPROCESS_STATE", 0);

	final boolean execStat = this.execStat;

		mdc("tRESTClient_1", "ZuMyes_");

	
		String iterateId = "";
	
	
	String currentComponent = "";
	s("none");
	String cLabel =  null;
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row1Struct row1 = new row1Struct();
row2Struct row2 = new row2Struct();





	
	/**
	 * [tLogRow_1 begin ] start
	 */

	

	
		
		sh("tLogRow_1");
		
	
	s(currentComponent="tLogRow_1");
	
			
			
	
			runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,0,0,"row2");
			
		int tos_count_tLogRow_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tLogRow_1 - "  + ("Start to work.") );
            if (log.isDebugEnabled()) {
                class BytesLimit65535_tLogRow_1{
                    public void limitLog4jByte() throws Exception{
                    StringBuilder log4jParamters_tLogRow_1 = new StringBuilder();
                    log4jParamters_tLogRow_1.append("Parameters:");
                            log4jParamters_tLogRow_1.append("BASIC_MODE" + " = " + "false");
                        log4jParamters_tLogRow_1.append(" | ");
                            log4jParamters_tLogRow_1.append("TABLE_PRINT" + " = " + "true");
                        log4jParamters_tLogRow_1.append(" | ");
                            log4jParamters_tLogRow_1.append("VERTICAL" + " = " + "false");
                        log4jParamters_tLogRow_1.append(" | ");
                            log4jParamters_tLogRow_1.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
                        log4jParamters_tLogRow_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tLogRow_1 - "  + (log4jParamters_tLogRow_1) );
                    } 
                } 
            new BytesLimit65535_tLogRow_1().limitLog4jByte();
            }
			if(enableLogStash) {
				talendJobLog.addCM("tLogRow_1", "tLogRow_1", "tLogRow");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			

	///////////////////////
	
         class Util_tLogRow_1 {

        String[] des_top = { ".", ".", "-", "+" };

        String[] des_head = { "|=", "=|", "-", "+" };

        String[] des_bottom = { "'", "'", "-", "+" };

        String name="";

        java.util.List<String[]> list = new java.util.ArrayList<String[]>();

        int[] colLengths = new int[11];

        public void addRow(String[] row) {

            for (int i = 0; i < 11; i++) {
                if (row[i]!=null) {
                  colLengths[i] = Math.max(colLengths[i], row[i].length());
                }
            }
            list.add(row);
        }

        public void setTableName(String name) {

            this.name = name;
        }

            public StringBuilder format() {
            
                StringBuilder sb = new StringBuilder();
  
            
                    sb.append(print(des_top));
    
                    int totals = 0;
                    for (int i = 0; i < colLengths.length; i++) {
                        totals = totals + colLengths[i];
                    }
    
                    // name
                    sb.append("|");
                    int k = 0;
                    for (k = 0; k < (totals + 10 - name.length()) / 2; k++) {
                        sb.append(' ');
                    }
                    sb.append(name);
                    for (int i = 0; i < totals + 10 - name.length() - k; i++) {
                        sb.append(' ');
                    }
                    sb.append("|\n");

                    // head and rows
                    sb.append(print(des_head));
                    for (int i = 0; i < list.size(); i++) {
    
                        String[] row = list.get(i);
    
                        java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());
                        
                        StringBuilder sbformat = new StringBuilder();                                             
        			        sbformat.append("|%1$-");
        			        sbformat.append(colLengths[0]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%2$-");
        			        sbformat.append(colLengths[1]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%3$-");
        			        sbformat.append(colLengths[2]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%4$-");
        			        sbformat.append(colLengths[3]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%5$-");
        			        sbformat.append(colLengths[4]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%6$-");
        			        sbformat.append(colLengths[5]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%7$-");
        			        sbformat.append(colLengths[6]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%8$-");
        			        sbformat.append(colLengths[7]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%9$-");
        			        sbformat.append(colLengths[8]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%10$-");
        			        sbformat.append(colLengths[9]);
        			        sbformat.append("s");
        			              
        			        sbformat.append("|%11$-");
        			        sbformat.append(colLengths[10]);
        			        sbformat.append("s");
        			                      
                        sbformat.append("|\n");                    
       
                        formatter.format(sbformat.toString(), (Object[])row);	
                                
                        sb.append(formatter.toString());
                        if (i == 0)
                            sb.append(print(des_head)); // print the head
                    }
    
                    // end
                    sb.append(print(des_bottom));
                    return sb;
                }
            

            private StringBuilder print(String[] fillChars) {
                StringBuilder sb = new StringBuilder();
                //first column
                sb.append(fillChars[0]);                
                    for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);	                

                    for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[5] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[6] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[7] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[8] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                    for (int i = 0; i < colLengths[9] - fillChars[3].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }
                    sb.append(fillChars[3]);
                
                    //last column
                    for (int i = 0; i < colLengths[10] - fillChars[1].length() + 1; i++) {
                        sb.append(fillChars[2]);
                    }         
                sb.append(fillChars[1]);
                sb.append("\n");               
                return sb;
            }
            
            public boolean isTableEmpty(){
            	if (list.size() > 1)
            		return false;
            	return true;
            }
        }
        Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
        util_tLogRow_1.setTableName("tLogRow_1");
        util_tLogRow_1.addRow(new String[]{"id","name","company","username","email","address","zip","state","country","phone","photo",});        
 		StringBuilder strBuffer_tLogRow_1 = null;
		int nb_line_tLogRow_1 = 0;
///////////////////////    			



 



		

/**
 * [tLogRow_1 begin ] stop
 */




	
	/**
	 * [tExtractJSONFields_1 begin ] start
	 */

	

	
		
		sh("tExtractJSONFields_1");
		
	
	s(currentComponent="tExtractJSONFields_1");
	
			
			
	
			runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,0,0,"row1");
			
		int tos_count_tExtractJSONFields_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tExtractJSONFields_1 - "  + ("Start to work.") );
            if (log.isDebugEnabled()) {
                class BytesLimit65535_tExtractJSONFields_1{
                    public void limitLog4jByte() throws Exception{
                    StringBuilder log4jParamters_tExtractJSONFields_1 = new StringBuilder();
                    log4jParamters_tExtractJSONFields_1.append("Parameters:");
                            log4jParamters_tExtractJSONFields_1.append("READ_BY" + " = " + "JSONPATH");
                        log4jParamters_tExtractJSONFields_1.append(" | ");
                            log4jParamters_tExtractJSONFields_1.append("JSON_PATH_VERSION" + " = " + "2_1_0");
                        log4jParamters_tExtractJSONFields_1.append(" | ");
                            log4jParamters_tExtractJSONFields_1.append("JSONFIELD" + " = " + "string");
                        log4jParamters_tExtractJSONFields_1.append(" | ");
                            log4jParamters_tExtractJSONFields_1.append("JSON_LOOP_QUERY" + " = " + "\"*\"");
                        log4jParamters_tExtractJSONFields_1.append(" | ");
                            log4jParamters_tExtractJSONFields_1.append("MAPPING_4_JSONPATH" + " = " + "[{QUERY="+("\"id\"")+", SCHEMA_COLUMN="+("id")+"}, {QUERY="+("\"name\"")+", SCHEMA_COLUMN="+("name")+"}, {QUERY="+("\"company\"")+", SCHEMA_COLUMN="+("company")+"}, {QUERY="+("\"username\"")+", SCHEMA_COLUMN="+("username")+"}, {QUERY="+("\"email\"")+", SCHEMA_COLUMN="+("email")+"}, {QUERY="+("\"address\"")+", SCHEMA_COLUMN="+("address")+"}, {QUERY="+("\"zip\"")+", SCHEMA_COLUMN="+("zip")+"}, {QUERY="+("\"state\"")+", SCHEMA_COLUMN="+("state")+"}, {QUERY="+("\"country\"")+", SCHEMA_COLUMN="+("country")+"}, {QUERY="+("\"phone\"")+", SCHEMA_COLUMN="+("phone")+"}, {QUERY="+("\"photo\"")+", SCHEMA_COLUMN="+("photo")+"}]");
                        log4jParamters_tExtractJSONFields_1.append(" | ");
                            log4jParamters_tExtractJSONFields_1.append("DIE_ON_ERROR" + " = " + "false");
                        log4jParamters_tExtractJSONFields_1.append(" | ");
                            log4jParamters_tExtractJSONFields_1.append("USE_LOOP_AS_ROOT" + " = " + "true");
                        log4jParamters_tExtractJSONFields_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tExtractJSONFields_1 - "  + (log4jParamters_tExtractJSONFields_1) );
                    } 
                } 
            new BytesLimit65535_tExtractJSONFields_1().limitLog4jByte();
            }
			if(enableLogStash) {
				talendJobLog.addCM("tExtractJSONFields_1", "tExtractJSONFields_1", "tExtractJSONFields");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			

int nb_line_tExtractJSONFields_1 = 0;
String jsonStr_tExtractJSONFields_1 = "";

	

class JsonPathCache_tExtractJSONFields_1 {
	final java.util.Map<String,com.jayway.jsonpath.JsonPath> jsonPathString2compiledJsonPath = new java.util.HashMap<String,com.jayway.jsonpath.JsonPath>();
	
	public com.jayway.jsonpath.JsonPath getCompiledJsonPath(String jsonPath) {
		if(jsonPathString2compiledJsonPath.containsKey(jsonPath)) {
			return jsonPathString2compiledJsonPath.get(jsonPath);
		} else {
			com.jayway.jsonpath.JsonPath compiledLoopPath = com.jayway.jsonpath.JsonPath.compile(jsonPath);
			jsonPathString2compiledJsonPath.put(jsonPath,compiledLoopPath);
			return compiledLoopPath;
		}
	}
}

JsonPathCache_tExtractJSONFields_1 jsonPathCache_tExtractJSONFields_1 = new JsonPathCache_tExtractJSONFields_1();

 



		

/**
 * [tExtractJSONFields_1 begin ] stop
 */




	
	/**
	 * [tRESTClient_1 begin ] start
	 */

	

	
		
		sh("tRESTClient_1");
		
	
	s(currentComponent="tRESTClient_1");
	
			
			
	
		int tos_count_tRESTClient_1 = 0;
		
			if(enableLogStash) {
				talendJobLog.addCM("tRESTClient_1", "tRESTClient_1", "tRESTClient");
				talendJobLogProcess(globalMap);
				s(currentComponent);
			}
			

 



		

/**
 * [tRESTClient_1 begin ] stop
 */

	
	/**
	 * [tRESTClient_1 main ] start
	 */

	

	
	
	s(currentComponent="tRESTClient_1");
	
			
			
	
	row1 = null;

// expected response body
Object responseDoc_tRESTClient_1 = null;

try {
	// request body
	org.dom4j.Document requestDoc_tRESTClient_1 = null;
	String requestString_tRESTClient_1 = null;

	Object requestBody_tRESTClient_1 = requestDoc_tRESTClient_1 != null ? requestDoc_tRESTClient_1 : requestString_tRESTClient_1;

	

    //resposne class name
	Class<?> responseClass_tRESTClient_1
		= String.class;

	// create web client instance
	org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean factoryBean_tRESTClient_1 =
			new org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean();

	boolean inOSGi = routines.system.BundleUtils.inOSGi();

	final java.util.List<org.apache.cxf.feature.Feature> features_tRESTClient_1 =
			new java.util.ArrayList<org.apache.cxf.feature.Feature>();

	String url = null;
	
		url = "https://fake-json-api.mock.beeceptor.com/users";
		// {baseUri}tRESTClient
		factoryBean_tRESTClient_1.setServiceName(new javax.xml.namespace.QName(url, "tRESTClient"));
		factoryBean_tRESTClient_1.setAddress(url);
	

	

    boolean log_messages_tRESTClient_1 = Boolean.valueOf(false);
	if (log_messages_tRESTClient_1) {
		org.apache.cxf.ext.logging.LoggingFeature loggingFeature = new  org.apache.cxf.ext.logging.LoggingFeature();
		loggingFeature.addSensitiveProtocolHeaderNames(new java.util.HashSet<>(java.util.Arrays.asList(org.apache.cxf.helpers.HttpHeaderHelper.AUTHORIZATION)));
		loggingFeature.addSensitiveElementNames(new java.util.HashSet<>(java.util.Arrays.asList("password")));
		features_tRESTClient_1.add(loggingFeature);
	}

	

	factoryBean_tRESTClient_1.setFeatures(features_tRESTClient_1);


	java.util.List<Object> providers_tRESTClient_1 = new java.util.ArrayList<Object>();
	providers_tRESTClient_1.add(new org.apache.cxf.jaxrs.provider.dom4j.DOM4JProvider() {
		// workaround for https://jira.talendforge.org/browse/TESB-7276
		public org.dom4j.Document readFrom(Class<org.dom4j.Document> cls,
											java.lang.reflect.Type type,
											java.lang.annotation.Annotation[] anns,
											javax.ws.rs.core.MediaType mt,
											javax.ws.rs.core.MultivaluedMap<String, String> headers,
											java.io.InputStream is)
				throws IOException, javax.ws.rs.WebApplicationException {
			String contentLength = headers.getFirst("Content-Length");
			if (!org.apache.cxf.common.util.StringUtils.isEmpty(contentLength)
					&& Integer.valueOf(contentLength) <= 0) {
				try {
					return org.dom4j.DocumentHelper.parseText("<root/>");
				} catch (org.dom4j.DocumentException e_tRESTClient_1) {
					e_tRESTClient_1.printStackTrace();
				}
				return null;
			}
			return super.readFrom(cls, type, anns, mt, headers, is);
		}
	});
	org.apache.cxf.jaxrs.provider.json.JSONProvider jsonProvider_tRESTClient_1 =
			new org.apache.cxf.jaxrs.provider.json.JSONProvider();
		jsonProvider_tRESTClient_1.setIgnoreNamespaces(true);
		jsonProvider_tRESTClient_1.setAttributesToElements(true);
	
	
	
		jsonProvider_tRESTClient_1.setSupportUnwrapped(true);
		jsonProvider_tRESTClient_1.setWrapperName("root");
	
	
		jsonProvider_tRESTClient_1.setDropRootElement(false);
		jsonProvider_tRESTClient_1.setConvertTypesToStrings(true);
	providers_tRESTClient_1.add(jsonProvider_tRESTClient_1);
	factoryBean_tRESTClient_1.setProviders(providers_tRESTClient_1);
	factoryBean_tRESTClient_1.setTransportId("http://cxf.apache.org/transports/http");

	boolean use_auth_tRESTClient_1 = false;

	

	org.apache.cxf.jaxrs.client.WebClient webClient_tRESTClient_1 = null;
	
		webClient_tRESTClient_1 = factoryBean_tRESTClient_1.createWebClient();
		// set request path
		webClient_tRESTClient_1.path("");
	

	// set connection properties
	org.apache.cxf.jaxrs.client.ClientConfiguration clientConfig_tRESTClient_1 = org.apache.cxf.jaxrs.client.WebClient.getConfig(webClient_tRESTClient_1);
	org.apache.cxf.transport.http.auth.HttpAuthSupplier httpAuthSupplerHttpConduit = null;
	org.apache.cxf.transport.http.HTTPConduit conduit_tRESTClient_1 = null;

	
		conduit_tRESTClient_1 = clientConfig_tRESTClient_1.getHttpConduit();
	
	
    if (clientConfig_tRESTClient_1.getEndpoint() != null) {
		org.apache.cxf.service.model.EndpointInfo endpointInfo_tRESTClient_1 = clientConfig_tRESTClient_1.getEndpoint().getEndpointInfo();
		if(endpointInfo_tRESTClient_1 != null) {
			endpointInfo_tRESTClient_1.setProperty("enable.webclient.operation.reporting", true);
		}
    }

	

	

	if (!inOSGi) {
		conduit_tRESTClient_1.getClient().setReceiveTimeout((long)(60 * 1000L));
		conduit_tRESTClient_1.getClient().setConnectionTimeout((long)(30 * 1000L));
	}
	
	
	
		

	

	

	
		// set Accept-Type
		webClient_tRESTClient_1.accept("application/json");
	

	
		// set optional query and header properties if any
	
	if (use_auth_tRESTClient_1 && "OAUTH2_BEARER".equals("BASIC")) {
		// set oAuth2 bearer token
		org.apache.cxf.rs.security.oauth2.client.BearerAuthSupplier authSupplier = new org.apache.cxf.rs.security.oauth2.client.BearerAuthSupplier();
		authSupplier.setAccessToken((String) "");
		conduit_tRESTClient_1.setAuthSupplier(authSupplier);
	}

	

	// if FORM request then capture query parameters into Form, otherwise set them as queries
	
		
		
	


	try {
		// start send request
		
			responseDoc_tRESTClient_1 = webClient_tRESTClient_1.get();
			javax.ws.rs.core.Response responseObjBase_tRESTClient_1 = (javax.ws.rs.core.Response)responseDoc_tRESTClient_1;
            int status_tRESTClient_1 = responseObjBase_tRESTClient_1.getStatus();
            if (status_tRESTClient_1 != 304 && status_tRESTClient_1 >= 300 && responseClass_tRESTClient_1 != javax.ws.rs.core.Response.class) {
                throw org.apache.cxf.jaxrs.utils.ExceptionUtils.toWebApplicationException((javax.ws.rs.core.Response)responseObjBase_tRESTClient_1);
            }
            if (status_tRESTClient_1 != 204 && responseObjBase_tRESTClient_1.getEntity() != null) {
				responseDoc_tRESTClient_1 = responseObjBase_tRESTClient_1.readEntity(responseClass_tRESTClient_1);
			}
		


		int webClientResponseStatus_tRESTClient_1 = webClient_tRESTClient_1.getResponse().getStatus();
		if (webClientResponseStatus_tRESTClient_1 >= 300) {
			throw new javax.ws.rs.WebApplicationException(webClient_tRESTClient_1.getResponse());
		}

		
			if (row1 == null) {
				row1 = new row1Struct();
			}

			row1.statusCode = webClientResponseStatus_tRESTClient_1;
			row1.string = "";
			
				
				{
					Object responseObj_tRESTClient_1 = responseDoc_tRESTClient_1;
				
				if(responseObj_tRESTClient_1 != null){
					if (responseClass_tRESTClient_1 == String.class && responseObj_tRESTClient_1 instanceof String) {
							row1.string = (String) responseObj_tRESTClient_1;
					} else {
						routines.system.Document responseTalendDoc_tRESTClient_1 = null;
						if (null != responseObj_tRESTClient_1) {
							responseTalendDoc_tRESTClient_1 = new routines.system.Document();
							if (responseObj_tRESTClient_1 instanceof org.dom4j.Document) {
								responseTalendDoc_tRESTClient_1.setDocument((org.dom4j.Document) responseObj_tRESTClient_1);
							}
						}
						row1.body = responseTalendDoc_tRESTClient_1;
					}
				}
			}
			

			java.util.Map<String, javax.ws.rs.core.NewCookie> cookies_tRESTClient_1 = new java.util.HashMap<String, javax.ws.rs.core.NewCookie>();

			if (webClient_tRESTClient_1.getResponse() != null && webClient_tRESTClient_1.getResponse().getCookies() != null ) { 
				cookies_tRESTClient_1.putAll(webClient_tRESTClient_1.getResponse().getCookies());
			}

			


			globalMap.put("tRESTClient_1_HEADERS", webClient_tRESTClient_1.getResponse().getHeaders());
			globalMap.put("tRESTClient_1_COOKIES", cookies_tRESTClient_1);
			
		

	} catch (javax.ws.rs.WebApplicationException ex_tRESTClient_1) {
	    globalMap.put("tRESTClient_1_ERROR_MESSAGE",ex_tRESTClient_1.getMessage());
		
			throw ex_tRESTClient_1;
		
	}

} catch(Exception e_tRESTClient_1) {
    globalMap.put("tRESTClient_1_ERROR_MESSAGE",e_tRESTClient_1.getMessage());
	
		throw new TalendException(e_tRESTClient_1, currentComponent, globalMap);
	
}


 


	tos_count_tRESTClient_1++;

		

/**
 * [tRESTClient_1 main ] stop
 */

	
	/**
	 * [tRESTClient_1 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="tRESTClient_1");
	
			
			
	

 



		

/**
 * [tRESTClient_1 process_data_begin ] stop
 */

// Start of branch "row1"
if(row1 != null) { 



	
	/**
	 * [tExtractJSONFields_1 main ] start
	 */

	

	
	
	s(currentComponent="tExtractJSONFields_1");
	
			
			
	
			if(runStat.update(execStat,enableLogStash,iterateId,1,1
				
					,"row1","tRESTClient_1","tRESTClient_1","tRESTClient","tExtractJSONFields_1","tExtractJSONFields_1","tExtractJSONFields"
				
			)) {
				talendJobLogProcess(globalMap);
			}
			
    			if(log.isTraceEnabled()){
    				log.trace("row1 - " + (row1==null? "": row1.toLogString()));
    			}
    		

            if(row1.string!=null){// C_01
                jsonStr_tExtractJSONFields_1 = row1.string.toString();
   
row2 = null;

	

String loopPath_tExtractJSONFields_1 = "*";
java.util.List<Object> resultset_tExtractJSONFields_1 = new java.util.ArrayList<Object>();

boolean isStructError_tExtractJSONFields_1 = true;
com.jayway.jsonpath.ReadContext document_tExtractJSONFields_1 = null;
try {
	document_tExtractJSONFields_1 = com.jayway.jsonpath.JsonPath.parse(jsonStr_tExtractJSONFields_1);
	com.jayway.jsonpath.JsonPath compiledLoopPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(loopPath_tExtractJSONFields_1);
	Object result_tExtractJSONFields_1 = document_tExtractJSONFields_1.read(compiledLoopPath_tExtractJSONFields_1,net.minidev.json.JSONObject.class);
	if (result_tExtractJSONFields_1 instanceof net.minidev.json.JSONArray) {
		resultset_tExtractJSONFields_1 = (net.minidev.json.JSONArray) result_tExtractJSONFields_1;
	} else {
		resultset_tExtractJSONFields_1.add(result_tExtractJSONFields_1);
	}
	
	isStructError_tExtractJSONFields_1 = false;
} catch (java.lang.Exception ex_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",ex_tExtractJSONFields_1.getMessage());
		log.error("tExtractJSONFields_1 - " + ex_tExtractJSONFields_1.getMessage());
		System.err.println(ex_tExtractJSONFields_1.getMessage());
}

String jsonPath_tExtractJSONFields_1 = null;
com.jayway.jsonpath.JsonPath compiledJsonPath_tExtractJSONFields_1 = null;

Object value_tExtractJSONFields_1 = null;

Object root_tExtractJSONFields_1 = null;
for(int i_tExtractJSONFields_1=0; isStructError_tExtractJSONFields_1 || (i_tExtractJSONFields_1 < resultset_tExtractJSONFields_1.size());i_tExtractJSONFields_1++){
	if(!isStructError_tExtractJSONFields_1){
		Object row_tExtractJSONFields_1 = resultset_tExtractJSONFields_1.get(i_tExtractJSONFields_1);
            row2 = null;
	row2 = new row2Struct();
	nb_line_tExtractJSONFields_1++;
	try {
		jsonPath_tExtractJSONFields_1 = "id";
		compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);
		
		try {
		    
		        value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1.read(row_tExtractJSONFields_1);
		    
				if(value_tExtractJSONFields_1 != null && !value_tExtractJSONFields_1.toString().isEmpty()) {
					row2.id = ParserUtils.parseTo_Integer(value_tExtractJSONFields_1.toString());
				} else {
					row2.id = 

		null

;
				}
		} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",e_tExtractJSONFields_1.getMessage());
			row2.id = 

		null

;
		}
		jsonPath_tExtractJSONFields_1 = "name";
		compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);
		
		try {
		    
		        value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1.read(row_tExtractJSONFields_1);
		    
				row2.name = value_tExtractJSONFields_1 == null ? 

		null

 : value_tExtractJSONFields_1.toString();
		} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",e_tExtractJSONFields_1.getMessage());
			row2.name = 

		null

;
		}
		jsonPath_tExtractJSONFields_1 = "company";
		compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);
		
		try {
		    
		        value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1.read(row_tExtractJSONFields_1);
		    
				row2.company = value_tExtractJSONFields_1 == null ? 

		null

 : value_tExtractJSONFields_1.toString();
		} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",e_tExtractJSONFields_1.getMessage());
			row2.company = 

		null

;
		}
		jsonPath_tExtractJSONFields_1 = "username";
		compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);
		
		try {
		    
		        value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1.read(row_tExtractJSONFields_1);
		    
				row2.username = value_tExtractJSONFields_1 == null ? 

		null

 : value_tExtractJSONFields_1.toString();
		} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",e_tExtractJSONFields_1.getMessage());
			row2.username = 

		null

;
		}
		jsonPath_tExtractJSONFields_1 = "email";
		compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);
		
		try {
		    
		        value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1.read(row_tExtractJSONFields_1);
		    
				row2.email = value_tExtractJSONFields_1 == null ? 

		null

 : value_tExtractJSONFields_1.toString();
		} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",e_tExtractJSONFields_1.getMessage());
			row2.email = 

		null

;
		}
		jsonPath_tExtractJSONFields_1 = "address";
		compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);
		
		try {
		    
		        value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1.read(row_tExtractJSONFields_1);
		    
				row2.address = value_tExtractJSONFields_1 == null ? 

		null

 : value_tExtractJSONFields_1.toString();
		} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",e_tExtractJSONFields_1.getMessage());
			row2.address = 

		null

;
		}
		jsonPath_tExtractJSONFields_1 = "zip";
		compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);
		
		try {
		    
		        value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1.read(row_tExtractJSONFields_1);
		    
				row2.zip = value_tExtractJSONFields_1 == null ? 

		null

 : value_tExtractJSONFields_1.toString();
		} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",e_tExtractJSONFields_1.getMessage());
			row2.zip = 

		null

;
		}
		jsonPath_tExtractJSONFields_1 = "state";
		compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);
		
		try {
		    
		        value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1.read(row_tExtractJSONFields_1);
		    
				row2.state = value_tExtractJSONFields_1 == null ? 

		null

 : value_tExtractJSONFields_1.toString();
		} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",e_tExtractJSONFields_1.getMessage());
			row2.state = 

		null

;
		}
		jsonPath_tExtractJSONFields_1 = "country";
		compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);
		
		try {
		    
		        value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1.read(row_tExtractJSONFields_1);
		    
				row2.country = value_tExtractJSONFields_1 == null ? 

		null

 : value_tExtractJSONFields_1.toString();
		} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",e_tExtractJSONFields_1.getMessage());
			row2.country = 

		null

;
		}
		jsonPath_tExtractJSONFields_1 = "phone";
		compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);
		
		try {
		    
		        value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1.read(row_tExtractJSONFields_1);
		    
				row2.phone = value_tExtractJSONFields_1 == null ? 

		null

 : value_tExtractJSONFields_1.toString();
		} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",e_tExtractJSONFields_1.getMessage());
			row2.phone = 

		null

;
		}
		jsonPath_tExtractJSONFields_1 = "photo";
		compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);
		
		try {
		    
		        value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1.read(row_tExtractJSONFields_1);
		    
				row2.photo = value_tExtractJSONFields_1 == null ? 

		null

 : value_tExtractJSONFields_1.toString();
		} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",e_tExtractJSONFields_1.getMessage());
			row2.photo = 

		null

;
		}	
	} catch (java.lang.Exception ex_tExtractJSONFields_1) {
globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",ex_tExtractJSONFields_1.getMessage());
			log.error("tExtractJSONFields_1 - " + ex_tExtractJSONFields_1.getMessage());
		    System.err.println(ex_tExtractJSONFields_1.getMessage());
		    row2 = null;	
	}
	
	}
    
	isStructError_tExtractJSONFields_1 = false;
	
	log.debug("tExtractJSONFields_1 - Extracting the record " + nb_line_tExtractJSONFields_1 + ".");
//}


 


	tos_count_tExtractJSONFields_1++;

		

/**
 * [tExtractJSONFields_1 main ] stop
 */

	
	/**
	 * [tExtractJSONFields_1 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="tExtractJSONFields_1");
	
			
			
	

 



		

/**
 * [tExtractJSONFields_1 process_data_begin ] stop
 */

// Start of branch "row2"
if(row2 != null) { 



	
	/**
	 * [tLogRow_1 main ] start
	 */

	

	
	
	s(currentComponent="tLogRow_1");
	
			
			
	
			if(runStat.update(execStat,enableLogStash,iterateId,1,1
				
					,"row2","tExtractJSONFields_1","tExtractJSONFields_1","tExtractJSONFields","tLogRow_1","tLogRow_1","tLogRow"
				
			)) {
				talendJobLogProcess(globalMap);
			}
			
    			if(log.isTraceEnabled()){
    				log.trace("row2 - " + (row2==null? "": row2.toLogString()));
    			}
    		
///////////////////////		
						

				
				String[] row_tLogRow_1 = new String[11];
   				
	    		if(row2.id != null) { //              
                 row_tLogRow_1[0]=    						    
				                String.valueOf(row2.id)			
					          ;	
							
	    		} //			
    			   				
	    		if(row2.name != null) { //              
                 row_tLogRow_1[1]=    						    
				                String.valueOf(row2.name)			
					          ;	
							
	    		} //			
    			   				
	    		if(row2.company != null) { //              
                 row_tLogRow_1[2]=    						    
				                String.valueOf(row2.company)			
					          ;	
							
	    		} //			
    			   				
	    		if(row2.username != null) { //              
                 row_tLogRow_1[3]=    						    
				                String.valueOf(row2.username)			
					          ;	
							
	    		} //			
    			   				
	    		if(row2.email != null) { //              
                 row_tLogRow_1[4]=    						    
				                String.valueOf(row2.email)			
					          ;	
							
	    		} //			
    			   				
	    		if(row2.address != null) { //              
                 row_tLogRow_1[5]=    						    
				                String.valueOf(row2.address)			
					          ;	
							
	    		} //			
    			   				
	    		if(row2.zip != null) { //              
                 row_tLogRow_1[6]=    						    
				                String.valueOf(row2.zip)			
					          ;	
							
	    		} //			
    			   				
	    		if(row2.state != null) { //              
                 row_tLogRow_1[7]=    						    
				                String.valueOf(row2.state)			
					          ;	
							
	    		} //			
    			   				
	    		if(row2.country != null) { //              
                 row_tLogRow_1[8]=    						    
				                String.valueOf(row2.country)			
					          ;	
							
	    		} //			
    			   				
	    		if(row2.phone != null) { //              
                 row_tLogRow_1[9]=    						    
				                String.valueOf(row2.phone)			
					          ;	
							
	    		} //			
    			   				
	    		if(row2.photo != null) { //              
                 row_tLogRow_1[10]=    						    
				                String.valueOf(row2.photo)			
					          ;	
							
	    		} //			
    			 

				util_tLogRow_1.addRow(row_tLogRow_1);	
				nb_line_tLogRow_1++;
                	log.info("tLogRow_1 - Content of row "+nb_line_tLogRow_1+": " + TalendString.unionString("|",row_tLogRow_1));
//////

//////                    
                    
///////////////////////    			

 


	tos_count_tLogRow_1++;

		

/**
 * [tLogRow_1 main ] stop
 */

	
	/**
	 * [tLogRow_1 process_data_begin ] start
	 */

	

	
	
	s(currentComponent="tLogRow_1");
	
			
			
	

 



		

/**
 * [tLogRow_1 process_data_begin ] stop
 */

	
	/**
	 * [tLogRow_1 process_data_end ] start
	 */

	

	
	
	s(currentComponent="tLogRow_1");
	
			
			
	

 



		

/**
 * [tLogRow_1 process_data_end ] stop
 */


} // End of branch "row2"

		// end for
	}



	
		} // C_01
	
	
	/**
	 * [tExtractJSONFields_1 process_data_end ] start
	 */

	

	
	
	s(currentComponent="tExtractJSONFields_1");
	
			
			
	

 



		

/**
 * [tExtractJSONFields_1 process_data_end ] stop
 */


} // End of branch "row1"




	
	/**
	 * [tRESTClient_1 process_data_end ] start
	 */

	

	
	
	s(currentComponent="tRESTClient_1");
	
			
			
	

 



		

/**
 * [tRESTClient_1 process_data_end ] stop
 */

	
	/**
	 * [tRESTClient_1 end ] start
	 */

	

	
	
	s(currentComponent="tRESTClient_1");
	
			
			
	


if (globalMap.get("tRESTClient_1_NB_LINE") == null) {
	globalMap.put("tRESTClient_1_NB_LINE", 1);
}

// [tRESTCliend_end]
 

ok_Hash.put("tRESTClient_1", true);
end_Hash.put("tRESTClient_1", System.currentTimeMillis());




		

/**
 * [tRESTClient_1 end ] stop
 */


	
	/**
	 * [tExtractJSONFields_1 end ] start
	 */

	

	
	
	s(currentComponent="tExtractJSONFields_1");
	
			
			
	
   globalMap.put("tExtractJSONFields_1_NB_LINE", nb_line_tExtractJSONFields_1);
	log.debug("tExtractJSONFields_1 - Extracted records count: " + nb_line_tExtractJSONFields_1 + " .");


			 		if(runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,"row1",2,0,
			 			"tRESTClient_1","tRESTClient_1","tRESTClient","tExtractJSONFields_1","tExtractJSONFields_1","tExtractJSONFields","output")) {
						talendJobLogProcess(globalMap);
					}
				
 
                if(log.isDebugEnabled())
            log.debug("tExtractJSONFields_1 - "  + ("Done.") );

ok_Hash.put("tExtractJSONFields_1", true);
end_Hash.put("tExtractJSONFields_1", System.currentTimeMillis());




		

/**
 * [tExtractJSONFields_1 end ] stop
 */


	
	/**
	 * [tLogRow_1 end ] start
	 */

	

	
	
	s(currentComponent="tLogRow_1");
	
			
			
	


//////

                    
                    java.io.PrintStream consoleOut_tLogRow_1 = null;
                    if (globalMap.get("tLogRow_CONSOLE")!=null)
                    {
                    	consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
                    }
                    else
                    {
                    	consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
                    	globalMap.put("tLogRow_CONSOLE",consoleOut_tLogRow_1);
                    }
                    
                    consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
                    consoleOut_tLogRow_1.flush();
//////
globalMap.put("tLogRow_1_NB_LINE",nb_line_tLogRow_1);
                if(log.isInfoEnabled())
            log.info("tLogRow_1 - "  + ("Printed row count: ")  + (nb_line_tLogRow_1)  + (".") );

///////////////////////    			

			 		if(runStat.updateStatAndLog(execStat,enableLogStash,resourceMap,iterateId,"row2",2,0,
			 			"tExtractJSONFields_1","tExtractJSONFields_1","tExtractJSONFields","tLogRow_1","tLogRow_1","tLogRow","output")) {
						talendJobLogProcess(globalMap);
					}
				
 
                if(log.isDebugEnabled())
            log.debug("tLogRow_1 - "  + ("Done.") );

ok_Hash.put("tLogRow_1", true);
end_Hash.put("tLogRow_1", System.currentTimeMillis());




		

/**
 * [tLogRow_1 end ] stop
 */







				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				    if(!(e instanceof TalendException)){
					   log.fatal(currentComponent + " " + e.getMessage(),e);
					}
				
				TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tRESTClient_1 finally ] start
	 */

	

	
	
	s(currentComponent="tRESTClient_1");
	
			
			
	

 



		

/**
 * [tRESTClient_1 finally ] stop
 */


	
	/**
	 * [tExtractJSONFields_1 finally ] start
	 */

	

	
	
	s(currentComponent="tExtractJSONFields_1");
	
			
			
	

 



		

/**
 * [tExtractJSONFields_1 finally ] stop
 */


	
	/**
	 * [tLogRow_1 finally ] start
	 */

	

	
	
	s(currentComponent="tLogRow_1");
	
			
			
	

 



		

/**
 * [tLogRow_1 finally ] stop
 */







				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tRESTClient_1_SUBPROCESS_STATE", 1);
	}
	


public void talendJobLogProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("talendJobLog_SUBPROCESS_STATE", 0);

	final boolean execStat = this.execStat;


	
		String iterateId = "";
	
	
	String currentComponent = "";
	s("none");
	String cLabel =  null;
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;





	
	/**
	 * [talendJobLog begin ] start
	 */

	

	
		
		sh("talendJobLog");
		
	
	s(currentComponent="talendJobLog");
	
			
			
	
		int tos_count_talendJobLog = 0;
		

	for (JobStructureCatcherUtils.JobStructureCatcherMessage jcm : talendJobLog.getMessages()) {
		org.talend.job.audit.JobContextBuilder builder_talendJobLog = org.talend.job.audit.JobContextBuilder.create().jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
			.custom("process_id", jcm.pid).custom("thread_id", jcm.tid).custom("pid", pid).custom("father_pid", fatherPid).custom("root_pid", rootPid);
		org.talend.logging.audit.Context log_context_talendJobLog = null;
		
		
		if(jcm.log_type == JobStructureCatcherUtils.LogType.PERFORMANCE){
			long timeMS = jcm.end_time - jcm.start_time;
			String duration = String.valueOf(timeMS);
			
			log_context_talendJobLog = builder_talendJobLog
				.sourceId(jcm.sourceId).sourceLabel(jcm.sourceLabel).sourceConnectorType(jcm.sourceComponentName)
				.targetId(jcm.targetId).targetLabel(jcm.targetLabel).targetConnectorType(jcm.targetComponentName)
				.connectionName(jcm.current_connector).rows(jcm.row_count).duration(duration).build();
			auditLogger_talendJobLog.flowExecution(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.JOBSTART) {
			log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).build();
			auditLogger_talendJobLog.jobstart(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.JOBEND) {
			long timeMS = jcm.end_time - jcm.start_time;
			String duration = String.valueOf(timeMS);
		
			log_context_talendJobLog = builder_talendJobLog
				.timestamp(jcm.moment).duration(duration).status(jcm.status).build();
			auditLogger_talendJobLog.jobstop(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.RUNCOMPONENT) {
			log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment)
				.connectorType(jcm.component_name).connectorId(jcm.component_id).connectorLabel(jcm.component_label).build();
			auditLogger_talendJobLog.runcomponent(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.FLOWINPUT) {//log current component input line
			long timeMS = jcm.end_time - jcm.start_time;
			String duration = String.valueOf(timeMS);
			
			log_context_talendJobLog = builder_talendJobLog
				.connectorType(jcm.component_name).connectorId(jcm.component_id).connectorLabel(jcm.component_label)
				.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
				.rows(jcm.total_row_number).duration(duration).build();
			auditLogger_talendJobLog.flowInput(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.FLOWOUTPUT) {//log current component output/reject line
			long timeMS = jcm.end_time - jcm.start_time;
			String duration = String.valueOf(timeMS);
			
			log_context_talendJobLog = builder_talendJobLog
				.connectorType(jcm.component_name).connectorId(jcm.component_id).connectorLabel(jcm.component_label)
				.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
				.rows(jcm.total_row_number).duration(duration).build();
			auditLogger_talendJobLog.flowOutput(log_context_talendJobLog);
		} else if(jcm.log_type == JobStructureCatcherUtils.LogType.JOBERROR) {
			java.lang.Exception e_talendJobLog = jcm.exception;
			if(e_talendJobLog!=null) {
				try(java.io.StringWriter sw_talendJobLog = new java.io.StringWriter();java.io.PrintWriter pw_talendJobLog = new java.io.PrintWriter(sw_talendJobLog)) {
					e_talendJobLog.printStackTrace(pw_talendJobLog);
					builder_talendJobLog.custom("stacktrace", sw_talendJobLog.getBuffer().substring(0,java.lang.Math.min(sw_talendJobLog.getBuffer().length(), 512)));
				}
			}

			if(jcm.extra_info!=null) {
				builder_talendJobLog.connectorId(jcm.component_id).custom("extra_info", jcm.extra_info);
			}
				
			log_context_talendJobLog = builder_talendJobLog
				.connectorType(jcm.component_id.substring(0, jcm.component_id.lastIndexOf('_')))
				.connectorId(jcm.component_id)
				.connectorLabel(jcm.component_label == null ? jcm.component_id : jcm.component_label).build();

			auditLogger_talendJobLog.exception(log_context_talendJobLog);
		}
		
		
		
	}

 



		

/**
 * [talendJobLog begin ] stop
 */

	
	/**
	 * [talendJobLog main ] start
	 */

	

	
	
	s(currentComponent="talendJobLog");
	
			
			
	

 


	tos_count_talendJobLog++;

		

/**
 * [talendJobLog main ] stop
 */

	
	/**
	 * [talendJobLog process_data_begin ] start
	 */

	

	
	
	s(currentComponent="talendJobLog");
	
			
			
	

 



		

/**
 * [talendJobLog process_data_begin ] stop
 */

	
	/**
	 * [talendJobLog process_data_end ] start
	 */

	

	
	
	s(currentComponent="talendJobLog");
	
			
			
	

 



		

/**
 * [talendJobLog process_data_end ] stop
 */

	
	/**
	 * [talendJobLog end ] start
	 */

	

	
	
	s(currentComponent="talendJobLog");
	
			
			
	

 

ok_Hash.put("talendJobLog", true);
end_Hash.put("talendJobLog", System.currentTimeMillis());




		

/**
 * [talendJobLog end ] stop
 */

				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				    if(!(e instanceof TalendException)){
					   log.fatal(currentComponent + " " + e.getMessage(),e);
					}
				
				TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [talendJobLog finally ] start
	 */

	

	
	
	s(currentComponent="talendJobLog");
	
			
			
	

 



		

/**
 * [talendJobLog finally ] stop
 */

				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("talendJobLog_SUBPROCESS_STATE", 1);
	}
	
    public String resuming_logs_dir_path = null;
    public String resuming_checkpoint_path = null;
    public String parent_part_launcher = null;
    private String resumeEntryMethodName = null;
    private boolean globalResumeTicket = false;

    public boolean watch = false;
    // portStats is null, it means don't execute the statistics
    public Integer portStats = null;
    public int portTraces = 4334;
    public String clientHost;
    public String defaultClientHost = "localhost";
    public String contextStr = "Default";
    public boolean isDefaultContext = true;
    public String pid = "0";
    public String rootPid = null;
    public String fatherPid = null;
    public String fatherNode = null;
    public long startTime = 0;
    public boolean isChildJob = false;
    public String log4jLevel = "";
    
    private boolean enableLogStash;

    private boolean execStat = true;
    
    private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
        protected java.util.Map<String, String> initialValue() {
            java.util.Map<String,String> threadRunResultMap = new java.util.HashMap<String, String>();
            threadRunResultMap.put("errorCode", null);
            threadRunResultMap.put("status", "");
            return threadRunResultMap;
        };
    };


    protected PropertiesWithType context_param = new PropertiesWithType();
    public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

    public String status= "";
    
    
    private final static java.util.Properties jobInfo = new java.util.Properties();
    private final static java.util.Map<String,String> mdcInfo = new java.util.HashMap<>();
    private final static java.util.concurrent.atomic.AtomicLong subJobPidCounter = new java.util.concurrent.atomic.AtomicLong();


    public static void main(String[] args){
        final MyRESTService MyRESTServiceClass = new MyRESTService();

        int exitCode = MyRESTServiceClass.runJobInTOS(args);
	        if(exitCode==0){
		        log.info("TalendJob: 'MyRESTService' - Done.");
	        }

        System.exit(exitCode);
    }
	

	
	
	private void getjobInfo() {
		final String TEMPLATE_PATH = "src/main/templates/jobInfo_template.properties";
		final String BUILD_PATH = "../jobInfo.properties";
		final String path = this.getClass().getResource("").getPath();
		if(path.lastIndexOf("target") > 0) {
			final java.io.File templateFile = new java.io.File(
					path.substring(0, path.lastIndexOf("target")).concat(TEMPLATE_PATH));
			if (templateFile.exists()) {
				readJobInfo(templateFile);
				return;
			}
		}
			readJobInfo(new java.io.File(BUILD_PATH));
	}

    private void readJobInfo(java.io.File jobInfoFile){
	
        if(jobInfoFile.exists()) {
            try (java.io.InputStream is = new java.io.FileInputStream(jobInfoFile)) {
            	jobInfo.load(is);
            } catch (IOException e) {
            	 
                log.debug("Read jobInfo.properties file fail: " + e.getMessage());
                
            }
        }
		log.info(String.format("Project name: %s\tJob name: %s\tGIT Commit ID: %s\tTalend Version: %s",
				projectName,jobName,jobInfo.getProperty("gitCommitId"), "8.0.1.20250126_0750-patch"));
		
    }


    public String[][] runJob(String[] args) {

        int exitCode = runJobInTOS(args);
        String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

        return bufferValue;
    }

    public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;
    	
        return hastBufferOutput;
    }

    public int runJobInTOS(String[] args) {
	   	// reset status
	   	status = "";
	   	
        String lastStr = "";
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--context_param")) {
                lastStr = arg;
            } else if (lastStr.equals("")) {
                evalParam(arg);
            } else {
                evalParam(lastStr + " " + arg);
                lastStr = "";
            }
        }

        final boolean enableCBP = false;
        boolean inOSGi = routines.system.BundleUtils.inOSGi();

        if (!inOSGi) {
        if(org.talend.metrics.CBPClient.getInstanceForCurrentVM() == null) {
            try {
                org.talend.metrics.CBPClient.startListenIfNotStarted(enableCBP, true);
            } catch (java.lang.Exception e) {
                errorCode = 1;
                status = "failure";
                e.printStackTrace();
                return 1;
            }
        }
        }
        
        enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

	        if(!"".equals(log4jLevel)){
	        	
				
				
				if("trace".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.TRACE);
				}else if("debug".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.DEBUG);
				}else if("info".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.INFO);
				}else if("warn".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.WARN);
				}else if("error".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.ERROR);
				}else if("fatal".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.FATAL);
				}else if ("off".equalsIgnoreCase(log4jLevel)){
					org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(), org.apache.logging.log4j.Level.OFF);
				}
				org.apache.logging.log4j.core.config.Configurator.setLevel(org.apache.logging.log4j.LogManager.getRootLogger().getName(), log.getLevel());
				
			}

	        getjobInfo();
			log.info("TalendJob: 'MyRESTService' - Start.");
		

                java.util.Set<Object> jobInfoKeys = jobInfo.keySet();
                for(Object jobInfoKey: jobInfoKeys) {
                    org.slf4j.MDC.put("_" + jobInfoKey.toString(), jobInfo.get(jobInfoKey).toString());
                }
                org.slf4j.MDC.put("_pid", pid);
                org.slf4j.MDC.put("_rootPid", rootPid);
                org.slf4j.MDC.put("_fatherPid", fatherPid);
                org.slf4j.MDC.put("_projectName", projectName);
                org.slf4j.MDC.put("_startTimestamp",java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC ).format( java.time.format.DateTimeFormatter.ISO_INSTANT ));
                org.slf4j.MDC.put("_jobRepositoryId","_ZkuUkO8MEe-KIvSEiRouvQ");
                org.slf4j.MDC.put("_compiledAtTimestamp","2025-02-24T17:30:43.374061200Z");

                java.lang.management.RuntimeMXBean mx = java.lang.management.ManagementFactory.getRuntimeMXBean();
                String[] mxNameTable = mx.getName().split("@"); //$NON-NLS-1$
                if (mxNameTable.length == 2) {
                    org.slf4j.MDC.put("_systemPid", mxNameTable[0]);
                } else {
                    org.slf4j.MDC.put("_systemPid", String.valueOf(java.lang.Thread.currentThread().getId()));
                }

		
		
			if(enableLogStash) {
				java.util.Properties properties_talendJobLog = new java.util.Properties();
				properties_talendJobLog.setProperty("root.logger", "audit");
				properties_talendJobLog.setProperty("encoding", "UTF-8");
				properties_talendJobLog.setProperty("application.name", "Talend Studio");
				properties_talendJobLog.setProperty("service.name", "Talend Studio Job");
				properties_talendJobLog.setProperty("instance.name", "Talend Studio Job Instance");
				properties_talendJobLog.setProperty("propagate.appender.exceptions", "none");
				properties_talendJobLog.setProperty("log.appender", "file");
				properties_talendJobLog.setProperty("appender.file.path", "audit.json");
				properties_talendJobLog.setProperty("appender.file.maxsize", "52428800");
				properties_talendJobLog.setProperty("appender.file.maxbackup", "20");
				properties_talendJobLog.setProperty("host", "false");

				System.getProperties().stringPropertyNames().stream()
					.filter(it -> it.startsWith("audit.logger."))
					.forEach(key -> properties_talendJobLog.setProperty(key.substring("audit.logger.".length()), System.getProperty(key)));

				
				
				
				org.apache.logging.log4j.core.config.Configurator.setLevel(properties_talendJobLog.getProperty("root.logger"), org.apache.logging.log4j.Level.DEBUG);
				
				auditLogger_talendJobLog = org.talend.job.audit.JobEventAuditLoggerFactory.createJobAuditLogger(properties_talendJobLog);
			}
		

        if(clientHost == null) {
            clientHost = defaultClientHost;
        }

        if(pid == null || "0".equals(pid)) {
            pid = TalendString.getAsciiRandomString(6);
        }

            org.slf4j.MDC.put("_pid", pid);

        if (rootPid==null) {
            rootPid = pid;
        }

            org.slf4j.MDC.put("_rootPid", rootPid);

        if (fatherPid==null) {
            fatherPid = pid;
        }else{
            isChildJob = true;
        }
            org.slf4j.MDC.put("_fatherPid", fatherPid);

        if (portStats != null) {
            // portStats = -1; //for testing
            if (portStats < 0 || portStats > 65535) {
                // issue:10869, the portStats is invalid, so this client socket can't open
                System.err.println("The statistics socket port " + portStats + " is invalid.");
                execStat = false;
            }
        } else {
            execStat = false;
        }

        try {
            java.util.Dictionary<String, Object> jobProperties = null;
            if (inOSGi) {
                jobProperties = routines.system.BundleUtils.getJobProperties(jobName);
    
                if (jobProperties != null && jobProperties.get("context") != null) {
                    contextStr = (String)jobProperties.get("context");
                }

                if (jobProperties != null && jobProperties.get("taskExecutionId") != null) {
                    taskExecutionId = (String)jobProperties.get("taskExecutionId");
                }

                // extract ids from parent route
                if(null == taskExecutionId || taskExecutionId.isEmpty()){
                    for(String arg : args) {
                        if(arg.startsWith("--context_param")
                                && (arg.contains("taskExecutionId") || arg.contains("jobExecutionId"))){

                            String keyValue = arg.replace("--context_param", "");
                            String[] parts = keyValue.split("=");
                            String[] cleanParts = java.util.Arrays.stream(parts)
                                    .filter(s -> !s.isEmpty())
                                    .toArray(String[]::new);
                            if (cleanParts.length == 2) {
                                String key = cleanParts[0];
                                String value = cleanParts[1];
                                if ("taskExecutionId".equals(key.trim()) && null != value) {
                                    taskExecutionId = value.trim();
                                }else if ("jobExecutionId".equals(key.trim()) && null != value) {
                                    jobExecutionId = value.trim();
                                }
                            }
                        }
                    }
                }
            }

            // first load default key-value pairs from application.properties
            if(isStandaloneMS) {
                context.putAll(this.getDefaultProperties());
            }
            //call job/subjob with an existing context, like: --context=production. if without this parameter, there will use the default context instead.
            java.io.InputStream inContext = MyRESTService.class.getClassLoader().getResourceAsStream("web_service_talend/myrestservice_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = MyRESTService.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
            }
            if (inContext != null) {
                try {
                    //defaultProps is in order to keep the original context value
                    if(context != null && context.isEmpty()) {
    	                defaultProps.load(inContext);
    	                if (inOSGi && jobProperties != null) {
                             java.util.Enumeration<String> keys = jobProperties.keys();
                             while (keys.hasMoreElements()) {
                                 String propKey = keys.nextElement();
                                 if (defaultProps.containsKey(propKey)) {
                                     defaultProps.put(propKey, (String) jobProperties.get(propKey));
                                 }
                             }
    	                }
    	                context = new ContextProperties(defaultProps);
                    }
                    if(isStandaloneMS) {
                        // override context key-value pairs if provided using --context=contextName
                        defaultProps.load(inContext);
                        context.putAll(defaultProps);
                    }
                } finally {
                    inContext.close();
                }
            } else if (!isDefaultContext) {
                //print info and job continue to run, for case: context_param is not empty.
                System.err.println("Could not find the context " + contextStr);
            }
            // override key-value pairs if provided via --config.location=file1.file2 OR --config.additional-location=file1,file2
            if(isStandaloneMS) {
                context.putAll(this.getAdditionalProperties());
            }
            
            // override key-value pairs if provide via command line like --key1=value1,--key2=value2
            if(!context_param.isEmpty()) {
                context.putAll(context_param);
				//set types for params from parentJobs
				for (Object key: context_param.keySet()){
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
            }
            class ContextProcessing {
                private void processContext_0() {
                        context.setContextType("db_host", "id_String");
                        if(context.getStringValue("db_host") == null) {
                            context.db_host = null;
                        } else {
                            context.db_host=(String) context.getProperty("db_host");
                        }
                        context.setContextType("db_port", "id_String");
                        if(context.getStringValue("db_port") == null) {
                            context.db_port = null;
                        } else {
                            context.db_port=(String) context.getProperty("db_port");
                        }
                        context.setContextType("db_name", "id_String");
                        if(context.getStringValue("db_name") == null) {
                            context.db_name = null;
                        } else {
                            context.db_name=(String) context.getProperty("db_name");
                        }
                        context.setContextType("db_user", "id_String");
                        if(context.getStringValue("db_user") == null) {
                            context.db_user = null;
                        } else {
                            context.db_user=(String) context.getProperty("db_user");
                        }
                        context.setContextType("db_password", "id_String");
                        if(context.getStringValue("db_password") == null) {
                            context.db_password = null;
                        } else {
                            context.db_password=(String) context.getProperty("db_password");
                        }
                        context.setContextType("additional_variables", "id_String");
                        if(context.getStringValue("additional_variables") == null) {
                            context.additional_variables = null;
                        } else {
                            context.additional_variables=(String) context.getProperty("additional_variables");
                        }
                } 
                public void processAllContext() {
                        processContext_0();
                }
            }

            new ContextProcessing().processAllContext();
        } catch (java.io.IOException ie) {
            System.err.println("Could not load context "+contextStr);
            ie.printStackTrace();
        }

        // get context value from parent directly
        if (parentContextMap != null && !parentContextMap.isEmpty()) {if (parentContextMap.containsKey("db_host")) {
                context.db_host = (String) parentContextMap.get("db_host");
            }if (parentContextMap.containsKey("db_port")) {
                context.db_port = (String) parentContextMap.get("db_port");
            }if (parentContextMap.containsKey("db_name")) {
                context.db_name = (String) parentContextMap.get("db_name");
            }if (parentContextMap.containsKey("db_user")) {
                context.db_user = (String) parentContextMap.get("db_user");
            }if (parentContextMap.containsKey("db_password")) {
                context.db_password = (String) parentContextMap.get("db_password");
            }if (parentContextMap.containsKey("additional_variables")) {
                context.additional_variables = (String) parentContextMap.get("additional_variables");
            }
        }

        //Resume: init the resumeUtil
        resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
        resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
        resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
        //Resume: jobStart
        resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","","","",resumeUtil.convertToJsonText(context,ContextProperties.class,parametersToEncrypt));

            org.slf4j.MDC.put("_context", contextStr);
            log.info("TalendJob: 'MyRESTService' - Started.");
            java.util.Optional.ofNullable(org.slf4j.MDC.getCopyOfContextMap()).ifPresent(mdcInfo::putAll);

if(execStat) {
    try {
        runStat.openSocket(!isChildJob);
        runStat.setAllPID(rootPid, fatherPid, pid, jobName);
        runStat.startThreadStat(clientHost, portStats);
        runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
    } catch (java.io.IOException ioException) {
        ioException.printStackTrace();
    }
}



	
	    java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
	    globalMap.put("concurrentHashMap", concurrentHashMap);
	

    long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    long endUsedMemory = 0;
    long end = 0;

    startTime = System.currentTimeMillis();


this.globalResumeTicket = true;//to run tPreJob




		if(enableLogStash) {
	        talendJobLog.addJobStartMessage();
	        try {
	            talendJobLogProcess(globalMap);
	        } catch (java.lang.Exception e) {
	            e.printStackTrace();
	        }
        }

this.globalResumeTicket = false;//to run others jobs

try {
errorCode = null;tRESTClient_1Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tRESTClient_1) {
globalMap.put("tRESTClient_1_SUBPROCESS_STATE", -1);

e_tRESTClient_1.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : MyRESTService");
        }
		if(enableLogStash) {
	        talendJobLog.addJobEndMessage(startTime, end, status);
	        try {
	            talendJobLogProcess(globalMap);
	        } catch (java.lang.Exception e) {
	            e.printStackTrace();
	        }
        }



if (execStat) {
    runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
    runStat.stopThreadStat();
}
    if (!inOSGi) {
    if(org.talend.metrics.CBPClient.getInstanceForCurrentVM() != null) {
        s("none");
        org.talend.metrics.CBPClient.getInstanceForCurrentVM().sendData();
    }
    }

    int returnCode = 0;


    if(errorCode == null) {
         returnCode = status != null && status.equals("failure") ? 1 : 0;
    } else {
         returnCode = errorCode.intValue();
    }
    resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","" + returnCode,"","","");
    resumeUtil.flush();


        org.slf4j.MDC.remove("_subJobName");
        org.slf4j.MDC.remove("_subJobPid");
        org.slf4j.MDC.remove("_systemPid");
        log.info("TalendJob: 'MyRESTService' - Finished - status: " + status + " returnCode: " + returnCode );

    return returnCode;

  }

    // only for OSGi env
    public void destroy() {
  // add CBP code for OSGI Executions
  if (null != taskExecutionId && !taskExecutionId.isEmpty()) {
     try {
	   org.talend.metrics.DataReadTracker.setExecutionId(taskExecutionId, jobExecutionId, false);
	   org.talend.metrics.DataReadTracker.sealCounter();
	   org.talend.metrics.DataReadTracker.reset();
	} catch (Exception | NoClassDefFoundError e) {
	   // ignore
	}
  }



    }














    private java.util.Map<String, Object> getSharedConnections4REST() {
        java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();






        return connections;
    }

    private void evalParam(String arg) {
        if (arg.startsWith("--resuming_logs_dir_path")) {
            resuming_logs_dir_path = arg.substring(25);
        } else if (arg.startsWith("--resuming_checkpoint_path")) {
            resuming_checkpoint_path = arg.substring(27);
        } else if (arg.startsWith("--parent_part_launcher")) {
            parent_part_launcher = arg.substring(23);
        } else if (arg.startsWith("--watch")) {
            watch = true;
        } else if (arg.startsWith("--stat_port=")) {
            String portStatsStr = arg.substring(12);
            if (portStatsStr != null && !portStatsStr.equals("null")) {
                portStats = Integer.parseInt(portStatsStr);
            }
        } else if (arg.startsWith("--trace_port=")) {
            portTraces = Integer.parseInt(arg.substring(13));
        } else if (arg.startsWith("--client_host=")) {
            clientHost = arg.substring(14);
        } else if (arg.startsWith("--context=")) {
            contextStr = arg.substring(10);
            isDefaultContext = false;
        } else if (arg.startsWith("--father_pid=")) {
            fatherPid = arg.substring(13);
        } else if (arg.startsWith("--root_pid=")) {
            rootPid = arg.substring(11);
        } else if (arg.startsWith("--father_node=")) {
            fatherNode = arg.substring(14);
        } else if (arg.startsWith("--pid=")) {
            pid = arg.substring(6);
        } else if (arg.startsWith("--context_type")) {
            String keyValue = arg.substring(15);
			int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.setContextType(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }

            }

		} else if (arg.startsWith("--context_param")) {
            String keyValue = arg.substring(16);
            int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }
            }
        } else if (arg.startsWith("--context_file")) {
        	String keyValue = arg.substring(15);
        	String filePath = new String(java.util.Base64.getDecoder().decode(keyValue));
        	java.nio.file.Path contextFile = java.nio.file.Paths.get(filePath);
            try (java.io.BufferedReader reader = java.nio.file.Files.newBufferedReader(contextFile)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    int index = -1;
                    if ( (index = line.indexOf('=')) > -1) {
							if (line.startsWith("--context_param")) {
								if ("id_Password".equals(context_param.getContextType(line.substring(16, index)))) {
									context_param.put(line.substring(16, index), routines.system.PasswordEncryptUtil.decryptPassword(
											line.substring(index + 1)));
								} else {
									context_param.put(line.substring(16, index), line.substring(index + 1));
								}
							}else {//--context_type
								context_param.setContextType(line.substring(15, index), line.substring(index + 1));
							}
                    }
                }
            } catch (java.io.IOException e) {
            	System.err.println("Could not load the context file: " + filePath);
                e.printStackTrace();
            }
        } else if (arg.startsWith("--log4jLevel=")) {
            log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {//for trunjob call
		    final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
    }
    
    private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

    private final String[][] escapeChars = {
        {"\\\\","\\"},{"\\n","\n"},{"\\'","\'"},{"\\r","\r"},
        {"\\f","\f"},{"\\b","\b"},{"\\t","\t"}
        };
    private String replaceEscapeChars (String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0],currIndex);
				if (index>=0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0], strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
    }

    public Integer getErrorCode() {
        return errorCode;
    }


    public String getStatus() {
        return status;
    }

    ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 *     120717 characters generated by Talend Cloud Data Management Platform 
 *     on the February 24, 2025 at 6:30:43 PM WAT
 ************************************************************************************************/