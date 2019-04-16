/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion.ejb;
import paq_asistencia.ejb.*;
import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Janeth Pullotasig and  Nicolas Cajilema
 */
@Stateless
public class ServicioTitulacion {

     /**
     * Dervuelve el sql de tipo emprea
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSqlTipoEmpresa() {
        String sql="";
        sql="select ide_ytitie,descripcion_ytitie from yavirac_titu_tipo_empresa order by descripcion_ytitie";
        return sql;
    }
    public String getSqlActividadEconomica() {
        String sql="";
        sql="select ide_ytiace, descripcion_ytiace from yavirac_titu_actividad_economic order by descripcion_ytiace";
        return sql;
    }
   public String getSqlTipoProducto() {
        String sql="";
        sql="select ide_ytitip, descripcion_ytitip from yavirac_titu_tipo_producto order by descripcion_ytitip";
        return sql;
    } 
       public String getSqlTipoEntidad() {
        String sql="";
        sql="select ide_ytiten, descripcion_ytiten from yavirac_titu_tipo_entidad order by descripcion_ytiten";
        return sql;
}     /**
     * Dervuelve el sql de tipo persona vinculacion
     *
     * 
     * @return la Tabla tip_per
     */
    public String getSqlTipoPersonaVincula() {
        String sql="";
        sql="select ide_ytitpv, descripcion_ytitpv from yavirac_titu_tipo_per_vinc order by descripcion_ytitpv";
        return sql;
    }
   public String getEmpresa() {
          
        String sql="";
        sql="select ide_ytiemp,nombre_comercial_ytiemp from yavirac_titu_empresa";
        return sql;
 }
   public String getTipoEmpresa() {
          
        String sql="";
        sql="select ide_ytitie,descripcion_ytitie from yavirac_titu_tipo_empresa";
        return sql;
 }

      public String getActividadEconomica() {
          
        String sql="";
        sql="select ide_ytiace,descripcion_ytiace from yavirac_titu_actividad_economic";
        return sql;
 }
public String getDatoEmpresa() {
          
        String sql="";
        sql="select ide_ytiemp, nombre_comercial_ytiemp,ruc_ytiemp from yavirac_titu_empresa";
        return sql;
}

public String getViabilidad () {
String sql="";
sql="select ide_ytiviv,no_informe_ytiviv,fecha_ytiviv from yavirac_titu_viabilidad";
return sql;
    
    }
public String getLineaSupervision() {
String sql="";
sql="select ide_ytilii,detalle_ytilii from yavirac_titu_linea_investigacio";
return sql;
    
    }
public String getIntervaloTiempo() {
String sql="";
sql="select ide_ytiint,descripcion_ytiint from yavirac_titu_intervalo_tiempo order by descripcion_ytiint";
return sql;
    
    }
public String getIndicador() {
String sql="";
sql="select ide_ytiind,detalle_ytiind from yavirac_titu_indicador";
return sql;
    
    }
public String getMediosVerifica() {
String sql="";
sql="select ide_ytimev,detalle_ytimev from yavirac_titu_medios_verifica";
return sql;
}

public String getTipoObjetivo() {
String sql="";
sql="select ide_ytitio,detalle_ytitio from yavirac_titu_tipo_objetivo";
return sql;

}


public String getActividadResulta() {
String sql="";
sql="select ide_ytiacr,detalle_ytiacr from yavirac_titu_actividad_resulta";
return sql;

}

}

