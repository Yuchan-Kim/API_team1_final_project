package com.javaex.vo;

public class RatesVo {
	private int userNum;
    private String usingProfilePic;
    private String userName;
    private int roomNum;
    private double achievementRate;
    
    private String date;
    private double percentage;
    private int approvedMissions;
    private int totalMissions;
    private int roomEnterPoint;

	public RatesVo() {
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getUsingProfilePic() {
		return usingProfilePic;
	}

	public void setUsingProfilePic(String usingProfilePic) {
		this.usingProfilePic = usingProfilePic;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public double getAchievementRate() {
		return achievementRate;
	}

	public void setAchievementRate(double achievementRate) {
		this.achievementRate = achievementRate;
	}
	public String getDate() {
	   return date;
	}

	public void setDate(String date) {
	    this.date = date;
	}

	public double getPercentage() {
	   return percentage;
	}

	public void setPercentage(double percentage) {
	    this.percentage = percentage;
	}

	public int getApprovedMissions() {
		return approvedMissions;
	}

	public void setApprovedMissions(int approvedMissions) {
		this.approvedMissions = approvedMissions;
	}

	public int getTotalMissions() {
		return totalMissions;
	}

	public void setTotalMissions(int totalMissions) {
		this.totalMissions = totalMissions;
	}

	public int getRoomEnterPoint() {
		return roomEnterPoint;
	}

	public void setRoomEnterPoint(int roomEnterPoint) {
		this.roomEnterPoint = roomEnterPoint;
	}

	@Override
	public String toString() {
		return "RatesVo [userNum=" + userNum + ", usingProfilePic=" + usingProfilePic + ", userName=" + userName
				+ ", roomNum=" + roomNum + ", achievementRate=" + achievementRate + ", date=" + date + ", percentage="
				+ percentage + ", approvedMissions=" + approvedMissions + ", totalMissions=" + totalMissions
				+ ", roomEnterPoint=" + roomEnterPoint + "]";
	}

		
	

	
    
}
