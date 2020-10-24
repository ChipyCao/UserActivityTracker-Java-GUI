import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;


public class ApplicationManager {
	List<User> user_list = new ArrayList<User>();
	List<Product> product_list = new ArrayList<Product>();
	List<Auction> auction_list = new ArrayList<Auction>();
	
	
	/// Login system raw prototype ///
	int login(String imp_username,String imp_password) {
		/// This is really primitive but im not really down to implementing
		/// an entire login system. It really depends on where we implement it,
		/// plus we would probably use a database and verify the credentials through
		/// a query, not by using this dogpoop function :P	
		for(int i=0;i<=user_list.size();i++) 
			{
			if(user_list.get(i).getUsername().equals(imp_username) &
			   user_list.get(i).getPassword().equals(imp_password));
			return 1;
			}
		return 0;
		}
	 /*
	 Some other functions that could be useful for the login system:
	 	a. A 'sign up' system that afterwards adds 
	 	   your newly created account to the database
	 	b. A 'forgot password' system that lets you reset your password
	 	   through a confirmation e-mail
	 	   (Oh yeah! an e-mail would be pretty useful 
	 	    as a parameter for our User class aswell)
	  */

	
	/// Method for adding promotion to products by name ///
	void addPromotion(String product_name, Integer promo_value) {
		
		/// Filtering the products by the specified name and adding them to a list ///
		List<Product> result = product_list.stream()
		    .filter(a -> Objects.equals(a.name, product_name))      
		    .collect(Collectors.toList());	
		/// Adding the promotion to the products///
		for(int i=0; i<=result.size();i++)
			{
			int id=result.get(i).getId();
			product_list.get(id).setPromo_active(1);
			product_list.get(id).setPromo_value(promo_value);
			}
		}
	
	
	/// Method that lists auctions by date ///
	void listAuctions(Date date) {
		/// I know, I'm just comparing two date values, ///
		/// but this is just for demonstration purposes ///
		List<Auction> result = auction_list.stream()
			    .filter(a -> Objects.equals(a.date, date)) 
			    .collect(Collectors.toList());
		for(int i=0; i<=result.size();i++)
		{
			int id=result.get(i).getId();
			auction_list.get(id); 
        
			/// now that we have the auction id-s we can list them all 
			/*
	    	Code that displays the list of auctions
			 */
        
			/// When having to pick the top 3 bids, 
			/// we simply have to pick the last three 
			/// entries in the auction's entry_list	
			int n = auction_list.get(id).getEntry_list().size();
			for(int j=n; j>=n-2; j--)
				auction_list.get(id).getEntry_list().get(j);
				/*
      			Code that displays these entries
				 */
			}
		}
	
}
