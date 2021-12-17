package com.leandroreis.instrumentosapi.controller;

import com.leandroreis.instrumentosapi.model.Instrumento;
import com.leandroreis.instrumentosapi.repository.InstrumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrumentos")
public class InstrumentoController {

    private InstrumentoRepository repository;

    public InstrumentoController(InstrumentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/salvar")
    public Instrumento register(@RequestBody Instrumento instrumento){
        return repository.save(instrumento);

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Instrumento instrumento){
        return repository.findById(id)
                .map(record -> {
                    record.setTipo(instrumento.getTipo());
                    record.setMarca(instrumento.getMarca());
                    record.setCor(instrumento.getCor());
                    Instrumento updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "excluir/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }
}
