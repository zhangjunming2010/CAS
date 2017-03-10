package com.tinymore.cas.model;

public class MType {
    private String ctId;

    private String ctName;

    public String getCtId() {
        return ctId;
    }

    public void setCtId(String ctId) {
        this.ctId = ctId == null ? null : ctId.trim();
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName == null ? null : ctName.trim();
    }
}