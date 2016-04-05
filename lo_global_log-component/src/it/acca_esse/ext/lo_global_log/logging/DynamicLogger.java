package it.acca_esse.ext.lo_global_log.logging;

import com.sun.star.uno.XComponentContext;
import it.acca_esse.ext.lo_global_log.*;

/**
 * class to wrap the UNO logger. Characterizes the class owner
 * Can be used only if the UNO context is known
 * All the logging rigamarole is carried out in an UNO singleton object.
 * The drawback is that the
 * 
 * Main logging switch turned on/off on a owner basis (eg the parent class).
 * 
 * 
 * @author beppe
 *
 */
public class DynamicLogger extends DynamicLoggerBase {
	/**
	 * Class for logger.
	 * The link point between the user class (e.g. the one that uses a logger) and
	 * the UNO singleton which exposes it.
	 * 
	 * Default starts disabled.
	 * Must be enabled when needed!
	 * @param _theOwner parent object
	 * @param _ctx the UNO context
	 */
	public DynamicLogger(Object _theOwner, XComponentContext _ctx) {
		super(_theOwner,_ctx);
	}

	public void warning(String _theMethod, String _message, Throwable ex) {
		if(m_bLogEnabled && m_bWarningEnabled)
			log_exception(LoggerLevel.LOG_LEVEL_WARNING_value, _theMethod, _message, ex,false);
	}

	public void severe(String _theMethod, String _message) {
		if(m_xLogger != null)
			m_xLogger.logp( LoggerLevel.LOG_LEVEL_SEVERE_value,  m_sOwnerClassHashHex+" "+m_sOwnerClass, _theMethod, _message);
	}

	/**
	 * this method cannot be disabled.
	 * Severe log messages are always sent to UNO logger.
	 * 
	 * 
	 * @param _theMethod
	 * @param _message
	 * @param ex
	 */
	public void severe(Throwable ex) {
		log_exception(LoggerLevel.LOG_LEVEL_SEVERE_value, "", "", ex,false);
	}

	public void severe(String _theMethod, String _message, java.lang.Throwable ex) {
		log_exception(LoggerLevel.LOG_LEVEL_SEVERE_value, _theMethod, _message, ex,false);
	}

	public void severe(String _theMethod, java.lang.Throwable ex) {
		log_exception(LoggerLevel.LOG_LEVEL_SEVERE_value, _theMethod, "", ex,false);
	}
}
