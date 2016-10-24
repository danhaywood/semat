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

    public Project create(String name, String code) {
        final Project object = new Project();
        object.setName(name);
        object.setCode(code);

        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }

    public List<Skill> listSkills() {
        return repositoryService.allInstances(Skill.class);
    }

    public List<Skill> findSkillsByName(final String code) {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Skill.class,
                        "findByName",
                        "code", code));
    }

    public Skill createSkill(final String code) {
        Skill object = new Skill();
        object.setCode(code);
        serviceRegistry.injectServicesInto(object);
        object.init();
        repositoryService.persist(object);
        return object;
    }
}
