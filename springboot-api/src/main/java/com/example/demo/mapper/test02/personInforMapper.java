package com.example.demo.mapper.test02;

import com.example.demo.pojo.personInfor;
import com.example.demo.pojo.personInforExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

public interface personInforMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_infor
     *
     * @mbg.generated Thu Jun 04 09:19:22 CST 2020
     */
    long countByExample(personInforExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_infor
     *
     * @mbg.generated Thu Jun 04 09:19:22 CST 2020
     */
    int deleteByExample(personInforExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_infor
     *
     * @mbg.generated Thu Jun 04 09:19:22 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_infor
     *
     * @mbg.generated Thu Jun 04 09:19:22 CST 2020
     */
    int insert(personInfor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_infor
     *
     * @mbg.generated Thu Jun 04 09:19:22 CST 2020
     */
    int insertSelective(personInfor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_infor
     *
     * @mbg.generated Thu Jun 04 09:19:22 CST 2020
     */
    List<personInfor> selectByExample(personInforExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_infor
     *
     * @mbg.generated Thu Jun 04 09:19:22 CST 2020
     */
    personInfor selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_infor
     *
     * @mbg.generated Thu Jun 04 09:19:22 CST 2020
     */
    int updateByExampleSelective(@Param("record") personInfor record, @Param("example") personInforExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_infor
     *
     * @mbg.generated Thu Jun 04 09:19:22 CST 2020
     */
    int updateByExample(@Param("record") personInfor record, @Param("example") personInforExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_infor
     *
     * @mbg.generated Thu Jun 04 09:19:22 CST 2020
     */
    int updateByPrimaryKeySelective(personInfor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person_infor
     *
     * @mbg.generated Thu Jun 04 09:19:22 CST 2020
     */
    int updateByPrimaryKey(personInfor record);

    @Select("select * from person_infor")
    Cursor<personInfor> scan();

    @Select("select * from person_infor where name= #{name}")
    List<personInfor> scanll(@Param("name") String name);


}