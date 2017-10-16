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
public class CicloConvenio extends Pantalla{
   private Tabla tab_ciclo_convenio = new Tabla();
    
    public CicloConvenio(){
        
            tab_ciclo_convenio.setId("tab_ciclo_convenio");
            tab_ciclo_convenio.setTabla("yavirac_titu_carrera_afines", "ide_yticaa", 8);
            tab_ciclo_convenio.setHeader("Ciclo de Convenio");
            tab_ciclo_convenio.dibujar(); 
            
            
            PanelTabla pat_ciclo_convenio = new PanelTabla();
            pat_ciclo_convenio.setId("pat_ciclo_convenio");
            pat_ciclo_convenio.setPanelTabla(tab_ciclo_convenio);
            
            
             Division div_ciclo_convenio = new Division();
            div_ciclo_convenio.setId("div_ciclo_convenio");
            div_ciclo_convenio.dividir1(pat_ciclo_convenio);
            
            agregarComponente(div_ciclo_convenio);
}
    @Override
    public void insertar() {
      tab_ciclo_convenio.insertar();
    }

    @Override
    public void guardar() {
       tab_ciclo_convenio.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_ciclo_convenio.eliminar();
    }

    public Tabla getTab_tipo_vinculacion() {
        return tab_ciclo_convenio;
    }

    public void setTab_ciclo_convenio(Tabla tab_ciclo_convenio) {
        this.tab_ciclo_convenio = tab_ciclo_convenio;
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
