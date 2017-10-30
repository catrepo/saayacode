
package paq_matricula;
import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

public class PeriodoMatricula extends Pantalla{
    
        private Tabla tab_periodo_matricula = new Tabla();//instanciar tabla del framework
        
     public PeriodoMatricula() {//constructor
       tab_periodo_matricula .setId("tab_periodo_matricula ");// todo objeto instanciado poner id 
        tab_periodo_matricula .setTabla("yavirac_matri_periodo_matric", "ide_ymaper ", 1);  // nombre de la base de datos ii la clave primaria
       tab_periodo_matricula.setHeader("PERIODO MATRICULA");
        tab_periodo_matricula .dibujar();//dibuja la tabla

        PanelTabla pa_periodo_matricula = new PanelTabla();//intanciamos el panel del framework
        pa_periodo_matricula.setId("pa_periodo_matricula");//nombre id
        pa_periodo_matricula.setPanelTabla(tab_periodo_matricula);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_periodo_matricula =new Division ();//instanciamos
        div_periodo_matricula.setId("div_periodo_matricula");//es un idientificador
        div_periodo_matricula.dividir1(tab_periodo_matricula);
        
        agregarComponente(div_periodo_matricula);//agregar componente
    }
 @Override
    public void insertar() {
        tab_periodo_matricula.insertar();
    }

    @Override
    public void guardar() {
        tab_periodo_matricula.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_periodo_matricula.eliminar();
    }
    
    //generar geter and seter

    public Tabla getTab_periodo_matricula() {
        return tab_periodo_matricula;
    }

    public void setTab_periodo_matricula(Tabla tab_periodo_matricula) {
        this.tab_periodo_matricula= tab_periodo_matricula;
    }
    
}