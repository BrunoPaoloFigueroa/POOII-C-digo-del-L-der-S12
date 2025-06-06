package servicio;

import modelo.Transaccion;
import repository.TransaccionRepositoryMySQL;
import strategy.TransaccionStrategy;

public class ProcesadorTransacciones {
    private TransaccionStrategy estrategia;
    private TransaccionRepositoryMySQL repo;

    public ProcesadorTransacciones(TransaccionRepositoryMySQL repo) {
        this.repo = repo;
    }

    public void setEstrategia(TransaccionStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public void procesarTransaccion(Transaccion transaccion) {
        if (estrategia == null) {
            System.err.println("Estrategia no definida");
            return;
        }

        if (estrategia.procesar(transaccion)) {
            if (repo.guardarTransaccion(transaccion)) {
                System.out.println("Transacción guardada con ID: " + transaccion.getId());
            } else {
                System.err.println("Error guardando la transacción");
            }
        } else {
            System.err.println("Error procesando la transacción");
        }
    }
}