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
        sql="SELECT ide_ystdoi, descripcion_ystdoi FROM yavirac_stror_docu_identidad where activo_ystdoi in ("+activo+")";
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
    * Retorna la Tipo Division Politica
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de modalidad
     */
    public String getTipoDivisionPolitica(String activo) {
        String sql="";
        sql="SELECT ide_ysttdp ,descripcion_ysttdp FROM yavirac_stror_tipo_divisio_po where activo_ysttdp in ("+activo+")";
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
        sql="SELECT ide_ystmen, descripcion_ystmen ,detalle_ysttfe FROM yavirac_stror_mension  a, yavirac_stror_tipo_for_educaci b where a.ide_ysttfe = b.ide_ysttfe";
        return sql;
    } 
        public String getTipoInstitucion(String activo) {
        String sql="";
        sql="SELECT ide_ysttii, descripcion_ysttii FROM yavirac_stror_tipo_institucion where activo_ysttii in ("+activo+")order by descripcion_ysttii";
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
        public String getTipoFormacionEducativa() {
        String sql="";
        sql="SELECT ide_ysttfe, detalle_ysttfe,abreviatura_ysttfe  FROM yavirac_stror_tipo_for_educaci order by detalle_ysttfe";
        return sql;
    } 
        public String getGenero(String activo) {
        String sql="";
        sql="select ide_ystgen,descripcion_ystgen from yavirac_stror_genero where activo_ystgen in ("+activo+")";
        return sql;
    } 
        public String getDocumentoRequerido(String activo) {
        String sql="";
        sql="select ide_ystdor,descripcion_ystdor,abreviatura_ystdor from yavirac_stror_documento_reque where activo_ystdor in ("+activo+") order by descripcion_ystdor";
        return sql;
    }  
        public String getRequeridoPara() {
        String sql="";
        sql="select ide_ystrep,descripcion_ystrep,abreviatura_ystrep from yavirac_stror_requerido_para order by descripcion_ystrep";
        return sql;
    }  
        public String getDocumentoRequeridoPeriodo(String periodo,String req_para) {
        String sql="";
        sql="select ide_ystdop,a.ide_ystdor,descripcion_ystdor,ide_ystrep from yavirac_stror_documento_periodo a,yavirac_stror_documento_reque b\n" +
            " where a.ide_ystdor = b.ide_ystdor" +
            " and a.ide_ystpea ="+periodo +
            " and ide_ystrep = " +req_para+
            " order by a.ide_ystdor ";
        return sql;
    }   
    public String deleteBloqueos(String ide_usua) {
        String sql="";
        sql="delete from sis_bloqueo where ide_usua ="+ide_usua;
        return sql;
    }  
        public String getMaterias() {
        String sql="";
        sql="SELECT ide_ystmat, detalle_ystmat,abreviatura_ystmat  FROM yavirac_stror_materia order by detalle_ystmat";
        return sql;
    } 
        public String getTipoFormacion() {
        String sql="";
        sql="SELECT ide_ysttif, detalle_ysttif,abreviatura_ysttif  FROM yavirac_stror_tipo_formacion order by detalle_ysttif";
        return sql;
    }       
        public String getUsuarioSistema(String ide_usua,String condicion) {
        String sql="";
        sql="select ide_usua,nom_usua,nick_usua,ide_ypedpe from sis_usuario where ide_usua in("+ide_usua+") "+condicion;
        return sql;
    }    
       public String getMalla() {
        String sql="";
        sql="select a.ide_ystmal,descripcion_ystnie,detalle_ystmat,descripcion_ystmen " +
            " from yavirac_stror_malla a, yavirac_stror_nivel_educacion b,yavirac_stror_mension c,yavirac_stror_materia d" +
            " where a.ide_ystnie = b.ide_ystnie" +
            " and a.ide_ystmen = c.ide_ystmen" +
            " and a.ide_ystmat = d.ide_ystmat";
        return sql;
    }  
       
      public String getSexo() {
        String sql="";
        sql="select ide_ysttin,descripcion_ysttin from yavirac_stror_tipo_instalacion ";
        return sql;
        
}
            public String getDiscapacidad() {
        String sql="";
        sql="select ide_ysttid,descripcion_ysttid,activo_ysttid from yavirac_stror_tipo_discapacid";
        return sql;
            }
      
            public String getEtnia() {
          
        String sql="";
        sql="select ide_ystetn,detalle_ystetn from yavirac_stror_etnia";
        return sql;
            }
                        
             
    public String getGradoDiscapacidad() {
          
        String sql="";
        sql="select ide_ystgrd,descripcion_ystgrd,activo_ystgrd from yavirac_stror_grado_discapaci";
        return sql;
            
             }
 public String getTipoColegio() {
          
        String sql="";
        sql="select ide_ysttco,descripcion_ysttco from yavirac_stror_tipo_colegio";
        return sql;
 }
 public String getTipoBachillerato() {
          
        String sql="";
        sql="select ide_ysttba,descripcion_ysttba from yavirac_stror_tipo_bachillerato";
        return sql;
 }

 public String getTipoInstalacion() {
          
        String sql="";
        sql="select ide_ysttin,descripcion_ysttin from yavirac_stror_tipo_instalacion";
        return sql;
 }

 
  public String getVinculaSociedad() {
          
        String sql="";
        sql="select ide_ystvis,descripcion_ystvis from yavirac_stror_vinculacion_socie";
        return sql;
 }
   public String getInstalacion() {
          
        String sql="";
        sql="select ide_ystins,nombre_ystins from yavirac_stror_instalacion";
        return sql;
 }
    public String getFormacionEducativa() {
          
        String sql="";
        sql="select ide_ystfoe,descripcion_ystfoe from yavirac_stror_formacion_educati";
        return sql;
 }
      public String getCategoriaMigratoria() {
          
        String sql="";
        sql="select ide_ystcam,descripcion_ystcam from yavirac_stror_categoria_migrato";
        return sql;
 }
          public String getAnioPeriodoCarrera() {
          
        String sql="";
        sql="select ide_ystani,descripcion_ystani from yavirac_stror_anio";
        return sql;
 }
            public String getDistribucionPolitica() {
          
        String sql="";
        sql="select ide_ystdip,yav_ide_ystdip,descripcion_ystdip,activo_ystdip from yavirac_stror_distribucion_pol";
        return sql;
 }
 
}
