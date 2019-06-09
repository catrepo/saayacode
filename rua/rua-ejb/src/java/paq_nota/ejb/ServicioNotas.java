/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_nota.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

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
    public String getPeriodoActividadEvaluacion(String periodoacademico, String tipo, String estado, String formacion) {
        String sql = "";
        sql += "select  ide_ynopae,b.descripcion_ynoace,d.descripcion_ynotie,detalle_ysttfe\n"
                + "from  yavirac_nota_periodo_activ_eva a,yavirac_nota_actividad_evaluac b,yavirac_nota_periodo_evaluacio c,yavirac_nota_tipo_evaluacion d,\n"
                + "yavirac_nota_actividad_tipo_for e,yavirac_stror_tipo_for_educaci f \n"
                + "where a.ide_ynoace = b.ide_ynoace and c.ide_ynopee = a.ide_ynopee and d.ide_ynotie = c.ide_ynotie and e.ide_ynoace=b.ide_ynoace and e.ide_ysttfe=f.ide_ysttfe \n"
                + "and activo_ynopae in (" + estado + ") ";
        if (tipo.equals("1")) {
            sql += " and c.ide_ystpea in (" + periodoacademico + ") and f.ide_ysttfe in (" + formacion + ")";
        }
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
        sql += "select ide_ypemad,ide_ystpea, d.ide_ystmen, b.ide_ystnie, ide_ypedpe, ide_yhogra, ide_ystjor,b.ide_ystmal,d.ide_ysttfe\n"
                + "from yavirac_perso_malla_docente a,yavirac_stror_malla b,yavirac_stror_nivel_educacion c,yavirac_stror_mension d,yavirac_stror_tipo_for_educaci  e\n"
                + "where a.ide_ystmal = b.ide_ystmal and b.ide_ystnie = c.ide_ystnie and b.ide_ystmen=d.ide_ystmen and d.ide_ysttfe=e.ide_ysttfe\n"
                + "and a.ide_ypemad in (" + codigo + ")";
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
     * Retorna la consulta si aplica recuperacion del examen
     *
     * @param m.- permite el ingreso del paramtero ide de la tabla nota cabecera
     * @param n.- permite el ingreso del parametro ide de la tabla actividad de
     * evalución
     * @return sql de consulta tipo eamen
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
            "1", "NIVEL 1"
        };
        Object dato2[] = {
            "2", "NIVEL 2"
        };
        Object dato3[] = {
            "3", "NIVEL 3"
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
        sql += "select a.ide_ysttfe,detalle_ysttfe from yavirac_stror_mension a,yavirac_stror_tipo_for_educaci b where a.ide_ysttfe=b.ide_ysttfe and ide_ystmen in (" + codigo + ")";
        return sql;
    }

    public String getImportarSumaNotas(String aplicanota, String tipo, String periodoacademico, String docente, String malla, String nivel, String paralelo, String jornada, String parcial, String alumno, String formacion, String actividad) {
        String sql = "";
        String case_nota = "1";
        if (aplicanota.equals("1")) {
            case_nota = "(case when aplica_recuperacion_ystpea=true then case when f.ide_ynoace=cast((select valor_para from sis_parametros where nom_para = 'p_tipo_eva_examen') as numeric) then\n"
                    + "case when recuperacion_ynodet=true then nota_ynodet else nota_ynodet*2 end else nota_ynodet end else nota_ynodet end)";
        } else {
            case_nota = " nota_ynodet ";
        }
        if (tipo.equals("1")) {
            sql += "select (sum(nota) / count(ide_ynoace)) as notas,a.ide_ystpea,a.ide_ypedpe,a.ide_ystmal,a.ide_ystnie,a.ide_yhogra,a.ide_ystjor,a.ide_ynotie,a.ide_yaldap,a.ide_ysttfe,a.ide_ynoace,a.ide_ynopae,recuperacion_ynodet\n"
                    + "from ( ";
        }
        sql += "select " + case_nota + " as nota,\n"
                + "ide_yaldap,ide_ystnie,ide_ypedpe,ide_yhogra,ide_ystjor,ide_ystmal,a.ide_ynopae,e.ide_ynotie,i.ide_ysttfe,g.ide_ystpea\n"
                + ",aplica_recuperacion_ystpea,recuperacion_ynodet,f.ide_ynoace\n"
                + "from yavirac_nota_cabecera_nota  a,yavirac_nota_detalle_nota b,yavirac_nota_periodo_activ_eva c,yavirac_nota_periodo_evaluacio d,\n"
                + "yavirac_nota_tipo_evaluacion e,yavirac_nota_actividad_evaluac f,yavirac_stror_periodo_academic g,yavirac_nota_actividad_tipo_for h,\n"
                + "yavirac_stror_tipo_for_educaci i\n"
                + "where a.ide_ynocan=b.ide_ynocan and a.ide_ynopae=c.ide_ynopae and c.ide_ynopee=d.ide_ynopee and d.ide_ynotie=e.ide_ynotie \n"
                + "and c.ide_ynoace=f.ide_ynoace and a.ide_ystpea=g.ide_ystpea and f.ide_ynoace=h.ide_ynoace and h.ide_ysttfe=i.ide_ysttfe and activo_ynopae in (true,false) \n"
                + "and a.ide_ystpea=" + periodoacademico + " and ide_ypedpe=" + docente + "  and ide_ystmal=" + malla + " and ide_ystnie=" + nivel + " and ide_yhogra=" + paralelo + " and ide_ystjor=" + jornada + " and e.ide_ynotie=" + parcial + " and b.ide_yaldap=" + alumno + " and i.ide_ysttfe=" + formacion + " and f.ide_ynoace in (" + actividad + ")";

        if (tipo.equals("1")) {
            sql += " ) a\n"
                    + "group by ide_ystpea,ide_ypedpe,ide_ystmal,ide_ystnie,ide_yhogra,ide_ystjor,ide_ynotie,ide_yaldap,ide_ysttfe,ide_ynoace,ide_ynopae,recuperacion_ynodet";
        }
        return sql;
    }

    public String getPesoNota(String nivel, String estado, String formacion) {
        String sql = "";
        /*sql += "select ide_ynopen,ide_ysttfe,ide_ynotie,peso_ynopen,nivel_ynopen,bloqueo_ynopen from yavirac_nota_peso_nota\n"
                + "where nivel_ynopen in (" + nivel + ") and bloqueo_ynopen in(" + estado + ") and ide_ysttfe in (" + formacion + ")";
         */
        sql += "select ide_ynopen,ide_ysttfe,a.ide_ynotie,peso_ynopen,nivel_ynopen,bloqueo_ynopen,bloquear_ynotie\n"
                + "from yavirac_nota_peso_nota a\n"
                + "left join yavirac_nota_tipo_evaluacion b on a.ide_ynotie=b.ide_ynotie \n"
                + "where nivel_ynopen in (" + nivel + ") and bloqueo_ynopen in(" + estado + ") and ide_ysttfe in (" + formacion + ") and bloquear_ynotie= false";
        return sql;
    }

    public String getPesoDetalleNota(String codigo) {
        String sql = "";
        sql += "select ide_ynodpn,ide_ynoace,ide_ynopen from yavirac_nota_detalle_peso_acti\n"
                + "where ide_ynopen in(" + codigo + ") ";
        return sql;
    }

    /**
     * Retorna los datos de la tabla Peso notas
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del peso notas
     */
    public String getPesoNotas(String estado) {
        String sql = "";
        sql += " select ide_ynopen,detalle_ynopen,descripcion_ynotie\n"
                + " from yavirac_nota_peso_nota a  \n"
                + " left join yavirac_nota_tipo_evaluacion b on a.ide_ynotie=b.ide_ynotie\n"
                + " where bloqueo_ynopen in (" + estado + ") ";
        return sql;
    }

    public String getPorcientoParametroEvaluacion(String nota, String docente, String malla, String nivel, String paralelo, String jornada, String actividad) {
        String sql = "";
        sql += "select ide_ynoacd,round(((" + nota + ") * porciento_evaluacion_ynoacd),2) as porcentaje \n"
                + "from yavirac_nota_actividad_docente a,yavirac_nota_periodo_activ_eva c,yavirac_nota_actividad_evaluac d\n"
                + "where a.ide_ynopae=c.ide_ynopae and c.ide_ynoace=d.ide_ynoace and \n"
                + "ide_ypedpe in (" + docente + ")  and ide_ystmal in (" + malla + ") and ide_ystnie in (" + nivel + ") and ide_yhogra in (" + paralelo + ") and ide_ystjor in (" + jornada + ") and d.ide_ynoace in (" + actividad + ") ";
        return sql;
    }

    public String getActualizarTablaResumenNota(String malladocente, String pesonota) {

        String sql = "";
        sql += "delete from yavirac_nota_alumno_resumen "
                + " where ide_ypemda in (" + malladocente + ") and ide_ynopen in (" + pesonota + ") ";
        return sql;
    }

    public String getActualizarTablaResumen(String periodoacademico, String mension, String nivel, String docente, String paralelo, String jornada, String malla, String alumno, String pesonota) {

        String sql = "";
        sql += "delete from yavirac_nota_resumen "
                + " where ide_ystpea in (" + periodoacademico + ") and ide_ystmen in (" + mension + ") and ide_ystnie in (" + nivel + ") and ide_ypedpe in (" + docente + ") and ide_yhogra in (" + paralelo + ") and ide_ystjor in (" + jornada + ") and ide_ystmal in (" + malla + ") \n"
                + "and ide_yaldap in (" + alumno + ")  and ide_ynopen in (" + pesonota + ") ";
        return sql;
    }

    /**
     * Retorna la nota total de la actividad de clases y el total del examen
     *
     * @param periodoacademico .- permite el ingreso del paramtero activo para
     * filtrar ya
     * @param mension .-
     * @param nivel .-
     * @param docente .-
     * @param paralelo .-
     * @param jornada .-
     * @param malla .-
     * @param alumno .-
     * @param pesonota .-
     * @return sql de Nota total a nivel 3
     */
    public String getNotaTotalTercerNivel(String recuperacion, String parametro, String periodoacademico, String mension, String nivel, String docente, String paralelo, String jornada, String malla, String alumno, String pesonota) {
        String sql = "";
        sql += "select 1 as codigo,round(((sum(porciento_ynores)* " + parametro + ")/100),2) as notatotal,b.ide_yaldap,b.ide_ynopen\n"
                + "from (\n";
        if (recuperacion.equals("1")) {
            sql += "select (sum(porciento_ynores) / count(porciento_ynores)) as porciento_ynores ,a.ide_yaldap,a.ide_ynopen from (\n";
        }
        sql += "select ide_ynores,ide_yaldap,ide_ynopen,porciento_ynores,recuperacion_ynores,ide_ynopae from yavirac_nota_resumen \n"
                + "where ide_ystpea in (" + periodoacademico + ") and ide_ystmen in (" + mension + ") and ide_ystnie in (" + nivel + ") and ide_ypedpe in (" + docente + ") and ide_yhogra in (" + paralelo + ") and ide_ystjor in (" + jornada + ") and ide_ystmal in (" + malla + ") \n"
                + "and ide_yaldap in (" + alumno + ")  and ide_ynopen in (" + pesonota + ") \n";
        if (recuperacion.equals("1")) {
            sql += ") a\n"
                    + "group by ide_yaldap,ide_ynopen\n";
        }
        sql += ") b\n"
                + " group by ide_yaldap,ide_ynopen ";
        return sql;

    }

    /**
     * Retorna la nota total de la actividad de clases y el total del examen
     *
     * @param periodoacademico .- permite el ingreso del paramtero activo para
     * filtrar ya
     * @param mension .-
     * @param nivel .-
     * @param docente .-
     * @param paralelo .-
     * @param jornada .-
     * @param malla .-
     * @param alumno .-
     * @param pesonota .-
     * @return sql de Nota total a nivel 3
     */
    public String getInsertarTabResumen(String codigo, String periodoacademico, String mension, String nivel, String docente, String paralelo, String jornada, String actividad, String malla, String alumno, String pesonota, String nota, String porciento, String recuperacion) {
        String sql = "";
        sql += "insert into yavirac_nota_resumen(\n"
                + "            ide_ynores,ide_ystpea, ide_ystmen, ide_ystnie, ide_ypedpe, ide_yhogra, \n"
                + "            ide_ystjor, ide_ynopae, ide_ystmal, ide_yaldap, ide_ynopen, nota_ynores, \n"
                + "            porciento_ynores, recuperacion_ynores)\n"
                + "    values (" + codigo + "," + periodoacademico + ", " + mension + ", " + nivel + ", " + docente + ", " + paralelo + ", \n"
                + "            " + jornada + ", " + actividad + ", " + malla + ", " + alumno + ", " + pesonota + ", " + nota + ", \n"
                + "            " + porciento + ", " + recuperacion + ");";
        return sql;

    }

    /**
     * Retorna la nota total de la actividad de clases y el total del examen
     *
     * @param periodoacademico .- permite el ingreso del paramtero activo para
     * filtrar ya
     * @param mension .-
     * @param nivel .-
     * @param pesonota .-
     * @return sql de Nota total a nivel 3
     */
    public String getInsertarTabAlumnoResumen(String codigo, String pesonota, String malladocentealumno, String nota, String porcentaje) {
        String sql = "";
        sql += "insert into yavirac_nota_alumno_resumen(\n"
                + "            ide_ynoalr, ide_ynopen, ide_ypemda, nota_ynoalr, porcentaje_evaluacion_ynoalr)\n"
                + "values (" + codigo + ", " + pesonota + ", " + malladocentealumno + ", " + nota + ", " + porcentaje + ");";
        return sql;
    }

    public String getConsultaTabResumen(String periodoacademico, String mension, String nivel, String docente, String paralelo, String jornada, String malla, String alumno, String pesonota) {
        String sql = "";
        //sql +="select 1 as cod, 2 as nota";
        sql += "select  ide_ynores,recuperacion_ynores from yavirac_nota_resumen\n"
                + "where ide_ystpea in (" + periodoacademico + ") and ide_ystmen in (" + mension + ") and ide_ystnie in (" + nivel + ") and ide_ypedpe in (" + docente + ") and ide_yhogra in (" + paralelo + ") and ide_ystjor in (" + jornada + ") and ide_ystmal in (" + malla + ") \n"
                + "and ide_yaldap in (" + alumno + ")  and ide_ynopen in (" + pesonota + ") ";
        return sql;

    }

    public String getPadreSegundoNivel(String nivel, String estado) {
        String sql = "";
        sql += "select ide_ynopen,ide_ysttfe,a.ide_ynotie,peso_ynopen,nivel_ynopen,bloqueo_ynopen,bloquear_ynotie\n"
                + "from yavirac_nota_peso_nota a\n"
                + "left join yavirac_nota_tipo_evaluacion b on a.ide_ynotie=b.ide_ynotie \n"
                + "where nivel_ynopen in (" + nivel + ") and bloqueo_ynopen in(" + estado + ") and bloquear_ynotie= false";
        return sql;

    }

    public String getPadreTercerNivel(String nivel, String estado) {
        String sql = "";
        sql += "select ide_ynopen,ide_ysttfe,ide_ynotie,detalle_ynopen,peso_ynopen,nivel_ynopen,bloqueo_ynopen from yavirac_nota_peso_nota\n"
                + "where nivel_ynopen in (" + nivel + ") and bloqueo_ynopen in(" + estado + ")";
        return sql;
    }

    public String getConsultarNotaTotalSegundoNivel(String codigo, String periodoacademico, String parcial, String alumno) {
        String sql = "";
        sql += "select sum(nota_ynoalr) as total,ide_yaldap,yav_ide_ynopen\n"
                + "from( \n"
                + "	select a.ide_ypemda,ide_ystpea,ide_ysttfe,ide_ynotie,ide_yaldap,c.ide_ynopen,yav_ide_ynopen,b.ide_ynopen,nota_ynoalr,peso_ynopen,nivel_ynopen,detalle_ynopen\n"
                + "	from yavirac_perso_malla_docen_alum a,yavirac_nota_alumno_resumen b,yavirac_nota_peso_nota c\n"
                + "	where a.ide_ypemda=b.ide_ypemda and b.ide_ynopen=c.ide_ynopen \n"
                + "	and  ide_ystpea in (" + periodoacademico + ")  and ide_ynotie in (" + parcial + ") and ide_yaldap in (" + alumno + ") \n"
                + ")a\n"
                + "where yav_ide_ynopen in (" + codigo + ")\n"
                + "group by ide_yaldap,yav_ide_ynopen";
        return sql;
    }

    public String getInsertarTotalNotaSegundoNivel(String codigo, String pesonota, String malladocentealumno, String nota, String porcentaje) {
        String sql = "";
        sql += "insert into yavirac_nota_alumno_resumen(\n"
                + "            ide_ynoalr, ide_ynopen, ide_ypemda, nota_ynoalr, porcentaje_evaluacion_ynoalr)\n"
                + "values (" + codigo + ", " + pesonota + ", " + malladocentealumno + ", " + nota + ", " + porcentaje + ");";
        return sql;
    }

    public String getConsultarNotaTotalTercerNivel(String codigo, String periodoacademico, String alumno) {
        String sql = "";
        sql += "select sum(nota_ynoalr) as total,ide_yaldap,yav_ide_ynopen\n"
                + "from( \n"
                + "	select a.ide_ypemda,ide_ystpea,ide_ysttfe,ide_ynotie,ide_yaldap,c.ide_ynopen,yav_ide_ynopen,b.ide_ynopen,nota_ynoalr,peso_ynopen,nivel_ynopen,detalle_ynopen\n"
                + "	from yavirac_perso_malla_docen_alum a,yavirac_nota_alumno_resumen b,yavirac_nota_peso_nota c\n"
                + "	where a.ide_ypemda=b.ide_ypemda and b.ide_ynopen=c.ide_ynopen \n"
                + "	and  ide_ystpea in (" + periodoacademico + ") and ide_yaldap in (" + alumno + ") \n"
                + ")a\n"
                + "where yav_ide_ynopen in (" + codigo + ")\n"
                + "group by ide_yaldap,yav_ide_ynopen";
        return sql;
    }

    public String getCerrarPeriodoAcademico(String codigo) {
        String sql = "";
        sql = "update yavirac_stror_periodo_academic set activo_ystpea=false where ide_ystpea=" + codigo + " ";
        return sql;
    }

    /**
     * Retorna el Tipo de Evaluacion
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getBloquearParcial(String codigo) {
        String sql = "";
        sql += "update yavirac_nota_tipo_evaluacion set\n"
                + "bloquear_ynotie=true\n"
                + "where ide_ynotie=" + codigo + " ";
        System.out.println("<<<<< Impimiendo" + sql);
        return sql;
    }

    /**
     * Retorna el Tipo de Evaluacion
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya
     * sea true, false, o ambos.
     * @return sql del tipo de evaluacion
     */
    public String getDesbloquearParcial(String codigo) {
        String sql = "";
        sql += "update yavirac_nota_tipo_evaluacion set\n"
                + "bloquear_ynotie=false\n"
                + "where ide_ynotie=" + codigo + " ";
        return sql;
    }

    public String getBloquearActividad(String codigo) {
        String sql = "";
        sql += "update  yavirac_nota_periodo_activ_eva set\n"
                + "activo_ynopae=false\n"
                + "where ide_ynopee=" + codigo + " ";
        System.out.println("<<<<< Impimiendo" + sql);
        return sql;
    }

    public String getDesbloquearActividad(String codigo) {
        String sql = "";
        sql += "update  yavirac_nota_periodo_activ_eva set\n"
                + "activo_ynopae=true\n"
                + "where ide_ynopee=" + codigo + " ";
        return sql;
    }

    public String getInsertarCabeceraRecordAcademico(String codigo,String alumno,String mension,String fecha_inico,String fecha_fin) {
        String sql = "";
        sql += "insert into yavirac_nota_cab_rec_acad(ide_ynocra, ide_yaldap, ide_ystmen, fecha_inicio_ynocra, fecha_fin_ynocra)\n"
                + "values ("+codigo+", "+alumno+", "+mension+", '"+fecha_inico+"', '"+fecha_fin+"')";
        return sql;
    }

}
