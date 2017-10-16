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
public class TipoVinculacion extends Pantalla{
    private Tabla tab_tipo_vinculacion = new Tabla();
    
    public TipoVinculacion(){
        
            tab_tipo_vinculacion.setId("tab_tipo_vinculacion");
            tab_tipo_vinculacion.setTabla("yavirac_tipos_vinculacion", "ide_ytitiv ", 6);
            tab_tipo_vinculacion.setHeader("Tipo de Vinculacion");
            tab_tipo_vinculacion.dibujar();
            
            
            PanelTabla pat_tipo_vinculacion = new PanelTabla();
            pat_tipo_vinculacion.setId("pat_tipo_vinculacion");
            pat_tipo_vinculacion.setPanelTabla(tab_tipo_vinculacion);
            
            
             Division div_tipo_vinculacion = new Division();
            div_tipo_vinculacion.setId("div_tipo_vinculacion");
            div_tipo_vinculacion.dividir1(pat_tipo_vinculacion);
            
            agregarComponente(div_tipo_vinculacion);
}
     @Override
    public void insertar() {
      tab_tipo_vinculacion.insertar();
    }

    @Override
    public void guardar() {
       tab_tipo_vinculacion.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_vinculacion.eliminar();
    }

    public Tabla getTab_tipo_vinculacion() {
        return tab_tipo_vinculacion;
    }

    public void setTab_tipo_vinculacion(Tabla tab_tipo_vinculacion) {
        this.tab_tipo_vinculacion = tab_tipo_vinculacion;
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