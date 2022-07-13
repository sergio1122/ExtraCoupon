package com.ExtraCoupon.app.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_config_general")
@Builder
public class Configuraciones {
    @Column(name = "idt_config_general")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String montoMinOperacion_T;
    private String montoMaxOperacion_T;
    private String base_comision_nacional;
    private String c_monto_porcentaje_nacional_menor;
    private String comisionEnvioNacional_menor;
    private String c_monto_porcentaje_nacional_mayor;
    private String comisionEnvioNacional_mayor;
    private String base_comision_internacional;
    private String c_monto_porcentaje_internacional_menor;
    private String comisionEnvioInternacional_menor;
    @NotNull(message = "el monto percentaje internacional mayor es nulo")
    @Column(name = "c_monto_porcentaje_internacional_mayor")
    private String porcentaje_internacional_mayor;
    private String comisionEnvioInternacional_mayor;
    private String montoMinOperacion_C;
    private String montoMaxOperacion_C;
    private String montoMinOperacion_L;
    private String montoMaxOperacion_L;
    private String comisionDivisaCompra;
    private String comisionDivisaVenta;
    private String comisionTarjetaNacional_F;
    private String comisionTarjetaInternacional_F;
    private String montoMaxFondeoMes_nivelUno;
    private String montoMaxFondeoMes_nivelDos;
    private String montoMaxFondeoMes_nivelTres;
    private String comisionRetiro_afiliado;
    private String comisionRetiro_extracoupon;
    private String montoMaxRetiroDia_nivelUno;
    private String montoMaxRetiroDia_nivelDos;
    private String montoMaxRetiroDia_nivelTres;
    private String c_status_envioInternacional;
    private String c_status_recargasTerjeta;
    private String montoMaxCuenta_nivelUno;
    private String montoMaxCuenta_nivelDos;
    private String montoMaxCuenta_nivelTres;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fechaActualizacion;
    @Column(name = "c_status_activo")
    private String status;
    @Column(name = "c_pais")
    private String pais;
    private String t_usuarios_admin;
    private String beneficio_registro;
    @Column(name = "c_monto_porcentaje_comisionPrepago_nacional")
    private String comision_prepago_nacional;
    @Column(name = "comisionAbonoPrepago_tarjetaNacional")
    private String comision_abono_prepago_tarjeta_nacional;
    @Column(name = "c_monto_porcentaje_comisionPrepago_internacional")
    private String comision_prepago_internacional;
    @Column(name = "comisionAbonoPrepago_tarjetaInternacional")
    private String abono_prepago_tarjeta_internacional;
    private String montoMinimo_fondeo_prepago;
    private String montoMaximo_fondeo_prepago;
    @Column(name = "c_monto_porcentaje_comisionRetiro_prepago")
    private String comisionRetiro_prepago;
    private String comisionRetiro_saldoPrepago;
    private String montoMinimo_retiro_prepago;
    private String montoMaximo_retiro_prepago;
    private String montoMaximo_cuentaPrepago;
    @Column(name = "c_monto_porcentaje_comisionPagoServicio_prepagoAfiliado")
    private String comision_pago_servicio_prepago_afiliado;
    @Column(name = "comisionPagoServicio_saldoPrepagoAfiliado")
    private String pago_servicio_saldo_prepago_afiliado;
    @Column(name = "IVA")
    private String iva;




}
