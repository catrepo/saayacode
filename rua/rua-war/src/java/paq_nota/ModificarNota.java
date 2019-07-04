/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import java.util.List;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_nota.ejb.ServicioNotas;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author JHONPRODUCER
 */
public class ModificarNota extends Pantalla {
    
    private Tabla tab_detalle_autorizacion = new Tabla();
    
    @EJB
    private final ServicioEstructuraOrganizacional ser_estructura_organizacional = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    @EJB
    private final ServicioPersonal ser_personal = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    @EJB
    private final ServicioNotas ser_notas = (ServicioNotas) utilitario.instanciarEJB(ServicioNotas.class);
    
    public ModificarNota() {
        
        if (TienePerfilNota()) {
            
            bar_botones.getBot_insertar().setRendered(false);
            bar_botones.getBot_eliminar().setRendered(false);
            
            tab_detalle_autorizacion.setId("tab_detalle_autorizacion");
            tab_detalle_autorizacion.setTabla("yavirac_nota_detalle_autorizac", "ide_ynodau", 3);
            tab_detalle_autorizacion.setHeader("AUTORIZAR MODIFICAR NOTA");
            tab_detalle_autorizacion.setCondicion("ide_ypedpe="+ide_docente);
            tab_detalle_autorizacion.getColumna("ide_ynodet").setCombo("select ide_ynodet,apellido_yaldap||' '||nombre_yaldap||'-'||detalle_ynocan as detalle\n"
                    + "from yavirac_nota_detalle_nota a \n"
                    + "left join yavirac_nota_cabecera_nota b on a.ide_ynocan=b.ide_ynocan\n"
                    + "left join yavirac_alum_dato_personal c on a.ide_yaldap=c.ide_yaldap");
            tab_detalle_autorizacion.getColumna("ide_ynoest").setVisible(false);
            tab_detalle_autorizacion.getColumna("fecha_registro_ynodau").setLectura(true);
            tab_detalle_autorizacion.getColumna("nota_anterior_ynodau").setLectura(true);
            tab_detalle_autorizacion.getColumna("ide_ypedpe").setVisible(false);
            tab_detalle_autorizacion.getColumna("fecha_autorizacion_ynodau").setVisible(false);
            tab_detalle_autorizacion.getColumna("num_autorizacion_ynodau").setVisible(false);
            tab_detalle_autorizacion.getColumna("observacion_ynodau").setVisible(false);
            tab_detalle_autorizacion.dibujar();
            
            PanelTabla pa_detalle_autorizacion = new PanelTabla();
            pa_detalle_autorizacion.setId("pa_detalle_autorizacion"); // nombre de i
            pa_detalle_autorizacion.setPanelTabla(tab_detalle_autorizacion);
            
            Division div_nota = new Division();
            div_nota.setId("div_nota");
            //div_nota.dividir1(pa_detalle_nota);
            div_nota.dividir1(pa_detalle_autorizacion);
            agregarComponente(div_nota);
            
        } else {
            utilitario.agregarNotificacionInfo("Mensaje,", "EL usuario ingresado no registra permisos para el control de Asistencia. Consulte con el Administrador");
        }
    }
    String docente = "";
    String documento = "";
    String ide_docente = "";
    
    private boolean TienePerfilNota() {
        List sql = utilitario.getConexion().consultar(ser_estructura_organizacional.getUsuarioSistema(utilitario.getVariable("IDE_USUA"), " and not ide_ypedpe is null"));
        
        if (!sql.isEmpty()) {
            Object[] fila = (Object[]) sql.get(0);
            List sql2 = utilitario.getConexion().consultar(ser_personal.getDatoPersonalCodigo(fila[3].toString()));
            if (!sql2.isEmpty()) {
                Object[] fila2 = (Object[]) sql2.get(0);
                docente = fila2[1].toString() + " " + fila2[2].toString();
                documento = fila2[3].toString();
                ide_docente = fila2[0].toString();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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
    
    public Tabla getTab_detalle_autorizacion() {
        return tab_detalle_autorizacion;
    }
    
    public void setTab_detalle_autorizacion(Tabla tab_detalle_autorizacion) {
        this.tab_detalle_autorizacion = tab_detalle_autorizacion;
    }
    
}
