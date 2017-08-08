/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mspficha;

import framework.componentes.AutoCompletar;
import framework.componentes.Boton;
import framework.componentes.Combo;
import framework.componentes.Division;
import framework.componentes.Etiqueta;
import framework.componentes.Grid;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import framework.componentes.Tabulador;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import msp_ficha_familiar_ejb.FormularioServicios;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author oscarpatricio
 */
public class FormularioFichaConsulta extends Pantalla{
    
    private Tabla tab_tabla = new Tabla();
    private Tabla tab_tabla_ficha_familiar = new Tabla();
    private Tabla tab_tabla_mortalidad_familiar = new Tabla();

    private AutoCompletar aut_formulario = new AutoCompletar();
    private Combo comb_provincia = new Combo();
    private Combo comb_canton =new Combo();
    private Combo comb_parroquia = new Combo();
    private Combo comb_zona = new Combo();
    private Combo comb_distrito = new Combo();
    private Combo comb_unidad = new Combo();
                
    @EJB
    private final FormularioServicios ser_formulario = (FormularioServicios) utilitario.instanciarEJB(FormularioServicios.class);    

    public FormularioFichaConsulta(){
        bar_botones.getBot_eliminar().setRendered(false);
        bar_botones.getBot_guardar().setRendered(false);
        bar_botones.getBot_insertar().setRendered(false);        
        /*
        bar_botones.agregarComponente(new Etiqueta("CLIENTE :"));
        aut_formulario.setId("aut_formulario");
        aut_formulario.setSize(75);

        bar_botones.agregarComponente(aut_formulario);*/
        
        Boton bot_clean = new Boton();
        bot_clean.setIcon("ui-icon-cancel");
        bot_clean.setValue("Limpiar Filtro");
        bot_clean.setMetodo("limpiar");
        bar_botones.agregarBoton(bot_clean);
        
        Boton bot_filtro = new Boton();
        bot_filtro.setIcon("ui-icon-search");
        bot_filtro.setValue("Filtrar");
        bot_filtro.setMetodo("filtro");
        bar_botones.agregarBoton(bot_filtro);
        
        comb_provincia.setCombo(ser_formulario.getSqlProvincia());
        comb_canton.setCombo(ser_formulario.getSqlCanton());
        comb_parroquia.setCombo(ser_formulario.getSqlParroquia());
        comb_zona.setCombo(ser_formulario.getSqlAdministracionZonal());
        comb_distrito.setStyle("width: 250px;");
        comb_distrito.setCombo(ser_formulario.getSqlDistrito());
        comb_unidad.setCombo(ser_formulario.getSqlUnidadOperativa());
        //comb_unidad.setHeight(50);
        

        Grid gri_formulario = new Grid();
    	gri_formulario.setColumns(12);
        
        gri_formulario.getChildren().add(new Etiqueta("Provincia :"));
    	gri_formulario.getChildren().add(comb_provincia);
        gri_formulario.getChildren().add(new Etiqueta("Cantón :"));
    	gri_formulario.getChildren().add(comb_canton);
        gri_formulario.getChildren().add(new Etiqueta("Parroquia :"));
    	gri_formulario.getChildren().add(comb_parroquia);        
        gri_formulario.getChildren().add(new Etiqueta("Zona :"));
    	gri_formulario.getChildren().add(comb_zona);        
        gri_formulario.getChildren().add(new Etiqueta("Distrito :"));
    	gri_formulario.getChildren().add(comb_distrito);        
        gri_formulario.getChildren().add(new Etiqueta("Unidad Operativa :"));
    	gri_formulario.getChildren().add(comb_unidad);     
        
        tab_tabla.setId("tab_tabla");
        tab_tabla.setTabla("formulario", "id_formulario", 1);
        tab_tabla.setGenerarPrimaria(false);
        tab_tabla.getColumna("id_formulario").setVisible(false);
        tab_tabla.setCondicion("id_unid_oper=-1");
        
        tab_tabla.getColumna("id_mat_pis").setEstilo("width: 250px;");
        tab_tabla.getColumna("id_mat_pis").setCombo(ser_formulario.getSqlMaterialPiso());
        tab_tabla.getColumna("id_mat_par").setEstilo("width: 250px;");
        tab_tabla.getColumna("id_mat_par").setCombo(ser_formulario.getSqlMaterialPared());
        tab_tabla.getColumna("id_est_tech").setEstilo("width: 250px;");
        tab_tabla.getColumna("id_est_tech").setCombo(ser_formulario.getSqlEstadoTecho());
        //tab_tabla.getColumna("id_est_tech").setAutoCompletar();
        tab_tabla.getColumna("id_est_pis").setEstilo("width: 250px;");
        tab_tabla.getColumna("id_est_pis").setCombo(ser_formulario.getSqlEstadoPiso());
        tab_tabla.getColumna("id_pro_agudd").setEstilo("width: 250px;");        
        tab_tabla.getColumna("id_pro_agudd").setCombo(ser_formulario.getSqlProcedenciaAgua());
        tab_tabla.getColumna("id_rec_agu").setEstilo("width: 250px;");
        tab_tabla.getColumna("id_rec_agu").setCombo(ser_formulario.getSqlRecibeAgua());
        tab_tabla.getColumna("id_tra_agu").setEstilo("width: 250px;");
        tab_tabla.getColumna("id_tra_agu").setCombo(ser_formulario.getSqlTratamientoAgua());
        tab_tabla.getColumna("id_ubi_let").setEstilo("width: 250px;");        
        tab_tabla.getColumna("id_ubi_let").setCombo(ser_formulario.getSqlUbicacionLetrete());
        tab_tabla.getColumna("id_eli_bas").setEstilo("width: 250px;");
        tab_tabla.getColumna("id_eli_bas").setCombo(ser_formulario.getSqlEliminarBasura());
        tab_tabla.getColumna("id_unid_oper").setEstilo("width: 250px;");        
        tab_tabla.getColumna("id_unid_oper").setCombo(ser_formulario.getSqlUnidadOperativa());
        tab_tabla.getColumna("id_tip_trans").setEstilo("width: 250px;");
        tab_tabla.getColumna("id_tip_trans").setCombo(ser_formulario.getSqlTipoTransporte());
        tab_tabla.getColumna("id_cond_ocup").setEstilo("width: 250px;");        
        tab_tabla.getColumna("id_cond_ocup").setCombo(ser_formulario.getSqlCondicionOcupacion());
        tab_tabla.getColumna("id_via_acc").setEstilo("width: 250px;");
        tab_tabla.getColumna("id_via_acc").setCombo(ser_formulario.getSqlViasAcceso());
        tab_tabla.getColumna("id_tip_viv").setEstilo("width: 250px;");        
        tab_tabla.getColumna("id_tip_viv").setCombo(ser_formulario.getSqlTipoVivienda());
        tab_tabla.getColumna("id_mat_tec").setEstilo("width: 250px;");
        tab_tabla.getColumna("id_mat_tec").setCombo(ser_formulario.getSqlMaterialTecho());
        tab_tabla.getColumna("id_comb_coc").setEstilo("width: 250px;");        
        tab_tabla.getColumna("id_comb_coc").setCombo(ser_formulario.getSqlCombustibleCocinar());
        tab_tabla.getColumna("id_eli_agu").setEstilo("width: 250px;");        
        tab_tabla.getColumna("id_eli_agu").setCombo(ser_formulario.getSqlEliminaAgua());
        
        tab_tabla.getColumna("calle1").setLongitud(50);
        tab_tabla.getColumna("calle2").setLongitud(50);
        tab_tabla.getColumna("contamina_agu_desc").setLongitud(50);
        tab_tabla.getColumna("contamina_suel_desc").setLongitud(50);
        tab_tabla.getColumna("contamina_air_desc").setLongitud(50);
        tab_tabla.getColumna("edificio").setLongitud(50);
        tab_tabla.getColumna("responsable").setLongitud(50);
        tab_tabla.getColumna("localidad").setLongitud(50);
        tab_tabla.getColumna("coordenadas").setLongitud(50);
        
        tab_tabla.getColumna("mosquit").setRadio(ser_formulario.desicion(), "1");
       // tab_tabla.getColumna("contamina_suel").setRadio(ser_formulario.desicion(), "1");
       // tab_tabla.getColumna("contamina_air").setRadio(ser_formulario.desicion(), "1");//
       // tab_tabla.getColumna("contamina_agu").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("dest_fami").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("prob_gra_fam").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("psico_soc").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("aisla").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("sin_escol").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("nin_noescola").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("alcoh").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("droga").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("intradomi").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("vec_trans").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("anim_viv").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("coc_inhog").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("sedazo").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("plaguicida").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("aepi").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("abandono").setRadio(ser_formulario.desicion(), "1");
        tab_tabla.getColumna("ir_uni_sal").setRadio(ser_formulario.desicion(), "1");

        tab_tabla.agregarRelacion(tab_tabla_ficha_familiar);
        tab_tabla.agregarRelacion(tab_tabla_mortalidad_familiar);
        
        tab_tabla.setTipoFormulario(true);
        tab_tabla.getGrid().setColumns(6);
        //tab_tabla.setCondicion("ide_geper=" + aut_clientes.getValor());
        tab_tabla.setLectura(true);
        tab_tabla.dibujar();
        PanelTabla pat_panel = new PanelTabla();
        pat_panel.setHeader(gri_formulario);
        pat_panel.setPanelTabla(tab_tabla);
        
        tab_tabla_ficha_familiar.setId("tab_tabla_ficha_familiar");
        tab_tabla_ficha_familiar.setIdCompleto("tab_tabulador:tab_tabla_ficha_familiar");
        tab_tabla_ficha_familiar.setTabla("persona", "id_persona", 2);
        //tab_tabla_ficha_familiar.getColumna("id_nac").setEstilo("width: 200px;");        
        tab_tabla_ficha_familiar.getColumna("id_nac").setCombo(ser_formulario.getSqlNacionalidad());
        tab_tabla_ficha_familiar.getColumna("id_est_civ").setCombo(ser_formulario.getSqlEstadoCivil());
        tab_tabla_ficha_familiar.getColumna("id_etn").setCombo(ser_formulario.getSqlEtnia());
        tab_tabla_ficha_familiar.getColumna("id_seg_pub").setCombo(ser_formulario.getSqlSeguroPublico());
        tab_tabla_ficha_familiar.getColumna("id_claf_diag").setCombo(ser_formulario.getSqlClasificacionDiagnostico());
        tab_tabla_ficha_familiar.getColumna("id_nacs").setCombo(ser_formulario.getSqlNacionalidades());
        tab_tabla_ficha_familiar.getColumna("id_niv_inst").setCombo(ser_formulario.getSqlNivelInstruccion());
        tab_tabla_ficha_familiar.getColumna("id_pue").setCombo(ser_formulario.getSqlPueblos());
        tab_tabla_ficha_familiar.getColumna("id_act_trab").setCombo(ser_formulario.getSqlActividadTrabajo());
        tab_tabla_ficha_familiar.getColumna("id_par_jh").setCombo(ser_formulario.getSqlParentescoJefeHogar());
        tab_tabla_ficha_familiar.getColumna("sexo").setCombo(ser_formulario.listSexo());
        tab_tabla_ficha_familiar.getColumna("seguro_priv").setRadio(ser_formulario.desicion(), "1");
        tab_tabla_ficha_familiar.getColumna("apellidos").setLongitud(50);
        tab_tabla_ficha_familiar.getColumna("nombres").setLongitud(50);
        tab_tabla_ficha_familiar.getColumna("det_seg_privado").setLongitud(50);
        tab_tabla_ficha_familiar.setLectura(true);
        tab_tabla_ficha_familiar.dibujar();
        
        PanelTabla pat_panel_ficha_familiar = new PanelTabla();
        pat_panel_ficha_familiar.setPanelTabla(tab_tabla_ficha_familiar);
        
        tab_tabla_mortalidad_familiar.setId("tab_tabla_mortalidad_familiar");
        tab_tabla_mortalidad_familiar.setIdCompleto("tab_tabulador:tab_tabla_mortalidad_familiar");
        tab_tabla_mortalidad_familiar.setTabla("mortalidad", "id_mortalida", 3);
        tab_tabla_mortalidad_familiar.getColumna("id_par_jh").setCombo(ser_formulario.getSqlParentescoJefeHogar());
        tab_tabla_mortalidad_familiar.getColumna("id_cau_mor").setCombo(ser_formulario.getSqlCausaMortalidad());
        tab_tabla_mortalidad_familiar.getColumna("sexo").setCombo(ser_formulario.listSexo());
        tab_tabla_mortalidad_familiar.setLectura(true);
        tab_tabla_mortalidad_familiar.dibujar();
        
        PanelTabla pat_panel_mortalidad_familiar = new PanelTabla();
        pat_panel_mortalidad_familiar.setPanelTabla(tab_tabla_mortalidad_familiar);
        
        
        Tabulador tab_tabulador = new Tabulador();
        tab_tabulador.setId("tab_tabulador");
        
        tab_tabulador.agregarTab("IDENIFICACION MIEMBROS DE LA FAMILIA", pat_panel_ficha_familiar);//intancia los tabuladores 
	tab_tabulador.agregarTab("MORTALIDAD FAMILIAR",pat_panel_mortalidad_familiar);
        
        Division div_division =new Division ();
        div_division.dividir2(pat_panel, tab_tabulador, "70%", "h");
	agregarComponente(div_division);


    }
    
 public void filtro(){
     String condicion =" 1=1 ";
     
     if(comb_provincia.getValue()!=null){
         condicion +=" and id_unid_oper in (select id_unid_oper from unidad_operativa where id_parroquia in ( select id_parroquia from parroquia where id_canton in (select id_canton from canton where id_provincia in ("+comb_provincia.getValue()+"))))";
     }
     if(comb_canton.getValue()!=null){
         condicion +=" and id_unid_oper in (select id_unid_oper from unidad_operativa where id_parroquia in ( select id_parroquia from parroquia where id_canton in ("+comb_canton.getValue()+")))";
     }
     if(comb_parroquia.getValue()!=null){
         condicion +=" and id_unid_oper in (select id_unid_oper from unidad_operativa where id_parroquia in ("+comb_parroquia.getValue()+"))";
     }
     if(comb_zona.getValue()!=null){
         condicion +=" and id_unid_oper in (select id_unid_oper from unidad_operativa where id_parroquia in ( select id_parroquia from parroquia where id_canton in (select id_canton from canton where id_admin in ("+comb_zona.getValue()+"))))";
     }     
     if(comb_distrito.getValue()!=null){
         condicion +=" and id_unid_oper in (select id_unid_oper from unidad_operativa where id_parroquia in ( select id_parroquia from parroquia where id_distrito in ("+comb_distrito.getValue()+"))) ";
     }
     if(comb_unidad.getValue()!=null){
         condicion +=" and id_unid_oper="+comb_unidad.getValue();
     }     
     tab_tabla.setCondicion(condicion);
     tab_tabla.ejecutarSql();
     tab_tabla_ficha_familiar.ejecutarValorForanea(tab_tabla.getValorSeleccionado());
     tab_tabla_mortalidad_familiar.ejecutarValorForanea(tab_tabla.getValorSeleccionado());
     tab_tabla.imprimirSql();
 }
 public void limpiar(){
     tab_tabla.setCondicion("id_unid_oper=-1");
     tab_tabla.ejecutarSql();     
     tab_tabla_ficha_familiar.ejecutarValorForanea(tab_tabla.getValorSeleccionado());
     tab_tabla_mortalidad_familiar.ejecutarValorForanea(tab_tabla.getValorSeleccionado());
 }
    @Override
    public void insertar() {

                if(tab_tabla.isFocus()){
			tab_tabla.insertar();
			}
		else if(tab_tabla_ficha_familiar.isFocus()){
			tab_tabla_ficha_familiar.insertar();
		}
			else if(tab_tabla_mortalidad_familiar.isFocus()){
				tab_tabla_mortalidad_familiar.insertar();
				
               }
    }

    @Override
    public void guardar() {
        if(tab_tabla.guardar()){
			if(tab_tabla_ficha_familiar.guardar()){
				if(tab_tabla_mortalidad_familiar.guardar()){
					
				}
			}
		}
		guardarPantalla();
    }

    @Override
    public void eliminar() {
		utilitario.getTablaisFocus().eliminar();

    }

    public Tabla getTab_tabla() {
        return tab_tabla;
    }

    public void setTab_tabla(Tabla tab_tabla) {
        this.tab_tabla = tab_tabla;
    }

    public Tabla getTab_tabla_ficha_familiar() {
        return tab_tabla_ficha_familiar;
    }

    public void setTab_tabla_ficha_familiar(Tabla tab_tabla_ficha_familiar) {
        this.tab_tabla_ficha_familiar = tab_tabla_ficha_familiar;
    }

    public Tabla getTab_tabla_mortalidad_familiar() {
        return tab_tabla_mortalidad_familiar;
    }

    public void setTab_tabla_mortalidad_familiar(Tabla tab_tabla_mortalidad_familiar) {
        this.tab_tabla_mortalidad_familiar = tab_tabla_mortalidad_familiar;
    }

    
}
