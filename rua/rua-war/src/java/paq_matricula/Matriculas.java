package paq_matricula;

import framework.componentes.Barra;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grupo;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

public class Matriculas extends Pantalla{
    
        private Tabla tab_matriculas = new Tabla();//instanciar tabla del framework
        private Tabla tab_documento_entregado= new Tabla();
        private Tabla tab_registro_credito= new Tabla();
        
        
        
     public Matriculas() {//constructor
         
         Tabulador tab_tabulador =new Tabulador();
         tab_tabulador.setId("tab_tabulador");
         
       tab_matriculas.setId("tab_matriculas");// todo objeto instanciado poner id 
       tab_matriculas.setTabla("yavirac_matriculas", "ide_yavmat", 1);  // nombre de la base de datos ii la clave primaria
       tab_matriculas.setHeader("MATRICULAS");
       tab_matriculas.dibujar();//dibuja la tabla

        PanelTabla pa_matriculas = new PanelTabla();//intanciamos el panel del framework
        pa_matriculas.setId("pa_matriculas");//nombre id
        pa_matriculas.setPanelTabla(tab_matriculas);//agregar a nuestra tabla el panel
        
        //**********************************************************//
        
          tab_matriculas.setId("tab_matriculas");// todo objeto instanciado poner id 
       tab_matriculas.setTabla("yavirac_matriculas", "ide_yavmat", 1);  // nombre de la base de datos ii la clave primaria
       tab_matriculas.setHeader("MATRICULAS");
       tab_matriculas.dibujar();//dibuja la tabla
        //****************************************************************************//
        tab_tabulador.agregarTab("USUARIOS",pa_matriculas);
        tab_tabulador.agregarTab("PESTAÑA VACIA",null);
        tab_tabulador.agregarTab("PESTAÑA CON ETIQUETA",new Etiqueta("ETIQUETA DENTRO DE UNA PESTAÑA"));
        
        //instanciar una division del framework
        Division div_matriculas =new Division ();//instanciamos
        div_matriculas.setId("div_matriculas");//es un idientificador
        div_matriculas.dividir1(tab_matriculas);
        
        div_matriculas.dividir1(tab_tabulador);
        
        agregarComponente(div_matriculas);//agregar componente
    }

    public Tabla getTab_documento_entregado() {
        return tab_documento_entregado;
    }

    public void setTab_documento_entregado(Tabla tab_documento_entregado) {
        this.tab_documento_entregado = tab_documento_entregado;
    }

    public Tabla getTab_registro_credito() {
        return tab_registro_credito;
    }

    public void setTab_registro_credito(Tabla tab_registro_credito) {
        this.tab_registro_credito = tab_registro_credito;
    }
    @Override
    public void insertar() {
        tab_matriculas.insertar();
    }

    @Override
    public void guardar() {
       tab_matriculas.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_matriculas.eliminar();
    }

    public Tabla getTab_matriculas() {
        return tab_matriculas;
    }

    public void setTab_matriculas(Tabla tab_matriculas) {
        this.tab_matriculas = tab_matriculas;
    }


    
}