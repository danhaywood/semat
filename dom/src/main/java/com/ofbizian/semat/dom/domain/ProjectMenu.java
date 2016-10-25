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

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Project.class
)
@DomainServiceLayout(
        named = "Projects",
        menuOrder = "20"
)
public class ProjectMenu {
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public List<Project> listProjects() {
        return projectRepository.listAll();
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<Project> findByName(
            @ParameterLayout(named="Name")
            final String name
    ) {
        return projectRepository.findByName(name);
    }

    public static class CreateDomainEvent extends ActionDomainEvent<ProjectMenu> {}
    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "3")
    public Project create(
            @ParameterLayout(named="Name")
            final String name,
            @ParameterLayout(named="Code")
            final String code) {
        return projectRepository.create(name, code);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "4")
    public List<Alpha> listAlphas() {
        return projectRepository.listAlphas();
    }

    @Action(domainEvent = CreateDomainEvent.class)
    @MemberOrder(sequence = "5")
    public Alpha createAlpha(
            @ParameterLayout(named="Name")
            final String name,
            @ParameterLayout(named="Concern")
            final Concern concern) {
        return projectRepository.createAlpha(name, concern);
    }

    @javax.inject.Inject
    ProjectRepository projectRepository;

}