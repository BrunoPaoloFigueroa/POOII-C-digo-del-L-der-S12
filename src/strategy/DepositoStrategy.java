package strategy;

import modelo.Transaccion;

public class DepositoStrategy implements TransaccionStrategy {
    @Override
    public boolean procesar(Transaccion transaccion) {
        try {
            if (!Transaccion.TipoTransaccion.DEPOSITO.equals(transaccion.getTipo())) {
                System.err.println("Tipo incorrecto para DepositoStrategy");
                return false;
            }
            if (transaccion.getMonto() <= 0) {
                System.err.println("Monto debe ser positivo");
                return false;
            }
            System.out.println("Depósito acreditado: " + transaccion.getMonto() +
                    " a cuenta " + transaccion.getCuentaDestino());
            return true;
        } catch (Exception e) {
            System.err.println("Error en DepositoStrategy: " + e.getMessage());
            return false;
        }
    }
}