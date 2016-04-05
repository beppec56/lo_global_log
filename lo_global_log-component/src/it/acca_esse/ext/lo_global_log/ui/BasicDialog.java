package it.acca_esse.ext.lo_global_log.ui;

import com.sun.star.awt.ActionEvent;
import com.sun.star.awt.AdjustmentEvent;
import com.sun.star.awt.ItemEvent;
import com.sun.star.awt.Rectangle;
import com.sun.star.awt.SpinEvent;
import com.sun.star.awt.TextEvent;
import com.sun.star.awt.XActionListener;
import com.sun.star.awt.XAdjustmentListener;
import com.sun.star.awt.XButton;
import com.sun.star.awt.XControl;
import com.sun.star.awt.XControlContainer;
import com.sun.star.awt.XControlModel;
import com.sun.star.awt.XDialog;
import com.sun.star.awt.XFixedText;
import com.sun.star.awt.XFocusListener;
import com.sun.star.awt.XItemListener;
import com.sun.star.awt.XSpinListener;
import com.sun.star.awt.XTextComponent;
import com.sun.star.awt.XTextListener;
import com.sun.star.awt.XToolkit;
import com.sun.star.awt.XTopWindow;
import com.sun.star.awt.XWindow;
import com.sun.star.awt.XWindowPeer;
import com.sun.star.awt.tree.ExpandVetoException;
import com.sun.star.awt.tree.TreeExpansionEvent;
import com.sun.star.awt.tree.XTreeExpansionListener;
import com.sun.star.beans.XMultiPropertySet;
import com.sun.star.beans.XPropertySet;
import com.sun.star.container.XNameAccess;
import com.sun.star.container.XNameContainer;
import com.sun.star.frame.XFrame;
import com.sun.star.frame.XFramesSupplier;
import com.sun.star.frame.XModel;
import com.sun.star.lang.EventObject;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;



public class BasicDialog implements XTextListener, XSpinListener, XActionListener,
/*XFocusListener,*/ XItemListener, XAdjustmentListener, XTreeExpansionListener /*, XKeyListener*/ {
	protected XComponentContext							m_xContext			= null;
	protected com.sun.star.lang.XMultiComponentFactory	m_xMCF;
	protected XMultiServiceFactory						m_xMSFDialogModel;
	protected XModel									m_xModel;
	protected XNameContainer							m_xDlgModelNameContainer;
	protected XControlContainer							m_xDlgContainer;
	// protected XNameAccess m_xDlgModelNameAccess;
	protected XControl									m_xDialogControl;
	protected XDialog									xDialog;
	// private XReschedule mxReschedule;
	protected XWindowPeer								m_xWindowPeer		= null;
	protected XTopWindow								m_xTopWindow		= null;
	protected XWindow									m_xParentWindow		= null;
	protected XFrame									m_xParentFrame		= null;
	protected XComponent								m_xComponent		= null;
	protected Rectangle									m_rectParentWindow	= null;


	public BasicDialog(XFrame _xFrame, XComponentContext _xContext,
			XMultiComponentFactory _xMCF) {
		m_xContext = _xContext;
		m_xMCF = _xMCF;
		m_xParentFrame = _xFrame;
//		m_aLogger = new DynamicLoggerDialog(this, _xContext);
//		m_aLogger.ctor();
		// from the frame get the parent window and set the window peer
		if (m_xParentFrame == null) {
			Object oDesktop = null;
			try {
				oDesktop = m_xMCF.createInstanceWithContext(
						"com.sun.star.frame.Desktop", m_xContext );
				XFramesSupplier xFramesSupplier = (XFramesSupplier) UnoRuntime
						.queryInterface( XFramesSupplier.class, oDesktop );
				m_xParentFrame = (XFrame) xFramesSupplier.getActiveFrame();
				// println("default Frame...");
			} catch (com.sun.star.uno.Exception oException) {
//				m_aLogger.severe(oException);
			}
		}
		if (m_xParentFrame != null) {
			m_xParentWindow = m_xParentFrame.getComponentWindow();
			if (m_xParentWindow != null) {
				m_rectParentWindow = m_xParentWindow.getPosSize();
				// println("H: " + m_rectParentWindow.Height + " W: "
				// + m_rectParentWindow.Width + " X: " + m_rectParentWindow.X
				// + " Y: " + m_rectParentWindow.Y);
			} /*else
				m_aLogger.severe("BasicDialog", "no parent Window!" );*/
		} /*else
			m_aLogger.severe( "BasicDialog","no parent Frame!" );*/
		createDialog( m_xMCF );
	}

	@Override
	public void disposing(EventObject arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestChildNodes(TreeExpansionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeCollapsed(TreeExpansionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeCollapsing(TreeExpansionEvent arg0) throws ExpandVetoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeExpanded(TreeExpansionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeExpanding(TreeExpansionEvent arg0) throws ExpandVetoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void down(SpinEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void first(SpinEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void last(SpinEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void up(SpinEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void textChanged(TextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	protected void createDialog(XMultiComponentFactory _xMCF) {
		try {
			Object oDialogModel = _xMCF.createInstanceWithContext(
					"com.sun.star.awt.UnoControlDialogModel", m_xContext );

			// The XMultiServiceFactory of the dialogmodel is needed to
			// instantiate the controls...
			m_xMSFDialogModel = (XMultiServiceFactory) UnoRuntime.queryInterface(
					XMultiServiceFactory.class, oDialogModel );

			// The named container is used to insert the created controls
			// into...
			m_xDlgModelNameContainer = (XNameContainer) UnoRuntime.queryInterface(
					XNameContainer.class, oDialogModel );

			// create the dialog...
			Object oUnoDialog = _xMCF.createInstanceWithContext(
					"com.sun.star.awt.UnoControlDialog", m_xContext );
			m_xDialogControl = (XControl) UnoRuntime.queryInterface( XControl.class,
					oUnoDialog );

			// The scope of the control container is public...
			m_xDlgContainer = (XControlContainer) UnoRuntime.queryInterface(
					XControlContainer.class, oUnoDialog );

			m_xTopWindow = (XTopWindow) UnoRuntime.queryInterface( XTopWindow.class,
					m_xDlgContainer );

			// link the dialog and its model...
			XControlModel xControlModel = (XControlModel) UnoRuntime.queryInterface(
					XControlModel.class, oDialogModel );

			m_xDialogControl.setModel( xControlModel );
		} catch (com.sun.star.uno.Exception exception) {
			exception.printStackTrace( System.out );
		}
	}

	public short executeDialog() throws com.sun.star.script.BasicErrorException {

		if (m_xWindowPeer == null) {
			createWindowPeer();
		}
		xDialog = (XDialog) UnoRuntime.queryInterface( XDialog.class, m_xDialogControl );
		m_xComponent = (XComponent) UnoRuntime.queryInterface( XComponent.class,
				m_xDialogControl );
		// repaintDialogStep();
		short Ret = xDialog.execute();
		m_xComponent.dispose();
		return Ret;
	}

	/**
	 * Creates a peer for this dialog, using the active LO frame as the parent
	 * window.
	 * 
	 * @return
	 * @throws java.lang.Exception
	 */
	public XWindowPeer createWindowPeer() throws com.sun.star.script.BasicErrorException {
		return createWindowPeer( null );
	}

	/**
	 * create a peer for this dialog, using the given peer as a parent.
	 * 
	 * @param parentPeer
	 * @return
	 * @throws java.lang.Exception
	 */
	public XWindowPeer createWindowPeer(XWindowPeer _xWindowParentPeer)
			throws com.sun.star.script.BasicErrorException {
		try {
			if (_xWindowParentPeer == null) {
				XWindow xWindow = (XWindow) UnoRuntime.queryInterface( XWindow.class,
						m_xDlgContainer );
				xWindow.setVisible( false );
				// XToolkit xToolkit = SignatureHandler.getToolkit();
				Object oToolkit = m_xMCF.createInstanceWithContext(
						"com.sun.star.awt.Toolkit", m_xContext );
/*				XWindowPeer xWindowParentPeer = ( (XToolkit) UnoRuntime.queryInterface(
						XToolkit.class, oToolkit ) ).getDesktopWindow();*/
				XToolkit xToolkit = (XToolkit) UnoRuntime.queryInterface( XToolkit.class,
						oToolkit );
				// mxReschedule = (XReschedule)
				// UnoRuntime.queryInterface(XReschedule.class, xToolkit);
				m_xDialogControl.createPeer( xToolkit, _xWindowParentPeer );
				m_xWindowPeer = m_xDialogControl.getPeer();
				return m_xWindowPeer;
			}
		} catch (com.sun.star.uno.Exception exception) {
			exception.printStackTrace( System.out );
		}
		return null;
	}

	/**
	 * makes a String unique by appending a numerical suffix
	 * 
	 * @param _xElementContainer
	 *            the com.sun.star.container.XNameAccess container that the new
	 *            Element is going to be inserted to
	 * @param _sElementName
	 *            the StemName of the Element
	 */
	public static String createUniqueName(XNameAccess _xElementContainer,
			String _sElementName) {
		boolean bElementexists = true;
		int i = 1;
		String BaseName = _sElementName;
		while (bElementexists) {
			bElementexists = _xElementContainer.hasByName( _sElementName );
			if (bElementexists) {
				i += 1;
				_sElementName = BaseName + Integer.toString( i );
			}
		}
		return _sElementName;
	}

	public void initialize(String sName, String sTitle, int nHeight, int nWidth,
			int nPosX, int nPosY) throws com.sun.star.script.BasicErrorException {
		String[] PropertyNames = new String[] { "Height", "Moveable", "Name",
				"PositionX", "PositionY", "Step", "TabIndex", "Title", "Width" };
		Object[] PropertyValues = new Object[] { new Integer( nHeight ), Boolean.TRUE,
				sName, new Integer( nPosX ), new Integer( nPosY ), new Integer( 0 ),
				new Short( (short) 0 ), sTitle, new Integer( nWidth ) };
		try {
			XMultiPropertySet xMultiPropertySet = (XMultiPropertySet) UnoRuntime
					.queryInterface( XMultiPropertySet.class, m_xDlgModelNameContainer );
			xMultiPropertySet.setPropertyValues( PropertyNames, PropertyValues );
		} catch (com.sun.star.uno.Exception ex) {
			ex.printStackTrace( System.out );
		}
	}
	
	public XButton insertButton(XActionListener _xActionListener, int _nPosX, int _nPosY,
			int _nWidth, int _nHeight, String _sName, String _sLabel,
			short _nPushButtonType, int _nStep) {
		XButton xButton = null;
		try {
			// create a unique name by means of an own implementation...
			// String sName = createUniqueName(m_xDlgModelNameContainer,
			// "CommandButton");

			// create a controlmodel at the multiservicefactory of the dialog
			// model...
			Object oButtonModel = m_xMSFDialogModel
					.createInstance( "com.sun.star.awt.UnoControlButtonModel" );
			XMultiPropertySet xButtonMPSet = (XMultiPropertySet) UnoRuntime
					.queryInterface( XMultiPropertySet.class, oButtonModel );
			// Set the properties at the model - keep in mind to pass the
			// property names in alphabetical order!
			xButtonMPSet.setPropertyValues( new String[] { "Height", "Label", "Name",
					"PositionX", "PositionY", "PushButtonType", "TabIndex", "Width" },
					new Object[] { new Integer( _nHeight ), _sLabel, _sName,
							new Integer( _nPosX ), new Integer( _nPosY ),
							new Short( _nPushButtonType ), new Short( (short)_nStep ),
							new Integer( _nWidth ) } );

			// add the model to the NameContainer of the dialog model
			m_xDlgModelNameContainer.insertByName( _sName, oButtonModel );
			XControl xButtonControl = m_xDlgContainer.getControl( _sName );
			xButton = (XButton) UnoRuntime.queryInterface( XButton.class, xButtonControl );
			// An ActionListener will be notified on the activation of the
			// button...
			xButton.addActionListener( _xActionListener );
			
/*			XWindow xTFWindow = (XWindow) UnoRuntime.queryInterface( XWindow.class,
							xButtonControl );*/
//			xTFWindow.addFocusListener( this );
//			xTFWindow.addKeyListener( this );			
		} catch (com.sun.star.uno.Exception ex) {
			/*
			 * perform individual exception handling here. Possible exception
			 * types are: com.sun.star.lang.IllegalArgumentException,
			 * com.sun.star.lang.WrappedTargetException,
			 * com.sun.star.container.ElementExistException,
			 * com.sun.star.beans.PropertyVetoException,
			 * com.sun.star.beans.UnknownPropertyException,
			 * com.sun.star.uno.Exception
			 */
			ex.printStackTrace( System.out );
		}
		return xButton;
	}

	public XButton insertButton(XActionListener _xActionListener, int _nPosX, int _nPosY,
			int _nWidth, String _WidgetName, String _WidgetLabelToDisplay, short _nPushButtonType, int _nStep) {
		return insertButton( _xActionListener, _nPosX, _nPosY, _nWidth,
				ControlDims.RSC_CD_PUSHBUTTON_HEIGHT, _WidgetName, _WidgetLabelToDisplay, _nPushButtonType,
				_nStep );
	}

	public XButton insertButton(XActionListener _xActionListener, int _nPosX, int _nPosY,
			int _nWidth, String _sName, String _sLabel, short _nPushButtonType) {
		return insertButton( _xActionListener, _nPosX, _nPosY, _nWidth, _sName, _sLabel,
				_nPushButtonType, 0 );
	}

	protected Object insertEditFieldModel(XTextListener _xTextListener,
			XFocusListener _xFocusListener, int _nPosX, int _nPosY, int _nHeight,
			int _nWidth, int _TabStep, String _DefaultText, String _WidgetName, boolean _bReadOnly,
			boolean _bMultiLine, boolean _bVScroll, boolean _bHScroll) {

		Object m_oTFModel = null;
		try {
			// create a controlmodel at the multiservicefactory of the dialog
			// model...
			m_oTFModel = m_xMSFDialogModel.createInstance( "com.sun.star.awt.UnoControlEditModel" );
			XMultiPropertySet xTFModelMPSet = (XMultiPropertySet) UnoRuntime
					.queryInterface( XMultiPropertySet.class, m_oTFModel );
			// Set the properties at the model - keep in mind to pass the
			// property names in alphabetical order!
			xTFModelMPSet.setPropertyValues( new String[] { "Height", "HScroll",
					"MultiLine", "Name", "PositionX", "PositionY", "ReadOnly", "TabIndex",
					"Text", "VScroll", "Width" }, new Object[] { 
					new Integer( _nHeight ),
					new Boolean( _bHScroll ), new Boolean( _bMultiLine ), _WidgetName,
					new Integer( _nPosX ), new Integer( _nPosY ),
					new Boolean( _bReadOnly ), new Short( (short)_TabStep ), _DefaultText,
					new Boolean( _bVScroll ), new Integer( _nWidth ) } );

			// The controlmodel is not really available until inserted to the
			// Dialog container
			m_xDlgModelNameContainer.insertByName( _WidgetName, m_oTFModel );
			XControl xTFControl = m_xDlgContainer.getControl( _WidgetName );
			// add a textlistener that is notified on each change of the
			// controlvalue...
			XTextComponent xTextComponent = (XTextComponent) UnoRuntime.queryInterface(
					XTextComponent.class, xTFControl );
			XWindow xTFWindow = (XWindow) UnoRuntime.queryInterface( XWindow.class,
					xTFControl );
			xTFWindow.addFocusListener( _xFocusListener );
			xTextComponent.addTextListener( _xTextListener );
//			xTFWindow.addKeyListener( this );
		} catch (com.sun.star.uno.Exception ex) {
			/*
			 * perform individual exception handling here. Possible exception
			 * types are: com.sun.star.lang.IllegalArgumentException,
			 * com.sun.star.lang.WrappedTargetException,
			 * com.sun.star.container.ElementExistException,
			 * com.sun.star.beans.PropertyVetoException,
			 * com.sun.star.beans.UnknownPropertyException,
			 * com.sun.star.uno.Exception
			 */
			ex.printStackTrace( System.out );
		}
		return m_oTFModel;
	}

	protected void center() {
		if (m_rectParentWindow != null) {
			XWindow xDlgWindow = (XWindow) UnoRuntime.queryInterface( XWindow.class,
					xDialog );
			Rectangle aDlgPosSize = xDlgWindow.getPosSize();
			int iWindowHeight = m_rectParentWindow.Height;
			int iWindowWidth = m_rectParentWindow.Width;
			int iDialogWidth = aDlgPosSize.Width;
			int iDialogHeight = aDlgPosSize.Height;

			int iXPos = ( ( iWindowWidth / 2 ) - ( iDialogWidth / 2 ) );
			int iYPos = ( ( iWindowHeight / 2 ) - ( iDialogHeight / 2 ) );

			xDlgWindow.setPosSize( iXPos, iYPos, iDialogWidth, iDialogHeight,
					com.sun.star.awt.PosSize.POS );
		}
	}

	protected void endDialog() {
		xDialog.endExecute();
	}

	// / overloading: testo con nome aggiunto e grassetto
	public XFixedText insertFixedText(XActionListener _xMouseListener, int _nPosX,
			int _nPosY, int _nWidth, int _nStep, String _sLabel, String _sName,
			float _fWeight) {
		XFixedText xFixedText = null;
		try {
			// create a unique name by means of an own implementation...
			// String sName = createUniqueName(m_xDlgModelNameContainer,
			// "Label");

			// create a controlmodel at the multiservicefactory of the dialog
			// model...
			Object oFTModel = m_xMSFDialogModel
					.createInstance( "com.sun.star.awt.UnoControlFixedTextModel" );
			XMultiPropertySet xFTModelMPSet = (XMultiPropertySet) UnoRuntime
					.queryInterface( XMultiPropertySet.class, oFTModel );
			// Set the properties at the model - keep in mind to pass the
			// property names in alphabetical order!

			xFTModelMPSet.setPropertyValues( new String[] { "FontPitch", "FontWeight",
					"Height", "Name", "PositionX", "PositionY", "TabIndex", "Width" },
					new Object[] { new Short( (short) com.sun.star.awt.FontPitch.FIXED ),
							_fWeight, new Integer( ControlDims.RSC_BS_CHARHEIGHT ),
							_sName, new Integer( _nPosX ), new Integer( _nPosY ),
							new Short( (short)_nStep ), new Integer( _nWidth ) } );
			// add the model to the NameContainer of the dialog model
			m_xDlgModelNameContainer.insertByName( _sName, oFTModel );

			// The following property may also be set with XMultiPropertySet but
			// we
			// use the XPropertySet interface merely for reasons of
			// demonstration
			XPropertySet xFTPSet = (XPropertySet) UnoRuntime.queryInterface(
					XPropertySet.class, oFTModel );
			xFTPSet.setPropertyValue( "Label", _sLabel );

			// reference the control by the Name
			XControl xFTControl = m_xDlgContainer.getControl( _sName );
			xFixedText = (XFixedText) UnoRuntime.queryInterface( XFixedText.class,
					xFTControl );
/*			XWindow xWindow = (XWindow) UnoRuntime.queryInterface( XWindow.class,
					xFTControl );*/
//			xWindow.addMouseListener( _xMouseListener );
		} catch (com.sun.star.uno.Exception ex) {
			/*
			 * perform individual exception handling here. Possible exception
			 * types are: com.sun.star.lang.IllegalArgumentException,
			 * com.sun.star.lang.WrappedTargetException,
			 * com.sun.star.container.ElementExistException,
			 * com.sun.star.beans.PropertyVetoException,
			 * com.sun.star.beans.UnknownPropertyException,
			 * com.sun.star.uno.Exception
			 */
			ex.printStackTrace( System.out );
		}
		return xFixedText;
	}

	// / overloading: testo con nome aggiunto
	public XFixedText insertFixedText(XActionListener _xMouseListener, int _nPosX,
			int _nPosY, int Height, int _nWidth, int _nStep, String _sLabel, String _sName) {
		XFixedText xFixedText = null;
		try {
			// create a unique name by means of an own implementation...
			// String sName = createUniqueName(m_xDlgModelNameContainer,
			// "Label");

			// create a controlmodel at the multiservicefactory of the dialog
			// model...
			Object oFTModel = m_xMSFDialogModel
					.createInstance( "com.sun.star.awt.UnoControlFixedTextModel" );
			XMultiPropertySet xFTModelMPSet = (XMultiPropertySet) UnoRuntime
					.queryInterface( XMultiPropertySet.class, oFTModel );
			// Set the properties at the model - keep in mind to pass the
			// property names in alphabetical order!

			xFTModelMPSet.setPropertyValues( new String[] { "FontPitch", "FontWeight",
					"Height", "Name", "PositionX", "PositionY", "TabIndex", "Width" },
					new Object[] { new Short( (short) com.sun.star.awt.FontPitch.FIXED ),
					(float) 100, new Integer( Height ),
							_sName, new Integer( _nPosX ), new Integer( _nPosY ),
							new Short( (short)_nStep ), new Integer( _nWidth ) } );
			// add the model to the NameContainer of the dialog model
			m_xDlgModelNameContainer.insertByName( _sName, oFTModel );

			// The following property may also be set with XMultiPropertySet but
			// we
			// use the XPropertySet interface merely for reasons of
			// demonstration
			XPropertySet xFTPSet = (XPropertySet) UnoRuntime.queryInterface(
					XPropertySet.class, oFTModel );
			xFTPSet.setPropertyValue( "Label", _sLabel );

			// reference the control by the Name
			XControl xFTControl = m_xDlgContainer.getControl( _sName );
			xFixedText = (XFixedText) UnoRuntime.queryInterface( XFixedText.class,
					xFTControl );
/*			XWindow xWindow = (XWindow) UnoRuntime.queryInterface( XWindow.class,
					xFTControl );*/
//			xWindow.addMouseListener( _xMouseListener );
		} catch (com.sun.star.uno.Exception ex) {
			/*
			 * perform individual exception handling here. Possible exception
			 * types are: com.sun.star.lang.IllegalArgumentException,
			 * com.sun.star.lang.WrappedTargetException,
			 * com.sun.star.container.ElementExistException,
			 * com.sun.star.beans.PropertyVetoException,
			 * com.sun.star.beans.UnknownPropertyException,
			 * com.sun.star.uno.Exception
			 */
			ex.printStackTrace( System.out );
		}
		return xFixedText;
	}

	// / overloading: testo con nome aggiunto
	public XFixedText insertFixedText(XActionListener _xMouseListener, int _nPosX,
			int _nPosY, int _nWidth, int _TabStep, String _WidgetLabelToDisplay, String _WidgetName) {
		return insertFixedText( _xMouseListener, _nPosX, _nPosY, _nWidth, _TabStep,
				_WidgetLabelToDisplay, _WidgetName, (float) 100 );
	}

	public XFixedText insertFixedText(XActionListener _xMouseListener, int _nPosX,
			int _nPosY, int _nWidth, int _nStep, String _sLabel) {
		// create a unique name by means of an own implementation...
		String sName = createUniqueName( m_xDlgModelNameContainer, "Label" );
		return insertFixedText( _xMouseListener, _nPosX, _nPosY, _nWidth, _nStep,
				_sLabel, sName );
	}

}
