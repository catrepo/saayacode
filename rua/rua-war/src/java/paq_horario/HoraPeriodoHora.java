
package paq_horario;

import framework.aplicacion.TablaGenerica;
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
import paq_personal.ejb.ServicioPersonal;
/**
 *
 * @author Andres
 */
public class HoraPeriodoHora extends Pantalla {
    
    private Tabla tab_hora_periodo_hora = new Tabla();
    private Tabla tab_hora_horario_materia = new Tabla();
    private Combo com_periodo_academico = new Combo();
    private Combo com_dia_modalidad = new Combo();
    private Dialogo dia_modalidad = new Dialogo();
    private Combo com_dia_jornada = new Combo();
    private Dialogo dia_jornada = new Dialogo();
    private SeleccionTabla set_tab_jornada = new SeleccionTabla();
    private SeleccionTabla set_tab_dias = new SeleccionTabla();
    private SeleccionTabla set_tab_mension = new SeleccionTabla();
    private String modalidad ="";
    private String jornada ="";
    private String dias ="";
    private String mension ="";
    private String horas ="";
    
     @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
  
    @EJB
    private final  ServiciosHorarios ser_horarios = (ServiciosHorarios) utilitario.instanciarEJB(ServiciosHorarios.class);
     
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    public HoraPeriodoHora(){
        
        com_periodo_academico.setId("cmb_periodo_academico");
        com_periodo_academico.setCombo(ser_estructura_organizacional.getPeriodoAcademico("true"));
        com_periodo_academico.setMetodo("filtroComboPeriodoAcademnico");
       
        bar_botones.agregarComponente(new Etiqueta("PERIODO ACADÉMICO"));
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
        set_tab_jornada.setTitle("TABLA DE LA JORNADA");
        set_tab_jornada.setSeleccionTabla(ser_horarios.getDefinicionJornada("-1","-1"), "ide_ystjor");
        set_tab_jornada.getTab_seleccion().getColumna("descripcion_ystjor").setNombreVisual("Jornada");
        set_tab_jornada.setWidth("40%");
        set_tab_jornada.setHeight("40%");
        set_tab_jornada.getBot_aceptar().setMetodo("aceptarJornada");
        agregarComponente(set_tab_jornada);
        
        set_tab_dias.setId("set_tab_dias");
        set_tab_dias.setTitle("DIAS DE LA SEMANA");
        set_tab_dias.setSeleccionTabla(ser_horarios.getDia(), "ide_yhodia");
        //select ide_yhodia, descripcion_yhodia from yavirac_hora_dia order by orden_yhodia asc", "ide_yhodia
        //set_tab_dias.getTab_seleccion().getColumna("descripcion_ystjor").setNombreVisual("Jornada");
        set_tab_dias.setWidth("30%");
        set_tab_dias.setHeight("50%");
        set_tab_dias.getBot_aceptar().setMetodo("aceptarDiasSemana");
        agregarComponente(set_tab_dias);
        
        set_tab_mension.setId("set_tab_mension");
        set_tab_mension.setTitle("MENSION");
        set_tab_mension.setSeleccionTabla(ser_estructura_organizacional.getMension(), "IDE_YSTMEN");
        //select ide_yhodia, descripcion_yhodia from yavirac_hora_dia order by orden_yhodia asc", "ide_yhodia
        //set_tab_dias.getTab_seleccion().getColumna("descripcion_ystjor").setNombreVisual("Jornada");
        set_tab_mension.setWidth("60%");
        set_tab_mension.setHeight("60%");
        set_tab_mension.getBot_aceptar().setMetodo("generarSemanero");
        agregarComponente(set_tab_mension);
        
    tab_hora_periodo_hora.setId("tab_hora_periodo_hora");   //identificador
    tab_hora_periodo_hora.setTabla("yavirac_hora_periodo_hor", "ide_yhopeh", 1);
    tab_hora_periodo_hora.setCondicion("ide_ystpea=-1");
    tab_hora_periodo_hora.agregarRelacion(tab_hora_horario_materia);
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
    PanelTabla pat_hora_periodo_hora = new PanelTabla();
    pat_hora_periodo_hora.setId("pat_hora_periodo_hora");
    pat_hora_periodo_hora.setPanelTabla(tab_hora_periodo_hora);
    
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
        /*agregarComponente(tab_hora_hora);*/ 
 
        Division div_hora_periodo_hora = new Division();
        div_hora_periodo_hora.setId("div_hora_periodo_hora");
        div_hora_periodo_hora.dividir2(pat_hora_periodo_hora,pat_hora_horario_materia,  "50%","H");
        agregarComponente(div_hora_periodo_hora); 
        
        Boton bot_replicar = new Boton();
        bot_replicar.setIcon("ui-icon-newwin");
        bot_replicar.setValue("GENERAR SEMANERO");
        bot_replicar.setTitle("GENERAR SEMANERO");
        bar_botones.agregarBoton(bot_replicar);    
        bot_replicar.setMetodo("generarPeriodoHora");

        Boton bot_hora_clase = new Boton();
        bot_hora_clase.setIcon("ui-icon-newwin");
        bot_hora_clase.setValue("GENERAR HORARIO CLASE");
        bot_hora_clase.setTitle("GENERAR HORARIO CLASE");
        bar_botones.agregarBoton(bot_hora_clase);    
        bot_hora_clase.setMetodo("insertaHorarioClase");
        
     
        
        Boton bot_n = new Boton();
        bot_n.setIcon("ui-icon-newwin");
        bot_n.setValue("NUEVO");
        bot_n.setTitle("NUEVO");
        bar_botones.agregarBoton(bot_n);    
        bot_n.setMetodo("horarioClaseConsolidado");
        
                }
     public void filtroComboPeriodoAcademnico(){
        
        tab_hora_periodo_hora.setCondicion("ide_ystpea="+com_periodo_academico.getValue().toString());
        tab_hora_periodo_hora.ejecutarSql();
        utilitario.addUpdate("tab_hora_definicion");
        
    }
    
 
    
    @Override
    public void insertar() {
        if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeError("ERROR", "Seleccione el Periodo Académico");
            return;
        }
        else if(tab_hora_periodo_hora.isFocus()){
        tab_hora_periodo_hora.insertar();
        tab_hora_periodo_hora.setValor("ide_ystpea", com_periodo_academico.getValue().toString());
       utilitario.addUpdateTabla( tab_hora_periodo_hora, "ide_ystpea", "");
       }
        
        else if(tab_hora_horario_materia.isFocus()){
            tab_hora_horario_materia.insertar();
        }
       // tab_hora_periodo_hora.insertar();
    }
    public void aceptarModalidad(){
    
    if(com_dia_modalidad.getValue() == null){
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione la modalidad");
        return;
        }
        else {
        modalidad = com_dia_modalidad.getValue()+"";
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
            jornada = set_tab_jornada.getSeleccionados();
            set_tab_jornada.cerrar();
            set_tab_dias.getTab_seleccion().setSql(ser_horarios.getDia());
            set_tab_dias.getTab_seleccion().ejecutarSql();              
            set_tab_dias.dibujar();
            //aceptarDiasSemana
                }
}
     public void aceptarDiasSemana(){
        if(set_tab_dias.getSeleccionados()== null){
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione los días");
        return;
        }
        else {
       dias = set_tab_dias.getSeleccionados();
       set_tab_dias.cerrar();
       set_tab_mension.getTab_seleccion().setSql(ser_estructura_organizacional.getMension());
       set_tab_mension.getTab_seleccion().ejecutarSql();              
       set_tab_mension.dibujar();
            //aceptarDiasSemana
                }
}
    
    public void generarPeriodoHora (){
    
    if(com_periodo_academico.getValue() == null){
            utilitario.agregarMensajeInfo("ADVERTENCIA", "Seleccione el Periodo Académico que desea generar");
        return;
        }
        else {
       dia_modalidad.dibujar();         
                }
}
    public void generarSemanero(){
        /*
        utilitario.agregarMensajeInfo("ADVERTENCIA","modalidad "+modalidad);
        utilitario.agregarMensajeInfo("ADVERTENCIA", "jornada "+jornada);
        utilitario.agregarMensajeInfo("ADVERTENCIA", "dias "+dias);
        mension = set_tab_mension.getSeleccionados();
        utilitario.agregarMensajeInfo("ADVERTENCIA", "mension "+mension);
        set_tab_mension.cerrar();
*/
         insertarReceso();
         insertaHorasClase();
         
    }
   
    public void insertarReceso(){
        mension = set_tab_mension.getSeleccionados();
        TablaGenerica receso = utilitario.consultar(ser_horarios.getDefinicionReceso(jornada, com_periodo_academico.getValue().toString(), modalidad, utilitario.getVariable("p_tipo_receso"),"true" ));
        String maximo = "";
        receso.imprimirSql();
        TablaGenerica tab_dias = utilitario.consultar(ser_horarios.getNumDias(dias));
        TablaGenerica tab_mension = utilitario.consultar(ser_horarios.getNumMension(mension));
        for (int i=0;i<receso.getTotalFilas();i++){
            for(int j=0; j<tab_dias.getTotalFilas();j++){ 
                for (int k=0; k<tab_mension.getTotalFilas();k++){
                  TablaGenerica codigo_maximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_hora_periodo_hor", "ide_yhopeh"));
                  maximo = codigo_maximo.getValor("maximo");
                  String sql = "insert into yavirac_hora_periodo_hor (ide_yhopeh, ide_ystmod, ide_ystjor, ide_yhohor, ide_ystpea, ide_yhodia, ide_yhothj, horainicial_yhopeh, horafinal_yhopeh, activo_yhopeh, ide_ystmen)\n" +
                               "values ("+maximo+", "+receso.getValor(i, "ide_ystmod")+", "+receso.getValor(i, "ide_ystjor")+", "+utilitario.getVariable("p_tipo_hora")+", "+com_periodo_academico.getValue()+", "+tab_dias.getValor(j, "ide_yhodia")+", "+utilitario.getVariable("p_tipo_receso")+", '"+receso.getValor(i, "hora_inicio_yhodeh")+"', '"+receso.getValor(i, "hora_final_yhodeh")+"', "+receso.getValor(i, "activo_yhodeh")+", "+tab_mension.getValor(k, "ide_ystmen")+")";
                              // System.out.print(sql);
                  utilitario.getConexion().ejecutarSql(sql);
                  set_tab_mension.cerrar();
                  tab_hora_periodo_hora.actualizar();
            }
          }
        }
        
  
    }
    public void insertaHorasClase(){
        double numero_horas_entrada=0;
        double numero_horas_receso=0;
        int numero_horas_total=0;
        double numero_horas_clase=0;
        String maximo="";
        String hora_ini="";
        String hora_fin="";
        boolean receso=false;
        TablaGenerica tab_entrada_salida = utilitario.consultar(ser_horarios.getDefinicionReceso(jornada, com_periodo_academico.getValue().toString(), modalidad, utilitario.getVariable("p_tipo_entrada_salida"),"true" ));
        TablaGenerica tab_periodo= utilitario.consultar("select * from yavirac_stror_periodo_academic where ide_ystpea="+com_periodo_academico.getValue());
        numero_horas_clase = Double.parseDouble(tab_periodo.getValor("hora_clase_ystpea"));
        for (int i=0;i<tab_entrada_salida.getTotalFilas();i++){
            
               utilitario.getConexion().ejecutarSql("delete from yavirac_hora_matriz; ");
               TablaGenerica tab_hora_entrada=utilitario.consultar(utilitario.getDiferenciaHorasMinutos(tab_entrada_salida.getValor(i,"hora_inicio_yhodeh"), tab_entrada_salida.getValor(i,"hora_final_yhodeh")));
               numero_horas_entrada = Double.parseDouble(tab_hora_entrada.getValor("resultado"));
               TablaGenerica tab_receso = utilitario.consultar("select * from yavirac_hora_definicion_hora where ide_ystpea="+com_periodo_academico.getValue()+" and ide_yhothj="+utilitario.getVariable("p_tipo_receso")+" and ide_ystjor="+tab_entrada_salida.getValor(i, "ide_ystjor")+" and ide_ystmod="+tab_entrada_salida.getValor(i, "ide_ystmod"));
              
               if(tab_receso.getTotalFilas()>0){
                    TablaGenerica tab_hora_receso=utilitario.consultar(utilitario.getDiferenciaHorasMinutos(tab_receso.getValor("hora_inicio_yhodeh"), tab_receso.getValor("hora_final_yhodeh")));
                    numero_horas_receso = Double.parseDouble(tab_hora_receso.getValor("resultado"));
                    receso=true;
               }
               else {
                   receso=false;
               }
               double opera=(numero_horas_entrada+numero_horas_receso)/numero_horas_clase;
               TablaGenerica resul_int=utilitario.consultar("select 1 as codigo,cast( cast('"+opera+"' as numeric) as integer) as res");
               numero_horas_total=Integer.parseInt(resul_int.getValor("res"));
               System.out.println("horas enteras "+numero_horas_total);
               //if(utilitario.isEnteroPositivo(numero_horas_total+"")){
               if(1==1){
                    System.out.println("entre  horas enteras "+numero_horas_total);
                   TablaGenerica tab_inicia_hora = utilitario.consultar("select * from yavirac_stror_jornada where ide_ystjor="+tab_entrada_salida.getValor(i, "ide_ystjor")); 
                   int inicia_hora= Integer.parseInt(tab_inicia_hora.getValor("inicia_hora_ystjor"));
                   for (int j=1;j<Integer.parseInt(numero_horas_total+"");j++){
                   TablaGenerica codigo_maximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_hora_matriz", "ide_yhomat"));
                   maximo = codigo_maximo.getValor("maximo");
                        if(j==1){
                            System.out.println("valor j "+j);
                            hora_ini=tab_entrada_salida.getValor(i, "hora_inicio_yhodeh");
                            System.out.println("valor hora_ini "+hora_ini);
                        }
                        else{
                            System.out.println("valor j else "+j);
                            TablaGenerica tab_hora_ini = utilitario.consultar("select * from yavirac_hora_matriz where ide_yhomat="+(j));
                            hora_ini= tab_hora_ini.getValor("hora_fin_yhomat");
                            System.out.println("valor hora_ini else "+hora_ini);
                        }
                         System.out.println("valor j suma "+j);
                        int horas_sumar=Integer.parseInt(tab_periodo.getValor("hora_clase_ystpea"))*60;
                        System.out.println("valor horas_sumar suma "+horas_sumar);
                        TablaGenerica tab_hora_fin= utilitario.consultar(utilitario.getSumaHoras(hora_ini, horas_sumar+""));
                        hora_fin=tab_hora_fin.getValor("hora_nueva");
                        System.out.println("hora_fin "+hora_fin);
                        
                        if(receso){
                            //TablaGenerica tab_valida_receso=utilitario.consultar(ser_horarios.getResultadoExisteReceso(tab_receso.getValor("hora_inicio_yhodeh"), tab_entrada_salida.getValor(i,"hora_inicio_yhodeh"), tab_entrada_salida.getValor(i,"hora_final_yhodeh")));
                            TablaGenerica tab_valida_receso=utilitario.consultar(ser_horarios.getResultadoExisteReceso(tab_receso.getValor("hora_inicio_yhodeh"), hora_ini, hora_fin));
                            if(tab_valida_receso.getTotalFilas()>0){
                                hora_ini=tab_receso.getValor("hora_final_yhodeh");
                                TablaGenerica tab_hora_f= utilitario.consultar(utilitario.getSumaHoras(hora_ini, horas_sumar+""));
                                hora_fin=tab_hora_f.getValor("hora_nueva");
                            }
                        }
                        System.out.println("hora_fin saliendo else"+hora_fin);
                        utilitario.getConexion().ejecutarSql("INSERT INTO yavirac_hora_matriz(ide_yhomat, hora_inicio_yhomat, hora_fin_yhomat, orden_hora_yhomat)" +
                        " VALUES ('"+maximo+"','"+hora_ini+"','"+hora_fin+"','"+inicia_hora+"');");
                        inicia_hora=inicia_hora+1;                        
                   }
                   TablaGenerica tab_matriz_hora= utilitario.consultar("select * from yavirac_hora_matriz");
                   for(int k=0;k<tab_matriz_hora.getTotalFilas();k++){
                       
                        TablaGenerica tab_dias = utilitario.consultar(ser_horarios.getNumDias(dias));
                        TablaGenerica tab_mension = utilitario.consultar(ser_horarios.getNumMension(mension));
                         
                             for(int m=0; m<tab_dias.getTotalFilas();m++){ 
                                 
                                for (int n=0; n<tab_mension.getTotalFilas();n++){
                       
                       TablaGenerica codigo_maximo_hora = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_hora_periodo_hor", "ide_yhopeh"));
                       maximo = codigo_maximo_hora.getValor("maximo");
                       
                       TablaGenerica tab_hohor=utilitario.consultar("select ide_yhohor,orden_yhohor from yavirac_hora_hora where orden_yhohor="+tab_matriz_hora.getValor(k, "orden_hora_yhomat"));
                       String ide_hohor=tab_hohor.getValor("ide_yhohor");
                       
                       utilitario.getConexion().ejecutarSql("INSERT INTO yavirac_hora_periodo_hor(ide_yhopeh, ide_ystmod, ide_ystjor, ide_yhohor, ide_ystpea, horainicial_yhopeh,horafinal_yhopeh, activo_yhopeh, ide_yhodia, ide_yhothj, ide_ystmen)\n" +
                        " VALUES ('"+maximo+"', '"+tab_entrada_salida.getValor(i, "ide_ystmod")+"', '"+tab_entrada_salida.getValor(i, "ide_ystjor")+"', '"+ide_hohor+"', '"+com_periodo_academico.getValue()+"', '"+tab_matriz_hora.getValor(k, "hora_inicio_yhomat")+"','"+tab_matriz_hora.getValor(k, "hora_fin_yhomat")+"', 'true', '"+tab_dias.getValor(m, "ide_yhodia")+"', '"+utilitario.getVariable("p_tipo_entrada_salida")+"', '"+tab_mension.getValor(n, "ide_ystmen")+"');");
                       
                                }
                             }
                        
                    }
               }
               else{
                    System.out.println("elseee    horas enteras "+numero_horas_total);
                   utilitario.agregarMensajeError("No se puede continuar", "No se encuentra bien definidos las horas clase");
                   return;
               }
                //System.out.print(receso);
           }
    }
    
    public void insertaHorarioClase(){
        utilitario.getConexion().ejecutarSql("delete from yavirac_matriz_temp; ");
        horarioLaboratorioTemp();
        horarioClaseTemp();
        generarDetalleMatrizTemp();
    }
    
    public void generarDetalleMatrizTemp(){
        TablaGenerica tab_matriz_tem = utilitario.consultar("select * from yavirac_matriz_temp");
        for(int i=0;i<tab_matriz_tem.getTotalFilas();i++){
            //System.out.println("entre primer for "+i);
            // sumo los detalle de la hora para de esta manera validar que se tengan todas las horas divididas
            
            int suma_hora= 0;
            int hora_semana=Integer.parseInt(tab_matriz_tem.getValor(i,"horas_semana_ystmal"));
            int hora_laboratorio=Integer.parseInt(tab_matriz_tem.getValor(i,"horas_semana_lab_ystmal"));
            int max_laboratorio=Integer.parseInt(tab_matriz_tem.getValor(i,"num_max_lab_ystmal"));
            int max_clase=Integer.parseInt(tab_matriz_tem.getValor(i,"maximo_horas_ystmal"));
            int detalle_hora_lab=0;
            int detalle_hora_cla=0;
            int modulo_lab=0;
            int recorrido_lab=0;
            String maximo_primario_detalle="";
            suma_hora=ser_horarios.sumDetalleMatriz(tab_matriz_tem.getValor(i,"ide_yamatz"));
            //System.out.println("antes de entrar al while "+suma_hora+" de la matriz: "+tab_matriz_tem.getValor(i,"ide_yamatz")+" hora semana: "+hora_semana);
            while(suma_hora<hora_semana){
                //System.out.println("entre el while: "+suma_hora+" de la matriz: "+tab_matriz_tem.getValor(i,"ide_yamatz"));
                detalle_hora_lab= ser_horarios.horaLaboratorio(tab_matriz_tem.getValor(i,"ide_yamatz"));
                //System.out.println("detalle_hora_lab: "+detalle_hora_lab+" detalle_hora_cla: "+detalle_hora_cla);
                if(detalle_hora_lab<hora_laboratorio){
                    //System.out.println("entre al primer if "+detalle_hora_cla);
                    TablaGenerica tab_recorrido = utilitario.consultar("select 1 as codigo,(horas_semana_lab_ystmal - mod(horas_semana_lab_ystmal,num_max_lab_ystmal) )/num_max_lab_ystmal as recorrido,mod(horas_semana_lab_ystmal,num_max_lab_ystmal) as modulo\n" +
                    "from yavirac_matriz_temp where ide_yamatz ="+tab_matriz_tem.getValor(i,"ide_yamatz"));
                    modulo_lab = Integer.parseInt(tab_recorrido.getValor("modulo"));
                    recorrido_lab = Integer.parseInt(tab_recorrido.getValor("recorrido"));
                    //System.out.println("modulo "+modulo_lab+" recorrido_lab "+recorrido_lab);
                    for(int r=0;r<recorrido_lab;r++){
                        //System.out.println("primer for"+r);
                        TablaGenerica tab_cod_maximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_matriz_detalle_temp", "ide_yamadt"));
                        maximo_primario_detalle= tab_cod_maximo.getValor("maximo");
                        utilitario.getConexion().ejecutarSql(ser_horarios.insertaMatrizDetalle(maximo_primario_detalle, tab_matriz_tem.getValor(i, "ide_yamatz"), tab_matriz_tem.getValor(i, "ide_ystmal"), tab_matriz_tem.getValor(i, "ide_ystnie"), tab_matriz_tem.getValor(i, "ide_ypedpe"), tab_matriz_tem.getValor(i, "ide_ystmat"), tab_matriz_tem.getValor(i, "ide_ystmen"), tab_matriz_tem.getValor(i, "ide_ysttif"), tab_matriz_tem.getValor(i, "ide_ystjor"), tab_matriz_tem.getValor(i, "ide_ymacal"), tab_matriz_tem.getValor(i, "ide_yhogra"), tab_matriz_tem.getValor(i, "ide_ystins"), tab_matriz_tem.getValor(i, "ide_ystpea"), tab_matriz_tem.getValor(i, "aplica_laboratorio_ystmal"), tab_matriz_tem.getValor(i, "num_max_lab_ystmal"), "0", tab_matriz_tem.getValor(i, "ide_ystmod")));

                    }
                    for(int m=0;m<modulo_lab;m++){
                        //System.out.println("modulo_lab "+modulo_lab);
                        TablaGenerica tab_cod_maximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_matriz_detalle_temp", "ide_yamadt"));
                        maximo_primario_detalle= tab_cod_maximo.getValor("maximo");
                        utilitario.getConexion().ejecutarSql(ser_horarios.insertaMatrizDetalle(maximo_primario_detalle, tab_matriz_tem.getValor(i, "ide_yamatz"), tab_matriz_tem.getValor(i, "ide_ystmal"), tab_matriz_tem.getValor(i, "ide_ystnie"), tab_matriz_tem.getValor(i, "ide_ypedpe"), tab_matriz_tem.getValor(i, "ide_ystmat"), tab_matriz_tem.getValor(i, "ide_ystmen"), tab_matriz_tem.getValor(i, "ide_ysttif"), tab_matriz_tem.getValor(i, "ide_ystjor"), tab_matriz_tem.getValor(i, "ide_ymacal"), tab_matriz_tem.getValor(i, "ide_yhogra"), tab_matriz_tem.getValor(i, "ide_ystins"), tab_matriz_tem.getValor(i, "ide_ystpea"), tab_matriz_tem.getValor(i, "aplica_laboratorio_ystmal"),"1", "0", tab_matriz_tem.getValor(i, "ide_ystmod")));
                        
                    }                   
                      
                }
                detalle_hora_cla= ser_horarios.horaClase(tab_matriz_tem.getValor(i,"ide_yamatz"));
                // horas clases de las materias registro de horarios
                if(detalle_hora_cla<hora_semana ){
                    //System.out.println("Ingrese al if de horas clase: "+detalle_hora_cla+" hora_semana "+ hora_semana);
                    TablaGenerica tab_recorrido = utilitario.consultar("select 1 as codigo,((horas_semana_ystmal-horas_semana_lab_ystmal) - mod((horas_semana_ystmal-horas_semana_lab_ystmal),maximo_horas_ystmal))/maximo_horas_ystmal as recorrido,mod((horas_semana_ystmal-horas_semana_lab_ystmal),maximo_horas_ystmal) as modulo\n" +
                    "from yavirac_matriz_temp where ide_yamatz ="+tab_matriz_tem.getValor(i,"ide_yamatz"));
                    modulo_lab = Integer.parseInt(tab_recorrido.getValor("modulo"));
                    recorrido_lab = Integer.parseInt(tab_recorrido.getValor("recorrido"));
                    for(int h=0;h<recorrido_lab;h++){
                        //System.out.println("for horas clase "+h);
                        TablaGenerica tab_cod_maximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_matriz_detalle_temp", "ide_yamadt"));
                        maximo_primario_detalle= tab_cod_maximo.getValor("maximo");
                        utilitario.getConexion().ejecutarSql(ser_horarios.insertaMatrizDetalle(maximo_primario_detalle, tab_matriz_tem.getValor(i, "ide_yamatz"), tab_matriz_tem.getValor(i, "ide_ystmal"), tab_matriz_tem.getValor(i, "ide_ystnie"), tab_matriz_tem.getValor(i, "ide_ypedpe"), tab_matriz_tem.getValor(i, "ide_ystmat"), tab_matriz_tem.getValor(i, "ide_ystmen"), tab_matriz_tem.getValor(i, "ide_ysttif"), tab_matriz_tem.getValor(i, "ide_ystjor"), tab_matriz_tem.getValor(i, "ide_ymacal"), tab_matriz_tem.getValor(i, "ide_yhogra"), tab_matriz_tem.getValor(i, "ide_ystins"), tab_matriz_tem.getValor(i, "ide_ystpea"), tab_matriz_tem.getValor(i, "aplica_laboratorio_ystmal"),"0", tab_matriz_tem.getValor(i, "maximo_horas_ystmal"), tab_matriz_tem.getValor(i, "ide_ystmod")));

                    }
                    for(int n=0;n<modulo_lab;n++){
                        //System.out.println("modulo clases "+modulo_lab);
                        TablaGenerica tab_cod_maximo = utilitario.consultar(ser_estructura_organizacional.getCodigoMaximoTabla("yavirac_matriz_detalle_temp", "ide_yamadt"));
                        maximo_primario_detalle= tab_cod_maximo.getValor("maximo");
                        utilitario.getConexion().ejecutarSql(ser_horarios.insertaMatrizDetalle(maximo_primario_detalle, tab_matriz_tem.getValor(i, "ide_yamatz"), tab_matriz_tem.getValor(i, "ide_ystmal"), tab_matriz_tem.getValor(i, "ide_ystnie"), tab_matriz_tem.getValor(i, "ide_ypedpe"), tab_matriz_tem.getValor(i, "ide_ystmat"), tab_matriz_tem.getValor(i, "ide_ystmen"), tab_matriz_tem.getValor(i, "ide_ysttif"), tab_matriz_tem.getValor(i, "ide_ystjor"), tab_matriz_tem.getValor(i, "ide_ymacal"), tab_matriz_tem.getValor(i, "ide_yhogra"), tab_matriz_tem.getValor(i, "ide_ystins"), tab_matriz_tem.getValor(i, "ide_ystpea"), tab_matriz_tem.getValor(i, "aplica_laboratorio_ystmal"), "0", "1", tab_matriz_tem.getValor(i, "ide_ystmod")));
                        
                    }                   
                      
                }
                
                suma_hora=ser_horarios.sumDetalleMatriz(tab_matriz_tem.getValor(i,"ide_yamatz"));
            } 
            
        }
        // Una vez ejecutado el temproral se actualiza el randomico
        randomicoHorarioTemp();
    }
    
    public void randomicoHorarioTemp(){
        TablaGenerica tab_detalle_matriz=utilitario.consultar("select * from yavirac_matriz_detalle_temp order by ide_yamadt");
        int maximo= tab_detalle_matriz.getTotalFilas();
    
        for(int i=0;i<tab_detalle_matriz.getTotalFilas();i++){
            TablaGenerica tab_randomico= utilitario.consultar("select 1 as codigo,cast((random() * "+maximo+" + 1) as integer) as resul");
            
            utilitario.getConexion().ejecutarSql("update yavirac_matriz_detalle_temp set randomico_yamadt="+tab_randomico.getValor("resul")+" where ide_yamadt="+tab_detalle_matriz.getValor(i, "ide_yamadt"));
        }
    }
    public void horarioClaseConsolidado(){
        // caragamos tabla generica de matriz detalle ordeno por laboratorio para hacer primero esas inserciones
        TablaGenerica tab_detalle_matriz=utilitario.consultar("select * from yavirac_matriz_detalle_temp order by aplica_laboratorio_yamadt desc,randomico_yamadt");
        for(int i=0;i<tab_detalle_matriz.getTotalFilas();i++){
            // verificamos si la materia a ser dada tiene laboratorio
            if(tab_detalle_matriz.getValor(i, "aplica_laboratorio_yamadt").equals("true")){
                
                utilitario.getConexion().ejecutarSql("INSERT INTO yavirac_hora_horario_mate(ide_yhohma, ide_ypedpe, ide_ystmal, ide_yhogra, ide_yhopeh)\n" +
                "values ();");
            }
            else{// con el sisguiente else no palica a laboratorios
                utilitario.getConexion().ejecutarSql("INSERT INTO yavirac_hora_horario_mate(ide_yhohma, ide_ypedpe, ide_ystmal, ide_yhogra, ide_yhopeh)\n" +
                "values ();");
            }
            
        }
    }
    public void horarioClaseTemp(){
        
        utilitario.getConexion().ejecutarSql(ser_horarios.insertHorarioMatriz("2", com_periodo_academico.getValue().toString(), mension, jornada, modalidad ));

    }
    public void horarioLaboratorioTemp(){
        utilitario.getConexion().ejecutarSql(ser_horarios.insertHorarioMatriz("1", com_periodo_academico.getValue().toString(), mension, jornada, modalidad));
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
     if (tab_hora_periodo_hora.isFocus()){
       tab_hora_periodo_hora.guardar();  
     }   
     else if (tab_hora_horario_materia.isFocus()){
       tab_hora_horario_materia.guardar();   
     }
    guardarPantalla();
        
    }

    @Override
    public void eliminar() {
     if (tab_hora_periodo_hora.isFocus()){
       tab_hora_periodo_hora.eliminar();  
     }   
     else if (tab_hora_horario_materia.isFocus()){
       tab_hora_horario_materia.eliminar();   
     }   
     
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

    public Tabla getTab_hora_horario_materia() {
        return tab_hora_horario_materia;
    }

    public void setTab_hora_horario_materia(Tabla tab_hora_horario_materia) {
        this.tab_hora_horario_materia = tab_hora_horario_materia;
    }
    
}
