package it.acca_esse.ext.lo_global_log;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class LogJarVersionComp {
	
	public LogJarVersionComp() {
	}

	public static String getVersion() {
		 //TODO: change the jar library name to the right one...
		final String m_aJarFileName = "lo_global_log.comp.jar";
		URI executivePath = null;

		String m_aJarVersion = "";

		CodeSource aCs = LogJarVersionComp.class.getProtectionDomain().getCodeSource();
        if(aCs != null) {
            try {
                URL aURL = aCs.getLocation(); // where this class is 'seen' by the java runtime
                																												String thisFile = aURL.getFile();
                int pos = aURL.toString().indexOf(m_aJarFileName);
                if(pos == -1) {
                    //non esiste, l'URL è il path
                    executivePath = new URI(aURL.toString());
                }
                else {
                    //esiste, elimina
                    executivePath = new URI(aURL.toString().substring(0, pos-1));
                }
            } catch (URISyntaxException e) {
//                m_aLogger.log(e, false);
            }
        }

        if (executivePath == null)
        	return "";
      //legge la versione dal manifest
        try {
            URI aNew = new URI(executivePath.getScheme(),
                    executivePath.getUserInfo(), executivePath.getHost(), executivePath.getPort(),
                    executivePath.getPath()+"/"+m_aJarFileName,
                    executivePath.getQuery(),
                    executivePath.getFragment());
//            info(aNew.getPath());
            JarFile jarFile = new JarFile(aNew.getPath());
            Manifest mf = jarFile.getManifest();
            Attributes mfAttr = mf.getMainAttributes();
            //qui estrae la versione e la stampa.
            String aVers = mfAttr.getValue("Implementation-Version");
            if(aVers != null) {
                String jarVersion = "version: ";
                jarVersion += aVers;
                m_aJarVersion = jarVersion; 
            }
            jarFile.close();
            //else
                //jarVersion += "not found in jar file !";
        } catch (IOException e3) {
//            m_aLogger.log(e3, false);
        } catch (URISyntaxException e) {
//            m_aLogger.log(e, false);
        }		
		return m_aJarFileName+":    "+m_aJarVersion;
	}
}
