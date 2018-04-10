/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_personal.ejb;

import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
/**
 *
 * @author Nicolas Cajilema
 */
@Stateless
@LocalBean
public class ServicioPersonal {

     /**
     * Insertar en la tabla Biometrico
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getDatopersonal (String activo) {
        String sql="";
        sql=" select ide_ypedpe,apellido_ypedpe,nombre_ypedpe,doc_identidad_ypedpe from yavirac_perso_dato_personal where activo_ypedpe in ("+activo+") order by apellido_ypedpe";
          
        return sql;
    }
      /**
     * Insertar en la tabla Biometrico
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getDatoPersonalCodigo (String ide_personal) {
        String sql="";
        sql=" select ide_ypedpe,apellido_ypedpe,nombre_ypedpe,doc_identidad_ypedpe,codigo_reloj_ypedpe from yavirac_perso_dato_personal where ide_ypedpe in ("+ide_personal+")";
        return sql;
    }   
    public String getTipoProfesional() {
          
        String sql="";
        sql="select ide_ypetip,descripcion_ypetip from yavirac_perso_titu_profesional";
        return sql;
 }
 
 
    
}
