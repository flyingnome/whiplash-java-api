package com.grandst.whiplash.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class JsonCleaner {
	
	public static String cleanDateFormat(String json){
		Pattern regex = Pattern.compile("\\d\\d:\\d\\d:\\d\\d[-\\+]\\d\\d:\\d\\d"); 
		Matcher regexMatcher = regex.matcher(json);
		StringBuffer buff = new StringBuffer();
		while(regexMatcher.find()){
			regexMatcher.appendReplacement(buff, getSubOfMatch(regexMatcher));
		}
		regexMatcher.appendTail(buff);
		return buff.toString();
	}
	
	public static String getSubOfMatch(Matcher matcher){
		StringBuilder sb = new StringBuilder(matcher.group(0));
		sb.deleteCharAt(sb.length()-3);
		return sb.toString();
	}

}
