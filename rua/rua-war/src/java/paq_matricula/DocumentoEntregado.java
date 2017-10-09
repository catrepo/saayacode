
package paq_matricula;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;


public class DocumentoEntregado extends Pantalla{
    
        private Tabla tab_documento_entregado = new Tabla();//instanciar tabla del framework
        
     public DocumentoEntregado() {//constructor
       tab_documento_entregado.setId("tab_documento_entregado");// todo objeto instanciado poner id 
        tab_documento_entregado.setTabla("public.yavirac_matri_docu_entre_matri", "ide_ymdem", 1);  // nombre de la base de datos ii la clave primaria
        tab_documento_entregado.setHeader("DOCUMENTOS ENTREGADOS");
        tab_documento_entregado.dibujar();//dibuja la tabla

        PanelTabla pa_actividad_evaluacion = new PanelTabla();//intanciamos el panel del framework
        pa_actividad_evaluacion.setId("pa_documento_entregado");//nombre id
        pa_actividad_evaluacion.setPanelTabla(tab_documento_entregado);//agregar a nuestra tabla el panel
        
        //instanciar una division del framework
        Division div_actividad_evaluacion =new Division ();//instanciamos
        div_actividad_evaluacion.setId("div_documento_entregado");//es un idientificador
        div_actividad_evaluacion.dividir1(tab_documento_entregado);
        
        agregarComponente(div_actividad_evaluacion);//agregar componente
    }
    @Override
    public void insertar() {
        tab_documento_entregado.insertar();
    }

    @Override
    public void guardar() {
        tab_documento_entregado.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_documento_entregado.eliminar();
    }
    
    //generar geter and seter
    public Tabla getTab_actividad_evaluacion() {
        return tab_documento_entregado;
    }

    public void setTab_actividad_evaluacion(Tabla  tab_documento_entregado) {
        this.tab_documento_entregado = tab_documento_entregado;
    }
}
