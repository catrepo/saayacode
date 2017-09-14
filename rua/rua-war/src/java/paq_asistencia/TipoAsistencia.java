/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Nicolas
 */
public class TipoAsistencia extends Pantalla {
    
    private Tabla tab_tipoasistencia = new Tabla();
 
   public TipoAsistencia() {
       
       tab_tipoasistencia.setId("tab_tipoasistencia");
       tab_tipoasistencia.setTabla("tipoasistencia","ide_yastas",1);
       tab_tipoasistencia.dibujar();
        
        
        PanelTabla pat_tipoasistencia = new PanelTabla();
        pat_tipoasistencia.setId("pat_tipoasistencia");
        pat_tipoasistencia.setPanelTabla(tab_tipoasistencia);
       
        Division div_tipoasistencia = new Division();
        div_tipoasistencia.setId("div_tipoasistencia");
        div_tipoasistencia.dividir1(pat_tipoasistencia);
        
        agregarComponente(div_tipoasistencia);

      } 
    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_tipoasistencia() {
        return tab_tipoasistencia;
    }

    public void setTab_tipoasistencia(Tabla tab_tipoasistencia) {
        this.tab_tipoasistencia = tab_tipoasistencia;
    }
    
  }
