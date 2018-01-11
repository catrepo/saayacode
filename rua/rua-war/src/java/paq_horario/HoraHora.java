package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class HoraHora extends Pantalla{
  private Tabla tab_hora_hora = new Tabla();
  
    public HoraHora(){
    tab_hora_hora.setId("tab_hora_hora");   //identificador
    tab_hora_hora.setTabla("yavirac_hora_hora", "ide_yhohor", 1);
    tab_hora_hora.getColumna("ide_yhohor").setNombreVisual("CODIGO");
    tab_hora_hora.getColumna("descripcion_yhohor").setNombreVisual("DESCRIPCION");
    tab_hora_hora.getColumna("abreviatura_yhohor").setNombreVisual("ABREVIATURA");
    tab_hora_hora.getColumna("orden_yhohor").setNombreVisual("ORDEN");
    tab_hora_hora.getColumna("activo_yhohor").setNombreVisual("ACTIVO");
    tab_hora_hora.dibujar();
        /*agregarComponente(tab_hora_hora);*/ 
        
        PanelTabla pat_hora_hora = new PanelTabla();
        pat_hora_hora.setId("pat_hora_hora");
        pat_hora_hora.setPanelTabla(tab_hora_hora);
        Division div_hora_hora = new Division();
        div_hora_hora.setId("div_hora_hora");
        div_hora_hora.dividir1(pat_hora_hora);
        agregarComponente(div_hora_hora); 
        
        
        
                }
    
 
    
    @Override
    public void insertar() {
        tab_hora_hora.insertar();
    }

    @Override
    public void guardar() {
        
    tab_hora_hora.guardar();
    guardarPantalla();
        
    }

    @Override
    public void eliminar() {
     tab_hora_hora.eliminar();
     
    }

    public Tabla getTab_hora_hora() {
        return tab_hora_hora;
    }

    public void setTab_hora_hora(Tabla tab_hora_hora) {
        this.tab_hora_hora = tab_hora_hora;
    }

    
}
