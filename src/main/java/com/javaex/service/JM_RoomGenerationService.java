package com.javaex.service;

import java.util.List;

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
	
	// 방 카테고리 키워드 업데이트
	public ChallengeVo exeRoomUpdateStep2(ChallengeVo  challengevo) {
			
		ChallengeVo newRoomCategory = dao.roomUpdateStep2(challengevo);
		System.out.println("새로운 카테고리+ 키워드"+ newRoomCategory);
			
		return newRoomCategory;
	}
	
	// 방 이미지, 제목, 설명 업데이트
	public ChallengeVo exeRoomUpdateStep3(ChallengeVo  challengevo) {
				
		ChallengeVo newRoomTitle = dao.roomUpdateStep3(challengevo);
		System.out.println("새로운 제목+설명+이미지"+ newRoomTitle);
				
		return newRoomTitle;
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
	
	// 방 기간+시작날짜 업데이트
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
            

}
