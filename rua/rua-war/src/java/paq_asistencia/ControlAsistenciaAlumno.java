/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import java.util.List;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_matricula.ejb.ServicioMatriculas;
import paq_personal.ejb.ServicioPersonal;

/**
 *
 * @author User
 */
public class ControlAsistenciaAlumno extends Pantalla{

     private Combo com_periodo_academico = new Combo();
     private Tabla tab_docente_mencion = new Tabla();
     private Tabla tab_fecha_control = new Tabla();
     private Tabla tab_asitencia= new Tabla();

    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioMatriculas ser_matricula = (ServicioMatriculas) utilitario.instanciarEJB(ServicioMatriculas.class);
    @EJB
    private final ServicioAlumno ser_alumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);
  
    public ControlAsistenciaAlumno(){
        if (tienePerfilAsistencia()) {
            
            bar_botones.getBot_insertar().setRendered(false);
            bar_botones.getBot_guardar().setRendered(false);
            bar_botones.getBot_eliminar().setRendered(false);
            
            Boton bot_asistencia = new Boton();
            bot_asistencia.setValue("Registrar Asistencia");
            bot_asistencia.setMetodo("registrarAsistencia");
            bar_botones.agregarBoton(bot_asistencia);
            
            com_periodo_academico.setId("com_periodo_academico");
            com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
            bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
            bar_botones.agregarComponente(com_periodo_academico);
            com_periodo_academico.setMetodo("filtroComboPeriodoAcademico");

            tab_docente_mencion.setId("tab_docente_mencion");
            tab_docente_mencion.setTabla("yavirac_perso_malla_docente", "ide_ypemad", 1);
            tab_docente_mencion.setCondicion("ide_ypemad=-1");
            tab_docente_mencion.getColumna("ide_ystmal").setCombo(ser_estructura_organizacional.getMalla());
            //tab_docente_mencion.getColumna("ide_ystmal").setAutoCompletar();
            tab_docente_mencion.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
            //tab_docente_mencion.getColumna("ide_ypedpe").setAutoCompletar();
            tab_docente_mencion.getColumna("ide_ystpea").setVisible(false);
            tab_docente_mencion.getColumna("ide_ystmal").setLectura(true);
            tab_docente_mencion.getColumna("ide_ypedpe").setLectura(true);
            tab_docente_mencion.onSelect("filtraEstudiantes");
            tab_docente_mencion.getColumna("ide_ypemad").setNombreVisual("IDE DOCENTE MENCION");
            tab_docente_mencion.getColumna("ide_ystmal").setNombreVisual("CAMPO PRIMARIO");
            tab_docente_mencion.getColumna("ide_ystpea").setNombreVisual("PERIODO ACADEMICO");
            tab_docente_mencion.getColumna("ide_ypedpe").setNombreVisual("DOCENTES");
            //tab_docente_mencion.setLectura(true);
        tab_docente_mencion.dibujar();
         tab_docente_mencion.setRows(5);
         
         PanelTabla pat_docente = new PanelTabla();
         pat_docente.setPanelTabla(tab_docente_mencion);
         
         tab_fecha_control.setId("tab_fecha_control");
         tab_fecha_control.setTabla("yavirac_asis_fecha_control","ide_yasfec", 2);
         tab_fecha_control.setCondicion("ide_yasfec=-1");
         tab_fecha_control.setLectura(true);
         tab_fecha_control.agregarRelacion(tab_asitencia);
         tab_fecha_control.getColumna("ide_yasfec").setNombreVisual("IDE FECHA CONTROL");
         tab_fecha_control.getColumna("ide_yassem").setNombreVisual("ASISTENCIA");
         tab_fecha_control.getColumna("ide_ystpea").setNombreVisual("PERIODO ACADEMICO");
         tab_fecha_control.getColumna("fecha_yasfec").setNombreVisual("FECHA");
         tab_fecha_control.getColumna("observacion_yasfec").setNombreVisual("OBSERVACIÓN");
         tab_fecha_control.getColumna("bloqueado_yasfec").setNombreVisual("BLOQUEO");
         tab_fecha_control.getColumna("activo_yasfec").setNombreVisual("ACTIVO INHABILITADO");
         tab_fecha_control.getColumna("festivo_yasfec").setNombreVisual("FESTIVO");
         
         tab_fecha_control.dibujar();
         tab_fecha_control.setRows(5);
         
         PanelTabla pat_fecha_control = new PanelTabla();
         pat_fecha_control.setPanelTabla(tab_fecha_control);
         
         tab_asitencia.setId("tab_asitencia");
         tab_asitencia.setTabla("yavirac_asis_asistencia", "ide_yasasi", 3);
         tab_asitencia.getColumna("ide_yaldap").setCombo(ser_alumno.getDatosAlumnos("true,false"));
         tab_asitencia.getColumna("ide_yasasi").setNombreVisual("IDE ASISTENCIA");
         tab_asitencia.getColumna("ide_ystpep").setNombreVisual("TOMAR ASISTENCIA");
         tab_asitencia.getColumna("ide_ypedpe").setNombreVisual("DOCENTES");
         tab_asitencia.getColumna("ide_yaldap").setNombreVisual("DATO PERSONAL ALUMNO");
         tab_asitencia.getColumna("ide_ystmal").setNombreVisual("CAMPO PRIMARIO");
         tab_asitencia.getColumna("asistencia_yasasi").setNombreVisual("ASISTENCIA");
         tab_asitencia.getColumna("justificado_yasasi").setNombreVisual("JUSTIFICACION");
         tab_asitencia.dibujar();
         
         PanelTabla pat_asistencia = new PanelTabla();
         pat_asistencia.setPanelTabla(tab_asitencia);
         
            Division div_control = new Division();
            div_control.dividir3(pat_docente, pat_fecha_control, pat_asistencia,"20%", "60%", "H");
            agregarComponente(div_control);
            
        } else {
            utilitario.agregarNotificacionInfo("Mensaje", "EL usuario ingresado no registra permisos para el control de Asistencia. Consulte con el Administrador");
        }
        
    }

    String docente = "";
    String documento="";
    String ide_docente="";
        private boolean tienePerfilAsistencia() {
        List sql = utilitario.getConexion().consultar(ser_estructura_organizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA")," and not ide_ypedpe is null"));

        if (!sql.isEmpty()) {
            Object[] fila = (Object[]) sql.get(0);
                    List sql2 = utilitario.getConexion().consultar(ser_personal.getDatoPersonalCodigo(fila[3].toString()));
            if (!sql2.isEmpty()) {
                Object[] fila2 = (Object[]) sql2.get(0);
                docente = fila2[1].toString()+" "+fila2[2].toString();
                documento = fila2[3].toString();
                ide_docente=fila2[0].toString();
                    return true;
            }  
            else{
            return false;
            }
        } else {
            return false;
        }
    }
    public void filtraEstudiantes(){
        String malla = tab_docente_mencion.getValorSeleccionado();
        TablaGenerica tab_malla = utilitario.consultar("select ide_ypemad,ide_ystmal,ide_ypedpe from yavirac_perso_malla_docente where ide_ypemad="+malla);

    }    
    public void registrarAsistencia(){
            
        String malla = tab_docente_mencion.getValorSeleccionado();
        TablaGenerica tab_malla = utilitario.consultar("select ide_ypemad,ide_ystmal,ide_ypedpe from yavirac_perso_malla_docente where ide_ypemad="+malla);
        
        String ide_fecha =tab_fecha_control.getValorSeleccionado();
        System.out.println("  ccvv "+ide_fecha);
        //TablaGenerica tab_malla_periodo = utilitario.consultar();
        utilitario.getConexion().ejecutarSql(ser_matricula.getAlumnosMallaPeriodo(tab_malla.getValor("ide_ystmal"), com_periodo_academico.getValue().toString(),ide_fecha,tab_malla.getValor("ide_ypedpe")));
        tab_asitencia.ejecutarSql();
        utilitario.addUpdate("tab_asitencia");
     
    }    
    public void filtroComboPeriodoAcademico(){
        
        tab_docente_mencion.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString()+" and ide_ypedpe ="+ide_docente);
        tab_docente_mencion.ejecutarSql();
        tab_fecha_control.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_fecha_control.ejecutarSql();
        utilitario.addUpdate("tab_docente_mencion,tab_fecha_control");
        
     
    }       
    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_docente_mencion() {
        return tab_docente_mencion;
    }

    public void setTab_docente_mencion(Tabla tab_docente_mencion) {
        this.tab_docente_mencion = tab_docente_mencion;
    }

    public Tabla getTab_fecha_control() {
        return tab_fecha_control;
    }

    public void setTab_fecha_control(Tabla tab_fecha_control) {
        this.tab_fecha_control = tab_fecha_control;
    }

    public Tabla getTab_asitencia() {
        return tab_asitencia;
    }

    public void setTab_asitencia(Tabla tab_asitencia) {
        this.tab_asitencia = tab_asitencia;
    }
    
}
