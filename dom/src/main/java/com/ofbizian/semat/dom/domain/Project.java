package com.ofbizian.semat.dom.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.repository.RepositoryService;

@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByName", language = "JDOQL",
                value = "SELECT "
                        + "FROM Project "
                        + "WHERE name.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@DomainObjectLayout(bookmarking= BookmarkPolicy.AS_ROOT)
public class Project extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String code;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String name;

    @javax.jdo.annotations.Column(allowsNull = "true")
    private String description;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Alpha opportunity;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Alpha stakeholders;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Alpha requirements;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Alpha softwareSystem;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Alpha work;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Alpha team;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Alpha wayOfWorking;

    @Property(editing = Editing.DISABLED, editingDisabledReason = "Use actions to change")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Property(editing = Editing.DISABLED, editingDisabledReason = "Use actions to change")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Property(editing = Editing.DISABLED, editingDisabledReason = "Use actions to change")
    @PropertyLayout(multiLine=5, hidden = Where.ALL_TABLES)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Programmatic
    public Alpha getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Alpha opportunity) {
        this.opportunity = opportunity;
    }

    @Programmatic
    public Alpha getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(Alpha stakeholders) {
        this.stakeholders = stakeholders;
    }

    @Programmatic
    public Alpha getRequirements() {
        return requirements;
    }

    public void setRequirements(Alpha requirements) {
        this.requirements = requirements;
    }

    @Programmatic
    public Alpha getSoftwareSystem() {
        return softwareSystem;
    }

    public void setSoftwareSystem(Alpha softwareSystem) {
        this.softwareSystem = softwareSystem;
    }

    @Programmatic
    public Alpha getWork() {
        return work;
    }

    public void setWork(Alpha work) {
        this.work = work;
    }

    @Programmatic
    public Alpha getTeam() {
        return team;
    }

    public void setTeam(Alpha team) {
        this.team = team;
    }

    @Programmatic
    public Alpha getWayOfWorking() {
        return wayOfWorking;
    }

    public void setWayOfWorking(Alpha wayOfWorking) {
        this.wayOfWorking = wayOfWorking;
    }

    public String title() {
        return "[" + code + "] " + name;
    }

    @CollectionLayout(named="Opportunity")
    public Set<AlphaState> getOpportunityAlphaStates() {
        return opportunity.getAlphaStates();
    }

    @CollectionLayout(named="Stakeholders")
    public Set<AlphaState> getStakeholdersAlphaStates() {
        return stakeholders.getAlphaStates();
    }

    @CollectionLayout(named="Requirements")
    public Set<AlphaState> getRequirementsAlphaStates() {
        return requirements.getAlphaStates();
    }

    @CollectionLayout(named="Software System")
    public Set<AlphaState> getSoftwareSystemAlphaStates() {
        return softwareSystem.getAlphaStates();
    }

    @CollectionLayout(named="Work")
    public Set<AlphaState> getWorkAlphaStates() {
        return work.getAlphaStates();
    }

    @CollectionLayout(named="Team")
    public Set<AlphaState> getTeamAlphaStates() {
        return team.getAlphaStates();
    }

    @CollectionLayout(named="Way of Working")
    public Set<AlphaState> getWayOfWorkingAlphaStates() {
        return wayOfWorking.getAlphaStates();
    }

    public String getOpportunityStatus() {
        return getAlphaStateSummary(opportunity.getAlphaStates());
    }

    public String getStakeholdersStatus() {
        return getAlphaStateSummary(stakeholders.getAlphaStates());
    }

    public String getRequirementsStatus() {
        return getAlphaStateSummary(requirements.getAlphaStates());
    }

    public String getSoftwareSystemStatus() {
        return getAlphaStateSummary(softwareSystem.getAlphaStates());
    }

    public String getWorkStatus() {
        return getAlphaStateSummary(work.getAlphaStates());
    }

    public String getTeamStatus() {
        return getAlphaStateSummary(team.getAlphaStates());
    }

    public String getWayOfWorkingStatus() {
        return getAlphaStateSummary(wayOfWorking.getAlphaStates());
    }

    private String getAlphaStateSummary(SortedSet<AlphaState> alphaStates) {
        List<AlphaState> list = new ArrayList(alphaStates);
        Collections.sort(list, Collections.reverseOrder());
        Set<AlphaState> resultSet = new LinkedHashSet(list);
        String lastState = "none";

        for (AlphaState alphaState : resultSet) {
            if (alphaState.isAchieved()) {
                lastState = alphaState.getState().getName();
                break;
            }
        }
        return lastState;
    }

    @MemberOrder(sequence = "3", name = "description")
    @Action
    public Project update(
            @ParameterLayout(named="Code")
            final String code,
            @ParameterLayout(named="Name")
            final String name,
            @ParameterLayout(named="Description", multiLine=5)
            @Parameter(optionality = Optionality.OPTIONAL)
            final String description) {
        setCode(code);
        setName(name);
        setDescription(description);
        return this;
    }

    public String default0Update() {
        return getCode();
    }

    public String default1Update() {
        return getName();
    }

    public String default2Update() {
        return getDescription();
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    public List<Project> remove() {
        projectRepository.remove(this);
        return projectRepository.listAll();
    }

    @javax.inject.Inject
    RepositoryService repositoryService;

    @javax.inject.Inject
    ProjectRepository projectRepository;

}