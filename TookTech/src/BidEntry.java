
public class BidEntry {
	Integer entry_number;
	Integer product_id;
	Integer bidder_id;
	Integer price;
	Integer state; /// 0:In progress  1:Lost  2:Won
	public BidEntry(Integer entry_number, Integer product_id, Integer bidder_id, Integer price) {
		super();
		this.entry_number = entry_number;
		this.product_id = product_id;
		this.bidder_id = bidder_id;
		this.price = price;
	}
	public Integer getEntry_number() {
		return entry_number;
	}
	public void setEntry_number(Integer entry_number) {
		this.entry_number = entry_number;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getBidder_id() {
		return bidder_id;
	}
	public void setBidder_id(Integer bidder_id) {
		this.bidder_id = bidder_id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
