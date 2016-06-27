/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.repository;

import java.util.Date;
import org.springframework.stereotype.Repository;
import telesul.model.Feriado;

/**
 *
 * @author lmohan
 */
@Repository
public interface FeriadoRepository extends BaseRepository<Feriado, Long> {

    public Feriado findByFeriado(Date today);
}
