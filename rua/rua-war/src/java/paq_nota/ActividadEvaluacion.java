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
 * @author ITSY
 */
public class ActividadEvaluacion extends Pantalla {

    private Tabla tab_actividad_evaluacion = new Tabla();//instanciar tabla del framework

    public ActividadEvaluacion() {//constructor
        tab_actividad_evaluacion.setId("tab_actividad_evaluacion");// todo objeto instanciado poner id 
        tab_actividad_evaluacion.setTabla("yavirac_nota_actividad_evaluac", "ide_ynoace", 1);  // nombre de la base de datos ii la clave primaria
        tab_actividad_evaluacion.dibujar();//dibuja la tabla

        PanelTabla pa_actividad_evaluacion = new PanelTabla();//intanciamos el panel del framework
        pa_actividad_evaluacion.setId("pa_actividad_evaluacion");//nombre id
        pa_actividad_evaluacion.setPanelTabla(tab_actividad_evaluacion);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_actividad_evaluacion =new Division ();//instanciamos
        div_actividad_evaluacion.setId("div_actividad_evaluacion");//es un idientificador
        div_actividad_evaluacion.dividir1(tab_actividad_evaluacion);
        
        agregarComponente(div_actividad_evaluacion);//agregar componente
    }

    @Override
    public void insertar() {
        tab_actividad_evaluacion.insertar();
    }

    @Override
    public void guardar() {
        tab_actividad_evaluacion.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_actividad_evaluacion.eliminar();
    }

    //generar geter and seter
    public Tabla getTab_actividad_evaluacion() {
        return tab_actividad_evaluacion;
    }

    public void setTab_actividad_evaluacion(Tabla tab_actividad_evaluacion) {
        this.tab_actividad_evaluacion = tab_actividad_evaluacion;
    }

}
