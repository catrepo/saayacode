/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author USER
 */
public class Carrera extends Pantalla{
    
    Tabla tab_carrera =new Tabla ();
   
    public Carrera (){
        tab_carrera.setId("tab_carrera");
        tab_carrera.setTabla("yavirac_stror_carrera", "ide_ystcrr",1 );
        tab_carrera.dibujar();
        
        PanelTabla pat_carrera =new PanelTabla();
        pat_carrera.setId("pat_carrera");
        pat_carrera.setPanelTabla(tab_carrera);
        
        Division div_carrera =new Division();
        div_carrera.setId("div_carrera");
        div_carrera.dividir1(pat_carrera);
        
        agregarComponente(div_carrera);
        
     }   

    @Override
    public void insertar() {
        tab_carrera.insertar();
    }

    @Override
    public void guardar() {
        tab_carrera.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_carrera.eliminar();
        
    }

    public Tabla getTab_carrera() {
        return tab_carrera;
    }

    public void setTab_carrera(Tabla tab_carrera) {
        this.tab_carrera = tab_carrera;
    }

    
}
