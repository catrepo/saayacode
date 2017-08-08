/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msp_ficha_familiar;

import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class TipoDocumentoIdentidad extends Pantalla{
    
   
    Tabla tab_tip_docu_identidad=new Tabla();
            

    public TipoDocumentoIdentidad() {
        
        tab_tip_docu_identidad.setId("tab_tip_docu_identidad");
        tab_tip_docu_identidad.setTabla("msp_tipo_docu_identidad","ide_mstdi",1);
        tab_tip_docu_identidad.dibujar();
        PanelTabla pt_tip_docu_identidad=new PanelTabla();
        pt_tip_docu_identidad.setPanelTabla(tab_tip_docu_identidad);
        Division div_tip_docu_identidad=new Division();
        div_tip_docu_identidad.setId("div_tip_docu_identidad");
        div_tip_docu_identidad.dividir1(pt_tip_docu_identidad);
        agregarComponente(div_tip_docu_identidad);
        
        
    }

     @Override
    public void eliminar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_tip_docu_identidad.eliminar();
    }

    @Override
    public void guardar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_tip_docu_identidad.guardar();
        guardarPantalla();
    }

    @Override
    public void insertar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_tip_docu_identidad.insertar();
    }

    public Tabla getTab_tip_docu_identidad() {
        return tab_tip_docu_identidad;
    }

    public void setTab_tip_docu_identidad(Tabla tab_tip_docu_identidad) {
        this.tab_tip_docu_identidad = tab_tip_docu_identidad;
    }
    
    
    
    
    
    
    
}
