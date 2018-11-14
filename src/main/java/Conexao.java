import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private String user = "root";
	private String password = "root";
	private String url = "jdbc:mysql://localhost:3306/EcoEneergy";
	
	private Connection conn;
	
	public Conexao() throws SQLException {
		conn = DriverManager.getConnection(url, user, password);
	}
	
	public Connection conectar() {
		return conn;
		
		
		
	}
	
}