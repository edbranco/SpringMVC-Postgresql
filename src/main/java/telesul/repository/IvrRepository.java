/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.repository;

import org.springframework.stereotype.Repository;
import telesul.model.IvrEvent;

/**
 *
 * @author lmohan
 */
@Repository
public interface IvrRepository extends BaseRepository<IvrEvent, Long> {
}
