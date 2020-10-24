
public class Customer extends User{
	Integer balance;
	BidEntry[] b_list;   /// List of all the current/previous bids of the customer
	public Customer(Integer id, String password, String username, Integer priviledges, Integer balance,
			BidEntry[] b_list) {
		super(id, password, username, priviledges = 0);
		/// inherits all the User class stuff, but has priviledges disabled (whatever that means)
		this.balance = balance;
		this.b_list = b_list;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public BidEntry[] getB_list() {
		return b_list;
	}
	public void setB_list(BidEntry[] b_list) {
		this.b_list = b_list;
	}
	 
}
