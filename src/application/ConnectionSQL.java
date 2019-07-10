package application;

import java.sql.Connection;
import java.sql.DriverManager;

// Documentacao 
// https://docs.oracle.com/javase/7/docs/api/java/sql/package-frame.html

public class ConnectionSQL {
	final private static String driver_name = "com.mysql.cj.jdbc.Driver";
	final private static String server_addr = "localhost";
	final private static String db_user = "root";
	final private static String db_password = "";
	final private static String db_name = "vocealuga";

	{
		// Vamos testar se o Driver consegue ser carregado
		try {
			Class.forName(driver_name).newInstance();
			System.out.println("Driver carregado successfully");
		} catch (Exception e) {
			System.err.println("Nao foi possivel carregar o driver do mysql :( ");
			System.err.println(e);
		}

		// Agora vamos tentar criar uma conexao com o banco de dados no endereco
		// especificado em server_addr
		Connection con;
		try {
			con = getConnection();
			if (con != null) {
				System.out.println("Conexao com o banco de dados estabelecida successfully");
				con.close();
			}
		} catch (Exception e) {
			System.err.println("Nao foi possivel estabelecer uma conexao com o banco de dados...");
			System.err.println(e);
		}
	}

	// Gera uma conexao para as classes DAO utilizar. Elas são responsáveis por
	// fechar a conexao.
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://" + server_addr + "/" + db_name + "?useTimezone=true&serverTimezone=UTC", db_user,
					db_password);
		} catch (Exception e) {
			System.err.println("Nao foi possivel estabelecer uma conexao com o banco de dados...");
			e.printStackTrace();
		}
		return null;
	}

}
