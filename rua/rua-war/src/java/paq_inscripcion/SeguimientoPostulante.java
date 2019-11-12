/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_inscripcion;

import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import java.util.List;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_inscripcion.ejb.ServicioInscripcion;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author CRISTIAN VEGA
 */
public class SeguimientoPostulante extends Pantalla {

    private Combo com_periodo_academico = new Combo();
    private Boton bot_clean = new Boton();
    private Tabla tab_docentealumno = new Tabla();
    private Tabla tab_docenteseguimiento = new Tabla();

    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioInscripcion ser_inscripcion = (ServicioInscripcion) utilitario.instanciarEJB(ServicioInscripcion.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

    public SeguimientoPostulante() {

        if (TienePermiso()) {

            tab_docentealumno.setId("tab_docentealumno");
            tab_docentealumno.setTabla("yavirac_ins_docente_alumno", "ide_yindoa", 2);
            tab_docentealumno.getColumna("ide_yindoa").setNombreVisual("CODIGO");
            tab_docentealumno.setHeader("DOCENTE: " + docente);
            tab_docentealumno.setCondicion("ide_yincda in (select ide_yincda from yavirac_ins_coordin_docent_as  where ide_ypedpe=" + ide_docente + ")");
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

        } else {
            utilitario.agregarNotificacionInfo("Mensaje,", "EL usuario ingresado no registra permisos para la asignacion de docentes. Consulte con el Administrador");
        }
    }

    /*
    public void filtroComboPeriodoAcademico() {

        tab_docenteseguimiento.setCondicion("ide_ystpea=" + com_periodo_academico.getValue().toString());
        tab_docenteseguimiento.ejecutarSql();
        ///tab_docentemension.ejecutarValorForanea(tab_docentemension.getValorSeleccionado());   
    }
     */
    String docente = "";
    String documento = "";
    String ide_docente = "";

    private boolean TienePermiso() {
        List sql = utilitario.getConexion().consultar(ser_estructura_organizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA"), " and not ide_ypedpe is null"));
        System.out.println(" " + sql);
        if (!sql.isEmpty()) {
            Object[] fila = (Object[]) sql.get(0);
            List sql2 = utilitario.getConexion().consultar(ser_personal.getDatoPersonalCodigo(fila[3].toString()));
            if (!sql2.isEmpty()) {
                Object[] fila2 = (Object[]) sql2.get(0);
                docente = fila2[1].toString() + " " + fila2[2].toString();
                documento = fila2[3].toString();
                ide_docente = fila2[0].toString();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void insertar() {
        if (tab_docenteseguimiento.isFocus()) {
            tab_docenteseguimiento.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_docenteseguimiento.guardar()) {
            guardarPantalla();
        }

    }

    @Override
    public void eliminar() {
        if (tab_docenteseguimiento.isFocus()) {
            tab_docenteseguimiento.eliminar();
        }
    }

    public Boton getBot_clean() {
        return bot_clean;
    }

    public void setBot_clean(Boton bot_clean) {
        this.bot_clean = bot_clean;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
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
