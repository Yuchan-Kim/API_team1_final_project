package com.javaex.vo;

import org.springframework.web.multipart.MultipartFile;

public class RoomStepVo {
	
	private int roomNum;
	private int categoryNum; //운동0/독서1/스터디2/생활루틴3/취미4
	private int roomTypeNum; //일반0/챌린지1
	private int roomStatusNum; //모집전1/모집중2/활동3/완료4
	private int periodNum; //1주2주3주4주
	
	private int regionNum; //지역 
	private String regionName; //지역이름
	
	private String roomKeyword;
	private String roomTitle;
	private String roomInfo;
	private String roomThumbNail; //대표이미지 경로
	private String roomStartDate; //시작날짜
	private int roomMinNum; //최소 시작 인원
	private int roomMaxNum; //최대 시작 인원
	private int roomEnterPoint; //입장 포인트
	private int roomEnterRate; //입장 성실도 100점만점
	private String missionInstruction; //미션 유의 사항
	private int evaluationType; //평가방법 방장0/유저1
	
	private int missionNum;
	private int missionTypeNum; //미션 유형 번호
	private int roomDayNum; //방 요일 번호
	private String missionName;
	private String missionMethod; //미션 인증 방법
	private int missionGoal; //미션 목표치 (AI 그룹 챌린지 용)
	private String missionAI; //AI 챌린지용 미션 번호
	
	private String missionType; //미션 유형 이름
	private int missionPoint; //미션 추가 포인트
	
	private int missionImgNum; //미션 이미지 번호
	private String missionImgRoute; //미션 이미지 경로
	private String missionImgName; //미션 이미지 이름
	
	private String roomMaker; //방 생성자
	
	private MultipartFile imageUrl;
	private String imageName;
	private String imagePath;
	
	


	public RoomStepVo() {
		super();
	}
	public RoomStepVo(int roomNum, int categoryNum, int roomTypeNum, int roomStatusNum, int periodNum, int regionNum,
			String regionName, String roomKeyword, String roomTitle, String roomInfo, String roomThumbNail,
			String roomStartDate, int roomMinNum, int roomMaxNum, int roomEnterPoint, int roomEnterRate,
			String missionInstruction, int evaluationType, int missionNum, int missionTypeNum, int roomDayNum,
			String missionName, String missionMethod, int missionGoal, String missionAI, String missionType,
			int missionPoint, int missionImgNum, String missionImgRoute, String missionImgName, String roomMaker,
			MultipartFile imageUrl, String imageName, String imagePath) {
		super();
		this.roomNum = roomNum;
		this.categoryNum = categoryNum;
		this.roomTypeNum = roomTypeNum;
		this.roomStatusNum = roomStatusNum;
		this.periodNum = periodNum;
		this.regionNum = regionNum;
		this.regionName = regionName;
		this.roomKeyword = roomKeyword;
		this.roomTitle = roomTitle;
		this.roomInfo = roomInfo;
		this.roomThumbNail = roomThumbNail;
		this.roomStartDate = roomStartDate;
		this.roomMinNum = roomMinNum;
		this.roomMaxNum = roomMaxNum;
		this.roomEnterPoint = roomEnterPoint;
		this.roomEnterRate = roomEnterRate;
		this.missionInstruction = missionInstruction;
		this.evaluationType = evaluationType;
		this.missionNum = missionNum;
		this.missionTypeNum = missionTypeNum;
		this.roomDayNum = roomDayNum;
		this.missionName = missionName;
		this.missionMethod = missionMethod;
		this.missionGoal = missionGoal;
		this.missionAI = missionAI;
		this.missionType = missionType;
		this.missionPoint = missionPoint;
		this.missionImgNum = missionImgNum;
		this.missionImgRoute = missionImgRoute;
		this.missionImgName = missionImgName;
		this.roomMaker = roomMaker;
		this.imageUrl = imageUrl;
		this.imageName = imageName;
		this.imagePath = imagePath;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public int getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}
	public int getRoomTypeNum() {
		return roomTypeNum;
	}
	public void setRoomTypeNum(int roomTypeNum) {
		this.roomTypeNum = roomTypeNum;
	}
	public int getRoomStatusNum() {
		return roomStatusNum;
	}
	public void setRoomStatusNum(int roomStatusNum) {
		this.roomStatusNum = roomStatusNum;
	}
	public int getPeriodNum() {
		return periodNum;
	}
	public void setPeriodNum(int periodNum) {
		this.periodNum = periodNum;
	}
	public int getRegionNum() {
		return regionNum;
	}
	public void setRegionNum(int regionNum) {
		this.regionNum = regionNum;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getRoomKeyword() {
		return roomKeyword;
	}
	public void setRoomKeyword(String roomKeyword) {
		this.roomKeyword = roomKeyword;
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
	public String getRoomThumbNail() {
		return roomThumbNail;
	}
	public void setRoomThumbNail(String roomThumbNail) {
		this.roomThumbNail = roomThumbNail;
	}
	public String getRoomStartDate() {
		return roomStartDate;
	}
	public void setRoomStartDate(String roomStartDate) {
		this.roomStartDate = roomStartDate;
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
	public int getRoomEnterPoint() {
		return roomEnterPoint;
	}
	public void setRoomEnterPoint(int roomEnterPoint) {
		this.roomEnterPoint = roomEnterPoint;
	}
	public int getRoomEnterRate() {
		return roomEnterRate;
	}
	public void setRoomEnterRate(int roomEnterRate) {
		this.roomEnterRate = roomEnterRate;
	}
	public String getMissionInstruction() {
		return missionInstruction;
	}
	public void setMissionInstruction(String missionInstruction) {
		this.missionInstruction = missionInstruction;
	}
	public int getEvaluationType() {
		return evaluationType;
	}
	public void setEvaluationType(int evaluationType) {
		this.evaluationType = evaluationType;
	}
	public int getMissionNum() {
		return missionNum;
	}
	public void setMissionNum(int missionNum) {
		this.missionNum = missionNum;
	}
	public int getMissionTypeNum() {
		return missionTypeNum;
	}
	public void setMissionTypeNum(int missionTypeNum) {
		this.missionTypeNum = missionTypeNum;
	}
	public int getRoomDayNum() {
		return roomDayNum;
	}
	public void setRoomDayNum(int roomDayNum) {
		this.roomDayNum = roomDayNum;
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
	public int getMissionImgNum() {
		return missionImgNum;
	}
	public void setMissionImgNum(int missionImgNum) {
		this.missionImgNum = missionImgNum;
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
	public String getRoomMaker() {
		return roomMaker;
	}
	public void setRoomMaker(String roomMaker) {
		this.roomMaker = roomMaker;
	}
	public MultipartFile getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(MultipartFile imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "RoomStepVo [roomNum=" + roomNum + ", categoryNum=" + categoryNum + ", roomTypeNum=" + roomTypeNum
				+ ", roomStatusNum=" + roomStatusNum + ", periodNum=" + periodNum + ", regionNum=" + regionNum
				+ ", regionName=" + regionName + ", roomKeyword=" + roomKeyword + ", roomTitle=" + roomTitle
				+ ", roomInfo=" + roomInfo + ", roomThumbNail=" + roomThumbNail + ", roomStartDate=" + roomStartDate
				+ ", roomMinNum=" + roomMinNum + ", roomMaxNum=" + roomMaxNum + ", roomEnterPoint=" + roomEnterPoint
				+ ", roomEnterRate=" + roomEnterRate + ", missionInstruction=" + missionInstruction
				+ ", evaluationType=" + evaluationType + ", missionNum=" + missionNum + ", missionTypeNum="
				+ missionTypeNum + ", roomDayNum=" + roomDayNum + ", missionName=" + missionName + ", missionMethod="
				+ missionMethod + ", missionGoal=" + missionGoal + ", missionAI=" + missionAI + ", missionType="
				+ missionType + ", missionPoint=" + missionPoint + ", missionImgNum=" + missionImgNum
				+ ", missionImgRoute=" + missionImgRoute + ", missionImgName=" + missionImgName + ", roomMaker="
				+ roomMaker + ", imageUrl=" + imageUrl + ", imageName=" + imageName + ", imagePath=" + imagePath + "]";
	}
	
	
	
	
	


}
