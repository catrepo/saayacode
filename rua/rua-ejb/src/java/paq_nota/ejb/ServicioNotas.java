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
    
      /**
     * Retorna periodo tipo evaluacion
     *
     * @param codigo.- permite crear una tabla maestro segun el periodo academico.
     * @return sql del tipo de evaluacion
     */
    public String getperiodotipoevaluacion(String codigo ) {
        String sql="";
        sql="select pe.ide_ynopee,t.descripcion_ynotie from yavirac_stror_periodo_academic p\n" +
        "join yavirac_nota_periodo_evaluacio pe\n" +
        "on p.ide_ystpea=pe.ide_ystpea\n" +
        "join yavirac_nota_tipo_evaluacion t\n" +
        "on t.ide_ynotie = pe.ide_ynotie\n" +
        "where p.ide_ystpea in ("+codigo+")";
        return sql;
    }
    
    
     /**
     * Retorna periodo tipo evaluacion
     *
     * @param codigo.- permite crear una tabla maestro segun el periodo academico.
     * @return sql del tipo de evaluacion
     */
    public String getFiltroperiodotipoevaluacion(String peridoacademico ) {
        String sql="";
        sql+="select ide_ynopee,descripcion_ynotie\n" +
            "From yavirac_nota_periodo_evaluacio b \n" +
            "join yavirac_nota_tipo_evaluacion c on c.ide_ynotie = b.ide_ynotie where ide_ystpea in ("+peridoacademico+")";
        return sql;
    }
    
      /**
     * Retorna periodo tipo evaluacion
     *
     * @param periodoacademico.- permite crear una tabla maestro segun el periodo academico.
     * @return sql del tipo de evaluacion
     */
    public String getPeriodoActividadEvaluacion(String periodoacademico,String tipo ) {
        String sql="";
        sql+="select ide_ynopae,a.descripcion_ynoace,t.descripcion_ynotie\n" +
        "from yavirac_nota_periodo_activ_eva p\n" +
        "join  yavirac_nota_actividad_evaluac a on p.ide_ynoace = a.ide_ynoace\n" +
        "join yavirac_nota_periodo_evaluacio e on e.ide_ynopee = p.ide_ynopee\n" +
        "join yavirac_nota_tipo_evaluacion t on t.ide_ynotie = e.ide_ynotie\n";
        if(tipo.equals("1")){        
          sql+="where e.ide_ystpea in ("+periodoacademico+")";
        }
        return sql;
    }
    
     /**
     * Retorna periodo tipo evaluacion
     *
     * @param codigo.- permite crear una tabla maestro segun el periodo academico.
     * @return sql del tipo de evaluacion
     */
    public String getPersonMallaDocente(String codigo ) {
        String sql="";
        sql+="select ide_ypemad,ide_ystpea, ide_ystmen, b.ide_ystnie, ide_ypedpe, ide_yhogra, ide_ystjor,b.ide_ystmal\n" +
        "from yavirac_perso_malla_docente a\n" +
        "join yavirac_stror_malla b on a.ide_ystmal = b.ide_ystmal\n" +
        "join yavirac_stror_nivel_educacion c on b.ide_ystnie = c.ide_ystnie\n" +
        "where a.ide_ypemad in ("+codigo+")";
        return sql;
    }
    
     /**
     * Retorna Descripción Periodo Evaluación
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getPerioEvaluac(String activo) {
        String sql="";
        sql="select ide_ynopee,descripcion_ynotie\n" +
            "From yavirac_nota_periodo_evaluacio b \n" +
            "join yavirac_nota_tipo_evaluacion c on c.ide_ynotie = b.ide_ynotie where activo_ynotie in ("+activo+")";
        return sql;
    }
     
    
}



