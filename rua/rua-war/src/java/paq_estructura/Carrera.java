/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author USER
 */
public class Carrera extends Pantalla{
    
    Tabla tab_carrera =new Tabla ();
    Tabla tab_mension = new Tabla();
    Tabla tab_malla = new Tabla();
   @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
   
    public Carrera (){
        tab_carrera.setId("tab_carrera");
        tab_carrera.setTabla("yavirac_stror_carrera", "ide_ystcrr",1 );
        tab_carrera.setHeader("REGISTRO DE CARRERAS");
        tab_carrera.agregarRelacion(tab_mension);
        tab_carrera.getColumna("ide_ystcrr").setNombreVisual("CÓDIGO");
        tab_carrera.getColumna("fechaaprob_ystcrr").setNombreVisual("FECHA APROBACIÓN");
        tab_carrera.getColumna("descripcion_ystcrr").setNombreVisual("DESCRICPIÓN");
        tab_carrera.getColumna("resolucion_ystcrr").setNombreVisual("RESOLUCIÓN");
        tab_carrera.dibujar();
        
        PanelTabla pat_carrera =new PanelTabla();
        pat_carrera.setId("pat_carrera");
        pat_carrera.setPanelTabla(tab_carrera);
        
        
        
        tab_mension.setId("tab_mension");
        tab_mension.setTabla("yavirac_stror_mension", "ide_ystmen",2 );
        tab_mension.getColumna("ide_ysttfe").setCombo(ser_estructura.getTipoFormacionEducativa("true"));
        tab_mension.setHeader("REGISTRO DE MENSIONES");
        tab_mension.agregarRelacion(tab_malla);
        tab_mension.getColumna("ide_ystmen").setNombreVisual("CÓDIGO");
        tab_mension.getColumna("ide_ysttfe").setNombreVisual("TIPO FORMACIÓN EDUCATIVA");
        tab_mension.getColumna("fechaapro_ystmen").setNombreVisual("FECHA APROBACIÓN");
        tab_mension.getColumna("descripcion_ystmen").setNombreVisual("DESCRIPCIÓN");
        tab_mension.getColumna("resolucion_ystmen").setNombreVisual("RESOLUCIÓN");
        tab_mension.getColumna("total_credito_ystmen").setNombreVisual("TOTAL CREDITOS");
        tab_mension.dibujar();
        
        PanelTabla pat_mension =new PanelTabla();
        pat_mension.setId("pat_mension");
        pat_mension.setPanelTabla(tab_mension);
        
        
        tab_malla.setId("tab_malla");
        tab_malla.setTabla("yavirac_stror_malla", "ide_ystmal",3 );
        tab_malla.getColumna("ide_ystnie").setCombo(ser_estructura.getNivelEducacion());
        tab_malla.getColumna("ide_ystmat").setCombo(ser_estructura.getMaterias());
        tab_malla.getColumna("ide_ysttif").setCombo(ser_estructura.getTipoFormacion());
        tab_malla.setHeader("MALLA ACADEMICA");
        tab_malla.getColumna("ide_ystmal").setNombreVisual("CÓDIGO");
        tab_malla.getColumna("ide_ystnie").setNombreVisual("NIVEL EDUCACIÓN");
        tab_malla.getColumna("ide_ystmat").setNombreVisual("MATERIAS");
        tab_malla.getColumna("ide_ysttif").setNombreVisual("TIPO FORMACIÓN");
        tab_malla.getColumna("codigo_ystmal").setNombreVisual("CÓDIGO MALLA");
        tab_malla.getColumna("numero_credito_ystmal").setNombreVisual("NÚMERO CREDITOS");
        tab_malla.getColumna("orden_ystmal").setNombreVisual("ORDEN");
        tab_malla.getColumna("aplica_requisitos_ystmal").setNombreVisual("APLICA REQUISITOS");
        tab_malla.dibujar();
        
        PanelTabla pat_malla =new PanelTabla();
        pat_malla.setPanelTabla(tab_malla);
        
        
        Division div_carrera =new Division();
        div_carrera.setId("div_carrera");
        div_carrera.dividir3(pat_carrera, pat_mension, pat_malla, "20%", "60%", "H");
        
        agregarComponente(div_carrera);
        
     }   

    @Override
    public void insertar() {
        if(tab_carrera.isFocus()){
        tab_carrera.insertar();
        }
        else if(tab_mension.isFocus()){
         tab_mension.insertar();
        }
        else if(tab_malla.isFocus()){
            tab_malla.insertar();
        }
    }

    @Override
    public void guardar() {
        
        if(tab_carrera.isFocus()){
        tab_carrera.guardar();
        }
        else if(tab_mension.isFocus()){
         tab_mension.guardar();
        }
        else if(tab_malla.isFocus()){
            tab_malla.guardar();
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if(tab_carrera.isFocus()){
        tab_carrera.eliminar();
        }
        else if(tab_mension.isFocus()){
         tab_mension.eliminar();
        }
        else if(tab_malla.isFocus()){
            tab_malla.eliminar();
        }        
    }

    public Tabla getTab_carrera() {
        return tab_carrera;
    }

    public void setTab_carrera(Tabla tab_carrera) {
        this.tab_carrera = tab_carrera;
    }

    public Tabla getTab_mension() {
        return tab_mension;
    }

    public void setTab_mension(Tabla tab_mension) {
        this.tab_mension = tab_mension;
    }

    public Tabla getTab_malla() {
        return tab_malla;
    }

    public void setTab_malla(Tabla tab_malla) {
        this.tab_malla = tab_malla;
    }

    
}
