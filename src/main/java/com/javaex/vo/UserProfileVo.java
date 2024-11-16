package com.javaex.vo;

public class UserProfileVo {
    private int userNum;
    private String userName;
    private String usingProfilePic;
    private double averageAchievementRate;
    private int pointRanking;
    private int activeChallenges;
    private int completedChallenges;
    private String location;
    private int reportCount;

    public UserProfileVo() {
    }

    // Getters and Setters
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

    public double getAverageAchievementRate() {
        return averageAchievementRate;
    }

    public void setAverageAchievementRate(double averageAchievementRate) {
        this.averageAchievementRate = averageAchievementRate;
    }

    public int getPointRanking() {
        return pointRanking;
    }

    public void setPointRanking(int pointRanking) {
        this.pointRanking = pointRanking;
    }

    public int getActiveChallenges() {
        return activeChallenges;
    }

    public void setActiveChallenges(int activeChallenges) {
        this.activeChallenges = activeChallenges;
    }

    public int getCompletedChallenges() {
        return completedChallenges;
    }

    public void setCompletedChallenges(int completedChallenges) {
        this.completedChallenges = completedChallenges;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    @Override
    public String toString() {
        return "UserProfileVo [userNum=" + userNum + ", userName=" + userName + ", usingProfilePic=" + usingProfilePic
                + ", averageAchievementRate=" + averageAchievementRate + ", pointRanking=" + pointRanking
                + ", activeChallenges=" + activeChallenges + ", completedChallenges=" + completedChallenges
                + ", location=" + location + ", reportCount=" + reportCount + "]";
    }
}
