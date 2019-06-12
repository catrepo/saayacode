/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.VisualizarPDF;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_matricula.ejb.ServicioMatriculas;
import paq_nota.ejb.ServicioNotas;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHONPRODUCER
 */
public class RecordAcademico extends Pantalla {

    private Tabla tab_cabecera = new Tabla();
    private Tabla tab_detalle = new Tabla();
    private AutoCompletar aut_alumno = new AutoCompletar();
    private VisualizarPDF vipdf_record = new VisualizarPDF();

    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioMatriculas ser_matricula = (ServicioMatriculas) utilitario.instanciarEJB(ServicioMatriculas.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);

    public RecordAcademico() {

        bar_botones.getBot_insertar().setRendered(false);
        bar_botones.getBot_eliminar().setRendered(false);
               
        aut_alumno.setId("aut_alumno");
        aut_alumno.setAutoCompletar(ser_alumno.getDatosAlumnos("true,false"));
        aut_alumno.setSize(75);
        aut_alumno.setMetodoChange("selecionoAutocompletar");
        bar_botones.agregarComponente(new Etiqueta("Alumno :"));
        bar_botones.agregarComponente(aut_alumno);

        tab_cabecera.setId("tab_cabecera");
        tab_cabecera.setTabla("yavirac_nota_cab_rec_acad", "ide_ynocra", 1);
        tab_cabecera.setHeader("CABECERA RECORD ACADEMICO");
        tab_cabecera.setCondicion("ide_ynocra=-1");
        tab_cabecera.agregarRelacion(tab_detalle);
        tab_cabecera.getColumna("ide_yaldap").setVisible(false);
        tab_cabecera.getColumna("ide_ystmen").setCombo(ser_estructura.getMension());
        tab_cabecera.getColumna("ide_ystmen").setAutoCompletar();
        tab_cabecera.getColumna("ide_ynocra").setNombreVisual("CODIGO");
        tab_cabecera.getColumna("ide_ystmen").setNombreVisual("MENSION");
        tab_cabecera.getColumna("fecha_inicio_ynocra").setNombreVisual("FECHA INICIO");
        tab_cabecera.getColumna("fecha_fin_ynocra").setNombreVisual("FECHA CULMINACIÓN");
        tab_cabecera.getColumna("ide_ystmen").setLectura(true);
        tab_cabecera.getColumna("fecha_inicio_ynocra").setLectura(true);
        tab_cabecera.getColumna("fecha_fin_ynocra").setLectura(true);
        tab_cabecera.dibujar();

        PanelTabla pa_cabecera = new PanelTabla();
        pa_cabecera.setId("pa_cabecera");
        pa_cabecera.setPanelTabla(tab_cabecera);

        tab_detalle.setId("tab_detalle");
        tab_detalle.setHeader("DETALLE RECORD ACADEMICO");
        tab_detalle.setTabla("yavirac_nota_det_rec_acad", "ide_ynodra", 2);
        tab_detalle.getColumna("ide_ystmat").setVisible(false);
        tab_detalle.getColumna("ide_ynoest").setCombo(ser_notas.getEstadoNota());
        tab_detalle.getColumna("ide_ystpea").setCombo(ser_estructura.getPeriodoAcademico("true,false"));
        tab_detalle.getColumna("ide_ymatrc").setCombo(ser_matricula.getTipoRegitroCredito());
        tab_detalle.getColumna("ide_ymanum").setCombo(ser_matricula.getNumeroMatricula());
        tab_detalle.getColumna("ide_ystmal").setCombo(ser_estructura.getMalla());
        tab_detalle.getColumna("ide_ynoest").setAncho(5);
        tab_detalle.getColumna("ide_ystpea").setAutoCompletar();
        tab_detalle.getColumna("ide_ymatrc").setAutoCompletar();
        tab_detalle.getColumna("ide_ymanum").setAutoCompletar();
        tab_detalle.getColumna("ide_ystmal").setAutoCompletar();
        tab_detalle.getColumna("ide_ynoest").setLectura(true);
        tab_detalle.getColumna("ide_ystpea").setLectura(true);
        tab_detalle.getColumna("ide_ymatrc").setLectura(true);
        tab_detalle.getColumna("ide_ymanum").setLectura(true);
        tab_detalle.getColumna("ide_ystmal").setLectura(true);
        tab_detalle.getColumna("codigo_mate_ynodra").setLectura(true);
        tab_detalle.getColumna("num_creditos_ynodra").setLectura(true);
        tab_detalle.getColumna("nota_ynodra").setLectura(true);
        tab_detalle.getColumna("ide_ynodra").setNombreVisual("CODIGO");
        tab_detalle.getColumna("ide_ynoest").setNombreVisual("ESTADO");
        tab_detalle.getColumna("ide_ystpea").setNombreVisual("PERIODO ACADÉMICO");
        tab_detalle.getColumna("ide_ymanum").setNombreVisual("N° MATRICULA");
        tab_detalle.getColumna("ide_ymatrc").setNombreVisual("TIPO APROBACIÓN");
        tab_detalle.getColumna("ide_ystmal").setNombreVisual("MATERIA");
        tab_detalle.getColumna("codigo_mate_ynodra").setNombreVisual("CODIGO MATERIA");
        tab_detalle.getColumna("num_creditos_ynodra").setNombreVisual("N° CRÉDITOS");
        tab_detalle.getColumna("nota_ynodra").setNombreVisual("CALIFICACIÓN");
        tab_detalle.getColumna("observacion_ynodra").setNombreVisual("OBSERVACIÓN");
        tab_detalle.setRows(15);
        tab_detalle.dibujar();

        PanelTabla pa_detalle = new PanelTabla();
        pa_detalle.setId("pa_detalle");
        pa_detalle.setPanelTabla(tab_detalle);

        Division div_record = new Division();
        div_record.setId("div_nota");
        div_record.dividir2(pa_cabecera, pa_detalle, "15%", "h");
        agregarComponente(div_record);

        Boton bot_nota = new Boton();
        bot_nota.setValue("Imprimir");
        bot_nota.setIcon("ui-icon-print");
        bot_nota.setMetodo("generarPDF");
        bar_botones.agregarBoton(bot_nota);

        vipdf_record.setId("vipdf_record");
        vipdf_record.setTitle("RECORD ACADEMICO");
        agregarComponente(vipdf_record);

    }

    public void generarPDF() {
        if (aut_alumno.getValue() != null) {
            Map map_parametros = new HashMap();
            map_parametros.put("ide_yaldap", Integer.parseInt(aut_alumno.getValor()));
            vipdf_record.setVisualizarPDF("rep_nota/rep_record_academico.jasper", map_parametros);
            vipdf_record.dibujar();
            utilitario.addUpdate("vipdf_record");
        } else {
            utilitario.agregarMensajeInfo("ADVERTENCIA,", "Seleccione un alumno");
        }
    }

    public void selecionoAutocompletar() {
        tab_cabecera.setCondicion("ide_yaldap=" + aut_alumno.getValor());
        tab_cabecera.ejecutarSql();
        utilitario.addUpdate("tab_cabecera");
    }

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        tab_detalle.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_cabecera() {
        return tab_cabecera;
    }

    public void setTab_cabecera(Tabla tab_cabecera) {
        this.tab_cabecera = tab_cabecera;
    }

    public Tabla getTab_detalle() {
        return tab_detalle;
    }

    public void setTab_detalle(Tabla tab_detalle) {
        this.tab_detalle = tab_detalle;
    }

    public AutoCompletar getAut_alumno() {
        return aut_alumno;
    }

    public void setAut_alumno(AutoCompletar aut_alumno) {
        this.aut_alumno = aut_alumno;
    }

    public VisualizarPDF getVipdf_record() {
        return vipdf_record;
    }

    public void setVipdf_record(VisualizarPDF vipdf_record) {
        this.vipdf_record = vipdf_record;
    }

}
