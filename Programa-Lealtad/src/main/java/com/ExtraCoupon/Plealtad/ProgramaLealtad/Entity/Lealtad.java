package com.ExtraCoupon.Plealtad.ProgramaLealtad.Entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Calendar;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;


@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "t_programa_lealtad_afiliado")
public class Lealtad {

    @Id
    @Column(name = "idt_programa_lealtad_afiliado", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(name = "t_afiliados")
   @NotNull(message = "el id del afiliado esta nulo")
   @Positive(message = "el afiliado debe ser mayor a 0")
   private String afiliado;
   @Column(name = "c_tipo_beneficio")
   @NotNull(message = "el tipo de beneficio esta nulo")
   @Positive(message = "el el tipo de beneficio debe ser mayor a 0")
   private String tipo_beneficio;
   @Column(name = "img_programa")
   private String imagen;
   private String beneficio;
   @Column(name = "c_tipo_notificacion")
   @Positive
   private String tipo_notificacion;
   //@Positive(message = "La cantidad de cupones debe ser mayor a 0")
   private String cantidad_cupones;
   //@PositiveOrZero(message = "EL campo cupones otorgados debe 0")
   private String cupones_otorgados;
   private String descuento;
   @CreatedDate
   @Temporal(DATE)
   @DateTimeFormat(style = "yyyy-MM-dd")
   private Date vigencia_cupon;
   private String terminos_condiciones;
   @Column(name = "c_status_validoGeneral_otorgar")
   @Positive(message = "el estatus valido general otorgar debe ser mayor a 0")
   private String status_valido_general_otorgar;
   private String minimo_otorgarCupon;
   @Column(name = "c_status_validoGeneral_canje")
   @Positive(message = "el estatus valido general canje debe ser mayor a 0")
   private String status_valido_general_canje;
   private String monto_minimo;
   private String monto_maximo;
   @CreatedDate
   @Column(name = "fecha_configuracion")
   @Temporal(DATE)
   @DateTimeFormat(style = "yyyy-MM-dd")
   private Date fecha;
   @Column(name = "c_status_activo")
   @NotNull(message = "El estatus esta nulo")
   @Positive
   private String status;
   private String equivalencia_peso;
   private String equivalencia_dolar;

   @PrePersist
   public void prePersist() {

      this.fecha= new Date();
   }

}
