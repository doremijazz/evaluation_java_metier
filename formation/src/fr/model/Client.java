package fr.model;

public class Client {
	private int idClient;
	private String first_name;
	private String last_name;
	private String email;
	private Adress adress;
	private String tel;
	
	public Client(int idClient, String first_name, String last_name, String email, Adress adress, String tel) {
		super();
		this.idClient = idClient;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.adress = adress;
		this.tel = tel;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
