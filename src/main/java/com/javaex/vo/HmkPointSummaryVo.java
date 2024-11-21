package com.javaex.vo;

public class HmkPointSummaryVo {
	// 필드
	private int pointsEarned;
    private int pointsSpent;
    private int totalPoints;
    // 생성자
	public HmkPointSummaryVo() {
		super();
	}
	public HmkPointSummaryVo(int pointsEarned, int pointsSpent, int totalPoints) {
		super();
		this.pointsEarned = pointsEarned;
		this.pointsSpent = pointsSpent;
		this.totalPoints = totalPoints;
	}
    // getter - setter
	public int getPointsEarned() {
		return pointsEarned;
	}
	public void setPointsEarned(int pointsEarned) {
		this.pointsEarned = pointsEarned;
	}
	public int getPointsSpent() {
		return pointsSpent;
	}
	public void setPointsSpent(int pointsSpent) {
		this.pointsSpent = pointsSpent;
	}
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	// 일반메소드
	@Override
	public String toString() {
		return "HmkPointSummaryVo [pointsEarned=" + pointsEarned + ", pointsSpent=" + pointsSpent + ", totalPoints="
				+ totalPoints + "]";
	}
	
}
