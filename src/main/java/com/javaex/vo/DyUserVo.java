package com.javaex.vo;

public class DyUserVo {
	
	//필드
	private int userNum;
	private int regionNum;
	private String userEmail;
	private String userPw;
	private String userName;
	private String socialLogin;
	private String usingProfilePic;
	private String userStatus;
	
	
	
	//생성자
	public DyUserVo() {
		super();
	}

	public DyUserVo(int userNum, int regionNum, String userEmail, String userPw, String userName, String socialLogin,
			String usingProfilePic, String userStatus) {
		super();
		this.userNum = userNum;
		this.regionNum = regionNum;
		this.userEmail = userEmail;
		this.userPw = userPw;
		this.userName = userName;
		this.socialLogin = socialLogin;
		this.usingProfilePic = usingProfilePic;
		this.userStatus = userStatus;
	}

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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSocialLogin() {
		return socialLogin;
	}

	public void setSocialLogin(String socialLogin) {
		this.socialLogin = socialLogin;
	}

	public String getUsingProfilePic() {
		return usingProfilePic;
	}

	public void setUsingProfilePic(String usingProfilePic) {
		this.usingProfilePic = usingProfilePic;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	
	@Override
	public String toString() {
		return "DyUserVo [userNum=" + userNum + ", regionNum=" + regionNum + ", userEmail=" + userEmail + ", userPw="
				+ userPw + ", userName=" + userName + ", socialLogin=" + socialLogin + ", usingProfilePic="
				+ usingProfilePic + ", userStatus=" + userStatus + "]";
	}

	
}
