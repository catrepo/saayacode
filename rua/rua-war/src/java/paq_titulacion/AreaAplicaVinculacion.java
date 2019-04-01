/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ANDRES
 */
public class AreaAplicaVinculacion extends Pantalla {
    private Tabla tab_area_aplica_vincul = new Tabla ();
    public AreaAplicaVinculacion () {
   tab_area_aplica_vincul.setId("tab_area_aplica_vincul");
   tab_area_aplica_vincul.setTabla("yavirac_titu_area_aplica_vincul","ide_ytiara",1);
   tab_area_aplica_vincul.setHeader("Area Aplica Vinculacion");
   tab_area_aplica_vincul.dibujar();
             
        
        
        PanelTabla pat_line= new PanelTabla();
        pat_line.setId("pat_line");
        pat_line.setPanelTabla(tab_area_aplica_vincul);
        
        Division div_area_aplica_vincul = new Division();
        div_area_aplica_vincul.setId("div_area_aplica_vincul");
        div_area_aplica_vincul.dividir1(pat_line);
        
        agregarComponente(div_area_aplica_vincul);
        
               
    }

    @Override
    public void insertar() {
        tab_area_aplica_vincul.insertar();

    }

    @Override
    public void guardar() {
       tab_area_aplica_vincul.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_area_aplica_vincul.eliminar(); 
    }
    

    public Tabla getTab_area_aplica_vincul() {
        return tab_area_aplica_vincul;
    }

    public void setTab_area_aplica_vincul(Tabla tab_area_aplica_vincul) {
        this.tab_area_aplica_vincul = tab_area_aplica_vincul;
    }
    
     
}
