/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author usuario
 */
@Stateless

public class ServicioNotas {

    /**
     * Retorna el Tipo de Evaluacion
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getTipoEvaluacion(String activo) {
        String sql = "";
        sql += "select ide_ynotie,descripcion_ynotie from yavirac_nota_tipo_evaluacion  where activo_ynotie in (" + activo + ")";
        return sql;
    }

    /**
     * Retorna la Actividad de Evaluacion
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getActividadEvaluacion(String activo) {
        String sql = "";
        sql += "select ide_ynoace,descripcion_ynoace from yavirac_nota_actividad_evaluac  where activo_ynoace in (" + activo + ")";
        return sql;
    }

    /**
     * Retorna Periodo Evaluacion
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getPeriodoEvaluacion(String activo) {
        String sql = "";
        sql += "select ide_ynotie,descripcion_ynotie from yavirac_nota_tipo_evaluacion  where activo_ynotie in (" + activo + ")";
        return sql;
    }

    /**
     * Retorna Remplazo de Gion
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getRemplazaG(String campo, String tabla) {
        String sql = "";
        sql += "select 1 as codigo,replace(replace('" + campo + "','-',''),' ','') as repa from " + tabla;
        return sql;
    }

    /**
     * Retorna Limite de caracteres
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getlimiteC(String campo) {
        String sql = "";
        sql += "select 1 as codigo,substring('" + campo + "' from 1  for 30) as nombre ";
        return sql;
    }

    /**
     * Retorna creacion de la tabla nota maestro
     *
     * @param tabla.- permite crear una tabla maestro segun el periodo
     * academico.
     * @return sql del tipo de evaluacion
     */
    public String gettabnotamaestro(String tabla) {
        String sql = "";
        sql += "create table " + tabla + " ( ide_ynotma integer,primary key (ide_ynotma))";
        return sql;
    }

    /**
     * Retorna periodo tipo evaluacion
     *
     * @param codigo.- permite crear una tabla maestro segun el periodo
     * academico.
     * @return sql del tipo de evaluacion
     */
    public String getperiodotipoevaluacion(String codigo) {
        String sql = "";
        sql += "select pe.ide_ynopee,t.descripcion_ynotie from yavirac_stror_periodo_academic p\n"
                + "join yavirac_nota_periodo_evaluacio pe\n"
                + "on p.ide_ystpea=pe.ide_ystpea\n"
                + "join yavirac_nota_tipo_evaluacion t\n"
                + "on t.ide_ynotie = pe.ide_ynotie\n"
                + "where p.ide_ystpea in (" + codigo + ")";
        return sql;
    }

    /**
     * Retorna periodo tipo evaluacion
     *
     * @param codigo.- permite crear una tabla maestro segun el periodo
     * academico.
     * @return sql del tipo de evaluacion
     */
    public String getFiltroperiodotipoevaluacion(String peridoacademico) {
        String sql = "";
        sql += "select ide_ynopee,descripcion_ynotie\n"
                + "From yavirac_nota_periodo_evaluacio b \n"
                + "join yavirac_nota_tipo_evaluacion c on c.ide_ynotie = b.ide_ynotie where ide_ystpea in (" + peridoacademico + ")";
        return sql;
    }

    /**
     * Retorna periodo tipo evaluacion
     *
     * @param periodoacademico.- permite crear una tabla maestro segun el
     * periodo academico.
     * @return sql del tipo de evaluacion
     */
    public String getPeriodoActividadEvaluacion(String periodoacademico, String tipo, String estado,String formacion) {
        String sql = "";
        sql += "select  ide_ynopae,b.descripcion_ynoace,d.descripcion_ynotie,detalle_ysttfe\n" +
               "from  yavirac_nota_periodo_activ_eva a,yavirac_nota_actividad_evaluac b,yavirac_nota_periodo_evaluacio c,yavirac_nota_tipo_evaluacion d,\n" +
               "yavirac_nota_actividad_tipo_for e,yavirac_stror_tipo_for_educaci f \n" +
               "where a.ide_ynoace = b.ide_ynoace and c.ide_ynopee = a.ide_ynopee and d.ide_ynotie = c.ide_ynotie and e.ide_ynoace=b.ide_ynoace and e.ide_ysttfe=f.ide_ysttfe \n" +
               "and activo_ynopae in ("+estado+") ";
        if (tipo.equals("1")) {
            sql += " and c.ide_ystpea in (" + periodoacademico + ") and f.ide_ysttfe in ("+formacion+")";
        }
        //System.out.println("combo "+sql);
        return sql;
    }

    /**
     * Retorna periodo tipo evaluacion
     *
     * @param codigo.- permite crear una tabla maestro segun el periodo
     * academico.
     * @return sql del tipo de evaluacion
     */
    public String getPersonMallaDocente(String codigo) {
        String sql = "";
        sql += "select ide_ypemad,ide_ystpea, ide_ystmen, b.ide_ystnie, ide_ypedpe, ide_yhogra, ide_ystjor,b.ide_ystmal\n"
                + "from yavirac_perso_malla_docente a\n"
                + "join yavirac_stror_malla b on a.ide_ystmal = b.ide_ystmal\n"
                + "join yavirac_stror_nivel_educacion c on b.ide_ystnie = c.ide_ystnie\n"
                + "where a.ide_ypemad in (" + codigo + ")";
        return sql;
    }

    /**
     * Retorna Descripción Periodo Evaluación
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getPerioEvaluac(String activo) {
        String sql = "";
        sql += "select ide_ynopee,descripcion_ynotie\n"
                + "From yavirac_nota_periodo_evaluacio b \n"
                + "join yavirac_nota_tipo_evaluacion c on c.ide_ynotie = b.ide_ynotie where activo_ynotie in (" + activo + ")";
        return sql;
    }

    /**
     * Retorna consulta tipo examen
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getConsultaTipoExamen(String m, String n) {
        String sql = "";
        sql += "select ide_ynocan,b.ide_ynoace,descripcion_ynoace from yavirac_nota_cabecera_nota  a \n"
                + "join yavirac_nota_periodo_activ_eva  b on a.ide_ynopae  = b.ide_ynopae\n"
                + "join yavirac_nota_actividad_evaluac c on b.ide_ynoace = c.ide_ynoace "
                + "where a.ide_ynocan in (" + m + ") and  b.ide_ynoace in (" + n + ")";
        return sql;
    }

    /**
     * Retorna consulta tipo examen
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public List getNivelResumen() {
        List lista = new ArrayList();
        Object dato1[] = {
            "1", "Nivel 1"
        };
        Object dato2[] = {
            "2", "Nivel 2"
        };
        Object dato3[] = {
            "3", "Nivel 3"
        };
        lista.add(dato1);
        lista.add(dato2);
        lista.add(dato3);
        return lista;
    }

    /**
     * Retorna Descripción Periodo Evaluación
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getPeriodoEvaResumen(String periodoacademico) {
        String sql = "";
        sql += "select ide_ynopen,detalle_ynopen\n"
                + "from yavirac_nota_peso_nota a,yavirac_nota_tipo_evaluacion b,yavirac_stror_periodo_academic c\n"
                + "where a.ide_ynotie=b.ide_ynotie and a.ide_ystpea=c.ide_ystpea and nivel_ynopen=2 and activo_ystpea=true and activo_ynotie=true and c.ide_ystpea in (" + periodoacademico + ")";
        return sql;
    }

    /**
     * Retorna Descripción Periodo Evaluación
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getResumenDetalle(String activo) {
        String sql = "";
        sql += "select ide_ynopen,detalle_ynopen\n"
                + "from yavirac_nota_peso_nota a,yavirac_nota_tipo_evaluacion b,yavirac_stror_periodo_academic c\n"
                + "where a.ide_ynotie=b.ide_ynotie and a.ide_ystpea=c.ide_ystpea and nivel_ynopen=2 and activo_ystpea in (" + activo + ") and activo_ynotie in (" + activo + ")";
        return sql;
    }

    public String getConsultaAlumnos(String codigo, String alumno) {
        String sql = "";
        sql += "select * from yavirac_perso_malla_docen_alum \n"
                + "where ide_ypemad in (" + codigo + ") and ide_yaldap in (" + alumno + ")";
        return sql;
    }

    public String getPesoNivel(String nivel) {
        String sql = "";
        sql += "select ide_ynopen,ide_ynotie,ide_ystpea,yav_ide_ynopen,detalle_ynopen,peso_ynopen,nivel_ynopen\n"
                + "from yavirac_nota_peso_nota \n"
                + "where  nivel_ynopen in (" + nivel + ")";
        return sql;
    }

    public String getDetallePeso(String codigo) {
        String sql = "";
        sql += "select ide_ynodpn,ide_ynoace,a.ide_ynopen \n"
                + "from  yavirac_nota_peso_nota a,yavirac_nota_detalle_peso_acti b\n"
                + "where a.ide_ynopen = b.ide_ynopen and b.ide_ynopen in (" + codigo + ")";
        return sql;
    }

    public String getSumaNota(String periodoacademico, String docente, String materia, String nivel, String grupo, String jornada) {
        String sql = "";
        sql += "select sum(nota_ynodet) as nota,ide_yaldap,ide_ystnie,ide_ypedpe,ide_yhogra,ide_ystjor,ide_ystmal\n"
                + "from yavirac_nota_cabecera_nota  a,yavirac_nota_detalle_nota b,yavirac_nota_periodo_activ_eva c,yavirac_nota_periodo_evaluacio d,\n"
                + "yavirac_nota_tipo_evaluacion e,yavirac_nota_actividad_evaluac f\n"
                + "where a.ide_ynocan=b.ide_ynocan and a.ide_ynopae=c.ide_ynopae and c.ide_ynopee=d.ide_ynopee and d.ide_ynotie=e.ide_ynotie and c.ide_ynoace=f.ide_ynoace\n"
                + "and a.ide_ystpea in (" + periodoacademico + ") and ide_ypedpe in (" + docente + ") "
                + "and ide_ystmal in (" + materia + ") and ide_ystnie in (" + nivel + ") "
                + "and ide_yhogra in (" + grupo + ") and ide_ystjor in (" + jornada + ")  \n"
                + "group by ide_yaldap,ide_ystnie,ide_ypedpe,ide_yhogra,ide_ystjor,ide_ystmal \n";
        return sql;
    }

    public String getFormacion(String codigo) {
        String sql = "";
        sql += "select a.ide_ysttfe,detalle_ysttfe from yavirac_stror_mension a,yavirac_stror_tipo_for_educaci b where a.ide_ysttfe=b.ide_ysttfe and ide_ystmen in ("+codigo+")";
        return sql;
    }
}
