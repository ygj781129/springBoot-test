package com.example.demo.service;

import com.example.demo.mapper.HMedicalExamineMapper;
import com.example.demo.pojo.KeyValVo;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.UserInfo;
import com.example.demo.util.LunarSolarConverter;
import com.example.demo.vo.SortKeyValVo;
import com.example.demo.vo.UserLoginLogVo;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.google.common.collect.Lists;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.time.LocalDateTime;


/**
 * Created by Administrator on 2019/10/11.
 */
public class Java8Test {
    @Resource
    private HMedicalExamineMapper medicalExamineMapper;
    public static void main1(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        for(Integer n: list) {
            System.out.println(n);
        }
    //New way:
        list.forEach(n -> System.out.println(n));

    //or we can use :: double colon operator in Java 8
        list.forEach(System.out::println);

    }
    public static void main3(String [] a)  {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        System.out.println("Print all numbers:");
        evaluate(list, (n)->true);

        System.out.println("Print no numbers:");
        evaluate(list, (n)->false);

        System.out.println("Print even numbers:");
        evaluate(list, (n)-> n%2 == 0 );

        System.out.println("Print odd numbers:");
        evaluate(list, (n)-> n%2 == 1 );

        System.out.println("Print numbers greater than 5:");
        evaluate(list, (n)-> n > 5 );

    }

    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list)  {
            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }


    public static void main5(String[] args) {
/**
 * 下面这一段代码的意思是
 * 有一个集合list
 * list.stream().filter()这块是过滤这个集合中的内容
 * 过滤什么内容呢 n->n.contains('m')每个元素中含有m的 符合条件
 * 然后把这些元素整理成一个list集合
 */
        List<String> names = Lists.newArrayList("Larry", "Jeremy", "Sam", "Simon", "Mike");
        List<String> result = names.stream()
                .filter(n -> n.contains("m"))
                .collect(Collectors.toList());
        result.forEach(n->System.out.println(n));//打印每个元素

    }

    public static void main(String[] args) {
        Random random = new Random();
        random.ints().limit(10);//.forEach(System.out::println);

        List<Integer> numbers2 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers2.stream().distinct().map( i -> i*i).collect(Collectors.toList());
        squaresList.forEach(System.out::println);

    /**
     * 统计功能
     */
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        DoubleSummaryStatistics stats = numbers.stream().mapToDouble((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

    public static void mainQQ(String[] args) {
        //并行流 （实质意义就一个多线程）
        //使用了多线程 可以大大提高代码的效率 但是会造成很多不可预知的问题
        //比如一下代码 使用多线程之后 控制台输出的数字是没有顺序的
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEach(System.out::println);
    }
    //排序
    //https://blog.csdn.net/lsgqjh/article/details/63686383
    public static void mainff(String[] args) {
        List<Integer> list = Arrays.asList(6, 2, 3, 5, 4,1, 7, 8, 9);
        List<Integer> newList= Arrays.asList();
        //自然升序按照升序的方式排序
        //newList=list.stream().sorted().collect(Collectors.toList());
        //自然排序降序的方式

        newList=list.stream().sorted(Comparator.reverseOrder()).limit(10).collect(Collectors.toList());
        newList.forEach(System.out::println);
    }

    public static void main333(String[] args) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1=sdf.parse("2019-02-02");
        Date date2=sdf.parse("2019-02-03");
        System.out.println(date1.before(date2));

        String beginTime=new String("2019-06-09");
        String endTime=new String("2018-05-08");
        Integer i=beginTime.compareTo(endTime);
        System.out.println(i);
    }


    public static void main444(String[] args) {
        String source="123456";
        String s = null;
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f' };
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            md.update(source.getBytes());
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>>
                // 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
      System.out.println(s);

    }


    public static void mainee(String[] args) {
//        String s="0.31%";
//        String str=s.substring(0,s.length()-1);
//        System.out.println(str);


        String ss="5646546465-fdsghdfhd4fh564";
        int tt=ss.indexOf("-");
        String t=ss.substring(0,ss.indexOf("-"));
        System.out.println(t);
    }

    public static void maineee(String[] args)throws Exception {

        String beginDate="2014-06-01";
        String endDate="2017-10-31";
        //开始时间当年的第一天
        String sd=new DateTime(beginDate).dayOfYear().withMinimumValue().toString("yyyy-MM-dd");
        //结束时间当年的最后一天
        String ed=new DateTime(endDate).dayOfYear().withMaximumValue().toString("yyyy-MM-dd");
        //开始时间往前推一年
        String strBegin = new LocalDate(sd).minusYears(1).toString();
        //结束时间往后推一年
        String strEnd = new LocalDate(ed).plusYears(1).toString();
        //开始时间的年份
        String byear=strBegin.substring(0,4);
        //结束时间的年份
        String eyear=strEnd.substring(0,4);

        /**
         * 当年的最后一天往后推三个月
         * 多取点不知道哪天过年
         */
        LocalDate localDateStart = new LocalDate(sd);//minusMonths(1);
        LocalDate localDateEnd = new LocalDate(ed).plusMonths(3);
        //取出日期范围内的所有日期的集合
        int days = Days.daysBetween(localDateStart, localDateEnd).getDays();
        List<String> listDates = Lists.newArrayList();
        for(int i = 1; i <= days+1; i++){
            LocalDate localDate = localDateStart.plusDays(i - 1);
            listDates.add(localDate.toString());
        }
        List<String> LanurList=Lists.newArrayList();
        //农历的转换
        LanurList=listDates.stream().map(n->{
            DateTime dateTime = new DateTime(n, DateTimeZone.UTC);
            LunarSolarConverter.Lunar lunar = LunarSolarConverter.converterDate(dateTime.getMillis());
            String str=lunar.year+lunar.getMonth()+"月"+lunar.getDay();


            return  str;
        }).collect(Collectors.toList());
        /**
         * 往后多取的日期给去掉
         * 农历前的年份去掉
         */
        Set<String> set =new HashSet<>();
        set=LanurList.stream()
                .filter(n->n.indexOf(byear)==-1&&n.indexOf(eyear)==-1)
                .map(n->{
//                    SortKeyValVo sort=new SortKeyValVo();
//                    String ss=n.getKey().substring(5);
//                    Integer o=Integer.valueOf(ss.replaceAll("-",""));
//                    String ss=n.getKey().substring(5);
//                    n.setKey(ss.replaceAll("-",""));

                    return n.substring(4);
                }).collect(Collectors.toSet());


        Map<Character,String> map=getSort();
        List<SortKeyValVo> returnList=Lists.newArrayList();
        returnList=set.stream().map(n->{
            String arryStr=n;
           if(arryStr.indexOf("廿十")!=-1||arryStr.indexOf("卅十")!=-1){
               StringBuilder sb = new StringBuilder(arryStr);
               arryStr=sb.replace(arryStr.length()-1, arryStr.length(), "初").toString();
           }
           if(arryStr.indexOf("初十")!=-1){
               arryStr=arryStr.replace(arryStr.charAt(arryStr.length()-2)+"","");
           }
            if('十'==(arryStr.charAt(arryStr.length()-2))){
                StringBuilder sb = new StringBuilder(arryStr);
                arryStr=sb.replace(arryStr.length()-2, arryStr.length()-1, "一").toString();
            }
            char[] arry=arryStr.toCharArray();
            String sortStr="";
            for (char c:arry){
                sortStr+=map.get(c);
            }
            Double dd= Double.parseDouble(sortStr);
            if(n.indexOf("闰")!=-1){
                dd+=30;
            }
            SortKeyValVo sort=new SortKeyValVo();
            sort.setKey(dd);
            sort.setVal(n);
            return sort;
        }).collect(Collectors.toList());
        returnList=returnList.stream().sorted(Comparator.comparing(SortKeyValVo::getKey)).collect(Collectors.toList());
        for (SortKeyValVo v:returnList){
            System.out.println(v.getKey()+"----"+v.getVal());
        }
     }



    public static void main666(String[] args) {
        String beginDate="四月廿十";
        char[] byear=beginDate.toCharArray();
        for (char c:byear){
            System.out.println(c);
        }
        //return ;
    }

    public static  Map<Character,String> getSort(){
        return new HashMap<Character, String>() {
            {
                put('正',"1");
                put('一',"1");
                put('二',"2");
                put('三',"3");
                put('四',"4");
                put('五',"5");
                put('六',"6");
                put('七',"7");
                put('八',"8");
                put('九',"9");
                put('十',"10");
                put('冬',"11");
                put('腊',"12");
                put('初',"0");
                put('廿',"2");
                put('卅',"3");
                put('月',"");
                put('闰',"");
            }};


    }


    public static void main33333  (String[] args) {
        System.out.println(getReacherDateByYearAndWeek(2020,1,"yyyy-MM-dd"));
        String date="2020-12-31";
        Map<String,Integer> result =  new HashMap<String,Integer>();
        Calendar cal = Calendar.getInstance();

        //设置一周的开始,默认是周日,这里设置成星期一
        //cal.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatMon = new SimpleDateFormat("MM");
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal.setTime(d);
        int month = Integer.valueOf(formatMon.format(d));
        int year = Integer.valueOf(formatYear.format(d));

        int week = cal.get(Calendar.WEEK_OF_YEAR);
        result.put("week", week);
        if(week == 1 && month == 12){
            result.put("year", year + 1);
        }else{
            result.put("year", year);
        }
        System.out.println(result.get("year")+"---"+result.get("week"));
        //
    }

    public static String getFirdayByYearAndWeek(int year,int week,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR,year);
        cal1.set(Calendar.DAY_OF_YEAR,1);
        if (cal1.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY){
            week=week+1;
        }
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);// 周五

        return sdf.format(cal.getTime());
    }


    public static void main88(String[] args) {
        LocalDate startDate = new LocalDate("2019-12-08");
        LocalDate endDate = new LocalDate("2020-01-19");

        LocalDate thisMonday = startDate.withDayOfWeek(DateTimeConstants.WEDNESDAY);

        if (startDate.isAfter(thisMonday)) {
            startDate = thisMonday.plusWeeks(1); // start on next monday
        } else {
            startDate = thisMonday; // start on this monday
        }

        while (startDate.isBefore(endDate)) {
            System.out.println(startDate);
            startDate = startDate.plusWeeks(1);
        }
    }




    public static void main88888(String[] args) throws Exception{
//        int string=new DateTime().withYear(2016).weekOfWeekyear().getMaximumValue();
//        int string1=new DateTime().withWeekyear(2016).weekOfWeekyear().getMaximumValue();
//        System.out.println(string);
//        System.out.println(string1);
//        System.out.println(getReacherDateByYearAndWeek(2017,1,"yyyy-MM-dd"));



        String today = "2016-12-31";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(today);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);

        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String s1="2016-12-20";
//        String s2="2017-01-08";
//        Date date1=format.parse(s1);
//        Date date2=format.parse(s2);
//        List<String[]> weekList =getWeekList(date1,date2);
//        for (String[] strings : weekList) {
//            for (String string : strings) {
//                System.out.println(string);
//            }
//        }

    }

    public static List<String[]> getWeekList(Date startDate,Date endDate){
        List<String[]> weekList = new ArrayList<>();
        //转换成joda-time的对象
        DateTime firstDay = new DateTime(startDate).dayOfWeek().withMinimumValue();
        DateTime lastDay = new DateTime(endDate).dayOfWeek().withMaximumValue();
        //计算两日期间的区间天数
        Period p = new Period(firstDay, lastDay, PeriodType.days());
        int days = p.getDays();
        if (days > 0){
            int weekLength = 7;
            for(int i=0;i<days;i=i+weekLength){
                String monDay = firstDay.plusDays(i).toString("yyyy-MM-dd");
                String sunDay = firstDay.plusDays(i+6).toString("yyyy-MM-dd");
                String [] week = {monDay,sunDay};
                weekList.add(week);
            }
        }
        return weekList;
    }


    public static String getReacherDateByYearAndWeek(int year,int week,String format){

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR,year);
        cal1.set(Calendar.DAY_OF_YEAR,1);
        if (cal1.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
            week=week+1;
        }
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.WEEK_OF_YEAR, week);

        int REARCH_DATE_WEEK=3;
        //获取周一
        if(REARCH_DATE_WEEK==1){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }
        //周二
        if(REARCH_DATE_WEEK==2){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        }
        //周三
        if(REARCH_DATE_WEEK==3){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        }
        //周四
        if(REARCH_DATE_WEEK==4){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        }
        //周五
        if(REARCH_DATE_WEEK==5){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        }
        //周六
        if(REARCH_DATE_WEEK==6){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        }
        //周日
        if(REARCH_DATE_WEEK==7){
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }

        return sdf.format(cal.getTime());

    }

    public static void main55(String[] args) {
        System.out.println(getReacherDateByYearAndWeek(2021,1,"yyyy-MM-dd"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar instanceBegin = Calendar.getInstance();
        instanceBegin.set(Calendar.YEAR,2019);
        instanceBegin.set(Calendar.WEEK_OF_YEAR,50);
        instanceBegin.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
         Date beginTime = instanceBegin.getTime();
        Calendar instanceEnd = Calendar.getInstance();
        instanceEnd.set(Calendar.YEAR,2021);
        instanceEnd.set(Calendar.WEEK_OF_YEAR,2);
        instanceEnd.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
        Date endTime = instanceEnd.getTime();
        while(beginTime.before(endTime)||beginTime.equals(endTime)){
            Calendar instance = Calendar.getInstance();
            instance.setTime(beginTime);
            int month = instance.get(Calendar.MONTH)+1;
            int year = instance.get(Calendar.YEAR);
            int week = instance.get(Calendar.WEEK_OF_YEAR);
                Calendar yyy = Calendar.getInstance();
                yyy.setTime(beginTime);
                yyy.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
                int satYear=yyy.get(Calendar.YEAR);
                //System.out.println("周六"+sdf.format(yyy.getTime()));
          if(satYear!=year){
              Calendar qq = Calendar.getInstance();
              qq.setTime(beginTime);
              qq.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
              qq.add(Calendar.WEEK_OF_YEAR,-1);
              week = qq.get(Calendar.WEEK_OF_YEAR)+1;
          }

            System.out.println(year+"--------"+week);

            System.out.println("周三"+sdf.format(instance.getTime()));
        instanceBegin.add(Calendar.WEEK_OF_YEAR,1);
        beginTime = instanceBegin.getTime();
    }

}

    public static void main555(String[] args) {
        //DateTime dtFr = new DateTime(2012,1,11,DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/Paris")));
        //DateTime dtUS = new DateTime(2012,DateTimeZone.forTimeZone(TimeZone.getTimeZone("US/Arizona")));
        DateTime dtUS=new DateTime("2020-12-31");
        System.out.println(dtUS.weekyear().get());
        System.out.println( dtUS.getWeekOfWeekyear());
        System.out.println(getReacherDateByYearAndWeek(2021,1,"yyyy-MM-dd"));
        System.out.println(dtUS.withWeekOfWeekyear(52));
        List<String[]> weekList = new ArrayList<>();
        //转换成joda-time的对象
        DateTime firstDay = new DateTime("2020-12-30").dayOfWeek().withMinimumValue();
        DateTime lastDay = new DateTime("2021-01-10").dayOfWeek().withMaximumValue();

        //计算两日期间的区间天数
        Period p = new Period(firstDay, lastDay, PeriodType.days());
        int days = p.getDays();
        if (days > 0) {
            int weekLength = 7;
            for (int i = 0; i < days; i = i + weekLength) {
                String wensday = firstDay.plusDays(i+2).toString("yyyy-MM-dd");
                //String sunDay = firstDay.plusDays(i + 6).toString("yyyy-MM-dd");
//                String[] week = {monDay, sunDay};
//                weekList.add(week);
                //System.out.println(wensday);
            }
        }
        }

    /**
     * 传入一个年份 例如2020
     * 获得这一年中所有的周三日期集合
     * @param args
     */
    public static void main1111555(String[] args) {
        String year="2021";
        String sdate="-01-01";
        String edate="-12-31";
        sdate=year+sdate;//指定年的第一天
        edate=year+edate;//指定年的最后一天
        LocalDate lastDay = new LocalDate(edate);
        //指定年第一天所在周的周三---所在周的周三
        LocalDate firstWednesDay =new LocalDate(sdate).withDayOfWeek(3);
        String fwdStr=firstWednesDay.toString();
        System.out.println(fwdStr);
        //指定年第一天所在周的周三所在的年
        String firstWednesDayYear=firstWednesDay.getYear()+"";
        if(!firstWednesDayYear.equals(year)){
            //如果指定年第一天所在周的周三所在的年 与 输入的年 不同（说明第一天的周三在上一年）
            //那么实际上这一年第一个周三在下一周
            firstWednesDay=firstWednesDay.plusDays(7);
        }
        System.out.println(firstWednesDayYear);
        System.out.println(firstWednesDay.toString("yyyy-MM-dd"));
        //本年第一个周三和本年最后一天中间有多少天
        Period p = new Period(firstWednesDay, lastDay, PeriodType.days());
        int days = p.getDays();
        if (days > 0) {
            //一周七天
            int weekLength = 7;
            //第几个周三
            int index=1;
            for (int i = 0; i < days; i = i + weekLength) {
                String wensday = firstWednesDay.plusDays(i).toString("yyyy-MM-dd");
//                weekList.add(week);
                System.out.println("第"+index+"----"+wensday);
                index+=1;
            }
        }


    }


    public static void main1111(String[] args) {
//        String jsonString = "{\"treeName\":\"666666\",\"parentId\":\"2020103001104044621542\"}";
//        if (jsonString != null) {
//
//        }
//        if (jsonString == null) {
//
//        }
//        if (1 == 1) {
//        }
//
//
//        for (int i = 0; i <5 ; i++) {
//
//        }
        LocalDate sdate=new LocalDate("2020-12-29");
        LocalDate edate=new LocalDate("2021-01-15");
        //System.out.println(Days.daysBetween(sdate, edate).getDays() + " 天 ");
        int no=Days.daysBetween(sdate, edate).getDays();
        LocalDate date=null;
        List<String> list=Lists.newArrayList();
        for (int i = 0; i <=no ; i++) {
            date=sdate.plusDays(i);
           // System.out.println(date.toString());
            list.add(date.toString());
//            if(date.isEqual(edate)){
//                break;
//            }
        }
        list.forEach(System.out::println);


        // \u000d System.out.println("wmyskxz is awesome!");
//        int n=3;
//        n=n << 2;
//        System.out.println(n);
//        Set<String> set = new HashSet<String>() {{
//            add("wmyskxz");
//            add("is");
//            add("awesome");
//            add("!");
//        } };
//        System.out.println("----" + date.weekyear().get());
//        System.out.println("----" + date.getWeekOfWeekyear());

//        System.out.println(
//                String.format( "min:%s, max:%s",DateTime.now().dayOfYear().withMinimumValue().toString("yyyy-MM-dd")
//                        ,DateTime.now().dayOfYear().withMaximumValue().toString("yyyy-MM-dd")));
//    }
    }

    public static void main8886688(String[] a){


        //获取当前时间
        LocalDateTime currentDate = LocalDateTime.now();
        //获取年份
        int year = currentDate.getYear();
        System.out.println("获取当前年份:" + year);
        //获取月份
        int month = currentDate.getMonthValue();
        System.out.println("获取当前月份:" + month);
        //获取当前周
        int week = currentDate.getDayOfWeek().getValue();
        System.out.println("获取当前周:" + week);
        //获取当前时间第X周
        /*
        public static WeekFields of​(DayOfWeek firstDayOfWeek, int minimalDaysInFirstWeek)
        从第一天和最小日期获得WeekFields的实例。
        第一天的每周定义ISO DayOfWeek ，即一周中的第一天。 第一周的最小天数定义一个月或一年中必须存在的天数，从第一天开始，在将一周计算为第一周之前。 值1将计算作为第一周的一部分的月或年的第一天，而值7将要求整个七天在新的月或年中。

        WeekFields实例是单例; 对于firstDayOfWeek和minimalDaysInFirstWeek的每个唯一组合，将返回相同的实例。

        参数
        firstDayOfWeek - 一周的第一天，不是null
        minimalDaysInFirstWeek - 第一周的最小天数，从1到7
         */
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
        int weeks = currentDate.get(weekFields.weekOfYear());
        System.out.println("获取当前时间第" + weeks + "周");

    }


    public static void main852(String[] args) {
        Cache<String, UserInfo> cache = Caffeine.newBuilder()
                // 数量上限
                .maximumSize(1024)
                // 过期机制
                .expireAfterWrite(5, TimeUnit.MINUTES)
                // 弱引用key
                .weakKeys()
                // 弱引用value
                .weakValues()
                // 剔除监听
                .removalListener((RemovalListener<String, UserInfo>) (key, value, cause) ->
                        System.out.println("key:" + key + ", value:" + value + ", 删除原因:" + cause.toString()))
                .build();
        // 将数据放入本地缓存中
        //cache.put("username", "afei");
        //cache.put("password", "123456");
        UserInfo userInfo=new UserInfo();
        userInfo.setAge(33);
        userInfo.setName("小明");
        userInfo.setId(3);
        cache.put("222", userInfo);


        UserInfo a=cache.getIfPresent("username");
// 从本地缓存中取出数据
        System.out.println(cache.getIfPresent("username"));
        System.out.println(cache.getIfPresent("password"));
       // System.out.println(cache.get("222"));
//        System.out.println(cache.get("blog", key -> {
//            // 本地缓存没有的话，从数据库或者Redis中获取
//            return getValue(key);
//        }));

    }

    public static void main9999(String[] args)throws Exception {

        //基于名字空间的UUID（MD5）
        System.out.println(UUID.nameUUIDFromBytes("myString".getBytes("UTF-8")).toString());
        //基于随机数的UUID
        System.out.println(UUID.randomUUID().toString());
    }

    public static void maineeee(String[] args) {
//
//        String beginRq="2020-05";
//        String endRq="2021-09";
//        String dayStr="-01";
//        LocalDate sdate = new LocalDate(beginRq+dayStr);
//        LocalDate edate = new LocalDate(endRq+dayStr);
//        List<String> list = new ArrayList<>();
//        while (sdate.isBefore(edate)) {
//            sdate= sdate.plusMonths(1);//向后推一个月
//           System.out.println(sdate);
//        }


//        //数据准备
//        List<Student> list = new ArrayList<>();
//        list.add(new Student("2020-01-01",1));
//        list.add(new Student("2020-08-08",4));
//        list.add(new Student("2021-05-01",3));
//        list.add(new Student("2020-11-05",5));
//        list.add(new Student("2021-02-02",2));


        List<UserLoginLogVo> list = new ArrayList<>();
        list.add(new UserLoginLogVo("2020-01-01","1","1","1","1"));
        list.add(new UserLoginLogVo("2020-08-08","1","1","1","1"));
        list.add(new UserLoginLogVo("2021-05-01","1","1","1","1"));
        list.add(new UserLoginLogVo("2020-01-01","1","1","1","1"));
        list.add(new UserLoginLogVo("2021-02-02","1","1","1","1"));
//        int nn=d1.compareTo(d2);
//        System.out.println(nn);
//        //使用Collections集合工具类进行排序
        Collections.sort(list, new Comparator<UserLoginLogVo>() {
            @Override
            public int compare(UserLoginLogVo o1, UserLoginLogVo o2) {
                //升序排序，降序反写
               if(o2.getCodeName().compareTo(o1.getCodeName())==0){
                   return -1;
               }else{
                    return o2.getCodeName().compareTo(o1.getCodeName());
                }
            }
        });

        for (UserLoginLogVo student : list) {
            System.out.println(student.getCodeName());
        }

        /**
         * 把要返回的东西封装个对象 然后给对象list排序
         * https://blog.csdn.net/superdog007/article/details/54133698
         *select sum( t.user_id) over(partition by t.user_id) from ts_online_user_login_log t
         * where to_char(t.login_date,'yyyy-MM')='2020-01'
         *
         * 需不需要排序 需要排序加参数 正序倒序
         */
    }

    public static void main99997u77(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
//
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                //升序排序，降序反写
//                if(o2.compareTo(o1)==0){
//                    return -1;
//                }else{
//                    return o2.compareTo(o1);
//                }
//            }
//        });
//        for (String s : list) {
//            System.out.println(s);
//        }

//
//        int pageNum=2;
//        int pageSize=3;
//        int beginIndex=(pageNum-1)*pageSize;
//        int endIndex=pageNum*pageSize;
//
//        list=list.subList(pageNum,pageSize);
//        list.add(0,"55");
//        for (String s : list) {
//            System.out.println(s);
//        }
        String end="2021-03-14";
        String d1="2021-11-14";
        String begin="2021-01-14";
//        System.out.println(d1.compareTo(d2));
//        System.out.println(d1.compareTo(d3));

        if(d1.compareTo(begin)>=0&&d1.compareTo(end)<=0){
            System.out.println("555555!!!!!");
        }

        LocalDate date=new LocalDate("2021-03-16");
        String monday = date.dayOfMonth().withMaximumValue().toString();
        System.out.println(monday);

        String dd="2020-01-01";
        System.out.println(dd.substring(0,7));
    }

    public static void main333333(String[] args) {
        Integer pre=10;
        Integer now=11;
        double pred=pre.doubleValue();
        double nowd=now.doubleValue();
        Double data=pred==0.0?null:(((nowd - pred) / Math.abs(pre)) * 100);
        System.out.println(pred);
        System.out.println(nowd);
        System.out.println(data);
    }
}
