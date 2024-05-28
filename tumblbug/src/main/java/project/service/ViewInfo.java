package project.service;

import java.util.ArrayList;
import java.util.Date;

import file.model.File;
import project.model.DetailCategory;

public class ViewInfo {
	
	private DetailCategory detailCategory ;  /* 세부 카테고리 */ 
	private String pro_long ; /* 프로젝트 이름 */ 
	private ArrayList<File> imageFiles ; /* 대표 이미지 */ 
	private int paySum ;  /* 모인 금액 */ 
	private int ach_rate ; /* 달성률 */ 
	private int left_days ; /* 남은 기간 */
	private int pro_sup ; /* 후원자 수 */
	private int pro_price ; /* 펀딩 가격 */
	private Date pro_start ; /* 펀딩 시작일 */
	private Date pro_end  ; /* 펀딩 종료일 */
	private Date pay_date  ; /* 결제일 */
	
	// 생성자 
	public ViewInfo() {
		super();
	}

	public ViewInfo(DetailCategory detailCategory, String pro_long, ArrayList<File> imageFiles, int paySum, int ach_rate,
			int left_days, int pro_sup, int pro_price, Date pro_start, Date pro_end, Date pay_date) {
		super();
		this.detailCategory = detailCategory;
		this.pro_long = pro_long;
		this.imageFiles = imageFiles;
		this.paySum = paySum;
		this.ach_rate = ach_rate;
		this.left_days = left_days;
		this.pro_sup = pro_sup;
		this.pro_price = pro_price;
		this.pro_start = pro_start;
		this.pro_end = pro_end;
		this.pay_date = pay_date;
	}
	
	
	// getter, setter 
	public DetailCategory getDetailCategory() {
		return detailCategory;
	}

	public void setDetailCategory(DetailCategory detailCategory) {
		this.detailCategory = detailCategory;
	}

	public String getPro_long() {
		return pro_long;
	}

	public void setPro_long(String pro_long) {
		this.pro_long = pro_long;
	}

	public ArrayList<File> getProjectPhoto() {
		return imageFiles;
	}

	public void setProjectPhoto(ArrayList<File> imageFiles) {
		this.imageFiles = imageFiles;
	}

	public int getPaySum() {
		return paySum;
	}

	public void setPaySum(int paySum) {
		this.paySum = paySum;
	}

	public int getAch_rate() {
		return ach_rate;
	}

	public void setAch_rate(int ach_rate) {
		this.ach_rate = ach_rate;
	}

	public int getLeft_days() {
		return left_days;
	}

	public void setLeft_days(int left_days) {
		this.left_days = left_days;
	}

	public int getPro_sup() {
		return pro_sup;
	}

	public void setPro_sup(int pro_sup) {
		this.pro_sup = pro_sup;
	}

	public int getPro_price() {
		return pro_price;
	}

	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}

	public Date getPro_start() {
		return pro_start;
	}

	public void setPro_start(Date pro_start) {
		this.pro_start = pro_start;
	}

	public Date getPro_end() {
		return pro_end;
	}

	public void setPro_end(Date pro_end) {
		this.pro_end = pro_end;
	}

	public Date getPay_date() {
		return pay_date;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	} 
	

	// 메서드 
	
	
	
	

}
