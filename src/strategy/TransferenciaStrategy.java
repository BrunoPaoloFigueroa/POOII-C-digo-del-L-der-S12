package strategy;

import modelo.Transaccion;

public class TransferenciaStrategy implements TransaccionStrategy {
    @Override
    public boolean procesar(Transaccion transaccion) {
        try {
            if (!Transaccion.TipoTransaccion.TRANSFERENCIA.equals(transaccion.getTipo())) {
                System.err.println("Tipo incorrecto para TransferenciaStrategy");
                return false;
            }
            if (transaccion.getMonto() <= 0) {
                System.err.println("Monto debe ser positivo");
                return false;
            }
            if (transaccion.getCuentaOrigen() == null || transaccion.getCuentaDestino() == null) {
                System.err.println("Cuentas inválidas");
                return false;
            }
            if (transaccion.getCuentaDestino().startsWith("IB")) {
                double comision = transaccion.getMonto() * 0.01;
                transaccion.setComision(comision);
            } else {
                transaccion.setComision(0.0);
            }
            System.out.println("Transferencia procesada: debitar " + transaccion.getMonto() +
                    " de " + transaccion.getCuentaOrigen() + ", acreditar a " + transaccion.getCuentaDestino() +
                    ". Comisión: " + transaccion.getComision());
            return true;
        } catch (Exception e) {
            System.err.println("Error en TransferenciaStrategy: " + e.getMessage());
            return false;
        }
    }
}
