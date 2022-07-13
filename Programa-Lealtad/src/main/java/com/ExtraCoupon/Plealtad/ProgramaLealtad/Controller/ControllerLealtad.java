package com.ExtraCoupon.Plealtad.ProgramaLealtad.Controller;

import com.ExtraCoupon.Plealtad.ProgramaLealtad.Entity.Lealtad;
import com.ExtraCoupon.Plealtad.ProgramaLealtad.Entity.Productos;
import com.ExtraCoupon.Plealtad.ProgramaLealtad.Service.ServiceLealtad;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/ProgramaLealtad")
@Slf4j
public class ControllerLealtad {

    @Autowired
    private ServiceLealtad service;


    @PostMapping
    public ResponseEntity<?> createLelatad(@NotNull @RequestBody List<Object> object,@RequestParam(name = "afiliado") String afiliado, BindingResult result){
        ObjectMapper mapper = new ObjectMapper();

        System.out.println(object.get(0));
        System.out.println();
        System.out.println(object.get(1));

Lealtad lealtad = mapper.convertValue(object.get(0),Lealtad.class);

        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(afiliado == null){
            ResponseEntity.noContent().build();
        }
        Lealtad l=service.findByAfiliadoAndStatus(afiliado);
        if (l!=null){
            service.desactivar(l.getId());
        }
        service.save(lealtad);
        System.out.println(lealtad.getId());
        if(lealtad.getTipo_beneficio().equals("3")){
            if(lealtad.getStatus_valido_general_otorgar().equals("2")){
                List<Productos> productos = mapper.convertValue(object.get(1), mapper.getTypeFactory().constructCollectionType(List.class, Productos.class));
                for (Productos prod:productos) {
                    prod.setIdt_programa(lealtad.getId()+"");
                    service.saveProductos(prod);
                }
            }


        }


        return ResponseEntity.status(HttpStatus.CREATED).body(lealtad);
    }
    @GetMapping
    public ResponseEntity<?> listarLealtad(@RequestParam(name = "afiliado") String afiliado){
        if (afiliado==null){
            return ResponseEntity.noContent().build();
        }
        List<Lealtad>lista=service.findByAfiliado(afiliado);

        return ResponseEntity.ok(lista);
    }
    @PutMapping
    public ResponseEntity<?> activar(@NotNull @RequestParam(name = "id") Long id){

        if (id==null){
            return ResponseEntity.noContent().build();
        }
        Lealtad lealtad = service.activar(id);

        return lealtad==null ? ResponseEntity.noContent().build():ResponseEntity.ok(lealtad);
    }
    @DeleteMapping
    public ResponseEntity<?> desactivar(@NotNull @Valid @RequestParam(name = "id") Long id){

        if (id==null){
            return ResponseEntity.noContent().build();
        }
        Lealtad lealtad = service.desactivar(id);
log.debug("entro en el metodo desactivar");
        return lealtad==null ? ResponseEntity.noContent().build():ResponseEntity.ok(lealtad);
    }
}
