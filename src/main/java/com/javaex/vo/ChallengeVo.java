package com.javaex.vo;

import java.util.Date;

public class ChallengeVo {
    private int roomNum;
    private String roomTitle;
    private String roomDescription;
    private Date roomStartDate;
    private int roomStatusNum; // 방 상태 번호 (1: 모집 중, 2: 챌린지 시작, 3: 챌린지 진행 중, 4: 챌린지 완료)
    private int periodType; // period 테이블에서 가져오는 기간 유형 (예: 7, 14, 21, 28일)
    private String roomTypeName;
    private String categoryName;
    private String roomKeyword;
    private String regionName;
    private boolean isJoined; // 사용자 참여 여부
    private int userAuth;      // 사용자 권한 상태
    
    // Getters and Setters

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
    public String getRoomDescription() {
        return roomDescription;
    }
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
    public Date getRoomStartDate() {
        return roomStartDate;
    }
    public void setRoomStartDate(Date roomStartDate) {
        this.roomStartDate = roomStartDate;
    }
    public int getRoomStatusNum() {
        return roomStatusNum;
    }
    public void setRoomStatusNum(int roomStatusNum) {
        this.roomStatusNum = roomStatusNum;
    }
    public int getPeriodType() {
        return periodType;
    }
    public void setPeriodType(int periodType) {
        this.periodType = periodType;
    }
    public String getRoomTypeName() {
        return roomTypeName;
    }
    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getRoomKeyword() {
        return roomKeyword;
    }
    public void setRoomKeyword(String roomKeyword) {
        this.roomKeyword = roomKeyword;
    }
    public String getRegionName() {
        return regionName;
    }
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    public boolean isJoined() {
        return isJoined;
    }
    public void setJoined(boolean isJoined) {
        this.isJoined = isJoined;
    }
    public int getUserAuth() {
        return userAuth;
    }
    public void setUserAuth(int userAuth) {
        this.userAuth = userAuth;
    }
}
