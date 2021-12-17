package com.leandroreis.instrumentosapi.repository;

import com.leandroreis.instrumentosapi.model.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentoRepository extends JpaRepository<Instrumento, Long> {
}
