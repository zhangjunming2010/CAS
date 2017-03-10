package com.tinymore.cas.model;

public class MEvaluationAssess {
    private String ceaId;

    private String ceId;

    private Integer ceaMin;

    private Integer ceaMax;

    private String ceaDesc;

    public String getCeaId() {
        return ceaId;
    }

    public void setCeaId(String ceaId) {
        this.ceaId = ceaId == null ? null : ceaId.trim();
    }

    public String getCeId() {
        return ceId;
    }

    public void setCeId(String ceId) {
        this.ceId = ceId == null ? null : ceId.trim();
    }

    public Integer getCeaMin() {
        return ceaMin;
    }

    public void setCeaMin(Integer ceaMin) {
        this.ceaMin = ceaMin;
    }

    public Integer getCeaMax() {
        return ceaMax;
    }

    public void setCeaMax(Integer ceaMax) {
        this.ceaMax = ceaMax;
    }

    public String getCeaDesc() {
        return ceaDesc;
    }

    public void setCeaDesc(String ceaDesc) {
        this.ceaDesc = ceaDesc == null ? null : ceaDesc.trim();
    }
}