/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paq_matricula.ejb;

import paq_personal.ejb.*;
import paq_estructura.ejb.*;
import framework.aplicacion.TablaGenerica;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class ServicioMatriculas {

     /**
     * Insertar en la tabla Biometrico
     *
     * @param todos.- Ingresar todos  los campos requeridos en la tabla
     * @return la Tabla insertada
     */
    public String getAlumnosMallaPeriodo (String malla,String periodo,String fecha_control,String docente) {
        String sql="";
        sql=" insert into yavirac_asis_asistencia (ide_yasasi,ide_yasfec,ide_ypedpe,ide_yaldap,asistencia_yasasi,ide_ystmal)\n" +
            "select row_number()over( order by ide_yaldap ) + (select (case when max(ide_yasasi) is null then 0 else max(ide_yasasi) end) as codigo from yavirac_asis_asistencia ) as codigo ,\n" +
            fecha_control+","+docente+", a.ide_yaldap,true," +malla+
            " from yavirac_matri_matricula a,yavirac_matri_periodo_matric b,yavirac_matri_registro_credito c\n" +
            " where a.ide_ymaper = b.ide_ymaper\n" +
            " and a.ide_ymamat = c.ide_ymamat\n" +
            " and ide_ystpea =" +periodo+
            " and ide_ystmal ="+malla;
              System.out.println("cccccc "+sql);
        return sql;
    }

    
}
