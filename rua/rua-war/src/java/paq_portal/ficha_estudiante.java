/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_portal;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import javax.ejb.EJB;
import paq_alumno.ejb.ServicioAlumno;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author OROCHII VEGA
 */
public class ficha_estudiante extends Pantalla {

    Tabla tab_tabla1 = new Tabla();
    Tabla tab_tabla2 = new Tabla();
    Tabla tab_tabla3 = new Tabla();

    @EJB
    private final ServicioAlumno servicioAlumno = (ServicioAlumno) utilitario.instanciarEJB(ServicioAlumno.class);

    public ficha_estudiante() {
        //Permite crear la tabla 
        tab_tabla1.setId("tab_tabla1");
        tab_tabla1.setTabla("yavirac_alum_dato_personal", "ide_yaldap", 1);
        tab_tabla1.dibujar();

        Tabulador tab_tabulador = new Tabulador();
        tab_tabulador.setId("tab_tabulador");

        //Es el contenedor de la tabla
        PanelTabla pat_panel1 = new PanelTabla();
        pat_panel1.setId("pat_panel1");
        pat_panel1.setPanelTabla(tab_tabla1);

        tab_tabla2.setId("tab_tabla2");
        tab_tabla2.setTabla("yavirac_alum_telefono", "ide_yaltel", 2);
        tab_tabla2.setIdCompleto("tab_tabulador:tab_tabla2");
        tab_tabla2.dibujar();

        PanelTabla pat_panel2 = new PanelTabla();
        pat_panel2.setId("pat_panel2");
        pat_panel2.setPanelTabla(tab_tabla2);

        tab_tabla3.setId("tab_tabla3");
        tab_tabla3.setTabla("yavirac_alum_correo", "ide_yalcor", 3);
        tab_tabla3.setIdCompleto("tab_tabulador:tab_tabla3");
        tab_tabla3.dibujar();

        PanelTabla pat_panel3 = new PanelTabla();
        pat_panel3.setId("pat_panel3");
        pat_panel3.setPanelTabla(tab_tabla3);
        tab_tabulador.agregarTab("DATOS PERSONALES", pat_panel1);
        tab_tabulador.agregarTab("ALUMNO TELEFONO", pat_panel2);
        tab_tabulador.agregarTab("ALUMNO CORREO", pat_panel3);

        //Permite la dision de la pantalla
        Division div_division1 = new Division();
        div_division1.setId("div_division1");
        div_division1.dividir1(tab_tabulador);

        agregarComponente(div_division1);

    }

    @Override
    public void insertar() {
        tab_tabla1.insertar();
        insertar();
    }

    @Override
    public void guardar() {

    }

    @Override
    public void eliminar() {

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

    public Tabla getTab_tabla3() {
        return tab_tabla3;
    }

    public void setTab_tabla3(Tabla tab_tabla3) {
        this.tab_tabla3 = tab_tabla3;
    }
    
}
