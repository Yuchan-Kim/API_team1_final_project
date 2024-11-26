package com.javaex.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.OpenAiService;
import com.javaex.util.JsonResult;
import com.javaex.vo.ChallengeVo;

@RestController
@RequestMapping("/api/genebang")
public class OpenAiController {

    @Autowired
    private OpenAiService openAiService;

    // 3. 방 번호로 챌린지 추천 생성하기
    @PostMapping("/generateChallenges/{roomNum}")
    public JsonResult generateChallenges(
    		@PathVariable int roomNum) {
        
    	List<ChallengeVo> challenges = openAiService.generateChallenges(roomNum);
    	System.out.println("컨트롤러 데이터 확인"+challenges);
        return JsonResult.success(challenges);
    }
//
//    // 4. 선택된 챌린지 저장하기
//    @PostMapping("/saveOpenAiMission")
//    public JsonResult saveSelectedChallenge(@RequestBody ChallengeVo challengevo) {
//   
//    	openAiService.saveSelectedChallenge(challengevo);
//            
//        return JsonResult.success("챌린지가 성공적으로 저장되었습니다.");
//    }
}
