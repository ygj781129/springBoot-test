package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Created by fb on 2021/3/12
 * 返回用户登录记录的对象
 */
//建造者模式
@Builder
//无参构造方法
@NoArgsConstructor
//全参构造方法
@AllArgsConstructor
public class UserLoginLogVo {
        private String codeName; //指标
        private String rq; //日期
        private String data; //数值
        private String chg; //涨跌
        private String range; //涨跌幅

        public String getCodeName() {
                return codeName;
        }

        public void setCodeName(String codeName) {
                this.codeName = codeName;
        }

        public String getRq() {
                return rq;
        }

        public void setRq(String rq) {
                this.rq = rq;
        }

        public String getData() {
                return data;
        }

        public void setData(String data) {
                this.data = data;
        }

        public String getChg() {
                return chg;
        }

        public void setChg(String chg) {
                this.chg = chg;
        }

        public String getRange() {
                return range;
        }

        public void setRange(String range) {
                this.range = range;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof UserLoginLogVo)) return false;
                UserLoginLogVo that = (UserLoginLogVo) o;
                return Objects.equals(getCodeName(), that.getCodeName()) &&
                        Objects.equals(getRq(), that.getRq()) &&
                        Objects.equals(getData(), that.getData()) &&
                        Objects.equals(getChg(), that.getChg()) &&
                        Objects.equals(getRange(), that.getRange());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getCodeName(), getRq(), getData(), getChg(), getRange());
        }
}
