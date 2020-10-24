import java.util.Date;

public class Counters {
	Integer id;
	Integer type;
	String counter_name;
	Integer counter_value;
	Date date_of_change;
	Integer user_id;
	public Counters(Integer id, Integer type, String counter_name, Integer counter_value, Date date_of_change,
			Integer user_id) {
		super();
		this.id = id;
		this.type = type;
		this.counter_name = counter_name;
		this.counter_value = counter_value;
		this.date_of_change = date_of_change;
		this.user_id = user_id;
	}
	public Integer getId()  {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCounter_name() {
		return counter_name;
	}
	public void setCounter_name(String counter_name) {
		this.counter_name = counter_name;
	}
	public Integer getCounter_value() {
		return counter_value;
	}
	public void setCounter_value(Integer counter_value) {
		this.counter_value = counter_value;
	}
	public Date getDate_of_change() {
		return date_of_change;
	}
	public void setDate_of_change(Date date_of_change) {
		this.date_of_change = date_of_change;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
}
