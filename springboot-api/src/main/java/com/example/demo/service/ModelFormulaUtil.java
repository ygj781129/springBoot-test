package com.example.demo.service;

import com.sun.xml.internal.bind.v2.TODO;
import net.sourceforge.jeval.function.math.Abs;
import oracle.jdbc.oracore.TDSPatch;

/**
 * Created by fb on 2021/7/23
 * 模块公式方法类
 * 模块4
 */
public class ModelFormulaUtil {

        /**
         * 计算指定公司的信用评级水平及对应的风险溢价
         */
        public static void getCreditRating(){
                double riskpre=0.0;
                /**
                 * @Describe：（决策汇总）负债率(debt )
                 * 数据库中取出--公司整体信息变动表
                 * TableName：enterprise_infor_change
                 * Column：fuzhailv
                 */
                double fuzhailv=0.0;
                /**
                 * @Describe：（决策汇总）财务杠杆系数(dfl )
                 * 数据库中取出--公司整体信息变动表
                 * TableName：enterprise_infor_change
                 * Column：caiwugangganxishu
                 */
                double caiwugangganxishu =0.0;
                /**
                 * @Describe：（主要指标）流动比率(cr)
                 * 数据库中取出--流动比率表
                 * TableName：current_ratio
                 * Column：liudongduibilv
                 */
                double liudongduibilv  =0.0;
                //逻辑判断
                if(caiwugangganxishu<1){
                        if(fuzhailv<0.5){
                                if(liudongduibilv>1){
                                        riskpre = 0.12;
                                }else {
                                        riskpre = 0.15;
                                }
                        }else {
                                riskpre = 0.15;
                        }
                }else if(caiwugangganxishu>2) {
                        if (fuzhailv < 0.5) {
                                if (liudongduibilv > 1) {
                                        riskpre = 0.05;
                                } else {
                                        riskpre = 0.07;
                                }
                        } else {
                                riskpre = 0.09;
                        }
                }else {
                        if(fuzhailv<0.4){
                                riskpre = 0.02;
                        }else {
                                if(liudongduibilv>1){
                                        riskpre = 0.03;
                                }else {
                                        riskpre = 0.04;
                                }
                        }
                }
                /**
                 * @Describe：（金融交易）风险溢价
                 * 保存数据库--股票数量汇总
                 * TableName：stock_quantity_summary
                 * Column：fengxianyijia
                 */
                double fengxianyijia=riskpre;

        }

        /**
         * 计算指定公司指定期数的普通股内在价值，使用剩余收益模型和DDM模型的加权平均数
         */
        public static  void getStockValuation(){
                //使用DDM模型估计公司股票的内在价值，零增长模型
                //期数
                Integer i=12;
                /**
                 * @Describe：（决策汇总）股利支付  当期值
                 * 数据库中取出--公司整体信息变动表
                 * TableName：enterprise_infor_change
                 * Column：gulizhifu
                 */
                double gulizhifu=0.0;
                //（决策汇总）股利支付 上期值
                double preGulizhifu=0.0;
                /**
                 * @Describe：（决策汇总）净资产收益率  当期值
                 * 数据库中取出--公司整体信息变动表
                 * TableName：enterprise_infor_change
                 * Column：jingzichanshouyilv
                 */
                double jingzichanshouyilv=0.0;
                // （决策汇总）净资产收益率  上期值
                double preJingzichanshouyilv=0.0;
                /**
                 * @Describe：（金融交易）总股数) 当期值
                 * 数据库中取出--股票数量汇总
                 * TableName：stock_quantity_summary
                 * Column：zonggushu
                 */
                double zonggushu=0.0;
                //（金融交易）总股数) 上期值
                double preZonggushu=0.0;
                /**
                 * @Describe：（金融交易）风险溢价
                 * 数据库中取出--股票数量汇总
                 * TableName：stock_quantity_summary
                 * Column：fengxianyijia
                 */
                double fengxianyijia=0.0;
                /**
                 * @Describe：(环境变量长期利率）10年期
                 * 数据库中取出--长期利率表
                 * TableName：long_interest_rate
                 * Column：lilv
                 */
                double lilv=0.0;

                /**
                 * @Describe：（资产负债表）所有者权益合计
                 * 数据库中取出-- 已有该表
                 */
                double suoyouzhequanyiheji=0.0;

                //（初始设定）股票相对债券风险溢价 --公共参数
                double gupiaoxiangduizhaiquanfengxianyijia=0.02;

                //以下为公式计算
                //(（决策汇总）股利支付/ （金融交易）总股数+（决策汇总）股利支付/ （金融交易）总股数) / 2
                double perdiv=(gulizhifu/zonggushu+preGulizhifu/preZonggushu)/2;
                //计算必要报酬率
                //（环境变量长期利率）10年期+ （金融交易）（金融交易）风险溢价+（初始设定）股票相对债券风险溢价
                double neceret=lilv+fengxianyijia+gupiaoxiangduizhaiquanfengxianyijia;
                //使用剩余收益模型估计公司股票的内在价值
                //DDM模型估值等于两期平均股利除以必要报酬率
                double svd= perdiv / neceret;
                //获取之前两期净资产收益率的均值
                //(（决策汇总）净资产收益率+（决策汇总）净资产收益率) / 2
                double netret=(jingzichanshouyilv+preJingzichanshouyilv)/2;

                double disrate = 1;
                double totald = 0;
                //循环计算
                for(int j=1;j<=i;j++) {
                        totald = totald + disrate / (1 + neceret);
                        disrate = disrate / (1 + neceret);
                }

                // 剩余收益模型估值，使用一致的每股净资产
                //（资产负债表）所有者权益合计* (1 + (netret - neceret) * totald) / （金融交易）总股数
                double svs=suoyouzhequanyiheji*(1 + (netret - neceret) * totald)/zonggushu;

                /**
                 * @Describe：（金融交易）股票价值
                 * 保存数据库--股票数量汇总
                 * TableName：stock_quantity_summary
                 * Column：gupiaojiazhi
                 */
                double gupiaojiazhi=(2 * svs + svd) / 3;
        }

        /**
         * 计算指定公司指定期数普通股的市场价格
         */
        public static void getStockPricing(){
                /**
                 * @Describe：（决策汇总）股票市场价格
                 * 数据库中取出--公司整体信息变动表
                 * TableName：enterprise_infor_change
                 * Column：gupiaoshichangjiage
                 */
                 double gupiaoshichangjiage=0.0;

                /**
                 * @Describe：（决策汇总）股利支付 当期值
                 * 数据库中取出--公司整体信息变动表
                 * TableName：enterprise_infor_change
                 * Column：gupiaoshichangjiage
                 */
                double gulizhifu=0.0;
                // （决策汇总）股利支付 上期值
                double preGulizhifu=0.0;
                //（决策汇总）股利支付 前二期
                double preTwoGulizhifu=0.0;
                /**
                 * @Describe：（决策汇总）净资产收益率  当期值
                 * 数据库中取出--公司整体信息变动表
                 * TableName：enterprise_infor_change
                 * Column：jingzichanshouyilv
                 */
                double jingzichanshouyilv=0.0;
                // （决策汇总）净资产收益率  上期值
                double preJingzichanshouyilv=0.0;
                // （决策汇总）净资产收益率  前二期
                double preTwoJingzichanshouyilv=0.0;
                /**
                 * @Describe：（金融交易）股票价值
                 * 数据库中取出--股票数量汇总
                 * TableName：stock_quantity_summary
                 * Column：gupiaojiazhi
                 */
                double gupiaojiazhi=0.0;

                /**
                 * @Describe：（金融交易）风险溢价
                 * 数据库中取出--股票数量汇总
                 * TableName：stock_quantity_summary
                 * Column：fengxianyijia
                 */
                double fengxianyijia=0.0;

                /**
                 * @Describe：（金融交易）上期评级
                 * 数据库中取出--股票数量汇总
                 * TableName：stock_quantity_summary
                 * Column：shangqipingji
                 */
                double shangqipingji=0.0;

                /**
                 * @Describe：（金融交易）投资决策
                 * 数据库中取出--股票数量汇总
                 * TableName：stock_quantity_summary
                 * Column：touzijuece
                 */
                double touzijuece=0.0;

                /**
                 * @Describe：（金融交易）剥离决策
                 * 数据库中取出--股票数量汇总
                 * TableName：stock_quantity_summary
                 * Column：bolijuece
                 */
                double bolijuece=0.0;
                /**
                 * @Describe：（金融交易）总股数) 当期值
                 * 数据库中取出--股票数量汇总
                 * TableName：stock_quantity_summary
                 * Column：zonggushu
                 */
                double zonggushu=0.0;
                /**
                 * @Describe：(环境变量）经济增长率
                 * 数据库中取出--宏观与产业环境信息
                 * TableName：environment _infor
                 * Column：lilv
                 */
                double jingjizengzhanglv=0.0;

                //（初始设定）过度反应阈值
                double guodufanyingyuzhi=0.5;
                //（初始设定）股票定价反应
                double gupiaodingjiafanying=1.2;
                //（初始设定） 过度反应幅度
                double guodufanyingfudu=0.1;
                //（初始设定）事件冲击影响
                double shijianchongjiyingxiang=0.2;

                //以下为公式计算
                //计算除息后的股票价格
                //exdip = （决策汇总）股票市场价格- （决策汇总）股利支付/ （金融交易）总股数
                double exdip=gupiaoshichangjiage-gulizhifu/zonggushu;
                //理论价格=内在价值与市价的均值*（1+经济增长率）
                //theop = (exdip + （金融交易）股票价值) * (1 + （环境变量）经济增长率) / 2
                double theop=(exdip+gupiaojiazhi)*(1+jingjizengzhanglv)/2;

                double divco=0.0;
                if(preTwoGulizhifu>0){
                        //计算股利变动率
                        // divch = (（决策汇总 前一期）股利支付 - 决策汇总 前二期）股利支付) /决策汇总 前二期）股利支付
                        double divch=(preGulizhifu-preTwoGulizhifu)/preTwoGulizhifu;
                        if(divch>0.1){
                                divco=1;
                        }else if(divch<-0.1){ //'计算是否存在股利发放的市场反应
                                divco=-1;
                        }else {
                                divco = 0;
                        }
                }else {
                        if(preGulizhifu==0){
                                divco=0;
                        }else {
                                divco=1;
                        }
                }

                double proco=0.0;
                if(jingzichanshouyilv>0){
                        if(preTwoJingzichanshouyilv!=0){
                                //计算收益的变动率
                                //  proch = (（决策汇总 前一期）净资产收益率- （决策汇总 前两期）净资产收益率) / Abs(（决策汇总 前两期）净资产收益率)
                                double proch=(preJingzichanshouyilv-preTwoJingzichanshouyilv)/ Math.abs(preTwoJingzichanshouyilv);
                                if(proch >guodufanyingyuzhi){
                                        proco=1;
                                }else if(proco<0-guodufanyingyuzhi){ // 计算是否存在公司利润变化的市场反应
                                        proco=-1;
                                }else {
                                        proco = 0;
                                }
                        }else {
                                proco = 1;
                        }
                }else {
                        proco = -1;
                }
                //计算经营变化带来的市场反映
                //overp = exdip * (divco + proco) * （初始设定）股票定价反应*（初始设定） 过度反应幅度
                double overp=exdip* (divco + proco) * gupiaodingjiafanying*guodufanyingfudu;
                double crc=0.0;
                if(fengxianyijia>shangqipingji){//计算是否存在公司信用评级下降的市场反应
                        crc=-1;
                }else if(fengxianyijia==shangqipingji){
                        crc = 0;
                }else {
                        crc = 1;
                }

                //计算信用评级变化、投资、资产剥离等事件的冲击影响
                //evenp = (crc + （金融交易）投资决策- （金融交易）剥离决策) *（初始设定）事件冲击影响* exdip
               // 说明，投资决策和剥离决策数据来自于企业决策信息，有资本预算决策取1，否则取0；有资产剥离决策取1，否则取0。这部分赋值在模块2数据准备SYXDataReady过程中。
                double evenp=(crc+touzijuece-bolijuece)*shijianchongjiyingxiang*exdip;
                /**
                 * @Describe：（决策汇总）股票市场价格--计算最终的市场价格
                 * 保存数据库--公司整体信息变动表
                 * TableName：enterprise_infor_change
                 * Column：gupiaoshichangjiage
                 */
                double returnGupiaoshichangjiage=theop + overp + evenp ;
        }





}



