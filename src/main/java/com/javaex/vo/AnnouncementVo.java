package com.javaex.vo;

import java.time.LocalDateTime;

// 작성자: 김유찬 
public class AnnouncementVo {
    private int announceNum;          // 공지사항 고유 번호 (PK)
    private int roomNum;              // 방 번호 (FK)
    private String place;             // 장소 정보 (NOT NULL)
    private String title;             // 공지사항 제목 (NOT NULL)
    private int announceAddedBy;      // 공지사항 추가한 사용자 번호 (FK)
    private String announcement;      // 공지사항 내용
    private LocalDateTime announceTime;   // 공지사항 추가 날짜 및 시간
    private int enteredUserNum;
    private int enteredUserStatusNum;
    private int userNum;
    private int enteredUserAuth;
    private boolean isModified;      // 공지사항 수정 여부
    
    // Constructor
    public AnnouncementVo() {
    }

    // Getters and Setters

    public int getAnnounceNum() {
        return announceNum;
    }

    public void setAnnounceNum(int announceNum) {
        this.announceNum = announceNum;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnnounceAddedBy() {
        return announceAddedBy;
    }

    public void setAnnounceAddedBy(int announceAddedBy) {
        this.announceAddedBy = announceAddedBy;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public LocalDateTime getAnnounceTime() {
        return announceTime;
    }

    public void setAnnounceTime(LocalDateTime announceTime) {
        this.announceTime = announceTime;
    }

    public int getEnteredUserNum() {
        return enteredUserNum;
    }

    public void setEnteredUserNum(int enteredUserNum) {
        this.enteredUserNum = enteredUserNum;
    }

    public int getEnteredUserStatusNum() {
        return enteredUserStatusNum;
    }

    public void setEnteredUserStatusNum(int enteredUserStatusNum) {
        this.enteredUserStatusNum = enteredUserStatusNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getEnteredUserAuth() {
        return enteredUserAuth;
    }

    public void setEnteredUserAuth(int enteredUserAuth) {
        this.enteredUserAuth = enteredUserAuth;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean isModified) {
        this.isModified = isModified;
    }

    @Override
    public String toString() {
        return "AnnouncementVo [announceNum=" + announceNum + ", roomNum=" + roomNum + ", place=" + place + ", title="
                + title + ", announceAddedBy=" + announceAddedBy + ", announcement=" + announcement + ", announceTime="
                + announceTime + ", enteredUserNum=" + enteredUserNum + ", enteredUserStatusNum=" + enteredUserStatusNum
                + ", userNum=" + userNum + ", enteredUserAuth=" + enteredUserAuth + ", isModified="
                + isModified + "]";
    }
}
