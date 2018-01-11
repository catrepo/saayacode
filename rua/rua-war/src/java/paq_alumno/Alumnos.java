/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_alumno;

import framework.aplicacion.Columna;
import framework.componentes.AutoCompletar;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.MenuPanel;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
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
    

 menup.setMenuPanel("FICHA DEL ESTUDIANTE", "22%");
 menup.agregarItem ("DATOS PERSONALES", "dibujarTablaDatoPersonal", "ui-icon-contact");
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
 
 }
public void selecionoAutocompletar(SelectEvent evt){
    aut_alumno.onSelect(evt);
    if (aut_alumno.getValor() != null) {
            tab_alumno.setCondicion("ide_yaldap="+aut_alumno.getValor());
            tab_alumno.ejecutarSql();

        } else {
            tab_alumno.limpiar();
            tab_alumno.limpiar();
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
                dibujarTablaDatosFamiliares();
            break;
            case 3:
                dibujarTablaDatosAcademicos();
            break;
            case 4:
                dibujarTablaDatosLaborales();
            break;
            case 5:
                dibujarTablaDatosMedicos();
            break;
            case 6:
                dibujarTablaAficiones();
            break;        
            default:
                dibujarTablaDatoPersonal();   
        }                
    } 
}
 public void dibujarTablaDatoPersonal(){
 int_opcion=1;
 Tabulador tab_tabulador = new Tabulador();
 tab_tabulador.setId("tab_tabulador");
 tab_alumno =new Tabla();
 tab_alumno.setId("tab_alumno");
 tab_alumno.setTipoFormulario(true);
 tab_alumno.setTabla("yavirac_alum_dato_personal", "ide_yaldap", 1);
 tab_alumno.setCondicion("ide_yaldap=-1");
 tab_alumno.getColumna("ide_ystnac").setCombo(ser_estructura.getNacionalidad("true,false"));
 tab_alumno.getColumna("ide_ysttis").setCombo(ser_estructura.getTipoSangre("true,false"));
 tab_alumno.getColumna("ide_ystdoi").setCombo(ser_estructura.getDocumentoIdentidad("true,false")); 
 tab_alumno.getColumna("ide_ystdip").setCombo(ser_estructura.getDistribucionPolitica("true,false"));
 tab_alumno.getColumna("ide_ystesc").setCombo(ser_estructura.getEstadoCivil("true,false"));
 tab_alumno.agregarRelacion(tab_alumno_correo);
 tab_alumno.agregarRelacion(tab_alumno_direccion);
 tab_alumno.agregarRelacion(tab_alumno_discapacidad);
 tab_alumno.agregarRelacion(tab_alumno_telefono);
 tab_alumno.agregarRelacion(tab_archivo_alumno);
 tab_alumno.setMostrarNumeroRegistros(false);
 tab_alumno.getGrid().setColumns(4);
 tab_alumno.dibujar();
 //tab_alumno.insertar();
 tab_alumno.setHeader("DATOS PERSONALES");
     PanelTabla pat_panel1=new PanelTabla();
     pat_panel1.setPanelTabla(tab_alumno);
     
     tab_alumno_direccion.setId("tab_alumno_direccion");
     tab_alumno_direccion.setIdCompleto("tab_tabulador:tab_alumno_direccion");
     tab_alumno_direccion.setTabla("yavirac_alum_direccion_alumno", "ide_yaldia", 2);
     tab_alumno_direccion.getColumna("ide_yaldia").setNombreVisual("IDE");
     tab_alumno_direccion.getColumna("ide_ystdip").setNombreVisual("DISTRIBUCIÓN POLÍTICA");
     tab_alumno_direccion.getColumna("descripcion_yaldia").setNombreVisual("LUGAR RESIDENCIA");
     tab_alumno_direccion.getColumna("notificacion_yaldia").setNombreVisual("REGISTRO CAMBIOS EN LA DIRECCIÓN");
     tab_alumno_direccion.getColumna("activo_yaldia").setNombreVisual("ACTIVO");
     
     tab_alumno_direccion.dibujar();
     PanelTabla pat_panel2=new PanelTabla();
     pat_panel2.setPanelTabla(tab_alumno_direccion);

     tab_alumno_correo.setId("tab_alumno_correo");
     tab_alumno_correo.setIdCompleto("tab_tabulador:tab_alumno_correo");
     tab_alumno_correo.setTabla("yavirac_alum_correo", "ide_yalcor", 3);
     tab_alumno_correo.getColumna("ide_yalcor").setNombreVisual("IDE");
     tab_alumno_correo.getColumna("ide_ysttoc").setNombreVisual("TIPO CORREO");
     tab_alumno_correo.getColumna("ide_yaldfe").setNombreVisual("DATO FAMILIAR");
     tab_alumno_correo.getColumna("descripcion_yalcor").setNombreVisual("CLASE CORREO");
     tab_alumno_correo.getColumna("notificacion_yalcor").setNombreVisual("DESCRIPCION CAMBIO DE CORREO");
     tab_alumno_correo.getColumna("activo_yalcor ").setNombreVisual("ACTIVO");
     tab_alumno_correo.dibujar();
     PanelTabla pat_panel3=new PanelTabla();
     pat_panel3.setPanelTabla(tab_alumno_correo);

     tab_alumno_telefono.setId("tab_alumno_telefono");
     tab_alumno_telefono.setIdCompleto("tab_tabulador:tab_alumno_telefono");
     tab_alumno_telefono.setTabla("yavirac_alum_telefono", "ide_yaltel", 4);
     tab_alumno_telefono.getColumna("ide_yaltel").setNombreVisual("IDE");
     tab_alumno_telefono.getColumna("ide_ysttio").setNombreVisual("TIPO OPERADORA");
     tab_alumno_telefono.getColumna("ide_ysttit").setNombreVisual("TIPO TELEFONO");
     tab_alumno_telefono.getColumna("ide_yaldfe").setNombreVisual("DATO FAMILIAR");
     tab_alumno_telefono.getColumna("numero_yaltel").setNombreVisual("NUMERO DE TELEFONO");
     tab_alumno_telefono.getColumna("activo_yaltel").setNombreVisual("ACTIVO");
     tab_alumno_telefono.getColumna("notificacion_yaltel").setNombreVisual("MODIFICACIÓN TELEFONO");
     tab_alumno_telefono.dibujar();
     PanelTabla pat_panel4=new PanelTabla();
     pat_panel4.setPanelTabla(tab_alumno_telefono);

     tab_alumno_discapacidad.setId("tab_alumno_discapacidad");
     tab_alumno_discapacidad.setIdCompleto("tab_tabulador:tab_alumno_discapacidad");
     tab_alumno_discapacidad.setTabla("yavirac_alum_discapacidad", "ide_yaldis", 5);
     tab_alumno_discapacidad.getColumna("ide_yaldis").setNombreVisual("IDE");
     tab_alumno_discapacidad.getColumna("ide_ystgrd").setNombreVisual("GRADO DISCAPACIDAD");
     tab_alumno_discapacidad.getColumna("ide_ysttid").setNombreVisual("TIPO DISCAPACIDAD");
     tab_alumno_discapacidad.getColumna("numero_conadis_yaldis").setNombreVisual("NÚMERO  DE CARNET CONADIS");
     tab_alumno_discapacidad.getColumna("fecha_emision_yaldis").setNombreVisual("FECHA EMISION CARNET ");
     tab_alumno_discapacidad.getColumna("porcentaje_disca_yaldis").setNombreVisual("PORCENTAJE");
     tab_alumno_discapacidad.getColumna("activo_yaldis").setNombreVisual("ACTIVO");
     tab_alumno_discapacidad.dibujar();
     PanelTabla pat_panel5=new PanelTabla();
     pat_panel5.setPanelTabla(tab_alumno_discapacidad);

     tab_archivo_alumno.setId("tab_archivo_alumno");
     tab_archivo_alumno.setIdCompleto("tab_tabulador:tab_archivo_alumno");
     tab_archivo_alumno.setTabla("yavirac_alum_documento", "ide_yaldoc", 6);
     tab_archivo_alumno.getColumna("ide_yaldoc").setNombreVisual("IDE");
     tab_archivo_alumno.getColumna("ide_ysttia").setNombreVisual("TIPO ARCHIVO");
     tab_archivo_alumno.getColumna("descripcion_yaldoc").setNombreVisual("DESCRIPCION");
     tab_archivo_alumno.getColumna("fecha_yaldoc").setNombreVisual("FECHA");
     tab_archivo_alumno.getColumna("archivo_anexo_yaldoc").setNombreVisual("ARCHIVO ANEXO");
     tab_archivo_alumno.dibujar();
     PanelTabla pat_panel6=new PanelTabla();
     pat_panel6.setPanelTabla(tab_archivo_alumno);

     
     tab_tabulador.agregarTab("DIRECCION", pat_panel2); //Agrega un Tab con el Formulario de Usuarios
     tab_tabulador.agregarTab("TELEFONO", pat_panel4); //Agrega un Tab sin ningun componente
     tab_tabulador.agregarTab("CORREO",pat_panel3);
     tab_tabulador.agregarTab("DISCAPCIDAD",pat_panel5);
     tab_tabulador.agregarTab("DOCUMENTACION",pat_panel6);

     pat_panel1.getMenuTabla().getItem_buscar().setRendered(false);

     Division div_division= new Division();
     div_division.dividir2(pat_panel1,tab_tabulador,"60%","H");//Agrego el Tabulador a una Division
     agregarComponente(div_division);//
     menup.dibujar(1,"DATOS PERSONALES",div_division);  
     
 }
 public void dibujarTablaDatosFamiliares(){
 int_opcion=2;
 tab_dato_familiar.setId("tab_dato_familiar");
 tab_dato_familiar.setTabla("yavirac_alum_dato_fami_alumno", "ide_yaldfa", 7);
 tab_dato_familiar.getColumna("ide_yalgas").setCombo(ser_alumno.getDatosVivienda("true,false"));
 tab_dato_familiar.getColumna("ide_yaltiv").setCombo(ser_alumno.getGasto("true,false"));
 tab_dato_familiar.getColumna("ide_yalocu").setCombo(ser_alumno.getOcupacion("true,false"));
 tab_dato_familiar.getColumna("ide_ystpaf").setCombo(ser_estructura.getParentezcoFamiliar("true,false"));
 tab_dato_familiar.getColumna("ide_ystesc").setCombo(ser_estructura.getEstadoCivil("true,false"));
 tab_dato_familiar.dibujar();
 tab_dato_familiar.setHeader("DATOS FAMILIARES ALUMNO");
     PanelTabla pat_panel2=new PanelTabla();
     pat_panel2.setPanelTabla(tab_dato_familiar);
     pat_panel2.getMenuTabla().getItem_buscar().setRendered(false);

     menup.dibujar(2,"DATOS FAMILIARES ALUMNO",pat_panel2);  
}
 public void dibujarTablaDatosAcademicos(){
 int_opcion=3;
 tab_dato_academico.setId("tab_alumno");
 tab_dato_academico.setTabla("yavirac_alum_dato_academico", "ide_yaldaa", 8);
 tab_dato_academico.setCondicion("ide_yaldaa=-1");
 tab_dato_academico.dibujar();
 tab_dato_academico.setHeader("DATOS ACADEMICOS");
     PanelTabla pat_panel3=new PanelTabla();
     pat_panel3.setPanelTabla(tab_dato_academico);
     pat_panel3.getMenuTabla().getItem_buscar().setRendered(false);
  
 menup.dibujar(3,"DATOS ACADEMICOS",pat_panel3);
}
public void dibujarTablaDatosLaborales(){
 int_opcion=4;
 tab_dato_laboral.setId("tab_dato_laboral");
 tab_dato_laboral.setTabla("yavirac_alum_dato_laboral", "ide_yaldal", 9);
 tab_dato_laboral.setCondicion("ide_yaldal=-1");
 tab_dato_laboral.dibujar();
 tab_dato_laboral.setHeader("DATOS LABORALES");
     PanelTabla pat_panel=new PanelTabla();
     pat_panel.setPanelTabla(tab_dato_laboral);
     pat_panel.getMenuTabla().getItem_buscar().setRendered(false);

 
 menup.dibujar(4,"DATOS LABORALES",pat_panel);
}
public void dibujarTablaDatosMedicos(){
 int_opcion=5;
 tab_dato_medico.setId("tab_dato_medico");
 tab_dato_medico.setTabla("yavirac_alum_dato_medico", "ide_yaldam", 10);
 tab_dato_medico.setCondicion("ide_yaldam=-1");
 tab_dato_medico.dibujar();
 tab_dato_medico.setHeader("DATOS MEDICOS");
     PanelTabla pat_panel4=new PanelTabla();
     pat_panel4.setPanelTabla(tab_dato_medico);
     pat_panel4.getMenuTabla().getItem_buscar().setRendered(false);

 menup.dibujar(5,"DATOS MEDICOS",pat_panel4);
}
public void dibujarTablaAficiones(){
 int_opcion=6;
 tab_aficiones.setId("tab_aficiones");
 tab_aficiones.setTabla("yavirac_alum_aficion", "ide_yalafi", 11);
 tab_aficiones.setCondicion("ide_yalafi=-1");
 tab_aficiones.dibujar();
 tab_aficiones.setHeader("AFICIONES");
     PanelTabla pat_panel5=new PanelTabla();
     pat_panel5.setPanelTabla(tab_aficiones);
     pat_panel5.getMenuTabla().getItem_buscar().setRendered(false);

 menup.dibujar(6,"AFICIONES",pat_panel5);
}
public void limpiar() {
        aut_alumno.limpiar();
        menup.limpiar();
    }

    @Override
    public void insertar() {
      if(int_opcion==1){
           if(tab_alumno.isFocus()){
            tab_alumno.insertar();
            }
           else if(tab_alumno_direccion.isFocus()){
               tab_alumno_direccion.insertar();
           }
      }  

    }

    @Override
    public void guardar() {
        if(int_opcion==1){
            if(tab_alumno.isFocus()){
            tab_alumno.guardar();
            }
             else if(tab_alumno_direccion.isFocus()){
               tab_alumno_direccion.guardar();
           }
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

    