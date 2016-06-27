/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.repository;

import java.util.Date;
import org.springframework.stereotype.Repository;
import telesul.model.ExceptionDia;

/**
 *
 * @author lmohan
 */
@Repository
public interface ExceptionDiaRepository extends BaseRepository<ExceptionDia, Long> {

    public ExceptionDia findByDia(Date today);
}
