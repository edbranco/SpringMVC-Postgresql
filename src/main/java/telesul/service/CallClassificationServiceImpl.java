/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telesul.model.CallClassification;
import telesul.repository.CallClassificationRepository;

/**
 *
 * @author ebranco
 */
@Service
public class CallClassificationServiceImpl implements CallClassificationService {

    private final Logger logger = LoggerFactory.getLogger(CallClassificationServiceImpl.class);

    @Autowired
    CallClassificationRepository callClassificationRepository;

    @Override
    public void saveCallClassification(CallClassification callClassification) {
        try {
            callClassificationRepository.save(callClassification);

        } catch (Exception e) {
            logger.error("exception in building Occurence", e);
        }
    }

}
