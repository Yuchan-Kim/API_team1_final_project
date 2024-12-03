// src/main/java/com/javaex/vo/ChallengesVo.java

package com.javaex.vo;

public class HmkChallengeVo {
	//필드
	private int roomNum;
	private String roomTitle;
	private String roomStartDate;
	private String endDate;
	private String roomThumbNail;
	private int roomStatusNum;
	// 미션 관련 필드
	private int missionNum;
	private String missionName;
	private String missionGoal;
	private String missionMethod;
	private String missionType;
	private int missionPoint;
	private int dayNumber;
	
	//생성자
	public HmkChallengeVo() {
		super();
	}
	
	public HmkChallengeVo(int roomNum, String roomTitle, String roomStartDate, String endDate, String roomThumbNail,
			int roomStatusNum) {
		super();
		this.roomNum = roomNum;
		this.roomTitle = roomTitle;
		this.roomStartDate = roomStartDate;
		this.endDate = endDate;
		this.roomThumbNail = roomThumbNail;
		this.roomStatusNum = roomStatusNum;
	}
	
	public HmkChallengeVo(int roomNum, String roomTitle, String roomStartDate, String endDate, String roomThumbNail,
			int roomStatusNum, int missionNum, String missionName, String missionGoal, String missionMethod,
			String missionType, int missionPoint, int dayNumber) {
		super();
		this.roomNum = roomNum;
		this.roomTitle = roomTitle;
		this.roomStartDate = roomStartDate;
		this.endDate = endDate;
		this.roomThumbNail = roomThumbNail;
		this.roomStatusNum = roomStatusNum;
		this.missionNum = missionNum;
		this.missionName = missionName;
		this.missionGoal = missionGoal;
		this.missionMethod = missionMethod;
		this.missionType = missionType;
		this.missionPoint = missionPoint;
		this.dayNumber = dayNumber;
	}

	// getter - setter
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getRoomTitle() {
		return roomTitle;
	}
	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}
	public String getRoomStartDate() {
		return roomStartDate;
	}
	public void setRoomStartDate(String roomStartDate) {
		this.roomStartDate = roomStartDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRoomThumbNail() {
		return roomThumbNail;
	}
	public void setRoomThumbNail(String roomThumbNail) {
		this.roomThumbNail = roomThumbNail;
	}
	
	public int getRoomStatusNum() {
		return roomStatusNum;
	}

	public void setRoomStatusNum(int roomStatusNum) {
		this.roomStatusNum = roomStatusNum;
	}

	public int getMissionNum() {
		return missionNum;
	}

	public void setMissionNum(int missionNum) {
		this.missionNum = missionNum;
	}

	public String getMissionName() {
		return missionName;
	}

	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}

	public String getMissionGoal() {
		return missionGoal;
	}

	public void setMissionGoal(String missionGoal) {
		this.missionGoal = missionGoal;
	}

	public String getMissionMethod() {
		return missionMethod;
	}

	public void setMissionMethod(String missionMethod) {
		this.missionMethod = missionMethod;
	}

	public String getMissionType() {
		return missionType;
	}

	public void setMissionType(String missionType) {
		this.missionType = missionType;
	}

	public int getMissionPoint() {
		return missionPoint;
	}

	public void setMissionPoint(int missionPoint) {
		this.missionPoint = missionPoint;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}
	//일반 메소드

	@Override
	public String toString() {
		return "HmkChallengeVo [roomNum=" + roomNum + ", roomTitle=" + roomTitle + ", roomStartDate=" + roomStartDate
				+ ", endDate=" + endDate + ", roomThumbNail=" + roomThumbNail + ", roomStatusNum=" + roomStatusNum
				+ ", missionNum=" + missionNum + ", missionName=" + missionName + ", missionGoal=" + missionGoal
				+ ", missionMethod=" + missionMethod + ", missionType=" + missionType + ", missionPoint=" + missionPoint
				+ ", dayNumber=" + dayNumber + "]";
	}
	
}
