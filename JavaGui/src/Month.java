public class Month {
	int nbOfDaysUsed;
	int nbOfUsers;
	public int users[] = {0,0,0,0,0,0,0,0,0};
	public Month(int nbOfDaysUsed, int nbOfUsers) {
		super();
		this.nbOfDaysUsed = nbOfDaysUsed;
		this.nbOfUsers = nbOfUsers;
	}
	public int getNbOfDaysUsed() {
		return nbOfDaysUsed;
	}
	public void setNbOfDaysUsed(int nbOfDaysUsed) {
		this.nbOfDaysUsed = nbOfDaysUsed;
	}
	public int getNbOfUsers() {
		return nbOfUsers;
	}
	public void setNbOfUsers(int nbOfUsers) {
		this.nbOfUsers = nbOfUsers;
	}
	public int[] getUsers() {
		return users;
	}
	public void setUsers(int[] users) {
		this.users = users;
	}
}
