package com.ExtraCoupon.app.Repository;

import com.ExtraCoupon.app.Entity.Configuraciones;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface RepositoryConfig extends JpaRepository<Configuraciones, Long> {

    Configuraciones findByStatusAndPais(String status, String pais);
    //@Query(value = "select * from t_config_general c where c.c_status_activo=1 and c.c_pais=1", nativeQuery = true)
    //Page<Configuraciones> findByPaisOrderByIdDesc(String pais,Pageable pageable);
    Page<Configuraciones> findByPais(String pais, Pageable pageable);
}
