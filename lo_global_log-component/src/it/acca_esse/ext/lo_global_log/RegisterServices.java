package it.acca_esse.ext.lo_global_log;

import com.sun.star.lang.XSingleComponentFactory;
import com.sun.star.lib.uno.helper.Factory;
import com.sun.star.registry.InvalidRegistryException;
import com.sun.star.registry.XRegistryKey;

import it.acca_esse.ext.lo_global_log.logging.GlobalLogger;
import it.acca_esse.ext.lo_global_log.options.ManageLoggingOptions;


public class RegisterServices {

	/** Gives a factory for creating the service(s).
	 * This method is called by the <code>JavaLoader</code>
	 * <p>
	 * @return Returns a <code>XSingleServiceFactory</code> for creating the
	 * component.
	 * @see com.sun.star.comp.loader.JavaLoader
	 * @param stringImplementationName The implementation name of the component.
	 * @param xmultiservicefactory The service manager, who gives access to every
	 * known service.
	 */
	public synchronized static XSingleComponentFactory __getComponentFactory( String sImplementationName ) {
		XSingleComponentFactory xFactory = null;

		if ( sImplementationName.equals( GlobalLogger.m_sImplementationName ) ) {
			xFactory = Factory.createComponentFactory( 
					GlobalLogger.class,
					GlobalLogger.m_sServiceNames );
//DEBUG			System.out.println("__getComponentFactory: "+GlobalLogger.m_sImplementationName);
		}
		else if ( sImplementationName.equals( ManageLoggingOptions.m_sImplementationName ) ) {
			xFactory = Factory.createComponentFactory( ManageLoggingOptions.class, ManageLoggingOptions.m_sServiceNames );
System.out.println("__getComponentFactory: "+ManageLoggingOptions.m_sImplementationName+" "+xFactory);
		}
		return xFactory;
	}

	/** Writes the service information into the given registry key.
	 * This method is called by the <code>JavaLoader</code>.
	 * @return returns true if the operation succeeded
	 * @see com.sun.star.comp.loader.JavaLoader
	 * @see com.sun.star.lib.uno.helper.Factory
	 * @param xregistrykey Makes structural information (except regarding tree
	 * structures) of a single
	 * registry key accessible.
	 */
	public synchronized static boolean __writeRegistryServiceInfo( XRegistryKey xRegistryKey ) {
		boolean retGLogg = false; 
		if(!Factory.writeRegistryServiceInfo(
				GlobalLogger.m_sImplementationName,
				GlobalLogger.m_sServiceNames,
				xRegistryKey)) {
System.out.println("__writeRegistryServiceInfo: Factory.writeRegistryServiceInfo returned false");
			return false;
		}
		//prepare the new key path
		try {
			XRegistryKey newKey = xRegistryKey.createKey(
					GlobalLogger.m_sImplementationName+ // the class implementing
					"/UNO/SINGLETONS/"+	//fixed key reference
					GlobalLogger.m_sServiceNames[0]); //
System.out.println("New singleton key: "+
					GlobalLogger.m_sImplementationName+ // the class implementing
					"/UNO/SINGLETONS/"+	//fixed key reference
					GlobalLogger.m_sServiceNames[0]); //

			newKey.setStringValue(GlobalLogger.m_sServiceNames[0]);
			retGLogg = Factory.writeRegistryServiceInfo( GlobalLogger.m_sImplementationName, 
					GlobalLogger.m_sServiceNames, xRegistryKey );
		} catch (InvalidRegistryException e) {
System.out.println("__writeRegistryServiceInfo: "+GlobalLogger.m_sImplementationName + "failed");		
			e.printStackTrace();
		}
System.out.println("__writeRegistryServiceInfo: "+GlobalLogger.m_sImplementationName+": "+retGLogg);

		boolean retLogging =
				Factory.writeRegistryServiceInfo( ManageLoggingOptions.m_sImplementationName, ManageLoggingOptions.m_sServiceNames, xRegistryKey );

System.out.println("__writeRegistryServiceInfo: "+ManageLoggingOptions.m_sImplementationName+": "+retLogging);
		return (retGLogg && retLogging);
	}
}
