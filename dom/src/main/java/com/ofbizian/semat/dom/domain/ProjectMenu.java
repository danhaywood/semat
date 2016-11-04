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

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Project.class
)
@DomainServiceLayout(
        named = "Projects",
        menuOrder = "10"
)
public class ProjectMenu {

    public static class CreateDomainEvent extends ActionDomainEvent<ProjectMenu> {}
    @Action(domainEvent = CreateDomainEvent.class,
            semantics = SemanticsOf.NON_IDEMPOTENT)
    @MemberOrder(sequence = "1")
    public Project newProject(
            @ParameterLayout(named="Code")
            final String code,
            @ParameterLayout(named="Name")
            final String name,
            @ParameterLayout(named="Description")
            final String descriptions) {
        return projectRepository.create(code, name, descriptions);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<Project> findProjects(
            @ParameterLayout(named="Name")
            final String name
    ) {
        return projectRepository.findByName(name);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "3")
    @Property()
    public List<Project> allProjects() {
        return projectRepository.listAll();
    }

    @javax.inject.Inject
    ProjectRepository projectRepository;

}
