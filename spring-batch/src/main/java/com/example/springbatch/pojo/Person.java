package com.example.springbatch.pojo;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by fb on 2021/7/15
 */
public class Person implements Serializable {
        private final long serialVersionUID = 1L;

        private String id;
        @Size(min = 2, max = 8)
        private String name;
        private int age;
        private String gender;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getAge() {
                return age;
        }

        public void setAge(int age) {
                this.age = age;
        }

        public String getGender() {
                return gender;
        }

        public void setGender(String gender) {
                this.gender = gender;
        }

        public Person(String id, @Size(min = 2, max = 8) String name, int age, String gender) {
                this.id = id;
                this.name = name;
                this.age = age;
                this.gender = gender;
        }
        public Person() {
                super();
        }

        @Override
        public String toString() {
                return "Person{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", age=" + age +
                        ", gender='" + gender + '\'' +
                        '}';
        }
}
