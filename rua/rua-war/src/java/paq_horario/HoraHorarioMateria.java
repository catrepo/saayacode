
package paq_horario;

/**
 *
 * @author ANDRES
 */
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

public class HoraHorarioMateria extends Pantalla{
   private Tabla tab_hora_horario_materia = new Tabla();
   private Combo com_periodo_academico = new Combo();
     @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
     @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    
     @EJB
    private final  ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
    
   public HoraHorarioMateria(){
        tab_hora_horario_materia.setId("tab_hora_horario_materia");   //identificador
        tab_hora_horario_materia.setTabla("yavirac_hora_horario_mate", "ide_yhohma", 1);
        tab_hora_horario_materia.getColumna("ide_ypedpe").setCombo(ser_personal.getDatopersonal("true,false"));
        tab_hora_horario_materia.getColumna("ide_yhogra").setCombo(ser_horarios.getGrupoAcademico());
        tab_hora_horario_materia.getColumna("ide_ystmal").setCombo(ser_estructura_organizacional.getMalla());        
        tab_hora_horario_materia.getColumna("ide_yhohma").setNombreVisual("CÓDIGO PRINCIPAL");
        tab_hora_horario_materia.getColumna("ide_ypedpe").setNombreVisual("PERSONAL DOCENTES");
        tab_hora_horario_materia.getColumna("ide_ystmal").setNombreVisual("MALLA ACADÉMICA");
        tab_hora_horario_materia.getColumna("ide_yhogra").setNombreVisual("GRUPO / PARALELO");
        tab_hora_horario_materia.getColumna("ide_yhopeh").setNombreVisual("PERIODO DE HORARIO");
        tab_hora_horario_materia.dibujar();   
        
        PanelTabla pat_hora_horario_materia = new PanelTabla();
        pat_hora_horario_materia.setId("pat_hora_horario_materia");
        pat_hora_horario_materia.setPanelTabla(tab_hora_horario_materia);
        Division div_hora_horario_materia = new Division();
        div_hora_horario_materia.setId("div_hora_horario_materia");
        div_hora_horario_materia.dividir1(pat_hora_horario_materia);
        agregarComponente(div_hora_horario_materia); 
        
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true,false"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
        bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
        bar_botones.agregarComponente(com_periodo_academico);
    }
    public void filtroComboPeriodoAcademnico(){
        
        tab_hora_horario_materia.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_hora_horario_materia.ejecutarSql();
        utilitario.addUpdate("tab_hora_definicion");
        
     
    }
   
        
   @Override
    public void insertar() {
        tab_hora_horario_materia.insertar();
    }

    @Override
    public void guardar() {
        tab_hora_horario_materia.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_hora_horario_materia.eliminar();
    }

    public Tabla getTab_hora_horario_materia() {
        return tab_hora_horario_materia;
    }

    public void setTab_hora_horario_materia(Tabla tab_hora_horario_materia) {
        this.tab_hora_horario_materia = tab_hora_horario_materia;
    }
    
}
