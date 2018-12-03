/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_nota.ejb.ServicioNotas;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author usuario
 */
public class NotaPeriodoActividad extends Pantalla{
    
    Tabla tab_periodo_evaluacion = new Tabla();
    Tabla tab_periodo_actividad_eva = new Tabla();
    private Combo com_periodo_academico = new Combo();
    
     @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
  
    public NotaPeriodoActividad(){
        
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
       
        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADÉMICO"));
        bar_botones.agregarComponente(com_periodo_academico);
        
        
        tab_periodo_evaluacion.setId("tab_periodo_evaluacion");  // todo objeto instanciado poner id 
        tab_periodo_evaluacion.setTabla("yavirac_nota_periodo_evaluacio", "ide_ynopee", 1);    // nom bdd
        tab_periodo_evaluacion.setCondicion("ide_ystpea=-1");
        tab_periodo_evaluacion.getColumna("ide_ynotie").setCombo(ser_notas.getTipoEvaluacion("true,false"));
        tab_periodo_evaluacion.agregarRelacion(tab_periodo_actividad_eva);
        //renombrar etiquetas
        tab_periodo_evaluacion.getColumna("ide_ynopee").setNombreVisual("CÓDIGO");
        tab_periodo_evaluacion.getColumna("ide_ynotie").setNombreVisual("TIPO DE EVALUACIÓN");
        //*****************
        tab_periodo_evaluacion.dibujar();

        PanelTabla pa_periodoevaluacion = new PanelTabla();
        pa_periodoevaluacion.setId("pa_periodoevaluacion"); // nombre de i
        pa_periodoevaluacion.setPanelTabla(tab_periodo_evaluacion);
        
        tab_periodo_actividad_eva.setId("tab_periodo_actividad_eva");
        //tab_periodo_actividad_eva.setIdCompleto("tab_tabulador:tab_periodo_actividad_eva");
        tab_periodo_actividad_eva.setTabla("yavirac_nota_periodo_activ_eva","ide_ynopae", 1);
        tab_periodo_actividad_eva.dibujar();
        
        PanelTabla pa_periodo_actividad_eva = new PanelTabla();
        pa_periodo_actividad_eva.setId("pa_periodo_actividad_eva"); // nombre de i
        pa_periodo_actividad_eva.setPanelTabla(tab_periodo_actividad_eva);
        
        ///// tabuladores
        Division div_periodoevaluacion = new Division();
        div_periodoevaluacion.dividir2(pa_periodoevaluacion, pa_periodo_actividad_eva, "40%", "h");
        agregarComponente(div_periodoevaluacion);
        
    }
     public void filtroComboPeriodoAcademnico(){
        
        tab_periodo_evaluacion.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_periodo_evaluacion.ejecutarSql();
        utilitario.addUpdate("tab_hora_definicion");
        
    }

    @Override
    public void insertar() {
        if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.insertar();
        } else if (tab_periodo_actividad_eva.isFocus()) {
            tab_periodo_actividad_eva.insertar();
        } 
    }

    @Override
    public void guardar() {
        if (tab_periodo_evaluacion.guardar()){
            if (tab_periodo_actividad_eva.guardar())
            {
                guardarPantalla();
            }
        }
    }

    @Override
    public void eliminar() {
         if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.eliminar();
        } else if (tab_periodo_actividad_eva.isFocus()) {
            tab_periodo_actividad_eva.eliminar();
        } 
    }

    public Tabla getTab_periodo_evaluacion() {
        return tab_periodo_evaluacion;
    }

    public void setTab_periodo_evaluacion(Tabla tab_periodo_evaluacion) {
        this.tab_periodo_evaluacion = tab_periodo_evaluacion;
    }

    public Tabla getTab_periodo_actividad_eva() {
        return tab_periodo_actividad_eva;
    }

    public void setTab_periodo_actividad_eva(Tabla tab_periodo_actividad_eva) {
        this.tab_periodo_actividad_eva = tab_periodo_actividad_eva;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }
    
    
}
