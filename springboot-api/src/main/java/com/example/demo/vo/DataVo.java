package com.example.demo.vo;

/**
 * Created by Administrator on 2019/11/11.
 */
public class DataVo {
    private double chgRadio;//涨跌幅
    private double chg;//涨跌值
    private double highest;//历史最高
    private double lowest;//历史最低
    private double price;//价格
    private String areaName;//区域名称
    private String groupCode;
    private String nLevel;
    private String dataDate;//当前日期
    private String originalName;//原名称
    private String basePriceName;//基准价名称
    private String unitName;//单位名称
    private String tag;
    private String type;
    private String areaId;
    private String provinceTag;//省级标识

    public String getProvinceTag() {
        return provinceTag;
    }

    public void setProvinceTag(String provinceTag) {
        this.provinceTag = provinceTag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBasePriceName() {
        return basePriceName;
    }

    public void setBasePriceName(String basePriceName) {
        this.basePriceName = basePriceName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getnLevel() {
        return nLevel;
    }

    public void setnLevel(String nLevel) {
        this.nLevel = nLevel;
    }

    public double getChgRadio() {
        return chgRadio;
    }

    public void setChgRadio(double chgRadio) {
        this.chgRadio = chgRadio;
    }

    public double getChg() {
        return chg;
    }

    public void setChg(double chg) {
        this.chg = chg;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public double getHighest() {
        return highest;
    }

    public void setHighest(double highest) {
        this.highest = highest;
    }

    public double getLowest() {
        return lowest;
    }

    public void setLowest(double lowest) {
        this.lowest = lowest;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
