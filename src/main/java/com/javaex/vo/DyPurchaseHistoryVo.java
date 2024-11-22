package com.javaex.vo;

public class DyPurchaseHistoryVo {
	
	//필드
	private int purchaseNum;
	private int itemNum;
	private int userNum;
	private String purchasedDate;
	private String purchasedStatus;
    private int itemCost;  
    private String itemBrandName; 
	
	
	//생성자
	public DyPurchaseHistoryVo() {
		super();
	}

	public DyPurchaseHistoryVo(int purchaseNum, int itemNum, int userNum, String purchasedDate,
			String purchasedStatus) {
		super();
		this.purchaseNum = purchaseNum;
		this.itemNum = itemNum;
		this.userNum = userNum;
		this.purchasedDate = purchasedDate;
		this.purchasedStatus = purchasedStatus;
	}

	
	public int getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(int purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(String purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public String getPurchasedStatus() {
		return purchasedStatus;
	}

	public void setPurchasedStatus(String purchasedStatus) {
		this.purchasedStatus = purchasedStatus;
	}

	public int getItemCost() {
		return itemCost;
	}

	public void setItemCost(int itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemBrandName() {
		return itemBrandName;
	}

	public void setItemBrandName(String itemBrandName) {
		this.itemBrandName = itemBrandName;
	}

	@Override
	public String toString() {
		return "DyPurchaseHistoryVo [purchaseNum=" + purchaseNum + ", itemNum=" + itemNum + ", userNum=" + userNum
				+ ", purchasedDate=" + purchasedDate + ", purchasedStatus=" + purchasedStatus + ", itemCost=" + itemCost
				+ ", itemBrandName=" + itemBrandName + "]";
	}


}
