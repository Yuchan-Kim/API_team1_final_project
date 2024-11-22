package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.JM_ChallengeDao;
import com.javaex.vo.ChallengeVo;

@Service
public class JM_ChallengeService {
	
	@Autowired
	private JM_ChallengeDao dao;
	
	
	// 방번호로 참여유저, 미션, 설명, 공지 리스트 가져오기
	public Map<String, Object> exeRoomMain(int roomNum) {
		System.out.println("ChallengeService.exeUserList");
		// 각 데이터를 가져옴
	    List<ChallengeVo> userList = dao.userList(roomNum);
	    List<ChallengeVo> missionList = dao.missionList2(roomNum);
	    ChallengeVo roomInfo = dao.roomInfo(roomNum);
	    ChallengeVo roomAnnoun = dao.roomAnnoun(roomNum);

	    // Map에 데이터 추가
	    Map<String, Object> roomDetails = new HashMap<>();
	    roomDetails.put("userList", userList);
	    roomDetails.put("missionList", missionList);
	    roomDetails.put("roomInfo", roomInfo);
	    roomDetails.put("roomAnnoun", roomAnnoun);

		return roomDetails;
	}
	
	// 방번호로 미션 리스트 가져오기 + 미션 제출여부 체크
	public List<ChallengeVo> exeMissionList(ChallengeVo challengevo) {
		System.out.println("ChallengeService.exeMissionList");
		// 모든 미션 리스트 가져오기
		List<ChallengeVo> missionList = dao.missionList(challengevo);
		System.out.println("ChallengeService" + missionList);

		return missionList;
	}
	
	
	// 방번호로 히스토리 리스트 가져오기
	public List<ChallengeVo> exeUserHistoryList(ChallengeVo challengevo) {
		System.out.println("ChallengeService.exeUserMissionList");
		System.out.println("히스토리 vo 체크"+challengevo);

		List<ChallengeVo> mHistoryList = dao.userMissionList(challengevo);
		System.out.println("ChallengeService" + mHistoryList);

		return mHistoryList;
	}
	
	// 방 평가타입 가져오기
	public int exeGetRoomEvalType(int  roomNum) {
	   int roomType = dao.getRoomEvalType(roomNum);
	   return roomType;
	}
	
	// 유저권한 가져오기
	public int exeGetUserAuth(ChallengeVo challengevo) {
        int userAuth = dao.getUserAuth(challengevo);
        return userAuth;
    }
	
	// 유의사항 업데이트
	public int exeRuleUpdate(ChallengeVo challengevo) {
        int rowsAffected = dao.ruleUpdate(challengevo);
        return rowsAffected;
    }
	
	// 유의사항 가져오기
	public ChallengeVo exeGetRule(int roomNum) {
		ChallengeVo roomRule = dao.getRule(roomNum);
		return roomRule;
	}
	
	
	/* 파일 저장 후 파일명 반환 */
	public String exeUpload(MultipartFile file) {
		System.out.println("AdminService.exeUpload()");

		// 파일 저장 경로 설정
		String saveDir;
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("linux")) {
			saveDir = "/app/upload";
			// saveDir = "/Users/yuchan/Desktop";
		} else {
			saveDir = "/Users/jiminpark/Desktop/upload";
		}

		// 오리지널 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("Original File Name: " + orgName);

		// 확장자 추출
		String exeName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("Extension: " + exeName);

		// 저장 파일명 (UUID로 중복 방지)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exeName;
		System.out.println("Save Name: " + saveName);

		// 파일 전체 경로 + 파일명
		String filePath = saveDir + File.separator + saveName;
		System.out.println("File Path: " + filePath);

		// 파일을 실제로 디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.write(fileData);
			bos.close();
		} catch (Exception e) {
			System.out.println("파일 저장 중 오류: " + e.getMessage());
			return null;
		}

		// 저장된 파일명 반환
		return saveName;
	}
	
	// 미션 제출
	public int exeAddMissionWithImages(ChallengeVo challengevo, List<MultipartFile> files) {
		// Step 1: 평가 테이블에 상품 정보 삽입 (자동 생성된 evalNum이 userVo에 설정됨)
		int evalNum = dao.insertMission(challengevo);
		System.out.println("@@evalNum@@"+evalNum);

		// Step 2: InfoImage 테이블에 파일 정보 삽입
		int fileCount = 0;
		for (int i = 0; i < files.size(); i++) {
			MultipartFile file = files.get(i);
			if (!file.isEmpty()) {
				// 파일 저장 후 저장된 파일명 반환
				String savedFileName = exeUpload(file);
				System.out.println("사진이름###"+savedFileName);

				if (savedFileName != null) {
					// unionVo에 저장된 파일명 및 이미지 순서 설정
					challengevo.setEvalImgName(savedFileName);
					dao.insertImageInfo(challengevo); // 이미지 정보 삽입
					fileCount++;
				}
			}
		}
		return fileCount; // 성공적으로 처리된 파일 수 반환
	}
	
	// 평가 업데이트
	public ChallengeVo exeUpdateEval(ChallengeVo challengevo) {
		System.out.println("ChallengeDao.updateEval");
		
		dao.updateEval(challengevo); 
		return  null;
	}

}
