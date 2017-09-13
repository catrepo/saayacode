package paq_nota;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

public class TipoEvaluacion extends Pantalla{
    
 private Tabla tab_tipo_evaluacion=new Tabla(); //intanciamos componente tabla
 
    public TipoEvaluacion(){
        tab_tipo_evaluacion.setId("tab_tipo_evaluacion");
        tab_tipo_evaluacion.setTabla("yavirac_nota_tipo_evaluacion", "ide_ynotie", 1);        
        tab_tipo_evaluacion.dibujar();
        
        PanelTabla pat_tipo_evaluacion = new PanelTabla();
        pat_tipo_evaluacion.setId("pat_tipo_evaluacion");
        pat_tipo_evaluacion.setPanelTabla(tab_tipo_evaluacion);
        
        Division div_tipo_evaluacion = new Division();
        div_tipo_evaluacion.setId("div_tipo_evaluacion");
        div_tipo_evaluacion.dividir1(pat_tipo_evaluacion);
        
        agregarComponente(div_tipo_evaluacion); 
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

    public Tabla getTab_tipo_evaluacion() {
        return tab_tipo_evaluacion;
    }

    public void setTab_tipo_evaluacion(Tabla tab_tipo_evaluacion) {
        this.tab_tipo_evaluacion = tab_tipo_evaluacion;
    }
    
    
}