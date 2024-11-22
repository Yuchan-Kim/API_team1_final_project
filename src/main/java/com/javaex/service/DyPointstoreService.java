package com.javaex.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.DyPointstoreDao;
import com.javaex.vo.DyCheckinVo;
import com.javaex.vo.DyItemVo;
import com.javaex.vo.DyPointHistoryVo;
import com.javaex.vo.DyPurchaseHistoryVo;

@Service
public class DyPointstoreService {
	
	@Autowired
	private DyPointstoreDao dyPointstoreDao;
	

	/* 포인트상점 폼  */
	public List<DyItemVo> exeItemList() {
		System.out.println("DyPointstoreService.exeItemList()");
		
		List<DyItemVo> itemList = dyPointstoreDao.getItemList();
		
		//System.out.println(itemList);
		
		return itemList;
	}
	
	
	/* 포인트상점 상품디테일 */
//	public DyItemVo exeItemRead(int itemNum) {
//		System.out.println("DyPointstoreService.exeItemRead()");
//		
//		DyItemVo dyItemVo = dyPointstoreDao.getItemRead(itemNum);
//		
//		return dyItemVo;
//	}
	

	/* 포인트상점 출석체크 */
    public boolean addCheckin(DyCheckinVo dyCheckinVo) {
    	System.out.println("DyPointstoreService.addCheckin()");
    	
        // 오늘 날짜 확인
        String todayDate = LocalDate.now().toString();
        dyCheckinVo.setCheckinDate(todayDate);
        
        // 중복 체크
        boolean alreadyCheckedIn = dyPointstoreDao.isAlreadyCheckedIn(dyCheckinVo);
        if (alreadyCheckedIn) {
            return false;  // 이미 출석체크 완료
        }

        // 출석 체크 추가
        dyPointstoreDao.insertCheckinAndPointHistory(dyCheckinVo);
        
        // 포인트 이력 추가
        DyPointHistoryVo dyPointHistoryVo = new DyPointHistoryVo();
        dyPointHistoryVo.setUserNum(dyCheckinVo.getUserNum());
        dyPointHistoryVo.setHistoryDate(dyCheckinVo.getCheckinDate());
        dyPointHistoryVo.setPointPurposeNum(7);  // 출석에 해당하는 pointPurposeNum (예: 7번)
        dyPointHistoryVo.setHistoryPoint(50);    // 출석 포인트 (50점)
        dyPointHistoryVo.setHistoryInfo("+");    // 포인트 정보 ('+'는 포인트 증가)
        dyPointstoreDao.insertPointHistory(dyPointHistoryVo);
        
        return true;
    }
	
	
	/* 아이템 교환 */
    public int exeItemExchange(DyPurchaseHistoryVo dyPurchaseHistoryVo) {
    	System.out.println("DyPointstoreService.exeItemExchange()");
    	
        int count = dyPointstoreDao.insertPurchaseHistory(dyPurchaseHistoryVo);
        
        if (count > 0) {
            // 아이템 브랜드에 따라 포인트 기록 삽입
        	String pointPurpose = "4"; // 기본값은 4 (꾸미기가 아닌 경우)

        	if ("꾸미기".equals(dyPurchaseHistoryVo.getItemBrandName())) {
        	    pointPurpose = "5"; // "꾸미기"일 경우 5
        	}
            String historyInfo = "-"; // 교환 시 "-"로 처리
            int historyPoint = dyPurchaseHistoryVo.getItemCost(); // 상품의 가격을 포인트로 사용

            System.out.println("Item Brand Name: " + dyPurchaseHistoryVo.getItemBrandName()); 
            
            // 포인트 기록을 추가하는 insert 포인트 히스토리 메서드 호출
            DyPointHistoryVo pointHistoryVo = new DyPointHistoryVo();
            pointHistoryVo.setUserNum(dyPurchaseHistoryVo.getUserNum());
            pointHistoryVo.setPointPurposeNum(Integer.parseInt(pointPurpose));
            pointHistoryVo.setHistoryDate(LocalDate.now().toString());  // 현재 날짜
            pointHistoryVo.setHistoryPoint(dyPurchaseHistoryVo.getItemCost());
            pointHistoryVo.setHistoryInfo(historyInfo);

            // 포인트 이력 삽입
            dyPointstoreDao.insertPointHistory2(pointHistoryVo);
        }

        return count;
    }
	
	
}
