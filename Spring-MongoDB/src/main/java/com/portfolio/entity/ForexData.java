package com.portfolio.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "forex_data") // 指定 MongoDB 中的集合名称
public class ForexData {

    @Id
    private String id;  // MongoDB 中的 _id 通常是 String 类型
    @Field("date")
    private String date;  // 时间字段

    @Field("usd_ntd_rate")
    private Double usdNtdRate;  // 美元对新台币汇率

    @Field("rmb_ntd_rate")
    private Double rmbNtdRate;  // 人民币对新台币汇率

    @Field("eur_usd_rate")
    private Double eurUsdRate;  // 欧元对美元汇率

    @Field("usd_jpy_rate")
    private Double usdJpyRate;  // 美元对日元汇率

    @Field("gbp_usd_rate")
    private Double gbpUsdRate;  // 英镑对美元汇率

    @Field("aud_usd_rate")
    private Double audUsdRate;  // 澳元对美元汇率

    @Field("usd_hkd_rate")
    private Double usdHkdRate;  // 美元对港元汇率

    @Field("usd_rmb_rate")
    private Double usdRmbRate;  // 美元对人民币汇率

    @Field("usd_zar_rate")
    private Double usdZarRate;  // 美元对南非兰特汇率

    @Field("nzd_usd_rate")
    private Double nzdUsdRate;  // 纽元对美元汇率

    public ForexData() {
    }

    // 构造函数
    public ForexData(String id, String date, Double usdNtdRate) {
        this.id = id;
        this.date = date;
        this.usdNtdRate = usdNtdRate;
    }

    public ForexData(String id, String date, Double usdNtdRate, Double rmbNtdRate, Double eurUsdRate,
                     Double usdJpyRate, Double gbpUsdRate, Double audUsdRate, Double usdHkdRate,
                     Double usdRmbRate, Double usdZarRate, Double nzdUsdRate) {
        this.id = id;
        this.date = date;
        this.usdNtdRate = usdNtdRate;
        this.rmbNtdRate = rmbNtdRate;
        this.eurUsdRate = eurUsdRate;
        this.usdJpyRate = usdJpyRate;
        this.gbpUsdRate = gbpUsdRate;
        this.audUsdRate = audUsdRate;
        this.usdHkdRate = usdHkdRate;
        this.usdRmbRate = usdRmbRate;
        this.usdZarRate = usdZarRate;
        this.nzdUsdRate = nzdUsdRate;
    }

    // Getter 和 Setter 方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getUsdNtdRate() {
        return usdNtdRate;
    }

    public void setUsdNtdRate(Double usdNtdRate) {
        this.usdNtdRate = usdNtdRate;
    }

    public Double getRmbNtdRate() {
        return rmbNtdRate;
    }

    public void setRmbNtdRate(Double rmbNtdRate) {
        this.rmbNtdRate = rmbNtdRate;
    }

    public Double getEurUsdRate() {
        return eurUsdRate;
    }

    public void setEurUsdRate(Double eurUsdRate) {
        this.eurUsdRate = eurUsdRate;
    }

    public Double getUsdJpyRate() {
        return usdJpyRate;
    }

    public void setUsdJpyRate(Double usdJpyRate) {
        this.usdJpyRate = usdJpyRate;
    }

    public Double getGbpUsdRate() {
        return gbpUsdRate;
    }

    public void setGbpUsdRate(Double gbpUsdRate) {
        this.gbpUsdRate = gbpUsdRate;
    }

    public Double getAudUsdRate() {
        return audUsdRate;
    }

    public void setAudUsdRate(Double audUsdRate) {
        this.audUsdRate = audUsdRate;
    }

    public Double getUsdHkdRate() {
        return usdHkdRate;
    }

    public void setUsdHkdRate(Double usdHkdRate) {
        this.usdHkdRate = usdHkdRate;
    }

    public Double getUsdRmbRate() {
        return usdRmbRate;
    }

    public void setUsdRmbRate(Double usdRmbRate) {
        this.usdRmbRate = usdRmbRate;
    }

    public Double getUsdZarRate() {
        return usdZarRate;
    }

    public void setUsdZarRate(Double usdZarRate) {
        this.usdZarRate = usdZarRate;
    }

    public Double getNzdUsdRate() {
        return nzdUsdRate;
    }

    public void setNzdUsdRate(Double nzdUsdRate) {
        this.nzdUsdRate = nzdUsdRate;
    }

    @Override
    public String toString() {
        return "ForexData [id=" + id + ", date=" + date + ", usdNtdRate=" + usdNtdRate + ", rmbNtdRate=" + rmbNtdRate
                + ", eurUsdRate=" + eurUsdRate + ", usdJpyRate=" + usdJpyRate + ", gbpUsdRate=" + gbpUsdRate
                + ", audUsdRate=" + audUsdRate + ", usdHkdRate=" + usdHkdRate + ", usdRmbRate=" + usdRmbRate
                + ", usdZarRate=" + usdZarRate + ", nzdUsdRate=" + nzdUsdRate + "]";
    }
}
