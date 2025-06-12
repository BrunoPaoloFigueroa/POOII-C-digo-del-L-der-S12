package strategy;

import modelo.Transaccion;

public class PagoServiciosStrategy implements TransaccionStrategy {
    @Override
    public boolean procesar(Transaccion transaccion) {
        try {
            if (!Transaccion.TipoTransaccion.PAGO_SERVICIO.equals(transaccion.getTipo())) {
                System.err.println("Tipo incorrecto para PagoServiciosStrategy");
                return false;
            }
            if (transaccion.getReferencia() == null || transaccion.getReferencia().isEmpty()) {
                System.err.println("Referencia inválida");
                return false;
            }
            boolean promocion = true;
            double montoFinal = transaccion.getMonto();
            if (promocion) {
                montoFinal = montoFinal * 0.95;  // 5% descuento
            }
            System.out.println("Pago de servicio procesado: referencia " + transaccion.getReferencia() +
                    ", monto original " + transaccion.getMonto() + ", monto final " + montoFinal);
            return true;
        } catch (Exception e) {
            System.err.println("Error en PagoServiciosStrategy: " + e.getMessage());
            return false;
        }
    }
}