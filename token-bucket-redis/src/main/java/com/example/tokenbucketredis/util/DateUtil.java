package com.example.tokenbucketredis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fb on 2020/10/21
 */
public  class  DateUtil {
        public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        public  static  String dateToString(Date date){
                String str=sdf.format(date);
                return str;
        }
}
