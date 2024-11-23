package com.javaex.vo;

public class HmkGiftVo {
	// 필드
	private int purchaseNum; // 추가
	private int id;
	private String name;
	private String image;
	private String purchasedStatus;
	private String description;
	private boolean isUsed;
	private String purchasedDate;
	private String usedDate;

	// 생성자
	public HmkGiftVo() {
		super();
	}

	public HmkGiftVo(int id, String name, String image, String description, boolean isUsed) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.isUsed = isUsed;
	}

	public HmkGiftVo(int purchaseNum, int id, String name, String image, String purchasedStatus, String description,
			boolean isUsed, String purchasedDate, String usedDate) {
		super();
		this.purchaseNum = purchaseNum;
		this.id = id;
		this.name = name;
		this.image = image;
		this.purchasedStatus = purchasedStatus;
		this.description = description;
		this.isUsed = isUsed;
		this.purchasedDate = purchasedDate;
		this.usedDate = usedDate;
	}
	// getter - setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public int getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(int purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public String getPurchasedStatus() {
		return purchasedStatus;
	}

	public void setPurchasedStatus(String purchasedStatus) {
		this.purchasedStatus = purchasedStatus;
	}

	public String getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(String purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public String getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(String usedDate) {
		this.usedDate = usedDate;
	}
	// 일반 메소드
	@Override
	public String toString() {
		return "HmkGiftVo [purchaseNum=" + purchaseNum + ", id=" + id + ", name=" + name + ", image=" + image
				+ ", purchasedStatus=" + purchasedStatus + ", description=" + description + ", isUsed=" + isUsed
				+ ", purchasedDate=" + purchasedDate + ", usedDate=" + usedDate + "]";
	}


	

}
