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
public class PersonaVinculacion extends Pantalla{
    
   private Tabla tab_persona_vinculacion = new Tabla();
   
   public PersonaVinculacion (){
        
            tab_persona_vinculacion.setId("tab_persona_vinculacion");
            tab_persona_vinculacion.setTabla("yavirac_titu_persona_vinculacion", "ide_ytitpv", 5);
            tab_persona_vinculacion.setHeader("Persona Encargada de la Vinculacion");
            tab_persona_vinculacion.dibujar();
            
            PanelTabla pat_persona_vinculacion = new PanelTabla();
            pat_persona_vinculacion.setId("pat_persona_vinculacion");
            pat_persona_vinculacion.setPanelTabla(tab_persona_vinculacion);
    
            Division div_persona_vinculacion = new Division();
            div_persona_vinculacion.setId("div_persona_vinculacion");
            div_persona_vinculacion.dividir1(pat_persona_vinculacion);
            
            agregarComponente(div_persona_vinculacion);
            
}
   @Override
    public void insertar() {
      tab_persona_vinculacion.insertar();
    }

    @Override
    public void guardar() {
       tab_persona_vinculacion.guardar();
       guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_persona_vinculacion.eliminar();
    }

    public Tabla getTab_tipo_entidad() {
        return tab_persona_vinculacion;
    }

    public void setTab_persona_vinculacion(Tabla tab_persona_vinculacion) {
        this.tab_persona_vinculacion = tab_persona_vinculacion;
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
