
package paq_horario;

import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_estructura.ejb.ServiciosJornada;
import paq_estructura.ejb.ServiciosModalidad;
import paq_horarios.ejb.ServicioHoraHora;
import paq_horarios.ejb.ServiciosHorarios;
import sistema.aplicacion.Pantalla;
/**
 *
 * @author Andres
 */
public class HoraPeriodoHora extends Pantalla {
    
    private Tabla tab_hora_periodo_hora = new Tabla();
    private Combo com_periodo_academico = new Combo();
    
     @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
  
      @EJB
    private final  ServiciosModalidad ser_modalidad = (ServiciosModalidad) utilitario.instanciarEJB(ServiciosModalidad.class);
      
      @EJB
    private final  ServiciosJornada ser_jornada = (ServiciosJornada) utilitario.instanciarEJB(ServiciosJornada.class);
      
      @EJB
    private final  ServicioHoraHora ser_horahora = (ServicioHoraHora) utilitario.instanciarEJB(ServicioHoraHora.class);
      
    public HoraPeriodoHora(){
        
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
       
        bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
        bar_botones.agregarComponente(com_periodo_academico);
        
        
    tab_hora_periodo_hora.setId("tab_hora_periodo_hora");   //identificador
    tab_hora_periodo_hora.setTabla("yavirac_hora_periodo_hor", "ide_yhopeh", 1);
     tab_hora_periodo_hora.setCondicion("ide_ystpea=-1");
     tab_hora_periodo_hora.getColumna("ide_ystmod").setCombo(ser_modalidad.getModalidad("true,false"));
     tab_hora_periodo_hora.getColumna("ide_ystjor").setCombo(ser_jornada.getJornada("true,false"));
     tab_hora_periodo_hora.getColumna("ide_yhohor").setCombo(ser_horahora.getHora("true,false"));
     tab_hora_periodo_hora.getColumna("ide_ystpea").setVisible(false);
    tab_hora_periodo_hora.dibujar();
        /*agregarComponente(tab_hora_hora);*/ 
        
        PanelTabla pat_hora_periodo_hora = new PanelTabla();
        pat_hora_periodo_hora.setId("pat_hora_hora");
        pat_hora_periodo_hora.setPanelTabla(tab_hora_periodo_hora);
        Division div_hora_periodo_hora = new Division();
        div_hora_periodo_hora.setId("div_hora_periodo_hora");
        div_hora_periodo_hora.dividir1(pat_hora_periodo_hora);
        agregarComponente(div_hora_periodo_hora); 
        
        
        
                }
     public void filtroComboPeriodoAcademnico(){
        
        tab_hora_periodo_hora.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_hora_periodo_hora.ejecutarSql();
        utilitario.addUpdate("tab_hora_definicion");
        
    }
    
 
    
    @Override
    public void insertar() {
        if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Academico");
            return;
        }
        else {
        
        tab_hora_periodo_hora.insertar();
        tab_hora_periodo_hora.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
       utilitario.addUpdateTabla( tab_hora_periodo_hora, "ide_ystpea", "");
       }
       // tab_hora_periodo_hora.insertar();
    }

    @Override
    public void guardar() {
        
    tab_hora_periodo_hora.guardar();
    guardarPantalla();
        
    }

    @Override
    public void eliminar() {
     tab_hora_periodo_hora.eliminar();
     
    }

    public Tabla getTab_hora_periodo_hora() {
        return tab_hora_periodo_hora;
    }

    public void setTab_hora_periodo_hora(Tabla tab_hora_periodo_hora) {
        this.tab_hora_periodo_hora = tab_hora_periodo_hora;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }
    
}
