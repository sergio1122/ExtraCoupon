package com.ExtraCoupon.app.Controller;

import com.ExtraCoupon.app.Entity.Configuraciones;
import com.ExtraCoupon.app.Service.ServiceConfig;
import com.ExtraCoupon.app.erros.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/Configurations")
public class ControllerConfig {
    @Autowired
    private ServiceConfig service;



    @GetMapping(value = "{pais}")
    public ResponseEntity<?> getActivo(@PathVariable() String pais ){
        Configuraciones config;
        if(pais==null){
        return ResponseEntity.noContent().build();
    }else{
         config = service.findStatusPais("1",pais);
         if(config==null){
             return ResponseEntity.notFound().build();
         }
    }
       return ResponseEntity.ok(config);
    }
    @GetMapping
    public ResponseEntity<?> getList(@RequestParam(name = "pais") String pais,@PageableDefault(page=0, size=5) @NotNull Pageable pageable){

      Sort sort= Sort.by("id").descending();
        Pageable pg =  PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        Page<Configuraciones> page= service.lista(pais,pg);
        List<Configuraciones> list = page.stream().collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
    @PostMapping
    public ResponseEntity<?> create(@NotNull @Valid @RequestBody Configuraciones config, @RequestParam(name = "id") Long id, @NotNull BindingResult result){
        Configuraciones created;
        System.err.println(config.toString());
        if (result.hasErrors()){
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
    }
    if (id==null){
        return ResponseEntity.noContent().build();
    }else{
        service.deleteConfigurations(id);
        created = service.createConfigurations(config);
    }

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @RequestMapping(value = "/replicate",method = RequestMethod.POST)
    public ResponseEntity<?> duplicate(@Valid @RequestBody List<Configuraciones> config, @NotNull BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Configuraciones _1,_2;
        Configuraciones created_mx = config.get(0);
        Configuraciones mex = service.findStatusPais("1","1");
        mex.setStatus("2");
        service.deleteConfigurations(mex.getId());
        _1 = service.createConfigurations(created_mx);

        Configuraciones created_usa = config.get(1);
        Configuraciones usd = service.findStatusPais("1","2");
        usd.setStatus("2");
        service.deleteConfigurations(usd.getId());
        _2 = service.createConfigurations(created_usa);
        ArrayList<Configuraciones> lista = new ArrayList<>();

        lista.add(_1);
        lista.add(_2);




       return ResponseEntity.status(HttpStatus.CREATED).body(lista);
    }
    private String formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try {
            json = mapper.writeValueAsString(errorMessage);
        }catch (Exception e){

        }
        return json;
    }
}
