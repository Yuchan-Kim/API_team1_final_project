package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.DyCheckinVo;
import com.javaex.vo.DyItemVo;
import com.javaex.vo.DyPointHistoryVo;
import com.javaex.vo.DyPurchaseHistoryVo;

@Repository 
public class DyPointstoreDao {
	
	@Autowired
	private SqlSession sqlSession;	


	/* 포인트상점 폼  */
	public List<DyItemVo> getItemList() {
		System.out.println("DyPointstoreDao.getItemList()");
		
		List<DyItemVo> itemList =sqlSession.selectList("dypointstore.selectList");
		
		//System.out.println(itemList);
		return itemList;	
	}
	
	
	/* 포인트상점 상품디테일 */
//	public DyItemVo getItemRead(int itemNum) {
//		System.out.println("DyPointstoreDao.getItemRead()");
//		
//		DyItemVo dyItemVo = sqlSession.selectOne("dypointstore.selectRead", itemNum);
//		
//		return dyItemVo;
//	}


	/* 포인트상점 출석체크 */
    public void insertCheckinAndPointHistory(DyCheckinVo dyCheckinVo) {
    	System.out.println("DyPointstoreDao.insertCheckin()");
        sqlSession.insert("dypointstore.insertCheckin", dyCheckinVo);
    }
    /* 출석체크 포인트 내역*/
    public void insertPointHistory(DyPointHistoryVo dyPointHistoryVo) {
        sqlSession.insert("dypointstore.insertPointHistory", dyPointHistoryVo);
    }
	/* 출석체크 중복체크 */
    public boolean isAlreadyCheckedIn(DyCheckinVo dyCheckinVo) {
        System.out.println("DyPointstoreDao.isAlreadyCheckedIn()");
        Integer count = sqlSession.selectOne("dypointstore.isAlreadyCheckedIn", dyCheckinVo);
        return count != null && count > 0;  // 이미 출석체크가 된 경우
    }
    

	/* 아이템 교환 */
    public int insertPurchaseHistory(DyPurchaseHistoryVo dyPurchaseHistoryVo) {
        System.out.println("DyPointstoreDao.insertPurchaseHistory()");
        
        return sqlSession.insert("dypointstore.insertPurchaseHistory", dyPurchaseHistoryVo);
    }

	/* 아이템 교환 포인트 내역 */
    public int insertPointHistory2(DyPointHistoryVo dyPointHistoryVo) {
        System.out.println("DyPointstoreDao.insertPointHistory()");

        // MyBatis를 사용하여 SQL 쿼리 실행
        return sqlSession.insert("dypointstore.insertPointHistory2", dyPointHistoryVo);
    }

	
}
