/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msp_ficha_familiar;

import framework.componentes.Arbol;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import msp_ficha_familiar_ejb.ParametrosFichaFamiliar;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class DivisionPolitica extends Pantalla{

    Tabla tab_division_politica=new Tabla();
    Arbol arb_division_politica =new Arbol();
    
       @EJB
    private final ParametrosFichaFamiliar ser_para_ficha_familiar = (ParametrosFichaFamiliar) utilitario.instanciarEJB(ParametrosFichaFamiliar.class);    
 
    public DivisionPolitica(){
    
        tab_division_politica.setId("tab_division_politica");
        tab_division_politica.setTabla("msp_division_politica","ide_msdip",1);
        tab_division_politica.setCampoPadre("msp_ide_msdip");
        tab_division_politica.setCampoNombre("detalle_msdip");
        tab_division_politica.getColumna("ide_mstdp").setCombo(ser_para_ficha_familiar.getSqlTipoDivisionPolitica());
        tab_division_politica.agregarArbol(arb_division_politica);
        tab_division_politica.dibujar();
        //arbol
        arb_division_politica.setId("arb_division_politica");
        arb_division_politica.dibujar();
        
        
        
        PanelTabla pat_division_politica =new PanelTabla();
        pat_division_politica.setPanelTabla(tab_division_politica);
        
        Division div_division_politica= new Division();
        div_division_politica.setId("div_division_politica");
        div_division_politica.dividir2(arb_division_politica,pat_division_politica,"20%","V");
        
        agregarComponente(div_division_politica);
        
    }
    @Override
    public void eliminar() {
        tab_division_politica.eliminar();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        tab_division_politica.guardar();
        guardarPantalla();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar() {
        tab_division_politica.insertar();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_division_politica() {
        return tab_division_politica;
    }

    public void setTab_division_politica(Tabla tab_division_politica) {
        this.tab_division_politica = tab_division_politica;
    }

    public Arbol getArb_division_politica() {
        return arb_division_politica;
    }

    public void setArb_division_politica(Arbol arb_division_politica) {
        this.arb_division_politica = arb_division_politica;
    }
 
    
    
}
