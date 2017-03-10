package com.tinymore.cas.model;

public class MQuestion {
    private String cqId;

    private String ctId;

    private String ccId;

    private String cqTitle;

    public String getCqId() {
        return cqId;
    }

    public void setCqId(String cqId) {
        this.cqId = cqId == null ? null : cqId.trim();
    }

    public String getCtId() {
        return ctId;
    }

    public void setCtId(String ctId) {
        this.ctId = ctId == null ? null : ctId.trim();
    }

    public String getCcId() {
        return ccId;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId == null ? null : ccId.trim();
    }

    public String getCqTitle() {
        return cqTitle;
    }

    public void setCqTitle(String cqTitle) {
        this.cqTitle = cqTitle == null ? null : cqTitle.trim();
    }
}