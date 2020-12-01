package com.example.demo.pojo;

public class Member {
        private String  username;
        private String  password;
        private String  truename;
        private String  nickname;
        private String  gender;
        private int     age;
        @Override
        public String toString(){
                return (username==null?"":"username:" + username) +
                        (password==null?"":" password:" + password) +
                        (truename==null?"":" truename:" + truename) +
                        (nickname==null?"":" nickname:" + nickname) +
                        (gender==null?"":" gender:" + gender) +
                        " age:" + age;
        }
}
