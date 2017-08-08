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
public class Nacionalidades extends Pantalla{
    
    Tabla tab_nacionalidades =new Tabla();
    
    public Nacionalidades (){
        tab_nacionalidades.setId("tab_nacionalidades");
        tab_nacionalidades.setTabla("nacionalidades", "id_nacs", 1);
        tab_nacionalidades.dibujar();
        PanelTabla pa_nacionalidades = new PanelTabla();
        pa_nacionalidades.setPanelTabla(tab_nacionalidades);
        Division div_nacionalidades =new Division();
        div_nacionalidades.dividir1(pa_nacionalidades);
        agregarComponente(div_nacionalidades);
        
    }

    @Override
    public void insertar() {
        tab_nacionalidades.insertar();
    }

    @Override
    public void guardar() {
        tab_nacionalidades.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_nacionalidades.eliminar();
    }

    public Tabla getTab_nacionalidades() {
        return tab_nacionalidades;
    }

    public void setTab_nacionalidades(Tabla tab_nacionalidades) {
        this.tab_nacionalidades = tab_nacionalidades;
    }
    
    
    
    
}
