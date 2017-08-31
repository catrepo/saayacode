
package paq_horario;

import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ebj.ServicioEstructuraOrganizacional;
import paq_estructura.ebj.ServiciosJornada;
import paq_estructura.ebj.ServiciosModalidad;
import paq_horarios.ejb.ServicioTipoHorario;
import paq_horarios.ejb.ServiciosHorarios;
import sistema.aplicacion.Pantalla;

public class HoraDefinicion extends Pantalla{
    private Tabla tab_hora_definicion = new Tabla();
    private Combo com_periodo_academico = new Combo();


    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    @EJB
    private final  ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
    
    @EJB
    private final  ServicioTipoHorario ser_tipohorario = (ServicioTipoHorario) utilitario.instanciarEJB(ServicioTipoHorario.class);
     
       @EJB
    private final  ServiciosModalidad ser_modalidad = (ServiciosModalidad) utilitario.instanciarEJB(ServiciosModalidad.class);
      
      @EJB
    private final  ServiciosJornada ser_jornada = (ServiciosJornada) utilitario.instanciarEJB(ServiciosJornada.class);
    
    public HoraDefinicion(){
        
        
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
       
        bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
        bar_botones.agregarComponente(com_periodo_academico);
        
        
    tab_hora_definicion.setId("tab_hora_definicion");   //identificador
    tab_hora_definicion.setTabla("yavirac_hora_definicion_hora", "ide_yhodeh", 1);
    tab_hora_definicion.setCondicion("ide_ystpea=-1");
    tab_hora_definicion.getColumna("ide_yhothj").setCombo(ser_horarios.getHorarios("true,false"));
      tab_hora_definicion.getColumna("ide_yhotih").setCombo(ser_tipohorario.getTipoHorarios("true,false"));
      tab_hora_definicion.getColumna("ide_ystjor").setCombo(ser_jornada.getJornada("true,false"));
      tab_hora_definicion.getColumna("ide_ystmod").setCombo(ser_modalidad.getModalidad("true,false"));
      tab_hora_definicion.getColumna("ide_ystpea").setVisible(false);
    tab_hora_definicion.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_definicion = new PanelTabla();
        pat_hora_definicion.setId("pat_hora_definicion");
        pat_hora_definicion.setPanelTabla(tab_hora_definicion);
        Division div_hora_definicion = new Division();
        div_hora_definicion.setId("div_hora_tipo_horario_jornada");
        div_hora_definicion.dividir1(pat_hora_definicion);
        agregarComponente(div_hora_definicion); 
        
    }
    public void filtroComboPeriodoAcademnico(){
        
        tab_hora_definicion.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_hora_definicion.ejecutarSql();
        utilitario.addUpdate("tab_hora_definicion");
        
    }
    @Override
    public void insertar() {
//        System.out.println("vaklor del combo"+com_periodo_academico.getValue().toString());
        if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Academico");
            return;
        }
        else {
        
       tab_hora_definicion.insertar();
       tab_hora_definicion.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
       utilitario.addUpdateTabla(tab_hora_definicion, "ide_ystpea", "");
       }
    }

    @Override
    public void guardar() {
        tab_hora_definicion.guardar();
        guardarPantalla();
    }
    @Override
    public void eliminar() {
        tab_hora_definicion.eliminar();
    }

    public Tabla getTab_hora_definicion() {
        return tab_hora_definicion;
    }

    public void setTab_hora_definicion(Tabla tab_hora_definicion) {
        this.tab_hora_definicion = tab_hora_definicion;
    }

    public Combo getCom_periodo_academico() {
        return com_periodo_academico;
    }

    public void setCom_periodo_academico(Combo com_periodo_academico) {
        this.com_periodo_academico = com_periodo_academico;
    }
    
    
}
