package com.ExtraCoupon.Plealtad.ProgramaLealtad.Service;

import com.ExtraCoupon.Plealtad.ProgramaLealtad.Entity.Lealtad;
import com.ExtraCoupon.Plealtad.ProgramaLealtad.Entity.Productos;
import com.ExtraCoupon.Plealtad.ProgramaLealtad.Repository.RepositoryLealtad;
import com.ExtraCoupon.Plealtad.ProgramaLealtad.Repository.RepositoryProductos;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
public class ServiceLealtad implements ServiceInterface{
    @Autowired
    public final RepositoryLealtad dao;
    @Autowired
    public final RepositoryProductos pro;
    @Override
    @Transactional
    public Lealtad save(Lealtad lealtad) {
        return dao.saveAndFlush(lealtad);
    }



    @Override
    public Lealtad activar(Long id) {
        if (!dao.existsById(id)){
            return null;
        }

        Lealtad lealtad = detalle(id);
        lealtad.setStatus("1");
        return dao.saveAndFlush(lealtad);
    }
    @Override
    public Lealtad desactivar(Long id) {
        if (!dao.existsById(id)){
            return null;
        }
        Lealtad lealtad = detalle(id);
        lealtad.setStatus("2");
        return dao.saveAndFlush(lealtad);
    }

    public List<Lealtad> findByAfiliado(String afiliado) {
        return dao.findByAfiliado(afiliado);
    }

    @Override
    public Lealtad detalle(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Page<Lealtad> paginacion(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public Productos saveProductos(Productos productos) {
        return pro.saveAndFlush(productos);
    }

    public Lealtad findByAfiliadoAndStatus(String afiliado){

        return dao.findByAfiliadoAndStatus(afiliado,"1");
    }

}
