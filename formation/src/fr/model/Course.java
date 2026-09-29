package fr.model;

public class Course {
	private int idCourse;
	private String name;
	private String description;
	private int duration;
	private boolean presentiel;
	private boolean distanciel;
	private double price;
	
	public Course(int idCourse, String name, String description, int duration, boolean presentiel, boolean distanciel,
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

	public Course(String name, String description, int duration, boolean presentiel, boolean distanciel,
			double price) {
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.presentiel = presentiel;
		this.distanciel = distanciel;
		this.price = price;
	}

	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isPresentiel() {
		return presentiel;
	}

	public void setPresentiel(boolean presentiel) {
		this.presentiel = presentiel;
	}

	public boolean isDistanciel() {
		return distanciel;
	}

	public void setDistanciel(boolean distanciel) {
		this.distanciel = distanciel;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Course [getIdCourse()=" + getIdCourse() + ", getName()=" + getName() + ", getDescription()="
				+ getDescription() + ", getDuration()=" + getDuration() + ", isPresentiel()=" + isPresentiel()
				+ ", isDistanciel()=" + isDistanciel() + ", getPrice()=" + getPrice() + "]";
	}
	
}
