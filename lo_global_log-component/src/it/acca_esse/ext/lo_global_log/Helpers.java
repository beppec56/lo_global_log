package it.acca_esse.ext.lo_global_log;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.sun.star.beans.PropertyValue;
import com.sun.star.beans.XPropertySet;
import com.sun.star.beans.XPropertySetInfo;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.AnyConverter;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

public class Helpers {

	//checks for connection to the internet through dummy request
    public static boolean isInternetReachable()
    {
            try {
                    //make a URL to a known source
                    URL url = new URL("http://www.google.com");

                    
                    //open a connection to that source
                    
                    HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

                    urlConnect.setConnectTimeout(20000);
                    //trying to retrieve data from the source. If there
                    //is no connection, this line will fail
                    @SuppressWarnings("unused")
					Object objData = urlConnect.getContent();

            } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
            }
            catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
            }
            return true;
    }

	public static String date2string(Date _aDate) {
		final String m_dateFormatXAdES = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat f = new SimpleDateFormat(m_dateFormatXAdES);
        f.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String dateStr = f.format(_aDate);
        return dateStr;
	}

	public static String getExtensionStorageSystemPath(XComponentContext _xCC) throws Exception, URISyntaxException, IOException {		
		String filesep = System.getProperty("file.separator");
		return getUserStorageSystemPath(_xCC)+filesep+"extdata"+filesep+GlobalConstant.m_sGLOBALLOGEXTENSION_IDENTIFIER;
	}

	public static String getUserStorageSystemPath(XComponentContext _xCC) throws Exception, URISyntaxException, IOException {
		String aPath = getUserStoragePathURL(_xCC);
		return fromURLtoSystemPath(aPath);
	}

	public static String getUserStoragePathURL(XComponentContext _xCC) throws Exception {
		XMultiComponentFactory xMCF = _xCC.getServiceManager();
		Object oPathServ = xMCF.createInstanceWithContext("com.sun.star.util.PathSettings", _xCC);
		if(oPathServ != null){
			XPropertySet xPS = (XPropertySet)UnoRuntime.queryInterface(XPropertySet.class, oPathServ);
			String aPath = (String)xPS.getPropertyValue("Storage");
			return aPath;
		}
		else
			throw (new Exception("The PathSetting service can not be retrieved") );
	}

	public static String fromURLtoSystemPath(String _aUrl) throws URISyntaxException, IOException {
		if(_aUrl != null) {
			URL aURL = new URL(_aUrl);
			URI aUri = new URI(aURL.toString());
			File aFile = new File(aUri);
			return aFile.getCanonicalPath();
		}
		else
			return "";
	}
	public static void showProperty(XPropertySet xPropSet, String pName) {
		try {
			XPropertySetInfo xPInfo = xPropSet.getPropertySetInfo();
			if (xPInfo.hasPropertyByName(pName)
					&& !AnyConverter.isVoid(xPropSet.getPropertyValue(pName))) {
				if (AnyConverter.isObject(xPropSet.getPropertyValue(pName))) {
					Object obj = xPropSet.getPropertyValue(pName);
					System.out.println(" isObject =-> " + obj.toString());
				} else if (AnyConverter.isString(xPropSet.getPropertyValue(pName))) {
					String st = AnyConverter.toString(xPropSet.getPropertyValue(pName));
					System.out.println(" isString =-> " + st);
				} else if (AnyConverter.isByte(xPropSet.getPropertyValue(pName))) {
					byte byt = AnyConverter.toByte(xPropSet.getPropertyValue(pName));
					System.out.println(" isByte =-> " + byt);
				} else if (AnyConverter.isShort(xPropSet.getPropertyValue(pName))) {
					short sho = AnyConverter.toShort(xPropSet.getPropertyValue(pName));
					System.out.println(" isShort =-> " + sho);
				} else if (AnyConverter.isInt(xPropSet.getPropertyValue(pName))) {
					int sho = AnyConverter.toInt(xPropSet.getPropertyValue(pName));
					System.out.println(" isInt =-> " + sho);
				} else if (AnyConverter.isFloat(xPropSet.getPropertyValue(pName))) {
					float sho = AnyConverter.toFloat(xPropSet.getPropertyValue(pName));
					System.out.println(" isFloat =-> " + sho);
				} else if (AnyConverter.isBoolean(xPropSet.getPropertyValue(pName))) {
					boolean sho = AnyConverter
							.toBoolean(xPropSet.getPropertyValue(pName));
					System.out.println(" Boolean =-> " + sho);
				} else
					System.out.println(" getType =-> "
							+ AnyConverter.getType(xPropSet.getPropertyValue(pName))
									.toString());
			} else
				System.out.println("(void)");
		} catch (com.sun.star.uno.Exception e) {
			System.out
					.println(" showProperty " + pName + " EXCEPTION: " + e.getMessage());
			// e.printStackTrace();
		}
	}

	public static String showPropertyValue(PropertyValue aPropVal) {
		String theMessage = aPropVal.Name + ",   ";
		try {
		if (AnyConverter.isObject(aPropVal)) {
			Object obj = aPropVal.Value;
			theMessage = theMessage + " isObject =-> " + obj.toString();
		} else if (AnyConverter.isString(aPropVal)) {
			String st = AnyConverter.toString(aPropVal.Value);
			theMessage = theMessage + " isString =-> " + st;
		} else if (AnyConverter.isByte(aPropVal)) {
			byte byt = AnyConverter.toByte(aPropVal.Value);
			theMessage = theMessage + " isByte =-> " + byt;
		} else if (AnyConverter.isShort(aPropVal)) {
			short sho = AnyConverter.toShort(aPropVal.Value);
			theMessage = theMessage + " isShort =-> " + sho;
		} else if (AnyConverter.isInt(aPropVal)) {
			int sho = AnyConverter.toInt(aPropVal.Value);
			theMessage = theMessage + " isInt =-> " + sho;
		} else if (AnyConverter.isFloat(aPropVal)) {
			float sho = AnyConverter.toFloat(aPropVal.Value);
			theMessage = theMessage + " isFloat =-> " + sho;
		} else if (AnyConverter.isBoolean(aPropVal)) {
			boolean sho = AnyConverter
					.toBoolean(aPropVal.Value);
			theMessage = theMessage + " Boolean =-> " + sho;
		} else
			theMessage = theMessage + " getType =-> "
					+ AnyConverter.getType(aPropVal)
							.toString();

		} catch (Throwable e) {
			theMessage = theMessage + " EXCEPTION: " + e.getMessage();
		}
		return theMessage;
	}

	/**
	 * return the com.sun.star.util.Color formed from the fundamental color
	 * the color is an object of typecom.sun.star.util.Color (a long)
	 * its bytes are: ignore, RGB:red,RGB:green,RGB:blue, hence grey will be:
	 *  127*256*256+127*256+127
	 * @param nRed
	 * @param nGreen
	 * @param nBlue
	 * @return
	 */
	public static int getRGBColor(int nRed, int nGreen, int nBlue) {
		return (nRed*256*256+nGreen*256+nBlue);
	}
}
