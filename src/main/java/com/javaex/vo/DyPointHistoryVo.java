package com.javaex.vo;

public class DyPointHistoryVo {
	
	//필드
	private int historyNum;
	private int userNum;
	private int pointPurposeNum;
	private String historyDate;
	private int historyPoint;
	private String historyInfo;
	private int itemCost;
	
	
	//생성자
	public DyPointHistoryVo() {
		super();
	}

	public DyPointHistoryVo(int historyNum, int userNum, int pointPurposeNum, String historyDate, int historyPoint,
			String historyInfo) {
		super();
		this.historyNum = historyNum;
		this.userNum = userNum;
		this.pointPurposeNum = pointPurposeNum;
		this.historyDate = historyDate;
		this.historyPoint = historyPoint;
		this.historyInfo = historyInfo;
	}


	public int getHistoryNum() {
		return historyNum;
	}

	public void setHistoryNum(int historyNum) {
		this.historyNum = historyNum;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public int getPointPurposeNum() {
		return pointPurposeNum;
	}

	public void setPointPurposeNum(int pointPurposeNum) {
		this.pointPurposeNum = pointPurposeNum;
	}

	public String getHistoryDate() {
		return historyDate;
	}

	public void setHistoryDate(String historyDate) {
		this.historyDate = historyDate;
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

	public int getItemCost() {
		return itemCost;
	}

	public void setItemCost(int itemCost) {
		this.itemCost = itemCost;
	}

	
	@Override
	public String toString() {
		return "DyPointHistoryVo [historyNum=" + historyNum + ", userNum=" + userNum + ", pointPurposeNum="
				+ pointPurposeNum + ", historyDate=" + historyDate + ", historyPoint=" + historyPoint + ", historyInfo="
				+ historyInfo + ", itemCost=" + itemCost + "]";
	}


}
