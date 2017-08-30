/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horarios.ejb;

import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
/**
 *
 * @author Andres
 */
@Stateless
public class ServiciosHorarios {
    
       /**
     * Retorna el periodo academico vigente
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del periodo academico
     */
    public String getHorarios(String activo) {
        String sql="";
        sql="select ide_yhothj, descripcion_yhothj from yavirac_hora_tipo_horario_jorna  where activo_yhothj in ("+activo+") order by descripcion_yhothj desc";
        return sql;
    }
    
}
