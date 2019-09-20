
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_inscripcion;

import framework.aplicacion.TablaGenerica;
import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.Calendario;
import framework.componentes.Combo;
import framework.componentes.Confirmar;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Espacio;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Imagen;
import framework.componentes.PanelTabla;
import framework.componentes.Reporte;
import framework.componentes.SeleccionFormatoReporte;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import framework.componentes.Upload;
import framework.componentes.VisualizarPDF;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import org.primefaces.component.editor.Editor;
import org.primefaces.event.FileUploadEvent;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_inscripcion.ejb.ServicioInscripcion;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;
import jxl.Sheet;
import jxl.Workbook;
import java.util.List;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Cristian Vega
 * @deprecated Prueba de versionamiento
 */
public class Preinscripcion extends Pantalla {

    private Tabla tab_pre_inscrip = new Tabla();
    private Tabla tab_requ_entregado = new Tabla();
    private Tabla tab_alumno = new Tabla();
    private Dialogo crear_alumno = new Dialogo();
    private Combo com_periodo_academico = new Combo();
    private SeleccionTabla sel_registra_alumno = new SeleccionTabla();
    private SeleccionTabla sel_actualiza_alumno = new SeleccionTabla();
    private Confirmar con_guardar_alumno = new Confirmar();
    private Reporte rep_reporte = new Reporte();
    private SeleccionFormatoReporte sel_rep = new SeleccionFormatoReporte();
    private VisualizarPDF vipdf_comprobante = new VisualizarPDF();
    private VisualizarPDF vipdf_rep_grafico = new VisualizarPDF();

    private Tabla tab_direccion = new Tabla();
    private Tabla tab_telefono = new Tabla();
    private Tabla tab_correo = new Tabla();
    private Tabla tab_familiar = new Tabla();
    private Upload upl_archivo = new Upload();
    private Dialogo dia_importar = new Dialogo();
    private Editor edi_mensajes = new Editor();
    private Combo com_carrera = new Combo();
    private AutoCompletar aut_carrera = new AutoCompletar();
    private Calendario cal_fecha = new Calendario();
    private List<String[]> lis_importa = null; //Guardo los empleados y el valor del rubro
    private AutoCompletar aut_alumno = new AutoCompletar();

    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_EstructuraOrganizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioInscripcion ser_inscripcion = (ServicioInscripcion) utilitario.instanciarEJB(ServicioInscripcion.class);

    public static String par_modulo_inscripcion;

    public Preinscripcion() {
        if (tienePerfiInscripcion()) {

            aut_alumno.setId("aut_alumno");
            aut_alumno.setAutoCompletar(ser_alumno.getDatosAlumnos("true,false"));
            aut_alumno.setSize(75);
            aut_alumno.setMetodoChange("filtrarAlumno");
            bar_botones.agregarComponente(new Etiqueta("Alumno :"));
            bar_botones.agregarComponente(aut_alumno);

            par_modulo_inscripcion = utilitario.getVariable("p_documento_inscripcion");

            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_EstructuraOrganizacional.getPeriodoAcademico("true"));
            agregarComponente(com_periodo_academico);
            bar_botones.agregarComponente(com_periodo_academico);
            //com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
            //

            Grid grup_cuerpo = new Grid();

            grup_cuerpo.setId("grup_cuerpo");

            grup_cuerpo.setColumns(14);
            grup_cuerpo.setWidth("100%");
            grup_cuerpo.setId("grup_cuerpo");

            //bar_botones.agregarBoton(bot_anular);
            Boton bot_imprimir = new Boton();
            bot_imprimir.setIcon("ui-icon-print");
            bot_imprimir.setValue("CERTIFICADO INSCRIPCION");
            bot_imprimir.setMetodo("generarPDF");

            grup_cuerpo.getChildren().add(bot_imprimir);

            //bar_botones.agregarBoton(bot_anular);
            Boton bot_imprimir_ins = new Boton();
            bot_imprimir_ins.setIcon("ui-icon-print");
            bot_imprimir_ins.setValue("REPORTE INSTITUTOS");
            bot_imprimir_ins.setMetodo("generarPDFins");
            grup_cuerpo.getChildren().add(bot_imprimir_ins);

            //BOTON REGISTRO DE ALUMNOS
            Boton bot_registroAlumno = new Boton();
            bot_registroAlumno.setValue("Listado Alumnos");
            bot_registroAlumno.setIcon("ui-icon-note");
            bot_registroAlumno.setMetodo("selregistraAlumno");
            grup_cuerpo.getChildren().add(bot_registroAlumno);

            //BOTON ACTUALIZAR DE ALUMNOS
            Boton bot_actualizaAlumno = new Boton();
            bot_actualizaAlumno.setValue("Actualizar Alumno");
            bot_actualizaAlumno.setIcon("ui-icon-refresh");
            bot_actualizaAlumno.setMetodo("selactualizaAlumno");
            grup_cuerpo.getChildren().add(bot_actualizaAlumno);

            //BOTON RECEPCION DE DOCUMENTOS
            Boton bot_recibe_documento = new Boton();
            bot_recibe_documento.setValue("Recibir Documentos");
            bot_recibe_documento.setIcon("ui-icon-clipboard");
            bot_recibe_documento.setMetodo("recibeDocumento");
            grup_cuerpo.getChildren().add(bot_recibe_documento);

            //BOTON CARGAR ARCHIVO
            Boton bot_archivo = new Boton();
            bot_archivo.setValue("Cargar Archivo SENECYT");
            bot_archivo.setIcon("ui-icon-clipboard");
            bot_archivo.setMetodo("abrirDialogoImportar");
            grup_cuerpo.getChildren().add(bot_archivo);

            //BOTON AGREGAR ALUMNO
            Boton bot_agregarAlumno = new Boton();
            bot_agregarAlumno.setValue("Crear Alumno");
            bot_agregarAlumno.setIcon("ui-icon-person");
            bot_agregarAlumno.setMetodo("crearAlumno");
            grup_cuerpo.getChildren().add(bot_agregarAlumno);

            tab_pre_inscrip.setId("tab_pre_inscrip");
            tab_pre_inscrip.setTabla("yavirac_ins_pre_inscripcion", "ide_yinpin", 1);
            tab_pre_inscrip.setCondicion("ide_yinpin=-1");
            tab_pre_inscrip.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
            tab_pre_inscrip.getColumna("ide_yaldap").setAutoCompletar();
            tab_pre_inscrip.getColumna("ide_yaldap").setLectura(true);
            tab_pre_inscrip.getColumna("ide_ystpea").setCombo(ser_EstructuraOrganizacional.getPeriodoAcademico("true,false"));
            tab_pre_inscrip.getColumna("ide_ystpea").setVisible(false);
            //tab_pre_inscrip.setCondicion("ide_ystpea=-1");
            tab_pre_inscrip.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
            tab_pre_inscrip.getColumna("ide_ypedpe").setAutoCompletar();
            tab_pre_inscrip.getColumna("ide_ypedpe").setLectura(true);
            tab_pre_inscrip.getColumna("fecha_inscripcion_yinpin").setValorDefecto(utilitario.getFechaActual());
            tab_pre_inscrip.getColumna("ide_ystmen").setCombo(ser_EstructuraOrganizacional.getMension());
            tab_pre_inscrip.getColumna("ide_ystmen").setLongitud(50);
            tab_pre_inscrip.getColumna("ide_ystmen").setRequerida(true);
            tab_pre_inscrip.getColumna("recibido_yinpin").setValorDefecto("false");
            tab_pre_inscrip.getColumna("recibido_yinpin").setLectura(true);
            tab_pre_inscrip.getColumna("ide_yinsin").setCombo(ser_inscripcion.getSqlInstituos());

            tab_pre_inscrip.agregarRelacion(tab_requ_entregado);
            tab_pre_inscrip.setTipoFormulario(true);
            tab_pre_inscrip.getGrid().setColumns(4);
            tab_pre_inscrip.getColumna("ide_yinpin").setNombreVisual("CODIGO");
            tab_pre_inscrip.getColumna("ide_ypedpe").setNombreVisual("RESPONSABLE");
            tab_pre_inscrip.getColumna("ide_yaldap").setNombreVisual("DATO ALUMNO");
            tab_pre_inscrip.getColumna("ide_ystmen").setNombreVisual("CARRERA");
            tab_pre_inscrip.getColumna("docum_senecyd_yinpin").setNombreVisual("DOCUMENTO SENESCYT");
            tab_pre_inscrip.getColumna("fecha_inscripcion_yinpin").setNombreVisual("FECHA INSCRIPCION");
            tab_pre_inscrip.getColumna("observacion_yinpin").setNombreVisual("OBSERVACION");
            tab_pre_inscrip.getColumna("recibido_yinpin").setNombreVisual("RECIBIDO");
            tab_pre_inscrip.dibujar();

            PanelTabla pat_pre_inscrip = new PanelTabla();
            pat_pre_inscrip.setId("pat_pre_inscrip");
            pat_pre_inscrip.setPanelTabla(tab_pre_inscrip);

            tab_requ_entregado.setId("tab_requ_entregado");
            tab_requ_entregado.setTabla("yavirac_ins_registro_entregado", "ide_yinree", 2);
            tab_requ_entregado.getColumna("ide_ystdor").setCombo(ser_EstructuraOrganizacional.getDocumentoRequerido("true,false"));
            tab_requ_entregado.getColumna("ide_yinree").setNombreVisual("CODIGO");
            tab_requ_entregado.getColumna("ide_ystdor").setNombreVisual("DOCUMENTO REQUERIDO");
            tab_requ_entregado.getColumna("observacion_yinree").setNombreVisual("OBSERVACION");
            tab_requ_entregado.getColumna("entregado_yinree").setNombreVisual("ENTREGADO");
            tab_requ_entregado.dibujar();

            PanelTabla pat_requ_entregado = new PanelTabla();
            pat_requ_entregado.setId("pat_requ_entregado");
            pat_requ_entregado.setPanelTabla(tab_requ_entregado);

            Division div_pre_inscrip = new Division();
            div_pre_inscrip.setId("div_pre_inscripo");
            div_pre_inscrip.dividir3(grup_cuerpo,pat_pre_inscrip, pat_requ_entregado, "14%","50%", "H");

            agregarComponente(div_pre_inscrip);

            //PANTALLA CREAR alumno
            crear_alumno.setId("crear_alumno");
            crear_alumno.setTitle("CREAR ALUMNO ASPIRANTE");
            crear_alumno.setWidth("45%");
            crear_alumno.setHeight("45%");

            Grid gri_cuerpo = new Grid();
            tab_alumno.setId("tab_alumno");
            tab_alumno.setTabla("yavirac_alum_dato_personal", "ide_yaldap", 3);
            tab_alumno.setTipoFormulario(true);
            tab_alumno.setCondicion("ide_yaldap=-1");//para que aparesca vacia
            tab_alumno.getColumna("ide_yaldap").setVisible(false);
            tab_alumno.getColumna("firma_yaldap").setVisible(false);
            tab_alumno.getColumna("fotografia_yaldap").setVisible(false);
            tab_alumno.getColumna("edad_yaldap").setVisible(false);
            tab_alumno.getColumna("discapacidad_yaldap").setVisible(false);
            tab_alumno.getColumna("activo_yaldap").setVisible(false);
            tab_alumno.getColumna("discapacidad_yaldap").setValorDefecto("true");
            tab_alumno.getColumna("activo_yaldap").setValorDefecto("true");
            tab_alumno.getColumna("ide_ystnac").setCombo(ser_EstructuraOrganizacional.getNacionalidad("true,false"));
            tab_alumno.getColumna("ide_ysttis").setCombo(ser_EstructuraOrganizacional.getTipoSangre("true,false"));
            tab_alumno.getColumna("ide_ystdoi").setCombo(ser_EstructuraOrganizacional.getDocumentoIdentidad("true,false"));
            tab_alumno.getColumna("ide_ystdip").setCombo(ser_EstructuraOrganizacional.getDistribucionPolitica("true,false"));
            tab_alumno.getColumna("ide_ystesc").setCombo(ser_EstructuraOrganizacional.getEstadoCivil("true,false"));
            tab_alumno.getColumna("ide_ystgen").setCombo(ser_EstructuraOrganizacional.getGenero("true,false"));

            tab_alumno.getColumna("ide_ystnac").setNombreVisual("NACIONALIDAD");
            tab_alumno.getColumna("ide_ysttis").setNombreVisual("TIPO SANGRE");
            tab_alumno.getColumna("ide_ystdoi").setNombreVisual("TIPO DOC. IDENTIDAD");
            tab_alumno.getColumna("ide_ystdip").setNombreVisual("LUGAR NACIMIENTO");
            tab_alumno.getColumna("ide_ystesc").setNombreVisual("ESTADO CIVIL");
            tab_alumno.getColumna("apellido_yaldap").setNombreVisual("APELLIDOS");
            tab_alumno.getColumna("nombre_yaldap").setNombreVisual("NOMBRES");
            tab_alumno.getColumna("fecha_nac_yaldap").setNombreVisual("FEC. NACIMIENTO");
            tab_alumno.getColumna("ide_ystgen").setNombreVisual("SEXO");
            tab_alumno.getColumna("doc_identidad_yaldap").setNombreVisual("DOC. IDENTIDAD");
            tab_alumno.getColumna("ide_ystgen").setAncho(50);

            tab_alumno.setMostrarNumeroRegistros(false);
            tab_alumno.getGrid().setColumns(4);
            tab_alumno.dibujar();
            gri_cuerpo.getChildren().add(tab_alumno);
            crear_alumno.getBot_aceptar().setMetodo("aceptarDialogoAlumno");
            crear_alumno.setDialogo(gri_cuerpo);
            agregarComponente(crear_alumno);

            //PANTALLA INGRESA ALUMNO
            sel_registra_alumno.setId("sel_registra_alumno");
            sel_registra_alumno.setTitle("SELECCIONE EL ALUMNO");
            sel_registra_alumno.getBot_aceptar().setMetodo("registraAlumno");
            sel_registra_alumno.setSeleccionTabla(ser_alumno.getDatosAlumnos("null"), "ide_yaldap");
            sel_registra_alumno.getTab_seleccion().getColumna("apellido_yaldap").setFiltro(true);
            sel_registra_alumno.getTab_seleccion().getColumna("nombre_yaldap").setFiltro(true);
            sel_registra_alumno.getTab_seleccion().getColumna("doc_identidad_yaldap").setFiltro(true);

            sel_registra_alumno.setRadio();
            agregarComponente(sel_registra_alumno);

            //PANTALLA ACTUALIZA ALUMNO
            sel_actualiza_alumno.setId("sel_actualiza_alumno");
            sel_actualiza_alumno.setTitle("SELECCIONE EL ALUMNO");
            sel_actualiza_alumno.getBot_aceptar().setMetodo("actualizaAlumno");
            sel_actualiza_alumno.setSeleccionTabla(ser_alumno.getDatosAlumnos("null"), "ide_yaldap");
            sel_actualiza_alumno.getTab_seleccion().getColumna("apellido_yaldap").setFiltro(true);
            sel_actualiza_alumno.getTab_seleccion().getColumna("nombre_yaldap").setFiltro(true);
            sel_actualiza_alumno.setRadio();
            agregarComponente(sel_actualiza_alumno);

            con_guardar_alumno.setId("con_guardar_alumno");
            agregarComponente(con_guardar_alumno);

            vipdf_comprobante.setId("vipdf_comprobante");
            vipdf_comprobante.setTitle("CERTIFICADO DE INSCRIPCION");
            agregarComponente(vipdf_comprobante);

            vipdf_rep_grafico.setId("vipdf_rep_grafico");
            vipdf_rep_grafico.setTitle("REPORTE POR INSTITUTOS");
            agregarComponente(vipdf_rep_grafico);

            //subir archivo
            Grid gri_cuerpo_archivo = new Grid();

            Grid gri_impo = new Grid();
            gri_impo.setColumns(2);
            gri_impo.getChildren().add(new Etiqueta("Fecha de Registro: "));
            cal_fecha.setId("cal_fecha");
            cal_fecha.setFechaActual();
            cal_fecha.setDisabled(true);
            gri_impo.getChildren().add(cal_fecha);

            gri_impo.getChildren().add(new Etiqueta("Seleccione el archivo: "));
            upl_archivo.setId("upl_archivo");
            upl_archivo.setMetodo("validarArchivo");
            upl_archivo.setUpdate("gri_valida");
            upl_archivo.setAuto(false);
            upl_archivo.setAllowTypes("/(\\.|\\/)(xls)$/");
            upl_archivo.setUploadLabel("Validar");
            upl_archivo.setCancelLabel("Cancelar Seleccion");

            gri_impo.getChildren().add(upl_archivo);
            gri_impo.setWidth("100%");

            Grid gri_valida = new Grid();
            gri_valida.setId("gri_valida");
            gri_valida.setColumns(3);

            Etiqueta eti_valida = new Etiqueta();
            eti_valida.setValueExpression("value", "pre_index.clase.upl_archivo.nombreReal");
            eti_valida.setValueExpression("rendered", "pre_index.clase.upl_archivo.nombreReal != null");
            gri_valida.getChildren().add(eti_valida);

            Imagen ima_valida = new Imagen();
            ima_valida.setWidth("22");
            ima_valida.setHeight("22");
            ima_valida.setValue("/imagenes/im_excel.gif");
            ima_valida.setValueExpression("rendered", "pre_index.clase.upl_archivo.nombreReal != null");
            gri_valida.getChildren().add(ima_valida);

            edi_mensajes.setControls("");
            edi_mensajes.setId("edi_mensajes");
            edi_mensajes.setStyle("overflow:auto;");
            edi_mensajes.setWidth(dia_importar.getAnchoPanel() - 15);
            edi_mensajes.setDisabled(true);
            gri_valida.setFooter(edi_mensajes);

            gri_cuerpo_archivo.setStyle("width:" + (dia_importar.getAnchoPanel() - 5) + "px;");
            gri_cuerpo_archivo.setMensajeInfo("Esta opción  permite subir el registro de Inscripciones SENECYT a partir de un archivo xls");
            gri_cuerpo_archivo.getChildren().add(gri_impo);
            gri_cuerpo_archivo.getChildren().add(gri_valida);
            gri_cuerpo_archivo.getChildren().add(edi_mensajes);
            gri_cuerpo_archivo.getChildren().add(new Espacio("0", "10"));

            dia_importar.setDialogo(gri_cuerpo_archivo);
            dia_importar.getBot_aceptar().setMetodo("aceptaDiaImp");
            dia_importar.setDynamic(false);

            agregarComponente(dia_importar);

        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el registro de Inscricpiones. Consulte con el Administrador");
        }
    }

    public void filtrarAlumno(SelectEvent evt) {
        aut_alumno.onSelect(evt);
        tab_pre_inscrip.setCondicion("ide_yaldap=" + aut_alumno.getValor());
        tab_pre_inscrip.ejecutarSql();
        tab_requ_entregado.ejecutarValorForanea(tab_pre_inscrip.getValorSeleccionado());
        utilitario.addUpdate("tab_pre_inscrip");
    }

    String str_resultado = "1";

    public void aceptaDiaImp() {
        dia_importar.cerrar();
        //tab_pre_inscrip.setCondicion("ide_ystpea=" + com_periodo_academico.getValue().toString());
        //tab_pre_inscrip.ejecutarSql();
        utilitario.addUpdate("tab_pre_inscrip");
    }

    public void validarArchivo(FileUploadEvent evt) {
        //Leer el archivo
        String str_msg_info = "";
        String str_msg_adve = "";
        String str_msg_erro = "";
        String codigo_yaldap = "";
        String codigo_discapacidad = "";
        String str_resul2 = "";
        System.out.println(" valor de resultado " + str_resultado);
        if (validarArchivoMensa(evt).equals("1")) {
            System.out.println(" entre if_valida " + str_resultado);

            try {
                Workbook archivoExcel = Workbook.getWorkbook(evt.getFile().getInputstream());
                Sheet hoja = archivoExcel.getSheet(0);//LEE LA PRIMERA HOJA

                if (hoja == null) {
                    utilitario.agregarMensajeError("No existe ninguna hoja en el archivo seleccionado", "");
                    return;
                }

                int int_fin = hoja.getRows();
                upl_archivo.setNombreReal(evt.getFile().getFileName());
                str_msg_info += getFormatoInformacion("El archivo " + upl_archivo.getNombreReal() + " contiene " + int_fin + " filas que se subieron con exito, dar clic en aceptar y actualizar la pantalla de inscripciones");

                System.out.println(" valor int_fin " + int_fin);
                for (int i = 0; i < int_fin; i++) {
                    System.out.println(" entre for " + i);

                    String str_institucion = hoja.getCell(0, i).getContents();
                    str_institucion = str_institucion.trim();
                    //System.out.println("str_institucion "+str_institucion);

                    String str_cedula = hoja.getCell(1, i).getContents();
                    str_cedula = str_cedula.trim();
                    //System.out.println("str_cedula "+str_cedula);             

                    String str_apellido = hoja.getCell(2, i).getContents();
                    str_apellido = str_apellido.trim();
                    //System.out.println("str_apellido "+str_apellido);

                    String str_nombre = hoja.getCell(3, i).getContents();
                    str_nombre = str_nombre.trim();
                    //System.out.println("str_nombre "+str_nombre);

                    String str_nota = hoja.getCell(4, i).getContents();
                    str_nota = str_nota.trim();
                    //System.out.println("str_nota "+str_nota);

                    String str_fecha_naci = hoja.getCell(5, i).getContents();
                    str_fecha_naci = str_fecha_naci.trim();
                    //System.out.println("str_fecha_naci "+str_fecha_naci);

                    String str_nacionalidad = hoja.getCell(6, i).getContents();
                    str_nacionalidad = str_nacionalidad.trim();
                    //System.out.println("str_nacionalidad "+str_nacionalidad);

                    String str_genero = hoja.getCell(7, i).getContents();
                    str_genero = str_genero.trim();
                    //System.out.println("str_genero "+str_genero);

                    String str_canton = hoja.getCell(8, i).getContents();
                    str_canton = str_canton.trim();
                    //System.out.println("str_canton "+str_canton);

                    String str_correo = hoja.getCell(9, i).getContents();
                    str_correo = str_correo.trim();
                    //System.out.println("str_correo "+str_correo);

                    String str_telefono = hoja.getCell(10, i).getContents();
                    str_telefono = str_telefono.trim();
                    //System.out.println("str_telefono "+str_telefono);

                    String str_movil = hoja.getCell(11, i).getContents();
                    str_movil = str_movil.trim();
                    //System.out.println("str_movil "+str_movil);

                    String str_discapacidad = hoja.getCell(12, i).getContents();
                    str_discapacidad = str_discapacidad.trim();
                    //System.out.println("str_discapacidad "+str_discapacidad);

                    String str_carrera = hoja.getCell(13, i).getContents();
                    str_carrera = str_carrera.trim();
                    //System.out.println("str_carrera "+str_carrera);

                    TablaGenerica tab_carrer = utilitario.consultar("select * from yavirac_stror_mension where codigo_mension_ystmen='" + str_carrera + "'");
                    TablaGenerica tab_institucion = utilitario.consultar("select * from yavirac_ins_instituto where codigo_instit_yinsin=" + str_institucion);
                    TablaGenerica tab_canton = utilitario.consultar("select * from yavirac_stror_distribucion_pol where descripcion_ystdip='" + str_canton + "' and ide_ysttdp=3 ");
                    TablaGenerica tab_cedula = utilitario.consultar(ser_alumno.getDatosAlumnosCedula(str_cedula));
                    TablaGenerica tab_maximo = utilitario.consultar(ser_EstructuraOrganizacional.getCodigoMaximoTabla("yavirac_alum_dato_personal", "ide_yaldap"));
                    TablaGenerica tab_maximo_correo = utilitario.consultar(ser_EstructuraOrganizacional.getCodigoMaximoTabla("yavirac_alum_correo", "ide_yalcor"));
                    TablaGenerica tab_maximo_conve = utilitario.consultar(ser_EstructuraOrganizacional.getCodigoMaximoTabla("yavirac_alum_telefono", "ide_yaltel"));
                    TablaGenerica tab_nacionalidad = utilitario.consultar("SELECT ide_ystnac, descripcion_ystnac FROM yavirac_stror_nacionalidad where descripcion_ystnac ='" + str_nacionalidad + "'");
                    TablaGenerica tab_genero = utilitario.consultar("select ide_ystgen,descripcion_ystgen from yavirac_stror_genero where descripcion_ystgen='" + str_genero + "'");
                    if (str_discapacidad.equals("SI")) {
                        codigo_discapacidad = "true";
                    } else {
                        codigo_discapacidad = "false";
                    }
                    System.out.println("antes de netrar a la cedula");
                    if (tab_cedula.isEmpty()) {
                        System.out.println("Entre cuanod cedula es nulo");
                        String sql_alumno = "INSERT INTO yavirac_alum_dato_personal(ide_yaldap, ide_ystnac, ide_ystdoi, ide_ystdip, ide_ystesc,ide_ystgen, nombre_yaldap, apellido_yaldap, doc_identidad_yaldap, \n"
                                + " fecha_nac_yaldap, activo_yaldap, discapacidad_yaldap) VALUES (" + tab_maximo.getValor("maximo") + "," + tab_nacionalidad.getValor("ide_ystnac") + "," + utilitario.getVariable("p_gth_tipo_documento_cedula") + ","
                                + tab_canton.getValor("ide_ystdip") + ",1," + tab_genero.getValor("ide_ystgen") + ",'" + str_nombre + "','" + str_apellido + "','" + str_cedula + "','" + str_fecha_naci + "',true," + codigo_discapacidad + ");";
                        System.out.println("inserto alumno " + sql_alumno);
                        utilitario.getConexion().ejecutarSql(sql_alumno);

                        codigo_yaldap = tab_maximo.getValor("maximo");

                        String sql_correo = "INSERT INTO yavirac_alum_correo(ide_yalcor, ide_ysttoc, ide_yaldap, descripcion_yalcor,notificacion_yalcor, activo_yalcor)\n"
                                + "    VALUES (" + tab_maximo_correo.getValor("maximo") + ",1," + codigo_yaldap + ",'" + str_correo + "',true,true);";
                        System.out.println("inserto correo " + sql_correo);
                        utilitario.getConexion().ejecutarSql(sql_correo);

                        utilitario.getConexion().ejecutarSql("INSERT INTO yavirac_alum_telefono(ide_yaltel,ide_ysttit,ide_yaldap, numero_yaltel,activo_yaltel, notificacion_yaltel) "
                                + "    VALUES (" + tab_maximo_conve.getValor("maximo") + ",2," + codigo_yaldap + ",'" + str_telefono + "',true,true);");
                        TablaGenerica tab_maximo_movil = utilitario.consultar(ser_EstructuraOrganizacional.getCodigoMaximoTabla("yavirac_alum_telefono", "ide_yaltel"));
                        utilitario.getConexion().ejecutarSql("INSERT INTO yavirac_alum_telefono(ide_yaltel,ide_ysttit,ide_yaldap, numero_yaltel,activo_yaltel, notificacion_yaltel) "
                                + "    VALUES (" + tab_maximo_movil.getValor("maximo") + ",1," + codigo_yaldap + ",'" + str_movil + "',true,true);");

                    } else {
                        codigo_yaldap = tab_cedula.getValor("ide_yaldap");
                    }
                    TablaGenerica tab_maximo_ins = utilitario.consultar(ser_EstructuraOrganizacional.getCodigoMaximoTabla("yavirac_ins_pre_inscripcion", "ide_yinpin"));
                    String inscripcion = "INSERT INTO yavirac_ins_pre_inscripcion(ide_yinpin, ide_yaldap, ide_ystpea, ide_ypedpe, ide_ystmen, \n"
                            + "fecha_inscripcion_yinpin, recibido_yinpin, ide_yinsin, nota_obtenida_yinsin)\n"
                            + "VALUES (" + tab_maximo_ins.getValor("maximo") + "," + codigo_yaldap + "," + com_periodo_academico.getValue() + "," + ide_docente + "," + tab_carrer.getValor("ide_ystmen") + ", "
                            + "'" + cal_fecha.getFecha() + "',true," + tab_institucion.getValor("ide_yinsin") + "," + str_nota + ");";
                    utilitario.getConexion().ejecutarSql(inscripcion);
                }

                archivoExcel.close();
            } catch (Exception e) {
                // TODO: handle exception
            }

            if (!str_msg_info.isEmpty()) {
                str_resultado = "<strong><font color='#3333ff'>INFORMACION</font></strong>" + str_msg_info;
            }/*
        if (!str_msg_adve.isEmpty()) {
            str_resul2 += "<strong><font color='#ffcc33'>ADVERTENCIAS</font></strong>" + str_msg_adve;
        }
        if (!str_msg_erro.isEmpty()) {
            str_resul2 += "<strong><font color='#ff0000'>ERRORES</font></strong>" + str_msg_erro;
        }
             */
            edi_mensajes.setValue(str_resultado);
            utilitario.addUpdate("edi_mensajes,eti_tot_val_imp");

        } else {
            edi_mensajes.setValue(str_resultado);

            utilitario.addUpdate("edi_mensajes,eti_tot_val_imp");
        }
    }

    public String validarArchivoMensa(FileUploadEvent evt) {
        //Leer el archivo
        str_resultado = "1";
        String str_msg_info = "";
        String str_msg_adve = "";
        String str_msg_erro = "";
        try {
            Workbook archivoExcel = Workbook.getWorkbook(evt.getFile().getInputstream());
            Sheet hoja = archivoExcel.getSheet(0);//LEE LA PRIMERA HOJA
            /*
            if (hoja == null) {
                utilitario.agregarMensajeError("No existe ninguna hoja en el archivo seleccionado", "");
                return;
            }
             */
            int int_fin = hoja.getRows();
            upl_archivo.setNombreReal(evt.getFile().getFileName());
            //str_msg_info += getFormatoInformacion("El archivo " + upl_archivo.getNombreReal() + " contiene " + int_fin + " filas");

            for (int i = 0; i < int_fin; i++) {

                String str_institucion = hoja.getCell(0, i).getContents();
                str_institucion = str_institucion.trim();

                String str_cedula = hoja.getCell(1, i).getContents();
                str_cedula = str_cedula.trim();

                String str_apellido = hoja.getCell(2, i).getContents();
                str_apellido = str_apellido.trim();

                String str_nombre = hoja.getCell(3, i).getContents();
                str_nombre = str_nombre.trim();

                String str_nota = hoja.getCell(4, i).getContents();
                str_nota = str_nota.trim();

                String str_fecha_naci = hoja.getCell(5, i).getContents();
                str_fecha_naci = str_fecha_naci.trim();

                String str_nacionalidad = hoja.getCell(6, i).getContents();
                str_nacionalidad = str_nacionalidad.trim();

                String str_genero = hoja.getCell(7, i).getContents();
                str_genero = str_genero.trim();

                String str_canton = hoja.getCell(8, i).getContents();
                str_canton = str_canton.trim();

                String str_correo = hoja.getCell(9, i).getContents();
                str_correo = str_correo.trim();

                String str_telefono = hoja.getCell(10, i).getContents();
                str_telefono = str_telefono.trim();

                String str_movil = hoja.getCell(11, i).getContents();
                str_movil = str_movil.trim();

                String str_discapacidad = hoja.getCell(12, i).getContents();
                str_discapacidad = str_discapacidad.trim();

                String str_carrera = hoja.getCell(13, i).getContents();
                str_carrera = str_carrera.trim();

                if (str_carrera == null || str_carrera.isEmpty()) {
                    //No existe el documento en la tabla de empleados
                    str_msg_erro += getFormatoError("No existe el código de carrera en la Fila: " + (i + 1));
                } else {
                    TablaGenerica tab_carrer = utilitario.consultar("select * from yavirac_stror_mension where codigo_mension_ystmen='" + str_carrera + "'");
                    if (tab_carrer.isEmpty()) {
                        //No existe el documento en la tabla de empleados
                        str_msg_erro += getFormatoError("El código de carrera <<" + str_carrera + " >> no se encuentra registrado en la base de datos SAAYA revisar en la Fila: " + (i + 1) + " no es válido");
                    }
                }
                if (str_institucion == null || str_institucion.isEmpty()) {
                    //No existe el documento en la tabla de empleados
                    str_msg_erro += getFormatoError("No existe el código de institución en la Fila: " + (i + 1));
                } else {
                    TablaGenerica tab_institucion = utilitario.consultar("select * from yavirac_ins_instituto where codigo_instit_yinsin=" + str_institucion);
                    if (tab_institucion.isEmpty()) {
                        //No existe el documento en la tabla de empleados
                        str_msg_erro += getFormatoError("El código de institución <<" + str_institucion + " >> no se encuentra registrado en la base de datos SAAYA revisar en la Fila: " + (i + 1) + " no es válido");
                    }
                }

                if (str_canton == null || str_canton.isEmpty()) {
                    //No existe el documento en la tabla de empleados
                    str_msg_erro += getFormatoError("No existe el cantón de procedencia del estudiante en la Fila: " + (i + 1));
                } else {
                    TablaGenerica tab_canton = utilitario.consultar("select * from yavirac_stror_distribucion_pol where descripcion_ystdip='" + str_canton + "' and ide_ysttdp=3 ");
                    if (tab_canton.isEmpty()) {
                        //No existe el documento en la tabla de empleados
                        str_msg_erro += getFormatoError("El cantón " + str_canton + " no se encuentra registrado en la base de datos SAAYA revisar en la Fila: " + (i + 1) + " no es válido");
                    }
                }

                if (str_cedula == null || str_cedula.isEmpty()) {
                    //No existe el documento en la tabla de empleados
                    str_msg_erro += getFormatoError("No existe un número de cédula en la Fila: " + (i + 1));
                } else if (!utilitario.validarCedula(str_cedula)) {
                    //No existe el documento en la tabla de empleados
                    str_msg_erro += getFormatoError("Número de cédula en la Fila: " + (i + 1) + " no es válido");
                }

                if (str_correo == null || str_correo.isEmpty()) {
                    //No existe el documento en la tabla de empleados
                } else if (!utilitario.isEmailValido(str_correo)) {
                    //No existe el documento en la tabla de empleados
                    str_msg_erro += getFormatoError("El correo << " + str_correo + " >> en la Fila: " + (i + 1) + " no es válido");
                }

            }

            archivoExcel.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (!str_msg_adve.isEmpty()) {
            str_resultado += "<strong><font color='#ffcc33'>ADVERTENCIAS</font></strong>" + str_msg_adve;
        }
        if (!str_msg_erro.isEmpty()) {
            str_resultado += "<strong><font color='#ff0000'>ERRORES</font></strong>" + str_msg_erro;
        }

        //edi_mensajes.setValue(str_resultado);
        //utilitario.addUpdate("edi_mensajes,eti_tot_val_imp");
        return str_resultado;
    }

    public void abrirDialogoImportar() {

        if (com_periodo_academico.getValue() != null) {
            upl_archivo.limpiar();
            dia_importar.dibujar();
            edi_mensajes.setValue(null);
        } else {
            utilitario.agregarMensajeInfo("Debe seleccionar un Periodo ", "");
        }

    }

    public void generarPDF() {
        if (tab_pre_inscrip.getValorSeleccionado() != null) {
            ///////////AQUI ABRE EL REPORTE
            Map map_parametros = new HashMap();
            map_parametros.put("pide_ins", Integer.parseInt(tab_pre_inscrip.getValor("ide_yinpin")));
            map_parametros.put("nombre", utilitario.getVariable("NICK"));

            //System.out.println(" " + str_titulos);
            vipdf_comprobante.setVisualizarPDF("rep_inscripcion/rep_inscripcion.jasper", map_parametros);
            vipdf_comprobante.dibujar();
            utilitario.addUpdate("vipdf_comprobante");
        } else {
            utilitario.agregarMensajeInfo("Seleccione una Inscripcion", "");
        }
    }

    public void generarPDFins() {
        if (tab_pre_inscrip.getValorSeleccionado() != null) {
            ///////////AQUI ABRE EL REPORTE
            Map map_parametros = new HashMap();
            map_parametros.put("pperiodo", com_periodo_academico.getValue());
            map_parametros.put("nombre", utilitario.getVariable("NICK"));

            //System.out.println(" " + str_titulos);
            vipdf_rep_grafico.setVisualizarPDF("rep_inscripcion/rep_inscripciones_instituto.jasper", map_parametros);
            vipdf_rep_grafico.dibujar();
            utilitario.addUpdate("vipdf_rep_grafico");
        } else {
            utilitario.agregarMensajeInfo("Seleccione un período académico", "");
        }
    }

    public void actualizaAlumno() {
        String str_clienteActualizado = sel_actualiza_alumno.getValorSeleccionado();
        if (str_clienteActualizado != null) {
            tab_pre_inscrip.modificar(tab_pre_inscrip.getFilaActual());

            tab_pre_inscrip.setValor("ide_yaldap", str_clienteActualizado);

            utilitario.addUpdate("tab_pre_inscrip");

            con_guardar_alumno.setMessage("Esta Seguro de Actualizar el Alumno");
            con_guardar_alumno.setTitle("Confirmación de actualizar");
            con_guardar_alumno.getBot_aceptar().setMetodo("guardarActualizarAlumno");
            con_guardar_alumno.dibujar();
            utilitario.addUpdate("con_guardar_alumno");
        }

    }

    public void guardarActualizarAlumno() {
        tab_pre_inscrip.guardar();
        con_guardar_alumno.cerrar();
        sel_actualiza_alumno.cerrar();
        guardarPantalla();
    }

    public void registraAlumno() {
        String str_seleccionado = sel_registra_alumno.getValorSeleccionado();
        if (str_seleccionado != null) {
            //Inserto los cleintes seleccionados en la tabla  
            if (tab_pre_inscrip.isFilaInsertada() == false) {
                //Controla que si ya esta insertada no vuelva a insertar
                tab_pre_inscrip.insertar();
            }
            tab_pre_inscrip.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
            tab_pre_inscrip.setValor("ide_ypedpe", ide_docente);
            tab_pre_inscrip.setValor("ide_yaldap", str_seleccionado);
            tab_pre_inscrip.modificar(tab_pre_inscrip.getFilaActual());//para que haga el update

            sel_registra_alumno.cerrar();
            utilitario.addUpdate("tab_pre_inscrip");
        } else {
            utilitario.agregarMensajeInfo("Debe seleccionar almenos un registro", "");
        }
    }

    public void selregistraAlumno() {

        //Hace aparecer el componente
        if (com_periodo_academico.getValue() == null) {

            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Academico que desea generar");
            return;
        } else {
            sel_registra_alumno.getTab_seleccion().setSql(ser_alumno.getDatosAlumnos("true"));
            sel_registra_alumno.getTab_seleccion().ejecutarSql();
            sel_registra_alumno.dibujar();
        }

    }

    public void selactualizaAlumno() {

        //Hace aparecer el componente
        if (com_periodo_academico.getValue() == null) {

            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Academico que desea generar");
            return;
        } else {
            sel_actualiza_alumno.getTab_seleccion().setSql(ser_alumno.getDatosAlumnos("true"));
            sel_actualiza_alumno.getTab_seleccion().ejecutarSql();
            sel_actualiza_alumno.dibujar();
        }

    }

    String docente = "";
    String documento = "";
    String ide_docente = "";

    private boolean tienePerfiInscripcion() {
        List sql = utilitario.getConexion().consultar(ser_EstructuraOrganizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA"), " and not ide_ypedpe is null"));

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

    public void recibeDocumento() {
        if (tab_pre_inscrip.getValor("recibido_yinpin").equals("false")) {
            TablaGenerica tab_documento = utilitario.consultar(ser_EstructuraOrganizacional.getDocumentoRequeridoPeriodo(com_periodo_academico.getValue().toString(), par_modulo_inscripcion));
            utilitario.getConexion().ejecutarSql(ser_EstructuraOrganizacional.deleteBloqueos(utilitario.getVariable("IDE_USUA")));

            if (tab_documento.getTotalFilas() > 0) {
                for (int i = 0; i < tab_documento.getTotalFilas(); i++) {
                    tab_requ_entregado.insertar();
                    tab_requ_entregado.setValor("ide_yinpin", tab_pre_inscrip.getValor("ide_yinpin"));
                    tab_requ_entregado.setValor("ide_ystdor", tab_documento.getValor(i, "ide_ystdor"));
                    tab_requ_entregado.setValor("entregado_yinree", "true");

                }
                tab_pre_inscrip.setValor("recibido_yinpin", "true");
                tab_pre_inscrip.modificar(tab_pre_inscrip.getFilaActual());//para que haga el update
                utilitario.addUpdate("tab_requ_entregado,tab_pre_inscrip");
                tab_pre_inscrip.guardar();
                tab_requ_entregado.guardar();
                guardarPantalla();

            } else {
                utilitario.agregarMensajeInfo("No existe registros configurados", "Favor configurar documentos parea el Periodo Academico Seleccionado");
            }
        } else {
            utilitario.agregarMensajeError("No puede recibir Documentos", "Usted ya ejecuto la opcion recibir documentos, y no puede volver a ejecutarle pues recibirlo individualmente si lo deses");
        }
    }

    public void aceptarDialogoAlumno() {
        if (!utilitario.validarDocumentoIdentidad(tab_alumno.getValor("ide_ystdoi"), tab_alumno.getValor("doc_identidad_yaldap"))) {
            System.out.println("entre a validar cedula");
            tab_alumno.setValor("doc_identidad_yaldap", "");
            utilitario.addUpdate("tab_alumno");
            return;

        }
        TablaGenerica tab_cedula = utilitario.consultar("SELECT * from yavirac_alum_dato_personal where doc_identidad_yaldap ='" + tab_alumno.getValor("doc_identidad_yaldap") + "' ");
        if (tab_cedula.getTotalFilas() > 0) {
            utilitario.agregarMensajeError("Documento de Identidad Registrado", "El registro que desea guardar ya existe consulte en el registro de alumnos");
        } else if (tab_alumno.guardar()) { //si guarda el slumno cierra el dialogo

            if (guardarPantalla().isEmpty()) {

                crear_alumno.cerrar();
                tab_pre_inscrip.actualizarCombos();//actualiza los combos para que aparezca el nuevo cliente	
                if (tab_pre_inscrip.isFilaInsertada() == false) {
                    tab_pre_inscrip.insertar();
                }
                //CARGA EL CLIENTE Q SE INSERTO
                tab_pre_inscrip.setValor("ide_ypedpe", ide_docente);
                tab_pre_inscrip.setValor("ide_yaldap", tab_alumno.getValor("ide_yaldap"));
                tab_pre_inscrip.setValor("ide_ystpea", com_periodo_academico.getValue().toString());

            }

        }
    }

    public void crearAlumno() {

        //Hace aparecer el componente
        if (com_periodo_academico.getValue() == null) {

            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Academico que desea generar");
            return;
        } else {
            tab_alumno.limpiar();
            tab_alumno.insertar();
            crear_alumno.dibujar();
        }

    }

    public void filtroComboPeriodoAcademnico() {

        tab_pre_inscrip.setCondicion("ide_ystpea=" + com_periodo_academico.getValue().toString());
        tab_pre_inscrip.ejecutarSql();
        utilitario.addUpdate("tab_pre_inscrip");

    }

    /**
     * Genera un mensaje de información color azul
     *
     * @param mensaje
     * @return
     */
    private String getFormatoInformacion(String mensaje) {
        return "<div><font color='#3333ff'><strong>*&nbsp;</strong>" + mensaje + "</font></div>";
    }

    /**
     * Genera un mensaje de Advertencia color tomate
     *
     * @param mensaje
     * @return
     */
    private String getFormatoAdvertencia(String mensaje) {
        return "<div><font color='#ffcc33'><strong>*&nbsp;</strong>" + mensaje + "</font></div>";
    }

    /**
     * Genera un mensaje de Error color rojo
     *
     * @param mensaje
     * @return
     */
    private String getFormatoError(String mensaje) {
        return "<div><font color='#ff0000'><strong>*&nbsp;</strong>" + mensaje + "</font></div>";
    }

    @Override
    public void insertar() {
        if (tab_pre_inscrip.isFocus()) {
            utilitario.agregarMensajeInfo("Registre a un Nuevo Alumno para realizar la inscripcion", "No se puede insertar desde esta opcion");
        } else if (tab_requ_entregado.isFocus()) {
            tab_requ_entregado.insertar();
        }
    }

    @Override
    public void guardar() {
        if (tab_pre_inscrip.isFocus()) {
            tab_pre_inscrip.guardar();
        } else if (tab_requ_entregado.isFocus()) {
            tab_requ_entregado.guardar();
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if (tab_pre_inscrip.isFocus()) {
            tab_pre_inscrip.eliminar();
        } else if (tab_requ_entregado.isFocus()) {
            tab_requ_entregado.eliminar();
        }

    }

    public Tabla getTab_pre_inscrip() {
        return tab_pre_inscrip;
    }

    public void setTab_pre_inscrip(Tabla tab_pre_inscrip) {
        this.tab_pre_inscrip = tab_pre_inscrip;
    }

    public Tabla getTab_requ_entregado() {
        return tab_requ_entregado;
    }

    public void setTab_requ_entregado(Tabla tab_requ_entregado) {
        this.tab_requ_entregado = tab_requ_entregado;
    }

    public Dialogo getCrear_alumno() {
        return crear_alumno;
    }

    public void setCrear_alumno(Dialogo crear_alumno) {
        this.crear_alumno = crear_alumno;
    }

    public Tabla getTab_alumno() {
        return tab_alumno;
    }

    public void setTab_alumno(Tabla tab_alumno) {
        this.tab_alumno = tab_alumno;
    }

    public SeleccionTabla getSel_registra_alumno() {
        return sel_registra_alumno;
    }

    public void setSel_registra_alumno(SeleccionTabla sel_registra_alumno) {
        this.sel_registra_alumno = sel_registra_alumno;
    }

    public SeleccionTabla getSel_actualiza_alumno() {
        return sel_actualiza_alumno;
    }

    public void setSel_actualiza_alumno(SeleccionTabla sel_actualiza_alumno) {
        this.sel_actualiza_alumno = sel_actualiza_alumno;
    }

    public Confirmar getCon_guardar_alumno() {
        return con_guardar_alumno;
    }

    public void setCon_guardar_alumno(Confirmar con_guardar_alumno) {
        this.con_guardar_alumno = con_guardar_alumno;
    }

    public Reporte getRep_reporte() {
        return rep_reporte;
    }

    public void setRep_reporte(Reporte rep_reporte) {
        this.rep_reporte = rep_reporte;
    }

    public SeleccionFormatoReporte getSel_rep() {
        return sel_rep;
    }

    public void setSel_rep(SeleccionFormatoReporte sel_rep) {
        this.sel_rep = sel_rep;
    }

    public VisualizarPDF getVipdf_comprobante() {
        return vipdf_comprobante;
    }

    public void setVipdf_comprobante(VisualizarPDF vipdf_comprobante) {
        this.vipdf_comprobante = vipdf_comprobante;
    }

    public Tabla getTab_direccion() {
        return tab_direccion;
    }

    public void setTab_direccion(Tabla tab_direccion) {
        this.tab_direccion = tab_direccion;
    }

    public Tabla getTab_telefono() {
        return tab_telefono;
    }

    public void setTab_telefono(Tabla tab_telefono) {
        this.tab_telefono = tab_telefono;
    }

    public Tabla getTab_correo() {
        return tab_correo;
    }

    public void setTab_correo(Tabla tab_correo) {
        this.tab_correo = tab_correo;
    }

    public Tabla getTab_familiar() {
        return tab_familiar;
    }

    public void setTab_familiar(Tabla tab_familiar) {
        this.tab_familiar = tab_familiar;
    }

    public Upload getUpl_archivo() {
        return upl_archivo;
    }

    public void setUpl_archivo(Upload upl_archivo) {
        this.upl_archivo = upl_archivo;
    }

    public Dialogo getDia_importar() {
        return dia_importar;
    }

    public void setDia_importar(Dialogo dia_importar) {
        this.dia_importar = dia_importar;
    }

    public AutoCompletar getAut_carrera() {
        return aut_carrera;
    }

    public void setAut_carrera(AutoCompletar aut_carrera) {
        this.aut_carrera = aut_carrera;
    }

    public VisualizarPDF getVipdf_rep_grafico() {
        return vipdf_rep_grafico;
    }

    public void setVipdf_rep_grafico(VisualizarPDF vipdf_rep_grafico) {
        this.vipdf_rep_grafico = vipdf_rep_grafico;
    }

    public AutoCompletar getAut_alumno() {
        return aut_alumno;
    }

    public void setAut_alumno(AutoCompletar aut_alumno) {
        this.aut_alumno = aut_alumno;
    }

}
