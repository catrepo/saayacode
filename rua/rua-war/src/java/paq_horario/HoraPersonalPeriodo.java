/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class HoraPersonalPeriodo extends Pantalla {
    private Tabla tab_hora_personal_periodo = new Tabla(); 
    public HoraPersonalPeriodo(){
        tab_hora_personal_periodo.setId("tab_hora_personal_periodo");   //identificador
        tab_hora_personal_periodo.setTabla("yavirac_hora_personal_perio", "ide_yhopep", 1);
        tab_hora_personal_periodo.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_personal_periodo = new PanelTabla();
        pat_hora_personal_periodo.setId("pat_hora_personal_periodo");
        pat_hora_personal_periodo.setPanelTabla(tab_hora_personal_periodo);
        Division div_hora_personal_periodo = new Division();
        div_hora_personal_periodo.setId("div_hora_personal_periodo");
        div_hora_personal_periodo.dividir1(pat_hora_personal_periodo);
        agregarComponente(div_hora_personal_periodo); 
    }
    @Override
    public void insertar() {
        tab_hora_personal_periodo.insertar();
    }

    @Override
    public void guardar() {
        tab_hora_personal_periodo.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_hora_personal_periodo.eliminar();
    }

    public Tabla getTab_hora_personal_periodo() {
        return tab_hora_personal_periodo;
    }

    public void setTab_hora_personal_periodo(Tabla tab_hora_personal_periodo) {
        this.tab_hora_personal_periodo = tab_hora_personal_periodo;
    }
    
}
