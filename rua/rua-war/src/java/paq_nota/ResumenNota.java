/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import java.util.List;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import paq_asistencia.ejb.ServicioAsistencia;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_matricula.ejb.ServicioMatriculas;
import paq_nota.ejb.ServicioNotas;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHON
 */
public class ResumenNota extends Pantalla {

    private Combo com_periodo_academico = new Combo();
    private Combo com_materia_docente = new Combo();
    private Etiqueta eti_docente = new Etiqueta();
    private Tabla tab_docente_mencion = new Tabla();
    private Tabla tab_resumen_nota = new Tabla();
    private Tabla tab_docente_alumno = new Tabla();
    private Tabla tab_nota_resumen = new Tabla();
    private Dialogo dia_dialogo = new Dialogo();
    private Combo com_resumen = new Combo();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);
    @EJB
    private final ServicioMatriculas ser_matricula = (ServicioMatriculas) utilitario.instanciarEJB(ServicioMatriculas.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);

    public ResumenNota() {
        if (TienePerfilNota()) {

            bar_botones.getBot_insertar().setRendered(false);
            bar_botones.getBot_eliminar().setRendered(false);
            bar_botones.getBot_atras().setRendered(false);
            bar_botones.getBot_fin().setRendered(false);
            bar_botones.getBot_siguiente().setRendered(false);
            bar_botones.getBot_inicio().setRendered(false);

            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
            bar_botones.agregarComponente(new Etiqueta("Periodo Académico "));
            bar_botones.agregarComponente(com_periodo_academico);
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");

            com_materia_docente.setId("com_materia_docente");
            com_materia_docente.setMetodo("mostrarResumen");
            com_materia_docente.setCombo(ser_asistencia.getMateriaNivelDocente("-1", "2"));

            bar_botones.agregarComponente(new Etiqueta("Curso "));
            bar_botones.agregarComponente(com_materia_docente);

            //boton limpiar
            Boton bot_limpiar = new Boton();
            bot_limpiar.setIcon("ui-icon-cancel");
            bot_limpiar.setMetodo("limpiar");
            bar_botones.agregarBoton(bot_limpiar);

            //boton registrar notas
            Boton bot_nota = new Boton();
            bot_nota.setValue("Calcular Nota");
            bot_nota.setIcon("ui-icon-note");//set icono Registrar///
            bot_nota.setMetodo("calcularNota");
            bar_botones.agregarBoton(bot_nota);

            //boton Prueba
            Boton bot_prueba = new Boton();
            bot_prueba.setValue("PRUEBA");
            bot_prueba.setIcon("ui-icon-note");//set icono Registrar///
            bot_prueba.setMetodo("totalTercerNivel");
            bar_botones.agregarBoton(bot_prueba);

            eti_docente.setStyle("font-size: 16px;font-weight: bold");
            eti_docente.setValue("Docente: " + docente);

            tab_docente_alumno.setId("tab_docente_alumno");   //identificador
            tab_docente_alumno.setTabla("yavirac_perso_malla_docen_alum", "ide_ypemda", 1);
            tab_docente_alumno.setHeader("Docente: " + docente);
            tab_docente_alumno.agregarRelacion(tab_resumen_nota);
            tab_docente_alumno.setCondicion("ide_ypemad=-1");
            tab_docente_alumno.getColumna("ide_ypemad").setVisible(false);
            tab_docente_alumno.getColumna("ide_yaldap").setNombreVisual("ALUMNO/A");
            tab_docente_alumno.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
            tab_docente_alumno.getColumna("ide_yaldap").setLectura(true);
            tab_docente_alumno.getColumna("ide_ymamat").setVisible(false);
            tab_docente_alumno.dibujar();

            PanelTabla pa_docente_alumno = new PanelTabla();
            pa_docente_alumno.setId("pa_docente_alumno");
            pa_docente_alumno.setPanelTabla(tab_docente_alumno);

            //TABLA RESUMEN NOTA
            tab_resumen_nota.setId("tab_resumen_nota");
            tab_resumen_nota.setTabla("yavirac_nota_alumno_resumen", "ide_ynoalr", 2);
            //tab_resumen_nota.getColumna("ide_ynopen").setCombo(ser_notas.getPesoNotas()); 
            tab_resumen_nota.getColumna("ide_ynopen").setLectura(true);
            tab_resumen_nota.getColumna("nota_ynoalr").setLectura(true);
            tab_resumen_nota.getColumna("porcentaje_evaluacion_ynoalr").setLectura(true);
            tab_resumen_nota.dibujar();

            PanelTabla pa_resumen_nota = new PanelTabla();
            pa_resumen_nota.setId("pa_resumen_nota");
            pa_resumen_nota.setPanelTabla(tab_resumen_nota);

            //TABLA RESUMEN
            tab_nota_resumen.setId("tab_resumen");
            tab_nota_resumen.setTabla("yavirac_nota_resumen", "ide_ynores", 3);

            Division div_resumen_nota = new Division();
            div_resumen_nota.dividir2(pa_docente_alumno, pa_resumen_nota, "40%", "h");
            agregarComponente(div_resumen_nota);

            //DIALOGO
            dia_dialogo.setId("dia_dialogo");
            dia_dialogo.setTitle("PRUEBA");
            dia_dialogo.setWidth("40%");
            dia_dialogo.setHeight("40%");

            dia_dialogo.setResizable(false);

            Grid gri_cuerpo = new Grid();
            gri_cuerpo.setColumns(2);
            gri_cuerpo.setWidth("100%");
            gri_cuerpo.setStyle("width:100%;overflow: auto;display: block;");

            gri_cuerpo.getChildren().add(new Etiqueta("Seleccione el parcial"));
            com_resumen.setCombo(ser_notas.getPeriodoEvaResumen("1"));
            gri_cuerpo.getChildren().add(com_resumen);
            dia_dialogo.getBot_aceptar().setMetodo("aceptarDialogo");
            dia_dialogo.setDialogo(gri_cuerpo);
            agregarComponente(dia_dialogo);

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

    public void filtraAlumno() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("Adevertencia: ", "Seleccione el Periodo Académico");
            return;
        }
        if (com_materia_docente.getValue() == null) {
            utilitario.agregarMensajeInfo("Adevertencia: ", "Seleccione la materia que desea consultar la asistencia");
            return;
        }
    }

    public void filtraEstudiantes() {
        String malla = tab_docente_mencion.getValorSeleccionado();
        TablaGenerica tab_malla = utilitario.consultar("select ide_ypemad,ide_ystmal,ide_ypedpe from yavirac_perso_malla_docente where ide_ypemad=" + malla);

    }

    public void filtroComboPeriodoAcademico() {

        com_materia_docente.setCombo(ser_asistencia.getMateriaNivelDocente(com_periodo_academico.getValue().toString(), ide_docente));
        utilitario.addUpdate("com_materia_docente");

    }

    public void abrirDialogo() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Académico");
            return;
        } else if (com_materia_docente.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el curso");
            return;
        } else {
            dia_dialogo.dibujar();
            com_resumen.setCombo(ser_notas.getPeriodoEvaResumen(com_periodo_academico.getValue().toString()));
        }
    }

    public void mostrarResumen() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Académico");
            return;
        } else {
            String cod = com_materia_docente.getValue() + "";
            TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(cod));
            tab_docente_alumno.setCondicion("ide_ypemad =" + tab_consuta.getValor("ide_ypemad"));
            tab_docente_alumno.ejecutarSql();
            tab_resumen_nota.ejecutarValorForanea(tab_docente_alumno.getValorSeleccionado());
        }
    }

    public void calcularNota() {
        if (com_periodo_academico.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Académico");
            return;
        } else if (com_materia_docente.getValue() == null) {
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione la materia");
            return;
        } else {
            String cod = com_materia_docente.getValue() + "";
            TablaGenerica tab_consulta = utilitario.consultar(ser_notas.getPersonMallaDocente(cod));
            TablaGenerica tab_peso = utilitario.consultar(ser_notas.getPesoNota("3", "true", tab_consulta.getValor("ide_ysttfe")));
            for (int i = 0; i < tab_peso.getTotalFilas(); i++) {
                TablaGenerica tab_detalle = utilitario.consultar(ser_notas.getPesoDetalleNota(tab_peso.getValor(i, "ide_ynopen")));
                for (int j = 0; j < tab_docente_alumno.getTotalFilas(); j++) {
                    utilitario.getConexion().ejecutarSql(ser_notas.getActualizarTablaResumenNota(tab_docente_alumno.getValor(j, "ide_ypemda"), tab_peso.getValor(i, "ide_ynopen")));
                    utilitario.getConexion().ejecutarSql(ser_notas.getActualizarTablaResumen(com_periodo_academico.getValue().toString(), tab_consulta.getValor("ide_ystmen"), tab_consulta.getValor("ide_ystnie"), tab_consulta.getValor("ide_ypedpe"), tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_consulta.getValor("ide_ystmal"), tab_docente_alumno.getValor(j, "ide_yaldap"), tab_peso.getValor(i, "ide_ynopen")));

                    for (int k = 0; k < tab_detalle.getTotalFilas(); k++) {

                        TablaGenerica tab_resumen = utilitario.consultar(ser_notas.getImportarSumaNotas("2", "1", com_periodo_academico.getValue().toString(), tab_consulta.getValor("ide_ypedpe"), tab_consulta.getValor("ide_ystmal"),
                                tab_consulta.getValor("ide_ystnie"), tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_peso.getValor(i, "ide_ynotie"), tab_docente_alumno.getValor(j, "ide_yaldap"), tab_consulta.getValor("ide_ysttfe"), tab_detalle.getValor(k, "ide_ynoace")));
                        if (tab_resumen.getTotalFilas() > 0) {
                            TablaGenerica tab_porciento = utilitario.consultar(ser_notas.getPorcientoParametroEvaluacion(tab_resumen.getValor("notas"), tab_consulta.getValor("ide_ypedpe"), tab_consulta.getValor("ide_ystmal"), tab_consulta.getValor("ide_ystnie"), tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_resumen.getValor("ide_ynoace")));

                            //INSERTAR TABLA RESUMEN
                            TablaGenerica tab_mximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_nota_resumen", "ide_ynores"));

                            utilitario.getConexion().ejecutarSql(ser_notas.getInsertarTabResumen(tab_mximo.getValor("maximo"), com_periodo_academico.getValue().toString(), tab_consulta.getValor("ide_ystmen"), tab_consulta.getValor("ide_ystnie"), tab_consulta.getValor("ide_ypedpe"),
                                    tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_resumen.getValor("ide_ynopae"), tab_consulta.getValor("ide_ystmal"), tab_docente_alumno.getValor(j, "ide_yaldap"), tab_peso.getValor(i, "ide_ynopen"),
                                    tab_resumen.getValor("notas"), tab_porciento.getValor("porcentaje"), tab_resumen.getValor("recuperacion_ynodet")));
                        }
                    }
                }

            }
            notaTotal();
            totalSegundoNivel();
            totalTercerNivel();
            //utilitario.addUpdate("tab_resumen_nota");
            tab_resumen_nota.ejecutarValorForanea(tab_docente_alumno.getValorSeleccionado());

        }

    }

    public void notaTotal() {

        String cod = com_materia_docente.getValue() + "";
        TablaGenerica tab_consulta = utilitario.consultar(ser_notas.getPersonMallaDocente(cod));
        TablaGenerica tab_peso = utilitario.consultar(ser_notas.getPesoNota("3", "true", tab_consulta.getValor("ide_ysttfe")));

        for (int i = 0; i < tab_peso.getTotalFilas(); i++) {
            TablaGenerica tab_detalle = utilitario.consultar(ser_notas.getPesoDetalleNota(tab_peso.getValor(i, "ide_ynopen")));
            for (int j = 0; j < tab_docente_alumno.getTotalFilas(); j++) {
                TablaGenerica tab_recuperacion = utilitario.consultar(ser_notas.getConsultaTabResumen(com_periodo_academico.getValue().toString(), tab_consulta.getValor("ide_ystmen"), tab_consulta.getValor("ide_ystnie"), tab_consulta.getValor("ide_ypedpe"), tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_consulta.getValor("ide_ystmal"), tab_docente_alumno.getValor(j, "ide_yaldap"), tab_peso.getValor(i, "ide_ynopen")));
                //tab_recuperacion.imprimirSql();
                String valor_examen = "0";
                if (tab_recuperacion.getTotalFilas() > 0) {
                    if (tab_detalle.getValor("ide_ynoace").equals(utilitario.getVariable("p_tipo_eva_examen")) && tab_recuperacion.getValor("recuperacion_ynores").equals("true")) {
                        valor_examen = "1";
                    }
                }
                TablaGenerica tab_total = utilitario.consultar(ser_notas.getNotaTotalTercerNivel(valor_examen, tab_peso.getValor(i, "peso_ynopen"), com_periodo_academico.getValue().toString(), tab_consulta.getValor("ide_ystmen"), tab_consulta.getValor("ide_ystnie"), tab_consulta.getValor("ide_ypedpe"), tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_consulta.getValor("ide_ystmal"), tab_docente_alumno.getValor(j, "ide_yaldap"), tab_peso.getValor(i, "ide_ynopen")));
                //INSERT TABLA ALUMNO RESUMEN
                TablaGenerica tab_codigo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_nota_alumno_resumen", "ide_ynoalr"));
                utilitario.getConexion().ejecutarSql(ser_notas.getInsertarTabAlumnoResumen(tab_codigo.getValor("maximo"), tab_peso.getValor(i, "ide_ynopen"), tab_docente_alumno.getValor(j, "ide_ypemda"), tab_total.getValor("notatotal"), tab_peso.getValor(i, "peso_ynopen")));

            }
        }

    }

    public void totalSegundoNivel() {
        String cod = com_materia_docente.getValue() + "";
        TablaGenerica tab_consulta = utilitario.consultar(ser_notas.getPersonMallaDocente(cod));
        TablaGenerica tab_peso = utilitario.consultar(ser_notas.getPadreSegundoNivel("2", "true"));
        for (int i = 0; i < tab_peso.getTotalFilas(); i++) {
            for (int j = 0; j < tab_docente_alumno.getTotalFilas(); j++) {
                TablaGenerica tab_segundoNivel = utilitario.consultar(ser_notas.getConsultarNotaTotalSegundoNivel(tab_peso.getValor(i, "ide_ynopen"), com_periodo_academico.getValue().toString(), tab_consulta.getValor("ide_ysttfe"), tab_peso.getValor("ide_ynotie"), tab_docente_alumno.getValor(j, "ide_yaldap")));
                
                TablaGenerica tab_codigo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_nota_alumno_resumen", "ide_ynoalr"));
                System.out.println("NOTA SEGUNDO NIVEL :"+i+" "+j);
                tab_segundoNivel.imprimirSql();
                //System.out.println("Padre: " + tab_peso.getValor(i, "ide_ynopen") + " Alumno: " + tab_docente_alumno.getValor(j, "ide_yaldap") + " Total: " + tab_segundoNivel.getValor("total"));
                utilitario.getConexion().ejecutarSql(ser_notas.getInsertarTabAlumnoResumen(tab_codigo.getValor("maximo"), tab_peso.getValor(i, "ide_ynopen"), tab_docente_alumno.getValor(j, "ide_ypemda"), tab_segundoNivel.getValor("total"), tab_peso.getValor(i, "peso_ynopen")));
            }
        }
    }

    public void totalTercerNivel() {
        TablaGenerica tab_peso = utilitario.consultar(ser_notas.getPadreSegundoNivel("1", "true"));
        for (int i = 0; i < tab_peso.getTotalFilas(); i++) {
            for (int j = 0; j < tab_docente_alumno.getTotalFilas(); j++) {
                TablaGenerica tab_tercerNivel = utilitario.consultar(ser_notas.getConsultarNotaTotalTercerNivel(tab_peso.getValor(i, "ide_ynopen"), com_periodo_academico.getValue().toString(), tab_docente_alumno.getValor(j, "ide_yaldap")));
                TablaGenerica tab_codigo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_nota_alumno_resumen", "ide_ynoalr"));
                System.out.println("NOTA TERCER NIVEL :"+i+" "+j);
                tab_tercerNivel.imprimirSql();
                //System.out.println("Padre: " + tab_peso.getValor(i, "ide_ynopen") + " Alumno: " + tab_docente_alumno.getValor(j, "ide_yaldap") + " Total: " + tab_tercerNivel.getValor("total"));
                utilitario.getConexion().ejecutarSql(ser_notas.getInsertarTabAlumnoResumen(tab_codigo.getValor("maximo"), tab_peso.getValor(i,"ide_ynopen"), tab_docente_alumno.getValor(j, "ide_ypemda"), tab_tercerNivel.getValor("total"), tab_peso.getValor(i,"peso_ynopen")));
            }
        }
        tab_resumen_nota.guardar();
        guardarPantalla();
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public Combo getCom_materia_docente() {
        return com_materia_docente;
    }

    public void setCom_materia_docente(Combo com_materia_docente) {
        this.com_materia_docente = com_materia_docente;
    }

    public Etiqueta getEti_docente() {
        return eti_docente;
    }

    public void setEti_docente(Etiqueta eti_docente) {
        this.eti_docente = eti_docente;
    }

    public Tabla getTab_docente_mencion() {
        return tab_docente_mencion;
    }

    public void setTab_docente_mencion(Tabla tab_docente_mencion) {
        this.tab_docente_mencion = tab_docente_mencion;
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

    public Tabla getTab_resumen_nota() {
        return tab_resumen_nota;
    }

    public void setTab_resumen_nota(Tabla tab_resumen_nota) {
        this.tab_resumen_nota = tab_resumen_nota;
    }

    public Dialogo getDia_dialogo() {
        return dia_dialogo;
    }

    public void setDia_dialogo(Dialogo dia_dialogo) {
        this.dia_dialogo = dia_dialogo;
    }

    public Combo getCom_resumen() {
        return com_resumen;
    }

    public void setCom_resumen(Combo com_resumen) {
        this.com_resumen = com_resumen;
    }

    public Tabla getTab_docente_alumno() {
        return tab_docente_alumno;
    }

    public void setTab_docente_alumno(Tabla tab_docente_alumno) {
        this.tab_docente_alumno = tab_docente_alumno;
    }

    public Tabla getTab_nota_resumen() {
        return tab_nota_resumen;
    }

    public void setTab_nota_resumen(Tabla tab_nota_resumen) {
        this.tab_nota_resumen = tab_nota_resumen;
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

}
