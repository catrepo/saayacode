/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_matricula;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author PC
 */
public class Anulacion_creditos extends Pantalla {
 private Tabla tab_anulacion_credito = new Tabla();//instanciar tabla del framework

    public Anulacion_creditos() {
         tab_anulacion_credito.setId("tab_actividad_evaluacion");// todo objeto instanciado poner id 
        tab_anulacion_credito.setTabla("yavirac_nota_actividad_evaluac", "ide_ynoace", 1);  // nombre de la base de datos ii la clave primaria
        tab_anulacion_credito.dibujar();//dibuja la tabla

        PanelTabla pa_anulacion_credito = new PanelTabla();//intanciamos el panel del framework
        pa_anulacion_credito.setId("pa_actividad_evaluacion");//nombre id
        pa_anulacion_credito.setPanelTabla(tab_anulacion_credito);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_anulacion_credito =new Division ();//instanciamos
        div_anulacion_credito.setId("div_actividad_evaluacion");//es un idientificador
        div_anulacion_credito.dividir1(tab_anulacion_credito);
        
        agregarComponente(div_anulacion_credito);//agregar componente
    }

    public Tabla getTab_anulacion_credito() {
        return tab_anulacion_credito;
    }

    public void setTab_anulacion_credito(Tabla tab_anulacion_credito) {
        this.tab_anulacion_credito = tab_anulacion_credito;
    }

    public Utilitario getUtilitario() {
        return utilitario;
    }

    public void setUtilitario(Utilitario utilitario) {
        this.utilitario = utilitario;
    }

    public Barra getBar_botones() {
        return bar_botones;
    }

    public void setBar_botones(Barra bar_botones) {
        this.bar_botones = bar_botones;
    }

    public Grupo getGru_pantalla() {
        return gru_pantalla;
    }

    public void setGru_pantalla(Grupo gru_pantalla) {
        this.gru_pantalla = gru_pantalla;
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
