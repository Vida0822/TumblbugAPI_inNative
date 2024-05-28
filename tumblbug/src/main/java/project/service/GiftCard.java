package project.service;

import java.util.ArrayList;

import project.model.Gift;
import project.model.ItemSet;

public class GiftCard {
	// 필드 
	
	private Gift gift ; /* 선물 정보 */
	private int buyAmount ; /* 구매 수량 */
	private int leftAmount ; /* 남은 수량 */ 
	private int gift_min ; /* 최소 갯수 */ 
	private String gift_desc ; /* 선물 설명 */ 
	private ArrayList<ItemSet> items ; /* 포함된 구성품 */ 

	// 생성자 
	public GiftCard() {
		super();
	}

	public GiftCard(Gift gift, int buyAmount, int leftAmount, int gift_min, String gift_desc,
			ArrayList<ItemSet> items) {
		super();
		this.gift = gift;
		this.buyAmount = buyAmount;
		this.leftAmount = leftAmount;
		this.gift_min = gift_min;
		this.gift_desc = gift_desc;
		this.items = items;
	}
	
	
	// getter, setter 
	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	public int getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(int buyAmount) {
		this.buyAmount = buyAmount;
	}

	public int getLeftAmount() {
		return leftAmount;
	}

	public void setLeftAmount(int leftAmount) {
		this.leftAmount = leftAmount;
	}

	public int getGift_min() {
		return gift_min;
	}

	public void setGift_min(int gift_min) {
		this.gift_min = gift_min;
	}

	public String getGift_desc() {
		return gift_desc;
	}

	public void setGift_desc(String gift_desc) {
		this.gift_desc = gift_desc;
	}

	public ArrayList<ItemSet> getItems() {
		return items;
	}

	public void setItems(ArrayList<ItemSet> items) {
		this.items = items;
	} 
	
	
	
	
	
	
}
