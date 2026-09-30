package fr.model;

/**
 * Représente une formation proposée.
 * 
 * Une formation posséde un identidfiant, un titre, une description,
 * une durée en jour, une ou plusieurs modalitées (distanciel, presnetiel),
 * et un prix en euri.
 * 
 * @author BinetA
 */

public class Course {
	private int idCourse;
	private String name;
	private String description;
	private int duration;
	private boolean presentiel;
	private boolean distanciel;
	private double price;
	
	
	/**
	 * Constructeur permettant de créer  une formation
	 * 
	 * @param idCourse identifiant de la formation
	 * @param name titre de la formation
	 * @param description description de la formation
	 * @param duration durée en jours de la formation
	 * @param presentiel indique si la formation est disponible en presentiel
	 * @param distanciel indique si la formation est disponible en distanciel
	 * @param price prix en euro de la formation
	 */
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
	
	/**
	 * Constructeur permettant de créer  une formation
	 * 
	 * @param name titre de la formation
	 * @param description description de la formation
	 * @param duration durée en jours de la formation
	 * @param presentiel indique si la formation est disponible en presentiel
	 * @param distanciel indique si la formation est disponible en distanciel
	 * @param price prix en euro de la formation
	 */
	public Course(String name, String description, int duration, boolean presentiel, boolean distanciel,
			double price) {
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.presentiel = presentiel;
		this.distanciel = distanciel;
		this.price = price;
	}
	
	/**
	 * Retourne l'identifiant de laa formation
	 * 
	 * @return identifiant de la formation
	 */
	public int getIdCourse() {
		return idCourse;
	}

	/**
	 * Modifie l'identifiant de la formation
	 * 
	 * @param idCourse nouvel identifiant
	 */
	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	/**
	 * Retourne le titre de la formation
	 * 
	 * @return titre de la formation
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le titre de la formation
	 * 
	 * @param name nouveau titre d ela formation
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retourne la description de la formation
	 * 
	 * @return la description de la formation
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Modifie la description de la formation
	 * @param description nouvelle description de la formation
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retourne le durée en jour de la formation
	 * 
	 * @return durée de la formation en jours
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Modifie la durée en jours de la formation
	 * 
	 * @param duration nouvelle durée en jours de la formation
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Indique si la formation est disponible en presentiel
	 * 
	 * @return true si la formation est en presentiel
	 */
	public boolean isPresentiel() {
		return presentiel;
	}

	/**
	 * 
	 * Modifie la disponibilitée en présnetiel
	 * 
	 * @param presentiel true si la formation est disponible en presnetiel
	 */
	public void setPresentiel(boolean presentiel) {
		this.presentiel = presentiel;
	}

	/**
	 * Indique si la formation est disponilbe en distanciel
	 * 
	 * @return true si la formation est disponible en distanciel
	 */
	public boolean isDistanciel() {
		return distanciel;
	}

	/**
	 * Modifie la disponibilité en distanciel de la formation
	 * 
	 * @param distanciel true si la formation est disponible en distanciel
	 */
	public void setDistanciel(boolean distanciel) {
		this.distanciel = distanciel;
	}

	/**
	 * Retourne le prix de la formation
	 * 
	 * @return prix de la formation
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Modifie le prix de la formation
	 * 
	 * @param price nouveau prix de la formation
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Retourne une représentation textuelle de la formation
	 * 
	 * @return information de la formation
	 */
	@Override
	public String toString() {
		return "Course " + getIdCourse() + "-> Titre :" + getName() + "/ Description :"
				+ getDescription() + "/ Durée : " + getDuration() + "/ Est en presentiel : " + isPresentiel()
				+ "/ Est en distanciel : " + isDistanciel() + "/ Prix" + getPrice();
	}
	
}
