package factory;

import java.math.BigDecimal;

public class Item {
    int id;
    int length;
    int quantity;
    float pieceWeight;
    
	public Item(int id, int length,int quantity,float pieceWeight ) {
		this.id = id;
		this.length = length;
		this.quantity = quantity;
		this.pieceWeight = pieceWeight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPieceWeight() {
		return pieceWeight;
	}

	public void setPieceWeight(int pieceWeight) {
		this.pieceWeight = pieceWeight;
	}
	
	

}
