/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion.ejb;

import paq_asistencia.ejb.*;
import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Janeth Pullotasig and  Nicolas Cajilema
 */
@Stateless
public class ServicioTitulacion {

     /**
     * Dervuelve el sql de tipo emprea
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlTipoEmpresa() {
        String sql="";
        sql="select ide_ytitie,descripcion_ytitie from yavirac_titu_tipo_empresa order by descripcion_ytitie";
        return sql;
    }
    public String getSqlActividadEconomica() {
        String sql="";
        sql="select ide_ytiace, decripcion_ytiace from yavirac_titu_actividad_economic order by decripcion_ytiace";
        return sql;
    }
   public String getSqlTipoProducto() {
        String sql="";
        sql="select ide_ytitip, descripcion_ytitip from yavirac_titu_tipo_producto order by descripcion_ytitip";
        return sql;
    } 
       public String getSqlTipoEntidad() {
        String sql="";
        sql="select ide_ytitie, descripcion_ytitie from yavirac_titu_tipo_entidad order by descripcion_ytitie";
        return sql;
}
       public String getSqlDistribucionPolitica() {
        String sql="";
        sql="select ide_ystdip, descripcion_ystdip from yavirac_stror_distribucion_pol order by descripcion_ystdip";
        return sql;
}
}
