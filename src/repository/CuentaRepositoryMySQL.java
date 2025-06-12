package repository;

import java.sql.*;

public class CuentaRepositoryMySQL {

    public void insertarOActualizarSaldo(String numeroCuenta, double saldo) {
        String sql = "INSERT INTO cuentas (numero_cuenta, saldo) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE saldo = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numeroCuenta);
            pstmt.setDouble(2, saldo);
            pstmt.setDouble(3, saldo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double obtenerSaldo(String numeroCuenta) {
        String sql = "SELECT saldo FROM cuentas WHERE numero_cuenta = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numeroCuenta);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("saldo");
                } else {
                    insertarOActualizarSaldo(numeroCuenta, 0.0);
                    return 0.0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}