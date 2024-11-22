// src/main/java/com/javaex/vo/ChartDataVo.java

package com.javaex.vo;

public class HmkChartDataVo {
	//필드
	private String chartTitle;      // 차트 제목
    private int attendedCount;      // 수행/달성 수
    private int totalCount;         // 전체 수
    private String ratioDisplay;    // 비율 표시 (X/Y)
    private double percentage;      // 백분율
	
    /**
     * 차트에 표시할 데이터 포맷팅 
     */
    public String getDisplayValue() {
        return String.format("%.1f%%", percentage);
    }
    
    /**
     * 차트 타입 구분
     */
    public boolean isPerformanceType() {
        return chartTitle.contains("수행률");
    }
    
    public boolean isAchievementType() {
        return chartTitle.contains("달성률");
    }
    
    //생성자
    public HmkChartDataVo() {
		super();
	}

	public HmkChartDataVo(String chartTitle, int attendedCount, int totalCount, String ratioDisplay,
			double percentage) {
		super();
		this.chartTitle = chartTitle;
		this.attendedCount = attendedCount;
		this.totalCount = totalCount;
		this.ratioDisplay = ratioDisplay;
		this.percentage = percentage;
	}
	// getter - setter

	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}

	public int getAttendedCount() {
		return attendedCount;
	}

	public void setAttendedCount(int attendedCount) {
		this.attendedCount = attendedCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getRatioDisplay() {
		return ratioDisplay;
	}

	public void setRatioDisplay(String ratioDisplay) {
		this.ratioDisplay = ratioDisplay;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	// 일반메소드 

	@Override
	public String toString() {
		return "HmkChartDataVo [chartTitle=" + chartTitle + ", attendedCount=" + attendedCount + ", totalCount="
				+ totalCount + ", ratioDisplay=" + ratioDisplay + ", percentage=" + percentage + "]";
	}
	
}
