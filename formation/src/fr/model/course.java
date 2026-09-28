package fr.model;

public class course {
	private int idCourse;
	private String name;
	private String description;
	private int duration;
	private boolean presentiel;
	private boolean distanciel;
	private double price;
	
	public course(int idCourse, String name, String description, int duration, boolean presentiel, boolean distanciel,
			double price) {
		super();
		this.idCourse = idCourse;
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.presentiel = presentiel;
		this.distanciel = distanciel;
		this.price = price;
	}
	
	
	
	
}
