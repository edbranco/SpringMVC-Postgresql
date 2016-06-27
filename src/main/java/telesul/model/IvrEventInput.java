/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.model;

/**
 *
 * @author lmohan
 */
public class IvrEventInput {

    private String telephone;
    private String digitsTyped;
    private String interactionPoints;
    private boolean abandoned;
    private boolean transfered;
    private int terminatedReason;
    private int invalidOptionCount;
    private String skillset;
    private String flowName;
    private int transferedDirectToDefaultSkill;
    private String answers;
    private boolean enteredInPesquisa;
    private String agent;

    public IvrEventInput(String telephone, String digitsTyped, String interactionPoints, String skillset, String flowName) {
        this.telephone = telephone;
        this.digitsTyped = digitsTyped;
        this.interactionPoints = interactionPoints;
        this.skillset = skillset;
        this.flowName = flowName;
    }

    public static IvrEventInput ivrEventInputTransfered(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName) {
        IvrEventInput ivrEventInput = new IvrEventInput(telephone, digitsTyped, interactionPoints, skillset, flowName);
        ivrEventInput.setInvalidOptionCount(invalidOptionCount);
        ivrEventInput.setTransfered(true);
        ivrEventInput.setTransferedDirectToDefaultSkill(transferedDirectToDefaultSkill);
        return ivrEventInput;
    }

    public static IvrEventInput ivrEventInputAbandoned(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, String skillset, String flowName, int enteredInPesquisa) {
        IvrEventInput ivrEventInput = new IvrEventInput(telephone, digitsTyped, interactionPoints, skillset, flowName);
        ivrEventInput.setInvalidOptionCount(invalidOptionCount);
        ivrEventInput.setAbandoned(true);
        if (enteredInPesquisa == 1) {
            ivrEventInput.setEnteredInPesquisa(true);
        }
        return ivrEventInput;
    }

    public static IvrEventInput ivrEventInputTerminated(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, String skillset, String flowName, int terminatedReason) {
        IvrEventInput ivrEventInput = new IvrEventInput(telephone, flowName);
        ivrEventInput.setTerminatedReason(terminatedReason);
        return ivrEventInput;
    }

    public static IvrEventInput ivrEventInputPesquisa(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName, String answers,String agent) {
        IvrEventInput ivrEventInput = ivrEventInputTransfered(telephone, digitsTyped, interactionPoints, invalidOptionCount, transferedDirectToDefaultSkill, skillset, flowName);
        ivrEventInput.setAnswers(answers);
        ivrEventInput.setEnteredInPesquisa(true);
        ivrEventInput.setAgent(agent);
        return ivrEventInput;
    }

    private IvrEventInput(String telephone, String flowName) {
        this.telephone = telephone;
        this.flowName = flowName;
    }

    public boolean isEnteredInPesquisa() {
        return enteredInPesquisa;
    }

    public void setEnteredInPesquisa(boolean enteredInPesquisa) {
        this.enteredInPesquisa = enteredInPesquisa;
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

    public boolean isAbandoned() {
        return abandoned;
    }

    public void setAbandoned(boolean abandoned) {
        this.abandoned = abandoned;
    }

    public boolean isTransfered() {
        return transfered;
    }

    public void setTransfered(boolean transfered) {
        this.transfered = transfered;
    }

    public int getTerminatedReason() {
        return terminatedReason;
    }

    public void setTerminatedReason(int terminatedReason) {
        this.terminatedReason = terminatedReason;
    }

    public int getInvalidOptionCount() {
        return invalidOptionCount;
    }

    public void setInvalidOptionCount(int invalidOptionCount) {
        this.invalidOptionCount = invalidOptionCount;
    }

    public String getSkillset() {
        return skillset;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public int getTransferedDirectToDefaultSkill() {
        return transferedDirectToDefaultSkill;
    }

    public void setTransferedDirectToDefaultSkill(int transferedDirectToDefaultSkill) {
        this.transferedDirectToDefaultSkill = transferedDirectToDefaultSkill;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "IvrEventInput{" + "telephone=" + telephone + ", digitsTyped=" + digitsTyped + ", interactionPoints=" + interactionPoints + ", abandoned=" + abandoned + ", transfered=" + transfered + ", terminatedReason=" + terminatedReason + ", invalidOptionCount=" + invalidOptionCount + ", skillset=" + skillset + ", flowName=" + flowName + ", transferedDirectToDefaultSkill=" + transferedDirectToDefaultSkill + ", answers=" + answers + ", enteredInPesquisa=" + enteredInPesquisa + ", agent=" + agent + '}';
    }


}
