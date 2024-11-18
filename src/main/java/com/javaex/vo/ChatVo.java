// src/com/javaex/vo/ChatVo.java

package com.javaex.vo;

import java.util.Date;

public class ChatVo {
    private int chatNum;
    private int roomNum;
    private String chatContent;
    private Date chatTime;
    private int chatter;
    
    // 추가: 사용자 정보
    private int userNum;
    private String userName;
    private String usingProfilePic;
    
    // Getters and Setters

    public int getChatNum() {
        return chatNum;
    }

    public void setChatNum(int chatNum) {
        this.chatNum = chatNum;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public Date getChatTime() {
        return chatTime;
    }

    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }

    public int getChatter() {
        return chatter;
    }

    public void setChatter(int chatter) {
        this.chatter = chatter;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUsingProfilePic() {
        return usingProfilePic;
    }

    public void setUsingProfilePic(String usingProfilePic) {
        this.usingProfilePic = usingProfilePic;
    }

    @Override
    public String toString() {
        return "ChatVo [chatNum=" + chatNum + ", roomNum=" + roomNum + ", chatContent=" + chatContent
                + ", chatTime=" + chatTime + ", chatter=" + chatter + ", userNum=" + userNum + ", userName="
                + userName + ", usingProfilePic=" + usingProfilePic + "]";
    }
}
