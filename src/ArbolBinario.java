
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ArbolBinario {

    private Nodo raiz;
    private int criterio;

    public ArbolBinario(Nodo raiz) {
        this.raiz = raiz;
    }

    public int getCriterio() {
        return criterio;
    }

    public void setCriterio(int criterio) {
        this.criterio = criterio;
    }

    public ArbolBinario() {
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public List<Nodo> buscarTodos(String valor) {
        List<Nodo> resultados = new ArrayList<>();
        buscarRecursivo(raiz, valor, resultados);
        return resultados;
    }

    private void buscarRecursivo(Nodo nodo, String valor, List<Nodo> resultados) {
        if (nodo == null) {
            return;
        }
        String criterioValor = nodo.getDocumento().getCriterio(criterio);

        if (criterioValor != null && criterioValor.toLowerCase().startsWith(valor.toLowerCase())) {
            resultados.add(nodo);
        }
      
        buscarRecursivo(nodo.getIzquierdo(), valor, resultados);
        buscarRecursivo(nodo.getDerecho(), valor, resultados);
    }

    @SuppressWarnings("null")
    public void agregarNodo(Nodo n) {

        if (raiz == null) {
            raiz = n;
            System.out.println("Nodo agregado como raíz: " + n.getDocumento().getApellido1());
            return;
        }

        Nodo actual = raiz;
        Nodo padre;
        while (true) {
            padre = actual;
            if (n.getDocumento().getCriterio(criterio).compareTo(actual.getDocumento().getCriterio(criterio)) < 0) {
                actual = actual.getIzquierdo();
                if (actual == null) {
                    padre.setIzquierdo(n);
                    System.out.println("Nodo agregado a la izquierda de " + padre.getDocumento().getApellido1());
                    return;
                }
            } else {
                actual = actual.getDerecho();
                if (actual == null) {
                    padre.setDerecho(n);
                    System.out.println("Nodo agregado a la derecha de " + padre.getDocumento().getApellido1());
                    return;
                }
            }
        }
    }

    public void mostrar(JTable tbl) {
        String[][] datos = null;
        if (raiz != null) {
            datos = new String[Documento.documentos.size()][Documento.encabezados.length];

            Nodo n = raiz;
            Nodo padre;
            int fila = -1;
            while (n != null) {
                if (n.getIzquierdo() == null) {
                    fila++;
                    datos[fila][0] = n.getDocumento().getApellido1();
                    datos[fila][1] = n.getDocumento().getApellido2();
                    datos[fila][2] = n.getDocumento().getNombre();
                    datos[fila][3] = n.getDocumento().getDocumento();
                    n = n.getDerecho();
                } else {
                    padre = n.getIzquierdo();
                    while (padre.getDerecho() != null && padre.getDerecho() != n) {
                        padre = padre.getDerecho();
                    }
                    if (padre.getDerecho() == null) {
                        padre.setDerecho(n);
                        n = n.getIzquierdo();
                    } else {
                        padre.setDerecho(null);
                        fila++;
                        datos[fila][0] = n.getDocumento().getApellido1();
                        datos[fila][1] = n.getDocumento().getApellido2();
                        datos[fila][2] = n.getDocumento().getNombre();
                        datos[fila][3] = n.getDocumento().getDocumento();
                        n = n.getDerecho();
                    }

                }
            }
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, Documento.encabezados);
        tbl.setModel(dtm);
    }

}
