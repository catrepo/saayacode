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
import framework.componentes.AutoCompletar;
import framework.componentes.Combo;
import framework.componentes.Etiqueta;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import javax.swing.text.TabableView;
import msp_ficha_familiar_ejb.ParametrosFichaFamiliar;
import org.primefaces.event.SelectEvent;
import servicios.sistema.ServicioSeguridad;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class DatosBloque  extends Pantalla{
    
    private Tabla tab_datos_bloque=new Tabla();
    private Arbol ar_datos_bloque=new Arbol();
    private AutoCompletar aut_tipo_bloque=new AutoCompletar(); 
    private Combo com_dat_bloque=new Combo();
            
    
    @EJB
    private final ParametrosFichaFamiliar ser_para_ficha_familiar = (ParametrosFichaFamiliar) utilitario.instanciarEJB(ParametrosFichaFamiliar.class);    
    
    

    public DatosBloque() {
       com_dat_bloque.setId("com_dat_bloque");
       com_dat_bloque.setCombo(ser_para_ficha_familiar.getSqlTipoBloqe());
       com_dat_bloque.setMetodo("actualizarForm");
        Etiqueta eti_tipo_bloque=new Etiqueta("Tipo de Bloque");
        bar_botones.agregarComponente(eti_tipo_bloque);
        bar_botones.agregarComponente(com_dat_bloque);
        
        tab_datos_bloque.setId("tab_datos_bloque");
        tab_datos_bloque.setTabla("msp_datos_bloque", "ide_msdab",1 );
        tab_datos_bloque.setCondicion("ide_mstib = -1");
      
        List lista = new ArrayList();
	       Object fila1[] = {
	           "1", "FINAL"
	       };
	       Object fila2[] = {
	           "2", "GRUPO"
	       };
	       
	       lista.add(fila1);
	       lista.add(fila2);
        tab_datos_bloque.getColumna("tipo_msdab").setCombo(lista);
        //arbol
        tab_datos_bloque.setCampoPadre("msp_ide_msdab");
        tab_datos_bloque.getColumna("ide_mstib").setVisible(false);
        tab_datos_bloque.setCampoNombre("detalle_msdab");
        tab_datos_bloque.agregarArbol(ar_datos_bloque);
        
        tab_datos_bloque.dibujar();
        tab_datos_bloque.imprimirSql();
        ar_datos_bloque.setId("ar_datos_bloque");
        ar_datos_bloque.setCondicion("ide_mstib = -1");
        ar_datos_bloque.dibujar();
        
        
        PanelTabla pt_datos_bloque=new PanelTabla();
        pt_datos_bloque.setPanelTabla(tab_datos_bloque);
        Division div_datos_bloque=new Division();
        div_datos_bloque.setId("div_datos_bloque");
        // div_datos_bloque.dividir1(pt_datos_bloque);
        div_datos_bloque.dividir2(ar_datos_bloque,pt_datos_bloque,"20%","V");
        agregarComponente(div_datos_bloque);
        
    }
    public void actualizarForm(AjaxBehaviorEvent evt){
        
        	//com_dat_bloque.;
		tab_datos_bloque.setCondicion("ide_mstib="+com_dat_bloque.getValue().toString());
		tab_datos_bloque.ejecutarSql();
                ar_datos_bloque.setCondicion("ide_mstib="+com_dat_bloque.getValue().toString());
                ar_datos_bloque.ejecutarSql();
                //System.out.println("arbolll  aa  "+ar_datos_bloque.getSql());
                utilitario.addUpdate("ar_datos_bloque");
    }
     @Override
    public void eliminar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_datos_bloque.eliminar();
    }

    @Override
    public void guardar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tab_datos_bloque.guardar();
       guardarPantalla();
    }

    @Override
    public void insertar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        tab_datos_bloque.insertar();
        tab_datos_bloque.setValor("ide_mstib", com_dat_bloque.getValue().toString());
        utilitario.addUpdateTabla(tab_datos_bloque,"ide_mstib","");
    }


    public Tabla getTab_datos_bloque() {
        return tab_datos_bloque;
    }

    public void setTab_datos_bloque(Tabla tab_datos_bloque) {
        this.tab_datos_bloque = tab_datos_bloque;
    }

    public Arbol getAr_datos_bloque() {
        return ar_datos_bloque;
    }

    public void setAr_datos_bloque(Arbol ar_datos_bloque) {
        this.ar_datos_bloque = ar_datos_bloque;
    }
    
    //Combo

    public Combo getCom_dat_bloque() {
        return com_dat_bloque;
    }

    public void setCom_dat_bloque(Combo com_dat_bloque) {
        this.com_dat_bloque = com_dat_bloque;
    }
    
    

    
    
    
    
    
    
    
    
}
