package object;

import java.util.Date;

public class Product {
	
	private int product_id;
	private int admin_id;
	private String product_name;
	private String category;
	private String explanation;
	private int quantity;
	private Date registered_time;
	private Date updated_time;
	
	
	public int getProductid() {
		return product_id;
	}
	public void setProductid(int product_id) {
		this.product_id = product_id;
	}
	public int getAdminid() {
		return admin_id;
	}
	public void setAdminid(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getProductName() {
		return product_name;
	}
	public void setProductName(String product_name) {
		this.product_name = product_name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getRegistered_time() {
		return registered_time;
	}
	public void setRegistered_time(Date registered_time) {
		this.registered_time = registered_time;
	}
	public Date getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}

}
