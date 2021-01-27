package com.example.demo.service;

import com.example.demo.mapper.test01.TNavigationMapper;
import com.example.demo.mapper.test02.personInforMapper;
import com.example.demo.mapper.test03.TonlineUserMapper;
import com.example.demo.pojo.*;
import com.example.demo.vo.KeyValVo;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fb on 2020/6/4
 */
@Service
public class DataSourceTest {
        @Resource
        private TNavigationMapper navigationMapper;

        @Resource
        private  personInforMapper personInforMapper;

        @Resource
        private TonlineUserMapper tonlineUserMappe;

        public List<KeyValVo> findList(){
                TNavigation na=navigationMapper.selectByPrimaryKey(39l);
                personInfor pe= personInforMapper.selectByPrimaryKey(1l);
                TonlineUserExample query=new TonlineUserExample();
                TonlineUserExample.Criteria criteria=query.createCriteria();
                criteria.andUserIdEqualTo("63E04E1934EE0CF1E050A8C00E01F01C");
                List<TonlineUser> user=tonlineUserMappe.selectByExample(query);
                List<KeyValVo> list= Lists.newArrayList();
                KeyValVo valVo=new KeyValVo();
                valVo.setKey(na.getNavigationId());
                valVo.setVal(na.getNavigationName());
                list.add(valVo);
                KeyValVo valVo2=new KeyValVo();
                valVo2.setKey(pe.getId());
                valVo2.setVal(pe.getName());
                list.add(valVo2);
                KeyValVo valVo3=new KeyValVo();
                valVo3.setKey(1L);
                valVo3.setVal(user.get(0).getEnterprise());
                list.add(valVo3);
                return list;
        }



        @Transactional(rollbackFor = Exception.class)
        public void update()throws  Exception{
                TNavigation na =new TNavigation();
                na.setNavigationId(90l);
                na.setNavigationName("995555555");
                navigationMapper.updateByPrimaryKeySelective(na);
                        List<personInfor> list = new ArrayList();
                        personInfor p1 = new personInfor();
                        p1.setAge(1);
                        p1.setId(122l);
                        p1.setName("非得梵蒂冈梵蒂冈");

                        personInfor p2 = new personInfor();
                        p2.setAge(111);
                        p2.setId(1l);
                        p2.setName("yy一样");
                        personInfor p3 = new personInfor();
                        p3.setAge(66);
                        p3.setId(66l);
                        p3.setName("uuygg");
                        list.add(p1);
                        list.add(p2);
                        list.add(p3);
                        for (personInfor per : list) {
                                personInforMapper.insert(per);
                        }


        }

        public List<personInfor> finddd(String name){
                personInforExample query=new personInforExample();
                personInforExample.Criteria criteria=query.createCriteria();
                criteria.andNameEqualTo(name);
                criteria.andAgeEqualTo(555);
                List<personInfor> list=personInforMapper.selectByExample(query);
               return list;
        }


}
