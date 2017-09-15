/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Janeth Pullotasig and  Nicolas Cajilema
 */
public class Biometrico extends Pantalla {
    
    private Tabla tab_biometrico = new Tabla();
 
   public Biometrico() {
       
       tab_biometrico.setId("tab_biometrico");
        tab_biometrico.setTabla("yavirac_asis_biometrico","ide_yasbio",1);
        tab_biometrico.dibujar();
        
        
        PanelTabla pat_biometrico = new PanelTabla();
        pat_biometrico.setId("pat_biometrico");
        pat_biometrico.setPanelTabla(tab_biometrico);
       
        Division div_biometrico = new Division();
        div_biometrico.setId("div_biometrico");
        div_biometrico.dividir1(pat_biometrico);
        
        agregarComponente(div_biometrico);
    
      } 
    @Override
    public void insertar() {
        tab_biometrico.insertar();
    }

    @Override
    public void guardar() {
        tab_biometrico.guardar();
        guardarPantalla();
        
    }

    @Override
    public void eliminar() {
        tab_biometrico.eliminar();
    }

    public Tabla getTab_biometrico() {
        return tab_biometrico;
    }

    public void setTab_biometrico(Tabla tab_biometrico) {
        this.tab_biometrico = tab_biometrico;
    }

}
