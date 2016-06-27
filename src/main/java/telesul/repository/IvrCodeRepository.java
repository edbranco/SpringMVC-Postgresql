/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.repository;

import org.springframework.stereotype.Repository;
import telesul.model.IvrCode;

/**
 *
 * @author lmohan
 */
@Repository
public interface IvrCodeRepository extends BaseRepository<IvrCode, Long> {

    public IvrCode findByCode(int code);
}
