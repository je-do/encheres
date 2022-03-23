package fr.eni.enchere.dal.jdbc;

import java.util.Properties;

public class Settings {

	private static Properties properties;
	
	/*
	 * appel� 1 fois et 1 seule fois la premi�re fois que l'on appelle la classe
	 * soit par un attribut static
	 * soit par une fonction static
	 * soit par une instance
	 */
	static {
		try {
			properties = new Properties();
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getValeur(String cle) {
		return properties.getProperty(cle);
	}
}
