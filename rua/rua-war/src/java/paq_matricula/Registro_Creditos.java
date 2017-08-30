
package paq_matricula;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author PC
 */
public class Registro_Creditos extends Pantalla {

    public Tabla getTab_registro_credito() {
        
        return tab_registro_credito;
    }

    public void setTab_registro_credito(Tabla tab_registro_credito) {
        this.tab_registro_credito = tab_registro_credito;
    }
private Tabla tab_registro_credito = new Tabla();//instanciar tabla del framework

    public Registro_Creditos() {
        tab_registro_credito.setId("tab_actividad_evaluacion");// todo objeto instanciado poner id 
        tab_registro_credito.setTabla("yavirac_nota_actividad_evaluac", "ide_ynoace", 1);  // nombre de la base de datos ii la clave primaria
        tab_registro_credito.dibujar();//dibuja la tabla

        PanelTabla pa_actividad_evaluacion = new PanelTabla();//intanciamos el panel del framework
        pa_actividad_evaluacion.setId("pa_actividad_evaluacion");//nombre id
        pa_actividad_evaluacion.setPanelTabla(tab_registro_credito);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_registro_credito =new Division ();//instanciamos
        div_registro_credito.setId("div_actividad_evaluacion");//es un idientificador
        div_registro_credito.dividir1(tab_registro_credito);
        
        agregarComponente(div_registro_credito);//agregar componente
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
//generar geter and seter
    public Tabla getTab_actividad_evaluacion() {
        return tab_registro_credito;
    }

    public void setTab_actividad_evaluacion(Tabla tab_actividad_evaluacion) {
        this.tab_registro_credito= tab_registro_credito;
    }
    
}
