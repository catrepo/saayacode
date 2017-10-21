/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_alumno.ejb;

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
public class ServicioAlumno {

     /**
     * Consultamos los datos del alumno 
     *
     * @return los datos del alumno
     */
    public String getDatosAlumnos(String estado) {
        String sql="";
        sql="Select ide_yaldap,nombre_yaldap,apellido_yaldap,doc_identidad_yaldap FROM yavirac_alum_dato_personal where activo_yaldap in ("+estado+") order by nombre_yaldap";
        return sql;
    }
    
    
}