/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author lmohan
 */
@Entity
public class IvrEvent implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date receivedDate;//current timestamp
    private String telephone;//customer no
    private String digitsTyped;//dtmf sequence
    private String interactionPoints;//100-101-102 ..
    private String interactionNames;//derived menu-submenu ..
    private boolean transfered;//!terminatedBecauseOfOutOfHour
    private boolean abandoned;
    private boolean transferedBecauseOfInvalidOptions;//derived
    private int totalInvalidOptions;//derived
    private boolean terminatedBecauseOfOutOfHour;//terminatedReason=1
    private boolean terminatedBecauseOfWeekend;//terminatedReason=2
    private boolean terminatedBecauseOfFixedHolidays;//terminatedReason=3
    private boolean terminatedBecauseOfExceptionHolidays;//terminatedReason=4
    private boolean terminatedBecauseOfTechnicalProblems;//terminatedReason=5
    private boolean terminatedBecauseOfBusyQueue;//terminatedReason=6
    private String flowName;
    private String skillset;
    private boolean transferedDirectToDefaultSkill;//transferedDirectToDefaultSkill=1 Direct transferedDirectToDefaultSkill=2 Indirect
    private String agent;
    private int answer1;
    private int answer2;
    private int answer3;
    private int answer4;
    private boolean enteredInPesquisa;
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDigitsTyped() {
        return digitsTyped;
    }

    public void setDigitsTyped(String digitsTyped) {
        this.digitsTyped = digitsTyped;
    }

    public String getInteractionPoints() {
        return interactionPoints;
    }

    public void setInteractionPoints(String interactionPoints) {
        this.interactionPoints = interactionPoints;
    }

    public String getInteractionNames() {
        return interactionNames;
    }

    public void setInteractionNames(String interactionNames) {
        this.interactionNames = interactionNames;
    }

    public boolean isTransfered() {
        return transfered;
    }

    public void setTransfered(boolean transfered) {
        this.transfered = transfered;
    }

    public boolean isAbandoned() {
        return abandoned;
    }

    public void setAbandoned(boolean abandoned) {
        this.abandoned = abandoned;
    }

    public boolean isTransferedBecauseOfInvalidOptions() {
        return transferedBecauseOfInvalidOptions;
    }

    public void setTransferedBecauseOfInvalidOptions(boolean transferedBecauseOfInvalidOptions) {
        this.transferedBecauseOfInvalidOptions = transferedBecauseOfInvalidOptions;
    }

    public int getTotalInvalidOptions() {
        return totalInvalidOptions;
    }

    public void setTotalInvalidOptions(int totalInvalidOptions) {
        this.totalInvalidOptions = totalInvalidOptions;
    }

    public boolean isTerminatedBecauseOfOutOfHour() {
        return terminatedBecauseOfOutOfHour;
    }

    public void setTerminatedBecauseOfOutOfHour(boolean terminatedBecauseOfOutOfHour) {
        this.terminatedBecauseOfOutOfHour = terminatedBecauseOfOutOfHour;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getSkillset() {
        return skillset;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }

    public boolean isTransferedDirectToDefaultSkill() {
        return transferedDirectToDefaultSkill;
    }

    public void setTransferedDirectToDefaultSkill(boolean transferedDirectToDefaultSkill) {
        this.transferedDirectToDefaultSkill = transferedDirectToDefaultSkill;
    }

    public boolean isTerminatedBecauseOfWeekend() {
        return terminatedBecauseOfWeekend;
    }

    public void setTerminatedBecauseOfWeekend(boolean terminatedBecauseOfWeekend) {
        this.terminatedBecauseOfWeekend = terminatedBecauseOfWeekend;
    }

    public boolean isTerminatedBecauseOfFixedHolidays() {
        return terminatedBecauseOfFixedHolidays;
    }

    public void setTerminatedBecauseOfFixedHolidays(boolean terminatedBecauseOfFixedHolidays) {
        this.terminatedBecauseOfFixedHolidays = terminatedBecauseOfFixedHolidays;
    }

    public boolean isTerminatedBecauseOfExceptionHolidays() {
        return terminatedBecauseOfExceptionHolidays;
    }

    public void setTerminatedBecauseOfExceptionHolidays(boolean terminatedBecauseOfExceptionHolidays) {
        this.terminatedBecauseOfExceptionHolidays = terminatedBecauseOfExceptionHolidays;
    }

    public boolean isTerminatedBecauseOfTechnicalProblems() {
        return terminatedBecauseOfTechnicalProblems;
    }

    public void setTerminatedBecauseOfTechnicalProblems(boolean terminatedBecauseOfTechnicalProblems) {
        this.terminatedBecauseOfTechnicalProblems = terminatedBecauseOfTechnicalProblems;
    }

    public boolean isTerminatedBecauseOfBusyQueue() {
        return terminatedBecauseOfBusyQueue;
    }

    public void setTerminatedBecauseOfBusyQueue(boolean terminatedBecauseOfBusyQueue) {
        this.terminatedBecauseOfBusyQueue = terminatedBecauseOfBusyQueue;
    }

    public int getAnswer1() {
        return answer1;
    }

    public void setAnswer1(int answer1) {
        this.answer1 = answer1;
    }

    public int getAnswer2() {
        return answer2;
    }

    public void setAnswer2(int answer2) {
        this.answer2 = answer2;
    }

    public int getAnswer3() {
        return answer3;
    }

    public void setAnswer3(int answer3) {
        this.answer3 = answer3;
    }

    public int getAnswer4() {
        return answer4;
    }

    public void setAnswer4(int answer4) {
        this.answer4 = answer4;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public boolean isEnteredInPesquisa() {
        return enteredInPesquisa;
    }

    public void setEnteredInPesquisa(boolean enteredInPesquisa) {
        this.enteredInPesquisa = enteredInPesquisa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "IvrEvent{" + "id=" + id + ", receivedDate=" + receivedDate + ", telephone=" + telephone + ", digitsTyped=" + digitsTyped + ", interactionPoints=" + interactionPoints + ", interactionNames=" + interactionNames + ", transfered=" + transfered + ", abandoned=" + abandoned + ", transferedBecauseOfInvalidOptions=" + transferedBecauseOfInvalidOptions + ", totalInvalidOptions=" + totalInvalidOptions + ", terminatedBecauseOfOutOfHour=" + terminatedBecauseOfOutOfHour + ", terminatedBecauseOfWeekend=" + terminatedBecauseOfWeekend + ", terminatedBecauseOfFixedHolidays=" + terminatedBecauseOfFixedHolidays + ", terminatedBecauseOfExceptionHolidays=" + terminatedBecauseOfExceptionHolidays + ", terminatedBecauseOfTechnicalProblems=" + terminatedBecauseOfTechnicalProblems + ", terminatedBecauseOfBusyQueue=" + terminatedBecauseOfBusyQueue + ", flowName=" + flowName + ", skillset=" + skillset + ", transferedDirectToDefaultSkill=" + transferedDirectToDefaultSkill + ", agent=" + agent + ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3=" + answer3 + ", answer4=" + answer4 + ", enteredInPesquisa=" + enteredInPesquisa + ", estado=" + estado + '}';
    }

}
