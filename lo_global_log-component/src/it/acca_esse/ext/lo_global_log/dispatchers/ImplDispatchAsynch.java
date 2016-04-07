package it.acca_esse.ext.lo_global_log.dispatchers;

import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XDispatch;
import com.sun.star.frame.XFrame;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.URL;


public class ImplDispatchAsynch extends ImplDispatchSynch implements IDispatchImplementer {

	protected Thread m_aThread = null;

	public ImplDispatchAsynch(XFrame xFrame, XComponentContext xContext,
			XMultiComponentFactory xMCF, XDispatch unoSaveSlaveDispatch) {

		super(xFrame, xContext, xMCF, unoSaveSlaveDispatch);
	}

	/* (non-Javadoc)
	 * @see com.sun.star.frame.XDispatch#dispatch(com.sun.star.util.URL, com.sun.star.beans.PropertyValue[])
	 */
	@Override
	public void dispatch(URL aURL, PropertyValue[] lArguments) {
		OnewayDispatchExecutor aExecutor = new OnewayDispatchExecutor((IDispatchImplementer) this,aURL, lArguments);
		m_aThread = aExecutor;
		aExecutor.start();
	}

	/* (non-Javadoc)
	 * @see com.yacme.ext.cnipa.dispatchers.IDispatchImplementer#impl_dispatch(com.sun.star.util.URL, com.sun.star.beans.PropertyValue[])
	 */
	@Override
	public void impl_dispatch(URL aURL, PropertyValue[] lArguments) {
		if(m_aUnoSlaveDispatch != null)
			m_aUnoSlaveDispatch.dispatch(aURL,lArguments);		
	}

}
