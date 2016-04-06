package it.acca_esse.ext.lo_global_log.logging;

public class DynamicSystemLogger implements IDynamicLogger {

	protected String m_sOwnerClass;
	protected String m_sOwnerClassHashHex;

	public DynamicSystemLogger(Object _theOwner) {
		m_sOwnerClassHashHex = String.format( "%8H", _theOwner );
		m_sOwnerClass =  _theOwner.getClass().getName();
	}

	@Override
	public void ctor() {
		System.out.println(m_sOwnerClassHashHex+" "+m_sOwnerClass+ "<init>");		
	}

	@Override
	public void ctor(String _message) {
		System.out.println(m_sOwnerClassHashHex+" "+m_sOwnerClass+ "<init> "+_message);
	}

	@Override
	public void config(String _message) {
		System.out.println(m_sOwnerClassHashHex+ " "+ _message);
	}

	@Override
	public void debug(String _message) {
		System.out.println(m_sOwnerClassHashHex+ " "+ _message);
	}

	@Override
	public void log(String _message) {
		System.out.println(m_sOwnerClassHashHex+ " "+ _message);
	}

	@Override
	public void log(Throwable e, boolean _useDialog) {
		log_exception(0, "", "", e, _useDialog);
	}

	@Override
	public void entering(String _theMethod) {
		System.out.println(m_sOwnerClassHashHex+" entering "+_theMethod);
	}

	@Override
	public void entering(String _theMethod, String _message) {
		System.out.println(m_sOwnerClassHashHex+" entering "+_theMethod+ " " +_message);
	}

	@Override
	public void exiting(String _theMethod, String _message) {
		System.out.println(m_sOwnerClassHashHex+" exiting "+_theMethod+ " " +_message);
	}

	@Override
	public void debug(String _theMethod, String _message) {
		System.out.println(m_sOwnerClassHashHex+" entering "+_theMethod+ " " +_message);
	}

	@Override
	public void log(String _theMethod, String _message) {
		System.out.println(m_sOwnerClassHashHex+" entering "+_theMethod+ " " +_message);
	}

	@Override
	public void info(String _theMethod) {
		System.out.println(m_sOwnerClassHashHex+" info "+_theMethod);
	}

	@Override
	public void info(String _theMethod, String _message) {
		System.out.println(m_sOwnerClassHashHex+" info "+_theMethod+ " " +_message);
	}

	@Override
	public void warning(String _theMethod) {
		System.out.println(m_sOwnerClassHashHex+" WARN "+_theMethod );
	}

	@Override
	public void warning(String _theMethod, String _message) {
		System.out.println(m_sOwnerClassHashHex+" WARN "+_theMethod+ " " +_message);
	}

	@Override
	public void warning(String _theMethod, String _message, Throwable ex) {
		log_exception(0, "", "", ex, false);
	}

	@Override
	public void severe(String _theMethod, String _message) {
		System.out.println(m_sOwnerClassHashHex+" severe "+_theMethod+ " " +_message);
	}

	@Override
	public void severe(Throwable ex) {
		log_exception(0, " severe ", "", ex, false);
	}

	@Override
	public void severe(String _theMethod, String _message, Throwable ex) {
		log_exception(0, _theMethod, _message, ex, false);
	}

	@Override
	public void severe(String _theMethod, Throwable ex) {
		log_exception(0, _theMethod, "", ex, false);
	}

	@Override
	public void disableLogging() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enableLogging() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enableInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disableInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enableWarning() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disableWarning() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopLogging() {
		// TODO Auto-generated method stub

	}

	@Override
	public void log_exception(int n_TheLevel, String _theMethod, String _message, Throwable ex, boolean useDialog) {
		log( m_sOwnerClassHashHex+" "+m_sOwnerClass +
					_theMethod +" "+_message,
					DynamicLoggerBase.getStackFromException(ex));
	}

}
