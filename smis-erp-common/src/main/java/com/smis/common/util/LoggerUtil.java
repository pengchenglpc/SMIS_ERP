package com.smis.common.util;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class LoggerUtil {
	public static Logger MyLogger = Logger.getLogger("MemberLogger");  
	public static void printRequestLog(Map<String, String> reqParam) {
		MyLogger.debug("====================报文打印========================");
		Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> en = it.next();
			MyLogger.debug("[" + en.getKey() + "] = [" + en.getValue() + "]");
		}
		MyLogger.debug("====================报文结束========================");
	}
}
