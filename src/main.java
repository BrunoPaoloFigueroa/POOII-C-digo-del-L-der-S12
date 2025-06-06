import modelo.Transaccion;
import repository.TransaccionRepositoryMySQL;
import servicio.ProcesadorTransacciones;
import strategy.DepositoStrategy;
import strategy.PagoServiciosStrategy;
import strategy.TransferenciaStrategy;

import java.util.List;

public class main {

    public static void main(String[] args) {

        // Crear el repositorio (suponiendo que lo tienes implementado)
        TransaccionRepositoryMySQL repo = new TransaccionRepositoryMySQL();

        // Crear el procesador de transacciones
        ProcesadorTransacciones procesador = new ProcesadorTransacciones(repo);

        // 1. Crear una transferencia
        Transaccion transferencia = new Transaccion(
                Transaccion.TipoTransaccion.TRANSFERENCIA,
                1500.00,
                "1234567890",
                "9876543210",
                null,
                null
        );
        // Asignar la estrategia transferencia
        procesador.setEstrategia(new TransferenciaStrategy());
        procesador.procesarTransaccion(transferencia);

        // 2. Crear un depósito
        Transaccion deposito = new Transaccion(
                Transaccion.TipoTransaccion.DEPOSITO,
                500.00,
                null,
                "1234567890",
                "Depósito móvil",
                null
        );
        // Asignar la estrategia deposito
        procesador.setEstrategia(new DepositoStrategy());
        procesador.procesarTransaccion(deposito);

        // 3. Crear un pago de servicio
        Transaccion pagoServicio = new Transaccion(
                Transaccion.TipoTransaccion.PAGO_SERVICIO,
                250.00,
                null,
                "1234567890",
                "Factura-12345",
                null
        );
        // Asignar la estrategia pago de servicios
        procesador.setEstrategia(new PagoServiciosStrategy());
        procesador.procesarTransaccion(pagoServicio);
    }






}
