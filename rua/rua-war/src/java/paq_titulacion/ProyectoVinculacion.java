/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Boton;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_titulacion.ejb.ServicioTitulacion;
import sistema.aplicacion.Pantalla;
/**
 *
 * @author usuario
 */
public class ProyectoVinculacion extends Pantalla {
    
     private Tabla tab_tabla = new Tabla();
  private final ServicioTitulacion ser_titulacion = (ServicioTitulacion) utilitario.instanciarEJB(ServicioTitulacion.class);
 
     public ProyectoVinculacion() {
        
        tab_tabla.setId("tab_tabla");
        tab_tabla.setTabla("yavirac_titu_proyecto", "ide_ytipro", 1);
        
        tab_tabla.setTipoFormulario(true);
        tab_tabla.getGrid().setColumns(6);
        tab_tabla.getColumna("ide_ytiviv").setCombo(ser_titulacion.getViabilidad());
        
        tab_tabla.getColumna("ide_ytipro").setNombreVisual("CODIGO DE PROYECTO");
        tab_tabla.getColumna("fecha_registro_ytipro").setNombreVisual("FECHA DE REGISTRO");
        tab_tabla.getColumna("objeto_proyecto_ytipro").setNombreVisual("OBJETO DEL PROYECTO");
        tab_tabla.getColumna("detalle_proyecto_ytipro").setNombreVisual("DETALLE DEL PROYECTO");
        tab_tabla.getColumna("fecha_inicio_ytipro").setNombreVisual("FECHA DE INICIO");
        tab_tabla.getColumna("fecha_fin_ytipro").setNombreVisual("FECHA FINAL");
        
        
               

        tab_tabla.dibujar();

        PanelTabla pat_panel = new PanelTabla();
        pat_panel.setPanelTabla(tab_tabla);
        Division div_division = new Division();
        div_division.dividir1(pat_panel);
        agregarComponente(div_division);
     }

    @Override
    public void insertar() {
        tab_tabla.insertar();
    }

    @Override
    public void guardar() {
        tab_tabla.guardar();
        guardarPantalla();
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
