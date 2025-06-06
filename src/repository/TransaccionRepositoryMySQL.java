package repository;

import modelo.Transaccion;
import modelo.Transaccion.TipoTransaccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransaccionRepositoryMySQL {

    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/nombre_base_de_datos?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "tu_usuario";
    private static final String CONTRASENA = "tu_contraseña";

    public TransaccionRepositoryMySQL() {
        try {
            connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión a la base de datos exitosa.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos.");
        }
    }

    public boolean guardarTransaccion(Transaccion transaccion) {
        String sql = "INSERT INTO transacciones (tipo, monto, cuenta_origen, cuenta_destino, referencia, comision, fecha) " +
                "VALUES (?, ?, ?, ?, ?, ?, NOW())";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, transaccion.getTipo().name());
            ps.setDouble(2, transaccion.getMonto());
            ps.setString(3, transaccion.getCuentaOrigen());
            ps.setString(4, transaccion.getCuentaDestino());
            ps.setString(5, transaccion.getReferencia());
            ps.setDouble(6, transaccion.getComision());

            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Transaccion obtenerTransaccionPorId(int id) {
        String sql = "SELECT * FROM transacciones WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TipoTransaccion tipo = TipoTransaccion.valueOf(rs.getString("tipo"));

                Transaccion transaccion = new Transaccion(
                        tipo,
                        rs.getDouble("monto"),
                        rs.getString("cuenta_origen"),
                        rs.getString("cuenta_destino"),
                        rs.getString("referencia"),
                        rs.getDouble("comision")
                );

                transaccion.setId(rs.getInt("id"));
                transaccion.setFecha(rs.getString("fecha"));

                return transaccion;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
