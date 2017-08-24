/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author usuario
 */
public class ActividadTrabajo extends Pantalla{
    
    private Tabla tab_actividad_trabajo = new Tabla();//instanciar tabla del framework

    public ActividadTrabajo() {//constructor
        tab_actividad_trabajo.setId("tab_actividad_trabajo");// todo objeto instanciado poner id 
        tab_actividad_trabajo.setTabla("yavirac_nota_actividad_trabajo", "ide_ynoact", 1);  // nombre de la base de datos ii la clave primaria
        tab_actividad_trabajo.dibujar();//dibuja la tabla

        PanelTabla pa_actividad_trabajo = new PanelTabla();//intanciamos el panel del framework
        pa_actividad_trabajo.setId("pa_actividad_trabajo");//nombre id
        pa_actividad_trabajo.setPanelTabla(tab_actividad_trabajo);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_actividad_trabajo =new Division ();//instanciamos
        div_actividad_trabajo.setId("div_actividad_trabajo");//es un idientificador
        div_actividad_trabajo.dividir1(tab_actividad_trabajo);
        
        agregarComponente(div_actividad_trabajo);//agregar componente
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
