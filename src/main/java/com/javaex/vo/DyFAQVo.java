package com.javaex.vo;

public class DyFAQVo {
	
	//필드
	private int faqNum;
	private String faqTitle;
	private String faqContent;
	
	
	//생성자
	public DyFAQVo() {
		super();
	}


	public DyFAQVo(int faqNum, String faqTitle, String faqContent) {
		super();
		this.faqNum = faqNum;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
	}


	public int getFaqNum() {
		return faqNum;
	}


	public void setFaqNum(int faqNum) {
		this.faqNum = faqNum;
	}


	public String getFaqTitle() {
		return faqTitle;
	}


	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}


	public String getFaqContent() {
		return faqContent;
	}


	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}


	@Override
	public String toString() {
		return "DyFAQVo [faqNum=" + faqNum + ", faqTitle=" + faqTitle + ", faqContent=" + faqContent + "]";
	}

	
}
