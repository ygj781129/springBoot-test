package com.example.demo.service;

import com.example.demo.mapper.HMedicalExamineMapper;
import com.example.demo.pojo.KeyValVo;
import com.example.demo.util.LunarSolarConverter;
import com.example.demo.vo.SortKeyValVo;
import com.google.common.collect.Lists;

import org.joda.time.*;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public static void maingg(String[] args) {
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


    public static void main999 (String[] args) {
        String date="2019-12-30";
        Map<String,Integer> result =  new HashMap<String,Integer>();
        Calendar cal = Calendar.getInstance();

        //设置一周的开始,默认是周日,这里设置成星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
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

    }

    public static void main(String[] args) {
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

    static int REARCH_DATE_WEEK=1;
    public static void main422244(String[] args) {
        int year=2020;
        int week=1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR,year);
        cal1.set(Calendar.DAY_OF_YEAR,1);
        if (cal1.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
            week=week+1;
        }
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.WEEK_OF_YEAR, week);


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
System.out.println(sdf.format(cal.getTime()));

    }
}
