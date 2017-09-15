/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Janeth Pullotasig
 */
public class TipoPersonal extends Pantalla {
    private Tabla tab_tipopersonal = new Tabla();
 
   public TipoPersonal() {
       
       tab_tipopersonal.setId("tab_tipopersonal");
        tab_tipopersonal.setTabla("tipopersonal","ide_yastpe",1);
        tab_tipopersonal.setHeader("Tipo Personal");
        tab_tipopersonal.dibujar();
        
        
        PanelTabla pat_tipopersonal = new PanelTabla();
        pat_tipopersonal.setId("pat_tipopersonal");
        pat_tipopersonal.setPanelTabla(tab_tipopersonal);
        
       
        Division div_tipopersonal = new Division();
        div_tipopersonal.setId("div_tipopersonal");
        div_tipopersonal.dividir1(pat_tipopersonal);
        
       agregarComponente(div_tipopersonal);   
    }
   
   @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_tipopersonal() {
        return tab_tipopersonal;
    }

    public void setTab_tipopersonal(Tabla tab_tipopersonal) {
        this.tab_tipopersonal = tab_tipopersonal;
    }
    
    
}
