package com.ExtraCoupon.Plealtad.ProgramaLealtad.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "t_productos_condicion_programa")
@NoArgsConstructor
@Builder
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idt_productos_condicion_programa", nullable = false)
    private Long id;
    private String codigo_barras;
    @Column(name = "descripcion")
    private String nombre;
    private String categoria;
    private String precio;
    @Column(name = "t_programa_lealtad_afiliado")
    private String idt_programa;


}
