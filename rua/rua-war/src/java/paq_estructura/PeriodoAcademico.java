/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Confirmar;
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
    private Confirmar con_confirma = new Confirmar();
    private Tabla tab_detalle_record = new Tabla();
    private Tabla tab_cabecera_record = new Tabla();

    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);

    public PeriodoAcademico() {

        //boton registrar notas
        Boton bot_nota = new Boton();
        bot_nota.setValue("CERRAR PERIODO");
        bot_nota.setIcon("ui-icon-note");//set icono Registrar///
        bot_nota.setMetodo("cerrarPeriodo");
        bar_botones.agregarBoton(bot_nota);

        //tab_periodo_academic.setIdCompleto("tab_tabulador:tab_periodo_academic");
        tab_periodo_academic.setId("tab_periodo_academic");  // todo objeto instanciado poner id 
        tab_periodo_academic.setTabla("yavirac_stror_periodo_academic", "ide_ystpea", 1);    // nom bdd
        tab_periodo_academic.setHeader("PERIODO ACADÉMICO");
        tab_periodo_academic.getColumna("ide_ystani").setCombo("yavirac_stror_anio", "ide_ystani", "descripcion_ystani", "");
        tab_periodo_academic.getColumna("ide_ystani").setAncho(-1);
        tab_periodo_academic.getColumna("ide_ystani").setLongitud(-1);
        tab_periodo_academic.getColumna("ide_ystani").setNombreVisual("AÑO");
        tab_periodo_academic.getColumna("nota_evaluacion_ystpea").setNombreVisual("NOTA EVALUACIÓN");
        tab_periodo_academic.getColumna("activo_ystpea").setLectura(true);
        tab_periodo_academic.getColumna("activo_ystpea").setValorDefecto("true");
        tab_periodo_academic.getColumna("aplica_recuperacion_ystpea").setValorDefecto("false");
        //*****************
        tab_periodo_academic.dibujar();

        PanelTabla pa_periodoacademico = new PanelTabla();
        pa_periodoacademico.setId("pa_periodoacademico"); // nombre de i
        pa_periodoacademico.setPanelTabla(tab_periodo_academic);

        ///// tabuladores
        Division div_periodo_academico = new Division();
        div_periodo_academico.setId("div_actividad_docente");
        div_periodo_academico.dividir1(pa_periodoacademico);
        agregarComponente(div_periodo_academico);

        //CONFIRMAR
        con_confirma.setId("con_confirma");
        con_confirma.setMessage("Está seguro que desea cerrar este periodo");
        con_confirma.setTitle("CERRAR PERIODO ACADEMICO");
        con_confirma.getBot_aceptar().setValue("Si");
        con_confirma.getBot_cancelar().setValue("No");
        agregarComponente(con_confirma);

        tab_detalle_record.setId("tab_detalle_record");
        tab_detalle_record.setTabla("yavirac_nota_det_rec_acad", "ide_ynodra", 2);

        tab_cabecera_record.setId("yavirac_nota_cab_rec_acad");
        tab_cabecera_record.setTabla("yavirac_nota_det_rec_acad", "ide_ynocra", 3);

    }

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

    public void cerrarPeriodo() {
        if (tab_periodo_academic.getValor("activo_ystpea").equals("false")) {
            utilitario.agregarMensajeInfo("Mensaje,", "El periodo academico esta cerrado");
        } else {
            con_confirma.getBot_aceptar().setMetodo("confirmarCerrarPeriodo");
            utilitario.addUpdate("con_confirma");
            con_confirma.dibujar();
        }
    }

    public void confirmarCerrarPeriodo() {
        //registrarRecordAcademico();
        utilitario.getConexion().ejecutarSql(ser_notas.getCerrarPeriodoAcademico(tab_periodo_academic.getValor(tab_periodo_academic.getFilaActual(), "ide_ystpea")));
        utilitario.agregarMensajeInfo("SUCCESSFULL,", "Exito");
        con_confirma.cerrar();
        utilitario.addUpdate("tab_periodo_academic");
    }

    public void registrarRecordAcademico() {
        TablaGenerica tab_matriculas = utilitario.consultar("select ide_ymamat,ide_yaldap,ide_ystmen,a.ide_ystnie,ide_ystpea \n"
                + "from yavirac_matri_matricula a\n"
                + "left join yavirac_matri_periodo_matric b on a.ide_ymaper=b.ide_ymaper\n"
                + "where ide_ystpea=" + tab_periodo_academic.getValor(tab_periodo_academic.getFilaActual(), "ide_ystpea"));
        TablaGenerica tab_cabecera = utilitario.consultar("select * from yavirac_nota_cab_rec_acad where ide_yaldap=" + tab_matriculas.getValor("ide_yaldap") + "");

        if (tab_matriculas.getTotalFilas() > 0) {
            if (tab_cabecera.getTotalFilas() > 0) {
                utilitario.getConexion().ejecutarSql("delete from yavirac_nota_det_rec_acad where ide_ystpea=" + tab_periodo_academic.getValor(tab_periodo_academic.getFilaActual(), "ide_ystpea"));
                //TablaGenerica tab_alumno=utilitario.consultar("");
                TablaGenerica tab_malla = utilitario.consultar("select * from yavirac_stror_malla  where ide_ystnie=" + tab_matriculas.getValor("ide_ystnie") + " and ide_ystmen=" + tab_matriculas.getValor("ide_ystmen") + "");
                //tab
                for (int i = 0; i < tab_malla.getTotalFilas(); i++) {
                    TablaGenerica tab_nota = utilitario.consultar("select ide_ynoalr,a.ide_ypemda,nota_ynoalr,ide_yaldap\n"
                            + "from yavirac_nota_alumno_resumen a  \n"
                            + "left join yavirac_nota_peso_nota  b on a.ide_ynopen=b.ide_ynopen\n"
                            + "left join yavirac_perso_malla_docen_alum c on a.ide_ypemda=c.ide_ypemda\n"
                            + "where nivel_ynopen=1 and ide_yaldap=" + tab_matriculas.getValor(i, "ide_yaldap"));
                    tab_detalle_record.insertar();
                    tab_detalle_record.setValor("ide_ynoest", utilitario.getVariable("p_estado_cursando"));
                    tab_detalle_record.setValor("ide_ynocra", tab_cabecera.getValor("ide_ynocra"));
                    tab_detalle_record.setValor("ide_ystmat", tab_malla.getValor(i, "ide_ystmat"));
                    tab_detalle_record.setValor("ide_ystpea", tab_matriculas.getValor("ide_ystpea"));
                    tab_detalle_record.setValor("ide_ystmal", tab_malla.getValor(i, "ide_ystmal"));
                    tab_detalle_record.setValor("codigo_mate_ynodra", tab_malla.getValor(i, "codigo_ystmal"));
                    tab_detalle_record.setValor("num_creditos_ynodra", tab_malla.getValor(i, "numero_credito_ystmal"));
                    //tab_detalle_record.setValor("nota_ynodra", tab_nota.getValor(i, "nota_ynoalr"));
                }
                tab_detalle_record.guardar();
                guardarPantalla();
            }
            /*if (tab_cabecera.getTotalFilas() > 0) {
            TablaGenerica tab_malla = utilitario.consultar("select * from yavirac_stror_malla  where ide_ystnie=" + tab_matriculas.getValor("ide_ystnie") + " and ide_ystmen=" + tab_matriculas.getValor("ide_ystmen") + "");
            TablaGenerica tab_periodo = utilitario.consultar("select * from yavirac_matri_periodo_matric  where ide_ymaper=" + tab_periodo_academic.getValor("ide_ystpea") + "");

            System.out.println("estoy en el metodo verdadero");
            for (int i = 0; i < tab_malla.getTotalFilas(); i++) {
                System.out.println("FOR 1: " + i);
                //TablaGenerica tab_mximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_nota_det_rec_acad", "ide_ynodra"));
                tab_detalle_record.insertar();
                tab_detalle_record.setValor("ide_ynoest", utilitario.getVariable("p_estado_cursando"));
                tab_detalle_record.setValor("ide_ynocra", tab_cabecera.getValor("ide_ynocra"));
                tab_detalle_record.setValor("ide_ystmat", tab_malla.getValor(i, "ide_ystmat"));
                tab_detalle_record.setValor("ide_ystpea", tab_periodo.getValor("ide_ystpea"));
                tab_detalle_record.setValor("ide_ystmal", tab_malla.getValor(i, "ide_ystmal"));
                tab_detalle_record.setValor("codigo_mate_ynodra", tab_malla.getValor(i, "codigo_ystmal"));
                tab_detalle_record.setValor("num_creditos_ynodra", tab_malla.getValor(i, "numero_credito_ystmal"));
            }
            tab_detalle_record.guardar();
            guardarPantalla();
        } else {
            TablaGenerica tab_malla = utilitario.consultar("select * from yavirac_stror_malla  where ide_ystnie=" + tab_matriculas.getValor("ide_ystnie") + " and ide_ystmen=" + tab_matriculas.getValor("ide_ystmen") + "");
            TablaGenerica tab_periodo = utilitario.consultar("select * from yavirac_matri_periodo_matric  where ide_ymaper=" + com_periodo_academico.getValue().toString() + "");
            System.out.println("estoy en el metodo falso");
            tab_cabecera_record.insertar();
            tab_cabecera_record.setValor("ide_yaldap", tab_matriculas.getValor("ide_yaldap"));
            tab_cabecera_record.setValor("ide_ystmen", tab_matriculas.getValor("ide_ystmen"));
            tab_cabecera_record.guardar();
            guardarPantalla();
            for (int i = 0; i < tab_malla.getTotalFilas(); i++) {
                System.out.println("FOR 1: " + i);
                tab_detalle_record.insertar();
                tab_detalle_record.setValor("ide_ynoest", utilitario.getVariable("p_estado_cursando"));
                tab_detalle_record.setValor("ide_ynocra", tab_cabecera.getValor("ide_ynocra"));
                tab_detalle_record.setValor("ide_ystmat", tab_malla.getValor(i, "ide_ystmat"));
                tab_detalle_record.setValor("ide_ystpea", tab_periodo.getValor("ide_ystpea"));
                tab_detalle_record.setValor("ide_ystmal", tab_malla.getValor(i, "ide_ystmal"));
                tab_detalle_record.setValor("codigo_mate_ynodra", tab_malla.getValor(i, "codigo_ystmal"));
                tab_detalle_record.setValor("num_creditos_ynodra", tab_malla.getValor(i, "numero_credito_ystmal"));
            }
            tab_detalle_record.guardar();
            guardarPantalla();
        }*/
        }
    }

    @Override
    public void insertar() {
        tab_periodo_academic.insertar();
    }

    String data = "";
    String anio = "";
    String limite = "";
    String generar = "";

    public Confirmar getCon_confirma() {
        return con_confirma;
    }

    public void setCon_confirma(Confirmar con_confirma) {
        this.con_confirma = con_confirma;
    }

    @Override
    public void guardar() {

        /*if (tab_periodo_academic.isFilaInsertada()) {

            /*TablaGenerica tab_anio = utilitario.consultar("select ide_ystani,descripcion_ystani from yavirac_stror_anio where ide_ystani=" + tab_periodo_academic.getValor("ide_ystani"));
            anio = tab_anio.getValor("descripcion_ystani");
            data = stripAccents(tab_periodo_academic.getValor("descripcion_ystpea"));
            TablaGenerica tab_remplazdo = utilitario.consultar(ser_notas.getRemplazaG(data, "yavirac_stror_periodo_academic"));
            //System.out.println("YAVIRAC" + anio + tab_remplazdo.getValor("repa"));
            limite = "yavirac_" + anio + "_" + tab_remplazdo.getValor("repa").toLowerCase();
            TablaGenerica tab_limite = utilitario.consultar(ser_notas.getlimiteC(limite));
            tab_periodo_academic.setValor("tabla_notas_ystpea", tab_limite.getValor("nombre"));
            utilitario.addUpdateTabla(tab_periodo_academic, "tabla_notas_ystpea", "");
        }*/
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
