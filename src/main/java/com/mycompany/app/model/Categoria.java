package com.mycompany.app.model;

public enum Categoria {
    REMEDIO(0), RACAO(1), BRINQUEDOS(2), MISC(3), HIGIENE(4);

    int valor;

    Categoria(int valor) {
        this.valor = valor;
    }
}
