/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_estructura;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
/**
 *
 * @author ITSY
 */
public class PeriodoAcademico extends Pantalla{
   
    Tabla tab_periodo_academic = new Tabla (); // importar tabla 
    
    public PeriodoAcademico (){
           
        
        tab_periodo_academic.setId("tab_periodo_academico");  // todo objeto instanciado poner id 
        
        tab_periodo_academic.setTabla("yavirac_stror_periodo_academic","ide_ytiace",1);    // nom bdd
        tab_periodo_academic.dibujar();
        
        PanelTabla pa_periodoacademico = new PanelTabla();
        pa_periodoacademico.setId("pa_periodoacademico"); // nombre de i
        pa_periodoacademico.setPanelTabla(tab_periodo_academic);
        
        Division div_periodoacademico = new Division();
        div_periodoacademico.setId("div_periodoacademico");
        div_periodoacademico.dividir1(pa_periodoacademico);
        
        agregarComponente(div_periodoacademico);
    }

    @Override
    public void insertar() {
        tab_periodo_academic.insertar();
    }

    @Override
    public void guardar() {
        tab_periodo_academic.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
      tab_periodo_academic.eliminar();
      
    }

    public Tabla getTab_periodo_academic() {
        return tab_periodo_academic;
    }

    public void setTab_periodo_academic(Tabla tab_periodo_academic) {
        this.tab_periodo_academic = tab_periodo_academic;
    }
    
    
    }
    
    

