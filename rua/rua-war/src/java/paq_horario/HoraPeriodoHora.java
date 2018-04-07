
package paq_horario;

import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_horarios.ejb.ServiciosHorarios;
import sistema.aplicacion.Pantalla;
import framework.componentes.Dialogo;
import framework.componentes.Boton;
import framework.componentes.Grupo;
import framework.componentes.SeleccionTabla;
/**
 *
 * @author Andres
 */
public class HoraPeriodoHora extends Pantalla {
    
    private Tabla tab_hora_periodo_hora = new Tabla();
    private Combo com_periodo_academico = new Combo();
    private Combo com_dia_modalidad = new Combo();
    private Dialogo dia_modalidad = new Dialogo();
    private Combo com_dia_jornada = new Combo();
    private Dialogo dia_jornada = new Dialogo();
    private SeleccionTabla set_tab_jornada = new SeleccionTabla();
    private SeleccionTabla set_tab_dias = new SeleccionTabla();
    private SeleccionTabla set_tab_mension = new SeleccionTabla();
    
     @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
  
    @EJB
    private final  ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
     
    public HoraPeriodoHora(){
        
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
       
        bar_botones.agregarComponente(new Etiqueta("Periodo Academico"));
        bar_botones.agregarComponente(com_periodo_academico);
        
        // creo dialogo para crear modalidad
        dia_modalidad.setId("dia_modalidad");
        dia_modalidad.setTitle("Seleccion de Modalidad");
        dia_modalidad.setWidth("40%");
        dia_modalidad.setHeight("18%");
        dia_modalidad.getBot_aceptar().setMetodo("aceptarModalidad");
        dia_modalidad.setResizable(false);
        
        com_dia_modalidad.setId("com_dia_modalidad");
        com_dia_modalidad.setCombo(ser_estructura_organizacional.getModalidad("true"));
        
        Grupo gru_cuerpo = new Grupo();
        Etiqueta eti_mensaje = new Etiqueta();
        eti_mensaje.setValue("Seleccione Modalidad                                              ");
        eti_mensaje.setStyle("font-size: 13px;border: none;text-shadow: 0px 2px 3px #ccc;background: none;");
        
        gru_cuerpo.getChildren().add(eti_mensaje);
        gru_cuerpo.getChildren().add(com_dia_modalidad);

        dia_modalidad.setDialogo(gru_cuerpo);
        agregarComponente(dia_modalidad);
        
        // creo dialogo para crear jornada
        /*dia_jornada.setId("dia_jornada");
        dia_jornada.setTitle("Seleccion de Jornada");
        dia_jornada.setWidth("40%");
        dia_jornada.setHeight("18%");
        //dia_jornada.getBot_aceptar().setMetodo("aceptarModalidad");
        dia_jornada.setResizable(false);
        
        com_dia_jornada.setId("com_dia_jornada");
        com_dia_jornada.setCombo(ser_estructura_organizacional.getJornada("true"));
        
        Grupo gru_cuerpo1 = new Grupo();
        Etiqueta eti_mensaje1 = new Etiqueta();
        eti_mensaje1.setValue("Seleccione la Jornada      ");
        eti_mensaje1.setStyle("font-size: 13px;border: none;text-shadow: 0px 2px 3px #ccc;background: none;");
        
        gru_cuerpo1.getChildren().add(eti_mensaje1);
        gru_cuerpo1.getChildren().add(com_dia_jornada);

        dia_jornada.setDialogo(gru_cuerpo1);
        agregarComponente(dia_jornada);*/
        set_tab_jornada.setId("set_tab_jornada");
        set_tab_jornada.setTitle("TABLA DE LA MODALIDAD");
        set_tab_jornada.setSeleccionTabla(ser_horarios.getDefinicionJornada("-1","-1"), "ide_ystjor");
        set_tab_jornada.getTab_seleccion().getColumna("descripcion_ystjor").setNombreVisual("Jornada");
        set_tab_jornada.setWidth("80%");
        set_tab_jornada.setHeight("70%");
        set_tab_jornada.getBot_aceptar().setMetodo("aceptarJornada");
        agregarComponente(set_tab_jornada);
        
        set_tab_dias.setId("set_tab_dias");
        set_tab_dias.setTitle("DIAS DE LA SEMANA");
        set_tab_dias.setSeleccionTabla(ser_horarios.getDia(), "ide_yhodia");
        //select ide_yhodia, descripcion_yhodia from yavirac_hora_dia order by orden_yhodia asc", "ide_yhodia
        //set_tab_dias.getTab_seleccion().getColumna("descripcion_ystjor").setNombreVisual("Jornada");
        set_tab_dias.setWidth("80%");
        set_tab_dias.setHeight("70%");
        set_tab_dias.getBot_aceptar().setMetodo("aceptarDiasSemana");
        agregarComponente(set_tab_dias);
        
        set_tab_mension.setId("set_tab_mension");
        set_tab_mension.setTitle("MENSION");
        set_tab_mension.setSeleccionTabla(ser_estructura_organizacional.getMension(), "IDE_YSTMEN");
        //select ide_yhodia, descripcion_yhodia from yavirac_hora_dia order by orden_yhodia asc", "ide_yhodia
        //set_tab_dias.getTab_seleccion().getColumna("descripcion_ystjor").setNombreVisual("Jornada");
        set_tab_mension.setWidth("80%");
        set_tab_mension.setHeight("70%");
        set_tab_mension.getBot_aceptar().setMetodo("aceptarDiasSemana");
        agregarComponente(set_tab_mension);
        
    tab_hora_periodo_hora.setId("tab_hora_periodo_hora");   //identificador
    tab_hora_periodo_hora.setTabla("yavirac_hora_periodo_hor", "ide_yhopeh", 1);
    tab_hora_periodo_hora.setCondicion("ide_ystpea=-1");
    tab_hora_periodo_hora.getColumna("ide_ystmod").setCombo(ser_estructura_organizacional.getModalidad("true,false"));
    tab_hora_periodo_hora.getColumna("ide_ystjor").setCombo(ser_estructura_organizacional.getJornada("true,false"));
    tab_hora_periodo_hora.getColumna("ide_yhohor").setCombo(ser_horarios.getHora("true,false"));
    tab_hora_periodo_hora.getColumna("ide_yhodia").setCombo(ser_horarios.getDia());
    tab_hora_periodo_hora.getColumna("ide_yhothj").setCombo(ser_horarios.getHorarios("true,false"));
    tab_hora_periodo_hora.getColumna("ide_ystmen").setCombo(ser_estructura_organizacional.getMension());
    tab_hora_periodo_hora.getColumna("ide_ystpea").setVisible(false);
    tab_hora_periodo_hora.getColumna("ide_yhopeh").setNombreVisual("CÓDIGO PRINCIPAL");
    tab_hora_periodo_hora.getColumna("ide_ystmod").setNombreVisual("MODALIDAD");
    tab_hora_periodo_hora.getColumna("ide_ystjor").setNombreVisual("JORNADA");
    tab_hora_periodo_hora.getColumna("ide_yhohor").setNombreVisual("HORA");
    tab_hora_periodo_hora.getColumna("ide_yhodia").setNombreVisual("DÍA");
    /*tab_hora_periodo_hora.getColumna("ide_yhopeh").setNombreVisual("CODIGO");*/
    tab_hora_periodo_hora.getColumna("ide_yhothj").setNombreVisual("TIPO DE HORARIO");
    tab_hora_periodo_hora.getColumna("horainicial_yhopeh").setNombreVisual("HORA INICIAL");
    tab_hora_periodo_hora.getColumna("horafinal_yhopeh").setNombreVisual("HORA FINAL");
    tab_hora_periodo_hora.getColumna("activo_yhopeh").setNombreVisual("ACTIVO");
    tab_hora_periodo_hora.getColumna("ide_ystmen").setNombreVisual("MENSIÓN CARRERAS");    
    tab_hora_periodo_hora.dibujar();
        /*agregarComponente(tab_hora_hora);*/ 
        
        PanelTabla pat_hora_periodo_hora = new PanelTabla();
        pat_hora_periodo_hora.setId("pat_hora_hora");
        pat_hora_periodo_hora.setPanelTabla(tab_hora_periodo_hora);
        Division div_hora_periodo_hora = new Division();
        div_hora_periodo_hora.setId("div_hora_periodo_hora");
        div_hora_periodo_hora.dividir1(pat_hora_periodo_hora);
        agregarComponente(div_hora_periodo_hora); 
        
        Boton bot_replicar = new Boton();
        bot_replicar.setIcon("ui-icon-newwin");
        bot_replicar.setValue("Generar");
        bot_replicar.setTitle("Generar");
        bar_botones.agregarBoton(bot_replicar);    
        bot_replicar.setMetodo("generarPeriodoHora");
        
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
    public void aceptarModalidad(){
    
    if(com_dia_modalidad.getValue() == null){
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione la modalidad");
        return;
        }
        else {
       dia_modalidad.cerrar();
       set_tab_jornada.getTab_seleccion().setSql(ser_horarios.getDefinicionJornada(com_dia_modalidad.getValue().toString(),com_periodo_academico.getValue().toString()));
            set_tab_jornada.getTab_seleccion().ejecutarSql();              
            set_tab_jornada.dibujar();
            
                }
}
        public void aceptarJornada(){   
         if(set_tab_jornada.getSeleccionados()== null){
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione la jornada");
         return;
          }
         else {
            set_tab_jornada.cerrar();
            set_tab_dias.getTab_seleccion().setSql(ser_horarios.getDia());
            set_tab_dias.getTab_seleccion().ejecutarSql();              
            set_tab_dias.dibujar();
            //aceptarDiasSemana
                }
}
     public void aceptarDiasSemana(){
        if(set_tab_dias.getSeleccionados()== null){
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione los dias");
        return;
        }
        else {
       set_tab_dias.cerrar();
       set_tab_mension.getTab_seleccion().setSql(ser_estructura_organizacional.getMension());
            set_tab_mension.getTab_seleccion().ejecutarSql();              
            set_tab_mension.dibujar();
            //aceptarDiasSemana
                }
}
    
    public void generarPeriodoHora (){
    
    if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Academico que desea generar");
        return;
        }
        else {
       dia_modalidad.dibujar();         
                }
}

    public Combo getCom_dia_modalidad() {
        return com_dia_modalidad;
    }

    public void setCom_dia_modalidad(Combo com_dia_modalidad) {
        this.com_dia_modalidad = com_dia_modalidad;
    }

    public Dialogo getDia_modalidad() {
        return dia_modalidad;
    }

    public void setDia_modalidad(Dialogo dia_modalidad) {
        this.dia_modalidad = dia_modalidad;
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

    public Combo getCom_dia_jornada() {
        return com_dia_jornada;
    }

    public void setCom_dia_jornada(Combo com_dia_jornada) {
        this.com_dia_jornada = com_dia_jornada;
    }

    public Dialogo getDia_jornada() {
        return dia_jornada;
    }

    public void setDia_jornada(Dialogo dia_jornada) {
        this.dia_jornada = dia_jornada;
    }

    public SeleccionTabla getSet_tab_jornada() {
        return set_tab_jornada;
    }

    public void setSet_tab_jornada(SeleccionTabla set_tab_jornada) {
        this.set_tab_jornada = set_tab_jornada;
    }

    public SeleccionTabla getSet_tab_dias() {
        return set_tab_dias;
    }

    public void setSet_tab_dias(SeleccionTabla set_tab_dias) {
        this.set_tab_dias = set_tab_dias;
    }

    public SeleccionTabla getSet_tab_mension() {
        return set_tab_mension;
    }

    public void setSet_tab_mension(SeleccionTabla set_tab_mension) {
        this.set_tab_mension = set_tab_mension;
    }
       
}
