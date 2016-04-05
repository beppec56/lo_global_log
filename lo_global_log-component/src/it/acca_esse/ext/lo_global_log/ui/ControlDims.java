package it.acca_esse.ext.lo_global_log.ui;

import it.acca_esse.ext.lo_global_log.Helpers;

public class ControlDims {

	public static final int	RSC_SP_DLG_INNERBORDER_BOTTOM	= 6;
	public static final int	RSC_CD_PUSHBUTTON_WIDTH			= 50;
	public static final int	RSC_CD_PUSHBUTTON_HEIGHT		= 14;
	public static final int	RSC_CD_EDIT_FIELD_HEIGHT		= 14;

	public static final int	RSC_SP_CTRL_DESC_X				= 3;
	public static final int	RSC_SP_CTRL_X					= 6;					// controls that are unrelated
	public static final int	RSC_SP_CTRL_Y					= 7;
	public static final int	RSC_SP_CTRL_GROUP_X				= 3;					// related controls, or controls in a groupbox
	public static final int	RSC_SP_CTRL_GROUP_Y				= 4;
	public static final int	RSC_SP_CTRL_DESC_Y				= 3;
	public static final int	RSC_SP_DLG_INNERBORDER_RIGHT	= 6;
	public static final int	RSC_SP_DLG_INNERBORDER_LEFT		= 6;
	public static final int	RSC_BS_CHARHEIGHT				= 8;

	public static final int	RSC_SP_DLG_INNERBORDER_TOP		= 6;
	public static final int	RSC_SP_TBPG_INNERBORDER_TOP		= 3;
	public static final int	RSC_SP_TBPG_INNERBORDER_RIGHT	= 6;
	public static final int	RSC_SP_TBPG_INNERBORDER_BOTTOM	= 6;
	public static final int	RSC_CD_FIXEDTEXT_HEIGHT			= RSC_BS_CHARHEIGHT;
	public static final int	RSC_SP_GRP_SPACE_Y				= 6;
	
	public static final int	DLG_ABOUT_WIDTH			= 205;
	public static final int	DLG_ABOUT_HEIGH			= 140;
	
	public static final int	DLG_SHOW_LICENSE_WIDTH			= 270;
	public static final int	DLG_SHOW_LICENSE_HEIGH			= 180;
	
	public static final int	DLG_ABOUT_BACKG_COLOR			= Helpers.getRGBColor(255, 255, 234);
	
	public static final int	DLG_CERT_TREE_BACKG_COLOR		= Helpers.getRGBColor(255, 255, 245);
	public static final int	DLG_CERT_TREE_STATE_ERROR_COLOR		= Helpers.getRGBColor(255, 102, 51);//means Orange 6 in OOo world
	public static final int	DLG_CERT_TREE_STATE_WARNING_COLOR		= Helpers.getRGBColor(255, 128, 128);//means ?? 

	public static final int	DLG_ROOT_VERIFY_WIDTH			= 205;
	public static final int	DLG_ROOT_VERIFY_HEIGH			= 180;
	public static final int	DLG_ROOT_VERIFY_BACKG_COLOR		= Helpers.getRGBColor(255, 255, 234);

	public static int RSC_CD_FIXEDTEXT_HEIGHT() {
		return RSC_BS_CHARHEIGHT;
	}

	public static int RSC_CD_FIXEDLINE_HEIGHT() {
		return RSC_BS_CHARHEIGHT;
	}

}
