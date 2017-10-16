/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Martha
 */
public class CarreraAfines extends Pantalla{
    private Tabla tab_carrera_afines = new Tabla();
    
    public CarreraAfines(){
        
            tab_carrera_afines.setId("tab_carrera_afines");
            tab_carrera_afines.setTabla("yavirac_titu_carrera_afines", "ide_yticaa", 1);
            tab_carrera_afines.setHeader("Tabla Carrera Afines");
            tab_carrera_afines.dibujar();
            
            PanelTabla pat_carrera_afines = new PanelTabla();
            pat_carrera_afines.setId("pat_carrera_afines");
            pat_carrera_afines.setPanelTabla(tab_carrera_afines);
            
            
             Division div_carrera_afines = new Division();
            div_carrera_afines.setId("div_carrera_afines");
            div_carrera_afines.dividir1(pat_carrera_afines);
            
            agregarComponente(div_carrera_afines);
}
     @Override
    public void insertar() {
      tab_carrera_afines.insertar();
    }

    @Override
    public void guardar() {
       tab_carrera_afines.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_carrera_afines.eliminar();
    }

    public Tabla getTab_tipo_vinculacion() {
        return tab_carrera_afines;
    }

    public void setTab_carrera_afines(Tabla tab_carrera_afines) {
        this.tab_carrera_afines = tab_carrera_afines;
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