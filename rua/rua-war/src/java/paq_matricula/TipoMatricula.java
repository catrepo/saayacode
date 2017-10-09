package paq_matricula;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;


public class TipoMatricula extends Pantalla {
      private Tabla tab_tipo_matricula = new Tabla();//instanciar tabla del framework

    public TipoMatricula() {//constructor
        tab_tipo_matricula.setId("tab_tipo_matricula");// todo objeto instanciado poner id 
        tab_tipo_matricula.setTabla("yavirac_matri_tipo_matric", "ide_ymatma", 1);  // nombre de la base de datos ii la clave primaria
        tab_tipo_matricula.setHeader("TIPO DE MATRICULA");
        tab_tipo_matricula.dibujar();//dibuja la tabla

        PanelTabla pa_tipo_matricula = new PanelTabla();//intanciamos el panel del framework
        pa_tipo_matricula.setId("pa_tipo_matricula");//nombre id
        pa_tipo_matricula.setPanelTabla(tab_tipo_matricula);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_tipo_matricula  =new Division ();//instanciamos
        div_tipo_matricula .setId("div_tipo_matricula");//es un idientificador
        div_tipo_matricula .dividir1(tab_tipo_matricula);
        
        agregarComponente(div_tipo_matricula);//agregar componente
    }

    @Override
    public void insertar() {
       tab_tipo_matricula.insertar();
    }

    @Override
    public void guardar() {
      tab_tipo_matricula.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_tipo_matricula.eliminar();
    }
     //generar geter and seter
    public Tabla getTab_actividad_evaluacion() {
        return tab_tipo_matricula;
    }

    public void setTab_actividad_evaluacion(Tabla tab_tipo_matricula) {
        this.tab_tipo_matricula = tab_tipo_matricula;
    }
}