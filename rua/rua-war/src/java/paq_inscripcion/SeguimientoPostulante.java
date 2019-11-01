/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_inscripcion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import paq_inscripcion.ejb.ServicioInscripcion;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author CRISTIAN VEGA
 */
public class SeguimientoPostulante extends Pantalla {

    private Tabla tab_docentealumno = new Tabla();
    private Tabla tab_docenteseguimiento = new Tabla();

    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioInscripcion ser_inscripcion = (ServicioInscripcion) utilitario.instanciarEJB(ServicioInscripcion.class);

    public SeguimientoPostulante() {

        tab_docentealumno.setId("tab_docentealumno");
        tab_docentealumno.setTabla("yavirac_ins_docente_alumno", "ide_yindoa", 2);
        tab_docentealumno.getColumna("ide_yindoa").setNombreVisual("CODIGO");
        tab_docentealumno.setHeader("DOCENTE ALUMNO");
        tab_docentealumno.getColumna("ide_yincda").setNombreVisual("COORDINADOR ASIGNA DOCENTE");
        tab_docentealumno.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true"));
        tab_docentealumno.getColumna("ide_yaldap").setNombreVisual("ALUMNO DATO PERSONAL");
        tab_docentealumno.getColumna("ide_yaldap").setAutoCompletar();
        tab_docentealumno.getColumna("ide_yinpin").setNombreVisual("PRE INSCRIPCION");
        tab_docentealumno.getColumna("asigna_yindoa").setNombreVisual("ASIGNA");
        tab_docentealumno.agregarRelacion(tab_docenteseguimiento);

        tab_docentealumno.dibujar();

        PanelTabla pat_docentealumno = new PanelTabla();
        pat_docentealumno.setId("pat_docentealumno");
        pat_docentealumno.setPanelTabla(tab_docentealumno);

        tab_docenteseguimiento.setId("tab_docenteseguimiento");
        tab_docenteseguimiento.setTabla("yavirac_ins_docente_seguimiento", "ide_yindos", 3);
        tab_docenteseguimiento.getColumna("ide_yindos").setNombreVisual("CODIGO");
        tab_docenteseguimiento.setHeader("SEGUIMIENTO POSTULANTE");
        tab_docenteseguimiento.getColumna("ide_yindoa").setNombreVisual("DOCENTE ALUMNO");
        tab_docenteseguimiento.getColumna("fecha_contacto_yindos").setNombreVisual("FECHA CONTACTO");
        tab_docenteseguimiento.getColumna("novedad_yindos").setNombreVisual("NOVEDAD");
        tab_docenteseguimiento.getColumna("contactado_yindos").setNombreVisual("CONTACTADO");

        tab_docenteseguimiento.dibujar();

        PanelTabla pat_docenteseguimiento = new PanelTabla();
        pat_docenteseguimiento.setId("pat_docenteseguimiento");
        pat_docenteseguimiento.setPanelTabla(tab_docenteseguimiento);

        Division div_division = new Division();
        div_division.setId("div_division");
        div_division.dividir2(pat_docentealumno, pat_docenteseguimiento, "50%", "H");    

        agregarComponente(div_division);

    }

    @Override
    public void insertar() {
        if (tab_docentealumno.isFocus()) {
            tab_docentealumno.insertar();
        } else if (tab_docenteseguimiento.isFocus()) {
            tab_docenteseguimiento.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_docentealumno.guardar()) {
            if (tab_docenteseguimiento.guardar()) {
                guardarPantalla();
            }
        }
    }

    @Override
    public void eliminar() {
        if (tab_docentealumno.isFocus()) {
            tab_docentealumno.eliminar();
        } else if (tab_docenteseguimiento.isFocus()) {
            tab_docenteseguimiento.eliminar();
        }
    }

    public Tabla getTab_docentealumno() {
        return tab_docentealumno;
    }

    public void setTab_docentealumno(Tabla tab_docentealumno) {
        this.tab_docentealumno = tab_docentealumno;
    }

    public Tabla getTab_docenteseguimiento() {
        return tab_docenteseguimiento;
    }

    public void setTab_docenteseguimiento(Tabla tab_docenteseguimiento) {
        this.tab_docenteseguimiento = tab_docenteseguimiento;
    }

}
