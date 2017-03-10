package com.tinymore.cas.utils;

public enum MessageCode {
	SUCCESS(0,"success"),
	FAILURE(-1,"failure");

    private int code;
    private String message;

    MessageCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
