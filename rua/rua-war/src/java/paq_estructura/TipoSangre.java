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
public class TipoSangre extends Pantalla{
    Tabla tab_tipo_sangre = new Tabla();// importar tabla
    public TipoSangre(){//constructor
    tab_tipo_sangre.setId("tab_tipo_sangre");// todo objeto instanciado poner id
    
    tab_tipo_sangre.setTabla("yavirac_stror_tipo_sangre","ide_ystnac",1); // nom bdd
    tab_tipo_sangre.dibujar();
            
    PanelTabla pa_tipo_sangre = new PanelTabla();
    pa_tipo_sangre.setId("pa_tipo_sangre");
    pa_tipo_sangre.setPanelTabla(tab_tipo_sangre);
    
    Division div_tipo_sangre = new Division();
    div_tipo_sangre.setId("tabla_tipo_sangre");
    
    agregarComponente(div_tipo_sangre);   
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
    
}
