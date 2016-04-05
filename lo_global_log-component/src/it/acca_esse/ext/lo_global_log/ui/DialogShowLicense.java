package it.acca_esse.ext.lo_global_log.ui;

import com.sun.star.awt.PushButtonType;
import com.sun.star.awt.XDialog;
import com.sun.star.awt.XWindowPeer;
import com.sun.star.beans.XPropertySet;
import com.sun.star.frame.XFrame;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

import it.acca_esse.ext.lo_global_log.registry.MessageConfigurationAccess;

public class DialogShowLicense extends BasicDialog {

	private static final String	DLS_SHOW_LIC	= "showlicdlg";
	private XWindowPeer			m_xParentWindow	= null;
	protected String				m_sTitle = new String("empty title");
	private String				m_sOk  = new String("Ok");
	protected String				m_sTheTextToShow = new String("License text missing...");

	/**
	 * @param frame
	 * @param context
	 * @param _xmcf
	 */
	public DialogShowLicense(XFrame _xFrame, XComponentContext context,
			XMultiComponentFactory _xmcf) {
		super(_xFrame, context, _xmcf );
		MessageConfigurationAccess m_aRegAcc = null;
		m_aRegAcc = new MessageConfigurationAccess(m_xContext, m_xMCF);

		try {
			m_sTitle = m_aRegAcc.getStringFromRegistry( "id_title_show_license" );
			m_sOk = m_aRegAcc.getStringFromRegistry( "id_ok" );
			m_sTheTextToShow = m_aRegAcc.getStringFromRegistry( "id_main_license" );
		} catch (Exception e) {
			e.printStackTrace();
		}
		m_aRegAcc.dispose();
	}

	public void initialize(int _nPosX, int _nPosY) throws Exception {
		initialize( m_xParentWindow, _nPosX, _nPosY );
	}

	public void initialize(XWindowPeer _xParentWindow, int _nPosX, int _nPosY)
			throws Exception {

		super.initialize( DLS_SHOW_LIC, m_sTitle, ControlDims.DLG_SHOW_LICENSE_HEIGH, ControlDims.DLG_SHOW_LICENSE_WIDTH, _nPosX, _nPosY );

		int _nPosButton = ControlDims.DLG_SHOW_LICENSE_HEIGH - ControlDims.RSC_CD_PUSHBUTTON_HEIGHT * 4 / 3;
		m_xParentWindow = _xParentWindow;

		// cancel button
		insertButton( this,
				( ControlDims.DLG_SHOW_LICENSE_WIDTH - ControlDims.RSC_CD_PUSHBUTTON_WIDTH - 
						ControlDims.RSC_SP_DLG_INNERBORDER_RIGHT), 
				// button
				_nPosButton, ControlDims.RSC_CD_PUSHBUTTON_WIDTH, "okb", m_sOk,
				(short) PushButtonType.OK_value );
		Object oEdit = insertEditFieldModel( this, /*this*/null, ControlDims.RSC_SP_DLG_INNERBORDER_LEFT,
						ControlDims.RSC_SP_DLG_INNERBORDER_TOP,
						ControlDims.DLG_SHOW_LICENSE_HEIGH - ( ControlDims.RSC_CD_PUSHBUTTON_HEIGHT * 2 ),
						ControlDims.DLG_SHOW_LICENSE_WIDTH -
						ControlDims.RSC_SP_DLG_INNERBORDER_LEFT -
						ControlDims.RSC_SP_DLG_INNERBORDER_RIGHT, 0, m_sTheTextToShow, "cyx",
				true, true, true, true );

		XPropertySet xPSet = (XPropertySet) UnoRuntime
							.queryInterface( XPropertySet.class, oEdit );
//		Utilities.showProperties(xPSet);
		
		xPSet.setPropertyValue(new String("BackgroundColor"), new Integer(ControlDims.DLG_ABOUT_BACKG_COLOR));

		xDialog = (XDialog) UnoRuntime.queryInterface( XDialog.class,
				super.m_xDialogControl );
		createWindowPeer();
		// we do not center the dialog, since the master is already a dialog box
		// so comment it out
		// center();
	}
}
