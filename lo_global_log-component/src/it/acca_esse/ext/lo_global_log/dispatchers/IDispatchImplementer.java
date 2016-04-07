package it.acca_esse.ext.lo_global_log.dispatchers;

/** implements the bare interface for asynchronous execution of dispatches
 * 
 * @author beppe
 *
 */
public interface IDispatchImplementer {

	/** method called through a Java thread, offers asynchronous capability 
	 * 
	 * @param aURL
	 * @param lArguments
	 */
	public abstract void impl_dispatch(/*IN*/ com.sun.star.util.URL aURL,/*IN*/ com.sun.star.beans.PropertyValue[] lArguments);    
}
