
public class User {
	Integer id;
	String password;
	String username;
	Integer priviledges;    /// only enabled for admins
	public User(Integer id, String password, String username, Integer priviledges) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
		this.priviledges = priviledges;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getPriviledges() {
		return priviledges;
	}
	public void setPriviledges(Integer priviledges) {
		this.priviledges = priviledges;
	}
	
}
