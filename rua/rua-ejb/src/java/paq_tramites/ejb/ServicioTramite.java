/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_tramites.ejb;

import paq_titulacion.ejb.*;
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
public class ServicioTramite {

  /**
     * Dervuelve el sql de tipo documento
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlDocumento() {
        String sql="";
        sql="select ide_ytrdoc,nombre_doc_ytrdoc from yavirac_tra_documento order by nombre_doc_ytrdoc";
        return sql;
    }  
/**
     * Dervuelve el sql de tipo entidad
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlTipoEntidad() {
        String sql="";
        sql="select ide_ytrtie,nombre_ytrtie,codigo_abreviatura_ytrtie from yavirac_tra_tipo_entidad order by nombre_ytrtie";
        return sql;
    }
/**
     * Dervuelve el sql de tipo entidad
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlTipoDocumento() {
        String sql="";
        sql="select ide_ytrtid,nombre_ytrtid from yavirac_tra_tipo_documento order by nombre_ytrtid";
        return sql;
    }    
}