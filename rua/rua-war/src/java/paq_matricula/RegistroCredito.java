
package paq_matricula;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;


public class RegistroCredito extends Pantalla{
    
        private Tabla tab_registro_credito = new Tabla();//instanciar tabla del framework
        
     public RegistroCredito() {//constructor
       tab_registro_credito.setId("tab_registro_credito");// todo objeto instanciado poner id 
      //  tab_registro_credito.setTabla("yavirac_matri_docu_entre_matri", "ide_ymdem", 1);  // ponombre de la base de datos ii la clave primaria
        tab_registro_credito.setHeader("REGISTRO DE CREDITOS");
        tab_registro_credito.dibujar();//dibuja la tabla

        PanelTabla pa_registro_credito = new PanelTabla();//intanciamos el panel del framework
       pa_registro_credito.setId("pa_registro_credito");//nombre id
        pa_registro_credito.setPanelTabla(tab_registro_credito);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_registro_credito =new Division ();//instanciamos
        div_registro_credito.setId("div_documento_entregado");//es un idientificador
       div_registro_credito.dividir1(tab_registro_credito);
        
        agregarComponente(div_registro_credito);//agregar componente
    }
    @Override
    public void insertar() {
       tab_registro_credito.insertar();
    }

    @Override
    public void guardar() {
        tab_registro_credito.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_registro_credito.eliminar();
    }
    
    //generar geter and seter
    public Tabla getTab_actividad_evaluacion() {
        return tab_registro_credito;
    }

    public void setTab_actividad_evaluacion(Tabla  tab_registro_credito) {
        this.tab_registro_credito = tab_registro_credito;
    }
}
