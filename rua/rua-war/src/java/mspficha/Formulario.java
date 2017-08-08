/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mspficha;

import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.Calendario;
import framework.componentes.Etiqueta;
import framework.componentes.MenuPanel;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.VisualizarPDF;
import javax.ejb.EJB;
import msp_ficha_familiar_ejb.FormularioServicios;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class Formulario extends Pantalla{
    
    private final MenuPanel mep_menu = new MenuPanel();
    private AutoCompletar aut_formulario = new AutoCompletar();
    private VisualizarPDF vipdf_acta = new VisualizarPDF();
    private final Calendario cal_fecha = new Calendario();
    private Tabla tab_tabla = new Tabla();

    @EJB
    private final FormularioServicios ser_formulario = (FormularioServicios) utilitario.instanciarEJB(FormularioServicios.class);    
        
    public Formulario(){
        
        bar_botones.quitarBotonsNavegacion();
        bar_botones.agregarComponente(new Etiqueta("CLIENTE :"));
        aut_formulario.setId("aut_formulario");
        //aut_formulario.setAutoCompletar(ser_cliente.getSqlComboClientes());
        aut_formulario.setSize(75);
        aut_formulario.setAutocompletarContenido(); // no startWith para la busqueda
        aut_formulario.setMetodoChangeRuta("pre_index.clase.seleccionarCliente");

        bar_botones.agregarComponente(aut_formulario);
        Boton bot_clean = new Boton();
        bot_clean.setIcon("ui-icon-cancel");
        bot_clean.setTitle("Limpiar");
        bot_clean.setMetodo("limpiar");
        bar_botones.agregarBoton(bot_clean);
        
        mep_menu.setMenuPanel("FORMULARIO", "21%");
        mep_menu.agregarItem("Ficha Familiar", "dibujarFormulario", "ui-icon-note");//1                

        mep_menu.agregarSubMenu("DATOS SALUD GRUPO FAMILIAR");
        mep_menu.agregarItem("Identificación Miembros Familia", "dibujarAsignarActivos", "ui-icon-document");//2
        mep_menu.agregarItem("Mortalidad Familiar", "dibujarListadoActasConstata", "ui-icon-note");//3
        dibujarFormulario();
        agregarComponente(mep_menu); 
    }

    
        /**
     * Dibuja el formulario de datos del Formlario de la Ficha Familiar, osigna opcion 1
     */
    public void dibujarFormulario() {
        tab_tabla = new Tabla();
        tab_tabla.setId("tab_tabla");
        tab_tabla.setTabla("formulario", "id_formulario", 1);
        tab_tabla.getColumna("id_formulario").setVisible(false);
        tab_tabla.getColumna("id_mat_pis").setCombo(ser_formulario.getSqlMaterialPiso());
        tab_tabla.getColumna("id_mat_par").setCombo(ser_formulario.getSqlMaterialPared());
        tab_tabla.getColumna("id_est_tech").setCombo(ser_formulario.getSqlEstadoTecho());
        tab_tabla.getColumna("id_est_pis").setCombo(ser_formulario.getSqlEstadoPiso());
        tab_tabla.getColumna("id_pro_agudd").setCombo(ser_formulario.getSqlProcedenciaAgua());
        tab_tabla.getColumna("id_rec_agu").setCombo(ser_formulario.getSqlRecibeAgua());
        tab_tabla.getColumna("id_tra_agu").setCombo(ser_formulario.getSqlTratamientoAgua());
        tab_tabla.getColumna("id_ubi_let").setCombo(ser_formulario.getSqlUbicacionLetrete());
        tab_tabla.getColumna("id_eli_bas").setCombo(ser_formulario.getSqlEliminarBasura());
        tab_tabla.getColumna("id_unid_oper").setCombo(ser_formulario.getSqlUnidadOperativa());
        tab_tabla.getColumna("id_tip_trans").setCombo(ser_formulario.getSqlTipoTransporte());
        tab_tabla.getColumna("id_cond_ocup").setCombo(ser_formulario.getSqlCondicionOcupacion());
        tab_tabla.getColumna("id_via_acc").setCombo(ser_formulario.getSqlViasAcceso());
        tab_tabla.getColumna("id_tip_viv_id").setCombo(ser_formulario.getSqlTipoVivienda());
        tab_tabla.getColumna("id_mat_tec").setCombo(ser_formulario.getSqlMaterialTecho());
        tab_tabla.getColumna("id_comb_coc").setCombo(ser_formulario.getSqlCombustibleCocinar());
        tab_tabla.getColumna("contamina_air_desc").setEstilo("heigth:50px;");
        tab_tabla.getColumna("contamina_agu_desc").setEstilo("size='15'");
//
        tab_tabla.setTipoFormulario(true);
        tab_tabla.getGrid().setColumns(6);
        //tab_tabla.setCondicion("ide_geper=" + aut_clientes.getValor());
        tab_tabla.setMostrarNumeroRegistros(false);
        tab_tabla.dibujar();
        PanelTabla pat_panel = new PanelTabla();
        pat_panel.setPanelTabla(tab_tabla);
        pat_panel.getMenuTabla().getItem_buscar().setRendered(false);
        mep_menu.dibujar(1, "FORMULARIO DE FICHA FAMILIAR", pat_panel);
    }
    
    /**
     * Validacion para que se seleccione un formulario del Autocompletar
     *
     * @return
     */
    private boolean isFormularioSeleccionado() {
        if (aut_formulario.getValor() == null) {
            utilitario.agregarMensajeInfo("Debe seleccionar un formulario", "Seleccione un formulario para consultar");
            return false;
        }
        return true;
    }
    
    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        if (mep_menu.getOpcion() == 1) {
            //FORMULARIO CLIENTE
                tab_tabla.guardar();
                if (guardarPantalla().isEmpty()) {
                    //Actualiza el autocompletar
                   /*
                    aut_clientes.actualizar();
                    aut_clientes.setSize(75);
                    aut_clientes.setValor(tab_tabla.getValorSeleccionado());
                    utilitario.addUpdate("aut_clientes");
                           */
                }
            }
         
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tabla getTab_tabla() {
        return tab_tabla;
    }

    public void setTab_tabla(Tabla tab_tabla) {
        this.tab_tabla = tab_tabla;
    }
            
            
}
