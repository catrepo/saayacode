
package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class HoraHorarioDocente extends Pantalla {
    private Tabla tab_hora_horario_docente = new Tabla(); 
    public HoraHorarioDocente(){
        tab_hora_horario_docente.setId("tab_hora_horario_docente");   //identificador
        tab_hora_horario_docente.setTabla("yavirac_hora_horario_docente", "ide_yhohod", 1);
        tab_hora_horario_docente.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_horario_docente = new PanelTabla();
        pat_hora_horario_docente.setId("pat_grupo_mension");
        pat_hora_horario_docente.setPanelTabla(tab_hora_horario_docente);
        Division div_hora_horario_docente = new Division();
        div_hora_horario_docente.setId("div_hora_horario_docente");
        div_hora_horario_docente.dividir1(pat_hora_horario_docente);
        agregarComponente(div_hora_horario_docente); 
    }
    @Override
    public void insertar() {
        tab_hora_horario_docente.insertar();
    }

    @Override
    public void guardar() {
        tab_hora_horario_docente.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_hora_horario_docente.eliminar();
    }

    public Tabla getTab_hora_horario_docente() {
        return tab_hora_horario_docente;
    }

    public void setTab_hora_horario_docente(Tabla tab_hora_horario_docente) {
        this.tab_hora_horario_docente = tab_hora_horario_docente;
    }
    
}
