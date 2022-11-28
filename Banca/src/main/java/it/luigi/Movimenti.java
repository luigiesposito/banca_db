package it.luigi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Movimenti {

	int id_movimento;
	String tipo;
	double importo;
	Date data;
	int	num_conto;
	
	Movimenti (int id_movimento, String tipo, double importo, Date data, int num_conto) {
		this.id_movimento=id_movimento;
		this.tipo=tipo;
		this.importo=importo;
		this.data=data;
		this.num_conto=num_conto;
	}
	
	static void lista_mov(int num_conto_movimento) 
	{
		int i;
		/* CONNESSIONE AL DATABASE */
		String url = "jdbc:mysql://localhost:3306/contocorrente";
		String user = "luigi";
		String password = "gigi";
		String driver = "com.mysql.cj.jdbc.Driver";

		Connection conn = null;

		try 
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connesso correttamente al database");
			String query_ricerca="SELECT tipo,importo,data,conto_corrente.saldo FROM movimenti, conto_corrente WHERE movimenti.num_conto="+num_conto_movimento+" AND conto_corrente.num_conto=movimenti.num_conto ORDER BY data DESC";
			
			PreparedStatement eseguo_query_ricerca = conn.prepareStatement(query_ricerca);

			ResultSet rs= eseguo_query_ricerca.executeQuery(query_ricerca);
			
			while (rs.next()) 
			{
				String tipo_db= rs.getString("tipo");
				double importo_db=rs.getDouble("importo");
				Date data_db=rs.getDate("data");
				double saldo_db=rs.getDouble("saldo");
				
				System.out.println(tipo_db + " €"+ importo_db + " " + data_db);
				
			}


		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		


	
	}
	}
