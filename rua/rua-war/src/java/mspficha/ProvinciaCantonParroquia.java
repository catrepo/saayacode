/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mspficha;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import msp_ficha_familiar_ejb.FormularioServicios;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author oscarpatricio
 */
public class ProvinciaCantonParroquia extends Pantalla{
    
    Tabla tab_provincia = new Tabla();
    Tabla tab_canton = new Tabla();
    Tabla tab_parroquia = new Tabla();
   @EJB
    private final FormularioServicios ser_formulario = (FormularioServicios) utilitario.instanciarEJB(FormularioServicios.class);    
    
    public ProvinciaCantonParroquia (){
        
        tab_provincia.setId("tab_provincia");
        tab_provincia.setHeader("PROVINCIA");
        tab_provincia.setTabla("provincia", "id_provincia", 1);
        tab_provincia.agregarRelacion(tab_canton);
        tab_provincia.dibujar();
        
        PanelTabla pat_provincia = new PanelTabla();
        pat_provincia.setPanelTabla(tab_provincia);
        
        tab_canton.setId("tab_canton");
        tab_canton.setHeader("CANTON");
        tab_canton.setTabla("canton", "id_canton", 2);
        tab_canton.getColumna("id_admin").setCombo(ser_formulario.getSqlAdministracionZonal());
        tab_canton.agregarRelacion(tab_parroquia);
        tab_canton.dibujar();
        
        PanelTabla pat_canton = new PanelTabla();
        pat_canton.setPanelTabla(tab_canton);
        
        List desicion = new ArrayList();
        Object lista[] ={
            "U","URBANO"
        };
        Object lista2[] ={
            "R","RURAL"
        };
        desicion.add(lista);
        desicion.add(lista2);
        tab_parroquia.setId("tab_parroquia");
        tab_parroquia.setHeader("PARROQUIA");
        tab_parroquia.setTabla("parroquia", "id_parroquia", 3);
        tab_parroquia.getColumna("id_distrito").setCombo(ser_formulario.getSqlDistrito());
        tab_parroquia.getColumna("tipo").setCombo(desicion);

        tab_parroquia.dibujar();
        
        PanelTabla pat_parroquia = new PanelTabla();
        pat_parroquia.setPanelTabla(tab_parroquia);
        
        Division div_prov_cant_parr = new Division();
        div_prov_cant_parr.setId("div_prov_cant_parr");
        div_prov_cant_parr.dividir3(pat_provincia, pat_canton, pat_parroquia, "20%", "20%", "H");
        
        agregarComponente(div_prov_cant_parr);
        
    }
    @Override
    public void insertar() {
         if(tab_provincia.isFocus()){
			tab_provincia.insertar();
			}
		else if(tab_canton.isFocus()){
			tab_canton.insertar();
		}
			else if(tab_parroquia.isFocus()){
				tab_parroquia.insertar();
				
               }
    }

    @Override
    public void guardar() {
        if(tab_provincia.guardar()){
			if(tab_canton.guardar()){
				if(tab_parroquia.guardar()){
					
				}
			}
		}
		guardarPantalla();
    }

    @Override
    public void eliminar() {
        		utilitario.getTablaisFocus().eliminar();

    }

    public Tabla getTab_provincia() {
        return tab_provincia;
    }

    public void setTab_provincia(Tabla tab_provincia) {
        this.tab_provincia = tab_provincia;
    }

    public Tabla getTab_canton() {
        return tab_canton;
    }

    public void setTab_canton(Tabla tab_canton) {
        this.tab_canton = tab_canton;
    }

    public Tabla getTab_parroquia() {
        return tab_parroquia;
    }

    public void setTab_parroquia(Tabla tab_parroquia) {
        this.tab_parroquia = tab_parroquia;
    }
      
    
}
