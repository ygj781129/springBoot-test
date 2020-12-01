package com.example.demo.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Created by fb on 2020/4/22
 */
@Getter
@Setter
public class KeyValVo {
        private String key;
        private String val;


        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof KeyValVo)) return false;
                KeyValVo keyValVo = (KeyValVo) o;
                return Objects.equals(getVal(), keyValVo.getVal());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getVal());
        }
}
