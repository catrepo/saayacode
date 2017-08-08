package mspficha;



import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class ViaAcceso extends Pantalla
{
     Tabla tab_tabla = new Tabla();

     public ViaAcceso(){
        
        tab_tabla.setId("tab_tabla");
        tab_tabla.setTabla("vias_acceso", "id_via_acc", 1);
        tab_tabla.setGenerarPrimaria(false);  
        tab_tabla.getColumna("id_via_acc").setLectura(true);
        tab_tabla.getColumna("activo_via_acc").setValorDefecto("true");
        
        tab_tabla.dibujar();
        
        PanelTabla pat_panel = new PanelTabla();
        pat_panel.setPanelTabla(tab_tabla);
        
        Division div_division= new Division();
        div_division.setId("div_division");
        div_division.dividir1(pat_panel);
        
        agregarComponente(div_division);
         
                  
     }
    
    @Override
    public void insertar() {
        tab_tabla.insertar();
    }

    @Override
    public void guardar() {
        tab_tabla.guardar();
        guardarPantalla();
        tab_tabla.ejecutarSql();
    }

    @Override
    public void eliminar() {
        tab_tabla.eliminar();
    }    

    public Tabla getTab_tabla() {
        return tab_tabla;
    }

    public void setTab_tabla(Tabla tab_tabla) {
        this.tab_tabla = tab_tabla;
    }
    
    
}

