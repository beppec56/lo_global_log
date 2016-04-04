package it.acca_esse.ext.lo_global_log;

public class GlobalConstant {
	
	public static final String m_sWEBIDENTBASE = "it.guermandi.ext"; // extension owner, used in building it,
	// same name, same meaning in extension_conf_files/build.xml
	public static final String m_sEXT_NAME ="easyb"; //name of the extension, used in configurations
			// same name, same meaning in extension_conf_files/build.xml
	public static final String m_sEXTENSION_IDENTIFIER = m_sWEBIDENTBASE+"."+m_sEXT_NAME;
	public static final String m_sEXTENSION_BASE_KEY = "/"+m_sWEBIDENTBASE+"."+ m_sEXT_NAME;
	public static final String m_sEXTENSION_CONF_BASE_KEY = m_sEXTENSION_BASE_KEY+".Configuration/";

	public static final String	m_sEASYBROWSE_PROTO_BASE_URL				= m_sEXTENSION_IDENTIFIER + ".lo.handlers.EasyBrowse:";

//	object for help
	public static final String	m_sON_HELP_ABOUT_PATH					= "HelpAbout";
	public static final String	m_sON_HELP_ABOUT_PATH_COMPLETE			= m_sEASYBROWSE_PROTO_BASE_URL+m_sON_HELP_ABOUT_PATH;

//	object for login dialog
	public static final String	m_sEASYBROWSE_LOGIN_PATH					= "Login";
	public static final String	m_sEASYBROWSE_LOGIN_PATH_COMPLETE			= m_sEASYBROWSE_PROTO_BASE_URL+m_sEASYBROWSE_LOGIN_PATH;
	
	
	public static final String m_sEXTENSION_CONF_OPTIONS = m_sEXTENSION_CONF_BASE_KEY	+ "EasyBrowseOptionsParameters/";
	
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

	/// for XOX_Logger
	public static final String m_sSINGLETON_LOGGER_SERVICE = m_sEXTENSION_IDENTIFIER + ".singleton.GlobalLogger";
	public static final String m_sSINGLETON_LOGGER_SERVICE_INSTANCE = "/singletons/"+m_sSINGLETON_LOGGER_SERVICE;

	public static final int	m_nLOG_LEVEL_FINE									= 0;
	public static final int	m_nLOG_LEVEL_INFO									= 1;
	public static final int	m_nLOG_LEVEL_DEBUG									= 2;
	public static final int	m_nLOG_LEVEL_WARNING								= 3;
	public static final int	m_nLOG_LEVEL_SEVERE									= 4;
	public static final int	m_nLOG_CONFIG									    = 5;
	public static final int	m_nLOG_ALWAYS									    = 6;
}
