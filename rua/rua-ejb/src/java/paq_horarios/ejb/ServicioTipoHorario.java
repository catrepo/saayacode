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
public class ServicioTipoHorario {
    
           /**
     * Retorna el periodo academico vigente
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del periodo academico
     */
    public String getHorarios(String activo) {
        String sql="";
        sql="select ide_yhotih, descripcion_yhotih from yavirac_hora_tipo_horario  where activo_yhotih in ("+activo+") order by descripcion_yhotih ";
        return sql;
    }
}
