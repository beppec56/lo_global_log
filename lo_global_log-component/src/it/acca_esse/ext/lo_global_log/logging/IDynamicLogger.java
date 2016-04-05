package it.acca_esse.ext.lo_global_log.logging;

/**
 * interface for class to wrap the UNO logger. Characterizes the class owner
 * Can be used only if the UNO context is known
 * All the logging rigamarole is carried out in an UNO singleton object.
 * 
 * Main logging switch turned on/off on a owner basis (eg the parent class).
 * 
 * 
 * @author beppec56
 *
 */
public interface IDynamicLogger {
	abstract void ctor();

	abstract void ctor(String _message);

	abstract void config(String _message);

	abstract void debug(String _message);

	abstract void log(String _message);

	abstract void log(Throwable e, boolean _useDialog);

	abstract void entering(String _theMethod);

	abstract void entering(String _theMethod, String _message);

	abstract void exiting(String _theMethod, String _message);

	abstract void debug(String _theMethod, String _message);

	abstract void log(String _theMethod, String _message);

	abstract void info(String _theMethod);

	abstract void info(String _theMethod, String _message);

	abstract void warning(String _theMethod);

	abstract void warning(String _theMethod, String _message);

	abstract void warning(String _theMethod, String _message, Throwable ex);

	abstract void severe(String _theMethod, String _message);

	abstract void severe(Throwable ex);

	abstract void severe(String _theMethod, String _message, Throwable ex);

	abstract void severe(String _theMethod, Throwable ex);
	
	abstract void disableLogging();
	
	abstract void enableLogging();
	
	abstract void enableInfo();

	abstract void disableInfo();

	abstract void enableWarning();

	abstract void disableWarning();

	abstract void stopLogging();
	
	abstract void log_exception(int n_TheLevel, String _theMethod, String _message, java.lang.Throwable ex, boolean useDialog);

}
