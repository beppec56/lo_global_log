package it.acca_esse.ext.lo_global_log.logging;

import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertySet;
import com.sun.star.container.XNameAccess;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XEventListener;
import com.sun.star.uno.AnyConverter;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

import it.acca_esse.ext.lo_global_log.GlobalConstant;


public class LoggerParametersAccess extends LoggerConfigurationAccess implements XComponent {

private Object	m_oOptionsParametersRegKey	= null;
// this class is instantiated by the logger upon starting, so no log is really possible	

/**
 * @param _Context
 */
public LoggerParametersAccess(XComponentContext _Context) {
	super(_Context);
	try {
		m_oOptionsParametersRegKey = createConfigurationReadWriteView( GlobalConstant.m_sEXTENSION_CONF_OPTIONS );
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
	}
}

public String getText(String sTheName) {
	String retVal = null;
	if(m_oOptionsParametersRegKey != null) {
		try {
			XNameAccess xNAccess = (XNameAccess) UnoRuntime.queryInterface(
					XNameAccess.class, m_oOptionsParametersRegKey );

			if (xNAccess.hasByName( sTheName )) {
				XPropertySet xPS = (XPropertySet) UnoRuntime.queryInterface(
						XPropertySet.class, m_oOptionsParametersRegKey );
				retVal = AnyConverter.toString( xPS.getPropertyValue( sTheName ) );
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnknownPropertyException e) {
			e.printStackTrace();
		} catch (WrappedTargetException e) {
			e.printStackTrace();
		}
	}
	return retVal;
}

public boolean getBoolean(String sTheName) {
	boolean retVal = false;
	if(m_oOptionsParametersRegKey != null) {
		try {
			XNameAccess xNAccess = (XNameAccess) UnoRuntime.queryInterface(
					XNameAccess.class, m_oOptionsParametersRegKey );

			if (xNAccess.hasByName( sTheName )) {
				XPropertySet xPS = (XPropertySet) UnoRuntime.queryInterface(
						XPropertySet.class, m_oOptionsParametersRegKey );
				retVal = AnyConverter.toBoolean( xPS.getPropertyValue( sTheName ) );
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownPropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrappedTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return retVal;
}

public int getNumber(String sTheName) {
	int retVal = 0;
	if(m_oOptionsParametersRegKey != null) {
		try {
			XNameAccess xNAccess = (XNameAccess) UnoRuntime.queryInterface(
					XNameAccess.class, m_oOptionsParametersRegKey );
			if (xNAccess.hasByName( sTheName )) {
				XPropertySet xPS = (XPropertySet) UnoRuntime.queryInterface(
						XPropertySet.class, m_oOptionsParametersRegKey );
				retVal = AnyConverter.toInt( xPS.getPropertyValue( sTheName ) );
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownPropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrappedTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return retVal;
}

public void addEventListener(XEventListener arg0) {
	// TODO Auto-generated method stub
}

/* (non-Javadoc)
 * @see com.sun.star.lang.XComponent#dispose()
 */
public void dispose() {
	synchronized (this) {
		if(m_oOptionsParametersRegKey != null) {
			// now clean up
			((XComponent) UnoRuntime.queryInterface(XComponent.class, m_oOptionsParametersRegKey)).dispose();
			m_oOptionsParametersRegKey = null;
		}
	}
}

/* (non-Javadoc)
 * @see com.sun.star.lang.XComponent#removeEventListener(com.sun.star.lang.XEventListener)
 */
public void removeEventListener(XEventListener arg0) {
	// TODO Auto-generated method stub
	System.out.println(this.getClass().getName()+" removeEventListener");		
}

}