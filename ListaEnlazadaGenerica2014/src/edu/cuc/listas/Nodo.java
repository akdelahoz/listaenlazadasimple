package edu.cuc.listas;

import java.io.Serializable;


/**
 *
 * @author adelahoz6
 */

public class Nodo<T> implements Serializable {
    private T info;
    private Nodo<T> sgte;

    public Nodo() {
    }

    public Nodo(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public Nodo<T> getSgte() {
        return sgte;
    }

    public void setSgte(Nodo<T> sgte) {
        this.sgte = sgte;
    }

    @Override
    public String toString() {
        return info.toString();
    }


}
