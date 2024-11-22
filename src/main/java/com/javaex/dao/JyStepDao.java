package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.MissionStepVo;
import com.javaex.vo.RoomStepVo;

@Repository
public class JyStepDao {

    @Autowired
    private SqlSession sqlSession;
    
    // 임시저장 체크
    public int checkRoom( int userNum ) {
        return sqlSession.selectOne("jyStep.checkRoom", userNum);
    }
    
    // 임시저장 불러오기
    public RoomStepVo getRoomInfo( int roomNum ) {
        return sqlSession.selectOne("jyStep.getRoomInfo", roomNum);
    }
    
    
    public int insertStep1 ( RoomStepVo roomStepVo ) {
    	System.out.println("dao"+roomStepVo);
        return sqlSession.insert("jyStep.insertRoomInfo", roomStepVo);
    }
    public int insertStep2 ( RoomStepVo roomStepVo ) {
    	System.out.println("dao"+roomStepVo);
    	
        return sqlSession.update("jyStep.updateStep2", roomStepVo);
    }
    public int insertStep3 ( RoomStepVo roomStepVo ) {
    	System.out.println("dao"+roomStepVo);
        return sqlSession.update("jyStep.updateStep3", roomStepVo);
    }
    public int insertStep4 ( RoomStepVo roomStepVo ) {
    	System.out.println("dao"+roomStepVo);
        return sqlSession.update("jyStep.updateStep4", roomStepVo);
    }
    public int insertStep44 ( RoomStepVo roomStepVo ) {
    	System.out.println("dao"+roomStepVo);
        return sqlSession.update("jyStep.updateStep44", roomStepVo);
    }
    public int insertStep5 ( RoomStepVo roomStepVo ) {
    	System.out.println("dao"+roomStepVo);
        return sqlSession.update("jyStep.updateStep5", roomStepVo);
    }
    
    // step6 미션유의사항 등록
    public int insertStep6MissionInstruction ( Map<String, Object> parameters ) {
    	return sqlSession.update("jyStep.insertStep6MissionInstruction", parameters);
    	
    }
    
    // step6 방요일번호 불러오기
    public int selectDay ( int roomNum ) {
    	return sqlSession.selectOne("jyStep.getRoomDayNum", roomNum); 
    }
    
    // 미션 집어 넣기
    public int insertStep6Mission ( MissionStepVo mission ) {
    	System.out.println("jyDao.mission - " + mission);
    	return sqlSession.insert("jyStep.insertStep6Mission", mission);
    }
    
    // 미션 이미지 집어 넣기
    public int insertStep6MissionImg ( MissionStepVo mission ) {
    	System.out.println("jyDao.missionImg - " + mission);
    	return sqlSession.insert("jyStep.insertStep6MissionImg", mission);
    }
    
    
    
    
//    평가 유형을 update
    public int insertStep7 ( RoomStepVo roomStepVo ) {
    	System.out.println("dao"+roomStepVo);
        return sqlSession.update("jyStep.updateStep7", roomStepVo);
    }
//    요일을 insert
    public int insertStep77 ( int roomNum, int dateNum ) {
    	System.out.println("dao roomNum"+roomNum);
    	System.out.println("dao dateNum"+dateNum);
    	
    	Map<String, Object> parameters = new HashMap<>();
    	parameters.put("roomNum", roomNum);
    	parameters.put("dateNum", dateNum);
    	
        return sqlSession.insert("jyStep.insertStep77", parameters);
    }


    
    // 지역 목록 불러오기
    public List<RoomStepVo> getResionList() {
        return sqlSession.selectList("jyStep.getResionList");
    }
    
    
    
}
