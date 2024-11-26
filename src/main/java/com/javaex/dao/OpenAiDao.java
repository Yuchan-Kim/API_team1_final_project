package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.javaex.vo.ChallengeVo;

@Repository
public class OpenAiDao {
	
	@Autowired
	private SqlSession session;
	
	// 키워드 가져오기
	public ChallengeVo getKeywords(int roomNum) {
		ChallengeVo keyword = session.selectOne("openAi.getKeywords",roomNum);
		return keyword;
	}
	
	// 최대인원 가져오기
	public ChallengeVo getMaxNum(int roomNum) {
		ChallengeVo maxNum = session.selectOne("openAi.getMaxNum",roomNum);
		return maxNum;
	}
		
	// 기간 가져오기
	public ChallengeVo getPeriodType(int roomNum) {
		ChallengeVo period = session.selectOne("openAi.getPeriodType",roomNum);
		return period;
	}
	
	// 미션리스트 가져오기
	public List<ChallengeVo> getMissions(int roomNum) {
		List<ChallengeVo> mission = session.selectList("openAi.getMissions",roomNum);
		System.out.println("미션리스트"+mission);
		return mission;
	}
	
	// 미션 등록
    public int insertMission(ChallengeVo challengevo) {
        int count = session.insert("generation.insertMission", challengevo);
        return count;
    }
	
	

}
