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
import framework.componentes.Tabulador;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Personal
 */
public class Ingreso extends Pantalla{
    
    private Tabla tab_ingreso = new Tabla();
    private Tabla tab_anexo = new Tabla();
    private Tabla tab_asignacion = new Tabla();
    
    public Ingreso (){
        tab_ingreso.setId("tab_ingreso");
        tab_ingreso.setTabla("yavirac_tra_ingreso", "ide_ytring", 1);
        tab_ingreso.setHeader("Registro Ingreso");
        tab_ingreso.setTipoFormulario(true);
        tab_ingreso.getGrid().setColumns(4);
        tab_ingreso.dibujar();
        
        PanelTabla pat_ingreso = new PanelTabla();
        pat_ingreso.setId("pat_ingreso");
        pat_ingreso.setPanelTabla(tab_ingreso);
        
        tab_asignacion.setId("tab_asignacion");
        tab_asignacion.setIdCompleto("tab_tabulador:tab_asignacion");
        tab_asignacion.setTabla("yavirac_tra_asignacion", "ide_ytrasi", 2);
        tab_asignacion.dibujar();
        
        PanelTabla pat_asignacion = new PanelTabla();
        pat_asignacion.setId("pat_asignacion");
        pat_asignacion.setPanelTabla(tab_asignacion);        
        
        tab_anexo.setId("tab_anexo");
        tab_anexo.setIdCompleto("tab_tabulador:tab_anexo");
        tab_anexo.setTabla("yavirac_tra_anexo", "ide_ytrane", 2);
        tab_anexo.dibujar();
        
        PanelTabla pat_anexo = new PanelTabla();
        pat_anexo.setId("pat_anexo");
        pat_anexo.setPanelTabla(tab_anexo);   

        
        /// tabulado
        Tabulador tab_tabulador=new Tabulador();
	tab_tabulador.setId("tab_tabulador");

	tab_tabulador.agregarTab("ASIGNACION TRAMITE", pat_asignacion);
	tab_tabulador.agregarTab("ANEXOS", pat_anexo);
        
        Division div_ingreso = new Division();
        div_ingreso.setId("div_ingreso");
        div_ingreso.dividir2(pat_ingreso, tab_tabulador, "50%", "H");
        
        
        agregarComponente(div_ingreso);   
}

    @Override
    public void insertar() {
        if(tab_ingreso.isFocus()){
         tab_ingreso.insertar();
        }
        else if(tab_asignacion.isFocus()){
            tab_asignacion.insertar();
        }
        else if(tab_anexo.isFocus()){
            tab_anexo.insertar();
        }
    }

    @Override
    public void guardar() {
        if(tab_ingreso.guardar()){
            if(tab_asignacion.guardar()){
                if(tab_anexo.guardar());
            }
        }  
            guardarPantalla();
        
    }

    @Override
    public void eliminar() {
        utilitario.getTablaisFocus().eliminar();
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

    public Tabla getTab_anexo() {
        return tab_anexo;
    }

    public void setTab_anexo(Tabla tab_anexo) {
        this.tab_anexo = tab_anexo;
    }

    public Tabla getTab_asignacion() {
        return tab_asignacion;
    }

    public void setTab_asignacion(Tabla tab_asignacion) {
        this.tab_asignacion = tab_asignacion;
    }
    
}
