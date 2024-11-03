public class Nodo {
    
    private Documento documento;
    private Nodo izquierdo;
    private Nodo derecho;

    public Nodo(Documento documento) {
        this.documento = documento;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Documento getDocumento() {
        return documento;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}