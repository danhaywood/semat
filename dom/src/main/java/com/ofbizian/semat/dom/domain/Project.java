/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ofbizian.semat.dom.domain;

import java.util.Set;
import java.util.TreeSet;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Where;

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

    @PropertyLayout(describedAs = "Unique name for this property")
    @javax.jdo.annotations.Column(allowsNull = "false")
    private String name;

    @javax.jdo.annotations.Column(allowsNull = "true")
    @PropertyLayout(multiLine=5, hidden = Where.ALL_TABLES)
    private String description;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Opportunity opportunity;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Stakeholders stakeholders;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Requirements requirements;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private SoftwareSystem softwareSystem;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Work work;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Team team;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private WayOfWorking wayOfWorking;

//    @javax.jdo.annotations.Persistent(table = "ProjectAlphas")
//    @javax.jdo.annotations.Join()
//    private Set<Alpha> alphas = new TreeSet<>();
//
//    public Set<Alpha> getAlphas() {
//        return alphas;
//    }
//
//    public void setAlphas(Set<Alpha> alphas) {
//        this.alphas = alphas;
//    }
//
//    @Action
//    public Project addAlpha(Alpha alpha) {
//        getAlphas().add(alpha);
//        return this;
//    }
//
//    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
//    public Project removeAlpha(Alpha alpha) {
//        getAlphas().remove(alpha);
//        return this;
//    }
//
//    @CollectionLayout(defaultView = "table", named = "Alpha States")
//    public Set<ProjectStateView> getAlphaStates() {
//        Set<ProjectStateView> projectStateViews = new LinkedHashSet<>();
//        final Set<Alpha> alphas = getAlphas();
//        for (Alpha alpha : alphas) {
//            ProjectStateView view = new ProjectStateView();
//            view.setAlpha(alpha);
//            view.setConcern(alpha.getConcern().getName());
//
//            final SortedSet<AlphaState> alphaStates = alpha.getAlphaStates();
//            List<AlphaState> list = new ArrayList(alphaStates);
//            Collections.sort(list, Collections.reverseOrder());
//            Set<AlphaState> resultSet = new LinkedHashSet(list);
//            State lastState = null;
//
//            for (AlphaState alphaState : resultSet) {
//                lastState = alphaState.getState();
//                if (alphaState.isAchieved()) {
//                    view.setAchieved(alphaState.isAchieved());
//                    break;
//                }
//            }
//            view.setState(lastState);
//            projectStateViews.add(view);
//        }
//        return projectStateViews;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    public Stakeholders getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(Stakeholders stakeholders) {
        this.stakeholders = stakeholders;
    }

    public Requirements getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirements requirements) {
        this.requirements = requirements;
    }

    public SoftwareSystem getSoftwareSystem() {
        return softwareSystem;
    }

    public void setSoftwareSystem(SoftwareSystem softwareSystem) {
        this.softwareSystem = softwareSystem;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public WayOfWorking getWayOfWorking() {
        return wayOfWorking;
    }

    public void setWayOfWorking(WayOfWorking wayOfWorking) {
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

}