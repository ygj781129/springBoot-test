package com.example.demo.service;

import com.example.demo.pojo.Student;
import com.google.common.collect.Lists;
import com.sun.org.apache.regexp.internal.RE;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by fb on 2020/9/18
 */
public class IOTest {
        public static void mainpp(String[] args) throws IOException {
                File file = new File("D:/360Downloads/myfile.txt");
                File file1 = new File("D:/360Downloads/8888888.txt");
//                BufferedReader bufferedReader =null;
//                BufferedWriter bufferedWriter  =null;
               try (

                       FileInputStream fileInputStream = new FileInputStream(file);
                       //获取字符输入流
                       InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"gbk");

                       BufferedReader bufferedReader=new BufferedReader(inputStreamReader);


                       FileOutputStream fileOutputStream = new FileOutputStream(file1);
                       OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                       BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
                       ){

                       String line = null;
                       while ((line=bufferedReader.readLine())!= null){
                               System.out.println(line);
                               bufferedWriter.write(line);
                               bufferedWriter.newLine();
                       }


                } catch (IOException e) {
                        e.printStackTrace();
                } finally {

                }
        }


        public static void mainhghg(String[] args) throws IOException {
                try {
                        writeObject();
                        readObject();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                }
        }


        public static void writeObject() {
                OutputStream outputStream = null;
                BufferedOutputStream buf = null;
                ObjectOutputStream obj = null;
                try {
                        //序列化文件輸出流
                        outputStream=new FileOutputStream("D:/360Downloads/myfile.tmp");
                        //构建缓冲流
                        buf = new BufferedOutputStream(outputStream);
                        //构建字符输出的对象流
                        obj = new ObjectOutputStream(buf);
                        Student student=new Student();
                        student.setName("fdfdfd");
                        student.setAge(111);
                        student.setSex("辅导费");
                        //序列化数据写入
                        obj.writeObject(student);//Person对象
                        //关闭流
                        obj.close();
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        /**
         * 读取对象
         */
        public static void readObject() throws IOException {
                try {
                        InputStream inputStream = new FileInputStream("D:/360Downloads/myfile.tmp");
                        //构建缓冲流
                        BufferedInputStream buf = new BufferedInputStream(inputStream);
                        //构建字符输入的对象流
                        ObjectInputStream obj = new ObjectInputStream(buf);
                        Student tempPerson = (Student) obj.readObject();
                        System.out.println("Person对象为：" + tempPerson);
                        //关闭流
                        obj.close();
                        buf.close();
                        inputStream.close();
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }
        }


        public static List<String> readFile() {//路径
                File file = new File("D:/360/44.txt");
                int line = 1;
                List<String> list= Lists.newArrayList();
                try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));//构造一个BufferedReader类来读取文件
                        String s = null;
                        while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
//                                System.out.println("line " + line + ": " + s);
//                                line++;
                                list.add(s);
                        }
                        br.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return list;
        }

        public static void main(String[] args) {
               List<String> list = readFile();

//                String str=list.get(0);
//                String str1=str.substring(0, str.indexOf(" -"));
//                System.out.println(str1);
                Map<String,List<String>> map = list.stream().filter(n->n.indexOf("pipeMarket")!=-1).collect(Collectors.groupingBy(b->b.substring(0, b.indexOf(" -"))));
                //Map<String,List<String>> map = list.stream().collect(Collectors.groupingBy(b->b.substring(0, b.indexOf(" -"))));
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                        System.out.println(entry.getKey() + "    出现次数：" + entry.getValue().size());
                }

        }
}
