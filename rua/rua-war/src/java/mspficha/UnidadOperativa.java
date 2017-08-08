/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mspficha;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import msp_ficha_familiar_ejb.FormularioServicios;
import sistema.aplicacion.Pantalla;



/**
 *
 * @author oscarpatricio
 */
public class UnidadOperativa extends Pantalla{
    
    Tabla tab_unidad_operativa = new Tabla();
    @EJB
    private final FormularioServicios ser_formulario = (FormularioServicios) utilitario.instanciarEJB(FormularioServicios.class);    
            
    public UnidadOperativa(){
        
        tab_unidad_operativa.setId("tab_unidad_operativa");
        tab_unidad_operativa.setTabla("unidad_operativa", "id_unid_oper", 1);
        tab_unidad_operativa.setGenerarPrimaria(false);
        tab_unidad_operativa.getColumna("id_unid_oper").setLectura(true);
        tab_unidad_operativa.getColumna("id_parroquia").setEstilo("width: 250px;");
        tab_unidad_operativa.getColumna("id_parroquia").setCombo(ser_formulario.getSqlProvinciaCantonParroquia());
        tab_unidad_operativa.dibujar();
        
        PanelTabla pat_panel = new PanelTabla();
        pat_panel.setPanelTabla(tab_unidad_operativa);
        
        Division div_unidad_operatva= new Division();
        div_unidad_operatva.setId("div_unidad_operatva");
        div_unidad_operatva.dividir1(pat_panel);
        
        agregarComponente(div_unidad_operatva);
    }
    @Override
    public void insertar() {
        tab_unidad_operativa.insertar();
    }

    @Override
    public void guardar() {
        tab_unidad_operativa.guardar();
        guardarPantalla();
        tab_unidad_operativa.ejecutarSql();
    }

    @Override
    public void eliminar() {
        tab_unidad_operativa.eliminar();
    }

    public Tabla getTab_unidad_operativa() {
        return tab_unidad_operativa;
    }

    public void setTab_unidad_operativa(Tabla tab_unidad_operativa) {
        this.tab_unidad_operativa = tab_unidad_operativa;
    }
      
    
}
