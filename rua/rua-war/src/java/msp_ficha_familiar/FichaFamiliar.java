/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msp_ficha_familiar;

import framework.componentes.MenuPanel;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class FichaFamiliar extends Pantalla{


    private final MenuPanel mep_menu = new MenuPanel();
    public FichaFamiliar (){
        
        mep_menu.setMenuPanel("OPCIONES FICHA", "20%");
        mep_menu.agregarItem("Listado de Facturas", "dibujarFacturas", "ui-icon-note");
        mep_menu.agregarItem("Generar Asiento Contable", "dibujarFacturasNoContabilizadas", "ui-icon-notice");
        mep_menu.agregarItem("Facturas Anuladas", "dibujarFacturasAnuladas", "ui-icon-cancel");
        mep_menu.agregarItem("Facturas Por Cobrar", "dibujarFacturasPorCobrar", "ui-icon-calculator");
        mep_menu.agregarSubMenu("INFORMES");
        mep_menu.agregarItem("Grafico de Ventas", "dibujarGraficoVentas", "ui-icon-clock");
        // mep_menu.agregarItem("Estadística de Ventas", "dibujarEstadisticas", "ui-icon-bookmark");
        mep_menu.agregarItem("Reporte de Ventas", "dibujarReporteVentas", "ui-icon-calendar");
        mep_menu.agregarSubMenu("FACTURACIÓN ELECTRÓNICA");
        mep_menu.agregarItem("Facturas Eléctrónicas", "dibujarFacturaElectronica", "ui-icon-signal-diag");
        agregarComponente(mep_menu);
        
    }
    
    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


}

