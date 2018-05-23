
package paq_horario;

/**
 *
 * @author ANDRES
 */

import framework.componentes.AutoCompletar;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;
public class HoraDocenteMalla extends Pantalla{
    private Tabla tab_docente_malla = new Tabla();
    /*private Combo com_periodo_academico = new Combo();*/
    private AutoCompletar aut_alumno = new AutoCompletar();
    private Combo com_periodo_academico = new Combo();
    
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
     @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    
     @EJB
    private final  ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
    
    public HoraDocenteMalla(){
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
       
        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADÉMICO"));
        bar_botones.agregarComponente(com_periodo_academico);
        
        tab_docente_malla.setId("tab_docente_malla");   //identificador
        tab_docente_malla.setTabla("yavirac_perso_malla_docente", "ide_ypemad", 1);
       // tab_docente_malla.setCondicion("ide_yhodom=-1"); // no dibuja nada de entrada
        tab_docente_malla.setCondicion("ide_ystpea=-1");
        tab_docente_malla.getColumna("ide_ystpea").setVisible(false);
        tab_docente_malla.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_docente_malla.getColumna("ide_yhogra").setCombo(ser_horarios.getGrupoAcademico());
        tab_docente_malla.getColumna("ide_ystmal").setCombo(ser_estructura_organizacional.getMalla());   
        tab_docente_malla.getColumna("ide_ystjor").setCombo(ser_estructura_organizacional.getJornada("true"));
        tab_docente_malla.getColumna("ide_yhodom").setNombreVisual("CÓDIGO PRINCIPAL");
        tab_docente_malla.getColumna("ide_ypedpe").setNombreVisual("PERSONAL DOCENTES");
        tab_docente_malla.getColumna("ide_ystjor").setNombreVisual("JORNADA");
        tab_docente_malla.getColumna("ide_ypedpe").setVisible(false);
        tab_docente_malla.getColumna("ide_ystmal").setNombreVisual("MALLA ACADÉMICA");
        tab_docente_malla.getColumna("ide_yhogra").setNombreVisual("GRUPOS / PARALELOS");
        tab_docente_malla.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_docente_malla = new PanelTabla();
        pat_hora_docente_malla.setId("pat_hora_docente_malla");
        pat_hora_docente_malla.setPanelTabla(tab_docente_malla);
        Division div_hora_docente_malla = new Division();
        div_hora_docente_malla.setId("div_hora_docente_malla");
        div_hora_docente_malla.dividir1(pat_hora_docente_malla);
        agregarComponente(div_hora_docente_malla); 
        
        aut_alumno.setId("aut_alumno");
        aut_alumno.setAutoCompletar(ser_personal.getDatopersonal("true,false"));
        aut_alumno.setSize(75);
        aut_alumno.setMetodoChange("selecionoAutocompletar");
        bar_botones.agregarComponente(new Etiqueta("Docente"));
        bar_botones.agregarComponente(aut_alumno);

    }
        public void selecionoAutocompletar(){
        
        tab_docente_malla.setCondicion("ide_ypedpe="+aut_alumno.getValor());
        tab_docente_malla.ejecutarSql();
        utilitario.addUpdate("tab_docente_malla");
        
     
    }
    public void filtroComboPeriodoAcademnico(){
        
        tab_docente_malla.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_docente_malla.ejecutarSql();
        utilitario.addUpdate("tab_docente_malla");
        
    }
    
     @Override
    public void insertar() {
        if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Académico");
            return;
        }
        else if(tab_docente_malla.isFocus()){
        tab_docente_malla.insertar();
        tab_docente_malla.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
       utilitario.addUpdateTabla( tab_docente_malla, "ide_ystpea", "");
       }
        
        //tab_docente_malla.insertar();
       // tab_docente_malla.setValor("ide_ypedpe", aut_alumno.getValue().);
       //utilitario.addUpdateTabla( tab_docente_malla, "ide_ypedpe", "");

    }

    @Override
    public void guardar() {
        tab_docente_malla.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_docente_malla.eliminar();
    }

    public Tabla getTab_docente_malla() {
        return tab_docente_malla;
    }

    public void setTab_docente_malla(Tabla tab_docente_malla) {
        this.tab_docente_malla = tab_docente_malla;
    }

    public AutoCompletar getAut_alumno() {
        return aut_alumno;
    }

    public void setAut_alumno(AutoCompletar aut_alumno) {
        this.aut_alumno = aut_alumno;
    }
    
}
