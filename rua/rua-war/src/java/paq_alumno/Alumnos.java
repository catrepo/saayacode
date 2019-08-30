/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_alumno;

import framework.aplicacion.Columna;
import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.MenuPanel;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import org.primefaces.event.SelectEvent;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author RICARDO COLLAHUAZO
 */
 public class Alumnos extends Pantalla {
 private AutoCompletar aut_alumno = new AutoCompletar();
 private MenuPanel menup=new MenuPanel();
 private int int_opcion=0;
 private Tabla tab_alumno=new Tabla();
 private Tabla tab_alumno_direccion= new Tabla();
 private Tabla tab_alumno_correo=new Tabla();
 private Tabla tab_alumno_telefono = new Tabla();
 private Tabla tab_alumno_discapacidad = new Tabla();
 private Tabla tab_archivo_alumno = new Tabla();
 private Tabla tab_dato_familiar = new Tabla();
 private Tabla tab_dato_academico = new Tabla();
 private Tabla tab_dato_laboral = new Tabla();
 private Tabla tab_dato_medico= new Tabla();
 private Tabla tab_aficiones = new Tabla();
 private Boton bot_clean = new Boton();


         
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    public Alumnos() {

                
        aut_alumno.setId("aut_alumno");
        aut_alumno.setAutoCompletar(ser_alumno.getDatosAlumnos("true,false"));
        aut_alumno.setSize(75);
        aut_alumno.setMetodoChange("selecionoAutocompletar");
        bar_botones.agregarComponente(new Etiqueta("Alumno :"));
        bar_botones.agregarComponente(aut_alumno);
    bot_clean.setIcon("ui-icon-cancel");
            bot_clean.setTitle("Limpiar");
            bot_clean.setMetodo("limpiar");
            bar_botones.agregarComponente(bot_clean);

 menup.setMenuPanel("FICHA DEL ESTUDIANTE", "22%");
 menup.agregarItem ("DATOS PERSONALES", "dibujarTablaDatoPersonal", "ui-icon-contact");
 menup.agregarItem ("DIRECCION", "dibujarTablaDireccion", "ui-icon-contact");
 menup.agregarItem ("TELEFONO", "dibujarTablaTelefono", "ui-icon-contact");
 menup.agregarItem ("CORREO", "dibujarTablaCorreo", "ui-icon-contact");
 menup.agregarItem ("DISCAPACIDAD", "dibujarTablaDiscapacidad", "ui-icon-contact");
 menup.agregarItem ("DOCUMENTOS ADJUNTOS", "dibujarTablaDocumentos", "ui-icon-contact");
 menup.agregarItem ("DATOS FAMILIARES", "dibujarTablaDatosFamiliares", "ui-icon-document");
 menup.agregarSubMenu("ESTUDIOS");
 menup.agregarItem ("DATOS ACADEMICOS", "dibujarTablaDatosAcademicos", "ui-icon-document");
 menup.agregarSubMenu("DATOS LABORALES");
 menup.agregarItem ("DATOS LABORALES", "dibujarTablaDatosLaborales", "ui-icon-document"); 
 menup.agregarSubMenu("DATOS MEDICOS");
 menup.agregarItem ("DATOS MEDICOS", "dibujarTablaDatosMedicos", "ui-icon-document");
 menup.agregarSubMenu("AFICIONES");
 menup.agregarItem ("AFICIONES", "dibujarTablaAficiones", "ui-icon-document");
 agregarComponente(menup);
 
 dibujarTablaDatoPersonal();
 }
public void selecionoAutocompletar(SelectEvent evt){
    aut_alumno.onSelect(evt);
    //menup.limpiar();
    if (aut_alumno.getValor() != null) {
        
        if(menup.getOpcion()==1){
            
            dibujarTablaDatoPersonal();  
            //utilitario.addUpdate("tab_alumno_direccion,tab_tabulador");
        }
        else if(menup.getOpcion()==2){
            dibujarTablaDireccion();
        }
        else if(menup.getOpcion()==3){
            dibujarTablaTelefono();
        }
        else if(menup.getOpcion()==4){
            dibujarTablaCorreo();
        }
        else if(menup.getOpcion()==5){
            dibujarTablaDiscapacidad();
        }
        else if(menup.getOpcion()==6){
            dibujarTablaDocumentos();
        }
        else if(menup.getOpcion()==7){
            dibujarTablaDatosFamiliares();
        }
        else if(menup.getOpcion()==8){
            dibujarTablaDatosAcademicos();
        }
        else if(menup.getOpcion()==9){
            dibujarTablaDatosLaborales();
        }
        else if(menup.getOpcion()==10){
            dibujarTablaDatosMedicos();
        }
        else if(menup.getOpcion()==11){
            dibujarTablaAficiones();
        }
                
        } else {
           utilitario.agregarMensajeInfo("Seleccionar un Empleado", "Debe seleccionar un Alumno");
        }    
}
   


public void alumno(SelectEvent evt){
    aut_alumno.onSelect(evt);
    if(aut_alumno!=null){
        switch(menup.getOpcion()){
            case 1:
                dibujarTablaDatoPersonal();
            break;
            case 2:
                dibujarTablaDireccion();
            break;
            case 3:
                dibujarTablaTelefono();
            break;
            case 4:
                dibujarTablaCorreo();
            break;
            case 5:
                dibujarTablaDiscapacidad();
            break;
            case 6:
                dibujarTablaDocumentos();
            break;
            case 7:
                dibujarTablaDatosFamiliares();
            break;
            case 8:
                dibujarTablaDatosAcademicos();
            break;
            case 9:
                dibujarTablaDatosLaborales();
            break;
            case 10:
                dibujarTablaDatosMedicos();
            break;
            case 11:
                dibujarTablaAficiones();
            break;        
            default:
                dibujarTablaDatoPersonal();   
        }                
    } 
}
 public void dibujarTablaDatoPersonal(){
 int_opcion=1;

 tab_alumno =new Tabla();
 tab_alumno.setId("tab_alumno");
 tab_alumno.setTipoFormulario(true);
 tab_alumno.setTabla("yavirac_alum_dato_personal", "ide_yaldap", 1);
 tab_alumno.setCondicion("ide_yaldap="+aut_alumno.getValor());
 tab_alumno.getColumna("ide_ystnac").setCombo(ser_estructura.getNacionalidad("true,false"));
 tab_alumno.getColumna("ide_ysttis").setCombo(ser_estructura.getTipoSangre("true,false"));
 tab_alumno.getColumna("ide_ystdoi").setCombo(ser_estructura.getDocumentoIdentidad("true,false")); 
 tab_alumno.getColumna("ide_ystdip").setCombo(ser_estructura.getDistribucionPolitica("true,false"));
 tab_alumno.getColumna("ide_ystesc").setCombo(ser_estructura.getEstadoCivil("true,false"));
 tab_alumno.getColumna("ide_ystgen").setCombo(ser_estructura.getGenero("true,false"));
 tab_alumno.getColumna("fotografia_yaldap").setNombreVisual("Foto");
 tab_alumno.getColumna("fotografia_yaldap").setUpload();
 tab_alumno.getColumna("fotografia_yaldap").setImagen(); 
 tab_alumno.getColumna("firma_yaldap").setNombreVisual("Firma");
 tab_alumno.getColumna("firma_yaldap").setUpload();
 tab_alumno.getColumna("firma_yaldap").setImagen(); 
 tab_alumno.setMostrarNumeroRegistros(false);
 tab_alumno.getGrid().setColumns(4);
 tab_alumno.dibujar();
 //tab_alumno.insertar();
 tab_alumno.setHeader("DATOS PERSONALES");
     PanelTabla pat_panel1=new PanelTabla();
     pat_panel1.setPanelTabla(tab_alumno);
 

     pat_panel1.getMenuTabla().getItem_buscar().setRendered(false);

     Division div_division= new Division();
     div_division.dividir1(pat_panel1);//Agrego el Tabulador a una Division
     agregarComponente(div_division);//
     menup.dibujar(1,"DATOS PERSONALES",div_division);  
     
     
 }
 public void dibujarTablaDireccion(){
 int_opcion=2;  
     tab_alumno_direccion.setId("tab_alumno_direccion");
     tab_alumno_direccion.setTabla("yavirac_alum_direccion_alumno", "ide_yaldia", 2);
     tab_alumno_direccion.setCondicion("ide_yaldap="+aut_alumno.getValor());
     tab_alumno_direccion.getColumna("ide_yaldap").setVisible(false); // para ocultar
     tab_alumno_direccion.getColumna("ide_yaldia").setNombreVisual("IDE");
     tab_alumno_direccion.getColumna("ide_ystdip").setCombo(ser_estructura.getDistribucionPolitica("true,false"));
     tab_alumno_direccion.getColumna("ide_ystdip").setNombreVisual("DISTRIBUCIÓN POLÍTICA");
     tab_alumno_direccion.getColumna("descripcion_yaldia").setNombreVisual("LUGAR RESIDENCIA");
     tab_alumno_direccion.getColumna("notificacion_yaldia").setNombreVisual("REGISTRO CAMBIOS EN LA DIRECCIÓN");
     tab_alumno_direccion.getColumna("activo_yaldia").setNombreVisual("ACTIVO");
     tab_alumno_direccion.setTipoFormulario(true);
     tab_alumno_direccion.getGrid().setColumns(4);
     tab_alumno_direccion.dibujar();
     PanelTabla pat_panel2=new PanelTabla();
     pat_panel2.setPanelTabla(tab_alumno_direccion); 
     
     pat_panel2.getMenuTabla().getItem_buscar().setRendered(false);
     Division div_division= new Division();
     div_division.dividir1(pat_panel2);//Agrego el Tabulador a una Division
     agregarComponente(div_division);//

    menup.dibujar(2,"DIRECCION",div_division);     
 }
  public void dibujarTablaTelefono(){
int_opcion=3;     
tab_alumno_telefono.setId("tab_alumno_telefono");
     tab_alumno_telefono.setTabla("yavirac_alum_telefono", "ide_yaltel", 4);
      tab_alumno_telefono.setCondicion("ide_yaldap="+aut_alumno.getValor());
      tab_alumno_telefono.getColumna("ide_yaldap").setVisible(false);
     tab_alumno_telefono.getColumna("ide_yaltel").setNombreVisual("IDE");
     tab_alumno_telefono.getColumna("ide_ysttio").setCombo(ser_estructura.getTipoOperadora());
     tab_alumno_telefono.getColumna("ide_ysttio").setNombreVisual("TIPO OPERADORA");
     tab_alumno_telefono.getColumna("ide_ysttit").setCombo(ser_estructura.getTipoTelefono());
     tab_alumno_telefono.getColumna("ide_ysttit").setNombreVisual("TIPO TELEFONO");
     tab_alumno_telefono.getColumna("ide_yaldfe").setCombo(ser_alumno.getDatosFamiliaremergencia());
     tab_alumno_telefono.getColumna("ide_yaldfe").setNombreVisual("DATO FAMILIAR");
     tab_alumno_telefono.getColumna("numero_yaltel").setNombreVisual("NUMERO DE TELEFONO");
     tab_alumno_telefono.getColumna("activo_yaltel").setNombreVisual("ACTIVO");
     tab_alumno_telefono.getColumna("notificacion_yaltel").setNombreVisual("MODIFICACIÓN TELEFONO");
     tab_alumno_telefono.setTipoFormulario(true);
     tab_alumno_telefono.getGrid().setColumns(4);
     tab_alumno_telefono.dibujar();
     PanelTabla pat_panel4=new PanelTabla();
     pat_panel4.setPanelTabla(tab_alumno_telefono);
     
      pat_panel4.getMenuTabla().getItem_buscar().setRendered(false);
     Division div_division= new Division();
     div_division.dividir1(pat_panel4);//Agrego el Tabulador a una Division
     agregarComponente(div_division);//

    menup.dibujar(3,"TELEFONO",div_division);

 }
 public void dibujarTablaCorreo(){
  int_opcion=4;   
   tab_alumno_correo.setId("tab_alumno_correo");
     tab_alumno_correo.setTabla("yavirac_alum_correo", "ide_yalcor", 3);
      tab_alumno_correo.setCondicion("ide_yaldap="+aut_alumno.getValor());
      tab_alumno_correo.getColumna("ide_yaldap").setVisible(false);
     tab_alumno_correo.getColumna("ide_yalcor").setNombreVisual("IDE");
     tab_alumno_correo.getColumna("ide_ysttoc").setCombo(ser_estructura.getTipoCorreo());
     tab_alumno_correo.getColumna("ide_ysttoc").setNombreVisual("TIPO CORREO");
     tab_alumno_correo.getColumna("ide_yaldfe").setCombo(ser_alumno.getDatosFamiliaremergencia());
     tab_alumno_correo.getColumna("ide_yaldfe").setNombreVisual("DATO FAMILIAR");
     tab_alumno_correo.getColumna("descripcion_yalcor").setNombreVisual("CORREO");
     tab_alumno_correo.getColumna("notificacion_yalcor").setNombreVisual("DESCRIPCION CAMBIO DE CORREO");
     tab_alumno_correo.getColumna("activo_yalcor ").setNombreVisual("ACTIVO");
     tab_alumno_correo.setTipoFormulario(true);
     tab_alumno_correo.getGrid().setColumns(4);
     tab_alumno_correo.dibujar();
     PanelTabla pat_panel3=new PanelTabla();
     pat_panel3.setPanelTabla(tab_alumno_correo);
     
      pat_panel3.getMenuTabla().getItem_buscar().setRendered(false);
     Division div_division= new Division();
     div_division.dividir1(pat_panel3);//Agrego el Tabulador a una Division
     agregarComponente(div_division);//

    menup.dibujar(4,"CORREO",div_division);
 }
 public void dibujarTablaDiscapacidad(){
 int_opcion=5;    
 tab_alumno_discapacidad.setId("tab_alumno_discapacidad");
     tab_alumno_discapacidad.setTabla("yavirac_alum_discapacidad", "ide_yaldis", 5);
      tab_alumno_discapacidad.setCondicion("ide_yaldap="+aut_alumno.getValor());
     tab_alumno_discapacidad.getColumna("ide_yaldap").setVisible(false);
     tab_alumno_discapacidad.getColumna("ide_yaldis").setNombreVisual("IDE");
     tab_alumno_discapacidad.getColumna("ide_ystgrd").setCombo(ser_estructura.getGradoDiscapacidad());
     tab_alumno_discapacidad.getColumna("ide_ystgrd").setNombreVisual("GRADO DISCAPACIDAD");
     tab_alumno_discapacidad.getColumna("ide_ysttid").setCombo(ser_estructura.getTipoDiscapacidad());
     tab_alumno_discapacidad.getColumna("ide_ysttid").setNombreVisual("TIPO DISCAPACIDAD");
     tab_alumno_discapacidad.getColumna("numero_conadis_yaldis").setNombreVisual("NÚMERO  DE CARNET CONADIS");
     tab_alumno_discapacidad.getColumna("fecha_emision_yaldis").setNombreVisual("FECHA EMISION CARNET ");
     tab_alumno_discapacidad.getColumna("porcentaje_disca_yaldis").setNombreVisual("PORCENTAJE");
     tab_alumno_discapacidad.getColumna("activo_yaldis").setNombreVisual("ACTIVO");
     tab_alumno_discapacidad.setTipoFormulario(true);
     tab_alumno_discapacidad.getGrid().setColumns(4);
     tab_alumno_discapacidad.dibujar();
     PanelTabla pat_panel5=new PanelTabla();
     pat_panel5.setPanelTabla(tab_alumno_discapacidad);
     
      pat_panel5.getMenuTabla().getItem_buscar().setRendered(false);
     Division div_division= new Division();
     div_division.dividir1(pat_panel5);//Agrego el Tabulador a una Division
     agregarComponente(div_division);//

    menup.dibujar(5,"DISCAPACIDAD",div_division);
 }
  public void dibujarTablaDocumentos(){
  int_opcion=6;   
   tab_archivo_alumno.setId("tab_archivo_alumno");
    tab_archivo_alumno.setTabla("yavirac_alum_documento", "ide_yaldoc", 6);
    tab_archivo_alumno.setCondicion("ide_yaldap="+aut_alumno.getValor());
     tab_archivo_alumno.getColumna("ide_yaldap").setVisible(false);
     tab_archivo_alumno.getColumna("ide_yaldoc").setNombreVisual("IDE");
     tab_archivo_alumno.getColumna("ide_ysttia").setCombo(ser_estructura.getTipoArchivo());
     tab_archivo_alumno.getColumna("ide_ysttia").setNombreVisual("TIPO ARCHIVO");
     tab_archivo_alumno.getColumna("descripcion_yaldoc").setNombreVisual("DESCRIPCION");
     tab_archivo_alumno.getColumna("fecha_yaldoc").setNombreVisual("FECHA");
     tab_archivo_alumno.getColumna("archivo_anexo_yaldoc").setNombreVisual("ARCHIVO ANEXO");
     tab_archivo_alumno.setTipoFormulario(true);
     tab_archivo_alumno.getGrid().setColumns(4);
     tab_archivo_alumno.dibujar();
     PanelTabla pat_panel6=new PanelTabla();
     pat_panel6.setPanelTabla(tab_archivo_alumno);
     
      pat_panel6.getMenuTabla().getItem_buscar().setRendered(false);
     Division div_division= new Division();
     div_division.dividir1(pat_panel6);//Agrego el Tabulador a una Division
     agregarComponente(div_division);//

    menup.dibujar(6,"DOCUMENTOS ADJUNTOS",div_division);
 }
 public void dibujarTablaDatosFamiliares(){
 int_opcion=7;
 tab_dato_familiar.setId("tab_dato_familiar");
 tab_dato_familiar.setTabla("yavirac_alum_dato_fami_alumno", "ide_yaldfa", 7);
 tab_dato_familiar.setCondicion("ide_yaldap="+aut_alumno.getValor());
 tab_dato_familiar.getColumna("ide_yaldap").setVisible(false);
 tab_dato_familiar.getColumna("ide_yalgas").setCombo(ser_alumno.getGasto("true,false"));
 tab_dato_familiar.getColumna("ide_yalgas").setNombreVisual("GASTOS");
 tab_dato_familiar.getColumna("ide_yalpea").setNombreVisual("PERIODO ACADEMICO");
 tab_dato_familiar.getColumna("ide_yaltiv").setCombo(ser_alumno.getDatosVivienda("true,false"));
 tab_dato_familiar.getColumna("ide_yaltiv").setNombreVisual("TIPO VIVIENDA");
 tab_dato_familiar.getColumna("ide_yalocu").setCombo(ser_alumno.getOcupacion("true,false"));
 tab_dato_familiar.getColumna("ide_yalocu").setNombreVisual("OCUPACION PADRE");
 tab_dato_familiar.getColumna("ide_ystpaf").setCombo(ser_estructura.getParentezcoFamiliar("true,false"));
 tab_dato_familiar.getColumna("ide_ystpaf").setNombreVisual("PARENTEZCO FAMILIAR");
 tab_dato_familiar.getColumna("ide_ystesc").setCombo(ser_estructura.getEstadoCivil("true,false"));
 tab_dato_familiar.getColumna("ide_ystesc").setNombreVisual("ESTADO CIVIL");
 tab_dato_familiar.getColumna("yav_ide_yalocu").setCombo(ser_alumno.getOcupacion("true,false"));
 tab_dato_familiar.getColumna("yav_ide_yalocu").setNombreVisual("OCUPACION MADRE");
 tab_dato_familiar.getColumna("yav_ide_yalocu2").setCombo(ser_alumno.getOcupacion("true,false"));
 tab_dato_familiar.getColumna("yav_ide_yalocu2").setNombreVisual("OCUPACION CONYUGE");
 tab_dato_familiar.getColumna("nombre_padre_yaldfa").setNombreVisual("NOMBRE PADRE");
 tab_dato_familiar.getColumna("edad_padre_yaldfa").setNombreVisual("EDAD PADRE");
 tab_dato_familiar.getColumna("nombre_madre_yaldfa").setNombreVisual("NOMBRE MADRE");
 tab_dato_familiar.getColumna("edad_madre_yaldfa").setNombreVisual("EDAD MADRE");
 tab_dato_familiar.getColumna("nombre_conyuge_yaldfa").setNombreVisual("NOMBRE CONYUGE");
 tab_dato_familiar.getColumna("edad_conyuge_yaldfa").setNombreVisual("EDAD CONYUGE");
 tab_dato_familiar.getColumna("numero_hijo_yaldfa").setNombreVisual("NUMERO DE HIJOS");
 tab_dato_familiar.getColumna("hermanos_yaldfa").setNombreVisual("CANTIDAD HERMANOS");
 tab_dato_familiar.getColumna("puesto_ocupa_yaldfa").setNombreVisual("PUESTO QUE OCUPA");
 tab_dato_familiar.getColumna("activo_yaldfa").setNombreVisual("ACTIVO");
 tab_dato_familiar.setTipoFormulario(true);
 tab_dato_familiar.getGrid().setColumns(4);
 tab_dato_familiar.setTipoFormulario(true);
 tab_dato_familiar.getGrid().setColumns(4);
 tab_dato_familiar.dibujar();
 tab_dato_familiar.setHeader("DATOS FAMILIARES ALUMNO");
     PanelTabla pat_panel2=new PanelTabla();
     pat_panel2.setPanelTabla(tab_dato_familiar);
     pat_panel2.getMenuTabla().getItem_buscar().setRendered(false);

     menup.dibujar(7,"DATOS FAMILIARES ALUMNO",pat_panel2);  
}
 public void dibujarTablaDatosAcademicos(){
 int_opcion=8;
 tab_dato_academico.setId("tab_dato_academico");
 tab_dato_academico.setTabla("yavirac_alum_dato_academico", "ide_yaldaa", 8);
 tab_dato_academico.setCondicion("ide_yaldap="+aut_alumno.getValor());
 tab_dato_academico.getColumna("ide_yaldaa").setNombreVisual("IDE");
 tab_dato_academico.getColumna("ide_yaldap").setVisible(false);
 tab_dato_academico.getColumna("ide_ystins").setNombreVisual("INSTITUCIÓN SECUNDARIA");
 tab_dato_academico.getColumna("ide_ystins").setCombo(ser_estructura.getInstitucion());
 tab_dato_academico.getColumna("ide_ystdip").setNombreVisual("DISTRIBUCIÓN POLÍTICA");
 tab_dato_academico.getColumna("ide_ystdip").setCombo(ser_estructura.getDistribucionPolitica("true,false"));
 tab_dato_academico.getColumna("ide_yalcar").setNombreVisual("CARRERA");
 tab_dato_academico.getColumna("ide_yalcar").setCombo(ser_alumno.getCarrera());
 tab_dato_academico.getColumna("yav_ide_ystins").setNombreVisual("INSTITUCIÓN SUPERIOR");
 tab_dato_academico.getColumna("yav_ide_ystins").setCombo(ser_estructura.getInstitucion());
 tab_dato_academico.getColumna("yav_ide_ystdip").setNombreVisual("DISTRIBUCIÓN POLÍTICA");
 tab_dato_academico.getColumna("yav_ide_ystdip").setCombo(ser_estructura.getDistribucionPolitica("true,false"));        
 tab_dato_academico.getColumna("ide_ypetip").setNombreVisual("TÍTULO PROFESIONAL");
// tab_dato_academico.getColumna("ide_ypetip").setCombo(ser_personal.getTituloProfesional());
 tab_dato_academico.getColumna("ide_yalpea").setNombreVisual("PERIODO ACADEMICO");
 tab_dato_academico.getColumna("anio_graduacion_yaldaa").setNombreVisual("AÑO GRADUACIÓN");
 tab_dato_academico.getColumna("estudio_adicional_yaldaa").setNombreVisual("ESTUDIOS ADICIONALES");
 tab_dato_academico.getColumna("anio_ingreso_yaldaa").setNombreVisual("AÑO DE INGRESO");
 tab_dato_academico.getColumna("anio_egreso_yaldaa").setNombreVisual("AÑO EGRESO");
 tab_dato_academico.getColumna("activo_yaldaa").setNombreVisual("ACTIVO");
 //tab_dato_academico.setCondicion("ide_yaldaa=-1");
 tab_dato_academico.setTipoFormulario(true);
 tab_dato_academico.getGrid().setColumns(4);
 tab_dato_academico.dibujar();
 tab_dato_academico.setHeader("DATOS ACADEMICOS");
     PanelTabla pat_panel3=new PanelTabla();
     pat_panel3.setPanelTabla(tab_dato_academico);
     pat_panel3.getMenuTabla().getItem_buscar().setRendered(false);
  
 menup.dibujar(8,"DATOS ACADEMICOS",pat_panel3);
}
public void dibujarTablaDatosLaborales(){
 int_opcion=9;
 tab_dato_laboral.setId("tab_dato_laboral");
 tab_dato_laboral.setTabla("yavirac_alum_dato_laboral", "ide_yaldal", 9);
 tab_dato_laboral.setCondicion("ide_yaldap="+aut_alumno.getValor());
 tab_dato_laboral.getColumna("ide_yaldap").setVisible(false);
 tab_dato_laboral.getColumna("ide_yaldal").setNombreVisual("IDE");
 tab_dato_laboral.getColumna("ide_yalpea").setNombreVisual("PERIODO ACADEMICO");
 tab_dato_laboral.getColumna("laborando_yaldal").setNombreVisual("TRABAJA");
 tab_dato_laboral.getColumna("nombre_empresa_yaldal").setNombreVisual("NOMBRE EMPRESA");
 tab_dato_laboral.getColumna("lugar_empresa_yaldal").setNombreVisual("UBICACION");
 tab_dato_laboral.getColumna("cargo_empresa_yaldal").setNombreVisual("CARGO");
 tab_dato_laboral.getColumna("horario_empresa_yaldal").setNombreVisual("HORARIO");
 tab_dato_laboral.getColumna("telefono_empresa_yaldal").setNombreVisual("TELEFONO");
 tab_dato_laboral.getColumna("email_empresa_yaldal").setNombreVisual("CORREO");
 tab_dato_laboral.getColumna("activo_yaldal").setNombreVisual("ACTIVO");
 tab_dato_laboral.setTipoFormulario(true);
 tab_dato_laboral.getGrid().setColumns(4);
 tab_dato_laboral.dibujar();
 tab_dato_laboral.setHeader("DATOS LABORALES");
     PanelTabla pat_panel=new PanelTabla();
     pat_panel.setPanelTabla(tab_dato_laboral);
     pat_panel.getMenuTabla().getItem_buscar().setRendered(false);

 
 menup.dibujar(9,"DATOS LABORALES",pat_panel);
}
public void dibujarTablaDatosMedicos(){
 int_opcion=10;
 tab_dato_medico.setId("tab_dato_medico");
 tab_dato_medico.setTabla("yavirac_alum_dato_medico", "ide_yaldam", 10);
 tab_dato_medico.setCondicion("ide_yaldap="+aut_alumno.getValor());
 tab_dato_medico.getColumna("ide_yaldap").setVisible(false);
 tab_dato_medico.getColumna("ide_yaldam").setNombreVisual("IDE");
 tab_dato_medico.getColumna("ide_yaltit").setCombo(ser_alumno.getTipoTratamiento());
 tab_dato_medico.getColumna("ide_yaltit").setNombreVisual("TIPO TRATAMIENTO");
 tab_dato_medico.getColumna("ide_yalpea").setNombreVisual("PERIODO ACADEMICO");
 tab_dato_medico.getColumna("ide_yalenf").setCombo(ser_alumno.getEnfermedad());
 tab_dato_medico.getColumna("ide_yalenf").setNombreVisual("TIPO ENFERMEDAD");
 tab_dato_medico.getColumna("padece_yaldam").setNombreVisual("PADECE ENFERMEDAD");
 tab_dato_medico.getColumna("tratamiento_yaldam").setNombreVisual("TRATAMIENTO");
 tab_dato_medico.getColumna("detalle_tratamiento_yaldam").setNombreVisual("DETALLE TRATAMIENTO");
 tab_dato_medico.getColumna("hospitalizado_yaldam").setNombreVisual("HOSPITALIZADO");
 tab_dato_medico.getColumna("motivo_especializado_yaldam").setNombreVisual("MOTIVO DE HOSPITALIZACION");
 tab_dato_medico.getColumna("operado_yaldam").setNombreVisual("OPERADO");
 tab_dato_medico.getColumna("motivo_operado_yaldam").setNombreVisual("MOTIVO DE OPERACION");
 tab_dato_medico.getColumna("enfermedad_cronica_yaldam").setNombreVisual("ENFERMEDAD CRONICA");
 tab_dato_medico.getColumna("activo_yaldam").setNombreVisual("ACTIVO");
 tab_dato_medico.setTipoFormulario(true);
 tab_dato_medico.getGrid().setColumns(4);
 tab_dato_medico.dibujar();
 tab_dato_medico.setHeader("DATOS MEDICOS");
     PanelTabla pat_panel4=new PanelTabla();
     pat_panel4.setPanelTabla(tab_dato_medico);
     pat_panel4.getMenuTabla().getItem_buscar().setRendered(false);

 menup.dibujar(10,"DATOS MEDICOS",pat_panel4);
}
public void dibujarTablaAficiones(){
 int_opcion=11;
 tab_aficiones.setId("tab_aficiones");
 tab_aficiones.setTabla("yavirac_alum_aficion", "ide_yalafi", 11);
 tab_aficiones.setCondicion("ide_yaldap="+aut_alumno.getValor());
 tab_aficiones.getColumna("ide_yaldap").setVisible(false);
 tab_aficiones.getColumna("ide_yalafi").setNombreVisual("IDE"); 
 tab_aficiones.getColumna("ide_yaltia").setCombo(ser_alumno.getTipoAficion());
 tab_aficiones.getColumna("ide_yaltia").setNombreVisual("TIPO AFICION");
 tab_aficiones.getColumna("ide_yalpea").setNombreVisual("PERIODO ACADEMICO");
 tab_aficiones.getColumna("detalle_yalafi").setNombreVisual("DETALLE AFICIONES");
 tab_aficiones.getColumna("activo_yalafi").setNombreVisual("ACTIVO");
 tab_aficiones.setTipoFormulario(true);
 tab_aficiones.getGrid().setColumns(4);
 tab_aficiones.dibujar();
 tab_aficiones.setHeader("AFICIONES");
     PanelTabla pat_panel5=new PanelTabla();
     pat_panel5.setPanelTabla(tab_aficiones);
     pat_panel5.getMenuTabla().getItem_buscar().setRendered(false);

 menup.dibujar(11,"AFICIONES",pat_panel5);
}

public void limpiar() {
        aut_alumno.limpiar();
        menup.limpiar();
    }

    @Override
    public void insertar() {
      if(int_opcion==1){
            tab_alumno.insertar();
        }  
        else if(int_opcion==2){
            tab_alumno_direccion.insertar();
            tab_alumno_direccion.setValor("ide_yaldap", aut_alumno.getValor());
            
           }
        else if(int_opcion==3){
            tab_alumno_telefono.insertar();
            tab_alumno_telefono.setValor("ide_yaldap", aut_alumno.getValor());

           }
         else if(int_opcion==4){
            tab_alumno_correo.insertar();
                        tab_alumno_correo.setValor("ide_yaldap", aut_alumno.getValor());

           }
         else if(int_opcion==5){
            tab_alumno_discapacidad.insertar();
                        tab_alumno_discapacidad.setValor("ide_yaldap", aut_alumno.getValor());

           }
         else if(int_opcion==6){
            tab_archivo_alumno.insertar();
                        tab_archivo_alumno.setValor("ide_yaldap", aut_alumno.getValor());

           }
         else if(int_opcion==7){
            tab_dato_familiar.insertar();
                        tab_dato_familiar.setValor("ide_yaldap", aut_alumno.getValor());

           }
         else if(int_opcion==8){
            tab_dato_academico.insertar();
                        tab_dato_academico.setValor("ide_yaldap", aut_alumno.getValor());

           }
         else if(int_opcion==9){
             if(aut_alumno.getValue() == null){
                 utilitario.agregarMensajeError("Seleccione opción", "Seleccione un alumno para poder registra el dato laboral");
             }
             else {
            tab_dato_laboral.insertar();
                        tab_dato_laboral.setValor("ide_yaldap", aut_alumno.getValor());
             }
           }
         else if(int_opcion==10){
            tab_dato_medico.insertar();
                        tab_dato_medico.setValor("ide_yaldap", aut_alumno.getValor());

           }
         else if(int_opcion==11){
            tab_aficiones.insertar();
                        tab_aficiones.setValor("ide_yaldap", aut_alumno.getValor());

           }
    }

    @Override
    public void guardar() {
        if(int_opcion==1){
            tab_alumno.guardar();
            guardarPantalla();
            aut_alumno.actualizar();
            aut_alumno.setValor(tab_alumno.getValor("ide_yaldap"));
            utilitario.addUpdate("aut_alumno");
           }
        else if(int_opcion==2){
            tab_alumno_direccion.guardar();
            guardarPantalla();
            aut_alumno.actualizar();
           }
        else if(int_opcion==3){
            tab_alumno_telefono.guardar();
            aut_alumno.actualizar();
           }
         else if(int_opcion==4){
            tab_alumno_correo.guardar();
            aut_alumno.actualizar();
           }
         else if(int_opcion==5){
            tab_alumno_discapacidad.guardar();
            aut_alumno.actualizar();
           }
         else if(int_opcion==6){
            tab_archivo_alumno.guardar();
            aut_alumno.actualizar();
           }
         else if(int_opcion==7){
            tab_dato_familiar.guardar();
            aut_alumno.actualizar();
           }
         else if(int_opcion==8){
            tab_dato_academico.guardar();
            aut_alumno.actualizar();
           }
         else if(int_opcion==9){
            tab_dato_laboral.guardar();
            aut_alumno.actualizar();
           }
         else if(int_opcion==10){
            tab_dato_medico.guardar();
            aut_alumno.actualizar();
           }
         else if(int_opcion==11){
            tab_aficiones.guardar();
            aut_alumno.actualizar();
           }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
      if(int_opcion==1){
          tab_alumno.eliminar();
      }
    }
    
    
    public Tabla getTab_alumno() {
        return tab_alumno;
    }

    public void setTab_alumno(Tabla tab_alumno) {
        this.tab_alumno = tab_alumno;
    }

    public Tabla getTab_alumno_direccion() {
        return tab_alumno_direccion;
    }

    public void setTab_alumno_direccion(Tabla tab_alumno_direccion) {
        this.tab_alumno_direccion = tab_alumno_direccion;
    }

    public Tabla getTab_alumno_correo() {
        return tab_alumno_correo;
    }

    public void setTab_alumno_correo(Tabla tab_alumno_correo) {
        this.tab_alumno_correo = tab_alumno_correo;
    }

    public Tabla getTab_alumno_telefono() {
        return tab_alumno_telefono;
    }

    public void setTab_alumno_telefono(Tabla tab_alumno_telefono) {
        this.tab_alumno_telefono = tab_alumno_telefono;
    }

    public Tabla getTab_alumno_discapacidad() {
        return tab_alumno_discapacidad;
    }

    public void setTab_alumno_discapacidad(Tabla tab_alumno_discapacidad) {
        this.tab_alumno_discapacidad = tab_alumno_discapacidad;
    }

    public Tabla getTab_archivo_alumno() {
        return tab_archivo_alumno;
    }

    public void setTab_archivo_alumno(Tabla tab_archivo_alumno) {
        this.tab_archivo_alumno = tab_archivo_alumno;
    }
     public AutoCompletar getAut_alumno() {
        return aut_alumno;
    }

    public void setAut_alumno(AutoCompletar aut_alumno) {
        this.aut_alumno = aut_alumno;
    }  

    public Tabla getTab_dato_familiar() {
        return tab_dato_familiar;
    }

    public void setTab_dato_familiar(Tabla tab_dato_familiar) {
        this.tab_dato_familiar = tab_dato_familiar;
    }

    public Tabla getTab_dato_academico() {
        return tab_dato_academico;
    }

    public void setTab_dato_academico(Tabla tab_dato_academico) {
        this.tab_dato_academico = tab_dato_academico;
    }

    public Tabla getTab_dato_laboral() {
        return tab_dato_laboral;
    }

    public void setTab_dato_laboral(Tabla tab_dato_laboral) {
        this.tab_dato_laboral = tab_dato_laboral;
    }

    public Tabla getTab_dato_medico() {
        return tab_dato_medico;
    }

    public void setTab_dato_medico(Tabla tab_dato_medico) {
        this.tab_dato_medico = tab_dato_medico;
    }

    public Tabla getTab_aficiones() {
        return tab_aficiones;
    }

    public void setTab_aficiones(Tabla tab_aficiones) {
        this.tab_aficiones = tab_aficiones;
    }
    
   } 

    
