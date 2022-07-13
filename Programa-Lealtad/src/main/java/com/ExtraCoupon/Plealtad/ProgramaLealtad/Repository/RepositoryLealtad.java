package com.ExtraCoupon.Plealtad.ProgramaLealtad.Repository;

import com.ExtraCoupon.Plealtad.ProgramaLealtad.Entity.Lealtad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryLealtad extends JpaRepository<Lealtad, Long> {
    public Lealtad findByAfiliadoAndStatus(String afiliado, String status);
    public List<Lealtad> findByAfiliado(String afiliado);
}
