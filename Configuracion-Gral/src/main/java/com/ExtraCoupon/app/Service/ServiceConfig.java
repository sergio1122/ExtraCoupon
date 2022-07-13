package com.ExtraCoupon.app.Service;

import com.ExtraCoupon.app.Entity.Configuraciones;
import com.ExtraCoupon.app.Repository.RepositoryConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceConfig implements InterfaceService {
    @Autowired
    public final RepositoryConfig dao;
    @Override
    @Transactional(readOnly = true)
    public List<Configuraciones> listAllConfigurations() {
        return dao.findAll();
    }

    @Override
    public Configuraciones getConfigurations(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Configuraciones createConfigurations(Configuraciones config) {
        return dao.saveAndFlush(config);
    }

    @Override
    public Configuraciones updateConfigurations(Configuraciones config) {
      if(!dao.existsById(config.getId())){
          return null;
      }
        return dao.saveAndFlush(config);
    }

    @Override
    public Configuraciones deleteConfigurations(Long id) {
        if(!dao.existsById(id)){
            return null;
        }
        Configuraciones config = getConfigurations(id);
        config.setStatus("2");

        return dao.save(config);
    }
    public Configuraciones findStatusPais(String status, String pais){
return dao.findByStatusAndPais(status, pais);
    }
    public Page<Configuraciones> lista(String pais, Pageable pageable){
        return dao.findByPais(pais,pageable);
    }
}
