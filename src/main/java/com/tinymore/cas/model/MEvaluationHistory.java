package com.tinymore.cas.model;

public class MEvaluationHistory {
    private String cehId;

    private String cuId;

    private String cehTitle;

    private Boolean cehStatus;

    private String cehDate;

    private Integer cehScore;

    private String cehDesc;

    private String cehOptions;
    
    private MUser user;

    public String getCehId() {
        return cehId;
    }

    public void setCehId(String cehId) {
        this.cehId = cehId == null ? null : cehId.trim();
    }

    public String getCuId() {
        return cuId;
    }

    public void setCuId(String cuId) {
        this.cuId = cuId == null ? null : cuId.trim();
    }

    public String getCehTitle() {
        return cehTitle;
    }

    public void setCehTitle(String cehTitle) {
        this.cehTitle = cehTitle == null ? null : cehTitle.trim();
    }

    public Boolean getCehStatus() {
        return cehStatus;
    }

    public void setCehStatus(Boolean cehStatus) {
        this.cehStatus = cehStatus;
    }

    public String getCehDate() {
        return cehDate;
    }

    public void setCehDate(String cehDate) {
        this.cehDate = cehDate == null ? null : cehDate.trim();
    }

    public Integer getCehScore() {
        return cehScore;
    }

    public void setCehScore(Integer cehScore) {
        this.cehScore = cehScore;
    }

    public String getCehDesc() {
        return cehDesc;
    }

    public void setCehDesc(String cehDesc) {
        this.cehDesc = cehDesc == null ? null : cehDesc.trim();
    }

    public String getCehOptions() {
        return cehOptions;
    }

    public void setCehOptions(String cehOptions) {
        this.cehOptions = cehOptions == null ? null : cehOptions.trim();
    }

	public MUser getUser() {
		return user;
	}

	public void setUser(MUser user) {
		this.user = user;
	}
}