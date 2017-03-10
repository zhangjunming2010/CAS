package com.tinymore.cas.model;

public class MUser {
    private String cuId;

    private String cuAccount;

    private String cuPassword;

    private String cuType;

    private String cuEmail;

    private Boolean cuStatus;
    
    private Integer cuRole;
    
    private String cuName;

    public String getCuId() {
        return cuId;
    }

    public void setCuId(String cuId) {
        this.cuId = cuId == null ? null : cuId.trim();
    }

    public String getCuAccount() {
        return cuAccount;
    }

    public void setCuAccount(String cuAccount) {
        this.cuAccount = cuAccount == null ? null : cuAccount.trim();
    }

    public String getCuPassword() {
        return cuPassword;
    }

    public void setCuPassword(String cuPassword) {
        this.cuPassword = cuPassword == null ? null : cuPassword.trim();
    }

    public String getCuType() {
        return cuType;
    }

    public void setCuType(String cuType) {
        this.cuType = cuType == null ? null : cuType.trim();
    }

    public String getCuEmail() {
        return cuEmail;
    }

    public void setCuEmail(String cuEmail) {
        this.cuEmail = cuEmail == null ? null : cuEmail.trim();
    }

    public Boolean getCuStatus() {
        return cuStatus;
    }

    public void setCuStatus(Boolean cuStatus) {
        this.cuStatus = cuStatus;
    }

	public Integer getCuRole() {
		return cuRole;
	}

	public void setCuRole(Integer cuRole) {
		this.cuRole = cuRole;
	}

	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName == null ? null : cuName.trim();
	}
}