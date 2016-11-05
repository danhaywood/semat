package com.ofbizian.semat.fixture.scenarios;

import java.util.Set;

import com.google.common.collect.Sets;
import com.ofbizian.semat.dom.domain.Alpha;
import com.ofbizian.semat.dom.domain.Item;
import com.ofbizian.semat.dom.domain.Project;
import com.ofbizian.semat.dom.domain.ProjectRepository;
import com.ofbizian.semat.dom.domain.State;
import com.ofbizian.semat.fixture.dom.ItemCreate;
import com.ofbizian.semat.fixture.dom.ProjectCreate;
import com.ofbizian.semat.fixture.dom.ProjectTearDown;
import com.ofbizian.semat.fixture.dom.StateCreate;

public class RecreateProjects extends AbstractFixtureScript {
    public RecreateProjects() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    private Set<Project> projects = Sets.newLinkedHashSet();
    private Set<State> states = Sets.newLinkedHashSet();
    private Set<Item> items = Sets.newLinkedHashSet();

    @Override
    protected void doExecute(ExecutionContext ec) {
        ec.executeChild(this, new ProjectTearDown());

        createProject(ec, "SOE", "Standard Operating Environment", "This is a demo SOE project");
        createProject(ec, "ESB", "Enterprise Service Bus",  "This is a demo ESB project");
        }

    private void createProject(ExecutionContext ec, String code, String name, String description) {
        ProjectCreate projectCreate = new ProjectCreate();
        projectCreate.setCode(code);
        projectCreate.setName(name);
        projectCreate.setDescription(description);

        ec.executeChild(this, projectCreate.getName(), projectCreate);
        Project project = projectCreate.getProject();
        createWayOfWorkingStates(ec, project.getWayOfWorking());
        createTeamStates(ec, project.getTeam());
        createWorkStates(ec, project.getWork());
        createOpportunityStates(ec, project.getOpportunity());
        createStakeholdersStates(ec, project.getStakeholders());
        createRequirementsStates(ec, project.getRequirements());
        createSoftwareSystemStates(ec, project.getSoftwareSystem());

        projects.add(project);
    }

    private void createWayOfWorkingStates(ExecutionContext ec, Alpha wayOfWorking) {
        State estabilished = createAlphaState(ec, wayOfWorking, 1, "Principles Established", "The principles, and constraints, that shape the way-of-working are established.", true);
        State foundationEstabilished = createAlphaState(ec, wayOfWorking, 2, "Foundation Established", "The key practices, and tools, that form the foundation of the way of working are selected and ready for use.", true);
        State inUse = createAlphaState(ec, wayOfWorking, 3, "In Use", "Some members of the team are using, and adapting, the way-of- working.", true);
        State inPlace = createAlphaState(ec, wayOfWorking, 4, "In Place", "All team members are using the way of working to accomplish their work.", true);
        State working = createAlphaState(ec, wayOfWorking, 5, "Working well", "The team's way of working is working well for the team.", false);
        State retired = createAlphaState(ec, wayOfWorking, 6, "Retired", "The way of working is no longer in use by the team.", false);

        createChecklist(ec, estabilished, 1, "Principles and constraints are committed to by the team.", true);
        createChecklist(ec, estabilished, 2, "Principles and constraints are agreed to by the stakeholders.", true);
        createChecklist(ec, estabilished, 3, "The tool needs of the work and its stakeholders are agreed.", true);
        createChecklist(ec, estabilished, 4, "A recommendation for the approach to be taken is available.", true);
        createChecklist(ec, estabilished, 5, "The context within which the team will operate is understood.", false);
        createChecklist(ec, estabilished, 6, "The constraints that apply to the selection, acquisition, and use of practices and tools are known.", false);

        createChecklist(ec, foundationEstabilished, 1, "The key practices and tools that form the foundation of the way-of-working are selected.", false);
        createChecklist(ec, foundationEstabilished, 2, "Enough practices for work to start are agreed to by the team.", false);
        createChecklist(ec, foundationEstabilished, 3, "All non-negotiable practices and tools have been identified.", false);
        createChecklist(ec, foundationEstabilished, 4, "The gaps that exist between the practices and tools that are needed and the practices and tools that are available have been analyzed and understood.", false);
        createChecklist(ec, foundationEstabilished, 5, "The capability gaps that exist between what is needed to execute the desired way of working and the capability levels of the team have been analyzed and understood.", false);
        createChecklist(ec, foundationEstabilished, 6, "The selected practices and tools have been integrated to form a usable way-of-working.", false);

        createChecklist(ec, inUse, 1, "The practices and tools are being used to do real work.", true);
        createChecklist(ec, inUse, 2, "The use of the practices and tools selected are regularly inspected.", true);
        createChecklist(ec, inUse, 3, "The practices and tools are being adapted to the team’s context.", true);
        createChecklist(ec, inUse, 4, "The use of the practices and tools is supported by the team.", false);
        createChecklist(ec, inUse, 5, "Procedures are in place to handle feedback on the team’s way of working.", false);
        createChecklist(ec, inUse, 6, "The practices and tools support team communication and collaboration.", true);

        createChecklist(ec, inPlace, 1, "The practices and tools are being used by the whole team to perform their work.", false);
        createChecklist(ec, inPlace, 2, "All team members have access to the practices and tools required to do their work.", false);
        createChecklist(ec, inPlace, 3, "The whole team is involved in the inspection and adaptation of the way-of-working.", false);

        createChecklist(ec, working, 1, "Team members are making progress as planned by using and adapting the way-of- working to suit their current context.", false);
        createChecklist(ec, working, 2, "The team naturally applies the practices without thinking about them", false);
        createChecklist(ec, working, 3, "The tools naturally support the way that the team works.", false);
        createChecklist(ec, working, 4, "The team continually tunes their use of the practices and tools.", false);

        createChecklist(ec, retired, 1, "The team's way of working is no longer being used.", false);
        createChecklist(ec, retired, 2, "Lessons learned are shared for future use.", false);
    }

    private void createTeamStates(ExecutionContext ec, Alpha team) {
        State seeded = createAlphaState(ec, team, 1, "Seeded", "The team’s mission is clear and the know-how needed to grow the team is in place.", true);
        State formed = createAlphaState(ec, team, 2, "Formed", "The team has been populated with enough committed people to start the mission.", true);
        State collaborating = createAlphaState(ec, team, 3, "Collaborating", "The team members are working together as one unit.", false);
        State performing = createAlphaState(ec, team, 4, "Performing", "The team is working effectively and efficiently.", false);
        State adjourned = createAlphaState(ec, team, 5, "Adjourned", "The team is no longer accountable for carrying out its mission.", false);

        createChecklist(ec, seeded, 1, "The team mission has been defined in terms of the opportunities and outcomes.", true);
        createChecklist(ec, seeded, 2, "Constraints on the team's operation are known.", true);
        createChecklist(ec, seeded, 3, "Mechanisms to grow the team are in place.", true);
        createChecklist(ec, seeded, 4, "The composition of the team is defined.", false);
        createChecklist(ec, seeded, 5, "Any constraints on where and how the work is carried out are defined.", false);
        createChecklist(ec, seeded, 6, "The team's responsibilities are outlined.", false);
        createChecklist(ec, seeded, 7, "The level of team commitment is clear.", false);
        createChecklist(ec, seeded, 8, "Required competencies are identified.", false);
        createChecklist(ec, seeded, 9, "The team size is determined.", false);
        createChecklist(ec, seeded, 10, "Governance rules are defined.", false);
        createChecklist(ec, seeded, 11, "Leadership model is selected.", false);

        createChecklist(ec, formed, 1, "Individual responsibilities are understood.", true);
        createChecklist(ec, formed, 2, "Enough team members have been recruited to enable the work to progress.", true);
        createChecklist(ec, formed, 3, "Every team member understands how the team is organized and what their individual role is.", true);
        createChecklist(ec, formed, 4, "All team members understand how to perform their work.", false);
        createChecklist(ec, formed, 5, "The team members have met (perhaps virtually) and are beginning to get to know each other.", false);
        createChecklist(ec, formed, 6, "The team members understand their responsibilities and how they align with their competencies.", false);
        createChecklist(ec, formed, 7, "Team members are accepting work.", false);
        createChecklist(ec, formed, 8, "Any external collaborators (organizations, teams and individuals) are identified.", false);
        createChecklist(ec, formed, 9, "Team communication mechanisms have been defined.", false);
        createChecklist(ec, formed, 10, "Each team member commits to working on the team as defined.", false);

        createChecklist(ec, collaborating, 1, "The team is working as one cohesive unit.", false);
        createChecklist(ec, collaborating, 2, "Communication within the team is open and honest.", false);
        createChecklist(ec, collaborating, 3, "The team is focused on achieving the team mission.", false);
        createChecklist(ec, collaborating, 4, "The team members know each other.", false);

        createChecklist(ec, performing, 1, "The team consistently meets its commitments.", false);
        createChecklist(ec, performing, 2, "The team continuously adapts to the changing context.", false);
        createChecklist(ec, performing, 3, "The team identifies and addresses problems without outside help.", false);
        createChecklist(ec, performing, 4, "Effective progress is being achieved with minimal avoidable backtracking and reworking.", false);
        createChecklist(ec, performing, 5, "Wasted work, and the potential for wasted work are continuously eliminated.", false);

        createChecklist(ec, adjourned, 1, "The team responsibilities have been handed over or fulfilled.", false);
        createChecklist(ec, adjourned, 2, "The team members are available for assignment to other teams.", false);
        createChecklist(ec, adjourned, 3, "No further effort is being put in by the team to complete the mission.", false);
    }

    private void createWorkStates(ExecutionContext ec, Alpha work) {
        State initiated = createAlphaState(ec, work, 1, "Initiated", "The work has been requested.", true);
        State prepared = createAlphaState(ec, work, 2, "Prepared", "All pre-conditions for starting the work have been met.", true);
        State started = createAlphaState(ec, work, 3, "Started", "The work is proceeding.", true);
        State controlled = createAlphaState(ec, work, 4, "Under Control", "The work is going well, risks are under control, and productivity levels are sufficient to achieve a satisfactory result.", false);
        State concluded = createAlphaState(ec, work, 5, "Concluded", "The work to produce the results has been concluded.", false);
        State closed = createAlphaState(ec, work, 6, "Closed", "All remaining housekeeping tasks have been completed and the work has been officially closed.", false);

        createChecklist(ec, initiated, 1, "The result required of the work being initiated is clear.", true);
        createChecklist(ec, initiated, 2, "Any constraints on the work’s performance are clearly identified.", true);
        createChecklist(ec, initiated, 3, "The stakeholders that will fund the work are known.", true);
        createChecklist(ec, initiated, 4, "The initiator of the work is clearly identified.", false);
        createChecklist(ec, initiated, 5, "The stakeholders that will accept the results are known.", false);
        createChecklist(ec, initiated, 6, "The source of funding is clear.", false);
        createChecklist(ec, initiated, 7, "The priority of the work is clear.", false);

        createChecklist(ec, prepared, 1, "Commitment is made.", true);
        createChecklist(ec, prepared, 2, "Cost and effort of the work are estimated.", true);
        createChecklist(ec, prepared, 3, "Resource availability is understood.", true);
        createChecklist(ec, prepared, 4, "Governance policies and procedures are clear.", false);
        createChecklist(ec, prepared, 5, "Risk exposure is understood.", false);
        createChecklist(ec, prepared, 6, "Acceptance criteria are defined and agreed with client.", false);
        createChecklist(ec, prepared, 7, "The work is broken down sufficiently for productive work to start.", false);
        createChecklist(ec, prepared, 8, "Tasks have been identified and prioritized by the team and stakeholders.", false);
        createChecklist(ec, prepared, 9, "A credible plan is in place.", false);
        createChecklist(ec, prepared, 10, "Funding to start the work is in place.", false);
        createChecklist(ec, prepared, 11, "The team or at least some of the team members are ready to start the work.", false);
        createChecklist(ec, prepared, 12, "Integration and delivery points are defined.", false);

        createChecklist(ec, started, 1, "Development work has been started.", true);
        createChecklist(ec, started, 2, "Work progress is monitored.", false);
        createChecklist(ec, started, 3, "The work is being broken down into actionable work items with clear definitions of done.", false);
        createChecklist(ec, started, 4, "Team members are accepting and progressing tasks.", false);

        createChecklist(ec, controlled, 1, "Tasks are being completed.", true);
        createChecklist(ec, controlled, 2, "Unplanned work is under control.", true);
        createChecklist(ec, controlled, 3, "Risks are under control as the impact if they occur and the likelihood of them occurring have been reduced to acceptable levels.", false);
        createChecklist(ec, controlled, 4, "Estimates are revised to reflect the team’s performance.", false);
        createChecklist(ec, controlled, 5, "Measures are available to show progress and velocity.", false);
        createChecklist(ec, controlled, 6, "Re-work is under control.", false);
        createChecklist(ec, controlled, 7, "Tasks are consistently completed on time and within their estimates.", false);

        createChecklist(ec, concluded, 1, "All outstanding tasks are administrative housekeeping or related to preparing the next piece of work.", false);
        createChecklist(ec, concluded, 2, "Work results have been achieved.", false);
        createChecklist(ec, concluded, 3, "The stakeholder(s) has accepted the resulting software system.", false);

        createChecklist(ec, closed, 1, "Lessons learned have been itemized, recorded and discussed.", true);
        createChecklist(ec, closed, 2, "Metrics have been made available.", true);
        createChecklist(ec, closed, 3, "Everything has been archived.", true);
        createChecklist(ec, closed, 4, "The budget has been reconciled and closed.", false);
        createChecklist(ec, closed, 5, "The team has been released.", false);
        createChecklist(ec, closed, 6, "There are no outstanding, uncompleted tasks.", false);
    }

    private void createOpportunityStates(ExecutionContext ec, Alpha opportunity) {
        State identified = createAlphaState(ec, opportunity, 1, "Identified", "A commercial, social, or business opportunity has been identified that could be addressed by a software-based solution.", true);
        State solutionNeeded = createAlphaState(ec, opportunity, 2, "Solution Needed", "The need for a software-based solution has been confirmed.", true);
        State valueEstabilished = createAlphaState(ec, opportunity, 3, "Value Established", "The value of a successful solution has been established.", true);
        State viable = createAlphaState(ec, opportunity, 4, "Viable", "It is agreed that a solution can be produced quickly and cheaply enough to successfully address the opportunity.", false);
        State addressed = createAlphaState(ec, opportunity, 5, "Addressed", "A solution has been produced that demonstrably addresses the opportunity.", false);
        State benefitAccured = createAlphaState(ec, opportunity, 6, "Benefit Accrued", "The operational use or sale of the solution is creating tangible benefits.", false);


        createChecklist(ec, identified, 1, "An idea for a way of improving current ways of working, increasing market share, or applying a new or innovative software system has been identified.", true);
        createChecklist(ec, identified, 2, "At least one of the stakeholders wishes to make an investment in better understanding the opportunity and the value associated with addressing it.", true);
        createChecklist(ec, identified, 3, "The other stakeholders who share the opportunity have been identified.", true);

        createChecklist(ec, solutionNeeded, 1, "The stakeholders in the opportunity and the proposed solution have been identified.", true);
        createChecklist(ec, solutionNeeded, 2, "The stakeholders' needs that generate the opportunity have been established.", true);
        createChecklist(ec, solutionNeeded, 3, "Any underlying problems and their root causes have been identified.", true);
        createChecklist(ec, solutionNeeded, 4, "It has been confirmed that a software-based solution is needed.", true);
        createChecklist(ec, solutionNeeded, 5, "At least one software-based solution has been proposed.", true);

        createChecklist(ec, valueEstabilished, 1, "The value of addressing the opportunity has been quantified either in absolute terms or in returns or savings per time period (e.g., per annum).", false);
        createChecklist(ec, valueEstabilished, 2, "The impact of the solution on the stakeholders is understood.", false);
        createChecklist(ec, valueEstabilished, 3, "The value that the software system offers to the stakeholders that fund and use the software system is understood.", false);
        createChecklist(ec, valueEstabilished, 4, "The success criteria by which the deployment of the software system is to be judged are clear.", false);
        createChecklist(ec, valueEstabilished, 5, "The desired outcomes required of the solution are clear and quantified.", false);

        createChecklist(ec, viable, 1, "A solution has been outlined.", true);
        createChecklist(ec, viable, 2, "The indications are that the solution can be developed and deployed within constraints.", true);
        createChecklist(ec, viable, 3, "The risks associated with the solution are acceptable and manageable.", true);
        createChecklist(ec, viable, 4, "The indicative (ball-park) costs of the solution are less than the anticipated value of the opportunity.", true);
        createChecklist(ec, viable, 5, "The reasons for the development of a software-based solution are understood by all members of the team.", false);
        createChecklist(ec, viable, 6, "It is clear that the pursuit of the opportunity is viable.", false);

        createChecklist(ec, addressed, 1, "A usable system that demonstrably addresses the opportunity is available.", true);
        createChecklist(ec, addressed, 2, "The stakeholders agree that the available solution is worth deploying.", true);
        createChecklist(ec, addressed, 3, "The stakeholders are satisfied that the solution produced addresses the opportunity.", false);

        createChecklist(ec, benefitAccured, 1, "The solution has started to accrue benefits for the stakeholders.", true);
        createChecklist(ec, benefitAccured, 2, "The return-on-investment profile is at least as good as anticipated.", true);
    }

    private void createStakeholdersStates(ExecutionContext ec, Alpha stakeholders) {
        State recognized = createAlphaState(ec, stakeholders, 1, "Recognized", "Stakeholders have been identified.", true);
        State represented = createAlphaState(ec, stakeholders, 2, "Represented", "The mechanisms for involving the stakeholders are agreed and the stakeholder representatives have been appointed.", true);
        State involved = createAlphaState(ec, stakeholders, 3, "Involved", "The stakeholder representatives are actively involved in the work and fulfilling their responsibilities.", true);
        State inAgreement = createAlphaState(ec, stakeholders, 4, "In Agreement", "The stakeholder representatives are in agreement.", false);
        State inDeployment = createAlphaState(ec, stakeholders, 5, "Satisfied for Deployment", "The minimal expectations of the stakeholder representatives have been achieved.", false);
        State inUse = createAlphaState(ec, stakeholders, 6, "Satisfied in Use", "The system has met or exceeds the minimal stakeholder expectations.", false);
        createChecklist(ec, recognized, 1, "All the different groups of stakeholders that are, or will be, affected by the development and operation of the software system are identified.", true);
        createChecklist(ec, recognized, 2, "There is agreement on the stakeholder groups to be represented. At a minimum, the stakeholders groups that fund, use, support, and maintain the system have been considered.", true);
        createChecklist(ec, recognized, 3, "The responsibilities of the stakeholder representatives have been defined.", true);

        createChecklist(ec, represented, 1, "The stakeholder representatives have agreed to take on their responsibilities.", false);
        createChecklist(ec, represented, 2, "The stakeholder representatives are authorized to carry out their responsibilities.", false);
        createChecklist(ec, represented, 3, "The collaboration approach among the stakeholder representatives has been agreed.", false);
        createChecklist(ec, represented, 4, "The stakeholder representatives support and respect the team's way of working.", false);

        createChecklist(ec, involved, 1, "The stakeholder representatives assist the team in accordance with their responsibilities.", false);
        createChecklist(ec, involved, 2, "The stakeholder representatives provide feedback and take part in decision making in a timely manner.", false);
        createChecklist(ec, involved, 3, "The stakeholder representatives provide feedback and take part in decision making in a timely manner.", false);

        createChecklist(ec, inAgreement, 1, "The stakeholder representatives have agreed upon their minimal expectations for the next deployment of the new system.", true);
        createChecklist(ec, inAgreement, 2, "The stakeholder representatives are happy with their involvement in the work.", true);
        createChecklist(ec, inAgreement, 3, "The stakeholder representatives agree that their input is valued by the team and treated with respect.", true);
        createChecklist(ec, inAgreement, 4, "The team members agree that their input is valued by the stakeholder representatives and treated with respect.", false);
        createChecklist(ec, inAgreement, 5, "The stakeholder representatives agree with how their different priorities and perspectives are being balanced to provide a clear direction for the team.", true);

        createChecklist(ec, inDeployment, 1, "The stakeholder representatives provide feedback on the system from their stakeholder group perspective.", false);
        createChecklist(ec, inDeployment, 2, "The stakeholder representatives confirm that they agree that the system is ready for deployment.", false);

        createChecklist(ec, inUse, 1, "Stakeholders are using the new system and providing feedback on their experiences.", true);
        createChecklist(ec, inUse, 2, "The stakeholders confirm that the new system meets their expectations.", true);
    }

    private void createSoftwareSystemStates(ExecutionContext ec, Alpha softwareSystem) {
        State selected = createAlphaState(ec, softwareSystem, 1, "Architecture Selected", "An architecture has been selected that addresses the key technical risks and any applicable organizational constraints.", true);
        State demonstrable = createAlphaState(ec, softwareSystem, 2, "Demonstrable", "An executable version of the system is available that demonstrates the architecture is fit for purpose and supports testing.", true);
        State usable = createAlphaState(ec, softwareSystem, 3, "Usable", "The system is usable and demonstrates all of the quality characteristics of an operational system.", false);
        State ready = createAlphaState(ec, softwareSystem, 4, "Ready", "The system (as a whole) has been accepted for deployment in a live environment.", false);
        State operational = createAlphaState(ec, softwareSystem, 5, "Operational", "The system is in use in an operational environment.", false);
        State retired = createAlphaState(ec, softwareSystem, 6, "Retired", "The system is no longer supported.", false);

        createChecklist(ec, selected, 1, "The criteria to be used when selecting the architecture have been agreed on.", true);
        createChecklist(ec, selected, 2, "Hardware platforms have been identified.", true);
        createChecklist(ec, selected, 3, "Programming languages and technologies to be used have been selected.", true);
        createChecklist(ec, selected, 4, "System boundary is known.", false);
        createChecklist(ec, selected, 5, "Significant decisions about the organization of the system have been made.", false);
        createChecklist(ec, selected, 6, "Buy, build, and reuse decisions have been made.", false);
        createChecklist(ec, selected, 7, "Key technical risks agreed to.", false);

        createChecklist(ec, demonstrable, 1, "Key architectural characteristics have been demonstrated.", true);
        createChecklist(ec, demonstrable, 2, "The system can be exercised and its performance can be measured.", true);
        createChecklist(ec, demonstrable, 3, "Critical hardware configurations have been demonstrated.", true);
        createChecklist(ec, demonstrable, 4, "Critical interfaces have been demonstrated.", false);
        createChecklist(ec, demonstrable, 5, "The integration with other existing systems has been demonstrated.", false);

        createChecklist(ec, usable, 1, "The system can be operated by stakeholders who use it.", true);
        createChecklist(ec, usable, 2, "The functionality provided by the system has been tested.", true);
        createChecklist(ec, usable, 3, "The performance of the system is acceptable to the stakeholders.", true);
        createChecklist(ec, usable, 4, "Defect levels are acceptable to the stakeholders.", false);
        createChecklist(ec, usable, 5, "The system is fully documented.", false);
        createChecklist(ec, usable, 6, "Release content is known.", false);
        createChecklist(ec, usable, 7, "The added value provided by the system is clear.", false);

        createChecklist(ec, ready, 1, "Installation and other user documentation are available.", true);
        createChecklist(ec, ready, 2, "The stakeholder representatives accept the system as fit-for-purpose.", true);
        createChecklist(ec, ready, 3, "The stakeholder representatives want to make the system operational.", false);
        createChecklist(ec, ready, 4, "Operational support is in place.", false);

        createChecklist(ec, operational, 1, "The system has been made available to the stakeholders intended to use it.", true);
        createChecklist(ec, operational, 2, "At least one example of the system is fully operational.", true);
        createChecklist(ec, operational, 3, "The system is fully supported to the agreed service levels.", false);

        createChecklist(ec, retired, 1, "The system has been replaced or discontinued.", true);
        createChecklist(ec, retired, 2, "The system is no longer supported.", false);
        createChecklist(ec, retired, 3, "There are no “official” stakeholders who still use the system.", false);
        createChecklist(ec, retired, 4, "Updates to the system will no longer be produced.", true);
    }

    private void createRequirementsStates(ExecutionContext ec, Alpha requirements) {
        State conceived = createAlphaState(ec, requirements, 1, "Conceived", "The need for a new system has been agreed.", true);
        State bounded = createAlphaState(ec, requirements, 2, "Bounded", "The purpose and extent of the new system are clear.", true);
        State coherent = createAlphaState(ec, requirements, 3, "Coherent", "The requirements provide a consistent description of the essential", true);
        State acceptable = createAlphaState(ec, requirements, 4, "Acceptable", "The requirements describe a system that is acceptable to the stakeholders.", false);
        State addressed = createAlphaState(ec, requirements, 5, "Addressed", "Enough of the requirements have been addressed to satisfy the need for a new system in a way that is acceptable to the stakeholders.", false);
        State fulfilled = createAlphaState(ec, requirements, 6, "Fulfilled", "The requirements that have been addressed fully satisfy the need for a new system.", false);

        createChecklist(ec, conceived, 1, "The initial set of stakeholders agrees that a system is to be produced.", true);
        createChecklist(ec, conceived, 2, "The stakeholders that will use the new system are identified.", true);
        createChecklist(ec, conceived, 3, "The stakeholders that will fund the initial work on the new system are identified.", true);
        createChecklist(ec, conceived, 4, "There is a clear opportunity for the new system to address.", false);

        createChecklist(ec, bounded, 1, "The stakeholders involved in developing the new system are identified.", true);
        createChecklist(ec, bounded, 2, "The stakeholders agree on the purpose of the new system.", true);
        createChecklist(ec, bounded, 3, "It is clear what success is for the new system.", true);
        createChecklist(ec, bounded, 4, "The stakeholders have a shared understanding of the extent of the proposed solution.", false);
        createChecklist(ec, bounded, 5, "The way the requirements will be described is agreed upon.", false);
        createChecklist(ec, bounded, 6, "The mechanisms for managing the requirements are in place.", false);
        createChecklist(ec, bounded, 7, "The prioritization scheme is clear.", true);
        createChecklist(ec, bounded, 8, "Constraints are identified and considered.", false);
        createChecklist(ec, bounded, 9, "Assumptions are clearly stated.", false);

        createChecklist(ec, coherent, 1, "The requirements are captured and shared with the team and the stakeholders.", true);
        createChecklist(ec, coherent, 2, "The origin of the requirements is clear.", true);
        createChecklist(ec, coherent, 3, "The rationale behind the requirements is clear.", true);
        createChecklist(ec, coherent, 4, "Conflicting requirements are identified and attended to.", false);
        createChecklist(ec, coherent, 5, "The requirements communicate the essential characteristics of the system to be delivered.", false);
        createChecklist(ec, coherent, 6, "The most important usage scenarios for the system can be explained.", true);
        createChecklist(ec, coherent, 7, "The priority of the requirements is clear.", true);
        createChecklist(ec, coherent, 8, "The impact of implementing the requirements is understood.", false);
        createChecklist(ec, coherent, 9, "The team understands what has to be delivered and agrees to deliver it.", false);

        createChecklist(ec, acceptable, 1, "The stakeholders accept that the requirements describe an acceptable solution.", false);
        createChecklist(ec, acceptable, 2, "The rate of change to the agreed requirements is relatively low and under control.", false);
        createChecklist(ec, acceptable, 3, "The value provided by implementing the requirements is clear.", true);
        createChecklist(ec, acceptable, 4, "The parts of the opportunity satisfied by the requirements are clear.", true);
        createChecklist(ec, acceptable, 5, "The requirements are testable.", false);

        createChecklist(ec, addressed, 1, "Enough of the requirements are addressed for the resulting system to be acceptable to the stakeholders.", true);
        createChecklist(ec, addressed, 2, "The stakeholders accept the requirements as accurately reflecting what the system does and does not do.", true);
        createChecklist(ec, addressed, 3, "The set of requirement items implemented provide clear value to the stakeholders.", true);
        createChecklist(ec, addressed, 4, "The system implementing the requirements is accepted by the stakeholders as worth making operational.", false);

        createChecklist(ec, fulfilled, 1, "The stakeholders accept the requirements as accurately capturing what they require to fully satisfy the need for a new system.", false);
        createChecklist(ec, fulfilled, 2, "There are no outstanding requirement items preventing the system from being accepted as fully satisfying the requirements.", false);
        createChecklist(ec, fulfilled, 3, "The system is accepted by the stakeholders as fully satisfying the requirements.", false);
    }

    private State createAlphaState(ExecutionContext ec, Alpha alpha, int sequence, String name, String description, boolean achieved) {
        StateCreate stateCreate = new StateCreate();
        stateCreate.setAlpha(alpha);
        stateCreate.setAchieved(achieved);
        stateCreate.setSequence(sequence);
        stateCreate.setName(name);
        stateCreate.setDescription(description);

        ec.executeChild(this, stateCreate.getName(), stateCreate);
        states.add(stateCreate.getState());
        return stateCreate.getState();
    }

    private Item createChecklist(ExecutionContext ec, State state, int sequence, String description, boolean achieved) {
        ItemCreate itemCreate = new ItemCreate();
        itemCreate.setState(state);
        itemCreate.setAchieved(achieved);
        itemCreate.setSequence(sequence);
        itemCreate.setDescription(description);

        ec.executeChild(this, itemCreate.getDescription(), itemCreate);
        items.add(itemCreate.getItem());
        return itemCreate.getItem();
    }

    @javax.inject.Inject
    private ProjectRepository repository;
}
