
package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class HoraGrupoMension extends Pantalla {
    
    private Tabla tab_grupo_mension = new Tabla(); 
    
    public HoraGrupoMension(){
        tab_grupo_mension.setId("tab_grupo_mension");   //identificador
        tab_grupo_mension.setTabla("yavirac_hora_grupo_mension", "ide_yhogrm", 1);
        tab_grupo_mension.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_grupo_mension = new PanelTabla();
        pat_grupo_mension.setId("pat_grupo_mension");
        pat_grupo_mension.setPanelTabla(tab_grupo_mension);
        Division div_grupo_mension = new Division();
        div_grupo_mension.setId("div_grupo_mension");
        div_grupo_mension.dividir1(pat_grupo_mension);
        agregarComponente(div_grupo_mension); 
    }
    @Override
    public void insertar() {
        tab_grupo_mension.insertar();
    }

    @Override
    public void guardar() {
        tab_grupo_mension.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_grupo_mension.eliminar();
    }

    public Tabla getTab_grupo_mension() {
        return tab_grupo_mension;
    }

    public void setTab_grupo_mension(Tabla tab_grupo_mension) {
        this.tab_grupo_mension = tab_grupo_mension;
    }
    
}
