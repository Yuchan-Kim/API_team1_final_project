package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaex.service.JyStepService;
import com.javaex.util.JsonResult;
import com.javaex.vo.MissionStepVo;
import com.javaex.vo.RoomStepVo;

@RestController
@RequestMapping("/api/genebang")
public class JyStepController {

	@Autowired
	private JyStepService jyStepService;
	
    
	
	// 임시저장 내용있나 체크하기
    @GetMapping("/checkroom/{userNum}")
    public JsonResult checkroom (
    		@PathVariable("userNum") int userNum ) {
    	
    	System.out.println("userNum" + userNum);
    	
    	int roomNum= jyStepService.checkRoom(userNum);
        System.out.println("roomNum" + roomNum);
        
        if ( roomNum == 0 ) {
            return JsonResult.fail("임시저장 내역이 없습니다.");
        }
        return JsonResult.success(roomNum);
    }
    
    // 임시저장 방의 정보 가져오기
	@GetMapping("/getRoomInfo/{no}")
	public JsonResult getRoomInfo ( @PathVariable(value = "userNum") int userNum ) {

		RoomStepVo roomStepVo = jyStepService.getRoomInfo(userNum);
		
		System.out.println(roomStepVo);

		if (roomStepVo == null) {
			return JsonResult.fail("read 오류");

		} else {
			return JsonResult.success(roomStepVo);
		}

	}
	
	
	// 1. 방생성 일반/챌린지 선택
	@PostMapping("/step1")
	public JsonResult insertStep1(
			@RequestParam("roomTypeNum") int roomTypeNum,
			@RequestParam("roomMaker") String roomMaker  ) {
		
		System.out.println("controller"+roomTypeNum);
		System.out.println("controller"+roomMaker);
		
		RoomStepVo roomStepVo = new RoomStepVo();
		
		roomStepVo.setRoomMaker(roomMaker); //방 생성자
		roomStepVo.setRoomTypeNum(roomTypeNum); //일반0/챌린지1
		//roomStepVo.setRoomStatusNum(0); //모집전0
		
		
		System.out.println("controller roomStepVo"+roomStepVo);
		
		int count = jyStepService.insertStep1(roomStepVo);
		
		if (count == 1) {
			return JsonResult.success("step1 등록 성공");
		} else {
			return JsonResult.fail("step1 등록 실패");
		}
		
	}	

	
	// 2. 카테고리, 키워드 설정
	@PostMapping("/step2")
	public JsonResult insertStep2(
			@RequestParam("roomNum") int roomNum,
			@RequestParam("categoryNum") int categoryNum,
			@RequestParam("roomKeyword") String roomKeyword ) {
		
		System.out.println("controller"+categoryNum);
		System.out.println("controller"+roomKeyword);
		
		RoomStepVo roomStepVo = new RoomStepVo();
		
		roomStepVo.setRoomNum(roomNum);
		roomStepVo.setCategoryNum(categoryNum);
		roomStepVo.setRoomKeyword(roomKeyword);
		
		int count = jyStepService.insertStep2(roomStepVo);
		if (count == 1) {
			return JsonResult.success("step2 등록 성공");
		} else {
			return JsonResult.fail("step2 등록 실패");
		}
		
	}
	
	// 3. 대표이미지, 방제목, 방설명 설정
	@PostMapping("/step3")
	public JsonResult insertStep3(
			@RequestParam("roomNum") int roomNum,
//			@RequestParam("roomThumbNail") String roomThumbNail,
			@RequestParam("roomTitle") String roomTitle,
			@RequestParam("roomInfo") String roomInfo,
			
			@RequestParam("roomThumbNail") MultipartFile imageUrl ) {
		
		System.out.println("controller imageUrl "+imageUrl);
		System.out.println("controller"+roomTitle);
		System.out.println("controller"+roomInfo);
		
		RoomStepVo roomStepVo = new RoomStepVo();
		
		roomStepVo.setRoomNum(roomNum);
//		roomStepVo.setRoomThumbNail(roomThumbNail);
		roomStepVo.setRoomTitle(roomTitle);
		roomStepVo.setRoomInfo(roomInfo);
		
		
		try {

			int count = jyStepService.insertStep3(roomStepVo, imageUrl);
			if (count == 1) {
				return JsonResult.success("step3 등록 성공");
			} else {
				return JsonResult.fail("step3 등록 실패");
			}

		} catch (IOException e) {
			e.printStackTrace();
			return JsonResult.fail("파일 저장 중 오류 발생: " + e.getMessage());
		}
		
		
	}
	
	// 4. 챌린지 - 참여인원, 배팅포인트, 성실도, 지역 설정
	@PostMapping("/step4")
	public JsonResult insertStep4(
			@RequestParam("roomNum") int roomNum,
			@RequestParam("roomMaxNum") int roomMaxNum,
			@RequestParam("roomMinNum") int roomMinNum,
			@RequestParam("roomEnterPoint") int roomEnterPoint,
			@RequestParam("roomEnterRate") int roomEnterRate,
			@RequestParam("regionNum") int regionNum   ) {
		
		System.out.println("controller"+roomMaxNum);
		System.out.println("controller"+roomMinNum);
		System.out.println("controller"+roomEnterPoint);
		System.out.println("controller"+roomEnterRate);
		System.out.println("controller"+regionNum);
		
		RoomStepVo roomStepVo = new RoomStepVo();
		
		roomStepVo.setRoomNum(roomNum);
		roomStepVo.setRoomMaxNum(roomMaxNum);
		roomStepVo.setRoomMinNum(roomMinNum);
		roomStepVo.setRoomEnterPoint(roomEnterPoint);
		roomStepVo.setRoomEnterRate(roomEnterRate);
		roomStepVo.setRegionNum(regionNum);
		
		int count = jyStepService.insertStep4(roomStepVo);
		if (count == 1) {
			return JsonResult.success("step4 등록 성공");
		} else {
			return JsonResult.fail("step4 등록 실패");
		}
		
	}
	
	// 4. 일반 - 참여인원, 배팅포인트, 성실도, 지역 설정
	@PostMapping("/step44")
	public JsonResult insertStep44(
			@RequestParam("roomNum") int roomNum,
			@RequestParam("roomMaxNum") int roomMaxNum,
			@RequestParam("roomMinNum") int roomMinNum,
			@RequestParam("regionNum") int regionNum   ) {
		
		System.out.println("controller"+roomMaxNum);
		System.out.println("controller"+roomMinNum);
		System.out.println("controller"+regionNum);
		
		RoomStepVo roomStepVo = new RoomStepVo();
		
		roomStepVo.setRoomNum(roomNum);
		roomStepVo.setRoomMaxNum(roomMaxNum);
		roomStepVo.setRoomMinNum(roomMinNum);
		roomStepVo.setRegionNum(regionNum);
		
		int count = jyStepService.insertStep44(roomStepVo);
		if (count == 1) {
			return JsonResult.success("step44 등록 성공");
		} else {
			return JsonResult.fail("step44 등록 실패");
		}
		
	}
	
	// 5. 시작날짜, 기간 설정
	@PostMapping("/step5")
	public JsonResult insertStep5(
			
			@RequestParam("roomNum") int roomNum,
			@RequestParam("roomStartDate") String roomStartDate,
			@RequestParam("periodNum") int periodNum  ) {
		
		System.out.println("controller"+roomStartDate);
		System.out.println("controller"+periodNum);
		
		RoomStepVo roomStepVo = new RoomStepVo();
		
		roomStepVo.setRoomNum(roomNum);
		roomStepVo.setRoomStartDate(roomStartDate);
		roomStepVo.setPeriodNum(periodNum);
		
		int count = jyStepService.insertStep5(roomStepVo);
		if (count == 1) {
			return JsonResult.success("step5 등록 성공");
		} else {
			return JsonResult.fail("step5 등록 실패");
		}
		
	}
	
	// 6. 미션 받아오기
	@PostMapping("/step6")
    public void insertStep6(
    		
        @RequestParam("roomNum") int roomNum,
        @RequestParam("missionInstruction") String missionInstruction,
        @RequestPart("data") String dataJson,
        @RequestPart List<MultipartFile> file // 이미지 파일들
        
    ) throws Exception {

        System.out.println("Room Number: " + roomNum);
        System.out.println("Mission Instruction: " + missionInstruction);  
        
        // ObjectMapper로 JSON 데이터를 파싱해서 MissionStepVo 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        List<MissionStepVo> missionSteps = objectMapper.readValue(dataJson, objectMapper.getTypeFactory().constructCollectionType(List.class, MissionStepVo.class));

        // 미션 데이터 출력
        for (MissionStepVo mission : missionSteps) {
            System.out.println("Mission ID: " + mission.getId());
            System.out.println("Mission Title: " + mission.getTitle());
            System.out.println("Mission imgCnt: " + mission.getImgCnt());
            System.out.println("Mission method: " + mission.getMethod());
        }

        // 이미지 파일 처리
//        for (MultipartFile uploadedFile : file) {
//            System.out.println("Received file: " + uploadedFile.getOriginalFilename());
//        }
        
    
        int count = jyStepService.insertStep6(roomNum, missionInstruction, missionSteps, file);
        
//        return "success";
    }

	
	// 7. 평가방법, 요일 설정
	@PostMapping("/step7")
	public JsonResult insertStep7(
			@RequestParam("roomNum") int roomNum,
			@RequestParam("evaluationType") int evaluationType,
			@RequestParam("roomDayNum") List roomDayNum  ) {
		
		System.out.println("controller"+evaluationType);
		System.out.println("controller"+roomDayNum);
		
		RoomStepVo roomStepVo = new RoomStepVo();
		
		roomStepVo.setRoomNum(roomNum);
		roomStepVo.setEvaluationType(evaluationType);
		
		int count = jyStepService.insertStep7(roomStepVo, roomDayNum);
		if (count == 1) {
			return JsonResult.success("step7 등록 성공");
		} else {
			return JsonResult.fail("step7 등록 실패");
		}
		
	}
	
	// 지역 목록 불러오기
    @GetMapping("/regions")
    public JsonResult getResionList() {
        List<RoomStepVo> resionList = jyStepService.getResionList();
        System.out.println("resionList"  + resionList);
        if (resionList == null || resionList.isEmpty()) {
            return JsonResult.fail("지역 목록이 없습니다.");
        }
        return JsonResult.success(resionList);
    }

	
	
	
	

}
