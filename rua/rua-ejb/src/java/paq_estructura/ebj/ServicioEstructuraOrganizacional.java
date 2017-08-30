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
public class ServicioEstructuraOrganizacional {

     /**
     * Retorna el periodo academico vigente
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del periodo academico
     */
    public String getPeriodoAcademico(String activo) {
        String sql="";
        sql="select * from yavirac_stror_periodo_academic  where activo_ystpea in ("+activo+") order by fecha_inicio_ystpea desc";
        return sql;
    }
    
}
