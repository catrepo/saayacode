/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_horarios.ejb;

import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
/**
 *
 * @author Andres
 */
@Stateless
public class ServiciosHorarios {
    
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
         public String getInstalacion() {
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
}
