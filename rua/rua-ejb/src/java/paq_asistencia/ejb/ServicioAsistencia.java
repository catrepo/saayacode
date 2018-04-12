/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_asistencia.ejb;

import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
/**
 *
 * @author Janeth Pullotasig and  Nicolas Cajilema
 */
@Stateless
public class ServicioAsistencia {

     /**
     * Insertar en la tabla Biometrico 
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String insertBiometrico(String ide,String indice,String hora,String fecha,String puerta,String num,String nombre,String departamento,String fecha_registro,String codigo_reloj,String usuario_ingre,String fecha_ingre,String hora_ingre) {
        String sql="";
        sql="insert into yavirac_asis_biometrico (ide_yasbio,indice_yasbio,hora_yasbio,fecha_yasbio,puerta_yasbio,num_yasbio,nombre_yasbio,departamento_yasbio,fecha_registro_yasbio,codigo_reloj_yasbio,usuario_ingre,fecha_ingre,hora_ingre)"+
                " values ("+ide+",'"+indice+"','"+hora+"','"+fecha+"','"+puerta+"','"+num+"','"+nombre+"','"+departamento+"','"+fecha_registro+"','"+codigo_reloj+"','"+usuario_ingre+"','"+fecha_ingre+"','"+hora_ingre+"')";
        return sql;
    }
       /**
     * Insertar en la tabla Biometrico
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getSemana() {
        String sql="";
        sql=" SELECT ide_yassem, detalle_yassem FROM yavirac_asis_semana order by detalle_yassem;";
              
        return sql;
    }   
       
    /**
     * SQL tipo motivo para asistencia
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getTipoMotivo() {
        String sql="";
        sql=" SELECT ide_yastmo, descripcion_yastmo FROM yavirac_asis_tipo_motivo order by descripcion_yastmo;";
              
        return sql;
    }     
  /**
     * SQL motivo para motiva de ausencia
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getMotivoAusencia() {
        String sql="";
        sql=" SELECT ide_yasmpe, descripcion_yasmpe FROM yavirac_asis_motivo_permiso order by descripcion_yasmpe;";
              
        return sql;
    } 
  /**
     * SQL motivo para cursos con materias para toma de  por periodo academico
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getMateriaNivelDocente(String periodo,String docente) {
        String sql="";
        sql="select a.ide_ypemad,detalle_ystmat,descripcion_ystnie " +
            " from yavirac_perso_malla_docente a, (" +
            " select ide_ystmal, detalle_ystmat, descripcion_ystnie " +
            " from yavirac_stror_malla a,yavirac_stror_nivel_educacion b,yavirac_stror_materia c " +
            " where a.ide_ystnie = b.ide_ystnie and a.ide_ystmat = c.ide_ystmat " +
            " ) b where a.ide_ystmal = b.ide_ystmal and ide_ystpea ="+periodo+" and ide_ypedpe =" +docente+
            " order by descripcion_ystnie,detalle_ystmat";
              
        return sql;
    }      
}