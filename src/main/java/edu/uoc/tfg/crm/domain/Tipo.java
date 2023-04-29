package edu.uoc.tfg.crm.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Tipo {
    PROPIETARIO(0),
    PROVEEDOR(1);

    private int valor;

    private Tipo(int value){ this.valor = value;}
    public int getValor(){ return valor;}
}

