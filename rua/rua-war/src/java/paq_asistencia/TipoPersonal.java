/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;

/**
 *
 * @author Janeth Pullotasig
 */
public class TipoPersonal {
    private Tabla tab_tipopersonal = new Tabla();
 
   public TipoPersonal() {
       
       tab_tipopersonal.setId("tab_tipopersonal");
        tab_tipopersonal.setTabla("tipopersonal","ide_yastpe",1);
        tab_tipopersonal.dibujar();
        
        
        PanelTabla pat_tipopersonal = new PanelTabla();
        pat_tipopersonal.setId("pat_tipopersonal");
        pat_tipopersonal.setPanelTabla(tab_tipopersonal);
        
       
        Division div_tipopersonal = new Division();
        div_tipopersonal.setId("div_tipopersonal");
        div_tipopersonal.dividir1(pat_tipopersonal);
        
       // agregarComponente (div_tipopersonal);   
    }
   
   
    
    
}
