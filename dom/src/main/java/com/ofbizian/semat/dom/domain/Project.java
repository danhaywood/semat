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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.jdo.annotations.IdentityType;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.ViewModelLayout;

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

    @javax.jdo.annotations.Persistent(table = "ProjectAlphas")
    @javax.jdo.annotations.Join()
    private Set<Alpha> alphas = new TreeSet<>();

    public Set<Alpha> getAlphas() {
        return alphas;
    }

    public void setAlphas(Set<Alpha> alphas) {
        this.alphas = alphas;
    }

    @Action
    public Project addAlpha(Alpha alpha) {
        getAlphas().add(alpha);
        return this;
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    public Project removeAlpha(Alpha alpha) {
        getAlphas().remove(alpha);
        return this;
    }

    @CollectionLayout(defaultView = "table")
    public Set<ProjectStateView> getAlphaStates() {
        Set<ProjectStateView> projectStateViews = new LinkedHashSet<>();
        final Set<Alpha> alphas = getAlphas();
        for (Alpha alpha : alphas) {
            final ProjectStateView view = new ProjectStateView();
            view.setAlpha(alpha.getName());
            view.setConcern(alpha.getConcern().getName());

            final SortedSet<AlphaState> alphaStates = alpha.getAlphaStates();
            List<AlphaState> list = new ArrayList(alphaStates);
            Collections.sort(list, Collections.reverseOrder());
            Set<AlphaState> resultSet = new LinkedHashSet(list);
            String lastState = null;

            for (AlphaState alphaState : resultSet) {
                if (alphaState.isAchieved()) {
                    view.setAchieved(alphaState.isAchieved());
                    break;
                }
                lastState = alphaState.getState().getName();
            }
            view.setState(lastState);
            projectStateViews.add(view);
        }
        return projectStateViews;
    }

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

    public String title() {
        return name + "[" + code + "]";
    }
}