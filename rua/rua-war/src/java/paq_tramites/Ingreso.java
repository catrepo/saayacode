/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_tramites;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Barra;
import framework.componentes.Boton;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;
import paq_tramites.ejb.ServicioTramite;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Personal
 */
public class Ingreso extends Pantalla{
    
    private Tabla tab_ingreso = new Tabla();
    private Tabla tab_anexo = new Tabla();
    private Tabla tab_asignacion = new Tabla();
    private SeleccionTabla sel_registra_alumno = new SeleccionTabla();

    @EJB
    private final ServicioTramite ser_tramite = (ServicioTramite) utilitario.instanciarEJB(ServicioTramite.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);

    
    public Ingreso (){
        if (tienePerfilSecretaria()) {
            
            //bar_botones.getBot_insertar().setRendered(false);
            //BOTON REGISTRO DE ALUMNOS
            Boton bot_registroAlumno = new Boton();
            bot_registroAlumno.setValue("Listado Alumnos");
            bot_registroAlumno.setIcon("ui-icon-note");
            bot_registroAlumno.setMetodo("selregistraAlumno");
            bar_botones.agregarBoton(bot_registroAlumno);     
            //boton para asiganar trammites
            Boton bot_asignar= new Boton();
             bot_asignar.setValue("Asignar Tramite");
            bot_asignar.setIcon("ui-icon-note");
            bot_asignar.setMetodo("selregistraAlumno");
            bar_botones.agregarBoton(bot_asignar);
            
        tab_ingreso.setId("tab_ingreso");
        tab_ingreso.setTabla("yavirac_tra_ingreso", "ide_ytring", 1);
        tab_ingreso.getColumna("ide_ytring").setVisible(false);
        tab_ingreso.getColumna("numero_sec_ytring").setOrden(0);
        tab_ingreso.getColumna("numero_sec_ytring").setNombreVisual("NRO DE TRÁMITE");
        tab_ingreso.getColumna("ide_ytrtid").setNombreVisual("TIPO DE DOCUMENTO");
        tab_ingreso.getColumna("ide_ytrtid").setOrden(12);
        tab_ingreso.getColumna("asunto_ytring").setNombreVisual("ASUNTO");
        tab_ingreso.getColumna("asunto_ytring").setOrden(15);
        tab_ingreso.getColumna("fecha_entrega_ytring").setNombreVisual("FECHA DE ENTREGA");
        tab_ingreso.getColumna("fecha_entrega_ytring").setOrden(2);
        tab_ingreso.getColumna("cedula_ytring").setNombreVisual("CÉDULA DE IDENTIDAD");
        tab_ingreso.getColumna("cedula_ytring").setOrden(5);
        tab_ingreso.getColumna("fecha_conclusion_ytring").setNombreVisual("FECHA DE CONCLUSIÓN");
        tab_ingreso.getColumna("fecha_conclusion_ytring").setOrden(17);
        tab_ingreso.getColumna("ide_ytrdoc").setNombreVisual("NRO DE DOCUMENTO");
        tab_ingreso.getColumna("ide_ytrdoc").setOrden(7);
        tab_ingreso.getColumna("fecha_documento_ytring").setNombreVisual("FECHA DE DOCUMENTO");
        tab_ingreso.getColumna("fecha_documento_ytring").setOrden(8);
        tab_ingreso.getColumna("respuesta_ytring").setNombreVisual("RESPUESTA");
        tab_ingreso.getColumna("respuesta_ytring").setOrden(18);
        tab_ingreso.getColumna("num_hojas_ytring").setNombreVisual("NRO HOJAS");
        tab_ingreso.getColumna("num_hojas_ytring").setOrden(20);
        tab_ingreso.getColumna("hora_ytring").setNombreVisual("HORA");
        tab_ingreso.getColumna("hora_ytring").setOrden(19);
        tab_ingreso.getColumna("observaciones_ytring").setNombreVisual("OBSERVACIONES");
        tab_ingreso.getColumna("observaciones_ytring").setOrden(16);
        tab_ingreso.getColumna("ide_ytrtie").setNombreVisual("TIPO DE ENTIDAD");
        tab_ingreso.getColumna("ide_ytrtie").setOrden(1);
        tab_ingreso.getColumna("estado_ytring").setNombreVisual("ESTADO");
        tab_ingreso.getColumna("estado_ytring").setOrden(6);
        tab_ingreso.getColumna("procedencia_ytring").setNombreVisual("PROCEDENCIA");
        tab_ingreso.getColumna("procedencia_ytring").setOrden(4);
        tab_ingreso.getColumna("nombre_ytring").setNombreVisual("NOMBRE");
        tab_ingreso.getColumna("nombre_ytring").setOrden(10);
        tab_ingreso.getColumna("responsable_ytring").setNombreVisual("RESPONSABLE");
        tab_ingreso.getColumna("responsable_ytring").setOrden(13);
        tab_ingreso.getColumna("nro_oficio_ytring").setNombreVisual("NRO DE OFICIO");
        tab_ingreso.getColumna("nro_oficio_ytring").setOrden(7);
        tab_ingreso.getColumna("ide_yaldap").setNombreVisual("ESTUDIANTE");
        tab_ingreso.getColumna("ide_yaldap").setOrden(3);
        tab_ingreso.getColumna("ide_ypedpe").setOrden(11);
        //tab_ingreso.getColumna("ide_ypede").setOrden(14);
        
        tab_ingreso.getColumna("ide_ytrtie").setCombo(ser_tramite.getSqlTipoEntidad());
        tab_ingreso.getColumna("ide_ytrtid").setCombo(ser_tramite.getSqlTipoDocumento());
        tab_ingreso.getColumna("ide_ytrtid").setMetodoChange("textoBase");
        tab_ingreso.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_ingreso.getColumna("ide_ypedpe").setAutoCompletar();
         tab_ingreso.getColumna("ide_ypedpe").setLectura(true);
         tab_ingreso.getColumna("ide_ytrtie").setLectura(true);
         tab_ingreso.getColumna("fecha_entrega_ytring").setValorDefecto(utilitario.getFechaActual());
         tab_ingreso.getColumna("hora_ytring").setValorDefecto(utilitario.getHoraActual());
         tab_ingreso.getColumna("fecha_entrega_ytring").setEtiqueta();
                 tab_ingreso.getColumna("hora_ytring").setEtiqueta();
        tab_ingreso.getColumna("estado_ytring").setEtiqueta();
        tab_ingreso.getColumna("estado_ytring").setEstilo("font-size:15px;font-weight: bold;color:green");
        tab_ingreso.getColumna("fecha_conclusion_ytring").setEtiqueta();
        tab_ingreso.getColumna("fecha_conclusion_ytring").setEstilo("font-size:15px;font-weight: bold;text-decoration: underline;color:blue");
        tab_ingreso.getColumna("fecha_entrega_ytring").setEstilo("font-size:15px;font-weight: bold;text-decoration: underline;color:blue");
        tab_ingreso.getColumna("hora_ytring").setEstilo("font-size:15px;font-weight: bold;text-decoration: underline;color:blue");
                 
        tab_ingreso.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
        tab_ingreso.getColumna("ide_yaldap").setAutoCompletar();
        tab_ingreso.getColumna("ide_yaldap").setLectura(true);
        tab_ingreso.getColumna("numero_sec_ytring").setEtiqueta();
        tab_ingreso.getColumna("numero_sec_ytring").setEstilo("font-size:15px;font-weight: bold;text-decoration: underline;color:red");
        //tab_ingreso.getColumna("ide_ypedpe").setVisible(false);  
        tab_ingreso.getColumna("TIPO_TRAMITE_YTRING").setValorDefecto("1");
        tab_ingreso.getColumna("TIPO_TRAMITE_YTRING").setVisible(false);
        tab_ingreso.setHeader("REGISTRO DOCUMENTAL INTERNO");
        tab_ingreso.agregarRelacion(tab_asignacion);
        tab_ingreso.agregarRelacion(tab_anexo);
        tab_ingreso.setTipoFormulario(true);
        tab_ingreso.getGrid().setColumns(6);
        tab_ingreso.dibujar();
        
        PanelTabla pat_ingreso = new PanelTabla();
        pat_ingreso.setId("pat_ingreso");
        pat_ingreso.setPanelTabla(tab_ingreso);
        
        tab_asignacion.setId("tab_asignacion");
        tab_asignacion.setIdCompleto("tab_tabulador:tab_asignacion");
        tab_asignacion.setTabla("yavirac_tra_asignacion", "ide_ytrasi", 2);
        tab_asignacion.dibujar();
        
        PanelTabla pat_asignacion = new PanelTabla();
        pat_asignacion.setId("pat_asignacion");
        pat_asignacion.setPanelTabla(tab_asignacion);        
        
        tab_anexo.setId("tab_anexo");
        tab_anexo.setIdCompleto("tab_tabulador:tab_anexo");
        tab_anexo.setTabla("yavirac_tra_anexo", "ide_ytrane", 2);
        tab_anexo.dibujar();
        
        PanelTabla pat_anexo = new PanelTabla();
        pat_anexo.setId("pat_anexo");
        pat_anexo.setPanelTabla(tab_anexo);   

        
        /// tabulado
        Tabulador tab_tabulador=new Tabulador();
	tab_tabulador.setId("tab_tabulador");

	tab_tabulador.agregarTab("ASIGNACION TRAMITE", pat_asignacion);
	tab_tabulador.agregarTab("ANEXOS", pat_anexo);
        
        Division div_ingreso = new Division();
        div_ingreso.setId("div_ingreso");
        div_ingreso.dividir2(pat_ingreso, tab_tabulador, "50%", "H");
        
        
        agregarComponente(div_ingreso);   
        
        //PANTALLA INGRESA ALUMNO
            sel_registra_alumno.setId("sel_registra_alumno");
            sel_registra_alumno.setTitle("SELECCIONE EL ALUMNO");
            sel_registra_alumno.getBot_aceptar().setMetodo("registraAlumno");
            sel_registra_alumno.setSeleccionTabla(ser_alumno.getDatosAlumnos("null"), "ide_yaldap");
            sel_registra_alumno.getTab_seleccion().getColumna("apellido_yaldap").setFiltro(true);
            sel_registra_alumno.getTab_seleccion().getColumna("nombre_yaldap").setFiltro(true);
            sel_registra_alumno.setRadio();
            agregarComponente(sel_registra_alumno);
            
        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el control de Asistencia. Consulte con el Administrador");
        }
}
public void textoBase(AjaxBehaviorEvent evt){
    TablaGenerica tab_texto_base = utilitario.consultar(ser_tramite.getSqlTipoDocumentoPara(tab_ingreso.getValor("ide_ytrtid")));
    tab_ingreso.setValor("asunto_ytring", tab_texto_base.getValor("texto_base_ytrtid"));
    utilitario.addUpdateTabla(tab_ingreso, "asunto_ytring", "");
}
    String docente = "";
    String documento="";
    String ide_docente="";
        private boolean tienePerfilSecretaria() {
        List sql = utilitario.getConexion().consultar(ser_estructura_organizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA")," and not ide_ypedpe is null"));

        if (!sql.isEmpty()) {
            Object[] fila = (Object[]) sql.get(0);
                    List sql2 = utilitario.getConexion().consultar(ser_personal.getDatoPersonalCodigo(fila[3].toString()));
            if (!sql2.isEmpty()) {
                Object[] fila2 = (Object[]) sql2.get(0);
                docente = fila2[1].toString()+" "+fila2[2].toString();
                documento = fila2[3].toString();
                ide_docente=fila2[0].toString();
                    return true;
            }  
            else{
            return false;
            }
        } else {
            return false;
        }
    }
    public void registraAlumno() {
        String str_seleccionado = sel_registra_alumno.getValorSeleccionado();
        if (str_seleccionado != null) {
            //Inserto los cleintes seleccionados en la tabla  
            if (tab_ingreso.isFilaInsertada() == false) {
                //Controla que si ya esta insertada no vuelva a insertar
                tab_ingreso.insertar();
            }
            TablaGenerica tab_secuencial= utilitario.consultar(ser_tramite.getSqlSecuencial(utilitario.getVariable("p_secuencial_interno")));
            TablaGenerica tab_procedencia= utilitario.consultar(ser_alumno.getDatosAlumnosCodigo(str_seleccionado));
            tab_ingreso.setValor("ide_ypedpe", ide_docente);
            tab_ingreso.setValor("procedencia_ytring", tab_procedencia.getValor("apellido_yaldap")+" "+tab_procedencia.getValor("nombre_yaldap"));
            tab_ingreso.setValor("cedula_ytring", tab_procedencia.getValor("doc_identidad_yaldap"));
            tab_ingreso.setValor("ide_yaldap", str_seleccionado);
            tab_ingreso.setValor("estado_ytring", "REGISTRADO");
            tab_ingreso.setValor("ide_ytrtie",utilitario.getVariable("p_entidad_yavirac"));
            tab_ingreso.setValor("numero_sec_ytring", tab_secuencial.getValor("detalle_ytrsec")+" "+tab_secuencial.getValor("secuencial"));
            tab_ingreso.modificar(tab_ingreso.getFilaActual());//para que haga el update

            sel_registra_alumno.cerrar();
            utilitario.addUpdate("tab_pre_inscrip");
        } else {
            utilitario.agregarMensajeInfo("Debe seleccionar al menos un registro", "");
        }
    }
    public void selregistraAlumno() {

            sel_registra_alumno.getTab_seleccion().setSql(ser_alumno.getDatosAlumnos("true"));
            sel_registra_alumno.getTab_seleccion().ejecutarSql();
            sel_registra_alumno.dibujar();
        

    }
    @Override
    public void insertar() {
        if(tab_ingreso.isFocus()){
            utilitario.agregarMensajeInfo("Seleccione el Almuno", "Para registrar un tramite, seleccione el Alumno");
            /*
         tab_ingreso.insertar();
         tab_ingreso.insertar();
         tab_ingreso.setValor("ide_ypedpe", ide_docente);
            */
        }
        else if(tab_asignacion.isFocus()){
            tab_asignacion.insertar();
        }
        else if(tab_anexo.isFocus()){
            tab_anexo.insertar();
        }
    }

    @Override
    public void guardar() {
        if(tab_ingreso.guardar()){
            if(tab_asignacion.guardar()){
                if(tab_anexo.guardar());
            }
        }  
            guardarPantalla();
        
    }

    @Override
    public void eliminar() {
        utilitario.getTablaisFocus().eliminar();
    }

    public Tabla getTab_ingreso() {
        return tab_ingreso;
    }

    public void setTab_ingreso(Tabla tab_ingreso) {
        this.tab_ingreso = tab_ingreso;
    }

    public Utilitario getUtilitario() {
        return utilitario;
    }

    public void setUtilitario(Utilitario utilitario) {
        this.utilitario = utilitario;
    }

    public Barra getBar_botones() {
        return bar_botones;
    }

    public void setBar_botones(Barra bar_botones) {
        this.bar_botones = bar_botones;
    }

    public Grupo getGru_pantalla() {
        return gru_pantalla;
    }

    public void setGru_pantalla(Grupo gru_pantalla) {
        this.gru_pantalla = gru_pantalla;
    }

    public Tabla getTab_anexo() {
        return tab_anexo;
    }

    public void setTab_anexo(Tabla tab_anexo) {
        this.tab_anexo = tab_anexo;
    }

    public Tabla getTab_asignacion() {
        return tab_asignacion;
    }

    public void setTab_asignacion(Tabla tab_asignacion) {
        this.tab_asignacion = tab_asignacion;
    }

    public SeleccionTabla getSel_registra_alumno() {
        return sel_registra_alumno;
    }

    public void setSel_registra_alumno(SeleccionTabla sel_registra_alumno) {
        this.sel_registra_alumno = sel_registra_alumno;
    }
    
}
