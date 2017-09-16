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
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Upload;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import org.primefaces.event.FileUploadEvent;
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
    private List<String[]> lis_importa=null; //Guardo los empleados y el valor del rubro

            

   public Biometrico() {
       
       bar_botones.getBot_guardar().setRendered(false);
       bar_botones.getBot_eliminar().setRendered(false);
       bar_botones.getBot_insertar().setRendered(false);
       
       Boton bot_abrir_upload = new Boton();
       bot_abrir_upload.setValue("Subir Marcaciones");
       bot_abrir_upload.setMetodo("abrirUpload");
      // bar_botones.agregarBoton(bot_abrir_upload);
       
       dia_subir_archivo.setId("dia_subir_archivo");
       dia_subir_archivo.setTitle("Subir Marcaciones Biometrico");
       dia_subir_archivo.getBot_aceptar().setRendered(false);
       dia_subir_archivo.setWidth("40%");
       dia_subir_archivo.setHeight("15%");
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
		upl_archivo.setMetodo("mensaje");

		//upl_archivo.setUpdate("gri_valida");	//	
		upl_archivo.setAuto(false);
		upl_archivo.setAllowTypes("/(\\.|\\/)(xls)$/");
		upl_archivo.setUploadLabel("Validar");
		upl_archivo.setCancelLabel("Cancelar Seleccion");
               
         bar_botones.agregarComponente(upl_archivo);
                
                Grid gri_valor=new Grid();
		gri_valor.setColumns(2);
		//gri_valor.getChildren().add(new Etiqueta("Seleccione Archivo: "));
		//gri_valor.getChildren().add(upl_archivo);
		//dia_subir_archivo.setDialogo(gri_valor);        
      } 
   public void mensaje(FileUploadEvent evt){
       System.out.println("rrrrrrrrr");
       utilitario.agregarMensaje("entre", "voi va");
       return;
   }
   public void validarArchivo(FileUploadEvent evt){	
                                       System.out.println("entre a metodo dubir ");

			//Leer el archivo
			String str_msg_info="";
			String str_msg_adve="";
			String str_msg_erro="";
			double dou_tot_valor_imp=0;
			try {
				//Válido que el rubro seleccionado este configurado en los tipo de nomina

				Workbook archivoExcel = Workbook.getWorkbook(evt.getFile().getInputstream());
				Sheet hoja = archivoExcel.getSheet(0);//LEE LA PRIMERA HOJA
				if (hoja == null) {
					utilitario.agregarMensajeError("No existe ninguna hoja en el archivo seleccionado", "");
					return;
				}
				int int_fin = hoja.getRows();
				upl_archivo.setNombreReal(evt.getFile().getFileName());
                                System.out.println("entre a int_fin "+int_fin);



				str_msg_info+=getFormatoInformacion("El archivo "+upl_archivo.getNombreReal()+" contiene "+int_fin+" filas");
                                System.out.println("entre a str_msg_info "+str_msg_info);
				lis_importa=new ArrayList<String[]>();
	
				
				for (int i = 0; i < int_fin; i++) {
					//codigo tercero remplaza a str_cedula permite leer el codigo de la factutra
					String str_codigo_tercero = hoja.getCell(2, i).getContents();	
					str_codigo_tercero=str_codigo_tercero.trim(); 
					String str_cedula_cliente =hoja.getCell(3, i).getContents();
					str_cedula_cliente = str_cedula_cliente.trim();
					
					System.out.println("imprimo valor celda factura "+str_codigo_tercero+"  numero d ecedula" +str_cedula_cliente);
					/*
					TablaGenerica tab_factura=ser_facturacion.getDatosClienteFactura(str_cedula_cliente, str_codigo_tercero);

					if(tab_factura.isEmpty() ){
						//No existe el documento en la tabla de tab_factura
						str_msg_erro+=getFormatoError("El documento de Identidad: "+str_cedula_cliente+" no se encuentra registrado en la base de datos, fila "+(i+1));
					}
					
					String str_valor_conciliar = hoja.getCell(3, i).getContents();
					String str_valor = hoja.getCell(19, i).getContents();
					double double_valor_conciliar= Double.parseDouble(str_valor_conciliar.replace(",", "."));
					System.out.println("imprimo valro a conciliar "+str_valor);
					tab_tabla.insertar();
					tab_tabla.setValor(0, "valor_conciliado_fafac", double_valor_conciliar+"");
                                            */
				}
				
				
				utilitario.addUpdate("tab_tabla");;

			} catch (Exception e) {
				// TODO: handle exception
			}
    }
    /**
	 * Genera un mensaje de información color azul
	 * @param mensaje
	 * @return
	 */
	private String getFormatoInformacion(String mensaje){
		return "<div><font color='#3333ff'><strong>*&nbsp;</strong>"+mensaje+"</font></div>";	
	}
	/**
	 * Genera un mensaje de Error color rojo
	 * @param mensaje
	 * @return
	 */
	private String getFormatoError(String mensaje){
		return "<div><font color='#ff0000'><strong>*&nbsp;</strong>"+mensaje+"</font></div>";	
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



    
}
