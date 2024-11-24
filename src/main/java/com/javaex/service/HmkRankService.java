// HmkRankService.java
package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.HmkRankDao;
import com.javaex.vo.HmkUserVo;

@Service
public class HmkRankService {
    @Autowired
    private HmkRankDao rankDao;

    public List<HmkUserVo> getTop10Ranks() {
        System.out.println("[Service] getTop10Ranks 호출됨");
        List<HmkUserVo> rankings = rankDao.selectTop10Ranks();
        System.out.println("[Service] getTop10Ranks 결과 개수: " + (rankings != null ? rankings.size() : "null"));
        System.out.println("[Service] getTop10Ranks 결과: " + rankings);
        return rankings;
    }

    public HmkUserVo getUserRank(int userNum) {
        System.out.println("[Service] getUserRank 호출됨 - userNum: " + userNum);
        HmkUserVo userRank = rankDao.selectUserRank(userNum);
        System.out.println("[Service] getUserRank 결과: " + userRank);
        return userRank;
    }
}