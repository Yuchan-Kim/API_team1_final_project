package com.javaex.vo;

public class HmkSocialUserVo {
	//필드
	private int userNum;
	private int regionNum;
	private String userName;
	private String userEmail;
	private String socialLogin;
	//생성자
	public HmkSocialUserVo() {
		super();
	}
	public HmkSocialUserVo(int userNum, int regionNum, String userName, String userEmail, String socialLogin) {
		super();
		this.userNum = userNum;
		this.regionNum = regionNum;
		this.userName = userName;
		this.userEmail = userEmail;
		this.socialLogin = socialLogin;
	}
	// getter - setter
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public int getRegionNum() {
		return regionNum;
	}
	public void setRegionNum(int regionNum) {
		this.regionNum = regionNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getSocialLogin() {
		return socialLogin;
	}
	public void setSocialLogin(String socialLogin) {
		this.socialLogin = socialLogin;
	}
	// 일반 메소드
	@Override
	public String toString() {
		return "HmkNaverUserVo [userNum=" + userNum + ", regionNum=" + regionNum + ", userName=" + userName
				+ ", userEmail=" + userEmail + ", socialLogin=" + socialLogin + "]";
	}
	
}

