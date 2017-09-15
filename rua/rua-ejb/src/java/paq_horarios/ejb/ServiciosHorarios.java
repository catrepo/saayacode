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
    
               /**
     * Retorna la descripcion hora
     *
     * @param activo.- permite el ingreso del paramtero activo para filtrar ya sea true, false, o ambos.
     * @return sql de la descripcion hora
     */
    public String getDescripcionHora(String codigo) {
        String sql="";
        sql="select  ide_yhodeh, c.ide_ystpea, d.ide_yhothj, e.ide_yhotih, a.ide_ystjor, f.ide_ystmod, hora_inicio_yhodeh, hora_final_yhodeh, activo_yhodeh, descripcion_ystjor, descripcion_ystpea, descripcion_yhothj, descripcion_yhotih, descripcion_ystmod \n" +
"from yavirac_hora_definicion_hora a, yavirac_stror_jornada b, yavirac_stror_periodo_academic c, yavirac_hora_tipo_horario_jorna d, yavirac_hora_tipo_horario e, yavirac_stror_modalidad f\n" +
"where a.ide_ystjor= b.ide_ystjor \n" +
"and a.ide_ystpea= c.ide_ystpea \n" +
"and a.ide_yhothj= d.ide_yhothj \n" +
"and a.ide_yhotih= e.ide_yhotih \n" +
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
    public String insertReplicaDefinicionHora(String ide_yhodeh,String ide_ystpea,String ide_yhothj,String ide_yhotih,String ide_ystjor,String ide_ystmod,String hora_inicio_yhodeh,String hora_final_yhodeh,String activo_yhodeh) {
        String sql="";
        sql="insert into yavirac_hora_definicion_hora (ide_yhodeh, ide_ystpea, ide_yhothj, ide_yhotih, ide_ystjor, ide_ystmod, hora_inicio_yhodeh, hora_final_yhodeh, activo_yhodeh)\n" +
            "values ("+ide_yhodeh+","+ide_ystpea+", "+ide_yhothj+", "+ide_yhotih+", "+ide_ystjor+", "+ide_ystmod+", '"+hora_inicio_yhodeh+"', '"+hora_final_yhodeh+"', "+activo_yhodeh+") ";
        return sql;
    }
    
}
