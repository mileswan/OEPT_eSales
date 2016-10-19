package com.oept.esales.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: mwan
 * Version: 1.1
 * Date: 2015/09/03
 * Description: Methods used to manipulate string.
 * Copyright (c) 2015 mwan. All rights reserved.
 */
public class StringUtils {
	
	public static String replace(final String sourceString,Object[] object) {
        
		String temp = sourceString;    
        for(int i=0;i<object.length;i++){
           String[] result = (String[])object[i];
           Pattern pattern = Pattern.compile(result[0]);
           Matcher matcher = pattern.matcher(temp);
           temp = matcher.replaceAll(result[1]);
        }
        return temp;
	}
	
	// convert integer to xx:xx:xx
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
    
 // convert xx:xx:xx to integer
    public static int timeToSec(String time) {
        int sec = 0;
        String times[] = time.split(":");
        
        sec = Integer.parseInt(times[0])*3600 + Integer.parseInt(times[1])*60 + Integer.parseInt(times[2]); 
        return sec;
    }

}
