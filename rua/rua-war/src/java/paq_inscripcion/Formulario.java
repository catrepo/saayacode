package paq_inscripcion;

import framework.componentes.Division;
import framework.componentes.PanelTabla;
import framework.componentes.Tabla;
import javax.ejb.EJB;
import paq_estructura.ejb.ServicioEstructuraOrganizacional;
import paq_titulacion.ejb.ServicioTitulacion;
import paq_personal.ejb.ServicioPersonal;
import sistema.aplicacion.Pantalla;

/**
 *
 * @author USER
 */
public class Formulario extends Pantalla{
    @EJB
    private final ServicioEstructuraOrganizacional ser_formulario = (ServicioEstructuraOrganizacional) utilitario.instanciarEJB(ServicioEstructuraOrganizacional.class);
    
    @EJB
    private final ServicioTitulacion ser_formulario1 = (ServicioTitulacion) utilitario.instanciarEJB(ServicioTitulacion.class);
    
    @EJB
    private final ServicioPersonal ser_formulario2 = (ServicioPersonal) utilitario.instanciarEJB(ServicioPersonal.class);
    Tabla tab_formulario_alumno=new Tabla();
    public Formulario (){
        tab_formulario_alumno.setId("tab_formulario_alumno");
        tab_formulario_alumno.setTabla("yavirac_ins_formulario", "ide_yinfor",1);
        tab_formulario_alumno.getColumna("ide_ystsex").setCombo(ser_formulario.getSexo());
        tab_formulario_alumno.getColumna("ide_ysttid").setCombo(ser_formulario.getDiscapacidad());
        tab_formulario_alumno.getColumna("ide_ystgen").setCombo(ser_formulario.getGenero("true,false"));
        tab_formulario_alumno.getColumna("ide_ystpaf").setCombo(ser_formulario.getParentezcoFamiliar("true,false"));
        tab_formulario_alumno.getColumna("ide_ystetn").setCombo(ser_formulario.getEtnia());
        tab_formulario_alumno.getColumna("ide_ystnac").setCombo(ser_formulario.getNacionalidad("true,false"));
        tab_formulario_alumno.getColumna("ide_ysttis").setCombo(ser_formulario.getTipoSangre("true,false"));
        tab_formulario_alumno.getColumna("ide_ystesc").setCombo(ser_formulario.getEstadoCivil("true,false"));
        tab_formulario_alumno.getColumna("ide_ystgrd").setCombo(ser_formulario.getGradoDiscapacidad());
        tab_formulario_alumno.getColumna("ide_ysttco").setCombo(ser_formulario.getTipoColegio());
        tab_formulario_alumno.getColumna("ide_ysttba").setCombo(ser_formulario.getTipoBachillerato());
        tab_formulario_alumno.getColumna("ide_ystpea").setCombo(ser_formulario.getPeriodoAcademico("true,false"));
        tab_formulario_alumno.getColumna("ide_ystnie").setCombo(ser_formulario.getNivelEducacion());
        tab_formulario_alumno.getColumna("ide_ystjor").setCombo(ser_formulario.getJornada("true,false"));
        tab_formulario_alumno.getColumna("ide_ytiemp").setCombo(ser_formulario1.getSqlTipoEmpresa());
        tab_formulario_alumno.getColumna("ide_ystvis").setCombo(ser_formulario.getVinculaSociedad());
        tab_formulario_alumno.getColumna("ide_ystmod").setCombo(ser_formulario.getModalidad("true,false"));
        tab_formulario_alumno.getColumna("ide_ypetip").setCombo(ser_formulario2.getTipoProfesional());
        tab_formulario_alumno.getColumna("ide_ystdip").setCombo(ser_formulario.getDistribucionPolitica("true,false")); 
        tab_formulario_alumno.getColumna("ide_ytiace").setCombo(ser_formulario1.getSqlActividadEconomica());
        tab_formulario_alumno.getColumna("ide_ystdoi").setCombo(ser_formulario.getDocumentoIdentidad("true,false"));
        tab_formulario_alumno.getColumna("ide_ystmen").setCombo(ser_formulario.getMension());
        tab_formulario_alumno.getColumna("ide_ytitie").setCombo(ser_formulario1.getTipoEmpresa());
        tab_formulario_alumno.getColumna("ide_ytiace").setCombo(ser_formulario1.getActividadEconomica());
        tab_formulario_alumno.getColumna("ide_ystfoe").setCombo(ser_formulario.getFormacionEducativa());
        tab_formulario_alumno.getColumna("ide_ystcam").setCombo(ser_formulario.getCategoriaMigratoria());
        tab_formulario_alumno.getColumna("ide_ystani").setCombo(ser_formulario.getAnioPeriodoCarrera());
        tab_formulario_alumno.getColumna("yav_ide_ystdip").setCombo(ser_formulario.getDistribucionPolitica());
        tab_formulario_alumno.getColumna("yav_ide_ytiace").setCombo(ser_formulario1.getActividadEconomica());
        tab_formulario_alumno.getColumna("yav_ide_ystfoe").setCombo(ser_formulario.getFormacionEducativa());
        tab_formulario_alumno.setTipoFormulario(true);
        tab_formulario_alumno.getGrid().setColumns(6);
        tab_formulario_alumno.dibujar();
        PanelTabla pat_formulario_alumno = new PanelTabla();
        pat_formulario_alumno.setId("pat_formulario_alumno");
        pat_formulario_alumno.setPanelTabla(tab_formulario_alumno);
        
        Division div_formulario_alumno = new Division();
        div_formulario_alumno.setId("div_formulario_alumno");
        div_formulario_alumno.dividir1(pat_formulario_alumno);
        
        agregarComponente(div_formulario_alumno);
        
              
    }
    

    @Override
    public void insertar() {
        tab_formulario_alumno.insertar();
    }

    @Override
    public void guardar() {
        tab_formulario_alumno.guardar();
        guardarPantalla();
    }

    @Override
    public void eliminar() {
        tab_formulario_alumno.eliminar();

    }

    public Tabla getTab_formulario_alumno() {
        return tab_formulario_alumno;
    }

    public void setTab_formulario_alumno(Tabla tab_formulario_alumno) {
        this.tab_formulario_alumno = tab_formulario_alumno;
    }
    
}
