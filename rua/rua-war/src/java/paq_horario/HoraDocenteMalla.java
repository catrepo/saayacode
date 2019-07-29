package paq_horario;

/**
 *
 * @author ANDRES
 */
import framework.aplicacion.TablaGenerica;
import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
import paq_matricula.ejb.ServicioMatriculas;
import paq_nota.ejb.ServicioNotas;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

public class HoraDocenteMalla extends Pantalla {

    private Tabla tab_docente_malla = new Tabla();
    private Tabla tab_docente_alumno = new Tabla();
    /*private Combo com_periodo_academico = new Combo();*/
    private AutoCompletar aut_alumno = new AutoCompletar();
    private Combo com_periodo_academico = new Combo();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
    @EJB
    private final ServicioMatriculas ser_matricula = (ServicioMatriculas) utilitario.instanciarEJB(ServicioMatriculas.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);

    public HoraDocenteMalla() {
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        //com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");

        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADÉMICO"));
        bar_botones.agregarComponente(com_periodo_academico);

        tab_docente_malla.setId("tab_docente_malla");   //identificador
        tab_docente_malla.setTabla("yavirac_perso_malla_docente", "ide_ypemad", 1);
        // tab_docente_malla.setCondicion("ide_yhodom=-1"); // no dibuja nada de entrada
        tab_docente_malla.setCondicion("ide_ystpea=-1");
        tab_docente_malla.agregarRelacion(tab_docente_alumno);
        tab_docente_malla.getColumna("ide_ystpea").setVisible(false);
        tab_docente_malla.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_docente_malla.getColumna("ide_yhogra").setCombo(ser_horarios.getGrupoAcademico());
        tab_docente_malla.getColumna("ide_yhogra").setAncho(-1);
        tab_docente_malla.getColumna("ide_yhogra").setLongitud(-1);
        tab_docente_malla.getColumna("ide_ystmal").setCombo(ser_estructura_organizacional.getMalla());
        //tab_docente_malla.getColumna("ide_ystmal").setAutoCompletar();
        //tab_docente_malla.getColumna("ide_ystmal").setAncho(-1);
        //tab_docente_malla.getColumna("ide_ystmal").setLongitud(-1);
        tab_docente_malla.getColumna("ide_ystjor").setCombo(ser_estructura_organizacional.getJornada("true"));
        tab_docente_malla.getColumna("ide_ystjor").setAncho(-1);
        tab_docente_malla.getColumna("ide_ystjor").setLongitud(-1);
        tab_docente_malla.getColumna("ide_ypemad").setNombreVisual("CODIGO");
        tab_docente_malla.getColumna("ide_yhodom").setNombreVisual("CÓDIGO PRINCIPAL");
        tab_docente_malla.getColumna("ide_ypedpe").setNombreVisual("PERSONAL DOCENTES");
        tab_docente_malla.getColumna("ide_ystjor").setNombreVisual("JORNADA");
        tab_docente_malla.getColumna("ide_ypedpe").setVisible(false);
        tab_docente_malla.getColumna("ide_ystmal").setNombreVisual("MALLA ACADÉMICA");
        tab_docente_malla.getColumna("ide_yhogra").setNombreVisual("GRUPOS / PARALELOS");
        tab_docente_malla.getColumna("ide_ystmal").setRequerida(true);
        tab_docente_malla.getColumna("ide_yhogra").setRequerida(true);
        tab_docente_malla.getColumna("ide_ystjor").setRequerida(true);
        tab_docente_malla.dibujar();
        /*agregarComponente(tab_hora_dia);*/

        PanelTabla pa_hora_docente_malla = new PanelTabla();
        pa_hora_docente_malla.setId("pa_hora_docente_malla");
        pa_hora_docente_malla.setPanelTabla(tab_docente_malla);

        tab_docente_alumno.setId("tab_docente_alumno");   //identificador
        tab_docente_alumno.setTabla("yavirac_perso_malla_docen_alum", "ide_ypemda", 2);
        tab_docente_alumno.setHeader("ALUMNOS MATRICULADOS");
        tab_docente_alumno.getColumna("ide_ypemda").setNombreVisual("CODIGO");
        tab_docente_alumno.getColumna("ide_yaldap").setNombreVisual("ALUMNO/A");
        tab_docente_alumno.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
        tab_docente_alumno.getColumna("ide_yaldap").setLectura(true);
        tab_docente_alumno.getColumna("ide_ymamat").setNombreVisual("CODIGO MATRICULA");
        tab_docente_alumno.getColumna("ide_ymamat").setLectura(true);
        tab_docente_alumno.setRows(30);
        tab_docente_alumno.dibujar();

        PanelTabla pa_docente_alumno = new PanelTabla();
        pa_docente_alumno.setId("pa_docente_alumno");
        pa_docente_alumno.setPanelTabla(tab_docente_alumno);

        Division div_hora_docente_malla = new Division();
        div_hora_docente_malla.dividir2(pa_hora_docente_malla, pa_docente_alumno, "30%", "h");
        agregarComponente(div_hora_docente_malla);

        aut_alumno.setId("aut_alumno");
        aut_alumno.setAutoCompletar(ser_personal.getDatopersonal("true,false"));
        aut_alumno.setSize(60);
        //aut_alumno.setMetodoChange("selecionoAutocompletar");
        bar_botones.agregarComponente(new Etiqueta("Docente"));
        bar_botones.agregarComponente(aut_alumno);

        //boton registrar notas
        Boton bot_consultar = new Boton();
        bot_consultar.setValue("Consultar");
        bot_consultar.setIcon("ui-icon-search");//set icono Registrar///
        bot_consultar.setMetodo("selecionoAutocompletar");
        bar_botones.agregarBoton(bot_consultar);

        //boton registrar notas
        Boton bot_registrar = new Boton();
        bot_registrar.setValue("Inscribir Alumnos");
        bot_registrar.setIcon("ui-icon-pencil");//set icono Registrar///
        bot_registrar.setMetodo("RegistarAlumno");
        bar_botones.agregarBoton(bot_registrar);

    }

    public void selecionoAutocompletar() {
        tab_docente_malla.setCondicion("ide_ystpea=" + com_periodo_academico.getValue() + " and ide_ypedpe=" + aut_alumno.getValor());
        tab_docente_malla.ejecutarSql();
        utilitario.addUpdate("tab_docente_malla");

    }

    public void filtroComboPeriodoAcademnico() {

        tab_docente_malla.setCondicion("ide_ystpea=" + com_periodo_academico.getValue().toString());
        tab_docente_malla.ejecutarSql();
        utilitario.addUpdate("tab_docente_malla");

    }

    @Override
    public void insertar() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Académico");
            return;
        } else if (tab_docente_malla.isFocus()) {
            tab_docente_malla.insertar();
            tab_docente_malla.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
            tab_docente_malla.setValor("ide_ypedpe", aut_alumno.getValor());
            utilitario.addUpdateTabla(tab_docente_malla, "ide_ystpea", "");
        }

        //tab_docente_malla.insertar();
        // tab_docente_malla.setValor("ide_ypedpe", aut_alumno.getValue().);
        //utilitario.addUpdateTabla( tab_docente_malla, "ide_ypedpe", "");
    }

    public void RegistarAlumno() {
        String codigo = tab_docente_malla.getFilaSeleccionada().getRowKey();
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione Periodo Académico");
            return;
        } else if (aut_alumno.getValor() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Docente");
            return;
        } else if (tab_docente_malla.getTotalFilas() > 0) {
            TablaGenerica tab_matriculados = utilitario.consultar(ser_matricula.getAlumnosMallaGrupo(tab_docente_malla.getValor(tab_docente_malla.getFilaActual(), "ide_ystmal"), tab_docente_malla.getValor(tab_docente_malla.getFilaActual(), "ide_yhogra"), com_periodo_academico.getValue().toString(), tab_docente_malla.getValor(tab_docente_malla.getFilaActual(), "ide_ystjor")));
            //System.out.println("codigo " + codigo);
            if (tab_matriculados.getTotalFilas() > 0) {
                for (int i = 0; i < tab_matriculados.getTotalFilas(); i++) {
                    TablaGenerica tab_consulta = utilitario.consultar(ser_notas.getConsultaAlumnos(codigo, tab_matriculados.getValor(i, "ide_yaldap")));
                    tab_consulta.imprimirSql();
                    if (tab_consulta.getTotalFilas() > 0) {
                    } else {
                        tab_docente_alumno.insertar();
                        tab_docente_alumno.setValor("ide_yaldap", tab_matriculados.getValor(i, "ide_yaldap"));
                        tab_docente_alumno.setValor("ide_ymamat", tab_matriculados.getValor(i, "ide_ymamat"));

                    }

                }
                tab_docente_alumno.guardar();
                guardarPantalla();
                utilitario.addUpdate("tab_docente_alumno");
                //utilitario.agregarMensaje("Se ", "");
            } else {
                utilitario.agregarMensajeInfo("ADVERTENCIA,", "No tiene estudiantes matriculados para esta materia,paralelo y jornada");
            }
        } else {
             utilitario.agregarMensajeInfo("ADVERTENCIA,", "Asigne uan materia,paralelo y jornada al docente seleccionado");
        }

    }

    @Override
    public void guardar() {
        tab_docente_malla.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_docente_malla.eliminar();
    }

    public Tabla getTab_docente_malla() {
        return tab_docente_malla;
    }

    public void setTab_docente_malla(Tabla tab_docente_malla) {
        this.tab_docente_malla = tab_docente_malla;
    }

    public AutoCompletar getAut_alumno() {
        return aut_alumno;
    }

    public void setAut_alumno(AutoCompletar aut_alumno) {
        this.aut_alumno = aut_alumno;
    }

    public Tabla getTab_docente_alumno() {
        return tab_docente_alumno;
    }

    public void setTab_docente_alumno(Tabla tab_docente_alumno) {
        this.tab_docente_alumno = tab_docente_alumno;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

}
