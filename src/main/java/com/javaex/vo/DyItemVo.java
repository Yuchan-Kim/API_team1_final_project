package com.javaex.vo;

public class DyItemVo {
	
	//필드
	private int itemNum;
	private int itemBrandNum;
	private String itemBrandName;
	private int itemCost;
	private String itemName;
	private String itemImg;
	private int purchaseNum;
	
	
	//생성자
	public DyItemVo() {
		super();
	}

	public DyItemVo(int itemNum, int itemBrandNum, String itemBrandName, int itemCost, String itemName, String itemImg,
			int purchaseNum) {
		super();
		this.itemNum = itemNum;
		this.itemBrandNum = itemBrandNum;
		this.itemBrandName = itemBrandName;
		this.itemCost = itemCost;
		this.itemName = itemName;
		this.itemImg = itemImg;
		this.purchaseNum = purchaseNum;
	}


	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getItemBrandNum() {
		return itemBrandNum;
	}

	public void setItemBrandNum(int itemBrandNum) {
		this.itemBrandNum = itemBrandNum;
	}

	public String getItemBrandName() {
		return itemBrandName;
	}

	public void setItemBrandName(String itemBrandName) {
		this.itemBrandName = itemBrandName;
	}

	public int getItemCost() {
		return itemCost;
	}

	public void setItemCost(int itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemImg() {
		return itemImg;
	}

	public void setItemImg(String itemImg) {
		this.itemImg = itemImg;
	}


	public int getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(int purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	@Override
	public String toString() {
		return "DyItemVo [itemNum=" + itemNum + ", itemBrandNum=" + itemBrandNum + ", itemBrandName=" + itemBrandName
				+ ", itemCost=" + itemCost + ", itemName=" + itemName + ", itemImg=" + itemImg + ", purchaseNum="
				+ purchaseNum + "]";
	}


}
