
public class Product {
	Integer id;
	Integer start_price;
	String name;
	Integer state;	/// The state determines if the product is curently in the auction process
	Integer promo_active;  /// Self evident
	Integer promo_value;  /// Promotion value in % off
	public Product(Integer id, Integer start_price, String name, Integer state, Integer promo_active,
			Integer promo_value) {
		super();
		this.id = id;
		this.start_price = start_price;
		this.name = name;
		this.state = state;
		this.promo_active = promo_active;
		this.promo_value = promo_value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStart_price() {
		return start_price;
	}
	public void setStart_price(Integer start_price) {
		this.start_price = start_price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getPromo_active() {
		return promo_active;
	}
	public void setPromo_active(Integer promo_active) {
		this.promo_active = promo_active;
	}
	public Integer getPromo_value() {
		return promo_value;
	}
	public void setPromo_value(Integer promo_value) {
		this.promo_value = promo_value;
	}
	
}
