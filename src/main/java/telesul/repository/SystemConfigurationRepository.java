/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.repository;

import telesul.model.SystemConfiguration;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lmohan
 */
@Repository
public interface SystemConfigurationRepository extends BaseRepository<SystemConfiguration, Long> {

}
