package fr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe abstraite définissant les opérations communes aux DAO de l'application
 * 
 * @param <T> type d'objet manipulé par le DAO
 * 
 * @author BinetA
 */
public abstract class Dao<T> {
	private static final String URL =
	        "jdbc:mariadb://127.0.0.1:3306/formation";

	private static final String USER = "abinet002";
	private static final String PASSWORD = "";
	
	protected Connection getconnection() throws SQLException{
		return DriverManager.getConnection(URL,USER,PASSWORD);
		
	}
	/**
	 * Récupére tous les objet présnet dans la table de la base de données
	 * 
	 * @return liste des objet
	 */
	public abstract List<T> getAll();
	
	/**
	 * Recherche un objet par son identifiant 
	 * 
	 * @param id identifiant d ela l'objet
	 * 
	 * @return objet trouvé sinon null
	 */
	public abstract T getById(int id);
	
	/**
	 * Ajoute un nouvel objet dans la table de la base de données
	 * 
	 * @param t objet a ajouter
	 * 
	 * @return l'identifiant de l'objet créé
	 */
	public abstract int create (T t);
	
	/**
	 * Met a jour les données de l'objet en basse de données
	 * 
	 * @param t objet avec les nouvelle valeur
	 * 
	 * @return true la la MAJ a reussi
	 */
	public abstract boolean update (T t);
	
	/**
	 * Supprime l'objet de la base de données
	 * 
	 * @param id identifiant de l'objet à supprimer
	 * 
	 * @return true la la supression est reussie
	 */
	public abstract boolean delete(int id);
	
}
