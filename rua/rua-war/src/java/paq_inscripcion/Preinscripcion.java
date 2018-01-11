/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_inscripcion;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Confirmar;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Grid;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import java.util.List;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author USER
 */
public class Preinscripcion extends Pantalla {

    private Tabla tab_pre_inscrip = new Tabla();
    private Tabla tab_requ_entregado = new Tabla();
    private Tabla tab_alumno = new Tabla();
    private Dialogo crear_alumno = new Dialogo();
    private Combo com_periodo_academico = new Combo();
    private SeleccionTabla sel_registra_alumno = new SeleccionTabla();
    private SeleccionTabla sel_actualiza_alumno = new SeleccionTabla();
    private Confirmar con_guardar_alumno=new Confirmar();
    

    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_EstructuraOrganizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    public static String par_modulo_inscripcion;

    public Preinscripcion() {
        if (tienePerfiInscripcion()) {
            par_modulo_inscripcion = utilitario.getVariable("p_documento_inscripcion");

            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_EstructuraOrganizacional.getPeriodoAcademico("true"));
            agregarComponente(com_periodo_academico);
            bar_botones.agregarComponente(com_periodo_academico);
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
            //

            tab_pre_inscrip.setId("tab_pre_inscrip");
            tab_pre_inscrip.setTabla("yavirac_ins_pre_inscripcion", "ide_yinpin", 1);
            tab_pre_inscrip.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
            tab_pre_inscrip.getColumna("ide_yaldap").setAutoCompletar();
            tab_pre_inscrip.getColumna("ide_yaldap").setLectura(true);
            tab_pre_inscrip.getColumna("ide_ystpea").setCombo(ser_EstructuraOrganizacional.getPeriodoAcademico("true,false"));
            tab_pre_inscrip.getColumna("ide_ystpea").setVisible(false);
            tab_pre_inscrip.setCondicion("ide_ystpea=-1");
            tab_pre_inscrip.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
            tab_pre_inscrip.getColumna("ide_ypedpe").setAutoCompletar();
            tab_pre_inscrip.getColumna("ide_ypedpe").setLectura(true);
            tab_pre_inscrip.getColumna("fecha_incripcion_yinpin").setValorDefecto(utilitario.getFechaActual());
            tab_pre_inscrip.getColumna("ide_ystmen").setCombo(ser_EstructuraOrganizacional.getMension());
            tab_pre_inscrip.getColumna("ide_ystmen").setLongitud(50);
            tab_pre_inscrip.getColumna("ide_ystmen").setRequerida(true);
            tab_pre_inscrip.getColumna("recibido_yinpin").setValorDefecto("false");
            tab_pre_inscrip.getColumna("recibido_yinpin").setLectura(true);

            tab_pre_inscrip.agregarRelacion(tab_requ_entregado);
            tab_pre_inscrip.setTipoFormulario(true);
            tab_pre_inscrip.getGrid().setColumns(4);
            tab_pre_inscrip.getColumna("ide_yinpin").setNombreVisual("CODIGO");
            tab_pre_inscrip.getColumna("ide_ypedpe").setNombreVisual("DATO PERSONAL");
            tab_pre_inscrip.getColumna("ide_yaldap").setNombreVisual("DATO ALUMNO");
            tab_pre_inscrip.getColumna("ide_ystmen").setNombreVisual("MENSION");
            tab_pre_inscrip.getColumna("docum_senecyd_yinpin").setNombreVisual("DOCUMENTO SENESCYT");
            tab_pre_inscrip.getColumna("fecha_incripcion_yinpin").setNombreVisual("FECHA INSCRIPCION");
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
            div_pre_inscrip.dividir2(pat_pre_inscrip, pat_requ_entregado, "50%", "H");

            agregarComponente(div_pre_inscrip);

            //BOTON AGREGAR ALUMNO
            Boton bot_agregarAlumno = new Boton();
            bot_agregarAlumno.setValue("Crear Alumno");
            bot_agregarAlumno.setIcon("ui-icon-person");
            bot_agregarAlumno.setMetodo("crearAlumno");
            bar_botones.agregarBoton(bot_agregarAlumno);
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

            //BOTON REGISTRO DE ALUMNOS
            Boton bot_registroAlumno = new Boton();
            bot_registroAlumno.setValue("Listado Alumnos");
            bot_registroAlumno.setIcon("ui-icon-note");
            bot_registroAlumno.setMetodo("selregistraAlumno");
            bar_botones.agregarBoton(bot_registroAlumno);


            //BOTON ACTUALIZAR DE ALUMNOS
            Boton bot_actualizaAlumno = new Boton();
            bot_actualizaAlumno.setValue("Actualizar Alumno");
            bot_actualizaAlumno.setIcon("ui-icon-refresh");
            bot_actualizaAlumno.setMetodo("selactualizaAlumno");
            bar_botones.agregarBoton(bot_actualizaAlumno);

            //BOTON RECEPCION DE DOCUMENTOS
            Boton bot_recibe_documento = new Boton();
            bot_recibe_documento.setValue("Recibir Documentos");
            bot_recibe_documento.setIcon("ui-icon-clipboard");
            bot_recibe_documento.setMetodo("recibeDocumento");
            bar_botones.agregarBoton(bot_recibe_documento);            
            
            //PANTALLA INGRESA ALUMNO
            sel_registra_alumno.setId("sel_registra_alumno");
            sel_registra_alumno.setTitle("SELECCIONE EL ALUMNO");
            sel_registra_alumno.getBot_aceptar().setMetodo("registraAlumno");
            sel_registra_alumno.setSeleccionTabla(ser_alumno.getDatosAlumnos("null"), "ide_yaldap");
            sel_registra_alumno.getTab_seleccion().getColumna("apellido_yaldap").setFiltro(true);
            sel_registra_alumno.getTab_seleccion().getColumna("nombre_yaldap").setFiltro(true);
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
        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el registro de Inscricpiones. Consulte con el Administrador");
        }
    }
	public void actualizaAlumno(){
		String str_clienteActualizado=sel_actualiza_alumno.getValorSeleccionado();
		if(str_clienteActualizado!=null){
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
	public void guardarActualizarAlumno(){
		tab_pre_inscrip.guardar();
		con_guardar_alumno.cerrar();
		sel_actualiza_alumno.cerrar();
		guardarPantalla();
	}        
    public void registraAlumno(){
		String str_seleccionado=sel_registra_alumno.getValorSeleccionado();
		if(str_seleccionado!=null){
			//Inserto los cleintes seleccionados en la tabla  
			if(tab_pre_inscrip.isFilaInsertada()==false){
				//Controla que si ya esta insertada no vuelva a insertar
				tab_pre_inscrip.insertar();	
			}
                        tab_pre_inscrip.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
                        tab_pre_inscrip.setValor("ide_ypedpe", ide_docente);
			tab_pre_inscrip.setValor("ide_yaldap", str_seleccionado);				
			tab_pre_inscrip.modificar(tab_pre_inscrip.getFilaActual());//para que haga el update
	
			sel_registra_alumno.cerrar();
			utilitario.addUpdate("tab_pre_inscrip");			
		}
		else{
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
        if(tab_pre_inscrip.getValor("recibido_yinpin").equals("false")){
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
        }
        else{
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
        } 
        //else if (tab_alumno.guardar()) { //si guarda el slumno cierra el dialogo

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

       // }
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

}
