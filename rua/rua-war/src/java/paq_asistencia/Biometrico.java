/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia;

import framework.componentes.Barra;
import framework.componentes.Boton;
import framework.componentes.Dialogo;
import framework.componentes.Division;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Upload;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Janeth Pullotasig and  Nicolas Cajilema
 */
public class Biometrico extends Pantalla {
    
    private Tabla tab_biometrico = new Tabla();
    private Upload upl_archivo=new Upload();
    private Dialogo dia_subir_archivo = new Dialogo();
            

   public Biometrico() {
       
       bar_botones.getBot_guardar().setRendered(false);
       bar_botones.getBot_eliminar().setRendered(false);
       bar_botones.getBot_insertar().setRendered(false);
       
       Boton bot_abrir_upload = new Boton();
       bot_abrir_upload.setValue("Subir Marcaciones");
       bot_abrir_upload.setMetodo("abrirUpload");
       bar_botones.agregarBoton(bot_abrir_upload);
       
       dia_subir_archivo.setId("dia_subir_archivo");
       dia_subir_archivo.setTitle("Subir Marcaciones Biometrico");
       dia_subir_archivo.getBot_aceptar().setRendered(false);
       agregarComponente(dia_subir_archivo);
       
       tab_biometrico.setId("tab_biometrico");
        tab_biometrico.setTabla("yavirac_asis_biometrico","ide_yasbio",1);
        tab_biometrico.setHeader("BIOMETRICO");
        tab_biometrico.dibujar();
        
        
        PanelTabla pat_biometrico = new PanelTabla();
        pat_biometrico.setId("pat_biometrico");
        pat_biometrico.setPanelTabla(tab_biometrico);
       
        Division div_biometrico = new Division();
        div_biometrico.setId("div_biometrico");
        div_biometrico.dividir1(pat_biometrico);
        
        agregarComponente(div_biometrico);
        
        upl_archivo.setId("upl_archivo");
		upl_archivo.setMetodo("validarArchivo");

		//upl_archivo.setUpdate("gri_valida");	//	
		upl_archivo.setAuto(false);
		upl_archivo.setAllowTypes("/(\\.|\\/)(xls)$/");
		upl_archivo.setUploadLabel("Validar");
		upl_archivo.setCancelLabel("Cancelar Seleccion");
         bar_botones.agregarComponente(upl_archivo);
      } 
   public void abrirUpload(){
       dia_subir_archivo.dibujar();
   }
    @Override
    public void insertar() {
        tab_biometrico.insertar();
    }

    @Override
    public void guardar() {
        tab_biometrico.guardar();
        guardarPantalla();
        
    }

    @Override
    public void eliminar() {
        tab_biometrico.eliminar();
    }

    public Tabla getTab_biometrico() {
        return tab_biometrico;
    }

    public void setTab_biometrico(Tabla tab_biometrico) {
        this.tab_biometrico = tab_biometrico;
    }

    public Upload getUpl_archivo() {
        return upl_archivo;
    }

    public void setUpl_archivo(Upload upl_archivo) {
        this.upl_archivo = upl_archivo;
    }

    
}
