package edu.uoc.tfg.crm.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum FormaPago {
    EFECTIVO(0),
    TRANSFERENCIA(1),
    BANCO(2);

    private int valor;

    private FormaPago(int value){ this.valor = value;}
    public int getValor(){ return valor;}
}
