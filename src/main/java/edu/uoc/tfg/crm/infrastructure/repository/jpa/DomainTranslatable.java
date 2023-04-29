package edu.uoc.tfg.crm.infrastructure.repository.jpa;

public interface DomainTranslatable<T> {

    T toDomain();

}
