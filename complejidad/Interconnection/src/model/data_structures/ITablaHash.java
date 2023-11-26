package model.data_structures;

public interface ITablaHash <K extends Comparable<K>, V extends Comparable <V>> {

    ILista<NodoTS<K, V>> darListaNodos();

    void rehash();
}