/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mspficha;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class EstadoCivil extends Pantalla{
    
    Tabla tab_estado_civil =new Tabla();

    public EstadoCivil(){
        
        
        tab_estado_civil.setId("tab_estado_civil");
        tab_estado_civil.setTabla("estado_civil", "id_est_civ", 1);
        tab_estado_civil.dibujar();
        
        PanelTabla pat_estado_civil = new PanelTabla();
        pat_estado_civil.setPanelTabla(tab_estado_civil);
        
        Division div_estado_civil = new Division();
        div_estado_civil.dividir1(pat_estado_civil);
        
        agregarComponente(div_estado_civil);
        
    }
    
    
    @Override
    public void insertar() {
        tab_estado_civil.insertar();
    }

    @Override
    public void guardar() {
        tab_estado_civil.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_estado_civil.eliminar();
       
    }

    public Tabla getTab_estado_civil() {
        return tab_estado_civil;
    }

    public void setTab_estado_civil(Tabla tab_estado_civil) {
        this.tab_estado_civil = tab_estado_civil;
    }
    
    
    
    
}
