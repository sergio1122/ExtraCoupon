package com.ExtraCoupon.Plealtad.ProgramaLealtad.Service;

import com.ExtraCoupon.Plealtad.ProgramaLealtad.Entity.Lealtad;
import com.ExtraCoupon.Plealtad.ProgramaLealtad.Entity.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceInterface {

    public Lealtad save(Lealtad lealtad);

    Lealtad activar(Long id);
    Lealtad desactivar(Long id);

    public Lealtad detalle(Long id);
    public Page<Lealtad> paginacion(Pageable pageable);

    public Productos saveProductos(Productos productos);

}
