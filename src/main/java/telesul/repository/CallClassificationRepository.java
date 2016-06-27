/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.repository;

import org.springframework.stereotype.Repository;
import telesul.model.CallClassification;
import telesul.repository.BaseRepository;

/**
 *
 * @author lmohan
 */
@Repository
public interface CallClassificationRepository extends BaseRepository<CallClassification, Long> {
}
