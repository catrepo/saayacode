

package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

public class HoraTipoHorarioJornada extends Pantalla {
   private Tabla tab_hora_tipo_horario_jornada = new Tabla();
    
    public HoraTipoHorarioJornada(){
    tab_hora_tipo_horario_jornada.setId("tab_hora_tipo_horario_jornada");   //identificador
    tab_hora_tipo_horario_jornada.setTabla("yavirac_hora_tipo_horario_jorna", "ide_yhothj", 1);
    tab_hora_tipo_horario_jornada.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_hora_tipo_horario_jornada = new PanelTabla();
        pat_hora_tipo_horario_jornada.setId("pat_hora_tipo_horario_jornada");
        pat_hora_tipo_horario_jornada.setPanelTabla(tab_hora_tipo_horario_jornada);
        Division div_hora_tipo_horario_jornada = new Division();
        div_hora_tipo_horario_jornada.setId("div_hora_tipo_horario_jornada");
        div_hora_tipo_horario_jornada.dividir1(pat_hora_tipo_horario_jornada);
        agregarComponente(div_hora_tipo_horario_jornada); 
        
    }

    @Override
    public void insertar() {
        tab_hora_tipo_horario_jornada.insertar();
    }

    @Override
    public void guardar() {
        tab_hora_tipo_horario_jornada.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_hora_tipo_horario_jornada.eliminar();
    }

    public Tabla getTab_hora_tipo_horario_jornada() {
        return tab_hora_tipo_horario_jornada;
    }

    public void setTab_hora_tipo_horario_jornada(Tabla tab_hora_tipo_horario_jornada) {
        this.tab_hora_tipo_horario_jornada = tab_hora_tipo_horario_jornada;
    }

 
}
