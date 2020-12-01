package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fb on 2020/11/23
 */
public class DateUtils {

        public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public static String formatDateTime(Date date){
                return sdf.format(date);
        }
}
