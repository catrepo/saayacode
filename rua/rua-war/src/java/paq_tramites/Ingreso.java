/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_tramites;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Personal
 */
public class Ingreso extends Pantalla{
    
    private Tabla tab_ingreso = new Tabla();
    
    public Ingreso (){
        tab_ingreso.setId("tab_ingreso");
        tab_ingreso.setTabla("yavirac_tra_ingreso", "ide_ytring", 1);
        tab_ingreso.setHeader("Registro Ingreso");
        tab_ingreso.dibujar();
        
        PanelTabla pat_ingreso = new PanelTabla();
        pat_ingreso.setId("pat_ingreso");
        pat_ingreso.setPanelTabla(tab_ingreso);
        
        Division div_ingreso = new Division();
        div_ingreso.setId("div_ingreso");
        div_ingreso.dividir1(pat_ingreso);
        
        
        agregarComponente(div_ingreso);   
}

    @Override
    public void insertar() {
         tab_ingreso.insertar();
    }

    @Override
    public void guardar() {
        tab_ingreso.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_ingreso.eliminar();
    }

    public Tabla getTab_ingreso() {
        return tab_ingreso;
    }

    public void setTab_ingreso(Tabla tab_ingreso) {
        this.tab_ingreso = tab_ingreso;
    }

    public Utilitario getUtilitario() {
        return utilitario;
    }

    public void setUtilitario(Utilitario utilitario) {
        this.utilitario = utilitario;
    }

    public Barra getBar_botones() {
        return bar_botones;
    }

    public void setBar_botones(Barra bar_botones) {
        this.bar_botones = bar_botones;
    }

    public Grupo getGru_pantalla() {
        return gru_pantalla;
    }

    public void setGru_pantalla(Grupo gru_pantalla) {
        this.gru_pantalla = gru_pantalla;
    }
}
