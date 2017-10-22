/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_alumno;

import framework.aplicacion.Columna;
import framework.componentes.AutoCompletar;
import framework.componentes.Etiqueta;
import framework.componentes.MenuPanel;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
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
 menup.agregarItem ("DATOS ACADEMICOS", "dibujarTablaDatosAcademicos", "ui-icon-document");
 menup.agregarItem ("DATOS LABORALES", "dibujarTablaDatosLaborales", "ui-icon-document"); 
 menup.agregarItem ("DATOS MEDICOS", "dibujarTablaDatosMedicos", "ui-icon-document");
 menup.agregarItem ("AFICIONES", "dibujarTablaAficiones", "ui-icon-document");
 agregarComponente(menup);
 
 }
public void selecionoAutocompletar(SelectEvent evt){
    aut_alumno.onSelect(evt);
utilitario.agregarMensaje("VALOR", aut_alumno.getValor()); 
utilitario.agregarMensaje("NOMBRE", aut_alumno.getValorArreglo(1));     
}
   
    public AutoCompletar getAut_alumno() {
        return aut_alumno;
    }

    public void setAut_alumno(AutoCompletar aut_alumno) {
        this.aut_alumno = aut_alumno;
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
 
 tab_alumno.setMostrarNumeroRegistros(false);
 tab_alumno.getGrid().setColumns(4);
 tab_alumno.dibujar();
 tab_alumno.insertar();
 tab_alumno.setHeader("DATOS PERSONALES");
     PanelTabla pat_panel1=new PanelTabla();
     pat_panel1.setPanelTabla(tab_alumno);
     pat_panel1.getMenuTabla().getItem_buscar().setRendered(false);
     pat_panel1.getMenuTabla().getItem_insertar().setRendered(false);
     pat_panel1.getMenuTabla().getItem_eliminar().setRendered(false);
     pat_panel1.getMenuTabla().getItem_actualizar().setRendered(false);

     menup.dibujar(1,"DATOS PERSONALES",pat_panel1);     
 }
 public void dibujarTablaDatosFamiliares(){
 int_opcion=2;
 menup.dibujar(2,"DATOS FAMILIARES",null);
}
 public void dibujarTablaDatosAcademicos(){
 int_opcion=3;
 menup.dibujar(3,"DATOS ACADEMICOS",null);
}
public void dibujarTablaDatosLaborales(){
 int_opcion=4;
 menup.dibujar(4,"DATOS LABORALES",null);
}
public void dibujarTablaDatosMedicos(){
 int_opcion=5;
 menup.dibujar(5,"DATOS MEDICOS",null);
}
public void dibujarTablaAficiones(){
 int_opcion=6;
 menup.dibujar(6,"AFICIONES",null);
}
public void limpiar() {
        aut_alumno.limpiar();
        menup.limpiar();
    }

    @Override
    public void insertar() {
      if(menup.getOpcion()==1){
          
          dibujarTablaDatoPersonal();
      }  
      if (menup.getOpcion()==1){
          aut_alumno.limpiar();
          tab_alumno.dibujar();
      }
    }

    @Override
    public void guardar() {
        if(int_opcion==1){
            tab_alumno.guardar();
            guardarPantalla();
        }
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
   } 

    