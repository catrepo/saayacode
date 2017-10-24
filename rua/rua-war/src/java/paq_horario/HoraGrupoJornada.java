
package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class HoraGrupoJornada extends Pantalla {
    private Tabla tab_grupo_jornada = new Tabla(); 
    
    public HoraGrupoJornada(){
        tab_grupo_jornada.setId("tab_grupo_jornada");   //identificador
        tab_grupo_jornada.setTabla("yavirac_hora_grupo_jornada", "ide_yhogrj", 1);
        tab_grupo_jornada.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_grupo_jornada = new PanelTabla();
        pat_grupo_jornada.setId("pat_grupo_jornada");
        pat_grupo_jornada.setPanelTabla(tab_grupo_jornada);
        Division div_grupo_jornada = new Division();
        div_grupo_jornada.setId("div_grupo_jornada");
        div_grupo_jornada.dividir1(pat_grupo_jornada);
        agregarComponente(div_grupo_jornada); 
    }
    
    
        @Override
    public void insertar() {
        tab_grupo_jornada.insertar();
    }

    @Override
    public void guardar() {
        tab_grupo_jornada.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_grupo_jornada.eliminar();
    }

    public Tabla getTab_grupo_jornada() {
        return tab_grupo_jornada;
    }

    public void setTab_grupo_jornada(Tabla tab_grupo_jornada) {
        this.tab_grupo_jornada = tab_grupo_jornada;
    }
    
    
}
