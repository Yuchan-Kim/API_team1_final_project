package com.javaex.vo;

public class HmkNoticeSummaryVo {
    private int readNotice;
    private int newNotice;
    private int totalNotice;
    
	public HmkNoticeSummaryVo() {
		super();
	}
	public HmkNoticeSummaryVo(int readNotice, int newNotice, int totalNotice) {
		super();
		this.readNotice = readNotice;
		this.newNotice = newNotice;
		this.totalNotice = totalNotice;
	}
	public int getReadNotice() {
		return readNotice;
	}
	public void setReadNotice(int readNotice) {
		this.readNotice = readNotice;
	}
	public int getNewNotice() {
		return newNotice;
	}
	public void setNewNotice(int newNotice) {
		this.newNotice = newNotice;
	}
	public int getTotalNotice() {
		return totalNotice;
	}
	public void setTotalNotice(int totalNotice) {
		this.totalNotice = totalNotice;
	}
	@Override
	public String toString() {
		return "HmkNoticeSummaryVo [readNotice=" + readNotice + ", newNotice=" + newNotice + ", totalNotice="
				+ totalNotice + "]";
	}
    
}
