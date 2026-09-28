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
	
	
}
