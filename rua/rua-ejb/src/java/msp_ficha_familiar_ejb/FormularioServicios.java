/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msp_ficha_familiar_ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author oscarpatricio
 */
@Stateless
public class FormularioServicios {
    
    public String getSqlAdministracionZonal(){
        
    String sql=""; 
    sql= "select id_admin,cod,descripcion from administracion_zonal order by cod";
   
        return sql;
    }       
    public String getSqlCausaMortalidad(){
        
    String sql=""; 
    sql= "select id_cau_mor,cod_cau_mor,descripcion from causa_mortalidad  order by cod_cau_mor";
   
        return sql;
    }    
    
    public String getSqlMaterialPiso(){
        
    String sql=""; 
    sql= "select id_mat_pis,cod_mat_pis,descripcion from material_piso  order by cod_mat_pis";
   
        return sql;
    }
        public String getSqlMaterialPared(){
        
    String sql=""; 
    sql= "select id_mat_par,cod_mat_par,descripcion from material_pared order by cod_mat_par";
   
        return sql;
    }
    
        public String getSqlEstadoTecho(){
        
    String sql=""; 
    sql= "select id_est_tech,cod_est_tech,descripcion from estado_techo order by cod_est_tech";
   
        return sql;
    }
        public String getSqlEstadoPiso(){
        
    String sql=""; 
    sql= "select id_est_pis,cod_est_pis,descripcion from estado_piso order by cod_est_pis";
   
        return sql;
    }
        public String getSqlProcedenciaAgua(){
        
    String sql=""; 
    sql= "select id_pro_agua,cod_pro_agu,descripcion from procedencia_agua  order by cod_pro_agu";
   
        return sql;
    }  
        public String getSqlRecibeAgua(){
        
    String sql=""; 
    sql= "select id_rec_agu,cod_rec_agu,descripcion from recibe_agua order by cod_rec_agu";
   
        return sql;
    } 
        public String getSqlTratamientoAgua(){
        
    String sql=""; 
    sql= "select id_tra_agu,cod_tra_agu,descripcion from tratamiento_agua order by cod_tra_agu";
   
        return sql;
    } 
        public String getSqlEliminaAgua(){
        
    String sql=""; 
    sql= "select id_elim_agu_ser,cod_agua_ser,descripcion from eliminar_agua_ser  order by cod_agua_ser";
   
        return sql;
    }         
        public String getSqlUbicacionLetrete(){
        
    String sql=""; 
    sql= "select id_ubi_let,cod_ubi_let,descripcion from ubicacion_letrete order by cod_ubi_let";
   
        return sql;
    }  
        public String getSqlEliminarBasura(){
        
    String sql=""; 
    sql= "select id_eli_bas,cod_eli_bas,descripcion from eliminar_basura order by cod_eli_bas";
   
        return sql;
    } 
        public String getSqlUnidadOperativa(){
        
    String sql=""; 
    sql= "select id_unid_oper,cod_uni_oper,nombre_oficial,nombre_comun from unidad_operativa order by cod_uni_oper";
   
        return sql;
    }
        public String getSqlTipoTransporte(){
        
    String sql=""; 
    sql= "select id_tip_trans,cod_tip_trans,descripcion from tipo_transp order by cod_tip_trans";
   
        return sql;
    }
        public String getSqlCondicionOcupacion(){
        
    String sql=""; 
    sql= "select id_cond_ocup,cod_ocup,descripcion from condicion_ocupacion order by cod_ocup";
   
        return sql;
    }  
        public String getSqlViasAcceso(){
        
    String sql=""; 
    sql= "select id_via_acc,cod_via_acc,descripcion from vias_acceso  order by cod_via_acc ";
   
        return sql;
    } 
        public String getSqlTipoVivienda(){
        
    String sql=""; 
    sql= "select id_tip_viv_id,cod_tip_viv,descripcion from tipo_vivienda order by cod_tip_viv";
   
        return sql;
    }   
        public String getSqlMaterialTecho(){
        
    String sql=""; 
    sql= "select id_mat_tec,cod_mat_tec,descripcion from material_techo order by cod_mat_tec";
   
        return sql;
    } 
        public String getSqlCombustibleCocinar(){
        
    String sql=""; 
    sql= "select id_comb_coc,cod_coc,descripcion from combustible_cocinar  order by cod_coc";
   
        return sql;
    } 
        public String getSqlNacionalidad(){
        
    String sql=""; 
    sql= "select id_nac,cod_nac,descripcion from nacionalidad order by cod_nac";
   
        return sql;
    }    
        public String getSqlEstadoCivil(){
        
    String sql=""; 
    sql= "select id_est_civ,cod_est_civ,descripcion from estado_civil order by cod_est_civ";
   
        return sql;
    }      
        public String getSqlEtnia(){
        
    String sql=""; 
    sql= "select id_etn,cod_etn,descripcion from etnia order by cod_etn";
   
        return sql;
    }  
        public String getSqlSeguroPublico(){
        
    String sql=""; 
    sql= "select id_seg_pub,cod_seg_pub,descripcion from seguro_publico order by cod_seg_pub";
   
        return sql;
    }   
        public String getSqlClasificacionDiagnostico(){
        
    String sql=""; 
    sql= "select id_claf_diag,cod_claf_diag,descripcion from clasif_diagnostico order by cod_claf_diag";
   
        return sql;
    } 
        public String getSqlNacionalidades(){
        
    String sql=""; 
    sql= "select id_nacs,cod_nacs,descripcion from nacionalidades order by cod_nacs";
   
        return sql;
    } 
        public String getSqlNivelInstruccion(){
        
    String sql=""; 
    sql= "select id_niv_inst,cod_niv_inst,descripcion from nivel_instruccion order by cod_niv_inst";
   
        return sql;
    }  
        public String getSqlPueblos(){
        
    String sql=""; 
    sql= "select id_pue,cod_pue,descripcion from pueblos order by cod_pue";
   
        return sql;
    }   
        public String getSqlActividadTrabajo(){
        
    String sql=""; 
    sql= "select id_act_trab,cod_act_trab,descripcion from actividad_trab order by cod_act_trab";
   
        return sql;
    }  
        public String getSqlParentescoJefeHogar(){
        
    String sql=""; 
    sql= "select id_par_jh,cod_par_jh,descripcion from parentesco_jefe_hogar order by cod_par_jh";
   
        return sql;
    }      
        public String getSqlProvinciaCantonParroquia(){
        
    String sql=""; 
    sql= "select id_parroquia,cod_parr,descripcion,canton,provincia from parroquia a, ( select id_canton, a.descripcion as canton,b.descripcion as provincia "
            + "from canton a,provincia b where a.id_provincia = b.id_provincia ) b where a.id_canton = b.id_canton order by cod_parr";
   
        return sql;
    }  
 
        public String getSqlDistrito(){
        
    String sql=""; 
    sql= "select id_distrito,cod_distrito,descripcion from distrito order  by cod_distrito";
   
        return sql;
    }
        public String getSqlProvincia(){
        
    String sql=""; 
    sql= "select id_provincia,cod_prov,descripcion from provincia order by cod_prov";
   
        return sql;
    } 
        public String getSqlCanton(){
        
    String sql=""; 
    sql= "select id_canton,cod_cant,descripcion from canton order by cod_cant";
   
        return sql;
    }        
        public String getSqlParroquia(){
        
    String sql=""; 
    sql= "select id_parroquia,cod_parr,descripcion from parroquia order by cod_parr";
   
        return sql;
    }                
        public List desicion(){
            List desicion = new ArrayList();
        
            Object lista[] ={
              "1","Si"
            };
            Object lista2[] ={
              "2","No"
            };
            desicion.add(lista);
            desicion.add(lista2);
            return desicion;
        }
        public List listSexo(){
            List desicion = new ArrayList();
        
            Object lista[] ={
              "1","Masculino"
            };
            Object lista2[] ={
              "2","Femenino"
            };
            desicion.add(lista);
            desicion.add(lista2);
            return desicion;
        }        
 }
