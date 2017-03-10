package com.tinymore.cas.model;

public class MEvaluation {
    private String ceId;

    private String ceTitle;

    private Boolean ceStatus;

    private String ceDes;

    public String getCeId() {
        return ceId;
    }

    public void setCeId(String ceId) {
        this.ceId = ceId == null ? null : ceId.trim();
    }

    public String getCeTitle() {
        return ceTitle;
    }

    public void setCeTitle(String ceTitle) {
        this.ceTitle = ceTitle == null ? null : ceTitle.trim();
    }

    public Boolean getCeStatus() {
        return ceStatus;
    }

    public void setCeStatus(Boolean ceStatus) {
        this.ceStatus = ceStatus;
    }

    public String getCeDes() {
        return ceDes;
    }

    public void setCeDes(String ceDes) {
        this.ceDes = ceDes == null ? null : ceDes.trim();
    }
}