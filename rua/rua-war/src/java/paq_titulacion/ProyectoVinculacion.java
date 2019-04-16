/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_personal.ejb.ServicioPersonal;
import paq_titulacion.ejb.ServicioTitulacion;
import sistema.aplicacion.Pantalla;
/**
 *
 * @author usuario
 */
public class ProyectoVinculacion extends Pantalla {
    
     private Tabla tab_tabla = new Tabla();
     private Combo com_periodo_academico = new Combo();
     @EJB
    private final ServicioEstructuraOrganizacional ser_periodoacademico = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
  private final ServicioTitulacion ser_titulacion = (ServicioTitulacion) utilitario.instanciarEJB(ServicioTitulacion.class);
    @EJB
  private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
 
     public ProyectoVinculacion() {
        
                 com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_periodoacademico.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");
        
        
        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADEMICO"));
        bar_botones.agregarComponente(com_periodo_academico);
         
        tab_tabla.setId("tab_tabla");
        tab_tabla.setTabla("yavirac_titu_proyecto", "ide_ytipro", 1);
        
        tab_tabla.setTipoFormulario(true);
        tab_tabla.getGrid().setColumns(6);
        tab_tabla.getColumna("ide_ytring ").setCombo(ser_titulacion.getIngreso());
        tab_tabla.getColumna("ide_ytiviv").setCombo(ser_titulacion.getViabilidad());
        tab_tabla.getColumna("ide_ystpea").setCombo(ser_titulacion.getPeriodoAcademico());     
        tab_tabla.getColumna("ide_ytitiv").setCombo(ser_titulacion.getTiposVinculacion());
         
            
        
        tab_tabla.getColumna("ide_ytipro").setNombreVisual("CODIGO DE PROYECTO");
        tab_tabla.getColumna("fecha_registro_ytipro").setNombreVisual("FECHA DE REGISTRO");
        tab_tabla.getColumna("objeto_proyecto_ytipro").setNombreVisual("OBJETO DEL PROYECTO");
        tab_tabla.getColumna("detalle_proyecto_ytipro").setNombreVisual("NOMBRE DEL PROYECTO");
        tab_tabla.getColumna("fecha_inicio_ytipro").setNombreVisual("FECHA DE INICIO");
        tab_tabla.getColumna("fecha_fin_ytipro").setNombreVisual("FECHA FINAL");
        tab_tabla.getColumna("ide_ypedpe").setNombreVisual("ELEBORADO POR");
        tab_tabla.getColumna("yav_ide_ypedpe").setNombreVisual("REVISADO POR");
        tab_tabla.getColumna("yav_ide_ypedpe2").setNombreVisual("APROBADO POR");
        tab_tabla.getColumna("ide_ystmen").setNombreVisual("CARRERA");
        tab_tabla.getColumna("ide_ytiemp"). setNombreVisual("EMPRESA");
        tab_tabla.getColumna("ide_ystdip").setNombreVisual("PROVINCIA");
        tab_tabla.getColumna("yav_ide_ystdip").setNombreVisual("CANTON");
        tab_tabla.getColumna("yav_ide_ystdip2").setNombreVisual("PARROQUIA"); 
        tab_tabla.getColumna("ide_ystpea").setVisible(false);
        tab_tabla.getColumna("ide_ytiviv").setVisible(false);
        tab_tabla.getColumna("ide_ystmen").setCombo(ser_periodoacademico.getMension());
        tab_tabla.getColumna("ide_ytiemp").setCombo(ser_titulacion.getDatoEmpresa());
        tab_tabla.getColumna("ide_ytiemp").setAutoCompletar();
        tab_tabla.getColumna("ide_ystmen").setAutoCompletar();
        tab_tabla.getColumna("ide_ytiint").setCombo(ser_titulacion.getIntervaloTiempo());
        tab_tabla.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tabla.getColumna("yav_ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tabla.getColumna("yav_ide_ypedpe2").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tabla.getColumna("ide_ypedpe").setAutoCompletar();
        tab_tabla.getColumna("yav_ide_ypedpe").setAutoCompletar();
        tab_tabla.getColumna("yav_ide_ypedpe2").setAutoCompletar();
        tab_tabla.getColumna("ide_ystdip").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tabla.getColumna("yav_ide_ystdip").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tabla.getColumna("yav_ide_ystdip2").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_tabla.getColumna("ide_ystdip").setAutoCompletar();
        tab_tabla.getColumna("yav_ide_ystdip").setAutoCompletar();
        tab_tabla.getColumna("yav_ide_ystdip2").setAutoCompletar();             
        tab_tabla.dibujar();

        PanelTabla pat_panel = new PanelTabla();
        pat_panel.setPanelTabla(tab_tabla);
        Division div_division = new Division();
        div_division.dividir1(pat_panel);
        agregarComponente(div_division);
     }
        public void filtroComboPeriodoAcademico(){
         tab_tabla.setCondicion("ide_ystpea="+com_periodo_academico.getValue());
         tab_tabla.ejecutarSql();
         //tab_anexo_libreta.ejecutarValorForanea(tab_libreta_practica.getValorSeleccionado());
         //tab_horario_practica.ejecutarValorForanea(tab_libreta_practica.getValorSeleccionado());
         utilitario.addUpdate("tab_tabla");
        }
    @Override
    public void insertar() {
         if(com_periodo_academico.getValue()== null){
        utilitario.agregarMensajeError("ERROR","Seleccione el Periodo Academico");
        return;
        }
        else{
             
            tab_tabla.insertar();
            tab_tabla.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
         }
    }

    @Override
    public void guardar() {
        tab_tabla.guardar();
        
        guardarPantalla();
    }

    @Override
    public void eliminar() {
         tab_tabla.eliminar();
    }
    public Tabla getTab_tabla() {
        return tab_tabla;
    }

    public void setTab_tabla(Tabla tab_tabla) {
        this.tab_tabla = tab_tabla;
    } 
}
