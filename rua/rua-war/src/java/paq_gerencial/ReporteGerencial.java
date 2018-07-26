/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_gerencial;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Imagen;
import framework.componentes.PanelTabla;
import framework.componentes.Radio;
import framework.componentes.Reporte;
import framework.componentes.SeleccionCalendario;
import framework.componentes.SeleccionFormatoReporte;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import paq_asistencia.ejb.ServicioAsistencia;
import sistema.aplicacion.Pantalla;
import framework.componentes.Panel;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;

/**
 * 
 */
public class ReporteGerencial extends Pantalla {
    private Reporte rep_reporte=new Reporte();
    private SeleccionFormatoReporte sel_rep=new SeleccionFormatoReporte();  
    private SeleccionTabla sel_tab_carrera = new SeleccionTabla();
    private SeleccionTabla sel_tab_periodo = new SeleccionTabla();
    private Panel panOpcion = new Panel();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    public ReporteGerencial(){
        
        bar_botones.getBot_insertar().setRendered(false);
        bar_botones.getBot_guardar().setRendered(false);
        bar_botones.getBot_eliminar().setRendered(false);
        bar_botones.quitarBotonsNavegacion();

       rep_reporte.setId("rep_reporte");
       rep_reporte.getBot_aceptar().setMetodo("aceptarReporte");
       agregarComponente(rep_reporte);
       bar_botones.agregarReporte();
       sel_rep.setId("sel_rep");
       agregarComponente(sel_rep);    
       
       
        //Diseño de fondo reporte //
        Imagen ImaReportes = new Imagen();
        ImaReportes.setValue("imagenes/ModAsistencia/Asistencia.png");
        
        panOpcion.setId("pan_opcion");
        panOpcion.setTransient(true);
        panOpcion.setHeader("REPORTES GERENCIALES");
        panOpcion.setStyle("font-size:10px;color:black;text-align:center;");
        
         Grid grid_pant = new Grid();
        grid_pant.setColumns(1);
        grid_pant.setStyle("text-align:center;position:absolute;top:210px;left:535px;");
        Etiqueta eti_encab = new Etiqueta();
        grid_pant.getChildren().add(ImaReportes);
        Boton bot_imprimir = new Boton();
        bot_imprimir.setValue("Imprimir Reporte");
        bot_imprimir.setIcon("ui-icon-print");
        bot_imprimir.setMetodo("abrirListaReportes");
        bar_botones.agregarBoton(bot_imprimir);
        grid_pant.getChildren().add(bot_imprimir);
        agregarComponente(grid_pant);
        panOpcion.getChildren().add(grid_pant);
        agregarComponente(panOpcion);
        
        
        
        
         //PANTALLA CONSULTA LA MENSIONES
            sel_tab_carrera.setId("sel_tab_carrera");
            sel_tab_carrera.setWidth("80%");
            sel_tab_carrera.setTitle("SELECCIONE LAS CARRERAS");
            sel_tab_carrera.getBot_aceptar().setMetodo("aceptarReporte");
            sel_tab_carrera.setSeleccionTabla(ser_estructura.getMension(), "ide_ystmen"); 
            agregarComponente(sel_tab_carrera);

         //PANTALLA CONSULTA PERIODO ACADEMICO
            sel_tab_periodo.setId("sel_tab_periodo");
            sel_tab_periodo.setWidth("80%");
            sel_tab_periodo.setTitle("SELECCIONE EL/LOS PERIODOS ACADEMICOS QUE DESEA CONSULTAR");
            sel_tab_periodo.getBot_aceptar().setMetodo("aceptarReporte");
            sel_tab_periodo.setSeleccionTabla(ser_estructura.getPeriodoAcademico("true,false"), "ide_ystpea"); 
            agregarComponente(sel_tab_periodo);            
        
    }
public void abrirListaReportes() {
// TODO Auto-generated method stub
rep_reporte.dibujar();
}

String str_seleccionado_periodo="";
public void aceptarReporte() {
    if (rep_reporte.getReporteSelecionado().equals("Inscripcion Carreras y Genero")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Inscripcion Carreras y Periodo Academico")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Inscritos vs Matriculados")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Matriculas Carreras y Genero")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                 utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Matriculas por Periodo Academico")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                 utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Porcentaje de Asistencia Alumnos")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                 utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
    else if (rep_reporte.getReporteSelecionado().equals("Porcentaje de Asistencia Funcionarios")){
                
        if(rep_reporte.isVisible()){
                rep_reporte.cerrar();
                sel_tab_periodo.dibujar();
                utilitario.addUpdate("rep_reporte,sel_tab_periodo");
        }
        else if(sel_tab_periodo.isVisible()){ 
                str_seleccionado_periodo = sel_tab_periodo.getSeleccionados();
                sel_tab_periodo.cerrar();
                sel_tab_carrera.dibujar();
                 utilitario.addUpdate("rep_reporte,sel_tab_carrera");
        }
        else if(sel_tab_carrera.isVisible()){ 
                String str_seleccionado_carrera = sel_tab_carrera.getSeleccionados();
                Map map_parametros = new HashMap();
                map_parametros.put("nombre", utilitario.getVariable("NICK"));
                map_parametros.put("pmension", str_seleccionado_carrera);
                map_parametros.put("pperiodo", str_seleccionado_periodo);
                sel_rep.setSeleccionFormatoReporte(map_parametros, rep_reporte.getPath());
                sel_tab_carrera.cerrar();
                sel_rep.dibujar();
        }
        else{
            utilitario.agregarMensajeInfo("No se puede continuar", "No ha selccionado ningun registro");
        }
    
    
    }
}    
    @Override
    public void insertar() {
    }

    @Override
    public void guardar() {

    }

    @Override
    public void eliminar() {
       
    }

    public Reporte getRep_reporte() {
        return rep_reporte;
    }

    public void setRep_reporte(Reporte rep_reporte) {
        this.rep_reporte = rep_reporte;
    }

    public SeleccionFormatoReporte getSel_rep() {
        return sel_rep;
    }

    public void setSel_rep(SeleccionFormatoReporte sel_rep) {
        this.sel_rep = sel_rep;
    }
   public SeleccionTabla getSel_tab_carrera() {
        return sel_tab_carrera;
    }

    public void setSel_tab_carrera(SeleccionTabla sel_tab_carrera) {
        this.sel_tab_carrera = sel_tab_carrera;
    }

    public SeleccionTabla getSel_tab_periodo() {
        return sel_tab_periodo;
    }

    public void setSel_tab_periodo(SeleccionTabla sel_tab_periodo) {
        this.sel_tab_periodo = sel_tab_periodo;
    }

 
}
