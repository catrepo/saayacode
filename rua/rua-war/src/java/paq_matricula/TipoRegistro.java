
package paq_matricula;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;


public class TipoRegistro extends Pantalla {
      private Tabla tab_tipo_registro = new Tabla();//instanciar tabla del framework

    public TipoRegistro() {//constructor
        tab_tipo_registro.setId("tab_tipo_registro");// todo objeto instanciado poner id 
        tab_tipo_registro.setTabla("yavirac_matri_tipo_reg_cred", "ide_ymatrc", 1);  // nombre de la base de datos ii la clave primaria
        tab_tipo_registro.setHeader("TIPO DE REGISTROS");
        tab_tipo_registro.dibujar();//dibuja la tabla

        PanelTabla pa_tipo_registro = new PanelTabla();//intanciamos el panel del framework
        pa_tipo_registro.setId("pa_tipo_registro");//nombre id
        pa_tipo_registro .setPanelTabla(tab_tipo_registro);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_tipo_registro  =new Division ();//instanciamos
        div_tipo_registro .setId("div_tipo_registro");//es un idientificador
        div_tipo_registro .dividir1(tab_tipo_registro);
        
        agregarComponente(div_tipo_registro);//agregar componente
    }

    @Override
    public void insertar() {
       tab_tipo_registro.insertar();
    }

    @Override
    public void guardar() {
      tab_tipo_registro.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_tipo_registro.eliminar();
    }
     //generar geter and seter
    public Tabla getTab_actividad_evaluacion() {
        return tab_tipo_registro;
    }

    public void setTab_actividad_evaluacion(Tabla tab_tipo_registro) {
        this.tab_tipo_registro = tab_tipo_registro;
    }
}
