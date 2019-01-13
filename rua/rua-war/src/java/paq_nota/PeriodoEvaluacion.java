/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_nota.ejb.ServicioNotas;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author usuario
 */
public class PeriodoEvaluacion extends Pantalla {

    private Combo com_periodo_academico = new Combo();
    private Boton bot_clean = new Boton();
    private Tabla tab_periodo_evaluacion = new Tabla();
    private Tabla tab_actividad_evaluacion = new Tabla();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);

    public PeriodoEvaluacion() {

        com_periodo_academico.setId("com_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
        bar_botones.agregarComponente(com_periodo_academico);
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");

        bot_clean.setIcon("ui-icon-cancel");
        bot_clean.setTitle("Limpiar");
        bot_clean.setMetodo("limpiar");
        bar_botones.agregarComponente(bot_clean);

        //TABLA 1
        tab_periodo_evaluacion.setId("tab_periodo_evaluacion");
        tab_periodo_evaluacion.setTabla("yavirac_nota_periodo_evaluacio ", "ide_ynopee", 1);
        tab_periodo_evaluacion.setCondicion("ide_ystpea=-1");
        tab_periodo_evaluacion.agregarRelacion(tab_actividad_evaluacion);
        tab_periodo_evaluacion.getColumna("ide_ynotie").setCombo(ser_notas.getTipoEvaluacion("true,false"));
        tab_periodo_evaluacion.getColumna("ide_ynopee").setNombreVisual("CÓDIGO");
        tab_periodo_evaluacion.getColumna("ide_ynotie").setNombreVisual("TIPO DE EVALUACIÓN");
        tab_periodo_evaluacion.getColumna("ide_ystpea").setVisible(false);
        tab_periodo_evaluacion.dibujar();

        PanelTabla pa_periodo_evaluacion = new PanelTabla();
        pa_periodo_evaluacion.setId("pa_periodo_evaluacion");
        pa_periodo_evaluacion.setPanelTabla(tab_periodo_evaluacion);

        // TABLA 2
        tab_actividad_evaluacion.setId("tab_actividad_evaluacion");
        tab_actividad_evaluacion.setTabla("yavirac_nota_periodo_activ_eva", "ide_ynopae", 2);
        tab_actividad_evaluacion.getColumna("ide_ynoace").setCombo(ser_notas.getActividadEvaluacion("true,false"));
        tab_actividad_evaluacion.getColumna("ide_ynopae").setNombreVisual("CÓDIGO");
        tab_actividad_evaluacion.getColumna("ide_ynoace").setNombreVisual("ACTIVIDAD EVALUACIÓN");
        tab_actividad_evaluacion.getColumna("orden_ynopae").setNombreVisual("ORDEN");
        tab_actividad_evaluacion.getColumna("activo_ynopae").setNombreVisual("ACTIVO");
        tab_actividad_evaluacion.dibujar();

        PanelTabla pa_actividad_evaluacion = new PanelTabla();
        pa_actividad_evaluacion.setId("pa_actividad_evaluacion"); // nombre de i
        pa_actividad_evaluacion.setPanelTabla(tab_actividad_evaluacion);

        Division div_periodo_evaluacion = new Division();
        div_periodo_evaluacion.dividir2(pa_periodo_evaluacion, pa_actividad_evaluacion, "50%", "h");
        agregarComponente(div_periodo_evaluacion);

    }

    public void filtroComboPeriodoAcademico() {

        tab_periodo_evaluacion.setCondicion("ide_ystpea=" + com_periodo_academico.getValue().toString());
        tab_periodo_evaluacion.ejecutarSql();
        tab_actividad_evaluacion.setCondicion("ide_ynopee=" + tab_periodo_evaluacion.getCampoPrimaria());
        tab_actividad_evaluacion.ejecutarSql();
        utilitario.addUpdate("tab_periodo_evaluacion,tab_actividad_evaluacion");

    }

    public void limpiar() {
        tab_periodo_evaluacion.limpiar();
        com_periodo_academico.setValue("null");
        utilitario.addUpdate("tab_periodo_evaluacion,com_periodo_academico,actividad_evaluacion");
    }

    @Override
    public void insertar() {
        if (com_periodo_academico.getValue() == null) {

            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Academico que desea generar");
            return;
        } else if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.insertar();
            tab_periodo_evaluacion.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
            utilitario.addUpdate("tab_periodo_evaluacion");
        } else if (tab_actividad_evaluacion.isFocus()) {
            tab_actividad_evaluacion.insertar();
        }

    }

    @Override
    public void guardar() {
        if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.guardar();
        } else if (tab_actividad_evaluacion.isFocus()) {
            tab_actividad_evaluacion.guardar();
        }
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.eliminar();
        } else if (tab_actividad_evaluacion.isFocus()) {
            tab_actividad_evaluacion.eliminar();
        }
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }

    public Boton getBot_clean() {
        return bot_clean;
    }

    public void setBot_clean(Boton bot_clean) {
        this.bot_clean = bot_clean;
    }

    public Tabla getTab_periodo_evaluacion() {
        return tab_periodo_evaluacion;
    }

    public void setTab_periodo_evaluacion(Tabla tab_periodo_evaluacion) {
        this.tab_periodo_evaluacion = tab_periodo_evaluacion;
    }

    public Tabla getTab_actividad_evaluacion() {
        return tab_actividad_evaluacion;
    }

    public void setTab_actividad_evaluacion(Tabla tab_actividad_evaluacion) {
        this.tab_actividad_evaluacion = tab_actividad_evaluacion;
    }

}