/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Janeth Pullotasig and  Nicolas Cajilema
 */
public class DiaAsistencia extends Pantalla {
    
    private Tabla tab_diaasistencia = new Tabla();
 
   public DiaAsistencia() {
       
       tab_diaasistencia.setId("tab_diaasistencia");
       tab_diaasistencia.setTabla("yavirac_asis_dia_asistencia","ide_yasdas",1);
       tab_diaasistencia.setHeader("DIA ASISTENCIA");
       tab_diaasistencia.dibujar();
        
        
        PanelTabla pat_diaasistencia = new PanelTabla();
        pat_diaasistencia.setId("pat_diaasistencia");
        pat_diaasistencia.setPanelTabla(tab_diaasistencia);
       
        Division div_diaasistencia = new Division();
        div_diaasistencia.setId("div_diaasistencia");
        div_diaasistencia.dividir1(pat_diaasistencia);
        
        agregarComponente(div_diaasistencia);
    
      } 
    @Override
    public void insertar() {
        tab_diaasistencia.insertar();
    }

    @Override
    public void guardar() {
        tab_diaasistencia.guardar();
        guardarPantalla();
        
    }

    @Override
    public void eliminar() {
        tab_diaasistencia.eliminar();
    }

    public Tabla getTab_diaasistencia() {
        return tab_diaasistencia;
    }

    public void setTab_diaasistencia(Tabla tab_diaasistencia) {
        this.tab_diaasistencia = tab_diaasistencia;
    }

}
