/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author usuario
 */
@Stateless

public class ServicioNotas {

     /**
     * Retorna el Tipo de Evaluacion
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getTipoEvaluacion(String activo) {
        String sql="";
        sql="select ide_ynotie,descripcion_ynotie from yavirac_nota_tipo_evaluacion  where activo_ynotie in ("+activo+")";
        return sql;
    }
    
     /**
     * Retorna la Actividad de Evaluacion
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getActividadEvaluacion(String activo) {
        String sql="";
        sql="select ide_ynoace,descripcion_ynoace from yavirac_nota_actividad_evaluac  where activo_ynoace in ("+activo+")";
        return sql;
    }
    
     /**
     * Retorna Periodo Evaluacion
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getPeriodoEvaluacion(String activo) {
        String sql="";
        sql="select ide_ynotie,descripcion_ynotie from yavirac_nota_tipo_evaluacion  where activo_ynotie in ("+activo+")";
        return sql;
    }
         /**
     * Retorna Remplazo de Gion
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getRemplazaG(String campo,String tabla) {
        String sql="";
        sql="select 1 as codigo,replace(replace('"+campo+"','-',''),' ','') as repa from "+tabla;
        return sql;
    }
    
         /**
     * Retorna Limite de caracteres
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getlimiteC(String campo) {
        String sql="";
        sql="select 1 as codigo,substring('"+campo+"' from 1  for 30) as nombre ";
        return sql;
    }
    
        /**
     * Retorna creacion de la tabla nota maestro
     *
     * @param tabla.- permite crear una tabla maestro segun el periodo academico.
     * @return sql del tipo de evaluacion
     */
    public String gettabnotamaestro(String tabla) {
        String sql="";
        sql="create table "+tabla+" ( ide_ynotma integer,primary key (ide_ynotma))";
        return sql;
    }
}
