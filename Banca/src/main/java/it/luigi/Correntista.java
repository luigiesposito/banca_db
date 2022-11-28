package it.luigi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Correntista {

	String nome;
	String cognome;
	String cf;

	Correntista(String nome, String cognome, String cf) {
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
	}

	Scanner scanner = new Scanner(System.in);

	static void inserisci(Correntista intestatario) {

		String url = "jdbc:mysql://localhost:3306/contocorrente";
		String user = "luigi";
		String password = "gigi";
		String driver = "com.mysql.cj.jdbc.Driver";

		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connesso correttamente al database");
			String query_da_eseguire = "INSERT INTO correntista(nome,cognome,cf)VALUES(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query_da_eseguire);
			System.out.println("Sto preparando la query");
			stmt.setString(1, intestatario.nome);
			stmt.setString(2, intestatario.cognome);
			stmt.setString(3, intestatario.cf);
			stmt.executeLargeUpdate();
			System.out.println("Ho terminato l'inserimento del correntista.");
			stmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
