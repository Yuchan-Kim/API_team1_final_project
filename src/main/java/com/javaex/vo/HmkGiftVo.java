package com.javaex.vo;

public class HmkGiftVo {
	//필드
	private int id;
    private String name;
    private String image;
    private String description;
    private boolean isUsed;
    
    //생성자
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
	
	//일반 메소드
	@Override
	public String toString() {
		return "HmkGiftVo [id=" + id + ", name=" + name + ", image=" + image + ", description=" + description
				+ ", isUsed=" + isUsed + "]";
	}
	

}
