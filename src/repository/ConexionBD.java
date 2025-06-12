package repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionBD {

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/banco", "root", "T3csup5629");
    }
}
