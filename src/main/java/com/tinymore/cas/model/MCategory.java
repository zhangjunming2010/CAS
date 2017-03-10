package com.tinymore.cas.model;

public class MCategory {
    private String ccId;

    private String ccName;

    public String getCcId() {
        return ccId;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId == null ? null : ccId.trim();
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName == null ? null : ccName.trim();
    }
}