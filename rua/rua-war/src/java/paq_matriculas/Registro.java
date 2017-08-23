
package paq_matriculas;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author ITSY
 */
public class Registro extends Pantalla{
 private Tabla tab_matri_registro = new Tabla();//instanciar tabla del framework
    public Registro() {
        //constructor
        tab_matri_registro.setId("tab_actividad_evaluacion");// todo objeto instanciado poner id 
        tab_matri_registro.setTabla("yavirac_nota_actividad_evaluac", "ide_ynoace", 1);  // nombre de la base de datos ii la clave primaria
        tab_matri_registro.dibujar();//dibuja la tabla

        PanelTabla pa_matri_registro = new PanelTabla();//intanciamos el panel del framework
        pa_matri_registro.setId("pa_actividad_evaluacion");//nombre id
        pa_matri_registro.setPanelTabla(tab_matri_registro);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_actividad_evaluacion =new Division ();//instanciamos
        div_actividad_evaluacion.setId("div_actividad_evaluacion");//es un idientificador
        div_actividad_evaluacion.dividir1(tab_matri_registro);
        
        agregarComponente(div_actividad_evaluacion);//agregar componente
    }

    public Tabla getTab_matri_registro() {
        return tab_matri_registro;
    }

    public void setTab_matri_registro(Tabla tab_matri_registro) {
        this.tab_matri_registro = tab_matri_registro;
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
