
public class book {
	private int item_number;
	 private String book_title;
	 private String topic;
	 private double price = 0.0;
	 private int quantity=0;
	 
	public int getItem_number() {
		return item_number;
	}
	public void setItem_number(int item_number) {
		this.item_number = item_number;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public double getCost() {
		return price;
	}
	public void setCost(double cost) {
		this.price = cost;
	}
	public int getBook_count() {
		return quantity;
	}
	public void setBook_count(int book_count) {
		this.quantity = book_count;
	}
	 
	 
	 
	 
	 
}
