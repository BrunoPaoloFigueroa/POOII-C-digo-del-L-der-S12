package modelo;

public class Transaccion {
    public enum TipoTransaccion {
        TRANSFERENCIA,
        DEPOSITO,
        PAGO_SERVICIO
    }

    private int id;
    private TipoTransaccion tipo;
    private double monto;
    private String cuentaOrigen;
    private String cuentaDestino;
    private String referencia;
    private double comision;
    private String fecha;

    public Transaccion(TipoTransaccion tipo, double monto, String cuentaOrigen,
                       String cuentaDestino, String referencia, Double comision) {
        this.tipo = tipo;
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.referencia = referencia;
        this.comision = (comision == null) ? 0.0 : comision;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public TipoTransaccion getTipo() { return tipo; }

    public double getMonto() { return monto; }

    public String getCuentaOrigen() { return cuentaOrigen; }

    public String getCuentaDestino() { return cuentaDestino; }

    public String getReferencia() { return referencia; }

    public double getComision() { return comision; }
    public void setComision(double comision) { this.comision = comision; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
