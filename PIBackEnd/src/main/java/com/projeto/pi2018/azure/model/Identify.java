package com.projeto.pi2018.azure.model;

import java.util.List;

public class Identify{
	private String faceId;
	private List<Candidatos> candidates;
	
	public String getFaceId() {
		return faceId;
	}
	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}
	public List<Candidatos> getCandidates() {
		return candidates;
	}
	public void setCandidates(List<Candidatos> candidates) {
		this.candidates = candidates;
	}

	
	
}
