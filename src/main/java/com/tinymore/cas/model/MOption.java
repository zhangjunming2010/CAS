package com.tinymore.cas.model;

public class MOption {
    private String coId;

    private String cqId;

    private String coTitle;

    private Integer coScore;

    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId == null ? null : coId.trim();
    }

    public String getCqId() {
        return cqId;
    }

    public void setCqId(String cqId) {
        this.cqId = cqId == null ? null : cqId.trim();
    }

    public String getCoTitle() {
        return coTitle;
    }

    public void setCoTitle(String coTitle) {
        this.coTitle = coTitle == null ? null : coTitle.trim();
    }

    public Integer getCoScore() {
        return coScore;
    }

    public void setCoScore(Integer coScore) {
        this.coScore = coScore;
    }
}