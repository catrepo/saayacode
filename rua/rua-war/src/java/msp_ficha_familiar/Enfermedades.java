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
public class Enfermedades extends Pantalla{
    
    private Tabla tab_enfermedad=new Tabla();
    

    public Enfermedades() {
        tab_enfermedad.setId("tab_enfermedad");
        tab_enfermedad.setTabla("msp_enfermedades_ciap_cie10", "ide_msenc", 1);
        tab_enfermedad.dibujar();
        PanelTabla pt_enfermadad=new PanelTabla();
        pt_enfermadad.setPanelTabla(tab_enfermedad);
        Division div_enfermedad=new Division();
        div_enfermedad.setId("div_enfermedad");
        div_enfermedad.dividir1(pt_enfermadad);
        agregarComponente(div_enfermedad);
        
        
        
        
    }
    
    
    
          @Override
    public void eliminar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_enfermedad.eliminar();
    }

    @Override
    public void guardar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_enfermedad.guardar();
        guardarPantalla();
    }

    @Override
    public void insertar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_enfermedad.insertar();
    }

    public Tabla getTab_enfermedad() {
        return tab_enfermedad;
    }

    public void setTab_enfermedad(Tabla tab_enfermedad) {
        this.tab_enfermedad = tab_enfermedad;
    }
    
    
    
    
    
}
