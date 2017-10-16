/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Barra;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import javax.ejb.EJB;
import paq_titulacion.ejb.ServicioTitulacion;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Martha
 */
public class Empresa extends Pantalla{
    private Tabla tab_empresa = new Tabla();
    private Tabla tab_representante = new Tabla();
    private Tabla tab_carrera = new Tabla();  
   // private Combo com_pantalla = new Combo();
  @EJB
    private final ServicioTitulacion ser_tiulacion = (ServicioTitulacion) utilitario.instanciarEJB(ServicioTitulacion.class);
       
    public Empresa(){
        
           tab_empresa.setId("tab_empresa"); // id del la empresa 
           tab_empresa.setTabla("yavirac_titu_empresa", "ide_ytiemp",1);
           tab_empresa.getColumna("ide_ytitie").setCombo(ser_tiulacion.getSqlTipoEmpresa());
           tab_empresa.getColumna("ide_ytiace").setCombo(ser_tiulacion.getSqlActividadEconomica());
           tab_empresa.getColumna("ide_ytitip").setCombo(ser_tiulacion.getSqlTipoProducto());
           tab_empresa.getColumna("ide_ytitie").setCombo(ser_tiulacion.getSqlTipoEntidad());
           tab_empresa.setHeader("Registro de datos de la Empresa"); 
           tab_empresa.agregarRelacion(tab_carrera);
           tab_empresa.agregarRelacion(tab_representante);
           tab_empresa.setTipoFormulario(true);
           tab_empresa.getGrid().setColumns(4);
           tab_empresa.dibujar(); 
    
            
            tab_representante.setId("tab_representante");// id de la tabla de representantes
            tab_representante.setIdCompleto("tab_tabulador:tab_representante");//tabulacion de la tabla representante

           tab_representante.setTabla("yavirac_titu_persona_empresa", "ide_ytipee",2);
           tab_representante.setHeader("Registro de los Representantes");
          // tab_representante.setTipoFormulario(true);
            tab_representante.dibujar();
            
             tab_carrera.setId("tab_carrera");
             tab_carrera.setIdCompleto("tab_tabulador:tab_carrera");

           tab_carrera.setTabla("yavirac_titu_carrera_afines", "ide_yticaa",3);
           tab_carrera.setHeader("Registro de datos la Carrera");
          // tab_carrera.setTipoFormulario(true);
            tab_carrera.dibujar();
            
            PanelTabla pat_empresa = new PanelTabla();
            pat_empresa.setId("pat_empresa");
            pat_empresa.setPanelTabla(tab_empresa);
            
            PanelTabla pat_representante = new PanelTabla();
            pat_representante.setId("pat_representante");
            pat_representante.setPanelTabla(tab_representante);
            
            PanelTabla pat_carrera = new PanelTabla();
            pat_carrera.setId("pat_carrera");
            pat_carrera.setPanelTabla(tab_carrera);
            
            
            Tabulador tab_tabulador = new Tabulador();
            tab_tabulador.setId("tab_tabulador");
            tab_tabulador.agregarTab("REPRESENTANTES", pat_representante);
            tab_tabulador.agregarTab("CARRERA", pat_carrera);
            
            Division div_empresa = new Division();
            div_empresa.setId("div_empresa");
            div_empresa.dividir2(pat_empresa, tab_tabulador, "40%", "H");

        
            
            agregarComponente(div_empresa);
    }

    @Override
    public void insertar() {
        if(tab_empresa.isFocus()){
                  tab_empresa.insertar();
        }
        else if (tab_carrera.isFocus()){
            tab_carrera.insertar();
        }
        else if(tab_representante.isFocus()){
            tab_representante.insertar();
        }
      
    }

    @Override
    public void guardar() {
         if(tab_empresa.isFocus()){
                  tab_empresa.guardar();
        }
        else if (tab_carrera.isFocus()){
            tab_carrera.guardar();
        }
        else if(tab_representante.isFocus()){
            tab_representante.guardar();
        }
       guardarPantalla();
    }

    @Override
    public void eliminar() {
                 if(tab_empresa.isFocus()){
                  tab_empresa.eliminar();
        }
        else if (tab_carrera.isFocus()){
            tab_carrera.eliminar();
        }
        else if(tab_representante.isFocus()){
            tab_representante.eliminar();
        }
    }

    public Tabla getTab_empresa() {
        return tab_empresa;
    }

    public void setTab_empresa(Tabla tab_empresa) {
        this.tab_empresa = tab_empresa;
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

    public Tabla getTab_representante() {
        return tab_representante;
    }

    public void setTab_representante(Tabla tab_representante) {
        this.tab_representante = tab_representante;
    }

    public Tabla getTab_carrera() {
        return tab_carrera;
    }

    public void setTab_carrera(Tabla tab_carrera) {
        this.tab_carrera = tab_carrera;
    }
    
}
