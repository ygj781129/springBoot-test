package com.example.demo.service;

import com.example.demo.mapper.HMedicalExamineMapper;
import com.example.demo.mapper.TimeTestMapper;
import com.example.demo.mapper.test02.personInforMapper;
import com.example.demo.pojo.HMedicalExamine;
import com.example.demo.pojo.TimeTest;
import com.example.demo.pojo.personInfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/9/18.
 */
@Service
public class ThhServicesImpl {
    @Resource
    private HMedicalExamineMapper medicalExamineMapper;
    @Autowired
    private TimeTestMapper timeTestMapper;



    public List<TimeTest> getgett(){
        List<TimeTest> list= timeTestMapper.selectByExample(null);
        /**
         * 如下这个排序
         * 给对象排序 根据对象的某个属性倒叙
         */
        List<TimeTest> newList=list.stream().sorted(Comparator.comparing(TimeTest::getOperateTime).reversed()).collect(Collectors.toList());
        return newList;
    }

    public List<HMedicalExamine> getget(){
        List<HMedicalExamine> list= medicalExamineMapper.selectByExample(null);
        List<HMedicalExamine> newList=list.stream().sorted(Comparator.comparing(HMedicalExamine::getId).reversed()).collect(Collectors.toList());
        return newList;
    }




    public List<Long> getEnty44(){
        List<HMedicalExamine> list= medicalExamineMapper.selectByExample(null);
        List<Long> list1= Arrays.asList();
        if(list!=null&&!list.isEmpty()){
            /**
             * stream（流） map 对应的是list中的每一个元素 让每一个元素有对应的值
             * map中的条件可以写成map(n->{ }) {}里写方法体 最后 return 个 对象
             * limit限制的是集合能有多少个
             * collect 变成一个集合
             */
            list1=list.stream().map(n->n.getId()).limit(5).collect(Collectors.toList());
        }
        return list1;
    }
    @Cacheable(cacheNames = "product333",key = "T(String).valueOf(#tag).concat('-').concat(#productId)")
    public List<HMedicalExamine> getEnty( String tag,String productId){
        List<HMedicalExamine> list= medicalExamineMapper.selectByExample(null);
        List<HMedicalExamine> list2=Arrays.asList();
        if(list!=null&&!list.isEmpty()){
            /**
             * filter 过滤
             */
            list2=list.stream().filter(n->
                n.getDepartment().equals("外呼吸道科")&&n.getId()==1
            ).collect(Collectors.toList());
//            list.forEach(n->{
//                n.setDepartment("666666666");
//            });
        }
        return list2;
    }


    @Cacheable(cacheNames = "product333",key = "#key")
    public List ff(String key){
       return null;
    }
    public void saveOrUpdate(List<HMedicalExamine> list){
       for(HMedicalExamine model:list){
            if(model.getId()!=null){
                medicalExamineMapper.updateByPrimaryKeySelective(model);
            }else {
                medicalExamineMapper.insert(model);
            }
       }
    }

    public void executeAsyncTask(String msg) {
        System.out.println(Thread.currentThread().getName()+"开启新线程执行" + msg);
    }

    @Resource
    private com.example.demo.mapper.test02.personInforMapper personInforMapper;
    public void save(List<personInfor> list){
        for(personInfor model:list){
            personInforMapper.insert(model);
        }
    }

}
