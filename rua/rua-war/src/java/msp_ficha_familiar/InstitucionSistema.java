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
public class InstitucionSistema extends Pantalla{
    
    private Tabla tab_institucion_sistema=new Tabla();
            
    

    public InstitucionSistema() {
        tab_institucion_sistema.setId("tab_institucion_sistema");
        tab_institucion_sistema.setTabla("msp_institucion_sistema", "ide_msins",1 );
        tab_institucion_sistema.dibujar();
        PanelTabla pt_institucion_sistema=new PanelTabla();
        pt_institucion_sistema.setPanelTabla(tab_institucion_sistema);
        Division div_institucion_sistema=new Division();
        div_institucion_sistema.setId("div_institucion_sistema");
        div_institucion_sistema.dividir1(pt_institucion_sistema);
        agregarComponente(div_institucion_sistema);
        
        
    }
    
          @Override
    public void eliminar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_institucion_sistema.eliminar();
    }

    @Override
    public void guardar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_institucion_sistema.guardar();
        guardarPantalla();
    }

    @Override
    public void insertar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_institucion_sistema.insertar();
    }

    public Tabla getTab_institucion_sistema() {
        return tab_institucion_sistema;
    }

    public void setTab_institucion_sistema(Tabla tab_institucion_sistema) {
        this.tab_institucion_sistema = tab_institucion_sistema;
    }
    
    

    
    
    
    
    
    
    
    
    
    
}
