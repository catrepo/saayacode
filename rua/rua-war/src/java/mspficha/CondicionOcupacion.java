/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mspficha;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class CondicionOcupacion extends Pantalla{

        Tabla tab_tabla = new Tabla();

    
    public CondicionOcupacion(){
        
       tab_tabla.setId("tab_tabla");
        tab_tabla.setTabla("condicion_ocupacion", "id_cond_ocup", 1);
        tab_tabla.setGenerarPrimaria(false);
        tab_tabla.dibujar();
        
        PanelTabla pat_panel = new PanelTabla();
        pat_panel.setPanelTabla(tab_tabla);
        
        Division div_tabla = new Division();
        div_tabla.setId("div_tabla");
        div_tabla.dividir1(pat_panel);
        
        agregarComponente(div_tabla);
    }
    @Override
    public void insertar() {
        tab_tabla.insertar();
    }

    @Override
    public void guardar() {
        tab_tabla.guardar();
        guardarPantalla();
        tab_tabla.ejecutarSql();
    }

    @Override
    public void eliminar() {
        tab_tabla.eliminar();
    }

    public Tabla getTab_tabla() {
        return tab_tabla;
    }

    public void setTab_tabla(Tabla tab_tabla) {
        this.tab_tabla = tab_tabla;
    }
    
    
}
