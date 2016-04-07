package it.acca_esse.ext.lo_global_log.handlers;

import com.sun.star.awt.XToolkit;
import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XDesktop;
import  com.sun.star.frame.XDispatch;
import  com.sun.star.frame.XDispatchProvider;
import  com.sun.star.frame.XFrame;
import  com.sun.star.frame.XStatusListener;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XEventListener;
import com.sun.star.lang.XInitialization;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XSingleComponentFactory;
import com.sun.star.lib.uno.helper.ComponentBase;
import com.sun.star.lib.uno.helper.Factory;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.CloseVetoException;
import com.sun.star.util.URL;
import com.sun.star.util.XCloseListener;
import com.sun.star.util.XCloseable;

import it.acca_esse.ext.lo_global_log.GlobalConstant;
import it.acca_esse.ext.lo_global_log.dispatchers.IDispatchBaseObject;
import it.acca_esse.ext.lo_global_log.dispatchers.ImplOnHelpDispatch;
import it.acca_esse.ext.lo_global_log.logging.DynamicLogger;


public class GlobaLoggerHandler {

	public static class GlobalLoggerHandlerImpl extends ComponentBase
	implements XServiceInfo, XInitialization, XDispatchProvider, XDispatch, XCloseable {

		// needed for registration
		public static final String			m_sImplementationName			= GlobalLoggerHandlerImpl.class.getName();
		public static final String[]		m_sServiceNames					= { "com.sun.star.frame.ProtocolHandler" };

		private XFrame						m_xFrame; // use when frame is needed as reference

		private final XComponentContext		m_xComponentContext;
		private XComponent					m_xCurrentComponent				= null;

///		@SuppressWarnings("unused")
		// may be we will need it afterward...
		private XMultiServiceFactory		m_xFactory						= null;

		protected XMultiComponentFactory	m_xRemoteServiceManager			= null;
		protected XMultiComponentFactory	m_xMultiComponentFactory		= null;

		/**
		 * The toolkit, that we can create UNO dialogs.
		 */
///		@SuppressWarnings("unused")
		private static XToolkit				m_xToolkit						= null;

		// next 3 static vars are for debug only
		public static int					m_nOnLoadCount					= 0;
		public static int					m_nOnSaveCount					= 0;
		public static int					m_nOnSaveAsCount				= 0;

		private IDispatchBaseObject			m_aImplEasyBrowseDispatchTB	= null;
		private XDispatch					m_aImplOnHelpDispatch			= null;
		private IDispatchBaseObject			m_aImplEasyBrowseLoginDispatch	= null;
		private IDispatchBaseObject			m_aImplEasyBrowseDispatch	= null;

//		private DynamicSystemLogger				m_aLogger;
		private DynamicLogger				m_aLogger;

		/**
		 * Constructs a new instance
		 * 
		 * @param context
		 *            the XComponentContext
		 */
		public GlobalLoggerHandlerImpl(XComponentContext context) {
			m_xComponentContext = context;
			try {
//				m_aLogger = new DynamicSystemLogger(this);
				m_aLogger = new DynamicLogger(this,context);
				m_aLogger.enableLogging();
				m_aLogger.ctor();
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
			try {
				m_xRemoteServiceManager = this.getRemoteServiceManager();
				// get the service manager from the component context
				this.m_xMultiComponentFactory = this.m_xComponentContext.getServiceManager();
				if (m_xMultiComponentFactory != null && m_xComponentContext != null) {
					Object toolkit = m_xMultiComponentFactory.createInstanceWithContext(
							"com.sun.star.awt.Toolkit", m_xComponentContext );
					m_xToolkit = (XToolkit) UnoRuntime.queryInterface( XToolkit.class,
							toolkit );
				}
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}

		public static String[] getServiceNames() {
			return m_sServiceNames;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sun.star.lang.XServiceInfo#getImplementationName()
		 */
		public String getImplementationName() {
			return m_sImplementationName;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sun.star.lang.XServiceInfo#getSupportedServiceNames()
		 */
		public String[] getSupportedServiceNames() {
			return getServiceNames();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sun.star.lang.XServiceInfo#supportsService(java.lang.String)
		 */
		public boolean supportsService(String _sService) {
			int len = m_sServiceNames.length;

			for (int i = 0; i < len; i++) {
				if (_sService.equals( m_sServiceNames[i] ))
					return true;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sun.star.lang.XInitialization#initialize(java.lang.Object[])
		 */
		public void initialize(Object[] object) throws com.sun.star.uno.Exception {
			m_aLogger.entering("initialize");
			if (object.length > 0) {

				m_xFrame = (com.sun.star.frame.XFrame) UnoRuntime.queryInterface(
						com.sun.star.frame.XFrame.class, object[0] );
			}
			// Create the toolkit to have access to it later
			m_xToolkit = (XToolkit) UnoRuntime.queryInterface( XToolkit.class,
					m_xComponentContext.getServiceManager().createInstanceWithContext(
							"com.sun.star.awt.Toolkit", m_xComponentContext ) );
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sun.star.frame.XDispatchProvider#queryDispatch(com.sun.star.util.URL,
		 *      java.lang.String, int)
		 */
		public com.sun.star.frame.XDispatch queryDispatch(com.sun.star.util.URL aURL,
				String sTargetFrameName, int iSearchFlags) {
			m_aLogger.debug("queryDispatch",aURL.Complete);
			try {
				if (aURL.Protocol.compareTo( GlobalConstant.m_sGLOBALLOG_PROTO_BASE_URL ) == 0) {
					if (aURL.Path.compareTo( GlobalConstant.m_sON_HELP_ABOUT_PATH ) == 0) {
						if (m_aImplOnHelpDispatch == null)
							m_aImplOnHelpDispatch = new ImplOnHelpDispatch( m_xFrame,
									m_xComponentContext, m_xMultiComponentFactory, null );
						return this;
					}
				}
			} catch (com.sun.star.uno.RuntimeException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			m_aLogger.debug("queryDispatch","return null: "+aURL.Complete);
			return null;
		}

		/* (non-Javadoc)
		 * @see com.sun.star.frame.XDispatch#addStatusListener(com.sun.star.frame.XStatusListener, com.sun.star.util.URL)
		 */
		@Override
		public void addStatusListener(XStatusListener arg0, URL arg1) {
			m_aLogger.debug("addStatusListener"," URL: "+arg1.Complete);
			if (arg1.Complete.equalsIgnoreCase( GlobalConstant.m_sON_HELP_ABOUT_PATH_COMPLETE )) {
				if (m_aImplOnHelpDispatch != null)
					m_aImplOnHelpDispatch.addStatusListener(arg0, arg1);
			}				
		}

		/* (non-Javadoc)
		 * @see com.sun.star.frame.XDispatch#dispatch(com.sun.star.util.URL, com.sun.star.beans.PropertyValue[])
		 */
		@Override
		public void dispatch(URL arg0, PropertyValue[] arg1) {
			m_aLogger.debug("dispatch"," URL: "+arg0.Complete);
			if (arg0.Complete.equalsIgnoreCase( GlobalConstant.m_sON_HELP_ABOUT_PATH_COMPLETE )) {
				if (m_aImplOnHelpDispatch != null)
					m_aImplOnHelpDispatch.dispatch(arg0, arg1);
			}
		}

		/* (non-Javadoc)
		 * @see com.sun.star.frame.XDispatch#removeStatusListener(com.sun.star.frame.XStatusListener, com.sun.star.util.URL)
		 */
		@Override
		public void removeStatusListener(XStatusListener arg0, URL arg1) {
			m_aLogger.debug("removeStatusListener"," URL: "+arg1.Complete);
			if (arg1.Complete.equalsIgnoreCase( GlobalConstant.m_sON_HELP_ABOUT_PATH_COMPLETE )) {
				if (m_aImplOnHelpDispatch != null)
					m_aImplOnHelpDispatch.removeStatusListener(arg0, arg1);
			}						
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sun.star.frame.XDispatchProvider#queryDispatches(com.sun.star.frame.DispatchDescriptor[])
		 */
		public com.sun.star.frame.XDispatch[] queryDispatches(
				com.sun.star.frame.DispatchDescriptor[] seqDescriptors) {
			int nCount = seqDescriptors.length;
			com.sun.star.frame.XDispatch[] seqDispatcher = new com.sun.star.frame.XDispatch[seqDescriptors.length];

			for (int i = 0; i < nCount; ++i) {
				seqDispatcher[i] = queryDispatch( seqDescriptors[i].FeatureURL,
						seqDescriptors[i].FrameName, seqDescriptors[i].SearchFlags );
			}
			return seqDispatcher;
		}

		/**
		 * Get the remote office context
		 */
		private XMultiComponentFactory getRemoteServiceManager() throws java.lang.Exception {
			if (m_xMultiComponentFactory == null && m_xRemoteServiceManager == null) {
				m_xRemoteServiceManager = m_xComponentContext.getServiceManager();
			}
			return m_xRemoteServiceManager;
		}

		/**
		 * Updates the Desktop current component in case of opening, creating or
		 * swapping to other document
		 * 
		 * @return XComponent Returns the current component of Desktop object
		 * 
		 */
		public void updateCurrentComponent() {
			//		m_aLogger.entering("updateCurrentComponent");
			XComponent ret = null;
			Object desktop;
			try {
				desktop = m_xRemoteServiceManager.createInstanceWithContext(
						"com.sun.star.frame.Desktop", m_xComponentContext );
				XDesktop xDesktop = (XDesktop) UnoRuntime.queryInterface( XDesktop.class,
						desktop );
				ret = xDesktop.getCurrentComponent();

				this.m_xMultiComponentFactory = this.m_xComponentContext.getServiceManager();
				this.m_xFactory = (XMultiServiceFactory) UnoRuntime.queryInterface(
						XMultiServiceFactory.class, this.m_xCurrentComponent );

			} catch (com.sun.star.uno.Exception ex) {
				ex.printStackTrace();
			}
			this.m_xCurrentComponent = ret;
		}

		public void disposing(com.sun.star.lang.EventObject arg0) {
			m_aLogger.debug("disposing");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sun.star.util.XCloseable#close(boolean)
		 */
		public void close(boolean arg0) throws CloseVetoException {
			m_aLogger.debug("close");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sun.star.util.XCloseBroadcaster#addCloseListener(com.sun.star.util.XCloseListener)
		 */
		public void addCloseListener(XCloseListener arg0) {
			m_aLogger.debug("addCloseListener");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sun.star.util.XCloseBroadcaster#removeCloseListener(com.sun.star.util.XCloseListener)
		 */
		public void removeCloseListener(XCloseListener arg0) {
		}

		/*
		 * (non-Javadoc)
		 * @see com.sun.star.lang.XComponent#addEventListener(com.sun.star.lang.XEventListener)
		 */
		@Override
		public void addEventListener(XEventListener arg0) {
			super.addEventListener(arg0);		
		}

		/*		 (non-Javadoc)
		 * @see com.sun.star.lang.XComponent#dispose()
		 */		 
		@Override
		public void dispose() {
			//call disposing of internal classes, deregistering
			if(m_aImplEasyBrowseDispatchTB != null)
				m_aImplEasyBrowseDispatchTB.dispose();
			if(m_aImplEasyBrowseDispatch != null)
				m_aImplEasyBrowseDispatch.dispose();
			if(m_aImplEasyBrowseLoginDispatch != null)
				m_aImplEasyBrowseLoginDispatch.dispose();
			super.dispose();
		}

		/* (non-Javadoc)
		 * @see com.sun.star.lang.XComponent#removeEventListener(com.sun.star.lang.XEventListener)
		 */
		@Override
		public void removeEventListener(XEventListener arg0) {
			super.removeEventListener(arg0);
		}
	}
}
