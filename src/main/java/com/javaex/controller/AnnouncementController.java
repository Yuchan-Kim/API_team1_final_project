package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.javaex.service.AnnouncementService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.util.NaverSearchResponse;
import com.javaex.vo.AnnouncementVo;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/challenge/announcement")
public class AnnouncementController {
    
    @Autowired
    private AnnouncementService announcementService;
    
    @Autowired
    private WebClient webClient;
    
    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

 // 방 참여 인원 체크
    @GetMapping("/{roomNum}")
    public JsonResult userChecker(@PathVariable int roomNum, HttpServletRequest request) {
        System.out.println("AnnouncementController.userChecker()");
        
        int userNum = JwtUtil.getNoFromHeader(request);
        
        List<Integer> enteredUserList = announcementService.getEnteredUserList(roomNum);
        
        if (enteredUserList.contains(userNum)) {
            return JsonResult.success(true);
        } else {
            return JsonResult.success(false);
        }
    }
    
    @GetMapping("/user/{roomNum}")
    public JsonResult checkUserAuthority(@PathVariable int roomNum, HttpServletRequest request) {
        System.out.println("AnnouncementController.checkUserAuthority()");
        
        int userNum = JwtUtil.getNoFromHeader(request);
        
        int userAuth = announcementService.getEnteredUserauth(roomNum, userNum);
        
        if (userAuth == 1 || userAuth == 2) {
            return JsonResult.success(userAuth);
        } else {
            return JsonResult.fail("오류 발생");
        }
        
    }
    
 // 공지사항 가져오기
    @GetMapping("/get/{roomNum}")
    public JsonResult getRoomAnnouncement(@PathVariable int roomNum) {
        System.out.println("AnnouncementController.getRoomAnnouncement()");
        List<AnnouncementVo> announcements = announcementService.getRoomAnnouncements(roomNum);
        return JsonResult.success(announcements);
    }
    
    // 공지사항 삭제
    @DeleteMapping("/delete/{noticeId}")
    public JsonResult deleteAnnouncement(@PathVariable int noticeId) {
        System.out.println("AnnouncementController.deleteAnnouncement()");
        announcementService.deleteAnnouncement(noticeId);
        return JsonResult.success("공지사항이 삭제되었습니다.");
    }
    
 // AnnouncementController.java
    @PutMapping("/edit/{editingNoticeId}")
    public JsonResult editAnnouncement(@PathVariable int editingNoticeId, @RequestBody AnnouncementVo announcementVo, HttpServletRequest request) {
        System.out.println("AnnouncementController.editAnnouncement()");
        int userNum = JwtUtil.getNoFromHeader(request);
        announcementVo.setAnnounceAddedBy(userNum);
        announcementVo.setAnnounceNum(editingNoticeId);
        announcementService.editAnnouncement(announcementVo);
        return JsonResult.success("공지사항이 수정되었습니다.");
    }

    @PostMapping("/addannounce")
    public JsonResult addAnnouncement(@RequestBody AnnouncementVo announcementVo, HttpServletRequest request) {
        System.out.println("AnnouncementController.addAnnouncement()");
        
        // JWT 토큰에서 사용자 번호 추출
        int userNum = JwtUtil.getNoFromHeader(request);
        announcementVo.setAnnounceAddedBy(userNum);
        // roomNum은 URL 파라미터나 announcementVo에서 이미 설정되었는지 확인
        announcementService.addAnnouncement(announcementVo);
        return JsonResult.success(announcementVo);
    }
    
 // src/main/java/com/javaex/controller/AnnouncementController.java

 // 네이버 지역 검색 API 프록시 엔드포인트
    @GetMapping("/search/local")
    public Mono<JsonResult> searchLocal(
            @RequestParam String query,
            @RequestParam(defaultValue = "5") int display) {
        System.out.println("AnnouncementController.searchLocal() called with query: " + query + ", display: " + display);
        
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("openapi.naver.com")
                        .path("/v1/search/local.json")
                        .queryParam("query", query)
                        .queryParam("display", display)
                        .build())
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .retrieve()
                .onStatus(status -> status.isError(), clientResponse -> {
                    // 에러 상태 코드에 따른 처리
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorBody -> {
                                System.err.println("Naver API Error Response: " + errorBody);
                                return Mono.error(new RuntimeException("Naver API Error: " + errorBody));
                            });
                })
                .bodyToMono(NaverSearchResponse.class)
                .map(response -> {
                    if (response.getItems() != null && !response.getItems().isEmpty()) {
                        return JsonResult.success(response);
                    } else {
                        return JsonResult.fail("검색 결과가 없습니다.");
                    }
                })
                .onErrorResume(e -> {
                    // 에러 로깅
                    System.err.println("Error during Naver API request: " + e.getMessage());
                    return Mono.just(JsonResult.fail("검색 중 오류가 발생했습니다."));
                });
    }

    
}

