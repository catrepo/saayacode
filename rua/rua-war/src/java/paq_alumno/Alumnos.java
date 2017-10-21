/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_alumno;

import framework.componentes.MenuPanel;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author RICARDO COLLAHUAZO
 */
 public class Alumnos extends Pantalla {
 private MenuPanel menup=new MenuPanel();
 private int int_opcion=0;
 private Tabla tab_alumno=new Tabla();

    public Tabla getTab_alumno() {
        return tab_alumno;
    }

    public void setTab_alumno(Tabla tab_alumno) {
        this.tab_alumno = tab_alumno;
    }
 public Alumnos(){
 menup.setMenuPanel("FICHA DEL ESTUDIANTE", "22%");
 menup.agregarItem ("DATOS PERSONALES", "dibujarTablaDatoPersonal", "ui-icon-contact");
 menup.agregarItem ("DATOS FAMILIARES", "dibujarTablaDatosFamiliares", "ui-icon-document");
 menup.agregarItem ("DATOS ACADEMICOS", "dibujarTablaDatosAcademicos", "ui-icon-document");
 menup.agregarItem ("DATOS LABORALES", "dibujarTablaDatosLaborales", "ui-icon-document"); 
 menup.agregarItem ("DATOS MEDICOS", "dibujarTablaDatosMedicos", "ui-icon-document");
 menup.agregarItem ("AFICIONES", "dibujarTablaAficiones", "ui-icon-document");
 agregarComponente(menup);
 
 }

 public void dibujarTablaDatoPersonal(){
 int_opcion=1;
 tab_alumno =new Tabla();
 tab_alumno.setId("tab_alumno");
 tab_alumno.setTipoFormulario(true);
 tab_alumno.setTabla("yavirac_alum_dato_personal", "ide_yaldap", 1);
 tab_alumno.getColumna("Nombre").setUnico(true);
 tab_alumno.getColumna("fecha de nacimiento").setLectura(true);
 tab_alumno.setHeader("DATOS PERSONALES");
 tab_alumno.dibujar();
     PanelTabla pat_panel1=new PanelTabla();
     pat_panel1.setPanelTabla(tab_alumno);
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

    @Override
    public void insertar() {
      if(int_opcion==1){
          tab_alumno.insertar();
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
   } 

    