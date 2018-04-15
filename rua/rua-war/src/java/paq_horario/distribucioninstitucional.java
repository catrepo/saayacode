/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horario;

import sistema.aplicacion.Pantalla;
import framework.componentes.Arbol;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;


/**
 *
 * @author Jenny
 */
public class distribucioninstitucional extends Pantalla {

    private Arbol arb_arbol = new Arbol();
    private Tabla tab_tabla1 = new Tabla();
    private Tabla tab_tabla2 = new Tabla();


    public distribucioninstitucional() {
        
        Tabulador tab_tabulador = new Tabulador();
        tab_tabulador.setId("tab_tabulador");
        
        tab_tabla1.setId("tab_tabla1");
        tab_tabla1.setTabla("yavirac_hora_distribuc_instit", "ide_yhodin", 1);
        tab_tabla1.setCampoPadre("yav_ide_yhodin");
        tab_tabla1.setCampoNombre("descripcion_yhodin");
        tab_tabla1.agregarArbol(arb_arbol);
        tab_tabla1.dibujar();
        PanelTabla pat_panel = new PanelTabla();
        pat_panel.setPanelTabla(tab_tabla1);

        arb_arbol.setId("arb_arbol");
        arb_arbol.dibujar();
        
        tab_tabla2.setId("tab_tabla2");
        tab_tabla2.setIdCompleto("tab_tabulador:tab_tabla2");
        tab_tabla2.setTabla("yavirac_hora_disponibi_laborat", "ide_yhodil", 2);
        tab_tabla2.dibujar();
        PanelTabla pat_panel2 = new PanelTabla();
        pat_panel2.setPanelTabla(tab_tabla2);

        
        tab_tabulador.agregarTab("DISPONIBLIDAD LABORATORIO", pat_panel2);

        
        
        Division div_division = new Division();
        div_division.setId("div_division");
        div_division.dividir2(pat_panel,tab_tabulador , "50%", "H");
        agregarComponente(div_division);
        
         Division div_division2 = new Division();
        div_division2.setId("div_division2");
        div_division2.dividir2(arb_arbol, div_division, "21%", "V");  //arbol y div3
        agregarComponente(div_division2);
    }

    @Override
    public void insertar() {
        tab_tabla1.insertar();
    }

    @Override
    public void guardar() {
        if (tab_tabla1.guardar()) {
            guardarPantalla();
        }
    }

    @Override
    public void eliminar() {
        tab_tabla1.eliminar();
    }

    public Arbol getArb_arbol() {
        return arb_arbol;
    }

    public void setArb_arbol(Arbol arb_arbol) {
        this.arb_arbol = arb_arbol;
    }

    public Tabla getTab_tabla1() {
        return tab_tabla1;
    }

    public void setTab_tabla1(Tabla tab_tabla1) {
        this.tab_tabla1 = tab_tabla1;
    }

    public Tabla getTab_tabla2() {
        return tab_tabla2;
    }

    public void setTab_tabla2(Tabla tab_tabla2) {
        this.tab_tabla2 = tab_tabla2;
    }
    
}
