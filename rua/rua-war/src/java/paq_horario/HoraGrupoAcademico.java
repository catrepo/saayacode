/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horario;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author Andres
 */
public class HoraGrupoAcademico extends Pantalla{
    private Tabla tab_grupo_academico = new Tabla(); 
    
    public HoraGrupoAcademico(){
        tab_grupo_academico.setId("tab_grupo_academico");   //identificador
        tab_grupo_academico.setTabla("yavirac_hora_grupo_academic", "ide_yhogra", 1);
        tab_grupo_academico.getColumna("ide_yhogra").setNombreVisual("CODIGO");
        tab_grupo_academico.getColumna("detalle_yhogra").setNombreVisual("DETALLE");
        tab_grupo_academico.getColumna("abreviatura_yhogra").setNombreVisual("ABREVIATURA");
        tab_grupo_academico.dibujar();
        /*agregarComponente(tab_hora_dia);*/ 
        
        PanelTabla pat_grupo_academico = new PanelTabla();
        pat_grupo_academico.setId("pat_grupo_academico");
        pat_grupo_academico.setPanelTabla(tab_grupo_academico);
        Division div_grupo_academico = new Division();
        div_grupo_academico.setId("div_grupo_academico");
        div_grupo_academico.dividir1(pat_grupo_academico);
        agregarComponente(div_grupo_academico); 
    }
    
    
        @Override
    public void insertar() {
        tab_grupo_academico.insertar();
    }

    @Override
    public void guardar() {
        tab_grupo_academico.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
       tab_grupo_academico.eliminar();
    }

    public Tabla getTab_grupo_academico() {
        return tab_grupo_academico;
    }

    public void setTab_grupo_academico(Tabla tab_grupo_academico) {
        this.tab_grupo_academico = tab_grupo_academico;
    }
}
