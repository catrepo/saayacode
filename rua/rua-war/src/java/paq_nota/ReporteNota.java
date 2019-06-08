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
import framework.componentes.Espacio;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Imagen;
import framework.componentes.ListaSeleccion;
import framework.componentes.Reporte;
import framework.componentes.Tabla;
import framework.componentes.VisualizarPDF;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import paq_asistencia.ejb.ServicioAsistencia;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_nota.ejb.ServicioNotas;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHONPRODUCER
 */
public class ReporteNota extends Pantalla {

    private Combo com_reportes = new Combo();
    private Combo com_periodo_academico = new Combo();
    private ListaSeleccion lis_materia = new ListaSeleccion();
    private ListaSeleccion lis_actividad = new ListaSeleccion();
    private ListaSeleccion lis_parcial = new ListaSeleccion();
    private Tabla tab_docente_alumno = new Tabla();
    private Tabla tab_nota_resumen = new Tabla();
    private Tabla tab_resumen_nota = new Tabla();
    private Reporte rep_reporte = new Reporte();
    private Map p_parametros = new HashMap();
    private VisualizarPDF vipdf_comprobante = new VisualizarPDF();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);

    public ReporteNota() {
        if (TienePerfilNota()) {

            bar_botones.getBot_insertar().setRendered(false);
            bar_botones.getBot_guardar().setRendered(false);
            bar_botones.getBot_eliminar().setRendered(false);
            bar_botones.quitarBotonsNavegacion();

            bar_botones.agregarComponente(new Etiqueta("Lista de Reportes"));
            com_reportes.setId("com_reportes");
            com_reportes.setCombo(ser_estructura.getListaReportes(utilitario.getVariable("p_menu_reportes")));
            bar_botones.agregarComponente(com_reportes);

            bar_botones.agregarComponente(new Etiqueta("Periodo Académico "));
            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true,false"));
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");
            bar_botones.agregarComponente(com_periodo_academico);

            Boton bot_imprimirpdf = new Boton();
            bot_imprimirpdf.setIcon("ui-icon-print");
            bot_imprimirpdf.setValue("IMPRIMIR REPORTE");
            bot_imprimirpdf.setMetodo("abrirListaReportes");
            bar_botones.agregarBoton(bot_imprimirpdf);

            Imagen ImaReportes = new Imagen();
            ImaReportes.setValue("imagenes/ModAsistencia/Asistencia.png");

            Grid grid_pant = new Grid();
            grid_pant.setColumns(1);
            grid_pant.setStyle("text-align:center;position:absolute;top:210px;left:535px;");
            Etiqueta eti_encab = new Etiqueta();
            grid_pant.getChildren().add(ImaReportes);
            Boton bot_imprimir = new Boton();
            bot_imprimir.setValue("Imprimir Reporte");
            bot_imprimir.setIcon("ui-icon-print");
            bot_imprimir.setMetodo("abrirListaReportes");
            bar_botones.agregarBoton(bot_imprimir);
            grid_pant.getChildren().add(bot_imprimir);

            Grid gri_formulario = new Grid();
            gri_formulario.setColumns(4);

          /*  lis_materia.setListaSeleccion("select a.ide_ypemad,detalle_ystmat,descripcion_ystnie,detalle_yhogra,d.descripcion_ystjor,detalle_ysttfe\n"
                    + "from yavirac_perso_malla_docente a,(select ide_ystmal, detalle_ystmat, descripcion_ystnie,detalle_ysttfe from yavirac_stror_malla a,yavirac_stror_nivel_educacion b,yavirac_stror_materia c,\n"
                    + "yavirac_stror_mension d,yavirac_stror_tipo_for_educaci e where a.ide_ystnie = b.ide_ystnie and a.ide_ystmat = c.ide_ystmat and a.ide_ystmen=d.ide_ystmen and d.ide_ysttfe=e.ide_ysttfe) b, \n"
                    + "yavirac_hora_grupo_academic c,yavirac_stror_jornada  d\n"
                    + "where a.ide_ystmal = b.ide_ystmal and a.ide_yhogra= c.ide_yhogra and ide_ystpea =2 and ide_ypedpe =" + ide_docente + " \n"
                    + "and a.ide_ystjor= d.ide_ystjor \n"
                    + "order by descripcion_ystnie,detalle_ystmat,descripcion_ystjor");
*/
            lis_materia.setListaSeleccion(ser_asistencia.getMateriaNivelDocente("-1", "2"));
            lis_materia.setLayout("pageDirection");
            lis_actividad.setListaSeleccion("select ide_ynoace,descripcion_ynoace from yavirac_nota_actividad_evaluac");
            lis_actividad.setLayout("pageDirection");
            lis_parcial.setListaSeleccion("select ide_ynotie,descripcion_ynotie from yavirac_nota_tipo_evaluacion");
            lis_parcial.setLayout("pageDirection");

            Espacio esp = new Espacio();
            Etiqueta eti_materia = new Etiqueta("MATERIAS");
            eti_materia.setEstiloContenido("font-size:16px;font-weight: bold;text-decoration: underline;color:blue");
            Etiqueta eti_actividad = new Etiqueta("ACTIVIDADES EVALUACION");
            eti_actividad.setEstiloContenido("font-size:16px;font-weight: bold;text-decoration: underline;color:blue");
            Etiqueta eti_parcial = new Etiqueta("PARCIAL");
            eti_parcial.setEstiloContenido("font-size:16px;font-weight: bold;text-decoration: underline;color:blue");
            gri_formulario.getChildren().add(esp);
            gri_formulario.getChildren().add(eti_materia);
            gri_formulario.getChildren().add(eti_actividad);
            gri_formulario.getChildren().add(eti_parcial);
            gri_formulario.getChildren().add(ImaReportes);
            gri_formulario.getChildren().add(lis_materia);
            gri_formulario.getChildren().add(lis_actividad);
            gri_formulario.getChildren().add(lis_parcial);

            Division div = new Division();
            div.dividir1(gri_formulario);
            agregarComponente(div);

            //TABLA DOCENTE MALLA ALUMNO
            tab_docente_alumno.setId("tab_docente_alumno");   //identificador
            tab_docente_alumno.setTabla("yavirac_perso_malla_docen_alum", "ide_ypemda", 1);

            //TABLA RESUMEN NOTA
            tab_resumen_nota.setId("tab_resumen_nota");
            tab_resumen_nota.setTabla("yavirac_nota_alumno_resumen", "ide_ynoalr", 2);

            //TABLA RESUMEN
            tab_nota_resumen.setId("tab_resumen");
            tab_nota_resumen.setTabla("yavirac_nota_resumen", "ide_ynores", 3);
            tab_nota_resumen.getColumna("recuperacion_ynores").setValorDefecto("false");

            vipdf_comprobante.setId("vipdf_comprobante");
            vipdf_comprobante.setTitle("REPORTES NOTAS");
            agregarComponente(vipdf_comprobante);

        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el control de Asistencia. Consulte con el Administrador");
        }
    }

    String docente = "";
    String documento = "";
    String ide_docente = "";
    String materia = "";
    String parcial = "";
    String actividad = "";

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

    public void filtroComboPeriodoAcademico() {
        System.out.println("Estoy en el filtro");
        lis_materia.setListaSeleccion(ser_asistencia.getMateriaNivelDocente(com_periodo_academico.getValue().toString(), ide_docente));
        utilitario.addUpdate("lis_materia");
    }

    public void abrirListaReportes() {
        if (com_reportes.getValue() != null) {
            if (com_periodo_academico.getValue() != null) {
                if (lis_materia.getSeleccionados() != "") {
                    materia = lis_materia.getSeleccionados();
                    if (lis_materia.getSeleccionados() != "") {
                        actividad = lis_actividad.getSeleccionados();
                        if (lis_materia.getSeleccionados() != "") {
                            parcial = lis_parcial.getSeleccionados();
                            calcularNota();
                            generarPDF();
                        } else {
                            utilitario.agregarMensajeInfo("Seleccione el parcial,", "Seleccione al menos un registro");
                        }
                    } else {
                        utilitario.agregarMensajeInfo("Seleccione la actividad de evaluación,", "Seleccione al menos un registro");
                    }
                } else {
                    utilitario.agregarMensajeInfo("Seleccione la materia,", "Seleccione al menos un registro");
                }
            } else {
                utilitario.agregarMensajeInfo("Mensaje,", "Seleccione el periodod académico");
            }
        } else {
            utilitario.agregarMensajeInfo("Mensaje,", "Seleccione un reporte de la lista de reportes");
        }

    }

    public void generarPDF() {
        TablaGenerica tab_reportes = utilitario.consultar("select * from sis_reporte where ide_repo=" + com_reportes.getValue());
        TablaGenerica tab_consuta = utilitario.consultar(ser_notas.getPersonMallaDocente(materia));

        String nombre_reporte = "";
        String reporte = "";
        nombre_reporte = tab_reportes.getValor("nom_repo");
        reporte = tab_reportes.getValor("path_repo");

        if (nombre_reporte.equals("Nota Final")) {

            Map map_parametros = new HashMap();
            map_parametros.put("nombre", utilitario.getVariable("NICK"));
            map_parametros.put("ide_ystpea", com_periodo_academico.getValue().toString());
            map_parametros.put("ide_ystmal", tab_consuta.getValor("ide_ystmal"));
            map_parametros.put("ide_ypedpe", ide_docente);
            map_parametros.put("ide_yhogra", tab_consuta.getValor("ide_yhogra"));
            map_parametros.put("ide_ystjor", tab_consuta.getValor("ide_ystjor"));
            map_parametros.put("ide_ystnie", tab_consuta.getValor("ide_ystnie"));
            vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
            vipdf_comprobante.dibujar();
            utilitario.addUpdate("vipdf_comprobante");
        } else if (nombre_reporte.equals("Nota Final por Parcial")) {

            Map map_parametros = new HashMap();
            map_parametros.put("nombre", utilitario.getVariable("NICK"));
            map_parametros.put("ide_ystpea", com_periodo_academico.getValue().toString());
            map_parametros.put("ide_ystmal", tab_consuta.getValor("ide_ystmal"));
            map_parametros.put("ide_ypedpe", ide_docente);
            map_parametros.put("ide_yhogra", tab_consuta.getValor("ide_yhogra"));
            map_parametros.put("ide_ystjor", tab_consuta.getValor("ide_ystjor"));
            map_parametros.put("ide_ystnie", tab_consuta.getValor("ide_ystnie"));
            map_parametros.put("ide_ynotie", parcial);
            vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
            vipdf_comprobante.dibujar();
            utilitario.addUpdate("vipdf_comprobante");

        } else if (nombre_reporte.equals("Lista Estudiantes Exonerados")) {

            Map map_parametros = new HashMap();
            map_parametros.put("nombre", utilitario.getVariable("NICK"));
            map_parametros.put("ide_ystpea", com_periodo_academico.getValue().toString());
            map_parametros.put("ide_ystmal", tab_consuta.getValor("ide_ystmal"));
            map_parametros.put("ide_ypedpe", ide_docente);
            map_parametros.put("ide_yhogra", tab_consuta.getValor("ide_yhogra"));
            map_parametros.put("ide_ystjor", tab_consuta.getValor("ide_ystjor"));
            map_parametros.put("ide_ystnie", tab_consuta.getValor("ide_ystnie"));
            map_parametros.put("ide_ynotie", parcial);
            vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
            vipdf_comprobante.dibujar();
            utilitario.addUpdate("vipdf_comprobante");

        } else if (nombre_reporte.equals("Lista Estudiantes Examen Recuperación")) {

            Map map_parametros = new HashMap();
            map_parametros.put("nombre", utilitario.getVariable("NICK"));
            map_parametros.put("ide_ystpea", com_periodo_academico.getValue().toString());
            map_parametros.put("ide_ystmal", tab_consuta.getValor("ide_ystmal"));
            map_parametros.put("ide_ypedpe", ide_docente);
            map_parametros.put("ide_yhogra", tab_consuta.getValor("ide_yhogra"));
            map_parametros.put("ide_ystjor", tab_consuta.getValor("ide_ystjor"));
            map_parametros.put("ide_ystnie", tab_consuta.getValor("ide_ystnie"));
            map_parametros.put("ide_ynotie", parcial);
            vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
            vipdf_comprobante.dibujar();
            utilitario.addUpdate("vipdf_comprobante");

        } else if (nombre_reporte.equals("Nota Final por Actividades")) {

            Map map_parametros = new HashMap();
            map_parametros.put("nombre", utilitario.getVariable("NICK"));
            map_parametros.put("ide_ystpea", com_periodo_academico.getValue().toString());
            map_parametros.put("ide_ystmal", tab_consuta.getValor("ide_ystmal"));
            map_parametros.put("ide_ypedpe", ide_docente);
            map_parametros.put("ide_yhogra", tab_consuta.getValor("ide_yhogra"));
            map_parametros.put("ide_ystjor", tab_consuta.getValor("ide_ystjor"));
            map_parametros.put("ide_ystnie", tab_consuta.getValor("ide_ystnie"));
            map_parametros.put("ide_ynotie", parcial);
            map_parametros.put("ide_ynoace", actividad);
            vipdf_comprobante.setVisualizarPDF(reporte, map_parametros);
            vipdf_comprobante.dibujar();
            utilitario.addUpdate("vipdf_comprobante");
        }
    }

    public void calcularNota() {

        System.out.println(">>>>>>>>>>> materia: "+materia);
        
        String cod = materia+"";
        TablaGenerica tab_consulta = utilitario.consultar(ser_notas.getPersonMallaDocente(cod));
        TablaGenerica tab_peso = utilitario.consultar(ser_notas.getPesoNota("3", "true", tab_consulta.getValor("ide_ysttfe")));
        System.out.println(" <<<<<<<<<<<<<<<<< TAB PESO >>>>>>>>>>>>>>>>>");
        tab_peso.imprimirSql();
        for (int i = 0; i < tab_peso.getTotalFilas(); i++) {
            TablaGenerica tab_detalle = utilitario.consultar(ser_notas.getPesoDetalleNota(tab_peso.getValor(i, "ide_ynopen")));
            for (int j = 0; j < tab_docente_alumno.getTotalFilas(); j++) {
                utilitario.getConexion().ejecutarSql(ser_notas.getActualizarTablaResumen(com_periodo_academico.getValue().toString(), tab_consulta.getValor("ide_ystmen"), tab_consulta.getValor("ide_ystnie"), tab_consulta.getValor("ide_ypedpe"), tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_consulta.getValor("ide_ystmal"), tab_docente_alumno.getValor(j, "ide_yaldap"), tab_peso.getValor(i, "ide_ynopen")));
                utilitario.getConexion().ejecutarSql(ser_notas.getActualizarTablaResumenNota(tab_docente_alumno.getValor(j, "ide_ypemda"), tab_peso.getValor(i, "ide_ynopen")));
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
        notaTotalActividades();
        notaTotalParcial();
        notaFinal();
        utilitario.addUpdate("tab_resumen_nota");
        //tab_resumen_nota.ejecutarValorForanea(tab_docente_alumno.getValorSeleccionado());

    }

    public void notaTotalActividades() {

        String cod = materia+"";
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

    public void notaTotalParcial() {
        String cod = materia+"";
        TablaGenerica tab_consulta = utilitario.consultar(ser_notas.getPersonMallaDocente(cod));
        TablaGenerica tab_peso = utilitario.consultar(ser_notas.getPadreSegundoNivel("2", "true"));
        for (int i = 0; i < tab_peso.getTotalFilas(); i++) {
            for (int j = 0; j < tab_docente_alumno.getTotalFilas(); j++) {
                utilitario.getConexion().ejecutarSql(ser_notas.getActualizarTablaResumenNota(tab_docente_alumno.getValor(j, "ide_ypemda"), tab_peso.getValor(i, "ide_ynopen")));
                TablaGenerica tab_segundoNivel = utilitario.consultar(ser_notas.getConsultarNotaTotalSegundoNivel(tab_peso.getValor(i, "ide_ynopen"), com_periodo_academico.getValue().toString(), tab_peso.getValor(i, "ide_ynotie"), tab_docente_alumno.getValor(j, "ide_yaldap")));
                TablaGenerica tab_codigo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_nota_alumno_resumen", "ide_ynoalr"));
                utilitario.getConexion().ejecutarSql(ser_notas.getInsertarTabAlumnoResumen(tab_codigo.getValor("maximo"), tab_peso.getValor(i, "ide_ynopen"), tab_docente_alumno.getValor(j, "ide_ypemda"), tab_segundoNivel.getValor("total"), tab_peso.getValor(i, "peso_ynopen")));
            }
        }
    }

    public void notaFinal() {
        TablaGenerica tab_peso = utilitario.consultar(ser_notas.getPadreTercerNivel("1", "true"));
        for (int i = 0; i < tab_peso.getTotalFilas(); i++) {
            for (int j = 0; j < tab_docente_alumno.getTotalFilas(); j++) {
                utilitario.getConexion().ejecutarSql(ser_notas.getActualizarTablaResumenNota(tab_docente_alumno.getValor(j, "ide_ypemda"), tab_peso.getValor(i, "ide_ynopen")));
                TablaGenerica tab_tercerNivel = utilitario.consultar(ser_notas.getConsultarNotaTotalTercerNivel(tab_peso.getValor(i, "ide_ynopen"), com_periodo_academico.getValue().toString(), tab_docente_alumno.getValor(j, "ide_yaldap")));
                TablaGenerica tab_codigo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_nota_alumno_resumen", "ide_ynoalr"));
                utilitario.getConexion().ejecutarSql(ser_notas.getInsertarTabAlumnoResumen(tab_codigo.getValor("maximo"), tab_peso.getValor(i, "ide_ynopen"), tab_docente_alumno.getValor(j, "ide_ypemda"), tab_tercerNivel.getValor("total"), tab_peso.getValor(i, "peso_ynopen")));
            }
        }
        tab_resumen_nota.guardar();
        guardarPantalla();
        //utilitario.agregarMensajeInfo("Mensaje", "Se calculo correctamente las notas");
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

    public ListaSeleccion getLis_materia() {
        return lis_materia;
    }

    public void setLis_materia(ListaSeleccion lis_materia) {
        this.lis_materia = lis_materia;
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

    public Tabla getTab_resumen_nota() {
        return tab_resumen_nota;
    }

    public void setTab_resumen_nota(Tabla tab_resumen_nota) {
        this.tab_resumen_nota = tab_resumen_nota;
    }

    public Reporte getRep_reporte() {
        return rep_reporte;
    }

    public void setRep_reporte(Reporte rep_reporte) {
        this.rep_reporte = rep_reporte;
    }

    public Map getP_parametros() {
        return p_parametros;
    }

    public void setP_parametros(Map p_parametros) {
        this.p_parametros = p_parametros;
    }

    public VisualizarPDF getVipdf_comprobante() {
        return vipdf_comprobante;
    }

    public void setVipdf_comprobante(VisualizarPDF vipdf_comprobante) {
        this.vipdf_comprobante = vipdf_comprobante;
    }

    public String getIde_docente() {
        return ide_docente;
    }

    public void setIde_docente(String ide_docente) {
        this.ide_docente = ide_docente;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    
    
}
