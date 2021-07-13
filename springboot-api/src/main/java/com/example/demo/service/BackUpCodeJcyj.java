//package com.example.demo.service;
//
//import org.joda.time.LocalDate;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
///**

// * Created by fb on 2021/3/18
// */
//public class BackUpCodeJcyj {

//
//
//        /**
//         * @Author: fb on 2021/3/16
//         * 统计每一个月的总登录数据量
//         * @param [rqList, beginDate, endDate, name]
//         * @return java.util.List<ts.usercore.vo.UserLoginLogVo>
//         */
//        public List<UserLoginLogVo> findMonthCounts(List<String> rqList,String beginDate, String endDate,String name){
//                //获得最后一个月的最后一天的日期
//                String lastDayMonth=new LocalDate(endDate).dayOfMonth().withMaximumValue().toString();
//                //根据日期区间拿出每天的登录人数
//                List<RqAndDataVo> dataList=tsOnlineUserLoginLogMapperCustom
//                        .findRqAndDataList(beginDate,lastDayMonth);
//                //组装返回对象
//                List<UserLoginLogVo> returenList=new ArrayList<>();
//                //统计 计算每个月的统计数据
//                for (String firstDay:rqList){
//                        UserLoginLogVo userLoginLogVo=new UserLoginLogVo();
//                        userLoginLogVo.setRq(firstDay.substring(0,7));
//                        userLoginLogVo.setData(RQ_DATA_NULL);
//                        userLoginLogVo.setCodeName(name);
//                        //获得一个月最后一天的日期
//                        String lastDay=new LocalDate(firstDay).dayOfMonth().withMaximumValue().toString();
//                        //周统计总数==每日的和
//                        int count=0;
//                        //找到周一到周日所有日数据的统计 求和
//                        for (RqAndDataVo vo:dataList){
//                                String rq=vo.getRq();
//                                //在本周日期范围内的日数据进行求和
//                                if(rq.compareTo(firstDay)>=0&&rq.compareTo(lastDay)<=0){
//                                        count+=vo.getCounts();
//                                        //统计过一次的数据不再反复循环统计--移除
//                                        //dataList.remove(vo);
//                                }
//                        }
//                        userLoginLogVo.setData(count);
//                        returenList.add(userLoginLogVo);
//                }
//                return returenList;
//        }
//
//
//
//        /**
//         * @Author: fb on 2021/3/16
//         * 统计每一周的总登录数据量
//         * @param [rqList, beginDate, endDate, name]
//         * @return java.util.List<ts.usercore.vo.UserLoginLogVo>
//         */
//        public List<UserLoginLogVo> findWeekCounts(List<String> rqList,String beginDate, String endDate,String name){
//                //周一的数据是一周的第一天
//                String firstMonday=new LocalDate(beginDate).withDayOfWeek(1).toString();
//                //根据日期区间拿出每天的登录人数
//                List<RqAndDataVo> dataList=tsOnlineUserLoginLogMapperCustom
//                        .findRqAndDataList(beginDate,endDate);
//                //组装返回对象
//                List<UserLoginLogVo> returenList=new ArrayList<>();
//                //统计 计算每周的统计数据
//                for (String sunDay:rqList){
//                        UserLoginLogVo userLoginLogVo=new UserLoginLogVo();
//                        userLoginLogVo.setRq(sunDay);
//                        userLoginLogVo.setData(RQ_DATA_NULL);
//                        userLoginLogVo.setCodeName(name);
//                        //获得周一的日期
//                        String monDay=new LocalDate(sunDay).withDayOfWeek(1).toString();
//                        //周统计总数==每日的和
//                        int count=0;
//                        //找到周一到周日所有日数据的统计 求和
//                        for (RqAndDataVo vo:dataList){
//                                String rq=vo.getRq();
//                                //在本周日期范围内的日数据进行求和
//                                if(rq.compareTo(monDay)>=0&&rq.compareTo(sunDay)<=0){
//                                        count+=vo.getCounts();
//                                        //统计过一次的数据不再反复循环统计--移除
//                                        //dataList.remove(vo);
//                                }
//                        }
//                        userLoginLogVo.setData(count);
//                        returenList.add(userLoginLogVo);
//                }
//                return returenList;
//        }
//
//        /**
//         * @Author: fb on 2021/3/16
//         * 监测预警周登录用户数 分页列表
//         * @param [beginIndex, endIndex, beginDate, endDate]
//         * @return ts.index.util.ListAndTotal
//         */
//        public ListAndTotal findWeekCountPage(int beginIndex,
//                                              int endIndex,
//                                              String beginDate,
//                                              String endDate){
//                //分页总数
//                int count=0;
//                //根据开始和结束时间获得 日期范围内的所有周的周日的日期的集合  日期正序排序
//                List<String> rqList=DateUtil.getWeekList( beginDate,endDate);
//                //总条数
//                count=rqList.size();
//                //向前多取一周的时间（取到上个周日） 来计算涨跌
//                LocalDate localBd=new LocalDate(beginDate);
//                String preBeginDate=localBd.minusWeeks(1).toString();
//                //放到list的第一个
//                rqList.add(0,preBeginDate);
//                //正序变倒序
//                Collections.sort(rqList, new Comparator<String>() {
//                        @Override
//                        public int compare(String o1, String o2) {
//                                //升序排序，降序反写
//                                if(o2.compareTo(o1)==0){
//                                        return -1;
//                                }else{
//                                        return o2.compareTo(o1);
//                                }
//                        }
//                });
//                endIndex=endIndex>=count?count:endIndex;
//                //分页之后的 日期list
//                List<String> rqPageList= rqList.subList(beginIndex,endIndex+1);
//                //从数据库中拿出往前偏移了一周的 时间段的数据统计集合
//                //组装返回对象
//                List<UserLoginLogVo> returenList=new ArrayList<>();
//                //获得分页之后的每一周的统计数据
//                returenList=this.findWeekCounts(rqPageList,
//                        rqPageList.get(rqPageList.size()-1),rqPageList.get(0),CODE_NAME_WEEK);
//                //计算涨跌
//                returenList= this.setChgAndRange(returenList);
//                //移除偏移一周数据
//                returenList.remove(returenList.size()-1);
//                //封装返回对象
//                ListAndTotal listAndTotal=new ListAndTotal();
//                listAndTotal.setCount(count);
//                listAndTotal.setList(returenList);
//                return listAndTotal;
//
//        }
//
//
//
//
//
//        /**
//         * @Author: fb on 2021/3/16
//         * 监测预警周登录用户数 折线图
//         * @param [beginDate, endDate]
//         * @return java.util.List<ts.usercore.vo.RqAndDataVo>
//         */
//        public List<UserLoginLogVo> findWeekCountList(String beginDate, String endDate){
//                //根据开始和结束时间获得 日期范围内的所有日期的集合  日期正序排序
//                List<String> rqList=DateUtil.getWeekList(beginDate,endDate);
//                List<UserLoginLogVo> returenList=new ArrayList<>();
//                //根据日期返回获得统计数据
//                returenList=this.findWeekCounts(rqList,beginDate,endDate,CODE_NAME_WEEK);
//                return returenList;
//        };
//
//
//
//        /**
//         * @Author: fb on 2021/3/16
//         * 监测预警月登录用户数 分页列表
//         * @param [beginIndex, endIndex, beginDate, endDate]
//         * @return ts.index.util.ListAndTotal
//         */
//        public ListAndTotal findMonthCountPage(int beginIndex,
//                                               int endIndex,
//                                               String beginDate,
//                                               String endDate){
//                //分页总数
//                int count=0;
//                //根据开始和结束时间获得 日期范围内的所有周的周日的日期的集合  日期正序排序
//                List<String> rqList=DateUtil.getMonthList(beginDate,endDate);
//                //总条数
//                count=rqList.size();
//                //向前多取一个月的时间（取到上个月的第一天） 来计算涨跌
//                LocalDate localBd=new LocalDate(beginDate);
//                String preBeginDate=localBd.minusMonths(1).toString();
//                //放到list的第一个
//                rqList.add(0,preBeginDate);
//                //正序变倒序
//                Collections.sort(rqList, new Comparator<String>() {
//                        @Override
//                        public int compare(String o1, String o2) {
//                                //升序排序，降序反写
//                                if(o2.compareTo(o1)==0){
//                                        return -1;
//                                }else{
//                                        return o2.compareTo(o1);
//                                }
//                        }
//                });
//                endIndex=endIndex>=count?count:endIndex;
//                //分页之后的 日期list
//                List<String> rqPageList= rqList.subList(beginIndex,endIndex+1);
//                //从数据库中拿出往前偏移了一周的 时间段的数据统计集合
//                //组装返回对象
//                List<UserLoginLogVo> returenList=new ArrayList<>();
//                //获得分页之后的每一周的统计数据
//                returenList=this.findMonthCounts(rqPageList,
//                        rqPageList.get(rqPageList.size()-1),rqPageList.get(0),CODE_NAME_MONTH);
//                //计算涨跌
//                returenList= this.setChgAndRange(returenList);
//                //移除偏移一周数据
//                returenList.remove(returenList.size()-1);
//                //封装返回对象
//                ListAndTotal listAndTotal=new ListAndTotal();
//                listAndTotal.setCount(count);
//                listAndTotal.setList(returenList);
//                return listAndTotal;
//        }
//
//        /**
//     * @Author: fb on 2021/3/16
//     * 监测预警周登录用户数 折线图
//     * @param [beginDate, endDate]
//     * @return java.util.List<ts.usercore.vo.RqAndDataVo>
//     */
//        public List<UserLoginLogVo> findMonthCountList(String beginDate, String endDate){
//                //根据开始和结束时间获得 日期范围内的所有日期的集合  日期正序排序
//                List<String> rqList=DateUtil.getMonthList(beginDate,endDate);
//                List<UserLoginLogVo> returenList=new ArrayList<>();
//                //根据日期返回获得统计数据
//                returenList=this.findMonthCounts(rqList,beginDate,endDate,CODE_NAME_MONTH);
//                return returenList;
//        }
//
//}
