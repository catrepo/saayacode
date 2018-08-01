/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horarios.ejb;

import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import sistema.aplicacion.Pantalla;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author Andres
 */
@Stateless
public class ServiciosHorarios {
    private final Utilitario utilitario = new Utilitario();
    
       /**
     * Retorna el periodo academico vigente
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del periodo academico
     */
    public String getHorarios(String activo) {
        String sql="";
        sql="select ide_yhothj, descripcion_yhothj from yavirac_hora_tipo_horario_jorna  where activo_yhothj in ("+activo+") order by descripcion_yhothj desc";
        return sql;
    }
    
     public String getDefinicionJornada(String ide_modalidad, String ide_periodo) {
        String sql="";
        sql= "select distinct c.ide_ystjor, d.descripcion_ystjor from  yavirac_hora_definicion_hora c,  yavirac_stror_jornada d where c.ide_ystjor = d.ide_ystjor and d.descripcion_ystjor = d.descripcion_ystjor and c.ide_ystmod="+ide_modalidad+" and c.ide_ystpea="+ide_periodo+" order by c.ide_ystjor asc";
                //"select ide_ystjor from yavirac_hora_definicion_hora  where activo_yhodeh in ("+activo+")"; //order by descripcion_yhothj desc";
        return sql;
    }
               /**
     * Retorna la descripcion hora
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de la descripcion hora
     */
    public String getDescripcionHora(String codigo) {
        String sql="";
        sql="select  ide_yhodeh, c.ide_ystpea, d.ide_yhothj, a.ide_ystjor, f.ide_ystmod, hora_inicio_yhodeh, hora_final_yhodeh, activo_yhodeh, descripcion_ystjor, descripcion_ystpea, descripcion_yhothj, descripcion_ystmod \n" +
"from yavirac_hora_definicion_hora a, yavirac_stror_jornada b, yavirac_stror_periodo_academic c, yavirac_hora_tipo_horario_jorna d, yavirac_stror_modalidad f\n" +
"where a.ide_ystjor= b.ide_ystjor \n" +
"and a.ide_ystpea= c.ide_ystpea \n" +
"and a.ide_yhothj= d.ide_yhothj \n" +
"and a.ide_ystmod= f.ide_ystmod\n" +
"and a.ide_ystpea = "+codigo+
"order by descripcion_ystjor "
                ;
        return sql;
    }
     /**
     * Retorna la hora
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de la tabla hora
     */
    public String getHora(String activo) {
        String sql="";
        sql="select ide_yhohor, descripcion_yhohor from yavirac_hora_hora  where activo_yhohor in ("+activo+") order by descripcion_yhohor desc";
        return sql;
    }
    
    /**
     * Retorna el Tipo Horarios
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del tipo horarios
     */
    public String getTipoHorarios(String activo) {
        String sql="";
        sql="select ide_yhotih, descripcion_yhotih from yavirac_hora_tipo_horario  where activo_yhotih in ("+activo+") order by descripcion_yhotih ";
        return sql;
    }
     /**
     * Retorna el insert de la replica definicion hora
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql del insert replica definicion hora
     */
    public String insertReplicaDefinicionHora(String ide_yhodeh,String ide_ystpea,String ide_yhothj,String ide_ystjor,String ide_ystmod,String hora_inicio_yhodeh,String hora_final_yhodeh,String activo_yhodeh) {
        String sql="";
        sql="insert into yavirac_hora_definicion_hora (ide_yhodeh, ide_ystpea, ide_yhothj, ide_ystjor, ide_ystmod, hora_inicio_yhodeh, hora_final_yhodeh, activo_yhodeh)\n" +
            "values ("+ide_yhodeh+","+ide_ystpea+", "+ide_yhothj+", "+ide_ystjor+", "+ide_ystmod+", '"+hora_inicio_yhodeh+"', '"+hora_final_yhodeh+"', "+activo_yhodeh+") ";
        return sql;
    }
 
       /**
     * Retorna el Periodo de la Hora
     *
     * @param activo.- permite el calculo de periodo de la hora 
     * @return sql del periodo de la hora 
     */
    public String getPeriodoHora(String ide_yhothj, String ide_ystpea) {
        String sql="";
        sql="SELECT   y.ide_yhodeh,y.ide_ystpea,y.ide_yhothj,y.ide_ystjor,y.ide_ystmod,y.descripcion_yhothj,y.descripcion_ystjor,y.descripcion_ystmod,y.hora_inicio_yhodeh,y.hora_final_yhodeh,y.resultado,x.descripcion_yhothj,x.descripcion_ystjor,x.descripcion_ystmod,\n" +
            "x.hora_inicio_yhodeh,x.hora_final_yhodeh,\n" +
            "(case when x.resultado is null then 0 else x.resultado end)as resultadoreceso,\n" +
            "(case when x.hora_clase_ystpea is null then 0 else x.hora_clase_ystpea end),\n" +
            "(case when (y.resultado - x.resultado) / y.hora_clase_ystpea is null then 0 else (y.resultado - x.resultado) / y.hora_clase_ystpea end) as resultadofinal\n" +
            "from (SELECT   ide_yhodeh,c.ide_ystpea, c.ide_yhothj, f.descripcion_yhothj,c.ide_ystjor,b.descripcion_ystjor,c.ide_ystmod,e.descripcion_ystmod,\n" +
            "date_part('hour', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp))) as hora1,\n" +
            " date_part('hour', cast('2001-02-16 '||hora_final_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-09-30 '||hora_final_yhodeh as timestamp))) as hora2,\n" +
            "hora_inicio_yhodeh,hora_final_yhodeh,\n" +
            "( date_part('hour', cast('2001-02-16 '||hora_final_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-09-30 '||hora_final_yhodeh as timestamp)))) - \n" +
            "(date_part('hour', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp)))) as resultado,\n" +
            " d.hora_clase_ystpea\n" +
            "from yavirac_hora_definicion_hora c , yavirac_stror_periodo_academic d, yavirac_stror_jornada b, yavirac_stror_modalidad e, yavirac_hora_tipo_horario_jorna f\n" +
            "where c.ide_ystpea = d.ide_ystpea\n" +
            "and c.ide_ystjor=b.ide_ystjor\n" +
            "and c.ide_ystmod=e.ide_ystmod\n" +
            "and c.ide_yhothj=f.ide_yhothj\n" +
            "and c.ide_yhothj= "+ide_yhothj+"\n" +
            "and c.ide_ystpea="+ide_ystpea+"\n" +
            ") y\n" +
            "left join (\n" +
            "SELECT   ide_yhodeh,c.ide_ystpea, c.ide_yhothj, f.descripcion_yhothj,c.ide_ystjor,b.descripcion_ystjor,c.ide_ystmod,e.descripcion_ystmod,\n" +
            "date_part('hour', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp))) as hora1,\n" +
            " date_part('hour', cast('2001-02-16 '||hora_final_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-09-30 '||hora_final_yhodeh as timestamp))) as hora2,\n" +
            "hora_inicio_yhodeh,hora_final_yhodeh,\n" +
            "( date_part('hour', cast('2001-02-16 '||hora_final_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-09-30 '||hora_final_yhodeh as timestamp)))) - \n" +
            "(date_part('hour', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp) ) * 60\n" +
            "+ ( date_part('minute', cast('2017-05-01 '||hora_inicio_yhodeh as timestamp)))) as resultado,\n" +
            " d.hora_clase_ystpea\n" +
            "from yavirac_hora_definicion_hora c , yavirac_stror_periodo_academic d, yavirac_stror_jornada b, yavirac_stror_modalidad e, yavirac_hora_tipo_horario_jorna f\n" +
            "where c.ide_ystpea = d.ide_ystpea\n" +
            "and c.ide_ystjor=b.ide_ystjor\n" +
            "and c.ide_ystmod=e.ide_ystmod\n" +
            "and c.ide_yhothj=f.ide_yhothj\n" +
            "and c.ide_yhothj= "+ide_yhothj+"\n" +
            "and c.ide_ystpea="+ide_ystpea+"\n" +
            ") x \n" +
            "on y.ide_ystjor= x.ide_ystjor  ";
        return sql;
    }  
        public String getGrupoAcademico() {
        String sql="";
        sql="SELECT ide_yhogra, detalle_yhogra FROM yavirac_hora_grupo_academic order by detalle_yhogra desc";
        return sql;
    }
   
        public String getTipoGeneracionHora() {
        String sql="";
        sql="SELECT ide_yhotgh, detalle_yhotgh FROM yavirac_hora_tipo_genera_hor ";
        return sql;
    }
        public String getDia() {
        String sql="";
        sql="select ide_yhodia, descripcion_yhodia from yavirac_hora_dia order by orden_yhodia asc ";
        return sql;
    }
         public String getMod() {
        String sql="";
        sql="select ide_ystmod, descripcion_ystmod from yavirac_stror_modalidad  ";
        return sql;
    }
         public String getDistribucionInstalacion() {
        String sql="";
        sql="select ide_yhodin, descripcion_yhodin from yavirac_hora_distribuc_instit";
        return sql;
         }
         public String getTipoAdecuacion() {
        String sql="";
        sql="select ide_yhotad, descripcion_yhotad from yavirac_hora_tipo_adecuacion";
        return sql;
         }
        public String getDefinicionHoras(String ide_ystjor, String ide_ystpea, String ide_ystmod) {
        String sql="";
        sql="select a.ide_yhodeh, b.descripcion_yhothj, c.descripcion_ystjor, d.descripcion_ystmod, a.hora_inicio_yhodeh, a.hora_final_yhodeh\n" +
            "from yavirac_hora_definicion_hora a\n" +
            "inner join yavirac_hora_tipo_horario_jorna b on a.ide_yhothj = b.ide_yhothj\n" +
            "inner join yavirac_stror_jornada c on a.ide_ystjor = c.ide_ystjor\n" +
            "inner join yavirac_stror_modalidad d on a.ide_ystmod = d.ide_ystmod\n" +
            "where a.ide_ystpea ="+ide_ystpea+" and a.ide_ystmod ="+ide_ystmod+" and a.ide_ystjor = "+ide_ystjor+"" ;
        return sql;
         }
        public String getDefinicionReceso(String ide_ystjor, String ide_ystpea, String ide_ystmod, String ide_yhothj,String estado) {
        String sql="";
        sql="select a.ide_yhodeh, a.ide_ystpea, a.ide_yhothj, b.descripcion_yhothj, a.ide_ystjor, c.descripcion_ystjor, a.ide_ystmod, d.descripcion_ystmod, a.hora_inicio_yhodeh, a.hora_final_yhodeh, a.activo_yhodeh\n" +
            "from yavirac_hora_definicion_hora a\n" +
            "inner join yavirac_hora_tipo_horario_jorna b on a.ide_yhothj = b.ide_yhothj\n" +
            "inner join yavirac_stror_jornada c on a.ide_ystjor = c.ide_ystjor\n" +
            "inner join yavirac_stror_modalidad d on a.ide_ystmod = d.ide_ystmod\n" +
            "where activo_yhodeh in ("+estado+") and a.ide_ystpea in ("+ide_ystpea+")  and a.ide_yhothj in ("+ide_yhothj+")  and a.ide_ystmod in ("+ide_ystmod+") and a.ide_ystjor in ("+ide_ystjor+")" ;
        return sql;
         }
        public String getNumDias(String ide_yhodia) {
        String sql="";
        sql="select ide_yhodia, descripcion_yhodia from yavirac_hora_dia where ide_yhodia in ("+ide_yhodia+") order by descripcion_yhodia" ;
        return sql;
         }
        public String getNumMension(String ide_ystmen) {
        String sql="";
        sql="select ide_ystmen, descripcion_ystmen from yavirac_stror_mension where ide_ystmen in ("+ide_ystmen+") order by descripcion_ystmen" ;
        return sql;
         }
        public String getResultadoExisteReceso(String hora_receso,String hora_inicio,String hora_fin) {
        String sql="";
        sql="select 1 as resultado,2 as respuesta from yavirac_hora_definicion_hora where ('"+hora_receso+"'::interval + '60' ::interval) between '"+hora_inicio+"'::interval and '"+hora_fin+"'::interval limit 1" ;
        //System.out.println("imprimo resultao receso "+sql);
        return sql;
         }  
        
        public String insertHorarioMatriz(String tipo_horario,String periodo, String mension, String jornada, String modalidad) {
        String sql="";
        sql="INSERT INTO yavirac_matriz_temp(ide_yamatz, ide_ystmal, ide_ystnie,ide_ystmat,ide_ystmen,ide_ysttif,ide_ystjor,\n" +
"ide_ymacal, ide_yhogra, ide_ystins, ide_ypedpe, numero_horas_ystmal, prioridad_materia_ystmal, maximo_horas_ystmal,ide_ystpea,minimo_horas_ystmal, \n" +
"aplica_laboratorio_ystmal, num_min_lab_ystmal, num_max_lab_ystmal,horas_semana_ystmal,horas_semana_lab_ystmal, ide_ystmod) \n" +
"select row_number()over(order by a.ide_ystmal) + (select (case when max( ide_yamatz) is null then 0 else max( ide_yamatz) end) as codigo \n" +
"from yavirac_matriz_temp) as ide, a.ide_ystmal, a.ide_ystnie, a.ide_ystmat, a.ide_ystmen, a.ide_ysttif,a.ide_ystjor,a.ide_ymacal,a.ide_yhogra, \n" +
"b.ide_ystins,c.ide_ypedpe, a.numero_horas_ystmal, a.prioridad_materia_ystmal, a.maximo_horas_ystmal, a.ide_ystpea, a.minimo_horas_ystmal, \n" +
"a.aplica_laboratorio_ystmal, a.num_min_lab_ystmal, a.num_max_lab_ystmal,a.horas_semana_ystmal, (case when a.horas_semana_lab_ystmal is null \n" +
"then 0 else a.horas_semana_lab_ystmal end) as hora_lab, d.ide_ystmod from ( select a.ide_ystmal, a.ide_ystnie, a.ide_ystmat, a.ide_ystmen, a.ide_ysttif, \n" +
"a.numero_horas_ystmal, a.prioridad_materia_ystmal, a.maximo_horas_ystmal, a.minimo_horas_ystmal, a.aplica_laboratorio_ystmal, a.num_min_lab_ystmal, \n" +
"a.num_max_lab_ystmal,ide_ystjor,ide_yhogra,ide_ymacal,b.ide_ystpea,a.horas_semana_ystmal,a.horas_semana_lab_ystmal \n" +
"from yavirac_stror_malla a,yavirac_stror_periodo_mension b, yavirac_hora_malla_periodo c,yavirac_matri_cupo_alumno d \n" +
"where a.ide_ystmen = b.ide_ystmen and b.ide_ystpea= c.ide_ystpea and a.ide_ystmal= c.ide_ystmal and b.ide_ystpea=d.ide_ystpea \n" +
"and a.ide_ystnie=d.ide_ystnie and a.ide_ystmen=d.ide_ystmen and b.ide_ystpea = "+periodo+" ) a \n" +
"left join yavirac_hora_aula_grupo b on a.ide_ymacal=b.ide_ymacal \n" +
"left join yavirac_perso_malla_docente c on a.ide_ystmal=c.ide_ystmal \n" +
"and a.ide_ystpea= c.ide_ystpea and a.ide_yhogra=c.ide_yhogra \n" +
"and a.ide_ystjor = c.ide_ystjor \n" +
"left join yavirac_hora_periodo_hor d on a.ide_ystpea = d.ide_ystpea and a.ide_ystjor = d.ide_ystjor and a.ide_ystmen = d.ide_ystmen\n" +
"where a.ide_ystjor = "+jornada+"\n" +
"and d.ide_ystmod = "+modalidad+"\n" +
"and a.ide_ystmen = "+mension+"" ;
            if(tipo_horario.equals("1")){
            sql +=" and aplica_laboratorio_ystmal=true " ;
            }
            if(tipo_horario.equals("2")){
            sql +=" and aplica_laboratorio_ystmal=false " ;
            }
        //System.out.println("imprimo resultao receso "+sql);
        return sql;
         }  
        
        public int sumDetalleMatriz(String ide){
            int suma_hora=0;
            TablaGenerica tab_suma_detalle_matriz_temp=utilitario.consultar("select 1 as codigo,(case when sum(horas_semana_lab_yamadt) is null then 0 else sum(horas_semana_lab_yamadt) end) +\n" +
            "(case when sum(horas_semana_yamadt) is null then 0 else sum(horas_semana_yamadt) end) as hora_clase\n" +
            "from yavirac_matriz_detalle_temp group by ide_yamatz having ide_yamatz ="+ide);
            if(tab_suma_detalle_matriz_temp.getTotalFilas()>0){
                suma_hora= Integer.parseInt(tab_suma_detalle_matriz_temp.getValor("hora_clase"));
            }
            return suma_hora;
        }
        public int horaLaboratorio(String ide){
            int suma_hora=0;
            TablaGenerica tab_suma_detalle_matriz_temp=utilitario.consultar("select 1 as codigo,(case when sum(horas_semana_lab_yamadt) is null then 0 else sum(horas_semana_lab_yamadt) end) as hora_lab\n" +
                "from yavirac_matriz_detalle_temp group by ide_yamatz having ide_yamatz ="+ide);
            if(tab_suma_detalle_matriz_temp.getTotalFilas()>0){
                suma_hora= Integer.parseInt(tab_suma_detalle_matriz_temp.getValor("hora_lab"));
            }
            return suma_hora;
        }
        public int horaClase(String ide){
            int suma_hora=0;
            TablaGenerica tab_suma_detalle_matriz_temp=utilitario.consultar("select 1 as codigo,(case when sum(horas_semana_yamadt) is null then 0 else sum(horas_semana_yamadt) end) + (case when sum(horas_semana_lab_yamadt) is null then 0 else sum(horas_semana_lab_yamadt) end) as hora_clase\n" +
                "from yavirac_matriz_detalle_temp group by ide_yamatz having ide_yamatz ="+ide);
            if(tab_suma_detalle_matriz_temp.getTotalFilas()>0){
                suma_hora= Integer.parseInt(tab_suma_detalle_matriz_temp.getValor("hora_clase"));
            }
            return suma_hora;
        }
        public String insertaMatrizDetalle(String codigo,String ide_yamatz,String ide_ystnie,String ide_ystmal, String ide_ypedpe,String ide_ystmat,String ide_ystmen,String ide_ysttif,String ide_ystjor,
                                           String ide_ymacal,String ide_yhogra,String ide_ystins,String ide_ystpea,String aplica_laboratorio_yamadt,String horas_semana_lab_yamadt,String horas_semana_yamadt, String ide_ystmod){
            String sql="";
            sql="INSERT INTO yavirac_matriz_detalle_temp( ide_yamadt, ide_yamatz, ide_ystmal, ide_ystnie, ide_ypedpe, ide_ystmat,ide_ystmen, ide_ysttif, ide_ystjor, \n" +
            "ide_ymacal, ide_yhogra, ide_ystins,ide_ystpea, aplica_laboratorio_yamadt, horas_semana_lab_yamadt,horas_semana_yamadt, ide_ystmod) values ( "
                    + codigo+","+ide_yamatz+", "+ide_ystnie+", "+ide_ystmal+", "+ide_ypedpe+", "+ide_ystmat+", "+ide_ystmen+", "+ide_ysttif+", "+ide_ystjor+", "+ide_ymacal+", "+ide_yhogra+", "+ide_ystins+", "+ide_ystpea+", "+aplica_laboratorio_yamadt+", "+horas_semana_lab_yamadt+", "+horas_semana_yamadt+", "+ide_ystmod+")";
            System.out.println("sql: "+sql);
            return sql;
        }
        public String validaGeneracionHorarioLaboratorio(String tipo_consulta,String tipo_horario,String ide_ystmod,String ide_ystjor,String ide_ystpea,String ide_yhodia,String ide_ystmen,String ide_yhogra,String ide_ystins,String ide_ypedpe,String condicion){
            String sql="";
            if(tipo_consulta.equals("1")){
                sql+="select ide_yhodia,sum(hora_ocupada) as hora_ocupada,sum( hora_clase) as hora_clase,sum( hora_clase)-sum(hora_ocupada) as hora_disponible from (";
            }
            //if(tipo_consulta.equals("2")){
                sql +="select a.ide_yhopeh,ide_ystmod,ide_ystjor,ide_ystpea,ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia,\n" +
                "(case when contador is null then 0 else contador end) as hora_ocupada,ide_ystins,hora_clase,ide_ypedpe,ide_yhohor,descripcion_yhohor,orden_yhohor\n" +
                "from (\n" +
                "select a.ide_yhopeh,a.ide_ystmod,a.ide_ystjor,a.ide_ystpea,a.ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia,\n" +
                "hora_clase_ystpea/60 as hora_clase,a.ide_yhohor,descripcion_yhohor,orden_yhohor \n" +
                "from yavirac_hora_periodo_hor a,yavirac_hora_dia b,yavirac_stror_periodo_academic c,yavirac_hora_hora d\n" +
                "where  a.ide_yhodia = b.ide_yhodia and a.ide_yhohor = d.ide_yhohor\n" +
                "and a.ide_ystpea = c.ide_ystpea\n" +
                "and ide_yhothj =" +tipo_horario+
                "and ide_ystmod =" +ide_ystmod+
                "and ide_ystjor = " +ide_ystjor+
                "and a.ide_ystpea = " +ide_ystpea+
                "and b.ide_yhodia = " +ide_yhodia+
                "and ide_ystmen = " +ide_ystmen+
                "order by orden_yhodia\n" +
                ") a\n" +
                "left join (select count(*) as contador,ide_ystins,ide_yhopeh,ide_ypedpe \n" +
                "from yavirac_hora_horario_mate group by ide_yhopeh,ide_ystins,ide_ypedpe )b on a.ide_yhopeh = b.ide_yhopeh and ide_ystins "+ide_ystins+" and ide_ypedpe "+ide_ypedpe+condicion;
            //}
             if(tipo_consulta.equals("1")){
                 sql+=" ) a group by ide_yhodia";
             }
            return sql;
        }
        public String validaGeneracionHorarioClase(String tipo_consulta,String tipo_horario,String ide_ystmod,String ide_ystjor,String ide_ystpea,String ide_yhodia,String ide_ystmen,String ide_yhogra,String ide_ystmal,String ide_ypedpe,String condicion){
            String sql="";
            if(tipo_consulta.equals("1")){
                sql+="select ide_yhodia,sum(hora_ocupada) as hora_ocupada,sum( hora_clase) as hora_clase,sum( hora_clase)-sum(hora_ocupada) as hora_disponible from (";
            }
            //if(tipo_consulta.equals("2")){
                sql +="select a.ide_yhopeh,ide_ystmod,ide_ystjor,ide_ystpea,ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia,\n" +
                    "(case when contador is null then 0 else contador end) as hora_ocupada,ide_yhogra,hora_clase,ide_ypedpe,descripcion_yhohor,orden_yhohor\n" +
                    "from (\n" +
                    "select a.ide_yhopeh,a.ide_ystmod,a.ide_ystjor,a.ide_ystpea,a.ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia,ide_yhohor,\n" +
                    "hora_clase_ystpea/60 as hora_clase,a.ide_yhohor,descripcion_yhohor,orden_yhohor\n" +
                    "from yavirac_hora_periodo_hor a,yavirac_hora_dia b,yavirac_stror_periodo_academic c,yavirac_hora_hora d\n" +
                    "where  a.ide_yhodia = b.ide_yhodia and a.ide_yhohor = d.ide_yhohor\n" +
                    "and a.ide_ystpea = c.ide_ystpea\n" +
                    "and ide_yhothj = " +tipo_horario+
                    "and ide_ystmod = " +ide_ystmod+
                    "and ide_ystjor = " +ide_ystjor+
                    "and a.ide_ystpea = " +ide_ystpea+
                    "and b.ide_yhodia = " +ide_yhodia+
                    "and ide_ystmen = " +ide_ystmen+
                    "order by orden_yhodia\n" +
                    ") a\n" +
                    "left join (select count(*) as contador,ide_yhogra,ide_yhopeh,ide_ystmal,ide_ypedpe \n" +
                    "from yavirac_hora_horario_mate group by ide_yhopeh,ide_yhogra,ide_ystmal,ide_ypedpe )b on a.ide_yhopeh = b.ide_yhopeh \n" +
                    "and ide_yhogra="+ide_yhogra+" and ide_ystmal="+ide_ystmal+" and ide_ypedpe="+ide_ypedpe+condicion;
            //}
            if(tipo_consulta.equals("1")){
                 sql+=" ) a group by ide_yhodia";
             }
            sql+=" order by orden_yhodia,orden_yhohor";
            return sql;
        }
        public String getSqlDiaHabilitado(String tipo_hora,String ide_ystmod,String ide_ystjor,String ide_ystpea,String ide_ystmen){
            String sql="";
                sql="select a.ide_ystmod,a.ide_ystjor,a.ide_ystpea,a.ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia\n" +
                    "from yavirac_hora_periodo_hor a,yavirac_hora_dia b,yavirac_stror_periodo_academic c\n" +
                    "where  a.ide_yhodia = b.ide_yhodia\n" +
                    "and a.ide_ystpea = c.ide_ystpea\n" +
                    "and ide_yhothj = " +tipo_hora+
                    "and ide_ystmod =" +ide_ystmod+
                    "and ide_ystjor =  " +ide_ystjor+
                    "and a.ide_ystpea = " +ide_ystpea+
                    "and ide_ystmen = "+ide_ystmen+" group by a.ide_ystmod,a.ide_ystjor,a.ide_ystpea,a.ide_yhodia,ide_ystmen,descripcion_yhodia,orden_yhodia\n" +
                    "order by orden_yhodia";
            return sql;
        }
}
