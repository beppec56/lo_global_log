package it.acca_esse.ext.lo_global_log.logging;

import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

public class LoggerConfigurationAccess {


	protected XComponentContext			m_xContext;	 
	protected XMultiServiceFactory	m_ConfProvider = null;
	private XMultiComponentFactory	m_xServiceManager = null;

	public LoggerConfigurationAccess(XComponentContext _xContext) {
//cannot use logger, called before instantiating the logger		
		m_xContext = _xContext;
		m_xServiceManager = m_xContext.getServiceManager(); 
		getProvider();
	}

	/**
	 * Create a default configuration provider
	 * Important: in run-time environment the provider should NEVER be disposed of.
	 * It is used everywhere!
	 */
	private void getProvider() {
		final String sProviderService = "com.sun.star.configuration.ConfigurationProvider";

		// create the provider and return it as a XMultiServiceFactory
		try {
			m_ConfProvider =(XMultiServiceFactory)
			    UnoRuntime.queryInterface(XMultiServiceFactory.class, 
			        m_xServiceManager.createInstanceWithContext(sProviderService,
			                                                   m_xContext));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a specified read-only configuration view
	 */
	protected Object createConfigurationReadOnlyView(String sPath)
			throws com.sun.star.uno.Exception {
		// XMultiServiceFactory xProvider = getProvider();

		// The service name: Need only read access:
		final String sReadOnlyView = "com.sun.star.configuration.ConfigurationAccess";

		// creation arguments: nodepath
		com.sun.star.beans.PropertyValue aPathArgument = new com.sun.star.beans.PropertyValue();
		aPathArgument.Name = "nodepath";
		aPathArgument.Value = sPath;

		Object[] aArguments = new Object[1];
		aArguments[0] = aPathArgument;

		Object xViewRoot = m_ConfProvider.createInstanceWithArguments( sReadOnlyView,
				aArguments );
		return xViewRoot;
	}

	/**
	 * Create a specified updatable configuration view using default
	 * synchronicity
	 */
	protected Object createConfigurationReadWriteView(String sPath)
			throws com.sun.star.uno.Exception {
		// The service name: Need update access:
		final String cUpdatableView = "com.sun.star.configuration.ConfigurationUpdateAccess";

		// creation arguments: nodepath
		com.sun.star.beans.PropertyValue aPathArgument = new com.sun.star.beans.PropertyValue();
		aPathArgument.Name = "nodepath";
		aPathArgument.Value = sPath;
		// creation arguments: commit mode - write-through or write-back
		com.sun.star.beans.PropertyValue aModeArgument = new com.sun.star.beans.PropertyValue();
		aModeArgument.Name = "enableasync";
		aModeArgument.Value = new Boolean( false );

		Object[] aArguments = new Object[2];
		aArguments[0] = aPathArgument;
		aArguments[1] = aModeArgument;

		// create the view
		Object xViewRoot = m_ConfProvider.createInstanceWithArguments( cUpdatableView,
				aArguments );
		return xViewRoot;
	}
}
