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

public class RecreateProjects extends AbstractFixtureScript {
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

        createProject(ec, "Standard Operating Environment", "SOE");
        createProject(ec, "Enterprise Service Bus", "ESB");
        }

    private void createProject(ExecutionContext ec, String name, String code) {
        ProjectCreate projectCreate = new ProjectCreate();
        projectCreate.setName(name);
        projectCreate.setCode(code);
        projectCreate.setAlphas(alphas);
        ec.executeChild(this, projectCreate.getName(), projectCreate);
        Project project = projectCreate.getProject();
        projects.add(project);
    }

    private void createWayOfWorkingStates(ExecutionContext ec, Alpha wayOfWorking) {
        createAlphaState(ec, wayOfWorking, 1, "Principles Established", "The principles, and constraints, that shape the way-of-working are established.");
        createAlphaState(ec, wayOfWorking, 2, "Foundation Established", "The key practices, and tools, that form the foundation of the way of working are selected and ready for use.");
        createAlphaState(ec, wayOfWorking, 3, "In Use", "Some members of the team are using, and adapting, the way-of- working.");
        createAlphaState(ec, wayOfWorking, 4, "In Place", "All team members are using the way of working to accomplish their work.");
        createAlphaState(ec, wayOfWorking, 5, "Working well", "The team's way of working is working well for the team.");
        createAlphaState(ec, wayOfWorking, 6, "Retired", "The way of working is no longer in use by the team.");
    }

    private void createTeamStates(ExecutionContext ec, Alpha team) {
        createAlphaState(ec, team, 1, "Seeded", "The team’s mission is clear and the know-how needed to grow the team is in place.");
        createAlphaState(ec, team, 2, "Formed", "The team has been populated with enough committed people to start the mission.");
        createAlphaState(ec, team, 3, "Collaborating", "The team members are working together as one unit.");
        createAlphaState(ec, team, 4, "Performing", "The team is working effectively and efficiently.");
        createAlphaState(ec, team, 5, "Adjourned", "The team is no longer accountable for carrying out its mission.");
    }

    private void createWorkStates(ExecutionContext ec, Alpha work) {
        createAlphaState(ec, work, 1, "Initiated", "The work has been requested.");
        createAlphaState(ec, work, 2, "Prepared", "All pre-conditions for starting the work have been met.");
        createAlphaState(ec, work, 3, "Started", "The work is proceeding.");
        createAlphaState(ec, work, 4, "Under Control", "The work is going well, risks are under control, and productivity levels are sufficient to achieve a satisfactory result.");
        createAlphaState(ec, work, 5, "Concluded", "The work to produce the results has been concluded.");
        createAlphaState(ec, work, 6, "Closed", "All remaining housekeeping tasks have been completed and the work has been officially closed.");
    }

    private void createOpportunityStates(ExecutionContext ec, Alpha opportunity) {
        createAlphaState(ec, opportunity, 1, "Identified", "A commercial, social, or business opportunity has been identified that could be addressed by a software-based solution.");
        createAlphaState(ec, opportunity, 2, "Solution Needed", "The need for a software-based solution has been confirmed.");
        createAlphaState(ec, opportunity, 3, "Value Established", "The value of a successful solution has been established.");
        createAlphaState(ec, opportunity, 4, "Viable", "It is agreed that a solution can be produced quickly and cheaply enough to successfully address the opportunity.");
        createAlphaState(ec, opportunity, 5, "Addressed", "A solution has been produced that demonstrably addresses the opportunity.");
        createAlphaState(ec, opportunity, 6, "Benefit Accrued", "The operational use or sale of the solution is creating tangible benefits.");
    }

    private void createStakeholdersStates(ExecutionContext ec, Alpha stakeholders) {
        createAlphaState(ec, stakeholders, 1, "Recognized", "Stakeholders have been identified.");
        createAlphaState(ec, stakeholders, 2, "Represented", "The mechanisms for involving the stakeholders are agreed and the stakeholder representatives have been appointed.");
        createAlphaState(ec, stakeholders, 3, "Involved", "The stakeholder representatives are actively involved in the work and fulfilling their responsibilities.");
        createAlphaState(ec, stakeholders, 4, "In Agreement", "The stakeholder representatives are in agreement.");
        createAlphaState(ec, stakeholders, 5, "Satisfied for Deployment", "The minimal expectations of the stakeholder representatives have been achieved.");
        createAlphaState(ec, stakeholders, 6, "Satisfied in Use", "The system has met or exceeds the minimal stakeholder expectations.");
    }

    private void createRequirementsStates(ExecutionContext ec, Alpha requirements) {
        createAlphaState(ec, requirements, 1, "Architecture Selected", "An architecture has been selected that addresses the key technical risks and any applicable organizational constraints.");
        createAlphaState(ec, requirements, 2, "Demonstrable", "An executable version of the system is available that demonstrates the architecture is fit for purpose and supports testing.");
        createAlphaState(ec, requirements, 3, "Usable", "The system is usable and demonstrates all of the quality characteristics of an operational system.");
        createAlphaState(ec, requirements, 4, "Ready", "The system (as a whole) has been accepted for deployment in a live environment.");
        createAlphaState(ec, requirements, 5, "Operational", "The system is in use in an operational environment.");
        createAlphaState(ec, requirements, 6, "Retired", "The system is no longer supported.");
    }

    private void createSoftwareSystemStates(ExecutionContext ec, Alpha softwareSystem) {
        createAlphaState(ec, softwareSystem, 1, "Conceived", "The need for a new system has been agreed.");
        createAlphaState(ec, softwareSystem, 2, "Bounded", "The purpose and extent of the new system are clear.");
        createAlphaState(ec, softwareSystem, 3, "Coherent", "The requirements provide a consistent description of the essential");
        createAlphaState(ec, softwareSystem, 4, "Acceptable", "The requirements describe a system that is acceptable to the stakeholders.");
        createAlphaState(ec, softwareSystem, 5, "Addressed", "Enough of the requirements have been addressed to satisfy the need for a new system in a way that is acceptable to the stakeholders.");
        createAlphaState(ec, softwareSystem, 6, "Fulfilled", "The requirements that have been addressed fully satisfy the need for a new system.");
    }

    private void createAlphaState(ExecutionContext ec, Alpha alpha, int sequence, String name, String description) {
        StateCreate stateCreate = new StateCreate();
        stateCreate.setAlpha(alpha);
        stateCreate.setAchieved(false);
        stateCreate.setSequence(sequence);
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
