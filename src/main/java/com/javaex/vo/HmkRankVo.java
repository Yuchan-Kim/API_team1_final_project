package com.javaex.vo;

public class HmkRankVo {
    private int userNum;             // 사용자 번호
    private String nickname;         // 사용자 닉네임
    private String image;           // 프로필 이미지 경로
    private int points;             // 누적 포인트
    private int user_rank;          // 현재 순위

    // Getters and Setters
    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getUser_rank() {
        return user_rank;
    }

    public void setUser_rank(int user_rank) {
        this.user_rank = user_rank;
    }

    @Override
    public String toString() {
        return "HmkRankVo [userNum=" + userNum + 
               ", nickname=" + nickname + 
               ", image=" + image + 
               ", points=" + points + 
               ", user_rank=" + user_rank + "]";
    }
}