package com.javaex.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaex.dao.JyStepDao;
import com.javaex.vo.MissionStepVo;
import com.javaex.vo.RoomStepVo;

@Service 
public class JyStepService {

    @Autowired
    private JyStepDao jyStepDao;
    

    @Autowired
    private S3Service s3Service; // S3 서비스 주입 - aws에 파일 저장을 위한

    @Value("${file.upload-dir}")  // application.properties 또는 application.yml에 설정된 파일 경로를 가져옴
    private String uploadDir;
    

    

    
    // 임시저장 체크
    public int checkRoom ( int userNum ) {
        return jyStepDao.checkRoom ( userNum );
    }
    
    // 임시저장 불러오기
    public RoomStepVo getRoomInfo ( int roomNum ) {
        return jyStepDao.getRoomInfo ( roomNum );
    }
    
    public int insertStep1 ( RoomStepVo roomStepVo ) {
    	System.out.println("service"+roomStepVo);
    	int count = jyStepDao.insertStep1(roomStepVo);
    	return count;
    	
//        return jyStepDao.insertStep1(roomStepVo);
    }
    
    public int insertStep2 ( RoomStepVo roomStepVo ) {
    	System.out.println("service"+roomStepVo);
    	int count = jyStepDao.insertStep2(roomStepVo);
    	return count;
    	
//        return jyStepDao.insertStep2(roomStepVo);
    }
    
    public int insertStep3 ( RoomStepVo roomStepVo, MultipartFile imageUrl ) throws IOException {

    	//System.out.println("service"+roomStepVo);
    	
        // 이미지와 음악 파일을 S3에 업로드
        String imageFileName = s3Service.uploadFile(imageUrl);
        
        System.out.println("imageFileName " + imageFileName);

        // 이미지와 음악 파일 저장 로컬
//        String imageFileName = saveFile(imageUrl);

        // roomStepVo 객체에 파일 이름과 경로 설정
//        roomStepVo.setImageName(imageFileName);
//        roomStepVo.setImagePath(uploadDir + File.separator + imageFileName);
//        roomStepVo.setRoomThumbNail(uploadDir + File.separator + imageFileName);
        roomStepVo.setRoomThumbNail(imageFileName);
        
        // DB에 음원 정보 저장
    	int count = jyStepDao.insertStep3(roomStepVo);
    	return count;
    	
//        return jyStepDao.insertStep3(roomStepVo);
    }
    
    public int insertStep4 ( RoomStepVo roomStepVo ) {
    	System.out.println("service"+roomStepVo);
    	int count = jyStepDao.insertStep4(roomStepVo);
    	return count;
    	
//        return jyStepDao.insertStep4(roomStepVo);
    }
    
    public int insertStep44 ( RoomStepVo roomStepVo ) {
    	System.out.println("service"+roomStepVo);
    	int count = jyStepDao.insertStep44(roomStepVo);
    	return count;
    	
//        return jyStepDao.insertStep44(roomStepVo);
    }
    
    public int insertStep5 ( RoomStepVo roomStepVo ) {
    	System.out.println("service"+roomStepVo);
    	int count = jyStepDao.insertStep5(roomStepVo);
    	return count;
    	
//        return jyStepDao.insertStep5(roomStepVo);
    }
    
    public int insertStep6 ( int roomNum, String missionInstruction, List<MissionStepVo> mission, List<MultipartFile> file) throws Exception {
    	
    	//#############
    	//미션 유의 사항 방에 등록
    	Map<String, Object> parameters = new HashMap<>();
    	parameters.put("roomNum", roomNum);
    	parameters.put("missionInstruction", missionInstruction);
    	
    	int count = jyStepDao.insertStep6MissionInstruction(parameters);
    	
    	
    	//#############
    	//방번호로 요일 조회하기
    	//int roomDayNum = jyStepDao.selectDay(roomNum);
    	int roomDayNum = 5;
    	
    	//#########조회한 요일 넘버로 미션 등록
    	
    	//Vo로 합치기
    	for ( int i = 0; i < mission.size() ; i++ ) {
    		
    		mission.get(i).setRoomDayNum(roomDayNum);
    		mission.get(i).setMissionTypeNum(1); //일반1/최종2
    		mission.get(i).setMissionImgName(s3Service.uploadFile(file.get(i)));
    		
    		
    		int count2 = jyStepDao.insertStep6Mission(mission.get(i));
    		jyStepDao.insertStep6MissionImg(mission.get(i));
    		if (count2 != 1) {
    			System.out.println("jyStepDao.insertStep6Mission(mission.get(i)) : 등록오류입니다");
    			break;
    		}

    	}
    	
    	return 0;
    	
//        return jyStepDao.insertStep5(roomStepVo);
    }
    
    public int insertStep7 ( RoomStepVo roomStepVo , List roomDayNum) {
    	System.out.println("service"+roomStepVo);
    	
    	//평가 유형 update
    	int count = jyStepDao.insertStep7(roomStepVo);
    	
    	if( roomDayNum.size() == 7) {
    		int sent = jyStepDao.insertStep77(roomStepVo.getRoomNum(), 8);
    	} else {
        	for ( int i = 0; i < roomDayNum.size(); i++) {
        		int sent = jyStepDao.insertStep77(roomStepVo.getRoomNum(), (int) roomDayNum.get(i));
        	}
    	}

    	
    	
    	return count;
    	
//        return jyStepDao.insertStep5(roomStepVo);
    }
    
    
    
    // 지역 목록 불러오기
    public List<RoomStepVo> getResionList (  ) {
        return jyStepDao.getResionList();
    }
    

     
    
    
    
    
    

}
