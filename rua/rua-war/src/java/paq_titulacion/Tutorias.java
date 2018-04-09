/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Jhon
 */
public class Tutorias extends Pantalla {

    private Tabla tab_tutorias = new Tabla();
    private Tabla tab_tutores = new Tabla();
    private Tabla tab_tutorial_alumno = new Tabla();
    private Tabla tab_seguimiento = new Tabla();

    public Tutorias() {

        tab_tutorias.setId("tab_tutorias");  // todo objeto instanciado poner id 
        tab_tutorias.setTabla("yavirac_tab_tutorias", "ide_ytitut", 1);    // nom bdd
        tab_tutorias.agregarRelacion(tab_tutores);
        tab_tutorias.agregarRelacion(tab_tutorial_alumno);
        tab_tutorias.agregarRelacion(tab_seguimiento);
        tab_tutorias.setHeader("TUTORIAS");
        tab_tutorias.dibujar();

        PanelTabla pa_tutorias = new PanelTabla();
        pa_tutorias.setId("pa_tutorias"); // nombre de i
        pa_tutorias.setPanelTabla(tab_tutorias);

        //tabla perioado de evaluacion
        tab_tutores.setId("tab_tutores");  // todo objeto instanciado poner id 
        tab_tutores.setIdCompleto("tab_tabulador:tab_tutores");
        tab_tutores.setTabla("yavirac_titu_tutores", "ide_ytitur", 1);    // nom bdd
        tab_tutores.dibujar();

        PanelTabla pa_tutores = new PanelTabla();
        pa_tutores.setId("pa_tutores"); // nombre de i
        pa_tutores.setPanelTabla(tab_tutores);

        //tabla perioado de evaluacion
        tab_tutorial_alumno.setId("tab_tutorial_alumno");  // todo objeto instanciado poner id 
        tab_tutorial_alumno.setIdCompleto("tab_tabulador:tab_tutorial_alumno");
        tab_tutorial_alumno.setTabla("yavirac_titu_tutorial_alumno", "ide_ytitua", 1);    // nom bdd
        tab_tutorial_alumno.dibujar();

        PanelTabla pa_tutorial_alumno = new PanelTabla();
        pa_tutorial_alumno.setId("pa_tutorial_alumno"); // nombre de i
        pa_tutorial_alumno.setPanelTabla(tab_tutorial_alumno);

        //tabla perioado de evaluacion
        tab_seguimiento.setId("tab_seguimiento");  // todo objeto instanciado poner id 
        tab_seguimiento.setIdCompleto("tab_tabulador:tab_tutorial_alumno");
        tab_seguimiento.setTabla("yavirac_titu_seguimiento", "ide_ytiseg", 1);    // nom bdd
        tab_seguimiento.dibujar();

        PanelTabla pa_seguimiento = new PanelTabla();
        pa_seguimiento.setId("pa_seguimiento"); // nombre de i
        pa_seguimiento.setPanelTabla(tab_seguimiento);

        Tabulador tab_tabulador = new Tabulador();
        tab_tabulador.setId("tab_tabulador");

        tab_tabulador.agregarTab("SEGUIMIENTO", pa_tutorias);
        tab_tabulador.agregarTab("TUTORES", pa_tutorias);
        tab_tabulador.agregarTab("TUTORIAL ALUMNO", pa_tutorias);

        Division div_tutoriales = new Division();
        div_tutoriales.dividir2(pa_tutorias, tab_tabulador, "30%", "h");
        agregarComponente(div_tutoriales);

    }

    @Override
    public void insertar() {
        if (tab_tutorias.isFocus()) {
            tab_tutorias.insertar();
        } else if (tab_tutores.isFocus()) {
            tab_tutores.insertar();
        } else if (tab_tutorial_alumno.isFocus()) {
            tab_tutorial_alumno.insertar();
        } else if (tab_seguimiento.isFocus()) {
            tab_seguimiento.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_tutorias.guardar()) {
            if (tab_tutores.guardar()) {
                if (tab_tutorial_alumno.guardar()) {
                    tab_seguimiento.guardar();
                }
            }
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if (tab_tutorias.isFocus()) {
            tab_tutorias.eliminar();
        } else if (tab_tutores.isFocus()) {
            tab_tutores.eliminar();
        } else if (tab_tutorial_alumno.isFocus()) {
            tab_tutorial_alumno.eliminar();
        } else if (tab_seguimiento.isFocus()) {
            tab_seguimiento.eliminar();
        }
    }

    public Tabla getTab_tutorias() {
        return tab_tutorias;
    }

    public void setTab_tutorias(Tabla tab_tutorias) {
        this.tab_tutorias = tab_tutorias;
    }

    public Tabla getTab_tutores() {
        return tab_tutores;
    }

    public void setTab_tutores(Tabla tab_tutores) {
        this.tab_tutores = tab_tutores;
    }

    public Tabla getTab_tutorial_alumno() {
        return tab_tutorial_alumno;
    }

    public void setTab_tutorial_alumno(Tabla tab_tutorial_alumno) {
        this.tab_tutorial_alumno = tab_tutorial_alumno;
    }

    public Tabla getTab_seguimiento() {
        return tab_seguimiento;
    }

    public void setTab_seguimiento(Tabla tab_seguimiento) {
        this.tab_seguimiento = tab_seguimiento;
    }

    
    
    
    
}

