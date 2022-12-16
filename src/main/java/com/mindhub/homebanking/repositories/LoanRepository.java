package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //repository es un recurso de rest y van a estar en las restricciones de rest(representation estate transfers)
//representacion son los datos en json o xml. estados: son los datos como son solicitados. transferir: es el que permite get/post/put/path/delete, leer json y pasarlo a java
public interface LoanRepository extends JpaRepository<Loan,Long> {
    Loan findById(long Id);
}
