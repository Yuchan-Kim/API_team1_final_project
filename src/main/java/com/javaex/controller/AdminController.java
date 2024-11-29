package com.javaex.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AdminService;
import com.javaex.util.JsonResult;
import com.javaex.vo.AdminVo;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	private static final List<String> ALLOWED_TABLES = Arrays.asList(
	        "categories", "roomType", "period", "regions", "missionType", "pointPurpose"
	    );
	
	@GetMapping("/key-stats")
	public JsonResult getKeyStats() {
	    System.out.println("AdminController.getKeyStats()");
	    AdminVo keyStats = service.getKeyStats();
	    if (keyStats != null) {
	        return JsonResult.success(keyStats);
	    } else {
	        return JsonResult.fail("주요 통계 데이터를 불러오는 중 오류 발생");
	    }
	}
	@GetMapping("/userlist")
	public JsonResult getAllUserList() {
		System.out.println("AdminController.getAllUserList()");
		List<AdminVo> userList = service.getAllUserList();
		if (userList != null) {
			return JsonResult.success(userList);
		}else {
			return JsonResult.fail("유저 목록을 불러오는 중 오류 발생");
		}
	}
	
	
	@GetMapping("/signupusersrate")
	public JsonResult getSignUpRate() {
		System.out.println("AdminController.getSignUpRate()");
		List<AdminVo> signupRate = service.getSignUpRate();
		if (signupRate != null) {
			return JsonResult.success(signupRate);
		}else {
			return JsonResult.fail("가입율 분석 실패");
		}

	}
		
	@GetMapping("/sales")
	public JsonResult getSalesData() {
		System.out.println("AdminController.getSalesData()");
		List<AdminVo> salesData = service.getSalesData();
		if (salesData != null) {
			return JsonResult.success(salesData);
		}else {
			return JsonResult.fail("판매 데이터를 불러오는 중 오류 발생");
		}
	}
	
	@GetMapping("/category-distribution")
	public JsonResult getCategoryDistribution() {
		System.out.println("AdminController.getCategoryDistribution()");
		List<AdminVo> categoryDistribution = service.getCategoryDistribution();
		if (categoryDistribution != null) {
			return JsonResult.success(categoryDistribution);
		}else {
			return JsonResult.fail("카테고리 분포 데이터를 불러오는 중 오류 발생");
		}
	}
	
	@GetMapping("/category-performance")
	public JsonResult getCategoryPerformance() {
		System.out.println("AdminController.getCategoryPerformance()");
		List<AdminVo> categoryPerformance = service.getCategoryPerformance();
		if (categoryPerformance != null) {
			return JsonResult.success(categoryPerformance);
		}else {
			return JsonResult.fail("카테고리 퍼포먼스 데이터를 불러오는 중 오류 발생");
		}
	}
	
	@GetMapping("/recent-activities")
	public JsonResult getRecentActivities() {
		System.out.println("AdminController.getRecentActivities()");
		List<AdminVo> recentActivities = service.getRecentActivities();
		if (recentActivities != null) {
			return JsonResult.success(recentActivities);
		}else {
			return JsonResult.fail("최근 활동 데이터를 불러오는 중 오류 발생");
		}
	}
	
	@GetMapping("/items")
	public JsonResult getAllItems() {
	    System.out.println("AdminController.getAllItems()");
	    List<AdminVo> items = service.getAllItems();
	    if (items != null) {
	        return JsonResult.success(items);
	    } else {
	        return JsonResult.fail("상품 데이터를 불러오는 중 오류 발생");
	    }
	}
	
	@GetMapping("/iteminfo/{itemNum}")
	public JsonResult editItems(@PathVariable int itemNum ) {
	    System.out.println("AdminController.iteminfo()");
	    AdminVo item = service.getItemInfo(itemNum);
	    return JsonResult.success(item);
	}
	
	@GetMapping("/itembrands")
    public JsonResult getAllItemBrands() {
        System.out.println("AdminController.getAllItemBrands()");
        List<AdminVo> itemBrands = service.getItemBrands();
        if (itemBrands != null) {
            return JsonResult.success(itemBrands);
        } else {
            return JsonResult.fail("아이템 브랜드 목록을 불러오는 중 오류 발생");
        }
    }
	
	@PostMapping("/itembrands")
    public JsonResult addItemBrand(@RequestBody AdminVo adminVo) {
        System.out.println("AdminController.addItemBrand()");
        boolean success = service.addItemBrand(adminVo.getItemBrandName());
        if (success) {
            return JsonResult.success("아이템 브랜드가 성공적으로 추가되었습니다.");
        } else {
            return JsonResult.fail("아이템 브랜드 추가에 실패했습니다.");
       
        }
	}
	
	@PostMapping("/additems")
    public JsonResult addItem(
            @RequestParam("itemName") String itemName,
            @RequestParam("price") int price,
            @RequestParam("category") int itemBrandNum,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        System.out.println("AdminController.addItem()");
        String fileName ="";
//        try {
            String imagePath = null;
            if (image != null && !image.isEmpty()) { 
                // 이미지 저장 경로 설정 (예: /uploads/items/)
                String uploadDir = "app/upload/"; 
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // 파일 이름 설정 (중복 방지를 위해 UUID 사용 권장)
                fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                
                String filePath = uploadDir + fileName;
                
                
        		try {
        			byte[] fileData = image.getBytes();
        			OutputStream os = new FileOutputStream(filePath);
        			BufferedOutputStream bos = new BufferedOutputStream(os);
        			bos.write(fileData);
        			bos.close();
        		} catch (Exception e) {
        			System.out.println("파일 저장 중 오류: " + e.getMessage());
        			return null;
        		}
        		
//                
//                image.transferTo(new File(filePath));
//                imagePath = "/" + filePath; // 접근 가능한 경로로 설정
            }
            
            AdminVo itemVo = new AdminVo();
            itemVo.setItemName(itemName);
            itemVo.setItemCost(price);
            itemVo.setItemBrandNum(itemBrandNum);
            itemVo.setItemImg(fileName);
            
            
            boolean success = service.addItem(itemVo);
            if (success) {
                return JsonResult.success("상품이 성공적으로 추가되었습니다.");
            } else {
                return JsonResult.fail("상품 추가에 실패했습니다.");
            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return JsonResult.fail("이미지 업로드 중 오류가 발생했습니다.");
//        }
    }
	
	@PutMapping("/editItems/{itemNum}")
	public JsonResult updateItem(
	    @PathVariable int itemNum,
	    @RequestParam("itemName") String itemName,
	    @RequestParam("price") int price,
	    @RequestParam("category") int itemBrandNum,
	    @RequestParam(value = "image", required = false) MultipartFile image
	) {
	    System.out.println("AdminController.updateItem()");
	    try {
	        String imagePath = null;

	        if (image != null && !image.isEmpty()) {
	            // 이미지 저장 경로 설정
	            String uploadDir = "app/upload/";
	            File dir = new File(uploadDir);
	            if (!dir.exists()) {
	                dir.mkdirs();
	            }
	            // 파일 이름 설정
	            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
	            String filePath = uploadDir + fileName;
	            image.transferTo(new File(filePath));
	            imagePath = "/" + filePath; // 접근 가능한 경로로 설정
	        }

	        AdminVo itemVo = new AdminVo();
	        itemVo.setItemNum(itemNum);
	        itemVo.setItemName(itemName);
	        itemVo.setItemCost(price);
	        itemVo.setItemBrandNum(itemBrandNum);
	        itemVo.setItemImg(imagePath); // 새 이미지 경로 설정 (없으면 null)

	        boolean success = service.updateItem(itemVo);
	        if (success) {
	            return JsonResult.success("상품이 성공적으로 업데이트되었습니다.");
	        } else {
	            return JsonResult.fail("상품 업데이트에 실패했습니다.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return JsonResult.fail("이미지 업로드 중 오류가 발생했습니다.");
	    }
	}
	
	@DeleteMapping("/deleteItem/{itemNum}")
	public JsonResult deleteItem(@PathVariable int itemNum) {
	    System.out.println("AdminController.deleteItem()");
	    boolean success = service.deleteItem(itemNum);
	    if (success) {
	        return JsonResult.success("상품이 성공적으로 삭제되었습니다.");
	    } else {
	        return JsonResult.fail("상품 삭제에 실패했습니다.");
	    }
	}
	
	// 테이블 데이터 조회
    @GetMapping("/{tableName}")
    public JsonResult getTableData(@PathVariable String tableName) {
        System.out.println("AdminController.getTableData() - Table: " + tableName);
        if (!ALLOWED_TABLES.contains(tableName)) {
            return JsonResult.fail("허용되지 않은 테이블입니다.");
        }
        List<AdminVo> data = service.getTableData(tableName);
        if (data != null) {
            return JsonResult.success(data);
        } else {
            return JsonResult.fail(tableName + " 데이터를 불러오는 중 오류 발생");
        }
    }

    // 테이블 데이터 추가
    @PostMapping("/{tableName}")
    public JsonResult addTableData(@PathVariable String tableName, @RequestBody AdminVo adminVo) {
        System.out.println("AdminController.addTableData() - Table: " + tableName);
        if (!ALLOWED_TABLES.contains(tableName)) {
            return JsonResult.fail("허용되지 않은 테이블입니다.");
        }
        boolean success = service.addTableData(tableName, adminVo);
        if (success) {
            return JsonResult.success(tableName + " 데이터가 성공적으로 추가되었습니다.");
        } else {
            return JsonResult.fail(tableName + " 데이터 추가에 실패했습니다.");
        }
    }

    // 테이블 데이터 수정
    @PutMapping("/{tableName}/{id}")
    public JsonResult updateTableData(
        @PathVariable String tableName,
        @PathVariable int id,
        @RequestBody AdminVo adminVo
    ) {
        System.out.println("AdminController.updateTableData() - Table: " + tableName);
        if (!ALLOWED_TABLES.contains(tableName)) {
            return JsonResult.fail("허용되지 않은 테이블입니다.");
        }
        adminVo.setId(id); // ID 설정
        boolean success = service.updateTableData(tableName, adminVo);
        if (success) {
            return JsonResult.success(tableName + " 데이터가 성공적으로 수정되었습니다.");
        } else {
            return JsonResult.fail(tableName + " 데이터 수정에 실패했습니다.");
        }
    }

    // 테이블 데이터 삭제
    @DeleteMapping("/{tableName}/{id}")
    public JsonResult deleteTableData(@PathVariable String tableName, @PathVariable int id) {
        System.out.println("AdminController.deleteTableData() - Table: " + tableName);
        if (!ALLOWED_TABLES.contains(tableName)) {
            return JsonResult.fail("허용되지 않은 테이블입니다.");
        }
        boolean success = service.deleteTableData(tableName, id);
        if (success) {
            return JsonResult.success(tableName + " 데이터가 성공적으로 삭제되었습니다.");
        } else {
            return JsonResult.fail(tableName + " 데이터 삭제에 실패했습니다.");
        }
    }
    
 // 알림 발송
    @PostMapping("/send-notification")
    public JsonResult sendNotification(@RequestBody AdminVo request) {
        System.out.println("AdminController.sendNotification()");
        boolean success = service.sendNotification(request);
        if (success) {
            return JsonResult.success("알림이 성공적으로 발송되었습니다.");
        } else {
            return JsonResult.fail("알림 발송에 실패했습니다.");
        }
    }
	
	
}
