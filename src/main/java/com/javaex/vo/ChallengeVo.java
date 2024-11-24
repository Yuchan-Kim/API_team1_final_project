package com.javaex.vo;

import java.util.Date;

public class ChallengeVo {
	private String roomDescription;
	private int roomStatusNum; // 방 상태 번호 (1: 모집 중, 2: 챌린지 시작, 3: 챌린지 진행 중, 4: 챌린지 완료)
	private String roomKeyword;
	private boolean isJoined; // 사용자 참여 여부
	// 필터용
	private String order;			 // 오름,내림 차순
	private int enteredUserStatusNum;
	// 유저 정보
	private int userNum;             // 유저 번호 (고유 식별자)
	private String userName;         // 유저 이름
	private String userEmail;        // 유저 이메일
	private String userStatus;       // 유저 상태 (활성, 비활성 등)
	private int userAuth;         // 유저 권한 (관리자, 일반 사용자 등)
	private String regionName;       // 유저가 속한 지역 이름
	private int regionNum;       // 유저가 속한 지역 번호

	// 미션 정보
	private int missionNum;          // 미션 번호 (고유 식별자)
	private String missionName;      // 미션 이름
	private String missionMethod;    // 미션 수행 방법
	private int missionGoal;         // 미션 목표 (예: 걸음 수, 시간 등)
	private String missionAI;        // AI 도움 여부 (사용 여부)

	// 제출 정보
	private int submitUser;			 // 제출 유저 
	private String submitDate;       // 미션 제출 날짜
	private String submitComment;    // 제출 시 남긴 코멘트

	// 미션 이미지 정보
	private String missionImgRoute;  // 미션 이미지 경로 (저장된 위치)
	private String missionImgName;   // 미션 이미지 파일 이름

	// 평가 정보
	private int evalNum; 			 // 평가 번호
	private String evalType;         // 평가 유형 (예: 승인, 거절 등)
	private int evalUser;			 // 평가 유저
	private String evalDate;         // 평가 날짜

	// 평가 이미지 정보
	private int evalImgNum;   		 // 평가 이미지 번호
	private String evalImgRoute;     // 평가 이미지 경로 (저장된 위치)
	private String evalImgName;      // 평가 이미지 파일 이름

	// 방 정보
	private int roomNum;             // 방 번호 (고유 식별자)
	private String roomTitle;        // 방 제목
	private String roomInfo;         // 방 설명
	private int roomTypeNum;		  // 방 유형 번호 (1:일반, 2:챌린지)
	private String roomTypeName;     // 방 유형 이름 (예: 개인, 그룹 등)
	private String roomStatusName;   // 방 상태 이름 (예: 모집 중, 종료 등)
	private String categoryName;     // 방 카테고리 이름 (예: 운동, 공부 등)
	private int periodType;          // 방 기간 유형 (주 단위)
	private String roomStartDate;    // 방 시작 날짜
	private String missionInstruction; // 방 미션 유의사항
	private String roomThumbNail; 		// 방 대표이미지
	private int roomMinNum; 			// 방 최소인원
	private int roomMaxNum; 			// 방 최소인원
	private int roomPoint;				// 입장 포인트
	private int roomRate; 				// 입장 성실도


	// 공지사항 정보
	private int announceNum;         // 공지사항 번호 (고유 식별자)
	private String title;            // 공지사항 제목
	private String announcement;     // 공지 내용
	private int announceAddedBy;     // 공지 작성자 유저 번호
	private String announceAddedByName; // 공지 작성자 이름
	private Date announceTime;       // 공지 작성 시간
	private int categoryNum;



	public ChallengeVo() {

	}
	
	public int getCategoryNum() {
		return categoryNum;
	}



	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}
	
	public int getRegionNum() {
		return regionNum;
	}



	public void setRegionNum(int regionNum) {
		this.regionNum = regionNum;

	}



	public String getRoomDescription() {
		return roomDescription;
	}



	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}


	public int getEnteredUserStatusNum() {
		return enteredUserStatusNum;
	}

	public void setEnteredUserStatusNum(int enteredUserStatusNum) {
		this.enteredUserStatusNum = enteredUserStatusNum;
	}

	public int getRoomStatusNum() {
		return roomStatusNum;
	}



	public void setRoomStatusNum(int roomStatusNum) {
		this.roomStatusNum = roomStatusNum;
	}



	public String getRoomKeyword() {
		return roomKeyword;
	}



	public void setRoomKeyword(String roomKeyword) {
		this.roomKeyword = roomKeyword;
	}



	public boolean isJoined() {
		return isJoined;
	}



	public void setJoined(boolean isJoined) {
		this.isJoined = isJoined;
	}



	public String getOrder() {
		return order;
	}



	public void setOrder(String order) {
		this.order = order;
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



	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public String getUserStatus() {
		return userStatus;
	}



	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}



	public int getUserAuth() {
		return userAuth;
	}



	public void setUserAuth(int userAuth) {
		this.userAuth = userAuth;
	}



	public String getRegionName() {
		return regionName;
	}



	public void setRegionName(String regionName) {
		this.regionName = regionName;
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



	public String getMissionMethod() {
		return missionMethod;
	}



	public void setMissionMethod(String missionMethod) {
		this.missionMethod = missionMethod;
	}



	public int getMissionGoal() {
		return missionGoal;
	}



	public void setMissionGoal(int missionGoal) {
		this.missionGoal = missionGoal;
	}



	public String getMissionAI() {
		return missionAI;
	}



	public void setMissionAI(String missionAI) {
		this.missionAI = missionAI;
	}



	public int getSubmitUser() {
		return submitUser;
	}



	public void setSubmitUser(int submitUser) {
		this.submitUser = submitUser;
	}



	public String getSubmitDate() {
		return submitDate;
	}



	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}



	public String getSubmitComment() {
		return submitComment;
	}



	public void setSubmitComment(String submitComment) {
		this.submitComment = submitComment;
	}



	public String getMissionImgRoute() {
		return missionImgRoute;
	}



	public void setMissionImgRoute(String missionImgRoute) {
		this.missionImgRoute = missionImgRoute;
	}



	public String getMissionImgName() {
		return missionImgName;
	}



	public void setMissionImgName(String missionImgName) {
		this.missionImgName = missionImgName;
	}



	public int getEvalNum() {
		return evalNum;
	}



	public void setEvalNum(int evalNum) {
		this.evalNum = evalNum;
	}



	public String getEvalType() {
		return evalType;
	}



	public void setEvalType(String evalType) {
		this.evalType = evalType;
	}



	public int getEvalUser() {
		return evalUser;
	}



	public void setEvalUser(int evalUser) {
		this.evalUser = evalUser;
	}



	public String getEvalDate() {
		return evalDate;
	}



	public void setEvalDate(String evalDate) {
		this.evalDate = evalDate;
	}



	public int getEvalImgNum() {
		return evalImgNum;
	}



	public void setEvalImgNum(int evalImgNum) {
		this.evalImgNum = evalImgNum;
	}



	public String getEvalImgRoute() {
		return evalImgRoute;
	}



	public void setEvalImgRoute(String evalImgRoute) {
		this.evalImgRoute = evalImgRoute;
	}



	public String getEvalImgName() {
		return evalImgName;
	}



	public void setEvalImgName(String evalImgName) {
		this.evalImgName = evalImgName;
	}



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



	public String getRoomInfo() {
		return roomInfo;
	}



	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}

	public int getRoomTypeNum() {
		return roomTypeNum;
	}



	public void setRoomTypeNum(int roomTypeNum) {
		this.roomTypeNum = roomTypeNum;
	}



	public String getRoomTypeName() {
		return roomTypeName;
	}



	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}



	public String getRoomStatusName() {
		return roomStatusName;
	}



	public void setRoomStatusName(String roomStatusName) {
		this.roomStatusName = roomStatusName;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public int getPeriodType() {
		return periodType;
	}



	public void setPeriodType(int periodType) {
		this.periodType = periodType;
	}



	public String getRoomStartDate() {
		return roomStartDate;
	}



	public void setRoomStartDate(String roomStartDate) {
		this.roomStartDate = roomStartDate;
	}



	public String getMissionInstruction() {
		return missionInstruction;
	}



	public void setMissionInstruction(String missionInstruction) {
		this.missionInstruction = missionInstruction;
	}



	public int getAnnounceNum() {
		return announceNum;
	}



	public void setAnnounceNum(int announceNum) {
		this.announceNum = announceNum;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getAnnouncement() {
		return announcement;
	}



	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}



	public int getAnnounceAddedBy() {
		return announceAddedBy;
	}



	public void setAnnounceAddedBy(int announceAddedBy) {
		this.announceAddedBy = announceAddedBy;
	}



	public String getAnnounceAddedByName() {
		return announceAddedByName;
	}



	public void setAnnounceAddedByName(String announceAddedByName) {
		this.announceAddedByName = announceAddedByName;
	}



	public Date getAnnounceTime() {
		return announceTime;
	}



	public void setAnnounceTime(Date announceTime) {
		this.announceTime = announceTime;
	}

	public String getRoomThumbNail() {
		return roomThumbNail;
	}



	public void setRoomThumbNail(String roomThumbNail) {
		this.roomThumbNail = roomThumbNail;
	}



	public int getRoomMinNum() {
		return roomMinNum;
	}



	public void setRoomMinNum(int roomMinNum) {
		this.roomMinNum = roomMinNum;
	}



	public int getRoomMaxNum() {
		return roomMaxNum;
	}



	public void setRoomMaxNum(int roomMaxNum) {
		this.roomMaxNum = roomMaxNum;
	}



	public int getRoomPoint() {
		return roomPoint;
	}



	public void setRoomPoint(int roomPoint) {
		this.roomPoint = roomPoint;
	}



	public int getRoomRate() {
		return roomRate;
	}



	public void setRoomRate(int roomRate) {
		this.roomRate = roomRate;
	}



	@Override
	public String toString() {
		return "ChallengeVo [roomDescription=" + roomDescription + ", roomStatusNum=" + roomStatusNum + ", roomKeyword="
				+ roomKeyword + ", isJoined=" + isJoined + ", order=" + order + ", userNum=" + userNum + ", userName="
				+ userName + ", userEmail=" + userEmail + ", userStatus=" + userStatus + ", userAuth=" + userAuth
				+ ", regionName=" + regionName + ", missionNum=" + missionNum + ", missionName=" + missionName
				+ ", missionMethod=" + missionMethod + ", missionGoal=" + missionGoal + ", missionAI=" + missionAI
				+ ", submitUser=" + submitUser + ", submitDate=" + submitDate + ", submitComment=" + submitComment
				+ ", missionImgRoute=" + missionImgRoute + ", missionImgName=" + missionImgName + ", evalNum=" + evalNum
				+ ", evalType=" + evalType + ", evalUser=" + evalUser + ", evalDate=" + evalDate + ", evalImgNum="
				+ evalImgNum + ", evalImgRoute=" + evalImgRoute + ", evalImgName=" + evalImgName + ", roomNum="
				+ roomNum + ", roomTitle=" + roomTitle + ", roomInfo=" + roomInfo + ", roomTypeName=" + roomTypeName
				+ ", roomStatusName=" + roomStatusName + ", categoryName=" + categoryName + ", periodType=" + periodType
				+ ", roomStartDate=" + roomStartDate + ", missionInstruction=" + missionInstruction + ", announceNum="
				+ announceNum + ", title=" + title + ", announcement=" + announcement + ", announceAddedBy="
				+ announceAddedBy + ", announceAddedByName=" + announceAddedByName + ", announceTime=" + announceTime
				+ "]";
	}


}
