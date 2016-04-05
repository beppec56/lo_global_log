package it.acca_esse.ext.lo_global_log.ui;

import com.sun.star.frame.XFrame;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.XComponentContext;

public class DialogDisplayLog extends DialogShowLicense {

	/**
	 * @param frame
	 * @param context
	 * @param _xmcf
	 */
	public DialogDisplayLog(XFrame frame, XComponentContext context,
			XMultiComponentFactory _xmcf, String theLog) {
		super(frame, context, _xmcf);
		//only title and text to display are different
		//FIXME: localize the title
		m_sTitle = "Display a severe log ";
		m_sTheTextToShow = theLog;
	}

}
