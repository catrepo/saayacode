/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_titulacion;

import framework.componentes.Tabla;
import sistema.aplicacion.Pantalla;
import framework.componentes.PanelTabla;
/**
 *
 * @author Martha
 */
public class LibretaPractica extends Pantalla{
   private Tabla tab_libreta_practica = new Tabla();
   private Tabla tab_anexo_libreta = new Tabla();
   private Tabla tab_horario_practica = new Tabla();
    
    public LibretaPractica(){
        
        tab_libreta_practica.setId("tab_libreta_practica");
        tab_libreta_practica.setTabla("yavirac_titu_libreta_practica", "ide_ytilpr",1);
        tab_libreta_practica.setHeader("LIBRETA DE PRACTICA");
        tab_libreta_practica.getColumna(" ide_ytilpr"). setNombreVisual("CÓDIGO DE LA LIBRETA DE PRACTICA");
        tab_libreta_practica.getColumna("fecha_desde_ytilpr"). setNombreVisual("FECHA DESDE");
        tab_libreta_practica.getColumna("fecha_hasta_ytilpr"). setNombreVisual("FECHA HASTA");
        tab_libreta_practica.getColumna("resumen_trabajo_ytilpr"). setNombreVisual("RESUEMN DEL TRABAJO");
        tab_libreta_practica.getColumna("nombre_ytilpr"). setNombreVisual("NOMBRE");
        tab_libreta_practica.getColumna("direccion_ytilpr"). setNombreVisual("DIRECCION");
        tab_libreta_practica.getColumna("telefono_ytilpr"). setNombreVisual("TELEFONO");
        tab_libreta_practica.getColumna("actividad_ytilpr"). setNombreVisual("ACTIVIDAD");
        tab_libreta_practica.getColumna("observacion_ytilpr"). setNombreVisual("OBSERVACION");
        tab_libreta_practica.dibujar();
        
         PanelTabla pat_libreta_practica = new PanelTabla();
         pat_libreta_practica.setId("pat_libreta_practica");
         pat_libreta_practica.setPanelTabla(tab_libreta_practica);
        
        tab_anexo_libreta.setId("tab_anexo_libreta");
        tab_anexo_libreta.setTabla("yavirac_titu_anexo_libreta", "ide_ytiali",2);
        tab_anexo_libreta.setHeader("ANEXO DE LA LIBRETA");
        tab_anexo_libreta.getColumna(" ide_ytiali"). setNombreVisual("CÓDIGO DEL ANEXO DE LA LIBRETA");
        tab_anexo_libreta.getColumna("fecha_ytiali"). setNombreVisual("FECHA DE ANEXO DE LA LIBRETA");
        tab_anexo_libreta.getColumna("archivo_ytiali"). setNombreVisual("ARCHIVO DEL ANEXO");
        tab_anexo_libreta.getColumna("obserbacion_ytiali"). setNombreVisual("OBSERVACION DEL ANEXO");
        tab_anexo_libreta.dibujar();
        
        PanelTabla pat_anexo_libreta = new PanelTabla();
        pat_anexo_libreta.setId("pat_anexo_libreta");
        pat_anexo_libreta.setPanelTabla(tab_anexo_libreta);
        
        tab_horario_practica.setId("tab_horario_practica");
        tab_horario_practica.setTabla("yavirac_titu_horario_practica", "ide_ytihpr",2);
        tab_horario_practica.setHeader("HORARIO DE PRACTICAS");
        tab_horario_practica.getColumna(" ide_ytihpr"). setNombreVisual("CÓDIGO DEL ANEXO DE LA LIBRETA");
        tab_horario_practica.getColumna("hora_inicio_ytihpr"). setNombreVisual("FECHA DE ANEXO DE LA LIBRETA");
        tab_horario_practica.getColumna("hora_fin_ytihpr"). setNombreVisual("ARCHIVO DEL ANEXO");
        tab_horario_practica.getColumna("numero_horas_ytihpr"). setNombreVisual("OBSERVACION DEL ANEXO");
        tab_horario_practica.dibujar();
        
        PanelTabla pat_horario_practica = new PanelTabla();
        pat_horario_practica.setId("pat_horario_practica");
        pat_horario_practica.setPanelTabla(tab_horario_practica);
               
    }

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
