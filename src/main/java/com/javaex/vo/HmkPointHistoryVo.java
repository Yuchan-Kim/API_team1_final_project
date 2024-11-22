package com.javaex.vo;

public class HmkPointHistoryVo {
	// 필드
	private String date;
    private String purposeName;
    private int historyPoint;
    private String historyInfo;
    private int total;
    
    // 생성자
	public HmkPointHistoryVo() {
		super();
	}
	public HmkPointHistoryVo(String date, String purposeName, int historyPoint, String historyInfo, int total) {
		super();
		this.date = date;
		this.purposeName = purposeName;
		this.historyPoint = historyPoint;
		this.historyInfo = historyInfo;
		this.total = total;
	}
    // getter - setter
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPurposeName() {
		return purposeName;
	}
	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}
	public int getHistoryPoint() {
		return historyPoint;
	}
	public void setHistoryPoint(int historyPoint) {
		this.historyPoint = historyPoint;
	}
	public String getHistoryInfo() {
		return historyInfo;
	}
	public void setHistoryInfo(String historyInfo) {
		this.historyInfo = historyInfo;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	// 일반 메소드
	@Override
	public String toString() {
		return "HmkPointHistoryVo [date=" + date + ", purposeName=" + purposeName + ", historyPoint=" + historyPoint
				+ ", historyInfo=" + historyInfo + ", total=" + total + "]";
	}
	
    
}
