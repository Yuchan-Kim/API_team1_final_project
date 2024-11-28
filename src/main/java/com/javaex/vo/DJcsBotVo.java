package com.javaex.vo;

public class DJcsBotVo {
	
	//필드
	private int userNum;
	private String csbotWriter;
	private String csbotAnswer;
	
	
	//생성자
	public DJcsBotVo() {
		super();
	}


	public DJcsBotVo(int userNum, String csbotWriter, String csbotAnswer) {
		super();
		this.userNum = userNum;
		this.csbotWriter = csbotWriter;
		this.csbotAnswer = csbotAnswer;
	}


	public int getUserNum() {
		return userNum;
	}


	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}


	public String getCsbotWriter() {
		return csbotWriter;
	}


	public void setCsbotWriter(String csbotWriter) {
		this.csbotWriter = csbotWriter;
	}


	public String getCsbotAnswer() {
		return csbotAnswer;
	}


	public void setCsbotAnswer(String csbotAnswer) {
		this.csbotAnswer = csbotAnswer;
	}


	@Override
	public String toString() {
		return "DJcsBotVo [userNum=" + userNum + ", csbotWriter=" + csbotWriter + ", csbotAnswer=" + csbotAnswer + "]";
	}


	
}
