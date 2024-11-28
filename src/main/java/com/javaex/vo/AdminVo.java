package com.javaex.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AdminVo {
    
	
	// ------------------- Categories -------------------
    private int categoryNum;
    private String categoryName;

    // ------------------- RoomType -------------------
    private int roomTypeNum;
    private String roomTypeName;

    // ------------------- Period -------------------
    private int periodNum;
    private int periodType;

    // ------------------- Regions -------------------
    private int regionNum;
    private String regionName;

    // ------------------- MissionType -------------------
    private int missionTypeNum;
    private String missionType;
    private int missionPoint;

    // ------------------- ItemBrand -------------------
    private int itemBrandNum;
    private String itemBrandName;

    // ------------------- PointPurpose -------------------
    private int pointPurposeNum;
    private String purposeName;

    // ------------------- EnteredUserStatus -------------------
    private int enteredUserStatusNum;
    private String enteredUserStatus;

    // ------------------- PointHistory -------------------
    private int historyNum;
    private int userNum;
    private int pointPurposeNumHistory; // Avoid naming conflict
    private String historyDate;
    private int historyPoint;
    private String historyInfo;

    // ------------------- PurchaseHistory -------------------
    private int purchaseNum;
    private int itemNum;
    private int userNumPurchase; // Avoid naming conflict
    private String purchasedDate;
    private String purchasedStatus;
    private String usedDate;

    // ------------------- RoomInfo -------------------
    private int roomNum;
    private int categoryNumRoomInfo; // Avoid naming conflict
    private int roomTypeNumRoomInfo; // Avoid naming conflict
    private int roomStatusNum;
    private int periodNumRoomInfo; // Avoid naming conflict
    private int regionNumRoomInfo; // Avoid naming conflict
    private String roomKeyword;
    private String roomTitle;
    private String roomDescription;
    private String roomThumbNail;
    private String roomStartDate;
    private int roomMinNum;
    private int roomMaxNum;
    private int roomEnterPoint;
    private int roomEnterRate;
    private String missionInstruction;
    private int evaluationType;
    private int roomMaker;
    private String userName;
    private String userEmail;
    private String socialLogin; 
    private String usingProfilePic;
    private int userStatus;
    private LocalDate joinDate;
    private int userCount;


    private String month;         
    private int sales;             
    private int roomCount;         
    private int categoryUserCount;
    private String itemName;      
    private int itemCost;         
    private int totalUsers;       
    private int totalSales;        
    private int totalOrders;      
    
   
    private String itemImg;

    private int id;
    private String noticeTitle;
    private String noticeMsg;
    private Object userNums;
    private int isCheck;
    private LocalDateTime createDate;
    private int msgSender;
    private String noticeType;
    
 // challenges 테이블
    private int missionNum;
    private int roomDayNum;
    private String missionName;
    private String missionMethod;
    private int missionGoal;
    private String missionAI;
    
    // missionImg 테이블
    private int missionImgNum;
    private String missionImgRoute;
    private String missionImgName;
    
    // enteredUser 테이블
    private int enteredUserNum;
  
    private int enteredUserAuth;
    
    // roomDay 테이블
    private int dateNum;
    
    // announcement 테이블
    private int announceNum;
    private String title;
    private String announcement;
    private LocalDateTime announceTime;
    private boolean isModified;
    private String address;
    private String placeTitle;
    private double latitude;
    private double longitude;
    
    // evaluation 테이블
    private int evalNum;
    private String evalType;
    private String evalDate;
    private LocalDate submitDate;
    private String submitComment;
    private int submitUser;
    private int evalUser;
    
    // evaluationImg 테이블
    private int evalImgNum;
    private String evalImgRoute;
    private String evalImgName;
    
    
    
    
   
 // getters and setters for fields
    
    
 

    public int getMissionNum() {
		return missionNum;
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



	public int getPeriodNum() {
		return periodNum;
	}



	public void setPeriodNum(int periodNum) {
		this.periodNum = periodNum;
	}



	public int getPeriodType() {
		return periodType;
	}



	public void setPeriodType(int periodType) {
		this.periodType = periodType;
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



	public int getPointPurposeNum() {
		return pointPurposeNum;
	}



	public void setPointPurposeNum(int pointPurposeNum) {
		this.pointPurposeNum = pointPurposeNum;
	}



	public String getPurposeName() {
		return purposeName;
	}



	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}



	public String getEnteredUserStatus() {
		return enteredUserStatus;
	}



	public void setEnteredUserStatus(String enteredUserStatus) {
		this.enteredUserStatus = enteredUserStatus;
	}



	public int getHistoryNum() {
		return historyNum;
	}



	public void setHistoryNum(int historyNum) {
		this.historyNum = historyNum;
	}



	public int getPointPurposeNumHistory() {
		return pointPurposeNumHistory;
	}



	public void setPointPurposeNumHistory(int pointPurposeNumHistory) {
		this.pointPurposeNumHistory = pointPurposeNumHistory;
	}



	public String getHistoryDate() {
		return historyDate;
	}



	public void setHistoryDate(String historyDate) {
		this.historyDate = historyDate;
	}



	public int getHistoryPoint() {
		return historyPoint;
	}



	public void setHistoryPoint(int historyPoint) {
		this.historyPoint = historyPoint;
	}



	public String getHistoryInfo() {
		return historyInfo;
	}



	public void setHistoryInfo(String historyInfo) {
		this.historyInfo = historyInfo;
	}



	public int getUserNumPurchase() {
		return userNumPurchase;
	}



	public void setUserNumPurchase(int userNumPurchase) {
		this.userNumPurchase = userNumPurchase;
	}



	public int getCategoryNumRoomInfo() {
		return categoryNumRoomInfo;
	}



	public void setCategoryNumRoomInfo(int categoryNumRoomInfo) {
		this.categoryNumRoomInfo = categoryNumRoomInfo;
	}



	public int getRoomTypeNumRoomInfo() {
		return roomTypeNumRoomInfo;
	}



	public void setRoomTypeNumRoomInfo(int roomTypeNumRoomInfo) {
		this.roomTypeNumRoomInfo = roomTypeNumRoomInfo;
	}



	public int getRoomStatusNum() {
		return roomStatusNum;
	}



	public void setRoomStatusNum(int roomStatusNum) {
		this.roomStatusNum = roomStatusNum;
	}



	public int getPeriodNumRoomInfo() {
		return periodNumRoomInfo;
	}



	public void setPeriodNumRoomInfo(int periodNumRoomInfo) {
		this.periodNumRoomInfo = periodNumRoomInfo;
	}



	public int getRegionNumRoomInfo() {
		return regionNumRoomInfo;
	}



	public void setRegionNumRoomInfo(int regionNumRoomInfo) {
		this.regionNumRoomInfo = regionNumRoomInfo;
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



	public String getRoomDescription() {
		return roomDescription;
	}



	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
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



	public int getRoomMaker() {
		return roomMaker;
	}



	public void setRoomMaker(int roomMaker) {
		this.roomMaker = roomMaker;
	}



	public int getPurchaseNum() {
		return purchaseNum;
	}



	public void setPurchaseNum(int purchaseNum) {
		this.purchaseNum = purchaseNum;
	}



	public String getUsedDate() {
		return usedDate;
	}



	public void setUsedDate(String usedDate) {
		this.usedDate = usedDate;
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



	public int getMissionImgNum() {
		return missionImgNum;
	}



	public void setMissionImgNum(int missionImgNum) {
		this.missionImgNum = missionImgNum;
	}



	public int getCategoryNum() {
		return categoryNum;
	}



	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
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



	public int getEnteredUserNum() {
		return enteredUserNum;
	}



	public void setEnteredUserNum(int enteredUserNum) {
		this.enteredUserNum = enteredUserNum;
	}



	public int getRoomNum() {
		return roomNum;
	}



	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}



	public int getEnteredUserStatusNum() {
		return enteredUserStatusNum;
	}



	public void setEnteredUserStatusNum(int enteredUserStatusNum) {
		this.enteredUserStatusNum = enteredUserStatusNum;
	}



	public int getEnteredUserAuth() {
		return enteredUserAuth;
	}



	public void setEnteredUserAuth(int enteredUserAuth) {
		this.enteredUserAuth = enteredUserAuth;
	}



	public int getDateNum() {
		return dateNum;
	}



	public void setDateNum(int dateNum) {
		this.dateNum = dateNum;
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



	public LocalDateTime getAnnounceTime() {
		return announceTime;
	}



	public void setAnnounceTime(LocalDateTime announceTime) {
		this.announceTime = announceTime;
	}



	public boolean isModified() {
		return isModified;
	}



	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getPlaceTitle() {
		return placeTitle;
	}



	public void setPlaceTitle(String placeTitle) {
		this.placeTitle = placeTitle;
	}



	public double getLatitude() {
		return latitude;
	}



	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



	public double getLongitude() {
		return longitude;
	}



	public void setLongitude(double longitude) {
		this.longitude = longitude;
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



	public String getEvalDate() {
		return evalDate;
	}



	public void setEvalDate(String evalDate) {
		this.evalDate = evalDate;
	}



	public LocalDate getSubmitDate() {
		return submitDate;
	}



	public void setSubmitDate(LocalDate submitDate) {
		this.submitDate = submitDate;
	}



	public String getSubmitComment() {
		return submitComment;
	}



	public void setSubmitComment(String submitComment) {
		this.submitComment = submitComment;
	}



	public int getSubmitUser() {
		return submitUser;
	}



	public void setSubmitUser(int submitUser) {
		this.submitUser = submitUser;
	}



	public int getEvalUser() {
		return evalUser;
	}



	public void setEvalUser(int evalUser) {
		this.evalUser = evalUser;
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



	public String getNoticeTitle() {
		return noticeTitle;
	}



	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}



	public String getNoticeMsg() {
		return noticeMsg;
	}



	public void setNoticeMsg(String noticeMsg) {
		this.noticeMsg = noticeMsg;
	}



	public int getIsCheck() {
		return isCheck;
	}



	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}



	public LocalDateTime getCreateDate() {
		return createDate;
	}



	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}



	public int getMsgSender() {
		return msgSender;
	}



	public void setMsgSender(int msgSender) {
		this.msgSender = msgSender;
	}



	public String getNoticeType() {
		return noticeType;
	}



	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}



	public Object getUserNums() {
        return userNums;
    }

    public void setUserNums(Object userNums) {
        this.userNums = userNums;
    }

    
    public AdminVo() {
    }
    
    
    
    
    public int getItemBrandNum() {
		return itemBrandNum;
	}


	public void setItemBrandNum(int itemBrandNum) {
		this.itemBrandNum = itemBrandNum;
	}


	public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemBrandName() {
        return itemBrandName;
    }

    public void setItemBrandName(String itemBrandName) {
        this.itemBrandName = itemBrandName;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
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


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getSocialLogin() {
		return socialLogin;
	}


	public void setSocialLogin(String socialLogin) {
		this.socialLogin = socialLogin;
	}


	public String getUsingProfilePic() {
		return usingProfilePic;
	}


	public void setUsingProfilePic(String usingProfilePic) {
		this.usingProfilePic = usingProfilePic;
	}


	public int getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}


	public LocalDate getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}


	public int getUserCount() {
		return userCount;
	}


	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}


	public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getCategoryUserCount() {
        return categoryUserCount;
    }

    public void setCategoryUserCount(int categoryUserCount) {
        this.categoryUserCount = categoryUserCount;
    }

    public String getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCost() {
        return itemCost;
    }

    public void setItemCost(int itemCost) {
        this.itemCost = itemCost;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }



	public String getPurchasedStatus() {
		return purchasedStatus;
	}



	public void setPurchasedStatus(String purchasedStatus) {
		this.purchasedStatus = purchasedStatus;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
}
