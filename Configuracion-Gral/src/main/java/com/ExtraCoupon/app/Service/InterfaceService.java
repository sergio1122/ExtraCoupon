package com.ExtraCoupon.app.Service;

import com.ExtraCoupon.app.Entity.Configuraciones;

import java.util.List;

public interface InterfaceService {

    public List<Configuraciones> listAllConfigurations();
    public Configuraciones getConfigurations(Long id);
    public Configuraciones createConfigurations(Configuraciones config);
    public Configuraciones updateConfigurations(Configuraciones config);
    public Configuraciones deleteConfigurations(Long id);

}
