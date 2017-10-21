/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_inscripcion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author USER
 */
public class preinscripcion extends Pantalla{
     
    Tabla tab_pre_inscrip  =new Tabla ();
    public preinscripcion (){
        tab_pre_inscrip.setId("tab_pre_inscrip");
        tab_pre_inscrip.setTabla("yavirac_ins_pre_inscrip" , "ide_yinpin", 1);
        tab_pre_inscrip.dibujar();
        
        PanelTabla pat_pre_inscrip = new PanelTabla();
        pat_pre_inscrip.setId("pat_pre_inscrip");
        pat_pre_inscrip.setPanelTabla(tab_pre_inscrip);
        
        Division div_pre_inscrip = new Division();
        div_pre_inscrip.setId("div_pre_inscripo");
        div_pre_inscrip.dividir1(pat_pre_inscrip);
        
        agregarComponente(div_pre_inscrip);
        
 }
    

    @Override
    public void insertar() {
        tab_pre_inscrip.insertar();
    }

    @Override
    public void guardar() {
        tab_pre_inscrip.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_pre_inscrip.eliminar();
        

    }
    
}