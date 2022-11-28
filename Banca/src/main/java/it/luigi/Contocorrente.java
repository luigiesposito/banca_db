package it.luigi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Contocorrente {

	int num_conto;
	double saldo;
	String cf;

	public Contocorrente(int num_conto, double saldo, String cf) {
		super();
		this.num_conto = num_conto;
		this.saldo = saldo;
		this.cf = cf;
	}

	private static final Random RANDOM = new Random();

	public static int getRandomInteger(int min, int max) {
		return min + RANDOM.nextInt(max + 1 - min);
	}

	static void nuovo_conto(String cf, double saldo, Contocorrente op) {
		String url = "jdbc:mysql://localhost:3306/contocorrente";
		String user = "luigi";
		String password = "gigi";
		String driver = "com.mysql.cj.jdbc.Driver";

		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connesso correttamente al database");
			String query_da_eseguire = "INSERT INTO conto_corrente(num_conto,saldo,cf)VALUES(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query_da_eseguire);
			System.out.println("Sto preparando la query");
			stmt.setInt(1, op.num_conto);
			stmt.setDouble(2, op.saldo);
			stmt.setString(3, op.cf);
			stmt.executeLargeUpdate();
			System.out.println("Ho terminato la generazione del conto corrente.");
			stmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	static void versamento(int num_conto,double operazione) {
		String url = "jdbc:mysql://localhost:3306/contocorrente";
		String user = "luigi";
		String password = "gigi";
		String driver = "com.mysql.cj.jdbc.Driver";

		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connesso correttamente al database");
			//Faccio la query per l'inserimento a database del movimento
			String query_movimento="INSERT INTO movimenti VALUES(null,?,?,?,?)";
			PreparedStatement movimento = conn.prepareStatement(query_movimento);
			movimento.setString(1, "Versamento");
			movimento.setDouble(2, operazione);
			movimento.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			movimento.setInt(4, num_conto);
			movimento.executeLargeUpdate();
			//Fine della query per l'inserimento a database del movimento 
			
			//Inizio la query per la selezione del saldo precedente
			String query_saldo_vecchio="SELECT saldo FROM conto_corrente WHERE num_conto=" + num_conto;
			PreparedStatement saldo = conn.prepareStatement(query_saldo_vecchio);
			ResultSet rs= saldo.executeQuery(query_saldo_vecchio);
			
			while (rs.next()) {
				double saldo_db= rs.getDouble("saldo");
				double nuovo_saldo=saldo_db+operazione;
			
			//Finisco la query per la selezione del saldo precedente
			
			//Inizio la query per l'aggiornamento del saldo
			String query_saldo = "UPDATE conto_corrente SET saldo = ? WHERE num_conto = ?";
			PreparedStatement stmt = conn.prepareStatement(query_saldo);
			System.out.println("Sto preparando la query");
			stmt.setDouble(1, nuovo_saldo);
			stmt.setInt(2, num_conto);
			stmt.executeLargeUpdate();
			System.out.println("Ho terminato il versamento");
			stmt.close();
			}
			//Finisco la query dell'aggiornamento del saldo e chiudo la connessione
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	static void prelievo(int num_conto_prelievo,double operazione_prelievo) {
		String url = "jdbc:mysql://localhost:3306/contocorrente";
		String user = "luigi";
		String password = "gigi";
		String driver = "com.mysql.cj.jdbc.Driver";

		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connesso correttamente al database");
			//Faccio la query per l'inserimento a database del movimento
			String query_movimento_prelievo="INSERT INTO movimenti VALUES(null,?,?,?,?)";
			PreparedStatement movimento_prelievo = conn.prepareStatement(query_movimento_prelievo);
			movimento_prelievo.setString(1, "Prelievo");
			movimento_prelievo.setDouble(2, -operazione_prelievo);
			movimento_prelievo.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			movimento_prelievo.setInt(4, num_conto_prelievo);
			movimento_prelievo.executeLargeUpdate();
			//Fine della query per l'inserimento a database del movimento 
			
			//Inizio la query per la selezione del saldo precedente
			String query_saldo_vecchio_prelievo="SELECT saldo FROM conto_corrente WHERE num_conto=num_conto";
			PreparedStatement saldo_prelievo = conn.prepareStatement(query_saldo_vecchio_prelievo);
			ResultSet rs= saldo_prelievo.executeQuery(query_saldo_vecchio_prelievo);
			
			while (rs.next()) {
				double saldo_db= rs.getDouble("saldo");
				double nuovo_saldo=saldo_db-operazione_prelievo;
			
			//Finisco la query per la selezione del saldo precedente
			
			//Inizio la query per l'aggiornamento del saldo
			String query_saldo = "UPDATE conto_corrente SET saldo = ? WHERE num_conto = ?";
			PreparedStatement query_prelievo = conn.prepareStatement(query_saldo);
			System.out.println("Sto preparando la query");
			query_prelievo.setDouble(1, nuovo_saldo);
			query_prelievo.setInt(2, num_conto_prelievo);
			query_prelievo.executeLargeUpdate();
			System.out.println("Ho terminato il prelievo");
			query_prelievo.close();
			}
			//Finisco la query dell'aggiornamento del saldo e chiudo la connessione
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
