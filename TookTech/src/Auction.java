import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auction {
	Integer id;		/// The auction's id
	Product product;	/// Product that is being auctioned
	List<User> user_list = new ArrayList<User>();     /// Only contains users that have participated in this auction
	List<BidEntry> entry_list = new ArrayList<BidEntry>();	  /// List of all the bids
	Date date;	 /// The date in which the auction takes/took place
	public Auction(Integer id, Product product, List<User> user_list, List<BidEntry> entry_list, Date date) {
		super();
		this.id = id;
		this.product = product;
		this.user_list = user_list;
		this.entry_list = entry_list;
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<User> getUser_list() {
		return user_list;
	}
	public void setUser_list(List<User> user_list) {
		this.user_list = user_list;
	}
	public List<BidEntry> getEntry_list() {
		return entry_list;
	}
	public void setEntry_list(List<BidEntry> entry_list) {
		this.entry_list = entry_list;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
