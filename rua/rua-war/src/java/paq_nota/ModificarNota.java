/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Confirmar;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.event.SelectEvent;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_nota.ejb.ServicioNotas;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHONPRODUCER
 */
public class ModificarNota extends Pantalla {

    private Tabla tab_detalle_autorizacion = new Tabla();
    private Tabla tab_detalle_nota = new Tabla();
    private Tabla tab_cabecera_nota = new Tabla();
    private Confirmar con_confirma = new Confirmar();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);

    public ModificarNota() {

        if (TienePerfilNota()) {

            bar_botones.getBot_insertar().setRendered(false);
            bar_botones.getBot_eliminar().setRendered(false);
            bar_botones.getBot_guardar().setRendered(false);

            tab_detalle_autorizacion.setId("tab_detalle_autorizacion");
            tab_detalle_autorizacion.setTabla("yavirac_nota_detalle_autorizac", "ide_ynodau", 3);
            tab_detalle_autorizacion.setHeader("AUTORIZAR MODIFICAR NOTA");
            tab_detalle_autorizacion.setCondicion("ide_ypedpe=" + ide_docente + " and ide_ynoest=" + utilitario.getVariable("p_estado_autorizado"));
            //tab_detalle_autorizacion.ejecutarValorPadre("ide_ynodet=ide_ynodet");
            //tab_detalle_autorizacion.agregarRelacion(tab_detalle_nota);
            tab_detalle_autorizacion.getColumna("ide_ynoest").setCombo(ser_notas.getEstadoNota());
            tab_detalle_autorizacion.getColumna("ide_ynoest").setLongitud(-1);
            tab_detalle_autorizacion.getColumna("ide_ynoest").setAncho(-1);
            tab_detalle_autorizacion.getColumna("num_autorizacion_ynodau").setLectura(true);
            tab_detalle_autorizacion.getColumna("observacion_ynodau").setLectura(true);
            tab_detalle_autorizacion.getColumna("fecha_autorizacion_ynodau").setLectura(true);
            tab_detalle_autorizacion.getColumna("nota_anterior_ynodau").setLectura(true);
            tab_detalle_autorizacion.getColumna("ide_ypedpe").setVisible(false);
            tab_detalle_autorizacion.getColumna("fecha_registro_ynodau").setVisible(false);

            tab_detalle_autorizacion.onSelect("filtroRelacion");
            tab_detalle_autorizacion.dibujar();

            PanelTabla pa_detalle_autorizacion = new PanelTabla();
            pa_detalle_autorizacion.setId("pa_detalle_autorizacion"); // nombre de i
            pa_detalle_autorizacion.setPanelTabla(tab_detalle_autorizacion);

            tab_detalle_nota.setId("tab_detalle_nota");
            tab_detalle_nota.setTabla("yavirac_nota_detalle_nota", "ide_ynodet", 2);
            //tab_detalle_nota.agregarRelacion(tab_detalle_autorizacion);
            tab_detalle_nota.setCondicionBuscar("ide_ynodet=" + tab_detalle_autorizacion.getValor("ide_ynodet"));
            tab_detalle_nota.setHeader("DETALLE NOTA");
            tab_detalle_nota.getColumna("ide_ynodet").setNombreVisual("CODIGO");
            tab_detalle_nota.getColumna("ide_yaldap").setNombreVisual("ALUMNO/A");
            tab_detalle_nota.getColumna("nota_ynodet").setNombreVisual("NOTA");
            tab_detalle_nota.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
            tab_detalle_nota.getColumna("ide_yaldap").setAutoCompletar();
            tab_detalle_nota.getColumna("ide_yaldap").setLectura(true);
            tab_detalle_nota.getColumna("nota_ynodet").setLectura(true);
            tab_detalle_nota.getColumna("recuperacion_ynodet").setLectura(true);
            tab_detalle_nota.getColumna("recuperacion_ynodet").setVisible(false);
            tab_detalle_nota.getColumna("recuperacion_ynodet").setValorDefecto("false");
            tab_detalle_nota.getColumna("bloqueo_ynodet").setVisible(false);
            tab_detalle_nota.dibujar();
            tab_detalle_nota.setRows(35);
            PanelTabla pa_detalle_nota = new PanelTabla();
            pa_detalle_nota.setId("pa_detalle_nota"); // nombre de i
            pa_detalle_nota.setPanelTabla(tab_detalle_nota);

            tab_cabecera_nota.setId("tab_cabecera_nota");  // todo objeto instanciado poner id 
            tab_cabecera_nota.setTabla("yavirac_nota_cabecera_nota", "ide_ynocan", 1);    // nom bdd
            tab_cabecera_nota.setHeader("ACTIVIDADES");
            tab_cabecera_nota.setCondicionBuscar("ide_ynocan=" + tab_detalle_nota.getValor("ide_ynocan"));
            //tab_cabecera_nota.setCondicion("ide_ynocan=-1");
            tab_cabecera_nota.getColumna("ide_ynocan").setNombreVisual("CODIGO");
            tab_cabecera_nota.getColumna("ide_ynopae").setCombo(ser_notas.getPeriodoActividadEvaluacion("0", "0", "true,false", "0"));
            tab_cabecera_nota.getColumna("ide_ystpea").setVisible(false);
            tab_cabecera_nota.getColumna("ide_ystmen").setVisible(false);
            tab_cabecera_nota.getColumna("ide_ystnie").setVisible(false);
            tab_cabecera_nota.getColumna("ide_ypedpe").setVisible(false);
            tab_cabecera_nota.getColumna("ide_yhogra").setVisible(false);
            tab_cabecera_nota.getColumna("ide_ystjor").setVisible(false);
            tab_cabecera_nota.getColumna("ide_ystmal").setVisible(false);
            tab_cabecera_nota.getColumna("ide_ynopae").setAutoCompletar();
            tab_cabecera_nota.getColumna("ide_ynopae").setLectura(true);
            tab_cabecera_nota.getColumna("ide_ynopae").setNombreVisual("ACTIVIDAD EVALUACIÓN");
            tab_cabecera_nota.getColumna("detalle_ynocan").setLectura(true);
            tab_cabecera_nota.getColumna("detalle_ynocan").setNombreVisual("DETALLE");
            tab_cabecera_nota.getColumna("fecha_calificacion_ynocan").setLectura(true);
            tab_cabecera_nota.getColumna("fecha_calificacion_ynocan").setNombreVisual("FECHA CALIFICACIÓN");
            tab_cabecera_nota.getColumna("ide_ynopae").setFiltro(true);
            tab_cabecera_nota.getColumna("detalle_ynocan").setFiltro(true);
            tab_cabecera_nota.setRows(5);
            tab_cabecera_nota.dibujar();

            PanelTabla pa_cabecera_nota = new PanelTabla();
            pa_cabecera_nota.setId("pa_cabecera_nota"); // nombre de i
            pa_cabecera_nota.setPanelTabla(tab_cabecera_nota);

            Division div_nota = new Division();
            div_nota.setId("div_nota");
            div_nota.dividir3(pa_detalle_autorizacion, pa_detalle_nota, pa_cabecera_nota, "50%", "20%", "H");
            ///div_nota.dividir2(pa_detalle_autorizacion, pa_detalle_nota, "50%", "H");
            agregarComponente(div_nota);

            Boton bot_aprobar = new Boton();
            bot_aprobar.setValue("Aprobar");
            bot_aprobar.setIcon("ui-icon-note");
            bot_aprobar.setMetodo("aprobarCambios");
            bar_botones.agregarBoton(bot_aprobar);

            //CONFIRMAR
            con_confirma.setId("con_confirma");
            con_confirma.setMessage("Está seguro que desea guardar los cambios y enviar a recalcular las notas del estuadiante\n" + alumno);
            con_confirma.setTitle("GUARDAR MODIFICACIONES");
            con_confirma.getBot_aceptar().setValue("Si");
            con_confirma.getBot_cancelar().setValue("No");
            agregarComponente(con_confirma);

        } else {
            utilitario.agregarNotificacionInfo("Mensaje,", "EL usuario ingresado no registra permisos para el control de Asistencia. Consulte con el Administrador");
        }
    }
    String alumno = "";

    public void aprobarCambios() {
        TablaGenerica tab_alumno = utilitario.consultar(ser_alumno.getDatosAlumnosCodigo(tab_detalle_nota.getValor("ide_yaldap").toString()));
        alumno = tab_alumno.getValor("apellido_yaldap") + " " + tab_alumno.getValor("nombre_yaldap");
        System.out.println("ALUMNO: ====>>" + alumno);
        con_confirma.dibujar();
        utilitario.addUpdate("alumno");
    }

    public void confirmarAprobar() {
        utilitario.agregarMensaje("BIEN", "MUCHACHO");
    }

    public void filtroRelacion(SelectEvent evt) {
        tab_detalle_autorizacion.seleccionarFila(evt);
        System.out.println("1.- " + tab_detalle_autorizacion.getValor(tab_detalle_autorizacion.getFilaActual(), "ide_ynodet"));
        System.out.println("2.- " + tab_detalle_nota.getValor(tab_detalle_nota.getFilaActual(), "ide_ynocan"));
        //tab_detalle_nota.setCondicion("ide_ynodet=" + tab_detalle_autorizacion.getValor(tab_detalle_autorizacion.getFilaActual(), "ide_ynodet"));
        //tab_cabecera_nota.setCondicion("ide_ynocan=" + tab_detalle_nota.getValor(tab_detalle_nota.getFilaActual(), "ide_ynocan"));
        //utilitario.addUpdate("tab_detalle_nota,tab_cabecera_nota");
        utilitario.addUpdateTabla(tab_detalle_nota, "ide_ynodet", "");
        utilitario.addUpdateTabla(tab_cabecera_nota, "ide_ynocan", "");
    }

    /*public void calcularNota() {
        TablaGenerica tab_autorizacion = utilitario.consultar(ser_notas.getConsultaTablaAutorizacion(tab_detalle_autorizacion.getValor(tab_detalle_autorizacion.getFilaActual(), "ide_ynodau")));
        TablaGenerica tab_malla = utilitario.consultar(ser_notas.getConsultaMallaDocente(tab_autorizacion.getValor("ystpea"), tab_autorizacion.getValor("ystmal"), tab_autorizacion.getValor("ypedpe"), tab_autorizacion.getValor("yhogra"), tab_autorizacion.getValor("ystjor")));
        TablaGenerica tab_consulta = utilitario.consultar(ser_notas.getPersonMallaDocente(tab_malla.getValor("ide_ypemad")));
        TablaGenerica tab_peso = utilitario.consultar(ser_notas.getPesoNota("3", "true,false", tab_consulta.getValor("ide_ysttfe")));
        System.out.println(" <<<<<<<<<<<<<<<<< TAB PESO >>>>>>>>>>>>>>>>>");
        tab_peso.imprimirSql();
        if (tab_peso.getTotalFilas() > 0) {
            for (int i = 0; i < tab_peso.getTotalFilas(); i++) {
                TablaGenerica tab_detalle = utilitario.consultar(ser_notas.getPesoDetalleNota(tab_peso.getValor(i, "ide_ynopen")));

                utilitario.getConexion().ejecutarSql(ser_notas.getActualizarTablaResumen(tab_autorizacion.getValor("ystpea"), tab_consulta.getValor("ide_ystmen"), tab_consulta.getValor("ide_ystnie"), tab_consulta.getValor("ide_ypedpe"), tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_consulta.getValor("ide_ystmal"), tab_autorizacion.getValor("ide_yaldap"), tab_peso.getValor(i, "ide_ynopen")));
                utilitario.getConexion().ejecutarSql(ser_notas.getActualizarTablaResumenNota(tab_malla.getValor("ide_ypemad"), tab_peso.getValor(i, "ide_ynopen")));
                for (int k = 0; k < tab_detalle.getTotalFilas(); k++) {
                    TablaGenerica tab_resumen = utilitario.consultar(ser_notas.getImportarSumaNotas("2", "1", tab_autorizacion.getValor("ystpea"), tab_consulta.getValor("ide_ypedpe"), tab_consulta.getValor("ide_ystmal"),
                            tab_consulta.getValor("ide_ystnie"), tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_peso.getValor(i, "ide_ynotie"), tab_autorizacion.getValor("ide_yaldap"), tab_consulta.getValor("ide_ysttfe"), tab_detalle.getValor(k, "ide_ynoace")));
                    if (tab_resumen.getTotalFilas() > 0) {
                        TablaGenerica tab_porciento = utilitario.consultar(ser_notas.getPorcientoParametroEvaluacion(tab_resumen.getValor("notas"), tab_consulta.getValor("ide_ypedpe"), tab_consulta.getValor("ide_ystmal"), tab_consulta.getValor("ide_ystnie"), tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_resumen.getValor("ide_ynoace")));
                        //INSERTAR TABLA RESUMEN
                        TablaGenerica tab_mximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_nota_resumen", "ide_ynores"));
                        utilitario.getConexion().ejecutarSql(ser_notas.getInsertarTabResumen(tab_mximo.getValor("maximo"), tab_autorizacion.getValor("ystpea"), tab_consulta.getValor("ide_ystmen"), tab_consulta.getValor("ide_ystnie"), tab_consulta.getValor("ide_ypedpe"),
                                tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_resumen.getValor("ide_ynopae"), tab_consulta.getValor("ide_ystmal"), tab_autorizacion.getValor("ide_yaldap"), tab_peso.getValor(i, "ide_ynopen"),
                                tab_resumen.getValor("notas"), tab_porciento.getValor("porcentaje"), tab_resumen.getValor("recuperacion_ynodet")));
                    }
                }

            }
            //notaTotalActividades();
            //notaTotalParcial();
            //notaFinal();
            //utilitario.addUpdate("tab_resumen_nota");
            //tab_resumen_nota.ejecutarValorForanea(tab_docente_alumno.getValorSeleccionado());
        } else {
            utilitario.agregarNotificacionInfo("ADVERTENCIA,", "No puede calcular las notas totales pongase en contacto con el administrador");
        }

    }

    public void notaTotalActividades() {

        TablaGenerica tab_autorizacion = utilitario.consultar(ser_notas.getConsultaTablaAutorizacion(tab_detalle_autorizacion.getValor(tab_detalle_autorizacion.getFilaActual(), "ide_ynodau")));
        TablaGenerica tab_malla = utilitario.consultar(ser_notas.getConsultaMallaDocente(tab_autorizacion.getValor("ystpea"), tab_autorizacion.getValor("ystmal"), tab_autorizacion.getValor("ypedpe"), tab_autorizacion.getValor("yhogra"), tab_autorizacion.getValor("ystjor")));
        TablaGenerica tab_consulta = utilitario.consultar(ser_notas.getPersonMallaDocente(tab_malla.getValor("ide_ypemad")));
        TablaGenerica tab_peso = utilitario.consultar(ser_notas.getPesoNota("3", "true", tab_consulta.getValor("ide_ysttfe")));

        for (int i = 0; i < tab_peso.getTotalFilas(); i++) {
            TablaGenerica tab_detalle = utilitario.consultar(ser_notas.getPesoDetalleNota(tab_peso.getValor(i, "ide_ynopen")));
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
            utilitario.getConexion().ejecutarSql(ser_notas.getInsertarTabAlumnoResumen(tab_codigo.getValor("maximo"), tab_peso.getValor(i, "ide_ynopen"), tab_malla.getValor("ide_ypemad"), tab_total.getValor("notatotal"), tab_peso.getValor(i, "peso_ynopen")));

        }

    }

    public void notaTotalParcial() {
        TablaGenerica tab_autorizacion = utilitario.consultar(ser_notas.getConsultaTablaAutorizacion(tab_detalle_autorizacion.getValor(tab_detalle_autorizacion.getFilaActual(), "ide_ynodau")));
        TablaGenerica tab_malla = utilitario.consultar(ser_notas.getConsultaMallaDocente(tab_autorizacion.getValor("ystpea"), tab_autorizacion.getValor("ystmal"), tab_autorizacion.getValor("ypedpe"), tab_autorizacion.getValor("yhogra"), tab_autorizacion.getValor("ystjor")));
        TablaGenerica tab_consulta = utilitario.consultar(ser_notas.getPersonMallaDocente(tab_malla.getValor("ide_ypemad")));
        TablaGenerica tab_peso = utilitario.consultar(ser_notas.getPadreSegundoNivel("2", "true"));
        for (int i = 0; i < tab_peso.getTotalFilas(); i++) {

            utilitario.getConexion().ejecutarSql(ser_notas.getActualizarTablaResumenNota(tab_docente_alumno.getValor(j, "ide_ypemda"), tab_peso.getValor(i, "ide_ynopen")));
            TablaGenerica tab_segundoNivel = utilitario.consultar(ser_notas.getConsultarNotaTotalSegundoNivel(tab_peso.getValor(i, "ide_ynopen"), com_periodo_academico.getValue().toString(), tab_peso.getValor(i, "ide_ynotie"), tab_docente_alumno.getValor(j, "ide_yaldap"), tab_consulta.getValor("ide_ystmal"), tab_consulta.getValor("ide_ypedpe"), tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_consulta.getValor("ide_ystmen"), tab_consulta.getValor("ide_ystnie")));
            TablaGenerica tab_codigo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_nota_alumno_resumen", "ide_ynoalr"));
            utilitario.getConexion().ejecutarSql(ser_notas.getInsertarTabAlumnoResumen(tab_codigo.getValor("maximo"), tab_peso.getValor(i, "ide_ynopen"), tab_docente_alumno.getValor(j, "ide_ypemda"), tab_segundoNivel.getValor("total"), tab_peso.getValor(i, "peso_ynopen")));

        }
    }

    public void notaFinal() {
        TablaGenerica tab_autorizacion = utilitario.consultar(ser_notas.getConsultaTablaAutorizacion(tab_detalle_autorizacion.getValor(tab_detalle_autorizacion.getFilaActual(), "ide_ynodau")));
        TablaGenerica tab_malla = utilitario.consultar(ser_notas.getConsultaMallaDocente(tab_autorizacion.getValor("ystpea"), tab_autorizacion.getValor("ystmal"), tab_autorizacion.getValor("ypedpe"), tab_autorizacion.getValor("yhogra"), tab_autorizacion.getValor("ystjor")));
        TablaGenerica tab_consulta = utilitario.consultar(ser_notas.getPersonMallaDocente(tab_malla.getValor("ide_ypemad")));
        TablaGenerica tab_peso = utilitario.consultar(ser_notas.getPadreSegundoNivel("1", "true"));
        for (int i = 0; i < tab_peso.getTotalFilas(); i++) {

            utilitario.getConexion().ejecutarSql(ser_notas.getActualizarTablaResumenNota(tab_docente_alumno.getValor(j, "ide_ypemda"), tab_peso.getValor(i, "ide_ynopen")));
            TablaGenerica tab_tercerNivel = utilitario.consultar(ser_notas.getConsultarNotaTotalTercerNivel(tab_peso.getValor(i, "ide_ynopen"), com_periodo_academico.getValue().toString(), tab_docente_alumno.getValor(j, "ide_yaldap"), tab_consulta.getValor("ide_ystmal"), tab_consulta.getValor("ide_ypedpe"), tab_consulta.getValor("ide_yhogra"), tab_consulta.getValor("ide_ystjor"), tab_consulta.getValor("ide_ystmen"), tab_consulta.getValor("ide_ystnie")));
            TablaGenerica tab_codigo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_nota_alumno_resumen", "ide_ynoalr"));
            utilitario.getConexion().ejecutarSql(ser_notas.getInsertarTabAlumnoResumen(tab_codigo.getValor("maximo"), tab_peso.getValor(i, "ide_ynopen"), tab_docente_alumno.getValor(j, "ide_ypemda"), tab_tercerNivel.getValor("total"), tab_peso.getValor(i, "peso_ynopen")));

        }
        //tab_resumen_nota.guardar();
        //guardarPantalla();
        utilitario.agregarMensajeInfo("Mensaje", "Se calculo correctamente las notas");
    }*/

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

    public Tabla getTab_detalle_autorizacion() {
        return tab_detalle_autorizacion;
    }

    public void setTab_detalle_autorizacion(Tabla tab_detalle_autorizacion) {
        this.tab_detalle_autorizacion = tab_detalle_autorizacion;
    }

    public Tabla getTab_detalle_nota() {
        return tab_detalle_nota;
    }

    public void setTab_detalle_nota(Tabla tab_detalle_nota) {
        this.tab_detalle_nota = tab_detalle_nota;
    }

    public Tabla getTab_cabecera_nota() {
        return tab_cabecera_nota;
    }

    public void setTab_cabecera_nota(Tabla tab_cabecera_nota) {
        this.tab_cabecera_nota = tab_cabecera_nota;
    }

    public Confirmar getCon_confirma() {
        return con_confirma;
    }

    public void setCon_confirma(Confirmar con_confirma) {
        this.con_confirma = con_confirma;
    }

}
