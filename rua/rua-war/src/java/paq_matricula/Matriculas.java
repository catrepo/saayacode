package paq_matricula;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import sistema.aplicacion.Pantalla;

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
       tab_matriculas.agregarRelacion(tab_documento_entregado);
       tab_matriculas.agregarRelacion(tab_registro_credito);
       tab_matriculas.setTipoFormulario(true);//para que se haga un formulario
       tab_matriculas.getGrid().setColumns(4); //numero de columnas del formulario
       tab_matriculas.dibujar();//dibuja la tabla

        PanelTabla pa_matriculas = new PanelTabla();//intanciamos el panel del framework
        pa_matriculas.setId("pa_matriculas");//nombre id
        pa_matriculas.setPanelTabla(tab_matriculas);//agregar a nuestra tabla el panel
        

          
          //*** tabulacion del documento entregado
          tab_documento_entregado.setId("tab_documento_entregado");// todo objeto instanciado poner id 
          tab_documento_entregado.setTabla("yavirac_matri_docu_entre_matri", "ide_ymdem", 2);  // nombre de la base de datos ii la clave primaria
          tab_documento_entregado.setHeader("DOCUMENTOS ENTREGADOS");
          tab_documento_entregado.dibujar();//dibuja la tabla
          PanelTabla pa_documento_entregado = new PanelTabla();//intanciamos el panel del framework
          pa_documento_entregado.setId("pa_documento_entregado");//nombre id
          pa_documento_entregado.setPanelTabla(tab_documento_entregado);//agregar a nuestra tabla el panel
          tab_documento_entregado.setIdCompleto("tab_tabulador:tab_documento_entregado"); 
          
          //*** tabulador de registro de credito
          tab_registro_credito.setId("tab_registro_credito");// todo objeto instanciado poner id 
          tab_registro_credito.setTabla("yavirac_matri_regis_cred", "ide_ymarec", 3);  // nombre de la base de datos ii la clave primaria
          tab_registro_credito.setHeader("REGISTRO DE CREDITOS");
          tab_registro_credito.dibujar();//dibuja la tabla
          PanelTabla pa_registro_credito = new PanelTabla();//intanciamos el panel del framework
          pa_registro_credito.setId("pa_documento_entregado");//nombre id
          pa_registro_credito.setPanelTabla(tab_registro_credito);//agregar a nuestra tabla el panel
          tab_registro_credito.setIdCompleto("tab_tabulador:tab_registro_credito");
          
        //*******************************AGREGA PESTAÑANAS*********************************************//
        tab_tabulador.agregarTab("DOCUMENTOS ENTREGADOS",pa_documento_entregado);
        tab_tabulador.agregarTab("REGISTRO DE CREDITOS",pa_registro_credito);
        
        //instanciar una division del framework
        Division div_matriculas =new Division ();//instanciamos
        div_matriculas.setId("div_matriculas");//es un idientificador
        div_matriculas.dividir2(pa_matriculas,tab_tabulador,"50%", "H");//dividir2 sirve para la division de pantallas maximo3
        //ponemos el principal luego el tabulador,el porcentaje de cuanto va hacer la pantalla,el tipo horizontal o vertical
        
       
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