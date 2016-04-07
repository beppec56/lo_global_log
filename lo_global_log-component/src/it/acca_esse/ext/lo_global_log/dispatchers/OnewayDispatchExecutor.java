package it.acca_esse.ext.lo_global_log.dispatchers;

import com.sun.star.beans.PropertyValue;
import com.sun.star.util.URL;

public class OnewayDispatchExecutor extends Thread {

	private IDispatchImplementer m_rLink = null;
	private URL m_aURL = null;
	private PropertyValue[] m_lParams = null;

	public OnewayDispatchExecutor(IDispatchImplementer rLink    ,
			com.sun.star.util.URL aURL,
			com.sun.star.beans.PropertyValue[] lArguments  )
	{
		m_rLink    = rLink   ;
		m_aURL = aURL;
		m_lParams  = lArguments ;

		if (m_rLink==null)
			System.out.println("ctor ... m_rLink == null");
		if (m_lParams==null)
			System.out.println("ctor ... m_lParams == null");
	}

	/**
	 * implements the thread function
	 * Here we call the internal set link object back and
	 * give him all neccessary parameters.
	 * After that we die by ouerself ...
	 */
	public void run() {
		if (m_rLink == null)
			System.out.println("run ... m_rLink == null");
		if (m_lParams == null)
			System.out.println("run ... m_lParams == null");

		if (m_rLink != null) {
				m_rLink.impl_dispatch(m_aURL, m_lParams);
		}
	}	
}
