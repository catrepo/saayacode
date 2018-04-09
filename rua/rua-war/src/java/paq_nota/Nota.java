/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import java.util.List;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Jhon
 */
public class Nota extends Pantalla {

    private Combo com_periodo_academico = new Combo();
    private Tabla tab_docente_mencion = new Tabla();
    private Tabla tab_tipo_evaluacion = new Tabla();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);

    public Nota() {
        if (TienePerfilNota()) {

            bar_botones.getBot_insertar().setRendered(false);
            bar_botones.getBot_guardar().setRendered(false);
            bar_botones.getBot_eliminar().setRendered(false);
            bar_botones.getBot_atras().setRendered(false);
            bar_botones.getBot_fin().setRendered(false);
            bar_botones.getBot_siguiente().setRendered(false);
            bar_botones.getBot_inicio().setRendered(false);

            bar_botones.agregarComponente(new Etiqueta("Docente *****"));
            bar_botones.agregarComponente(new Etiqueta(docente));
            bar_botones.agregarComponente(new Etiqueta("*****"));
            bar_botones.agregarComponente(new Etiqueta("      "));

            /*Boton bot_asistencia = new Boton();
            bot_asistencia.setValue("Registrar Asistencia");
            bot_asistencia.setMetodo("registrarAsistencia");
            bar_botones.agregarBoton(bot_asistencia);*/

            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
            bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
            bar_botones.agregarComponente(com_periodo_academico);
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");
            /*tab_tipo_evaluacion.setId("tab_tipo_evaluacion");
            tab_tipo_evaluacion.setTabla("yavirac_nota_tipo_evaluacion", "ide_ynotie", 1);
            tab_tipo_evaluacion.setHeader("TIPO DE EVALUACIÓN");
            tab_tipo_evaluacion.getColumna("ide_ynotie").setNombreVisual("CODIGO");
            tab_tipo_evaluacion.getColumna("descripcion_ynotie").setNombreVisual("DESCRIPCION");
            tab_tipo_evaluacion.getColumna("activo_ynotie").setNombreVisual("ACTIVO");
            tab_tipo_evaluacion.dibujar();

            PanelTabla pat_tipo_evaluacion = new PanelTabla();
            pat_tipo_evaluacion.setId("pat_tipo_evaluacion");
            pat_tipo_evaluacion.setPanelTabla(tab_tipo_evaluacion);

            Division div_tipo_evaluacion = new Division();
            div_tipo_evaluacion.setId("div_tipo_evaluacion");
            div_tipo_evaluacion.dividir1(pat_tipo_evaluacion);

            agregarComponente(div_tipo_evaluacion);*/

        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el control de Asistencia. Consulte con el Administrador");
        }
    }

    String docente = "";
    String documento = "";
    String ide_docente = "";

    private boolean TienePerfilNota() {
        List sql = utilitario.getConexion().consultar(ser_estructura_organizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA"), " and not ide_ypedpe is null"));

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

    public void filtraEstudiantes() {
        String malla = tab_docente_mencion.getValorSeleccionado();
        TablaGenerica tab_malla = utilitario.consultar("select ide_ypemad,ide_ystmal,ide_ypedpe from yavirac_perso_malla_docente where ide_ypemad=" + malla);

    }

    public void filtroComboPeriodoAcademico() {

        tab_docente_mencion.setCondicion("ide_ystpea=" + com_periodo_academico.getValue().toString() + " and ide_ypedpe =" + ide_docente);
        tab_docente_mencion.ejecutarSql();
        //tab_fecha_control.setCondicion("ide_ystpea=" + com_periodo_academico.getValue().toString());
        //tab_fecha_control.ejecutarSql();
        utilitario.addUpdate("tab_docente_mencion");

    }

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getIde_docente() {
        return ide_docente;
    }

    public void setIde_docente(String ide_docente) {
        this.ide_docente = ide_docente;
    }

    public Grupo getGru_pantalla() {
        return gru_pantalla;
    }

    public void setGru_pantalla(Grupo gru_pantalla) {
        this.gru_pantalla = gru_pantalla;
    }

    public Tabla getTab_docente_mencion() {
        return tab_docente_mencion;
    }

    public void setTab_docente_mencion(Tabla tab_docente_mencion) {
        this.tab_docente_mencion = tab_docente_mencion;
    }

    public Tabla getTab_tipo_evaluacion() {
        return tab_tipo_evaluacion;
    }

    public void setTab_tipo_evaluacion(Tabla tab_tipo_evaluacion) {
        this.tab_tipo_evaluacion = tab_tipo_evaluacion;
    }

}
