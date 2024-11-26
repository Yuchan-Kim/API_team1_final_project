package com.javaex.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdminVo {
    
    // 기존 필드
    private int userNum;
    private String userName;
    private int regionNum;
    private String regionName;
    private String userEmail;
    private String socialLogin; 
    private String usingProfilePic;
    private int userStatus;
    private LocalDate joinDate;
    private int userCount;
    
    private String month;         
    private int sales;             
    private String categoryName;  
    private int roomCount;         
    private int categoryUserCount;
    private LocalDateTime purchasedDate; 
    private String itemName;      
    private int itemCost;         
    private int totalUsers;       
    private int totalSales;        
    private int totalOrders;      
    
    private int itemNum;
    private int itemBrandNum;
    private String itemBrandName;
    private String itemImg;

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

    public LocalDateTime getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(LocalDateTime purchasedDate) {
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
}
