import modelo.Transaccion;
import repository.CuentaRepositoryMySQL;
import repository.TransaccionRepositoryMySQL;
import servicio.*;
import strategy.*;

public class main {
    public static void main(String[] args) {
        CuentaRepositoryMySQL cuentaRepo = new CuentaRepositoryMySQL();
        TransaccionRepositoryMySQL transaccionRepo = new TransaccionRepositoryMySQL();

        ProcesadorTransacciones procesador = new ProcesadorTransacciones(transaccionRepo);
        GestorSaldos gestorSaldos = new GestorSaldos(cuentaRepo);

        Transaccion deposito = new Transaccion(
                Transaccion.TipoTransaccion.DEPOSITO, 300.0,
                "SERV001", "CU456", "Depósito móvil", null);
        procesador.setEstrategia(new DepositoStrategy());
        procesador.procesarTransaccion(deposito);
        gestorSaldos.aplicarTransaccion("DEPOSITO", deposito.getMonto(),
                null, deposito.getCuentaDestino(), null);
        gestorSaldos.imprimirEstadoTransaccion("DEPOSITO", true, deposito);

        Transaccion pagoServicio = new Transaccion(
                Transaccion.TipoTransaccion.PAGO_SERVICIO, 150.0,
                "CU123", "SERV001", "FACT-12345", null);
        procesador.setEstrategia(new PagoServiciosStrategy());
        procesador.procesarTransaccion(pagoServicio);
        gestorSaldos.aplicarTransaccion("PAGO_SERVICIO", pagoServicio.getMonto(),
                pagoServicio.getCuentaOrigen(), pagoServicio.getCuentaDestino(), pagoServicio.getReferencia());
        gestorSaldos.imprimirEstadoTransaccion("PAGO_SERVICIO", true, pagoServicio);

        Transaccion pagoServicio1 = new Transaccion(
        Transaccion.TipoTransaccion.PAGO_SERVICIO, 150.0,
                "zzz", "Saa", "FACT-12345", null);
        procesador.setEstrategia(new PagoServiciosStrategy());
        procesador.procesarTransaccion(pagoServicio1);
        gestorSaldos.aplicarTransaccion("PAGO_SERVICIO", pagoServicio1.getMonto(),
                pagoServicio1.getCuentaOrigen(), pagoServicio1.getCuentaDestino(), pagoServicio1.getReferencia());
        gestorSaldos.imprimirEstadoTransaccion("PAGO_SERVICIO", true, pagoServicio1);
    }
}