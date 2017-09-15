/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura.ejb;

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
        sql="select ide_ystpea, descripcion_ystpea, fecha_inicio_ystpea, fecha_fianal_ystpera from yavirac_stror_periodo_academic  where activo_ystpea in ("+activo+") order by fecha_inicio_ystpea desc";
        return sql;
    }
    
       /**
     * Retorna la jornada vigente
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de la jornada
     */
    public String getJornada(String activo) {
        String sql="";
        sql="select ide_ystjor, descripcion_ystjor from yavirac_stror_jornada  where activo_ystjor in ("+activo+") order by descripcion_ystjor desc";
        return sql;
    }   
    
              /**
     * Retorna la modalidad
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getModalidad(String activo) {
        String sql="";
        sql="select ide_ystmod, descripcion_ystmod from yavirac_stror_modalidad  where activo_ystmod in ("+activo+") order by descripcion_ystmod desc";
        return sql;
    }
    
    public String getCodigoMaximoTabla(String tabla, String primario){
        String sql="";
        sql="select 1 as codigo, (case when  max("+primario+") is null then 1 else max("+primario+") end) + 1 as maximo from "+tabla;
        return sql;
    }
}
