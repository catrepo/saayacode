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
public class Anio extends Pantalla{

    Tabla tab_anio=new Tabla();
    
    public Anio(){
        
        tab_anio.setId("tab_anio");
        tab_anio.setTabla("msp_anio","ide_msani",1);
                tab_anio.getColumna("imagen").setUpload();
        //tab_anio.getColumna("imagen").setImagen();
        tab_anio.dibujar();
        
        PanelTabla pat_anio=new PanelTabla();
        pat_anio.setPanelTabla(tab_anio);
        
        Division div_anio=new Division();
        div_anio.setId("div_anio");
        div_anio.dividir1(pat_anio);
        
        agregarComponente(div_anio);
    }

    @Override
    public void eliminar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_anio.eliminar();
    }

    @Override
    public void guardar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_anio.guardar();
        guardarPantalla();
    }

    @Override
    public void insertar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_anio.insertar();
    }
    
    
    


    public Tabla getTab_anio() {
        return tab_anio;
    }

    public void setTab_anio(Tabla tab_anio) {
        this.tab_anio = tab_anio;
    }
    
    
    
}
