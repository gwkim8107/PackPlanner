package factory;

import java.math.BigDecimal;

public class Pack {
    private int number;
    private int id;
    private float currentWeight;
    private int currentItems;
    private int length;
    public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	private float maxWeight;
    private int maxItems;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pack(int number, int id , float currentWeight, int currentItems, int length) {
		this.number = number;
		this.id = id;
		this.currentWeight = currentWeight;
		this.currentItems = currentItems;
		this.length = length;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getCurrentWeight() {
		
		return currentWeight;
	}

	public void setCurrentWeight(float currentWeight) {
		this.currentWeight = currentWeight;
	}

	public int getCurrentItems() {
		return currentItems;
	}

	public void setCurrentItems(int currentItems) {
		this.currentItems = currentItems;
	}
	
	
	
}
