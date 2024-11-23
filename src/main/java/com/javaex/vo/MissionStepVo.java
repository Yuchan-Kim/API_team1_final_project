package com.javaex.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MissionStepVo {
	
	private int roomDayNum;      // 방요일 번호
	private int missionTypeNum;  // 미션유형번호 일반/최종
	
    private int id;              // 미션 ID
    private String title;        // 미션 제목
    private int imgCnt;
    private String method;       // 미션 method
    
    private String missionImgName; // 미션 이미지
    
    
    //private List<MultipartFile> imageFiles; // 미션에 관련된 이미지들
	
    
    public MissionStepVo() {
	}


	public MissionStepVo(int roomDayNum, int missionTypeNum, int id, String title, int imgCnt, String method,
			String missionImgName) {
		super();
		this.roomDayNum = roomDayNum;
		this.missionTypeNum = missionTypeNum;
		this.id = id;
		this.title = title;
		this.imgCnt = imgCnt;
		this.method = method;
		this.missionImgName = missionImgName;
	}


	public int getRoomDayNum() {
		return roomDayNum;
	}


	public void setRoomDayNum(int roomDayNum) {
		this.roomDayNum = roomDayNum;
	}


	public int getMissionTypeNum() {
		return missionTypeNum;
	}


	public void setMissionTypeNum(int missionTypeNum) {
		this.missionTypeNum = missionTypeNum;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getImgCnt() {
		return imgCnt;
	}


	public void setImgCnt(int imgCnt) {
		this.imgCnt = imgCnt;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public String getMissionImgName() {
		return missionImgName;
	}


	public void setMissionImgName(String missionImgName) {
		this.missionImgName = missionImgName;
	}


	@Override
	public String toString() {
		return "MissionStepVo [roomDayNum=" + roomDayNum + ", missionTypeNum=" + missionTypeNum + ", id=" + id
				+ ", title=" + title + ", imgCnt=" + imgCnt + ", method=" + method + ", missionImgName="
				+ missionImgName + "]";
	}
    

    
    
	

    
    
    
}
