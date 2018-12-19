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
import javax.ejb.EJB;
import paq_nota.ejb.ServicioNotas;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ITSY
 */
public class PeriodoAcademico extends Pantalla {

    Tabla tab_periodo_academic = new Tabla(); // importar tabla 
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
        if (tab_periodo_academic.getValor(tab_periodo_academic.getFilaActual(), "tabla_creada_ystpea").equals("true")) {
            utilitario.agregarNotificacionInfo("Mensajes", "Existe la tabla");
        } else {
            utilitario.getConexion().ejecutarSql(ser_notas.gettabnotamaestro(tab_periodo_academic.getValor("tabla_notas_ystpea")));
            tab_periodo_academic.setValor("tabla_creada_ystpea", "true");
            tab_periodo_academic.modificar(tab_periodo_academic.getFilaActual());
            tab_periodo_academic.guardar();
            guardarPantalla();
            utilitario.addUpdateTabla(tab_periodo_academic, "tabla_creada_ystpea", "");
            utilitario.agregarMensaje("Mensaje", "Tabla creada exitosamente");
        }
        //
    }

    public PeriodoAcademico() {
       

        //tab_periodo_academic.setIdCompleto("tab_tabulador:tab_periodo_academic");
        tab_periodo_academic.setId("tab_periodo_academic");  // todo objeto instanciado poner id 
        tab_periodo_academic.setTabla("yavirac_stror_periodo_academic", "ide_ystpea", 1);    // nom bdd
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
        tab_periodo_academic.getColumna("tabla_notas_ystpea").setVisible(false);
        tab_periodo_academic.getColumna("tabla_creada_ystpea").setLectura(true);
        tab_periodo_academic.getColumna("tabla_creada_ystpea").setValorDefecto("false");
        tab_periodo_academic.getColumna("tabla_creada_ystpea").setVisible(false);
        tab_periodo_academic.getColumna("ide_ystani").setCombo("yavirac_stror_anio", "ide_ystani", "descripcion_ystani", "");
        tab_periodo_academic.getColumna("ide_ystani").setNombreVisual("AÑO");
        //*****************
        tab_periodo_academic.dibujar();

        PanelTabla pa_periodoacademico = new PanelTabla();
        pa_periodoacademico.setId("pa_periodoacademico"); // nombre de i
        pa_periodoacademico.setPanelTabla(tab_periodo_academic);

        ///// tabuladores
        Division div_periodo_academico = new Division();
        div_periodo_academico.setId("div_actividad_docente");
        div_periodo_academico.dividir1(tab_periodo_academic);
        agregarComponente(div_periodo_academico);

    }

    @Override
    public void insertar() {
        tab_periodo_academic.insertar();
    }

    String data = "";
    String anio = "";
    String limite = "";
    String generar = "";

    @Override
    public void guardar() {

        if (tab_periodo_academic.isFilaInsertada()) {

            TablaGenerica tab_anio = utilitario.consultar("select ide_ystani,descripcion_ystani from yavirac_stror_anio where ide_ystani=" + tab_periodo_academic.getValor("ide_ystani"));
            anio = tab_anio.getValor("descripcion_ystani");
            data = stripAccents(tab_periodo_academic.getValor("descripcion_ystpea"));
            TablaGenerica tab_remplazdo = utilitario.consultar(ser_notas.getRemplazaG(data, "yavirac_stror_periodo_academic"));
            //System.out.println("YAVIRAC" + anio + tab_remplazdo.getValor("repa"));
            limite = "yavirac_" + anio + "_" + tab_remplazdo.getValor("repa").toLowerCase();
            TablaGenerica tab_limite = utilitario.consultar(ser_notas.getlimiteC(limite));
            tab_periodo_academic.setValor("tabla_notas_ystpea", tab_limite.getValor("nombre"));
            utilitario.addUpdateTabla(tab_periodo_academic, "tabla_notas_ystpea", "");
        }
        if (tab_periodo_academic.guardar()) {
            
                    guardarPantalla();
        }
        guardarPantalla();

    }

    @Override
    public void eliminar() {
        tab_periodo_academic.eliminar();
    }

    public Tabla getTab_periodo_academic() {
        return tab_periodo_academic;
    }

    public void setTab_periodo_academic(Tabla tab_periodo_academic) {
        this.tab_periodo_academic = tab_periodo_academic;
    }
}
