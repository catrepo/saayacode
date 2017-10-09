/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import javax.ejb.EJB;
import paq_nota.ejb.ServicioNotas;
import servicios.sistema.ServicioSeguridad;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ITSY
 */
public class PeriodoAcademico extends Pantalla {

    Tabla tab_periodo_academic = new Tabla(); // importar tabla 
    Tabla tab_periodo_evaluacion = new Tabla();
    Tabla tab_actividad_evaluacion = new Tabla();
    
   @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);
    

    public PeriodoAcademico() {

        //tab_periodo_academic.setIdCompleto("tab_tabulador:tab_periodo_academic");
        tab_periodo_academic.setId("tab_periodo_academic");  // todo objeto instanciado poner id 

        tab_periodo_academic.setTabla("yavirac_stror_periodo_academic", "ide_ystpea", 1);    // nom bdd
        tab_periodo_academic.agregarRelacion(tab_periodo_evaluacion);
        tab_periodo_academic.agregarRelacion(tab_actividad_evaluacion);

        tab_periodo_academic.dibujar();

        PanelTabla pa_periodoacademico = new PanelTabla();
        pa_periodoacademico.setId("pa_periodoacademico"); // nombre de i
        pa_periodoacademico.setPanelTabla(tab_periodo_academic);

        //tabla perioado de evaluacion
        tab_periodo_evaluacion.setId("tab_periodo_evaluacion");  // todo objeto instanciado poner id 
        tab_periodo_evaluacion.setIdCompleto("tab_tabulador:tab_periodo_evaluacion");        
        tab_periodo_evaluacion.setTabla("yavirac_nota_periodo_evaluacio", "ide_ynopee", 1);    // nom bdd
        tab_periodo_evaluacion.getColumna("ide_ynotie").setCombo(ser_notas.getTipoEvaluacion("true,false"));
        tab_periodo_evaluacion.dibujar();

        PanelTabla pa_periodoevaluacion = new PanelTabla();
        pa_periodoevaluacion.setId("pa_periodoevaluacion"); // nombre de i
        pa_periodoevaluacion.setPanelTabla(tab_periodo_evaluacion);

        //tabala  periodo actividad de evaluacion
        tab_actividad_evaluacion.setId("tab_actividad_evaluacion");  // todo objeto instanciado poner id 
        tab_actividad_evaluacion.setIdCompleto("tab_tabulador:tab_actividad_evaluacion");        
        tab_actividad_evaluacion.setTabla("yavirac_nota_periodo_activ_eva", "ide_ynopae", 1);    // nom bdd
        tab_actividad_evaluacion.dibujar();

        PanelTabla pa_actividadevaluacion = new PanelTabla();
        pa_actividadevaluacion.setId("pa_actividadevaluacion"); // nombre de i
        pa_actividadevaluacion.setPanelTabla(tab_actividad_evaluacion);

        ///// tabuladores
        Tabulador tab_tabulador = new Tabulador();
        tab_tabulador.setId("tab_tabulador");

        tab_tabulador.agregarTab("PERIODO EVALUACION", pa_periodoevaluacion);
        tab_tabulador.agregarTab("PERIODO ACTIVIDAD EVALUACION", pa_actividadevaluacion);

        Division div_periodo_academico = new Division();
        div_periodo_academico.dividir2(pa_periodoacademico, tab_tabulador, "30%", "h");
        agregarComponente(div_periodo_academico);

    }

    @Override
    public void insertar() {
        if (tab_periodo_academic.isFocus()) {
            tab_periodo_academic.insertar();
        } else if (tab_actividad_evaluacion.isFocus()) {
            tab_actividad_evaluacion.insertar();
        } else if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_periodo_academic.guardar()) {
            if (tab_periodo_evaluacion.guardar()) {
                tab_actividad_evaluacion.guardar();
            }
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if (tab_periodo_academic.isFocus()) {
            tab_periodo_academic.eliminar();
        } else if (tab_actividad_evaluacion.isFocus()) {
            tab_actividad_evaluacion.eliminar();
        } else if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.eliminar();
        }
    }

    public Tabla getTab_periodo_academic() {
        return tab_periodo_academic;
    }

    public void setTab_periodo_academic(Tabla tab_periodo_academic) {
        this.tab_periodo_academic = tab_periodo_academic;
    }

    public Tabla getTab_periodo_evaluacion() {
        return tab_periodo_evaluacion;
    }

    public void setTab_periodo_evaluacion(Tabla tab_periodo_evaluacion) {
        this.tab_periodo_evaluacion = tab_periodo_evaluacion;
    }

    public Tabla getTab_actividad_evaluacion() {
        return tab_actividad_evaluacion;
    }

    public void setTab_actividad_evaluacion(Tabla tab_actividad_evaluacion) {
        this.tab_actividad_evaluacion = tab_actividad_evaluacion;
    }

}
