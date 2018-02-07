package com.atguigu.bean;

public class T_mall_trade_mark {
    private Integer id;

    private String ppmch;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPpmch() {
        return ppmch;
    }

    public void setPpmch(String ppmch) {
        this.ppmch = ppmch == null ? null : ppmch.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}