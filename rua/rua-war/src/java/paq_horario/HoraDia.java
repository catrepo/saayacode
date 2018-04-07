package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class HoraDia extends Pantalla {
    private Tabla tab_hora_dia = new Tabla();
    
    public HoraDia(){
    tab_hora_dia.setId("tab_hora_dia");   //identificador
    tab_hora_dia.setTabla("yavirac_hora_dia", "ide_yhodia", 1);
    tab_hora_dia.getColumna("ide_yhodia").setNombreVisual("CÓDIGO PRINCIPAL");
    tab_hora_dia.getColumna("descripcion_yhodia").setNombreVisual("DESCRIPCIÓN");
    tab_hora_dia.getColumna("abreviatura_yhodia").setNombreVisual("ABREVIATURA");
    tab_hora_dia.getColumna("orden_yhodia").setNombreVisual("Nº ORDEN");
    tab_hora_dia.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_dia = new PanelTabla();
        pat_hora_dia.setId("pat_hora_dia");
        pat_hora_dia.setPanelTabla(tab_hora_dia);
        Division div_hora_dia = new Division();
        div_hora_dia.setId("div_hora_dia");
        div_hora_dia.dividir1(pat_hora_dia);
        agregarComponente(div_hora_dia); 
        
    }

    @Override
    public void insertar() {
        tab_hora_dia.insertar();
    }

    @Override
    public void guardar() {
        tab_hora_dia.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_hora_dia.eliminar();
    }
 
        


    public Tabla getTab_hora_dia() {
        return tab_hora_dia;
    }

    public void setTab_hora_dia(Tabla tab_hora_dia) {
        this.tab_hora_dia = tab_hora_dia;
    
        
        
        
                }
}
