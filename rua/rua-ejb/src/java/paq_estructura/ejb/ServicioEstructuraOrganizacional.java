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
              /**
     * Retorna la Nacionalidad
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getNacionalidad(String activo) {
        String sql="";
        sql="SELECT ide_ystnac, descripcion_ystnac FROM yavirac_stror_nacionalidad where activo_ystnac in ("+activo+")";
        return sql;
    } 
      /**
    * Retorna el Tipo de Sangre
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getTipoSangre(String activo) {
        String sql="";
        sql="SELECT ide_ysttis, descripcion_ysttis FROM yavirac_stror_tipo_sangre where activo_ysttis in ("+activo+")";
        return sql;
    }    
/**
    * Retorna el Documento de Identidad
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getDocumentoIdentidad(String activo) {
        String sql="";
        sql="SELECT ide_ystdoi, descripcion_ystdoi FROM yavirac_stror_docu__identidad where activo_ystdoi in ("+activo+")";
        return sql;
    }  
    /**
    * Retorna la Distribucion Politica
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getDistribucionPolitica(String activo) {
        String sql="";
        sql="SELECT ide_ystdip, ide_ysttdp, descripcion_ystdip FROM yavirac_stror_distribucion_pol where activo_ystdip in ("+activo+")";
        return sql;
    }    
    /**
    * Retorna el Estado Civil
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getEstadoCivil(String activo) {
        String sql="";
        sql="SELECT ide_ystesc, descripcion_ystesc FROM yavirac_stror_estado_civil where activo_ystesc in ("+activo+")";
        return sql;
    }    
        public String getMension() {
        String sql="";
        sql="SELECT ide_ystmen, descripcion_ystmen FROM yavirac_stror_mension ";
        return sql;
    } 
        public String getNivelEducacion() {
        String sql="";
        sql="select ide_ystnie, descripcion_ystnie from yavirac_stror_nivel_educacion ";
        return sql;
    } 
        public String getParentezcoFamiliar(String activo) {
        String sql="";
        sql="SELECT ide_ystpaf, descripcion_ystpaf  FROM yavirac_stror_parentezco_fami where activo_ystpaf in ("+activo+") ";
        return sql;
    } 
}
