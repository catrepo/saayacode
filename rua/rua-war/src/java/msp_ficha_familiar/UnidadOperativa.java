/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msp_ficha_familiar;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class UnidadOperativa extends Pantalla {
    
    
    private Tabla tab_unidad_operativa=new Tabla();

    public UnidadOperativa() {
        tab_unidad_operativa.setId("tab_unidad_operativa");
        tab_unidad_operativa.setTabla("msp_unidad_operativa", "ide_msuno",1);
        tab_unidad_operativa.dibujar();
        PanelTabla pt_unidad_operativa=new PanelTabla();
        pt_unidad_operativa.setPanelTabla(tab_unidad_operativa);
        Division div_unidad_operativa=new Division();
        div_unidad_operativa.setId("div_unidad_operativa");
        div_unidad_operativa.dividir1(tab_unidad_operativa);
        agregarComponente(div_unidad_operativa);
        
        
        
        
    }
    
     @Override
    public void eliminar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_unidad_operativa.eliminar();
    }

    @Override
    public void guardar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_unidad_operativa.guardar();
        guardarPantalla();
    }

    @Override
    public void insertar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_unidad_operativa.insertar();
    }

    public Tabla getTab_unidad_operativa() {
        return tab_unidad_operativa;
    }

    public void setTab_unidad_operativa(Tabla tab_unidad_operativa) {
        this.tab_unidad_operativa = tab_unidad_operativa;
    }
    
    
    
}
