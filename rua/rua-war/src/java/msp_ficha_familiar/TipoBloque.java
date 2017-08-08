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
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class TipoBloque  extends Pantalla{
    
    Tabla tab_tipo_bloque=new Tabla();
    Arbol ar_tipo_bloque=new Arbol();
    
    
    public TipoBloque(){
        tab_tipo_bloque.setId("tab_tipo_bloque");
        tab_tipo_bloque.setTabla("msp_tipo_bloque", "ide_mstib", 1);
        //arbol
         tab_tipo_bloque.setCampoPadre("msp_ide_mstib");
        tab_tipo_bloque.setCampoNombre("detalle_mstib");
        tab_tipo_bloque.agregarArbol(ar_tipo_bloque);
        
        tab_tipo_bloque.dibujar();
        
        ar_tipo_bloque.setId("ar_tipo_bloque");
        ar_tipo_bloque.dibujar();
        
        PanelTabla pt_tipo_bloque=new PanelTabla();
        pt_tipo_bloque.setPanelTabla(tab_tipo_bloque);
        Division div_tipo_bloque=new Division();
        div_tipo_bloque.setId("div_tipo_bloque");         
        div_tipo_bloque.dividir2(ar_tipo_bloque,pt_tipo_bloque,"20%","V");
       agregarComponente(div_tipo_bloque);
        
        
    }
      @Override
    public void eliminar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_tipo_bloque.eliminar();
    }

    @Override
    public void guardar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_tipo_bloque.guardar();
       guardarPantalla();
    }

    @Override
    public void insertar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_tipo_bloque.insertar();
    }

    

    public Tabla getTab_tipo_bloque() {
        return tab_tipo_bloque;
    }

    public void setTab_tipo_bloque(Tabla tab_tipo_bloque) {
        this.tab_tipo_bloque = tab_tipo_bloque;
    }

    public Arbol getAr_tipo_bloque() {
        return ar_tipo_bloque;
    }

    public void setAr_tipo_bloque(Arbol ar_tipo_bloque) {
        this.ar_tipo_bloque = ar_tipo_bloque;
    }
    
    
    
}
