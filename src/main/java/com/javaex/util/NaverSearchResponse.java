// src/main/java/com/javaex/util/NaverSearchResponse.java
package com.javaex.util;

import java.util.List;

public class NaverSearchResponse {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items;

    // Getters and Setters

    public static class Item {
        private String title;
        private String link;
        private String category;
        private String description;
        private String telephone;
        private String address;
        private String roadAddress;
        private double mapx;
        private double mapy;

        // Getters and Setters
     // 모든 필드에 대한 Getter와 Setter 추가
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRoadAddress() {
            return roadAddress;
        }

        public void setRoadAddress(String roadAddress) {
            this.roadAddress = roadAddress;
        }

        public double getMapx() {
            return mapx;
        }

        public void setMapx(double mapx) {
            this.mapx = mapx;
        }

        public double getMapy() {
            return mapy;
        }

        public void setMapy(double mapy) {
            this.mapy = mapy;
        }
    }

	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "NaverSearchResponse [lastBuildDate=" + lastBuildDate + ", total=" + total + ", start=" + start
				+ ", display=" + display + ", items=" + items + "]";
	}
    
    

}
