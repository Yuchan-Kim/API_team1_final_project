package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.JM_RoomGenerationDao;
import com.javaex.vo.ChallengeVo;

@Service
public class JM_RoomGenerationService {
	
	@Autowired
	private JM_RoomGenerationDao dao;
	
	// 유저넘버 받아서 방 생성 -> 방 번호 가져오기
	public int exeRoomUpdateStep1(ChallengeVo  challengevo) {
		int newRoomNum = dao.roomUpdateStep1(challengevo);
		System.out.println("새로생긴 방번호"+ newRoomNum);
		return newRoomNum;
	}
	
	// 방 번호로 방 삭제
	public boolean deleteRoom(int roomNum) {
        int deletedRows = dao.deleteRoom(roomNum);
        return deletedRows > 0; // 삭제된 행이 1개 이상인지 확인
    }
	
	// 방 카테고리 키워드 업데이트
	public ChallengeVo exeRoomUpdateStep2(ChallengeVo  challengevo) {
			
		ChallengeVo newRoomCategory = dao.roomUpdateStep2(challengevo);
		System.out.println("새로운 카테고리+ 키워드"+ newRoomCategory);
			
		return newRoomCategory;
	}
	
	// 방 이미지, 제목, 설명 업데이트
	public ChallengeVo exeRoomUpdateStep3(ChallengeVo challengevo, MultipartFile file) {
	    // 파일 업로드 처리
	    String savedFileName = exeUpload(file);

	    if (savedFileName != null) {
	        System.out.println("업로드된 파일명: " + savedFileName);
	        challengevo.setRoomThumbNail(savedFileName); // ChallengeVo에 업로드된 파일명 설정
	    } else {
	        System.out.println("파일 업로드 실패, 기본 이미지 유지");
	    }

	    // DB 업데이트
	    ChallengeVo updatedRoom = dao.roomUpdateStep3(challengevo);
	    System.out.println("업데이트된 방 정보: " + updatedRoom);

	    return updatedRoom;
	}
	
	// 방 타입넘버 가져오기
	public int getRoomType (int roomNum) {
		int roomTypeNum = dao.getRoomType(roomNum);
		return roomTypeNum;
	}
	
	// 보유 포인트 가져오기
	public int getUserPoint (int userNum) {
		int userPoint = dao.getUserPoint(userNum);
		return userPoint;
	}
	
	// 성실도 가져오기
	public double getUserScore (int userNum) {
		double userScore = dao.getUserScore(userNum);
		return userScore;
	}
	
	// 방 상세설정 업데이트
	public ChallengeVo exeRoomUpdateStep4(ChallengeVo  challengevo) {
					
		ChallengeVo newRoomTitle = dao.roomUpdateStep4(challengevo);
		System.out.println("새로운 제목+설명+이미지"+ newRoomTitle);
					
		return newRoomTitle;
	}
	
	// 방 기간+시작날짜 업데이트
	public ChallengeVo exeRoomUpdateStep5(ChallengeVo  challengevo) {
						
		ChallengeVo newRoomTitle = dao.roomUpdateStep5(challengevo);
		System.out.println("새로운 제목+설명+이미지"+ newRoomTitle);
						
		return newRoomTitle;
	}
	
	// 방 평가방법 + 요일 업데이트
	public ChallengeVo exeRoomUpdateStep7(ChallengeVo challengevo, List<Integer> roomDayNumList) {
	    // 1. roomInfo 테이블의 evaluationType 업데이트
	    dao.updateEvaluationType(challengevo);

	    // 2. roomDay 테이블에 roomNum과 roomDayNum 리스트 삽입
	    int insertedCount = 0; // 성공적으로 삽입된 row 수를 추적
	    for (Integer roomDayNum : roomDayNumList) {
	        if (roomDayNum != null) { // null 값 방지
	            ChallengeVo roomDayVo = new ChallengeVo();
	            roomDayVo.setRoomNum(challengevo.getRoomNum());
	            roomDayVo.setRoomDayNum(roomDayNum);
	            
	            dao.insertRoomDay(roomDayVo);
	            insertedCount++;
	        }
	    }
	    System.out.println("총 삽입된 요일 데이터 개수: " + insertedCount);
	    return challengevo;
	}
	
	// 유의사항 업데이트
	public ChallengeVo saveInstruction(ChallengeVo  challengevo) {
		System.out.println(challengevo.getRoomDayNum());
		System.out.println(challengevo.getMissionInstruction());
        
		ChallengeVo Instruction = dao.updateInstruction(challengevo);
		
		return Instruction;
    }
	
	/* 파일 저장 후 파일명 반환 */
	public String exeUpload(MultipartFile file) {
		System.out.println("AdminService.exeUpload()");

		// 파일 저장 경로 설정
		String saveDir;
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("linux")) {
			saveDir = "/app/upload";
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
	
	// 미션 및 이미지 생성 서비스
	public int registerMissions(ChallengeVo challengevo, List<MultipartFile> files) {
        // 1. 방 번호로 roomDayNum 리스트 가져오기
        List<ChallengeVo> roomDayList = dao.getRoomDayList(challengevo.getRoomNum());

        int conut = -1;
        // 2. roomDayNum 기준으로 미션 등록 및 이미지 등록
        for (int i = 0; i <roomDayList.size(); i++) {
        	
        	int roomDayNum = roomDayList.get(i).getRoomDayNum();
        	challengevo.setRoomDayNum(roomDayNum);
        	
        	// 미션 등록
            conut = dao.insertMission(challengevo); // missionNum 반환
            System.out.println(conut);

             // Step 2: InfoImage 테이블에 파일 정보 삽입
        		int fileCount = 0;
        		for (int a = 0; a < files.size(); a++) {
        			MultipartFile file = files.get(a);
        			if (!file.isEmpty()) {
        				// 파일 저장 후 저장된 파일명 반환
        				String savedFileName = exeUpload(file);
        				System.out.println("사진이름###"+savedFileName);

        				if (savedFileName != null) {
        					// unionVo에 저장된 파일명 및 이미지 순서 설정
        					challengevo.setMissionImgName(savedFileName);
        					dao.insertMissionImage(challengevo); // 이미지 정보 삽입
        					fileCount++;
        				}else {
        					System.err.println("파일 저장 실패: " + file.getOriginalFilename());
        				}
        			}
        		}
            }
        return conut;
	}
	
	// 방 참가 + 포인트 차감
	public ChallengeVo joinRoom(ChallengeVo challengevo) {
	    // 방 참가 시도
	    int joinCount = dao.joinRoom(challengevo);
	    System.out.println("joinCount 값: " + joinCount);

	    if (joinCount > 0) { // 참가 성공 여부 확인
	        System.out.println("방 참가 성공");

	        // 입장 포인트 가져오기
	        int roomPoint = dao.getRoomPoint(challengevo);

	        // 사용자 정보에 입장 포인트 설정
	        challengevo.setRoomPoint(roomPoint);

	        System.out.println("사용자 번호: " + challengevo.getUserNum());
	        System.out.println("방 입장 포인트: " + roomPoint);

	        // 포인트 차감
	        int updateCount = dao.minusPoint(challengevo);
	        if (updateCount > 0) { // 포인트 차감 성공 여부 확인
	            System.out.println("포인트 차감 성공");
	            return challengevo; // 최종 성공 시 업데이트된 정보 반환
	        } else {
	            System.out.println("포인트 차감 실패: 포인트가 부족할 수 있음");
	            // 필요시 참가 취소 롤백 로직
	            // dao.cancelJoinRoom(challengevo); // 예: 방 참가 취소 메서드 호출
	        }
	    } else {
	        System.out.println("방 참가 실패");
	    }

	    return null; // 참가 실패 또는 포인트 차감 실패 시 null 반환
	}


}
