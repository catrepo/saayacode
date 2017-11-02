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
 Tabulador tab_tabulador = new Tabulador();
 tab_tabulador.setId("tab_tabulador");
 tab_alumno =new Tabla();
 tab_alumno.setId("tab_alumno");
 tab_alumno.setIdCompleto("tab_tabulador:tab_alumno");
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
     tab_tabulador.agregarTab("DIRECCION", pat_panel1); //Agrega un Tab con el Formulario de Usuarios
     tab_tabulador.agregarTab("TELEFONO", null); //Agrega un Tab sin ningun componente
     tab_tabulador.agregarTab("CORREO",null);
     pat_panel1.getMenuTabla().getItem_buscar().setRendered(false);
     pat_panel1.getMenuTabla().getItem_insertar().setRendered(false);
     pat_panel1.getMenuTabla().getItem_eliminar().setRendered(false);
     pat_panel1.getMenuTabla().getItem_actualizar().setRendered(false);
     Division div_division= new Division();
     div_division.dividir1(tab_tabulador);//Agrego el Tabulador a una Division
     agregarComponente(div_division);//
     menup.dibujar(1,"DATOS PERSONALES",pat_panel1);  
     
 }
 public void dibujarTablaDatosFamiliares(){
 int_opcion=2;
 tab_alumno =new Tabla();
 tab_alumno.setId("tab_alumno");
 tab_alumno.setTipoFormulario(true);
 tab_alumno.setTabla("yavirac_alum_dato_fami_alumno", "ide_yaldfa", 1);
 tab_alumno.setCondicion("ide_yaldfa=-1");
 tab_alumno.getColumna("ide_yalgas").setCombo(ser_alumno.getDatosVivienda("true,false"));
 tab_alumno.getColumna("ide_yaltiv").setCombo(ser_alumno.getGasto("true,false"));
 tab_alumno.getColumna("ide_yalocu").setCombo(ser_alumno.getOcupacion("true,false"));
 tab_alumno.getColumna("ide_ystpaf").setCombo(ser_estructura.getParentezcoFamiliar("true,false"));
 tab_alumno.getColumna("ide_ystesc").setCombo(ser_estructura.getEstadoCivil("true,false"));
 tab_alumno.setMostrarNumeroRegistros(false);
 tab_alumno.getGrid().setColumns(4);
 tab_alumno.dibujar();
 tab_alumno.insertar();
 tab_alumno.setHeader("DATOS FAMILIARES ALUMNO");
     PanelTabla pat_panel2=new PanelTabla();
     pat_panel2.setPanelTabla(tab_alumno);
     pat_panel2.getMenuTabla().getItem_buscar().setRendered(false);
     pat_panel2.getMenuTabla().getItem_insertar().setRendered(false);
     pat_panel2.getMenuTabla().getItem_eliminar().setRendered(false);
     pat_panel2.getMenuTabla().getItem_actualizar().setRendered(false);

     menup.dibujar(2,"DATOS FAMILIARES ALUMNO",pat_panel2);  
}
 public void dibujarTablaDatosAcademicos(){
 int_opcion=3;
 tab_alumno =new Tabla();
 tab_alumno.setId("tab_alumno");
 tab_alumno.setTipoFormulario(true);
 tab_alumno.setTabla("yavirac_alum_dato_academico", "ide_yaldaa", 1);
 tab_alumno.setCondicion("ide_yaldaa=-1");
 tab_alumno.setMostrarNumeroRegistros(false);
 tab_alumno.getGrid().setColumns(4);
 tab_alumno.dibujar();
 tab_alumno.insertar();
 tab_alumno.setHeader("DATOS ACADEMICOS");
     PanelTabla pat_panel3=new PanelTabla();
     pat_panel3.setPanelTabla(tab_alumno);
     pat_panel3.getMenuTabla().getItem_buscar().setRendered(false);
     pat_panel3.getMenuTabla().getItem_insertar().setRendered(false);
     pat_panel3.getMenuTabla().getItem_eliminar().setRendered(false);
     pat_panel3.getMenuTabla().getItem_actualizar().setRendered(false);
 
 menup.dibujar(3,"DATOS ACADEMICOS",pat_panel3);
}
public void dibujarTablaDatosLaborales(){
 int_opcion=4;
 tab_alumno =new Tabla();
 tab_alumno.setId("tab_alumno");
 tab_alumno.setTipoFormulario(true);
 tab_alumno.setTabla("yavirac_alum_dato_laboral", "ide_yaldal", 1);
 tab_alumno.setCondicion("ide_yaldal=-1");
 tab_alumno.setMostrarNumeroRegistros(false);
 tab_alumno.getGrid().setColumns(4);
 tab_alumno.dibujar();
 tab_alumno.insertar();
 tab_alumno.setHeader("DATOS LABORALES");
     PanelTabla pat_panel=new PanelTabla();
     pat_panel.setPanelTabla(tab_alumno);
     pat_panel.getMenuTabla().getItem_buscar().setRendered(false);
     pat_panel.getMenuTabla().getItem_insertar().setRendered(false);
     pat_panel.getMenuTabla().getItem_eliminar().setRendered(false);
     pat_panel.getMenuTabla().getItem_actualizar().setRendered(false);
 
 menup.dibujar(4,"DATOS LABORALES",pat_panel);
}
public void dibujarTablaDatosMedicos(){
 int_opcion=5;
 tab_alumno =new Tabla();
 tab_alumno.setId("tab_alumno");
 tab_alumno.setTipoFormulario(true);
 tab_alumno.setTabla("yavirac_alum_dato_medico", "ide_yaldam", 1);
 tab_alumno.setCondicion("ide_yaldam=-1");
 tab_alumno.setMostrarNumeroRegistros(false);
 tab_alumno.getGrid().setColumns(4);
 tab_alumno.dibujar();
 tab_alumno.insertar();
 tab_alumno.setHeader("DATOS MEDICOS");
     PanelTabla pat_panel4=new PanelTabla();
     pat_panel4.setPanelTabla(tab_alumno);
     pat_panel4.getMenuTabla().getItem_buscar().setRendered(false);
     pat_panel4.getMenuTabla().getItem_insertar().setRendered(false);
     pat_panel4.getMenuTabla().getItem_eliminar().setRendered(false);
     pat_panel4.getMenuTabla().getItem_actualizar().setRendered(false);
 menup.dibujar(5,"DATOS MEDICOS",pat_panel4);
}
public void dibujarTablaAficiones(){
 int_opcion=6;
 tab_alumno =new Tabla();
 tab_alumno.setId("tab_alumno");
 tab_alumno.setTipoFormulario(true);
 tab_alumno.setTabla("yavirac_alum_aficion", "ide_yalafi", 1);
 tab_alumno.setCondicion("ide_yalafi=-1");
 tab_alumno.setMostrarNumeroRegistros(false);
 tab_alumno.getGrid().setColumns(4);
 tab_alumno.dibujar();
 tab_alumno.insertar();
 tab_alumno.setHeader("AFICIONES");
     PanelTabla pat_panel5=new PanelTabla();
     pat_panel5.setPanelTabla(tab_alumno);
     pat_panel5.getMenuTabla().getItem_buscar().setRendered(false);
     pat_panel5.getMenuTabla().getItem_insertar().setRendered(false);
     pat_panel5.getMenuTabla().getItem_eliminar().setRendered(false);
     pat_panel5.getMenuTabla().getItem_actualizar().setRendered(false);
 menup.dibujar(6,"AFICIONES",pat_panel5);
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

    