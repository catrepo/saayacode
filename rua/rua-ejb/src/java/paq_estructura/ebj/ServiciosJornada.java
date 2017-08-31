/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura.ebj;

import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
/**
 *
 * @author Andres
 */
@Stateless
public class ServiciosJornada {
    
              /**
     * Retorna el periodo academico vigente
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del periodo academico
     */
    public String getJornada(String activo) {
        String sql="";
        sql="select ide_ystjor, descripcion_ystjor from yavirac_stror_jornada  where activo_ystjor in ("+activo+") order by descripcion_ystjor desc";
        return sql;
    }
    
}
