/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.service;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telesul.model.ExceptionDia;
import telesul.model.IvrCode;
import telesul.model.IvrEvent;
import telesul.model.IvrEventInput;
import telesul.model.IvrState;
import telesul.repository.ExceptionDiaRepository;
import telesul.repository.FeriadoRepository;
import telesul.repository.IvrCodeRepository;
import telesul.repository.IvrRepository;
import telesul.repository.IvrStateRepository;

/**
 *
 * @author lmohan
 */
@Service
public class ZapServiceImpl implements ZapService {

    private final Logger logger = LoggerFactory.getLogger(ZapServiceImpl.class);
    @Autowired
    FeriadoRepository feriadoRepository;
    private static final int invalidOption = 1000;
    @Autowired
    ExceptionDiaRepository exceptionDiaRepository;

    @Autowired
    IvrRepository ivrRepository;

    @Autowired
    IvrCodeRepository ivrCodeRepository;
    @Autowired
    IvrStateRepository ivrStateRepository;

    @Override
    public boolean isFeriado() {
        return (feriadoRepository.findByFeriado(new Date())) != null;
    }

    @Override
    public boolean isExceptionDiaWithinHours() {
        ExceptionDia exceptionDia = exceptionDiaRepository.findByDia(new Date());
        if (exceptionDia == null) {
            return false;
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        LocalTime now = zonedDateTime.toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String startTimeStr;
        String endTimeStr;
        if (StringUtils.isBlank(exceptionDia.getStartTime()) || StringUtils.isBlank(exceptionDia.getEnd_time())) {
            return true;
        }
        startTimeStr = exceptionDia.getStartTime();
        endTimeStr = exceptionDia.getEnd_time();
        LocalTime startTime = LocalTime.parse(startTimeStr, formatter);
        LocalTime endTime = LocalTime.parse(endTimeStr, formatter);

        return (now.compareTo(startTime) > 0 && now.compareTo(endTime) < 0);
    }

    @Override
    public void saveStatisticsTransfered(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName) {
        logger.info("saveStatisticsTransfered ..");
        buildAndSaveIvrEvent(IvrEventInput.ivrEventInputTransfered(telephone, digitsTyped, interactionPoints, invalidOptionCount, transferedDirectToDefaultSkill, skillset, flowName));
    }

    @Override
    public void saveStatisticsAbandoned(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, String skillset, String flowName, int enteredInPesquisa) {
        logger.info("saveStatisticsAbandoned ..");
        buildAndSaveIvrEvent(IvrEventInput.ivrEventInputAbandoned(telephone, digitsTyped, interactionPoints, invalidOptionCount, skillset, flowName, enteredInPesquisa));
    }

    @Override
    public void saveStatisticsTerminated(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName, int terminatedReason) {
        logger.info("saveStatisticsTerminated ..");
        buildAndSaveIvrEvent(IvrEventInput.ivrEventInputTerminated(telephone, digitsTyped, interactionPoints, invalidOptionCount, skillset, flowName, terminatedReason));
    }

    @Override
    public void saveStatisticsPesquisa(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName, String answers,String agent) {
        logger.info("saveStatisticsPesquisa ..");
        buildAndSaveIvrEvent(IvrEventInput.ivrEventInputPesquisa(telephone, digitsTyped, interactionPoints, invalidOptionCount, transferedDirectToDefaultSkill, skillset, flowName, answers,agent));
    }

    @Override
    public int getStateCode(String telefone_origem) {
        if (telefone_origem.length() == 10 || telefone_origem.length() == 11) {
            return Integer.parseInt(telefone_origem.substring(0, 2));
        } else {
            return -1;
        }
    }

    @Override
    public IvrState getStateByPrefixo(int prefixo) {
        return ivrStateRepository.findByPrefixo(prefixo);
    }

    public String removeQuotes(String input) {
        return StringUtils.replace(input, "'", "");
    }

    public void buildAndSaveIvrEvent(IvrEventInput ivrEventInput) {
        try {
            logger.info("\n ivrEventInput received from IVR ::" + ivrEventInput);
            IvrEvent ivrEvent = new IvrEvent();

            ivrEvent.setReceivedDate(new Date());

            ivrEvent.setTelephone(ivrEventInput.getTelephone());

            ivrEvent.setDigitsTyped(removeQuotes(ivrEventInput.getDigitsTyped()));

            ivrEvent.setAgent(ivrEventInput.getAgent());
            
            String removeQuote = removeQuotes(ivrEventInput.getInteractionPoints());
            ivrEvent.setInteractionPoints(removeQuote);

            ivrEvent.setTransfered(ivrEventInput.isTransfered());

            ivrEvent.setAbandoned(ivrEventInput.isAbandoned());

            ivrEvent.setEnteredInPesquisa(ivrEventInput.isEnteredInPesquisa());

            if (ivrEventInput.getInvalidOptionCount() == 2) {
                ivrEvent.setTransferedBecauseOfInvalidOptions(true);
            }

            List ret = convertCodeToName(removeQuote);
            ivrEvent.setInteractionNames((String) ret.get(0));
            ivrEvent.setTotalInvalidOptions((int) ret.get(1));

            switch (ivrEventInput.getTerminatedReason()) {
                case 1:
                    ivrEvent.setTerminatedBecauseOfOutOfHour(true);
                    break;
                case 2:
                    ivrEvent.setTerminatedBecauseOfWeekend(true);
                    break;
                case 3:
                    ivrEvent.setTerminatedBecauseOfFixedHolidays(true);
                    break;
                case 4:
                    ivrEvent.setTerminatedBecauseOfExceptionHolidays(true);
                    break;
                case 5:
                    ivrEvent.setTerminatedBecauseOfTechnicalProblems(true);
                    break;
                case 6:
                    ivrEvent.setTerminatedBecauseOfBusyQueue(true);
                    break;
            }

            ivrEvent.setFlowName(ivrEventInput.getFlowName());
            ivrEvent.setSkillset(ivrEventInput.getSkillset());

            if (StringUtils.contains(ivrEventInput.getAnswers(), "-")) {
                if (ivrEventInput.getAnswers().contains("'")) {
                    ivrEventInput.setAnswers(ivrEventInput.getAnswers().replaceAll("'", ""));
                }
                String[] answers = ivrEventInput.getAnswers().split("-");
                for (int i = 0; i < answers.length; i++) {
                    switch (i) {
                        case 0:
                            ivrEvent.setAnswer1(Integer.parseInt(answers[i].trim()));
                            break;
                        case 1:
                            ivrEvent.setAnswer2(Integer.parseInt(answers[i].trim()));
                            break;
                        case 2:
                            ivrEvent.setAnswer3(Integer.parseInt(answers[i].trim()));
                            break;
                        case 3:
                            ivrEvent.setAnswer4(Integer.parseInt(answers[i].trim()));
                            break;
                    }
                }
            }

            //set state
            int prefixo = getStateCode(ivrEventInput.getTelephone());
            logger.info("prefixo::{}", prefixo);
            if (prefixo > 0) {
                IvrState ivrState = getStateByPrefixo(prefixo);
                if (ivrState != null) {
                    ivrEvent.setEstado(ivrState.getEstado());
                } else {
                    ivrEvent.setEstado(null);
                }
            } else {
                ivrEvent.setEstado(null);
            }
            ivrEvent = ivrRepository.save(ivrEvent);
            logger.info("\n saved ivrEvent ::" + ivrEvent);
        } catch (Exception e) {
            logger.error("exception in building ivr event", e);
        }
    }

    public List convertCodeToName(String interactionPoints) {
        List ret = new ArrayList<>();
        StringBuilder interactionNames = new StringBuilder();
        int totalInvalidOptionsInTheFlow = 0;
        if (StringUtils.contains(interactionPoints, '-')) {
            String[] codes = StringUtils.split(interactionPoints, '-');

            for (int i = 0; i < codes.length; i++) {
                String code = codes[i];
                if (StringUtils.isNotBlank(code)) {
                    try {
                        int c = Integer.parseInt(code);
                        if (c != invalidOption) {
                            IvrCode ivrCode = ivrCodeRepository.findByCode(c);
                            String codeName = ivrCode.getName();
                            if (StringUtils.isNotBlank(codeName)) {
                                interactionNames.append(codeName);
                            } else {
                                interactionNames.append("CodeNotFound");
                            }
                        } else {
                            totalInvalidOptionsInTheFlow++;
                            interactionNames.append("InValidOption");
                        }
                    } catch (NumberFormatException e) {
                        interactionNames.append("CodeIsNAN");
                    }
                    if (i < codes.length - 1) {
                        interactionNames.append("-");
                    }
                }
            }
        }
        ret.add(interactionNames.toString());
        ret.add(totalInvalidOptionsInTheFlow);
        return ret;
    }

}
