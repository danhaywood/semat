/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package com.ofbizian.semat.fixture.scenarios;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.ofbizian.semat.dom.domain.Alpha;
import com.ofbizian.semat.dom.domain.Concern;
import com.ofbizian.semat.dom.domain.Project;
import com.ofbizian.semat.dom.domain.ProjectRepository;
import com.ofbizian.semat.dom.domain.State;
import com.ofbizian.semat.fixture.dom.AlphaCreate;
import com.ofbizian.semat.fixture.dom.ConcernCreate;
import com.ofbizian.semat.fixture.dom.ProjectCreate;
import com.ofbizian.semat.fixture.dom.ProjectTearDown;
import com.ofbizian.semat.fixture.dom.StateCreate;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class RecreateProjects extends AbstractFixtureScript {

    public List<String> PROJECTS = Collections.unmodifiableList(Arrays.asList("ESB", "SOE"));

    public RecreateProjects() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    private Set<Project> projects = Sets.newLinkedHashSet();
    private Set<Concern> concerns = Sets.newLinkedHashSet();
    private Set<Alpha> alphas = Sets.newLinkedHashSet();
    private Set<State> states = Sets.newLinkedHashSet();

    public Set<Project> getProjects() {
        return projects;
    }

    public Set<Concern> getConcerns() {
        return concerns;
    }

    public Set<Alpha> getAlphas() {
        return alphas;
    }

    @Override
    protected void doExecute(ExecutionContext ec) {
        ec.executeChild(this, new ProjectTearDown());

        Concern customerConcern = getConcern(ec, "Customer");
        Alpha opportunity = getAlpha(ec, customerConcern, "Opportunity");
        Alpha stakeholders = getAlpha(ec, customerConcern, "Stakeholders");
        createOpportunityStates(ec, opportunity);
        createStakeholdersStates(ec, stakeholders);
        concerns.add(customerConcern);
        alphas.add(opportunity);
        alphas.add(stakeholders);

        Concern solutionConcern = getConcern(ec, "Solution");
        Alpha requirements = getAlpha(ec, solutionConcern, "Requirements");
        Alpha softwareSystem = getAlpha(ec, solutionConcern, "Software System");
        createRequirementsStates(ec, requirements);
        createSoftwareSystemStates(ec, softwareSystem);

        concerns.add(solutionConcern);
        alphas.add(requirements);
        alphas.add(softwareSystem);

        Concern endeavorConcern = getConcern(ec, "Endeavor");
        Alpha work = getAlpha(ec, endeavorConcern, "Work");
        Alpha team = getAlpha(ec, endeavorConcern, "Team");
        Alpha wayOfWorking = getAlpha(ec, endeavorConcern, "Way-of-Working");

        createWorkStates(ec, work);
        createTeamStates(ec, team);
        createWayOfWorkingStates(ec, wayOfWorking);

        concerns.add(endeavorConcern);
        alphas.add(work);
        alphas.add(team);
        alphas.add(wayOfWorking);


        for (int i = 0; i < PROJECTS.size(); i++) {
            ProjectCreate projectCreate = new ProjectCreate();
            projectCreate.setName(PROJECTS.get(i));
            projectCreate.setCode("" + i);
            projectCreate.setAlphas(alphas);

            ec.executeChild(this, projectCreate.getName(), projectCreate);
            Project project = projectCreate.getProject();
            projects.add(project);
        }
    }

    private void createWayOfWorkingStates(ExecutionContext ec, Alpha wayOfWorking) {
        getStateCreate(ec, wayOfWorking, "Principles Established", "The principles, and constraints, that shape the way-of-working are established.");
        getStateCreate(ec, wayOfWorking, "Foundation Established", "The key practices, and tools, that form the foundation of the way of working are selected and ready for use.");
        getStateCreate(ec, wayOfWorking, "In Use", "Some members of the team are using, and adapting, the way-of- working.");
        getStateCreate(ec, wayOfWorking, "In Place", "All team members are using the way of working to accomplish their work.");
        getStateCreate(ec, wayOfWorking, "Working well", "The team's way of working is working well for the team.");
        getStateCreate(ec, wayOfWorking, "Retired", "The way of working is no longer in use by the team.");
    }

    private void createTeamStates(ExecutionContext ec, Alpha team) {
        getStateCreate(ec, team, "Seeded", "The team’s mission is clear and the know-how needed to grow the team is in place.");
        getStateCreate(ec, team, "Formed", "The team has been populated with enough committed people to start the mission.");
        getStateCreate(ec, team, "Collaborating", "The team members are working together as one unit.");
        getStateCreate(ec, team, "Performing", "The team is working effectively and efficiently.");
        getStateCreate(ec, team, "Adjourned", "The team is no longer accountable for carrying out its mission.");
    }

    private void createWorkStates(ExecutionContext ec, Alpha work) {
        getStateCreate(ec, work, "Initiated", "The work has been requested.");
        getStateCreate(ec, work, "Prepared", "All pre-conditions for starting the work have been met.");
        getStateCreate(ec, work, "Started", "The work is proceeding.");
        getStateCreate(ec, work, "Under Control", "The work is going well, risks are under control, and productivity levels are sufficient to achieve a satisfactory result.");
        getStateCreate(ec, work, "Concluded", "The work to produce the results has been concluded.");
        getStateCreate(ec, work, "Closed", "All remaining housekeeping tasks have been completed and the work has been officially closed.");
    }

    private void createOpportunityStates(ExecutionContext ec, Alpha opportunity) {
        getStateCreate(ec, opportunity, "Identified", "A commercial, social, or business opportunity has been identified that could be addressed by a software-based solution.");
        getStateCreate(ec, opportunity, "Solution Needed", "The need for a software-based solution has been confirmed.");
        getStateCreate(ec, opportunity, "Value Established", "The value of a successful solution has been established.");
        getStateCreate(ec, opportunity, "Viable", "It is agreed that a solution can be produced quickly and cheaply enough to successfully address the opportunity.");
        getStateCreate(ec, opportunity, "Addressed", "A solution has been produced that demonstrably addresses the opportunity.");
        getStateCreate(ec, opportunity, "Benefit Accrued", "The operational use or sale of the solution is creating tangible benefits.");
    }

    private void createStakeholdersStates(ExecutionContext ec, Alpha stakeholders) {
        getStateCreate(ec, stakeholders, "Recognized", "Stakeholders have been identified.");
        getStateCreate(ec, stakeholders, "Represented", "The mechanisms for involving the stakeholders are agreed and the stakeholder representatives have been appointed.");
        getStateCreate(ec, stakeholders, "Involved", "The stakeholder representatives are actively involved in the work and fulfilling their responsibilities.");
        getStateCreate(ec, stakeholders, "In Agreement", "The stakeholder representatives are in agreement.");
        getStateCreate(ec, stakeholders, "Satisfied for Deployment", "The minimal expectations of the stakeholder representatives have been achieved.");
        getStateCreate(ec, stakeholders, "Satisfied in Use", "The system has met or exceeds the minimal stakeholder expectations.");
    }

    private void createRequirementsStates(ExecutionContext ec, Alpha requirements) {
        getStateCreate(ec, requirements, "Architecture Selected", "An architecture has been selected that addresses the key technical risks and any applicable organizational constraints.");
        getStateCreate(ec, requirements, "Demonstrable", "An executable version of the system is available that demonstrates the architecture is fit for purpose and supports testing.");
        getStateCreate(ec, requirements, "Usable", "The system is usable and demonstrates all of the quality characteristics of an operational system.");
        getStateCreate(ec, requirements, "Ready", "The system (as a whole) has been accepted for deployment in a live environment.");
        getStateCreate(ec, requirements, "Operational", "The system is in use in an operational environment.");
        getStateCreate(ec, requirements, "Retired", "The system is no longer supported.");
    }

    private void createSoftwareSystemStates(ExecutionContext ec, Alpha softwareSystem) {
        getStateCreate(ec, softwareSystem, "Conceived", "The need for a new system has been agreed.");
        getStateCreate(ec, softwareSystem, "Bounded", "The purpose and extent of the new system are clear.");
        getStateCreate(ec, softwareSystem, "Coherent", "The requirements provide a consistent description of the essential");
        getStateCreate(ec, softwareSystem, "Acceptable", "The requirements describe a system that is acceptable to the stakeholders.");
        getStateCreate(ec, softwareSystem, "Addressed", "Enough of the requirements have been addressed to satisfy the need for a new system in a way that is acceptable to the stakeholders.");
        getStateCreate(ec, softwareSystem, "Fulfilled", "The requirements that have been addressed fully satisfy the need for a new system.");
    }



    private void getStateCreate(ExecutionContext ec, Alpha opportunity, String name, String description) {
        StateCreate stateCreate = new StateCreate();
        stateCreate.setAlpha(opportunity);
        stateCreate.setAchieved(false);
        stateCreate.setName(name);
        stateCreate.setDescription(description);

        ec.executeChild(this, stateCreate.getName(), stateCreate);
        states.add(stateCreate.getState());
    }

    private Concern getConcern(ExecutionContext ec, String name) {
        ConcernCreate customerConcernCreate = new ConcernCreate();
        customerConcernCreate.setName(name);
        ec.executeChild(this, customerConcernCreate.getName(), customerConcernCreate);
        return customerConcernCreate.getConcern();
    }

    private Alpha getAlpha(ExecutionContext ec, Concern customerConcern, String name) {
        AlphaCreate opportunityAlpha = new AlphaCreate();
        opportunityAlpha.setName(name);
        opportunityAlpha.setConcern(customerConcern);
        ec.executeChild(this, opportunityAlpha.getName(), opportunityAlpha);
        return opportunityAlpha.getAlpha();
    }

    @javax.inject.Inject
    private ProjectRepository repository;
}
