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
public class ServicioHoraHora {
    
                  /**
     * Retorna el periodo academico vigente
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del periodo academico
     */
    public String getHora(String activo) {
        String sql="";
        sql="select ide_yhohor, descripcion_yhohor from yavirac_hora_hora  where activo_yhohor in ("+activo+") order by descripcion_yhohor desc";
        return sql;
    }
    
}
