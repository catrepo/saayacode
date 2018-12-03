/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.SeleccionTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import javax.ejb.EJB;
import paq_nota.ejb.ServicioNotas;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ITSY
 */
public class PeriodoAcademico extends Pantalla {

    Tabla tab_periodo_academic = new Tabla(); // importar tabla 
    Tabla tab_periodo_evaluacion = new Tabla();
    Tabla tab_periodo_actividad_eva = new Tabla();
    Tabla tab_nota_final = new Tabla();
    private SeleccionTabla sel_periodo_acedemico = new SeleccionTabla();

    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);

    private static final String ORIGINAL
            = "ÁáÉéÍíÓóÚúÑñÜü";
    private static final String REPLACEMENT
            = "AaEeIiOoUuNnUu";

    public static String stripAccents(String str) {
        if (str == null) {
            return null;
        }
        char[] array = str.toCharArray();
        for (int index = 0; index < array.length; index++) {
            int pos = ORIGINAL.indexOf(array[index]);
            if (pos > -1) {
                array[index] = REPLACEMENT.charAt(pos);
            }
        }
        return new String(array);
    }

    public void generartabla() {
      // System.err.println("1° metodo"+tab_periodo_academic.getValor(tab_periodo_academic.getFilaActual(), "tabla_creada_ystpea"));
    if(tab_periodo_academic.getValor(tab_periodo_academic.getFilaActual(),"tabla_creada_ystpea").equals("true")){
        utilitario.agregarNotificacionInfo("Mensajes", "Existe la tabla");
    }else {
        utilitario.getConexion().ejecutarSql(ser_notas.gettabnotamaestro(tab_periodo_academic.getValor("tabla_notas_ystpea")));
        tab_periodo_academic.setValor("tabla_creada_ystpea", "true");
        tab_periodo_academic.modificar(tab_periodo_academic.getFilaActual());
        tab_periodo_academic.guardar();
        guardarPantalla();
        utilitario.addUpdateTabla(tab_periodo_academic, "tabla_creada_ystpea","");
        utilitario.agregarMensaje("Mensaje", "Tabla creada exitosamente");
    }
        //
    }

    public PeriodoAcademico() {
        //botones
        Boton bot_generar = new Boton();
        bot_generar.setValue("Generar Tabla");
        bot_generar.setIcon("ui-icon-calculator");
        bot_generar.setMetodo("generartabla");
        bar_botones.agregarBoton(bot_generar);

        //tab_periodo_academic.setIdCompleto("tab_tabulador:tab_periodo_academic");
        tab_periodo_academic.setId("tab_periodo_academic");  // todo objeto instanciado poner id 
        tab_periodo_academic.setTabla("yavirac_stror_periodo_academic", "ide_ystpea", 1);    // nom bdd
        tab_periodo_academic.agregarRelacion(tab_periodo_evaluacion);
        tab_periodo_academic.agregarRelacion(tab_nota_final);
        tab_periodo_academic.agregarRelacion(tab_periodo_actividad_eva);
        tab_periodo_academic.setHeader("PERIODO ACADÉMICO");
        //renombra etiquetas de la tabla
        tab_periodo_academic.getColumna("ide_ystpea").setNombreVisual("CÓDIGO");
        tab_periodo_academic.getColumna("descripcion_ystpea").setNombreVisual("DESCRIPCIÓN");
        tab_periodo_academic.getColumna("fecha_inicio_ystpea").setNombreVisual("FECHA INICIO");
        tab_periodo_academic.getColumna("fecha_final_ystpea").setNombreVisual("FECHA FINAL");
        tab_periodo_academic.getColumna("activo_ystpea").setNombreVisual("ACTIVO");
        tab_periodo_academic.getColumna("hora_clase_ystpea").setNombreVisual("HORA CLASE");
        tab_periodo_academic.getColumna("aplica_jornada_completa_ystpea").setNombreVisual("APLICA JORNADA");
        tab_periodo_academic.getColumna("tabla_notas_ystpea").setNombreVisual("NOMBRE TABLA");
        tab_periodo_academic.getColumna("tabla_notas_ystpea").setLectura(true);
        tab_periodo_academic.getColumna("tabla_notas_ystpea").setUnico(true);
        tab_periodo_academic.getColumna("tabla_creada_ystpea").setLectura(true);
        tab_periodo_academic.getColumna("tabla_creada_ystpea").setValorDefecto("false");
        tab_periodo_academic.getColumna("ide_ystani").setCombo("yavirac_stror_anio", "ide_ystani", "descripcion_ystani", "");
        tab_periodo_academic.getColumna("ide_ystani").setNombreVisual("AÑO");
        //*****************
        tab_periodo_academic.dibujar();

        PanelTabla pa_periodoacademico = new PanelTabla();
        pa_periodoacademico.setId("pa_periodoacademico"); // nombre de i
        pa_periodoacademico.setPanelTabla(tab_periodo_academic);

        //tabla perioado de evaluacion
        tab_periodo_evaluacion.setId("tab_periodo_evaluacion");  // todo objeto instanciado poner id 
        tab_periodo_evaluacion.setIdCompleto("tab_tabulador:tab_periodo_evaluacion");
        tab_periodo_evaluacion.setTabla("yavirac_nota_periodo_evaluacio", "ide_ynopee", 1);    // nom bdd
        tab_periodo_evaluacion.getColumna("ide_ynotie").setCombo(ser_notas.getTipoEvaluacion("true,false"));
        //renombrar etiquetas
        tab_periodo_evaluacion.getColumna("ide_ynopee").setNombreVisual("CÓDIGO");
        tab_periodo_evaluacion.getColumna("ide_ynotie").setNombreVisual("TIPO DE EVALUACIÓN");
        //*****************
        tab_periodo_evaluacion.dibujar();

        PanelTabla pa_periodoevaluacion = new PanelTabla();
        pa_periodoevaluacion.setId("pa_periodoevaluacion"); // nombre de i
        pa_periodoevaluacion.setPanelTabla(tab_periodo_evaluacion);

        //tabala  periodo actividad de evaluacion
        tab_nota_final.setId("tab_nota_final");  // todo objeto instanciado poner id 
        tab_nota_final.setIdCompleto("tab_tabulador:tab_nota_final");
        tab_nota_final.setTabla("yavirac_nota_nota_final", "ide_ynonof", 1);    // nom bdd
        tab_nota_final.getColumna("ide_ynoace").setCombo(ser_notas.getActividadEvaluacion("true,false"));
        //cambio etiketas
        tab_nota_final.getColumna("ide_ynonof").setNombreVisual("CÓDIGO");
        tab_nota_final.getColumna("ide_ynoace").setNombreVisual("ACTIVIDAD DE EVALUACIÓN");
        tab_nota_final.getColumna("formula_ynonof").setNombreVisual("FORMULA");
        //*******************
        tab_nota_final.dibujar();

        PanelTabla pa_nota_final = new PanelTabla();
        pa_nota_final.setId("pa_nota_final"); // nombre de i
        pa_nota_final.setPanelTabla(tab_nota_final);
        
        ///tabla periodo actividad evaluacion
        tab_periodo_actividad_eva.setId("tab_periodo_actividad_eva");
        tab_periodo_actividad_eva.setIdCompleto("tab_tabulador:tab_periodo_actividad_eva");
        tab_periodo_actividad_eva.setTabla("yavirac_nota_periodo_activ_eva","ide_ynopae", 1);
        tab_periodo_actividad_eva.getColumna("ide_ynoace").setCombo(ser_notas.getActividadEvaluacion("true,false"));
        tab_periodo_actividad_eva.getColumna("ide_ynopee").setCombo(ser_notas.getPeriodoEvaluacion("true,false"));
        tab_periodo_actividad_eva.dibujar();
        
        PanelTabla pa_periodo_actividad_eva = new PanelTabla();
        pa_periodo_actividad_eva.setId("pa_periodo_actividad_eva"); // nombre de i
        pa_periodo_actividad_eva.setPanelTabla(tab_periodo_actividad_eva);

        ///// tabuladores
        Tabulador tab_tabulador = new Tabulador();
        tab_tabulador.setId("tab_tabulador");
        tab_tabulador.agregarTab("PERIODO EVALUACIÓN", pa_periodoevaluacion);
        tab_tabulador.agregarTab("NOTA FINAL", pa_nota_final);
        tab_tabulador.agregarTab("PERIODO ACTIVIDAD EVALUACIÓN",pa_periodo_actividad_eva );
        Division div_periodo_academico = new Division();
        div_periodo_academico.dividir2(pa_periodoacademico, tab_tabulador, "40%", "h");
        agregarComponente(div_periodo_academico);

    }

    @Override
    public void insertar() {
        if (tab_periodo_academic.isFocus()) {
            tab_periodo_academic.insertar();
        } else if (tab_nota_final.isFocus()) {
            tab_nota_final.insertar();
        } else if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.insertar();
        }
        else if (tab_periodo_actividad_eva.isFocus()) {
            tab_periodo_actividad_eva.insertar();
            tab_periodo_actividad_eva.getColumna("ide_ynopee").setCombo(ser_notas.getperiodotipoevaluacion(tab_periodo_academic.getValor("ide_ystpea")));
        }
    }

    String data = "";
    String anio = "";
    String limite = "";
    String generar = "";

    @Override
    public void guardar() {
        if (tab_periodo_academic.isFocus()) {
            TablaGenerica tab_anio = utilitario.consultar("select ide_ystani,descripcion_ystani from yavirac_stror_anio where ide_ystani=" + tab_periodo_academic.getValor("ide_ystani"));
            anio = tab_anio.getValor("descripcion_ystani");
            data = stripAccents(tab_periodo_academic.getValor("descripcion_ystpea"));
            TablaGenerica tab_remplazdo = utilitario.consultar(ser_notas.getRemplazaG(data, "yavirac_stror_periodo_academic"));
            //System.out.println("YAVIRAC" + anio + tab_remplazdo.getValor("repa"));
            limite = "yavirac_" + anio + "_" + tab_remplazdo.getValor("repa").toLowerCase();
            TablaGenerica tab_limite = utilitario.consultar(ser_notas.getlimiteC(limite));
            tab_periodo_academic.setValor("tabla_notas_ystpea", tab_limite.getValor("nombre"));
            utilitario.addUpdateTabla(tab_periodo_academic, "tabla_notas_ystpea", "");
            tab_periodo_academic.guardar();
            if (tab_periodo_evaluacion.guardar()) {
              if (tab_nota_final.guardar()) {
                tab_periodo_actividad_eva.guardar();
                }  
            }
            
        }
        guardarPantalla();

    }

    @Override
    public void eliminar() {
        if (tab_periodo_academic.isFocus()) {
            tab_periodo_academic.eliminar();
        } else if (tab_nota_final.isFocus()) {
            tab_nota_final.eliminar();
        } else if (tab_periodo_evaluacion.isFocus()) {
            tab_periodo_evaluacion.eliminar();
        } else if (tab_periodo_actividad_eva.isFocus()) {
            tab_periodo_actividad_eva.eliminar();
        }
    }

    public Tabla getTab_periodo_academic() {
        return tab_periodo_academic;
    }

    public void setTab_periodo_academic(Tabla tab_periodo_academic) {
        this.tab_periodo_academic = tab_periodo_academic;
    }

    public Tabla getTab_periodo_evaluacion() {
        return tab_periodo_evaluacion;
    }

    public void setTab_periodo_evaluacion(Tabla tab_periodo_evaluacion) {
        this.tab_periodo_evaluacion = tab_periodo_evaluacion;
    }

    public Tabla getTab_nota_final() {
        return tab_nota_final;
    }

    public Tabla getTab_periodo_actividad_eva() {
        return tab_periodo_actividad_eva;
    }

    public void setTab_periodo_actividad_eva(Tabla tab_periodo_actividad_eva) {
        this.tab_periodo_actividad_eva = tab_periodo_actividad_eva;
    }

    public void setTab_nota_final(Tabla tab_nota_final) {
        this.tab_nota_final = tab_nota_final;
    }
}
