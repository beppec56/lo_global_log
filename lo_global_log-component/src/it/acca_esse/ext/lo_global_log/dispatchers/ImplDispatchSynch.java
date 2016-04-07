package it.acca_esse.ext.lo_global_log.dispatchers;

import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XDispatch;
import com.sun.star.frame.XFrame;
import com.sun.star.frame.XStatusListener;
import com.sun.star.lang.XEventListener;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.URL;


/** superclass of all dispatchers
 * @author beppe
 *
 */
public class ImplDispatchSynch implements IDispatchBaseObject {

	protected XFrame m_xFrame;
	protected XMultiComponentFactory m_axMCF;
	protected XComponentContext m_xCC;
	protected XDispatch m_aUnoSlaveDispatch = null;
	
	public ImplDispatchSynch(XFrame xFrame, XComponentContext xContext,
			XMultiComponentFactory xMCF, XDispatch unoSaveSlaveDispatch) {

		m_xFrame = xFrame;
		m_axMCF = xMCF;
		m_xCC = xContext;
		m_aUnoSlaveDispatch = unoSaveSlaveDispatch;
	}

	/* (non-Javadoc)
	 * @see com.sun.star.frame.XDispatch#addStatusListener(com.sun.star.frame.XStatusListener, com.sun.star.util.URL)
	 */
	public void addStatusListener(XStatusListener aListener, URL aURL) {
		if(m_aUnoSlaveDispatch != null)
			m_aUnoSlaveDispatch.addStatusListener(aListener, aURL);
	}

	/* (non-Javadoc)
	 * @see com.sun.star.frame.XDispatch#dispatch(com.sun.star.util.URL, com.sun.star.beans.PropertyValue[])
	 * 
	 */
	/** important: the derived class should implement itself the XNotifyingDispatch behavior 
	 * 
	 */
	public void dispatch(URL aURL, PropertyValue[] lArguments) {
		if(m_aUnoSlaveDispatch!=null)
			m_aUnoSlaveDispatch.dispatch(aURL,lArguments);
	}

	/* (non-Javadoc)
	 * @see com.sun.star.frame.XDispatch#removeStatusListener(com.sun.star.frame.XStatusListener, com.sun.star.util.URL)
	 */
	public void removeStatusListener(XStatusListener aListener, URL aURL) {
//		m_aLoggerDialog.log("removeStatusListener",aURL.Complete);		
		if(m_aUnoSlaveDispatch != null)
			m_aUnoSlaveDispatch.removeStatusListener(aListener, aURL);
	}

	/* (non-Javadoc)
	 * @see com.sun.star.lang.XComponent#addEventListener(com.sun.star.lang.XEventListener)
	 */
	@Override
	public void addEventListener(XEventListener arg0) {		
	}

	/* (non-Javadoc)
	 * @see com.sun.star.lang.XComponent#dispose()
	 */
	@Override
	public void dispose() {
	}

	/* (non-Javadoc)
	 * @see com.sun.star.lang.XComponent#removeEventListener(com.sun.star.lang.XEventListener)
	 */
	@Override
	public void removeEventListener(XEventListener arg0) {
	}	
}
