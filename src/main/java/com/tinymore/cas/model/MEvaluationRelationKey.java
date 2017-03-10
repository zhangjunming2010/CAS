package com.tinymore.cas.model;

import java.util.List;

public class MEvaluationRelationKey {
    private String ceId;

    private String cqId;
    
    private List<MQuestion> questions;

    public String getCeId() {
        return ceId;
    }

    public void setCeId(String ceId) {
        this.ceId = ceId == null ? null : ceId.trim();
    }

    public String getCqId() {
        return cqId;
    }

    public void setCqId(String cqId) {
        this.cqId = cqId == null ? null : cqId.trim();
    }

	public List<MQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<MQuestion> questions) {
		this.questions = questions;
	}
}