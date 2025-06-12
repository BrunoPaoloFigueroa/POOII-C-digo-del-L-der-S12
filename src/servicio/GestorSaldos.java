package servicio;

import repository.CuentaRepositoryMySQL;

public class GestorSaldos {
    private CuentaRepositoryMySQL repo;

    public GestorSaldos(CuentaRepositoryMySQL repo) {
        this.repo = repo;
    }

    public void aplicarTransaccion(String tipo, double monto, String cuentaOrigen, String cuentaDestino, String referencia) {
        if ("DEPOSITO".equals(tipo)) {
            double saldoDestino = repo.obtenerSaldo(cuentaDestino);
            saldoDestino += monto;
            repo.insertarOActualizarSaldo(cuentaDestino, saldoDestino);
        } else if ("PAGO_SERVICIO".equals(tipo)) {
            double saldoOrigen = repo.obtenerSaldo(cuentaOrigen);
            double saldoDestino = repo.obtenerSaldo(cuentaDestino);
            saldoOrigen -= monto;
            saldoDestino += monto;
            repo.insertarOActualizarSaldo(cuentaOrigen, saldoOrigen);
            repo.insertarOActualizarSaldo(cuentaDestino, saldoDestino);
        }
    }

    public void imprimirEstadoTransaccion(String tipo, boolean exito, modelo.Transaccion transaccion) {
        System.out.println("\nEstado después de " + tipo + ":");
        System.out.println("- Cuenta " + transaccion.getCuentaOrigen() + ": $" + repo.obtenerSaldo(transaccion.getCuentaOrigen()));
        System.out.println("- Cuenta " + transaccion.getCuentaDestino() + ": $" + repo.obtenerSaldo(transaccion.getCuentaDestino()));
    }
}