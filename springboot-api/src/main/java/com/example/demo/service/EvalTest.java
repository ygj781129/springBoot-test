package com.example.demo.service;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

/**
 * Created by fb on 2020/10/13
 */
public class EvalTest {

        public static void main(String args[]) {
                Evaluator evaluator = new Evaluator();
                String str="sin(30)";
                try {
                        //System.out.println("1.-->"+evaluator.evaluate(str));  //直接计算字符串型的数学表达式
                        //System.out.println("2.-->"+evaluator.evaluate("toUpperCase(trim( trim(' a b c ') ))"));  //运行java中的方法
                        evaluator.putVariable("a", "1");//定义字符串变量
                        evaluator.putVariable("b", "2");
                        evaluator.putVariable("c", "3"); //定义数字变量
                        evaluator.putVariable("d", "4");
                        //System.out.println("3.-->"+evaluator.evaluate("#{a}")); //输出字符串
                        //System.out.println("4.-->"+evaluator.evaluate("#{b}"));
                        //System.out.println("5.-->"+evaluator.evaluate("#{PI}"));
                        //System.out.println("6.-->"+evaluator.evaluate("#{a} + ' ' + #{b} + '!'")); //拼接后输出
                        System.out.println("7.-->"+evaluator.evaluate("(#{a}+#{b}-(3*#{a}))#{c}"));  //拼接后输出计算结果

                } catch (EvaluationException ee) {
                        System.out.println(ee);
                }
        }
}
