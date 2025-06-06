package strategy;

import modelo.Transaccion;

public interface TransaccionStrategy {

    boolean procesar(Transaccion transaccion);

}
