/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import framework.componentes.PanelTabla;
import sistema.aplicacion.Utilitario;
import framework.componentes.Tabulador;

/**
 *
 * @author Martha
 */
public class LibretaPractica extends Pantalla{
   private  Tabla tab_libreta_practica = new Tabla();
   private  Tabla tab_anexo_libreta = new Tabla();
   private  Tabla tab_horario_practica = new Tabla();
    
    public LibretaPractica(){
        
        tab_libreta_practica.setId("tab_libreta_practica");
        tab_libreta_practica.setTabla("yavirac_titu_libreta_practica", "ide_ytilpr",1);
        tab_libreta_practica.setHeader("LIBRETA DE PRACTICA");
        tab_libreta_practica.getColumna("ide_ytilpr"). setNombreVisual("CÓDIGO");
        tab_libreta_practica.getColumna("ide_yaldap"). setNombreVisual("CÓDIGO");
        tab_libreta_practica.getColumna("ide_ystmen"). setNombreVisual("CÓDIGO");
        tab_libreta_practica.getColumna("ide_ypedpe"). setNombreVisual("CÓDIGO");
        tab_libreta_practica.getColumna("yav_ide_ypedpe"). setNombreVisual("CÓDIGO");
        tab_libreta_practica.getColumna("yav_ide_ypedpe2"). setNombreVisual("CÓDIGO");
        tab_libreta_practica.getColumna("ide_ytiemp"). setNombreVisual("CÓDIGO");
        tab_libreta_practica.getColumna("fecha_desde_ytilpr"). setNombreVisual("FECHA DESDE");
        tab_libreta_practica.getColumna("fecha_hasta_ytilpr"). setNombreVisual("FECHA HASTA");
        tab_libreta_practica.getColumna("resumen_trabajo_ytilpr"). setNombreVisual("RESUEMN DEL TRABAJO");
        tab_libreta_practica.getColumna("nombre_ytilpr"). setNombreVisual("NOMBRE");
        tab_libreta_practica.getColumna("direccion_ytilpr"). setNombreVisual("DIRECCION");
        tab_libreta_practica.getColumna("telefono_ytilpr"). setNombreVisual("TELEFONO");
        tab_libreta_practica.getColumna("actividades_ytilpr"). setNombreVisual("ACTIVIDAD");
        tab_libreta_practica.getColumna("observaciones_ytilpr"). setNombreVisual("OBSERVACION");
        tab_libreta_practica.agregarRelacion(tab_anexo_libreta);
        tab_libreta_practica.agregarRelacion(tab_horario_practica);
            
        tab_libreta_practica.setTipoFormulario(true);//para que se haga un formulario
        tab_libreta_practica.getGrid().setColumns(2); //numero de columnas del formulario
        tab_libreta_practica.dibujar();
        
         PanelTabla pat_libreta_practica = new PanelTabla();
         pat_libreta_practica.setId("pat_libreta_practica");
         pat_libreta_practica.setPanelTabla(tab_libreta_practica);
               
        tab_horario_practica.setId("tab_horario_practica");
        tab_horario_practica.setTabla("yavirac_titu_horario_practica", "ide_ytihpr",3);
        tab_horario_practica.setHeader("HORARIO DE PRACTICAS");
        tab_horario_practica.getColumna(" ide_ytihpr "). setNombreVisual("CÓDIGO");
        tab_horario_practica.getColumna("ide_ytilpr "). setNombreVisual("CÓDIGO SOLICITADO");
        tab_horario_practica.getColumna("ide_yhodia "). setNombreVisual("CÓDIGO");
        tab_horario_practica.getColumna("hora_inicio_ytihpr "). setNombreVisual("FECHA ANEXO");
        tab_horario_practica.getColumna("hora_fin_ytihpr "). setNombreVisual("ARCHIVO ANEXO");
        tab_horario_practica.getColumna("numero_horas_ytihpr "). setNombreVisual("OBSERVACION");
        tab_horario_practica.dibujar();
        
        PanelTabla pat_horario_practica = new PanelTabla();
        pat_horario_practica.setId("pat_horario_practica");
        pat_horario_practica.setPanelTabla(tab_horario_practica);
        
        tab_anexo_libreta.setId("tab_anexo_libreta");
        tab_anexo_libreta.setTabla("yavirac_titu_anexo_libreta", "ide_ytiali",2);
        tab_anexo_libreta.setHeader("ANEXO DE LA LIBRETA");
        tab_anexo_libreta.getColumna(" ide_ytiali"). setNombreVisual("CÓDIGO DEL ANEXO DE LA LIBRETA");
        tab_anexo_libreta.getColumna("fecha_ytiali"). setNombreVisual("FECHA DE ANEXO DE LA LIBRETA");
        tab_anexo_libreta.getColumna("archivo_ytiali"). setNombreVisual("ARCHIVO DEL ANEXO");
        tab_anexo_libreta.getColumna("observaciones_ytiali"). setNombreVisual("OBSERVACION DEL ANEXO");
        tab_anexo_libreta.dibujar();
        
        PanelTabla pat_anexo_libreta = new PanelTabla();
        pat_anexo_libreta.setId("pat_anexo_libreta");
        pat_anexo_libreta.setPanelTabla(tab_anexo_libreta);
              
        Tabulador tab_tabulador = new Tabulador();
            tab_tabulador.setId("tab_tabulador");

        //*******************************AGREGA PESTAÑANAS*********************************************//
            tab_tabulador.agregarTab("HORARIO DE PRACTICAS", pat_horario_practica);
            tab_tabulador.agregarTab("ANEXO DE LIBRETAS", pat_anexo_libreta);

            //instanciar una division del framework
            Division div_libreta_practica = new Division();//instanciamos
            div_libreta_practica.setId("div_libreta_practica");//es un idientificador
            div_libreta_practica.dividir2(pat_libreta_practica, tab_tabulador, "50%", "H");
            agregarComponente(div_libreta_practica);
    }

    @Override
    public void insertar() {
        tab_libreta_practica.insertar();
    
    }

    @Override
    public void guardar() {
        tab_libreta_practica.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_libreta_practica.eliminar(); 
    }

    public Utilitario getUtilitario() {
        return utilitario;
    }

    public void setUtilitario(Utilitario utilitario) {
        this.utilitario = utilitario;
    }

    public Barra getBar_botones() {
        return bar_botones;
    }

    public void setBar_botones(Barra bar_botones) {
        this.bar_botones = bar_botones;
    }

    public Grupo getGru_pantalla() {
        return gru_pantalla;
    }

    public void setGru_pantalla(Grupo gru_pantalla) {
        this.gru_pantalla = gru_pantalla;
    }

    public Tabla getTab_libreta_practica() {
        return tab_libreta_practica;
    }

    public void setTab_libreta_practica(Tabla tab_libreta_practica) {
        this.tab_libreta_practica = tab_libreta_practica;
    }

    public Tabla getTab_anexo_libreta() {
        return tab_anexo_libreta;
    }

    public void setTab_anexo_libreta(Tabla tab_anexo_libreta) {
        this.tab_anexo_libreta = tab_anexo_libreta;
    }

    public Tabla getTab_horario_practica() {
        return tab_horario_practica;
    }

    public void setTab_horario_practica(Tabla tab_horario_practica) {
        this.tab_horario_practica = tab_horario_practica;
    }

    
    
}
