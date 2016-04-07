package it.acca_esse.ext.lo_global_log;

public class GlobalConstant {
	
	public static final String m_sGLOBALLOGWEBIDENTBASE = "it.acca_esse.ext"; // extension owner, used in building it,
	// same name, same meaning in extension_conf_files/build.xml
	public static final String m_sGLOBALLOGEXT_NAME ="lo_global_log"; //name of the extension, used in configurations
			// same name, same meaning in extension_conf_files/build.xml
	public static final String m_sGLOBALLOGEXTENSION_IDENTIFIER = m_sGLOBALLOGWEBIDENTBASE+"."+m_sGLOBALLOGEXT_NAME;
	public static final String m_sSINGLETON_LOGGER_SERVICE = m_sGLOBALLOGEXTENSION_IDENTIFIER + ".singleton.GlobalLogger";
	public static final String m_sSINGLETON_LOGGER_SERVICE_INSTANCE = "/singletons/"+m_sSINGLETON_LOGGER_SERVICE;



	public static final String m_sEXTENSION_BASE_KEY = "/"+m_sGLOBALLOGWEBIDENTBASE+"."+ m_sGLOBALLOGEXT_NAME;
	public static final String m_sEXTENSION_CONF_BASE_KEY = m_sEXTENSION_BASE_KEY+".Configuration/";
	
	public static final String m_sEXTENSION_CONF_OPTIONS = m_sEXTENSION_CONF_BASE_KEY	+ "GlobalLogOptionsParameters/";
	
	public static final String	m_sGLOBALLOG_PROTO_BASE_URL			= m_sGLOBALLOGEXTENSION_IDENTIFIER + ".handlers.GlobalLoggerHandler:";

//	object for help
	public static final String	m_sON_HELP_ABOUT_PATH					= "HelpAbout";
	public static final String	m_sON_HELP_ABOUT_PATH_COMPLETE			= m_sGLOBALLOG_PROTO_BASE_URL+m_sON_HELP_ABOUT_PATH;

	
	// Names used in configuration, the names are defined in file
	// extension_conf_files/extension/AddonConfiguration.xcs.xml
	/// logging configuration
	public static final String	m_sENABLE_INFO_LEVEL 							= "EnableInfoLevel";// boolean
	public static final String	m_sENABLE_DEBUG_LOGGING 						= "EnableDebugLogging";// boolean
	public static final String	m_sENABLE_CONSOLE_OUTPUT 						= "EnableConsoleOutput";// boolean
	public static final String	m_sENABLE_FILE_OUTPUT 							= "EnableFileOutput";// boolean
	public static final String	m_sLOG_FILE_PATH 								= "LogFilePath";// string
	public static final String	m_sFILE_ROTATION_COUNT 							= "FileRotationCount";// int
	public static final String	m_sMAX_FILE_SIZE	 							= "MaxFileSize";// int

}
