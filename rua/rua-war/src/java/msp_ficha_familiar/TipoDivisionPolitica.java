/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msp_ficha_familiar;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class TipoDivisionPolitica extends Pantalla{

    private Tabla tab_tipo_division_politica= new Tabla();
    public TipoDivisionPolitica() {
        
        tab_tipo_division_politica.setId("tab_tipo_division_politica");
        tab_tipo_division_politica.setTabla("msp_tipo_division_politica", "ide_mstdp", 1);
        tab_tipo_division_politica.dibujar();
        
        PanelTabla pat_tabla_tipo = new PanelTabla();
        pat_tabla_tipo.setPanelTabla(tab_tipo_division_politica);
        
        Division div_tipo_division = new Division();
        div_tipo_division.setId("div_tipo_division");
        div_tipo_division.dividir1(pat_tabla_tipo);
        
        agregarComponente(div_tipo_division);
        
    }
    
    @Override
    public void guardar() {
        tab_tipo_division_politica.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
                tab_tipo_division_politica.eliminar();
                

    }

    @Override
    public void insertar() {
        tab_tipo_division_politica.insertar();
    }

    public Tabla getTab_tipo_division_politica() {
        return tab_tipo_division_politica;
    }

    public void setTab_tipo_division_politica(Tabla tab_tipo_division_politica) {
        this.tab_tipo_division_politica = tab_tipo_division_politica;
    }
  
}
