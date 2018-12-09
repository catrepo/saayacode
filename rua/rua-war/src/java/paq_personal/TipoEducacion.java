/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_personal;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_nota.ejb.ServicioNotas;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ITSY
 */
public class TipoEducacion extends Pantalla {

    private Tabla tab_tipo_educacion = new Tabla(); //intanciamos componente tabla
    private Tabla tab_actividad_docente = new Tabla();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);

    public TipoEducacion() {
        tab_tipo_educacion.setId("tab_tipo_educacion");
        tab_tipo_educacion.setTabla("yavirac_perso_dato_personal", "ide_ypedpe", 1);
        tab_tipo_educacion.setHeader("TIPO DE EDUCACIÓN");
        tab_tipo_educacion.getColumna("ide_ysttis").setCombo(ser_estructura.getTipoSangre("true,false"));
        tab_tipo_educacion.getColumna("ide_ystesc").setCombo(ser_estructura.getEstadoCivil("true,false"));
        //tab_tipo_educacion.getColumna("ide_ysttdp").setCombo(ser_estructura.getEstadoCivil("true,false"));
        tab_tipo_educacion.getColumna("ide_ystdoi").setCombo(ser_estructura.getDocumentoIdentidad("true,false"));
        tab_tipo_educacion.getColumna("ide_ystnac").setCombo(ser_estructura.getNacionalidad("true,false"));
        tab_tipo_educacion.getColumna("ide_ystgen").setCombo(ser_estructura.getGenero("true,false"));
        tab_tipo_educacion.getColumna("ide_ystdip").setCombo(ser_estructura.getDistribucionPolitica("true,false"));
        //crear formularios
        tab_tipo_educacion.setTipoFormulario(true);//para que se haga un formulario
        tab_tipo_educacion.getGrid().setColumns(4);//numero de columnas del formulario
        //*****************************************************************************
        tab_tipo_educacion.getColumna("ide_ypedpe").setNombreVisual("CÓDIGO");
        tab_tipo_educacion.getColumna("ide_ysttis").setNombreVisual("TIPO SANGRE");
        tab_tipo_educacion.getColumna("ide_ystesc").setNombreVisual("ESTADO CIVIL");
        //tab_tipo_educacion.getColumna("ide_yastpe").setNombreVisual("ESTADO CIVIL");
        tab_tipo_educacion.getColumna("ide_ystdoi").setNombreVisual("DOCUMENTO IDENTIDAD");
        tab_tipo_educacion.getColumna("ide_ystnac").setNombreVisual("NACIONALIDAD");
        tab_tipo_educacion.getColumna("ide_ystgen").setNombreVisual("GÉNERO");
        tab_tipo_educacion.getColumna("apellido_ypedpe").setNombreVisual("APELLIDOS");
        tab_tipo_educacion.getColumna("nombre_ypedpe").setNombreVisual("NOMBRES");
        tab_tipo_educacion.getColumna("fecha_nacimiento_ypedpe").setNombreVisual("FECHA NACIMIENTO");
        tab_tipo_educacion.getColumna("foto_ypedpe").setNombreVisual("FOTO");
        tab_tipo_educacion.getColumna("foto_ypedpe").setUpload();//cargafoto
        tab_tipo_educacion.getColumna("foto_ypedpe").setImagen();//visualizalafoto
        tab_tipo_educacion.getColumna("lugar_nacimiento_ypedpe").setNombreVisual("LUGAR NACIMIENTO");
        tab_tipo_educacion.getColumna("firma_ypedpe").setNombreVisual("FIRMA");
        tab_tipo_educacion.getColumna("firma_ypedpe").setUpload();
        tab_tipo_educacion.getColumna("firma_ypedpe").setImagen();
        tab_tipo_educacion.getColumna("doc_identidad_ypedpe").setNombreVisual("DOCUMENTO IDENTIDAD");
        tab_tipo_educacion.getColumna("edad_ypedpe").setNombreVisual("EDAD");
        tab_tipo_educacion.getColumna("codigo_reloj_ypedpe").setNombreVisual("CÓDIGO RELOJ");
        tab_tipo_educacion.getColumna("discapacitado_ypedpe").setNombreVisual("DISCAPACIDAD");
        tab_tipo_educacion.getColumna("activo_ypedpe").setNombreVisual("ACTIVO");
        //************************************************************************
        tab_tipo_educacion.dibujar();
        PanelTabla pat_tipo_educacion = new PanelTabla();
        pat_tipo_educacion.setId("pat_tipo_educacion");
        pat_tipo_educacion.setPanelTabla(tab_tipo_educacion);

        //TABLA ACTIVIDAD DOCENTE
        tab_actividad_docente.setId("tab_actividad_docente");
        tab_actividad_docente.setIdCompleto("tab_tabulador:tab_actividad_docente");
        tab_actividad_docente.setTabla("yavirac_nota_actividad_docente", "ide_ynoacd", 2);
        tab_actividad_docente.getColumna("ide_ynoacd").setNombreVisual("CODIO");
        tab_actividad_docente.getColumna("ide_ynopae").setNombreVisual("PERIODO ACTIVIDAD EVALUACIÓN");
        tab_actividad_docente.getColumna("ide_ypedpe").setNombreVisual("DOCENTE");
        tab_actividad_docente.getColumna("porciento_evaluacion_ynoacd").setNombreVisual("% EVALUACIÓN");
        tab_actividad_docente.dibujar();

        PanelTabla pa_actividad_docente = new PanelTabla();
        pa_actividad_docente.setId("pa_actividad_docente"); 
        pa_actividad_docente.setPanelTabla(tab_actividad_docente);

        Tabulador tab_tabulador = new Tabulador();
        tab_tabulador.setId("tab_tabulador");
        tab_tabulador.agregarTab("ACTIVDAD DOCENTE", pa_actividad_docente);
        Division div_tipo_educacion = new Division();
        div_tipo_educacion.dividir2(pat_tipo_educacion, tab_tabulador, "60%", "h");
        agregarComponente(div_tipo_educacion);
    }

    @Override
    public void insertar() {
        tab_tipo_educacion.insertar();
    }

    @Override
    public void guardar() {
        tab_tipo_educacion.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_tipo_educacion.eliminar();
    }

    public Tabla getTab_tipo_educacion() {
        return tab_tipo_educacion;
    }

    public void setTab_tipo_educacion(Tabla tab_tipo_educacion) {
        this.tab_tipo_educacion = tab_tipo_educacion;
    }

    public Tabla getTab_actividad_docente() {
        return tab_actividad_docente;
    }

    public void setTab_actividad_docente(Tabla tab_actividad_docente) {
        this.tab_actividad_docente = tab_actividad_docente;
    }
    
}
