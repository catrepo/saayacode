/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.VisualizarPDF;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.SelectEvent;
import sistema.aplicacion.Pantalla;
import paq_asistencia.ejb.ServicioAsistencia;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author Janeth Pullotasig
 */
public class PermisoDias extends Pantalla {
    private Tabla tab_tipomotivo =new  Tabla();
    private AutoCompletar aut_empleado = new AutoCompletar();    
    @EJB
    private final ServicioAsistencia ser_asistencia = (ServicioAsistencia) utilitario.instanciarEJB(ServicioAsistencia.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_EstructuraOrganizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    private VisualizarPDF vipdf_comprobante = new VisualizarPDF();    
    public PermisoDias(){
        if (tienePerfilSecretaria()) {
    // boton limpiar
		Boton bot_limpiar = new Boton();
		bot_limpiar.setIcon("ui-icon-cancel");
		bot_limpiar.setMetodo("limpiar");
                
                Boton bot_aprobar= new Boton();
		bot_aprobar.setValue("Aprobar Solicitud");
		bot_aprobar.setMetodo("aprobar");
                 bot_aprobar.setIcon("ui-icon-circle-check");

                
                Boton bot_anular= new Boton();
		bot_anular.setValue("Anular Solicitud");
		bot_anular.setMetodo("anular");
                bot_anular.setIcon("ui-icon-circle-close");                
                
        // autocompletar empleado
		aut_empleado.setId("aut_empleado");
		String str_sql_emp=ser_personal.getDatopersonal("true,false");
		aut_empleado.setAutoCompletar(str_sql_emp);		
		aut_empleado.setMetodoChange("filtrarEmpleado");

		Etiqueta eti_colaborador=new Etiqueta("Colaborador/a:");
		bar_botones.agregarComponente(eti_colaborador);
		bar_botones.agregarComponente(aut_empleado);
		bar_botones.agregarBoton(bot_limpiar);
                bar_botones.agregarBoton(bot_aprobar);
                bar_botones.agregarBoton(bot_anular);
        
                
        Boton bot_imprimir = new Boton();
        bot_imprimir.setIcon("ui-icon-print");
        bot_imprimir.setValue("Imprimir solicitud");
        bot_imprimir.setMetodo("generarPDF");
        bar_botones.agregarBoton(bot_imprimir);
        
        tab_tipomotivo.setId("tab_tipomotivo");
        tab_tipomotivo.setTabla("yavirac_asis_permisos","ide_yasper",1);
        tab_tipomotivo.setHeader("PERMISOS DE COLABORADORES POR DIAS");
        tab_tipomotivo.getColumna("yav_ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tipomotivo.getColumna("yav_ide_ypedpe2").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tipomotivo.getColumna("ide_yasmpe").setCombo(ser_asistencia.getMotivoAusencia());
        tab_tipomotivo.setCondicion("ide_yasper=-1");
        
        List lista = new ArrayList();
	       Object fila1[] = {
	           "1", "APROBADO"
	       };
	       Object fila2[] = {
	           "0", "REGISTRADO"
	       };
               Object fila3[] = {
	           "2", "REPROBADO"
	       };
	       
	       lista.add(fila1);
	       lista.add(fila2); 
               lista.add(fila3); 
        tab_tipomotivo.getColumna("aprobado_repro_yasper").setCombo(lista);
        tab_tipomotivo.getColumna("aprobado_repro_yasper").setAutoCompletar();
        tab_tipomotivo.getColumna("aprobado_repro_yasper").setLectura(true);
        tab_tipomotivo.getColumna("yav_ide_ypedpe").setValorDefecto(ide_docente);
        tab_tipomotivo.getColumna("yav_ide_ypedpe").setAutoCompletar();
        tab_tipomotivo.getColumna("tipo_yasper").setValorDefecto("1");
        tab_tipomotivo.getColumna("tipo_yasper").setVisible(false);
        tab_tipomotivo.getColumna("yav_ide_ypedpe").setLectura(true);
        tab_tipomotivo.getColumna("fecha_desde_yasper").setRequerida(true);
        tab_tipomotivo.getColumna("fecha_hasta_yasper").setRequerida(true);
        tab_tipomotivo.getColumna("fecha_desde_yasper").setMetodoChange("calcularDiasPermisos");
        tab_tipomotivo.getColumna("fecha_hasta_yasper").setMetodoChange("calcularDiasPermisos");
        tab_tipomotivo.getColumna("hora_inicio_yasper").setVisible(false);
        tab_tipomotivo.getColumna("hora_fin_yasper").setVisible(false);
        tab_tipomotivo.getColumna("ide_ypedpe").setVisible(false);
        tab_tipomotivo.getColumna("total_horas_yasper").setFormatoNumero(2);
	tab_tipomotivo.getColumna("total_horas_yasper").setEtiqueta();
	tab_tipomotivo.getColumna("total_horas_yasper").alinearCentro();
	tab_tipomotivo.getColumna("num_dias_yasper").setRequerida(true);
	tab_tipomotivo.getColumna("num_dias_yasper").setEtiqueta();
	tab_tipomotivo.getColumna("num_dias_yasper").alinearCentro();  
        tab_tipomotivo.getColumna("archivo_yasper").setUpload();
         tab_tipomotivo.getColumna("total_horas_yasper").setEstilo("color: red; font-size: 18px; align: right;font-weight:bold");
         tab_tipomotivo.getColumna("num_dias_yasper").setEstilo("color: red; font-size: 18px; align: right;font-weight:bold");
        tab_tipomotivo.setTipoFormulario(true);
        tab_tipomotivo.getGrid().setColumns(4);
        tab_tipomotivo.dibujar();
        
        
        
        PanelTabla pat_tipomotivo = new PanelTabla();
        pat_tipomotivo.setId("pat_tipomotivo");
        pat_tipomotivo.setPanelTabla(tab_tipomotivo);
        
       
        Division div_tipomotivo = new Division();
        div_tipomotivo.setId("div_tipomotivo");
        div_tipomotivo.dividir1(pat_tipomotivo);
        
        agregarComponente(div_tipomotivo);   
        
         vipdf_comprobante.setId("vipdf_comprobante");
            vipdf_comprobante.setTitle("SOLICITUD DE PERMISOS POR DIAS");
            agregarComponente(vipdf_comprobante);
        
            } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el registro de SOLICITUDES. Consulte con el Administrador");
        }        
    }
  
public void generarPDF() {
        if (tab_tipomotivo.getValorSeleccionado() != null) {
            ///////////AQUI ABRE EL REPORTE
            Map map_parametros = new HashMap();
            map_parametros.put("pide_solicitud", Integer.parseInt(tab_tipomotivo.getValor("ide_yasper")));
            map_parametros.put("nombre", utilitario.getVariable("NICK"));
            map_parametros.put("fecha_desde", tab_tipomotivo.getValor("fecha_desde_yasper"));
            map_parametros.put("fecha_hasta", tab_tipomotivo.getValor("fecha_hasta_yasper"));
            map_parametros.put("tipo_soli", "POR DIAS");
            //System.out.println(" " + str_titulos);
            vipdf_comprobante.setVisualizarPDF("rep_asistencia/rep_permisos.jasper", map_parametros);
            vipdf_comprobante.dibujar();
            utilitario.addUpdate("vipdf_comprobante");
        } else {
            utilitario.agregarMensajeInfo("Seleccione una Inscripcion", "");
        }
    }      
public void aprobar(){
    if(tab_tipomotivo.getValor("aprobado_repro_yasper").equals("0")){
    tab_tipomotivo.setValor("aprobado_repro_yasper", "1");
    utilitario.addUpdate("tab_tipomotivo");
    guardar();
    guardarPantalla();
    }
    else {
        utilitario.agregarMensajeError("Aprobar Solicitud", "Para aprobar al socitud de encontrase en estado Registrado");
    }
}
public void anular(){
    if(tab_tipomotivo.getValor("aprobado_repro_yasper").equals("0")){
    tab_tipomotivo.setValor("aprobado_repro_yasper", "2");
    utilitario.addUpdate("tab_tipomotivo");
     guardar();
    guardarPantalla();
    }
    else {
        utilitario.agregarMensajeError("Aprobar Solicitud", "Para aprobar al socitud de encontrase en estado Registrado");
    }
}
public void filtrarEmpleado(SelectEvent evt){
		aut_empleado.onSelect(evt);
		tab_tipomotivo.setCondicion("tipo_yasper=1 AND ide_ypedpe="+aut_empleado.getValor());
		tab_tipomotivo.ejecutarSql();

	}   
public void calcularDiasPermisos(DateSelectEvent evt){
		tab_tipomotivo.modificar(evt);
		
		if(aut_empleado.getValor()!=null){				
			if((tab_tipomotivo.getValor("fecha_desde_yasper")==null || tab_tipomotivo.getValor("fecha_desde_yasper").isEmpty()) 
					|| (tab_tipomotivo.getValor("fecha_hasta_yasper")==null || tab_tipomotivo.getValor("fecha_hasta_yasper").isEmpty())){
				return;
			}
			if (utilitario.isFechaMenor(utilitario.getFecha(tab_tipomotivo.getValor("fecha_hasta_yasper")), utilitario.getFecha(tab_tipomotivo.getValor("fecha_desde_yasper")))){
				tab_tipomotivo.setValor("num_dias_yasper", "0");
				tab_tipomotivo.setValor("total_horas_yasper", "0");
				utilitario.addUpdateTabla(tab_tipomotivo,"num_dias_yasper,total_horas_yasper", "");
				utilitario.agregarMensajeInfo("No se puede calcular el numero de dias", "La fecha hasta no puede ser menor que la fecha desde");		
			}else{				
				int nro_dias=0;	
				int nrh_horas=0;
				nro_dias=utilitario.getDiferenciasDeFechas(utilitario.getFecha(tab_tipomotivo.getValor("fecha_desde_yasper")), utilitario.getFecha(tab_tipomotivo.getValor("fecha_hasta_yasper")));
				tab_tipomotivo.setValor(tab_tipomotivo.getFilaActual(),"num_dias_yasper",(nro_dias+1)+"");
				int nrh_horas_semi=8;  //Integer.parseInt(utilitario.getVariable("p_asi_permiso_dias"));
				nrh_horas=nrh_horas_semi*(nro_dias+1);
				tab_tipomotivo.setValor(tab_tipomotivo.getFilaActual(),"total_horas_yasper",nrh_horas+"");				 
				utilitario.addUpdateTabla(tab_tipomotivo,"num_dias_yasper,total_horas_yasper", "");
			}					
		}else{
			utilitario.agregarMensajeInfo("No se puede calcular los dias de vacación", "Ingrese un Empleado");
		}	
	}	

 
    String docente = "";
    String documento = "";
    String ide_docente = "";

    private boolean tienePerfilSecretaria() {
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
    @Override
    public void insertar() {
        if (aut_empleado.getValor()!=null){
        tab_tipomotivo.insertar();
        tab_tipomotivo.setValor("ide_ypedpe", aut_empleado.getValor());
        tab_tipomotivo.setValor("aprobado_repro_yasper", "0");
        }else{
	utilitario.agregarMensajeInfo("No se puede insertar", "Debe seleccionar el Funcionario que solicita el Permiso");
        }
    }
    	public boolean validarSolicitudPermiso(){
		if (utilitario.isFechaMenor(utilitario.getFecha(tab_tipomotivo.getValor("fecha_hasta_yasper")), utilitario.getFecha(tab_tipomotivo.getValor("fecha_desde_yasper")))){
			utilitario.agregarMensajeInfo("No se puede guardar", "La fecha hasta no puede ser menor que la fecha desde");
			return false;
		}
		if (tab_tipomotivo.getValor("fecha_desde_yasper")==null || tab_tipomotivo.getValor("fecha_desde_yasper").isEmpty()){
			utilitario.agregarMensajeInfo("No se puede guardar", "Debe ingresar la fecha desde ");
			return false;
		}

		if (tab_tipomotivo.getValor("fecha_hasta_yasper")==null || tab_tipomotivo.getValor("fecha_hasta_yasper").isEmpty()){
			utilitario.agregarMensajeInfo("No se puede guardar", "Debe ingresar la fecha hasta");
			return false;
		}
    return true;
    }
    @Override
    public void guardar() {
        if (aut_empleado.getValor()!=null){		
			if (validarSolicitudPermiso()){	
        tab_tipomotivo.guardar();
        guardarPantalla();
        }
		}else{
			utilitario.agregarMensajeInfo("No se puede guardar el Permiso", "Debe seleccionar un Empleado");
		}
    }

    @Override
    public void eliminar() {
        tab_tipomotivo.eliminar();
    }

    public Tabla getTab_tipomotivo() {
        return tab_tipomotivo;
    
    }
   public void setTab_tipomotivo(Tabla tab_tipomotivo){
       this.tab_tipomotivo = tab_tipomotivo;
   }

    public AutoCompletar getAut_empleado() {
        return aut_empleado;
    }

    public void setAut_empleado(AutoCompletar aut_empleado) {
        this.aut_empleado = aut_empleado;
    }

    public VisualizarPDF getVipdf_comprobante() {
        return vipdf_comprobante;
    }

    public void setVipdf_comprobante(VisualizarPDF vipdf_comprobante) {
        this.vipdf_comprobante = vipdf_comprobante;
    }
   
}
