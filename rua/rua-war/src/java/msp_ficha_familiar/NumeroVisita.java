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
public class NumeroVisita extends Pantalla{
    
    Tabla tab_numVisita=new Tabla();
    
            

    public NumeroVisita() {
        
        tab_numVisita.setId("tab_numVisita");
        tab_numVisita.setTabla("msp_nro_visita", "ide_msnrv", 1);
        tab_numVisita.dibujar();
        
        PanelTabla pt_numVisita=new PanelTabla();
   pt_numVisita.setPanelTabla(tab_numVisita);
   Division div_numVisita=new Division();
   div_numVisita.setId("div_numVisita");
   div_numVisita.dividir1(pt_numVisita);
        agregarComponente(div_numVisita);
        
        
        
    }
    
    
    
      @Override
    public void eliminar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_numVisita.eliminar();
    }

    @Override
    public void guardar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_numVisita.guardar();
        guardarPantalla();
    }

    @Override
    public void insertar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_numVisita.insertar();
    }


    public Tabla getTab_numVisita() {
        return tab_numVisita;
    }

    public void setTab_numVisita(Tabla tab_numVisita) {
        this.tab_numVisita = tab_numVisita;
    }
    
    
    
    
    
}
