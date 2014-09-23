package edu.cuc.listas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author adelahoz6
 */
public class Lista<T> implements Serializable {
    protected Nodo<T> ptr;
    protected Nodo<T> nodoFinal;

    public boolean estaVacia() {
        return ptr == null;
    }

    public void insertarAlInicio(T dato) {
        //Caso1. Lista Vacia
        Nodo<T> nodoNuevo = new Nodo<T>(dato);
        if (estaVacia()) {
            ptr = nodoNuevo;
            nodoFinal = nodoNuevo;
        } else {
            //Caso2. Existe un nodo
            nodoNuevo.setSgte(ptr);
            ptr = nodoNuevo;
        }
    }

    public void insertarAlFinal(T dato) {        
        //Caso1. Lista Vacia
        Nodo<T> nodoNuevo = new Nodo<T>(dato);
        if (estaVacia()) {
            ptr = nodoNuevo;
            nodoFinal = nodoNuevo;
        } else {
            //Caso2. Existe un nodo
            nodoNuevo.setSgte(null);
            nodoFinal.setSgte(nodoNuevo);
            nodoFinal = nodoNuevo;
        }
    }

    public boolean buscar(T dato) {
        if (estaVacia()) {
            return false;
        } else {
            Nodo<T> p = ptr;
            while (p != null) {
                if (p.getInfo().equals(dato)) {
                    return true;
                }
                p = p.getSgte();
            }
            return false;
        }
    }

    public T extraerAlInicio() {
        if (estaVacia()) {
            return null;
        } else {
            Nodo<T> p = ptr;
            ptr = ptr.getSgte();
            return p.getInfo();
        }
    }

    public T extraerAlFinal() {
        if (ptr == null) {
            return null;
        } else {
            if (ptr == nodoFinal) {
                Nodo<T> p = ptr;
                ptr = nodoFinal = null;
                return p.getInfo();
            } else {
                Nodo<T> ant = null;
                Nodo<T> p = ptr;
                while (p != nodoFinal) {
                    ant = p;
                    p = p.getSgte();
                }
                nodoFinal = ant;
                ant.setSgte(null);
                return p.getInfo();
            }

        }
    }

    public boolean eliminar(T dato) {
        if (estaVacia()) {
            return false;
        } else {
            // Caso Nodo Unico
            if (ptr == nodoFinal && ptr.getInfo().equals(dato)) {
                ptr = null;
                nodoFinal = null;
                return true;
            } else {
                //Buscar la info
                Nodo<T> antP = null;
                Nodo<T> p = ptr;
                while (p != null && !p.getInfo().equals(dato)) {
                    antP = p;
                    p = p.getSgte();
                }
                if (p == null) {
                    //Caso no encontrado
                    return false;
                } else {
                    //Caso PTR
                    if (p == ptr) {
                        ptr = ptr.getSgte();
                        return true;
                    } else {
                        //Caso Final
                        if (p == nodoFinal) {
                            antP.setSgte(null);
                            nodoFinal = antP;
                            return true;
                        } else {
                            //Nodo Intermedio
                            antP.setSgte(p.getSgte());
                            return true;
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        Nodo<T> p = ptr;
        String cad = "Lista: ";
        while (p != null) {
            cad += p.toString() + " ";
            p = p.getSgte();
        }
        return cad;
    }
   
    // Metodo para serializar 
    public void guardarEnArchivo(String nombreArchivo) throws FileNotFoundException, IOException {
        //Abrir flujo de archivo
        FileOutputStream fos = new FileOutputStream(nombreArchivo);
        //Abrir flujo de objetos
        ObjectOutputStream os = new ObjectOutputStream(fos);
        //Realizar escritura en el flujo
        os.writeObject(this);
        //Cierrar flujo
        os.close();
    }

    //Metodo para deserializar
    public Lista<T> leerDeArchivo(String nomArchivo) throws FileNotFoundException, IOException, ClassNotFoundException {
        //Abrir flujo de archivo
        FileInputStream fis = new FileInputStream(nomArchivo);
        //Abrir flujo de objetos
        ObjectInputStream os = new ObjectInputStream(fis);
        //Realizar lectura del flujo
        Lista<T> listaLeida = (Lista<T>) os.readObject();
        //Cerrar flujo
        os.close();
        //Retorno
        return listaLeida;
    }

}
