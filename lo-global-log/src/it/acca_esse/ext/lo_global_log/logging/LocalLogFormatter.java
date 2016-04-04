package it.acca_esse.ext.lo_global_log.logging;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LocalLogFormatter extends Formatter {

	/**
	 * 
	 */
	public LocalLogFormatter() {
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	public String format(LogRecord _rec) {
		String sReturn;
		String sLevel;
		
		if(_rec.getLevel() == Level.FINER)
			sLevel = "l ";
		else if(_rec.getLevel() == Level.CONFIG)
			sLevel = "c ";
		else if(_rec.getLevel() == Level.SEVERE)
			sLevel = "S ";
		else if (_rec.getLevel() == Level.WARNING)
			sLevel = "W ";
		else if (_rec.getLevel() == Level.FINE)
			sLevel = "d ";
		else
			sLevel = "i ";			

//		sReturn = getTimeMs(_rec) + _rec.getLoggerName() + "."+ _rec.getSourceMethodName()+ " "+ _rec.getMessage();	
/*		sReturn = sLevel+getTimeMs(_rec) + " " + _rec.getLoggerName() +" "+
						_rec.getSourceClassName()+" "+ _rec.getSourceMethodName()+" "+
						_rec.getMessage()+"\n";*/
		sReturn = sLevel+getTimeMs(_rec) + " "+
		_rec.getSourceClassName()+" "+ _rec.getSourceMethodName()+" "+
		_rec.getMessage()+"\n";
		return sReturn;
	}

	private String getTimeMs(LogRecord _rec) {
		Date aDate = new Date(_rec.getMillis());
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(aDate);	
		
//string with date and time
//		String time = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL", calendar);
//string with time only
		String time = String.format("%1$tH:%1$tM:%1$tS.%1$tL", calendar);
		return time;
	}
	
	public String getHead(Handler h) {
		return ""; // called when the formatter is instantianted
		
	}
	
	public String getTail(Handler h) {
		return "";
	}
	
	public String formatter(LogRecord rec) {
		return "";
	}
}
