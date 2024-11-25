package com.javaex.vo;

public class HmkNoticeVo {
	// 알림 상세 필드
	private int noticeNum;
	private int userNum;
	private String noticeTitle;
	private String noticeMsg;
	private Boolean isCheck; // Boolean (참조 타입)으로 변경
	private String createDate;
	private int msgSender;
	private String senderNickname;

	// 생성자
	public HmkNoticeVo() {
		super();
	}

	public HmkNoticeVo(int noticeNum, int userNum, String noticeTitle, String noticeMsg, Boolean isCheck,
			String createDate, int msgSender, String senderNickname) {
		super();
		this.noticeNum = noticeNum;
		this.userNum = userNum;
		this.noticeTitle = noticeTitle;
		this.noticeMsg = noticeMsg;
		this.isCheck = isCheck;
		this.createDate = createDate;
		this.msgSender = msgSender;
		this.senderNickname = senderNickname;
	}
	
	//getter - setter

	public int getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
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

	public Boolean getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getMsgSender() {
		return msgSender;
	}

	public void setMsgSender(int msgSender) {
		this.msgSender = msgSender;
	}

	public String getSenderNickname() {
		return senderNickname;
	}

	public void setSenderNickname(String senderNickname) {
		this.senderNickname = senderNickname;
	}

	@Override
	public String toString() {
		return "HmkNoticeVo [noticeNum=" + noticeNum + ", userNum=" + userNum + ", noticeTitle=" + noticeTitle
				+ ", noticeMsg=" + noticeMsg + ", isCheck=" + isCheck + ", createDate=" + createDate + ", msgSender="
				+ msgSender + ", senderNickname=" + senderNickname + "]";
	}

}