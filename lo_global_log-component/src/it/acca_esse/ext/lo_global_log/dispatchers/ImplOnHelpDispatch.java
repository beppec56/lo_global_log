package it.acca_esse.ext.lo_global_log.dispatchers;

import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XDispatch;
import com.sun.star.frame.XFrame;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.URL;

import it.acca_esse.ext.lo_global_log.ui.DialogAbout;


public class ImplOnHelpDispatch extends ImplDispatchAsynch {

	public ImplOnHelpDispatch(XFrame xFrame, XComponentContext xContext,
			XMultiComponentFactory xMCF, XDispatch unoSaveSlaveDispatch) {

		super( xFrame, xContext, xMCF, unoSaveSlaveDispatch);
	}

	public void impl_dispatch(URL aURL, PropertyValue[] lArguments) {
		DialogAbout.showDialog(m_xFrame, m_xCC, m_axMCF);
	}
}
