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
 * @author User
 */
public class TipoDocumento extends Pantalla{
    
    private Tabla tab_tipodocumento = new Tabla();
    
    public TipoDocumento (){
        tab_tipodocumento.setId("tab_tipodocumento");
        tab_tipodocumento.setTabla("yavirac_tra_tipo_documento", "ytrtido", 1);
        tab_tipodocumento.setHeader("Registro Tipo de Documento");
        tab_tipodocumento.dibujar();
        
        PanelTabla pat_tipodocumento = new PanelTabla();
        pat_tipodocumento.setId("pat_tipodocumento");
        pat_tipodocumento.setPanelTabla(tab_tipodocumento);
        
        Division div_tipodocumento = new Division();
        div_tipodocumento.setId("div_tipodocumento");
        div_tipodocumento.dividir1(pat_tipodocumento);
        
        
        agregarComponente(div_tipodocumento);
}

    @Override
    public void insertar() {
        tab_tipodocumento.insertar();
    }

    @Override
    public void guardar() {
        tab_tipodocumento.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipodocumento.eliminar();
    }

    public Tabla getTab_tipodocumento() {
        return tab_tipodocumento;
    }

    public void setTab_tipodocumento(Tabla tab_tipodocumento) {
        this.tab_tipodocumento = tab_tipodocumento;
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

