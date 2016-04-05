package it.acca_esse.ext.lo_global_log.registry;

import com.sun.star.uno.XInterface;

public interface IGeneralConfigurationProcessor {

	
	/** process an OOo configuration value item.<br>
	 * Used when recursing into the OOo registry structure
	 * 
	 * @param _sPath a string holding the path to examine
	 * @param _aValue the value to process
	 * @param _aObject a user object to process
	 */
	public abstract void processValueElement(String _sPath, Object _aValue, Object _aObject);

	/**
	 * 
	 * @param _sPath a string holding the path to examine
	 * @param _xElement the element to examine
	 */
// process a structural item
  	public abstract void processStructuralElement(String _sPath, XInterface _xElement);

}
