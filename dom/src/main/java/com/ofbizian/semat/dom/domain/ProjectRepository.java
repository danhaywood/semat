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
package com.ofbizian.semat.dom.domain;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Project.class
)
public class ProjectRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;

    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public List<Project> listAll() {
        return repositoryService.allInstances(Project.class);
    }

    public List<Project> findByName(final String name) {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Project.class,
                        "findByName",
                        "name", name));
    }

    public Project create(String code, String name, String description) {
        final Project object = new Project();
        object.setCode(code);
        object.setName(name);
        object.setDescription(description);

        serviceRegistry.injectServicesInto(object);
        object.init();
        createAlphas(object);
        repositoryService.persist(object);
        return object;
    }

    public void remove(Project project) {
        repositoryService.removeAndFlush(project.getOpportunity().getAlphaStates());
//        repositoryService.removeAndFlush(project.getOpportunity());
//        repositoryService.removeAndFlush(project.getStakeholders());
//        repositoryService.removeAndFlush(project.getRequirements());
//        repositoryService.removeAndFlush(project.getSoftwareSystem());
//        repositoryService.removeAndFlush(project.getTeam());
//        repositoryService.removeAndFlush(project.getWork());
//        repositoryService.removeAndFlush(project.getWayOfWorking());
//        repositoryService.removeAndFlush(project);
    }

    private void createAlphas(Project object) {
        Opportunity opportunity = new Opportunity();
        Stakeholders stakeholders = new Stakeholders();
        Requirements requirements = new Requirements();
        SoftwareSystem softwareSystem = new SoftwareSystem();
        Team team = new Team();
        Work work = new Work();
        WayOfWorking wayOfWorking = new WayOfWorking();

        createAlpha(opportunity);
        createAlpha(stakeholders);
        createAlpha(requirements);
        createAlpha(softwareSystem);
        createAlpha(team);
        createAlpha(work);
        createAlpha(wayOfWorking);

        object.setOpportunity(opportunity);
        object.setStakeholders(stakeholders);
        object.setRequirements(requirements);
        object.setSoftwareSystem(softwareSystem);
        object.setTeam(team);
        object.setWork(work);
        object.setWayOfWorking(wayOfWorking);
    }

    private void createAlpha(Alpha object) {
        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
    }

    public List<Alpha> listAlphas() {
        return repositoryService.allInstances(Alpha.class);
    }

    public State createState(final String name, String description) {
        State object = new State();
        object.setName(name);
        object.setDescription(description);
        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public List<State> listStates() {
        return repositoryService.allInstances(State.class);
    }

    public AlphaState createAlphaState(Alpha alpha, State state, boolean achieved, int sequence) {
        AlphaState object = new AlphaState();
        object.setAlpha(alpha);
        object.setState(state);
        object.setAchieved(achieved);
        object.setSequence(sequence);
        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public List<AlphaState> listAlphaState() {
        return repositoryService.allInstances(AlphaState.class);
    }

    public Item createItem(String description) {
        Item object = new Item();
        object.setDescription(description);
        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public List<Item> listItems() {
        return repositoryService.allInstances(Item.class);
    }

    public Checklist createChecklist(State state, Item item, boolean achieved, int sequence) {
        Checklist object = new Checklist();
        object.setState(state);
        object.setItem(item);
        object.setAchieved(achieved);
        object.setSequence(sequence);
        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public List<Checklist> listChecklists() {
        return repositoryService.allInstances(Checklist.class);
    }

}
