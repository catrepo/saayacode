/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msp_ficha_familiar_ejb;

import javax.ejb.Stateless;

/**
 *
 * @author oscarpatricio
 */
@Stateless
public class ParametrosFichaFamiliar {
    
    
    
    
    public String getSqlTipoBloqe(){
        
    String sql=""; 
    sql= "select a.ide_mstib, padre,detalle_mstib from msp_tipo_bloque a,"
            + " (select ide_mstib, detalle_mstib as padre from msp_tipo_bloque) b where a.msp_ide_mstib = b.ide_mstib "
            + " order by a.msp_ide_mstib,detalle_mstib";
        
        
        return sql;
    }
    
        public String getSqlTipoDivisionPolitica(){
        
    String sql=""; 
    sql= "select ide_mstdp,detalle_mstdp from msp_tipo_division_politica order by detalle_mstdp";
        
        
        return sql;
    }
}
