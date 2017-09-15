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
           
        
        tab_periodo_academic.setId("tab_periodo_academic");  // todo objeto instanciado poner id 
        
        tab_periodo_academic.setTabla("yavirac_stror_periodo_academic","ide_ytiace",1);    // nom bdd
        tab_periodo_academic.dibujar();
        
        PanelTabla pa_periodoacademico = new PanelTabla();
        pa_periodoacademico.setId("pa_PeriodoAcademico"); // nombre de i
        pa_periodoacademico.setPanelTabla(tab_periodo_academic);
        
        Division div_periodoacademico = new Division();
        div_periodoacademico.setId("div_nacionalidad");
        div_periodoacademico.dividir1(tab_periodo_academic);
        
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
    
    
    }
    
    

