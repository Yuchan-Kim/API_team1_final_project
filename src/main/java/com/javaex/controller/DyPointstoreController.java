package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.DyPointstoreService;
import com.javaex.util.JsonResult;
import com.javaex.vo.DyCheckinVo;
import com.javaex.vo.DyItemVo;
import com.javaex.vo.DyPurchaseHistoryVo;

@RestController	
public class DyPointstoreController {

	@Autowired		
	private DyPointstoreService dyPointstoreService;
	
	
	/* 포인트상점 폼 */
	@GetMapping("/api/pointstores")
	public JsonResult pointstoreForm() {
		System.out.println("DyPointstoreController.pointstoreForm()");
		
		List<DyItemVo> itemList = dyPointstoreService.exeItemList();
		
		return JsonResult.success(itemList);
		
	}
	
	
	/* 포인트상점 상품디테일 */
//	@GetMapping("/api/pointstores/{itemNum}")
//	public JsonResult itemRead(@PathVariable(value="itemNum") int itemNum) {
//		System.out.println("DyPointstoreController.itemRead()");
//		System.out.println(itemNum);
//		
//		DyItemVo dyItemVo = dyPointstoreService.exeItemRead(itemNum);
//		
//		if(dyItemVo != null) {
//			return JsonResult.success(dyItemVo);
//		}else { 				//로그인 안됨
//			return JsonResult.fail("아이템 디테일 읽기실패");
//		}
//		
//	}
	
	/* 포인트상점 출석체크 */
   @PostMapping("/api/checkin")
    public JsonResult checkIn(@RequestBody DyCheckinVo dyCheckinVo) {
	   System.out.println("DyPointstoreController.checkIn()");

	    // 출석체크 처리
	    boolean success = dyPointstoreService.addCheckin(dyCheckinVo);

	    if (success) {
	        return JsonResult.success("출석체크가 완료되었습니다.");
	    } else {
	        return JsonResult.fail("출석체크 처리 중 오류가 발생하였습니다.");
	    }
	    
    }
	
	/* 아이템 교환 */
	@PostMapping("api/item/exchange")
	public JsonResult itemExchange(@RequestBody DyPurchaseHistoryVo dyPurchaseHistoryVo) {
		System.out.println("DyPointstoreController.itemExchange()");

		// 서비스 호출하여 영수증에 정보 추가
		int count = dyPointstoreService.exeItemExchange(dyPurchaseHistoryVo);

		if (count > 0) {
			return JsonResult.success("결제 완료.");
		} else {
			return JsonResult.fail("결제 실패.");
		}
	}
	
}
