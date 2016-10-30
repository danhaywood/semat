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

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Nature;

@DomainObject(nature = Nature.VIEW_MODEL)
public class ProjectStateView implements Comparable <ProjectStateView>{
    private String alpha;
    private String concern;
    private String state;
    private boolean achieved;

    @MemberOrder(sequence="1")
    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    @MemberOrder(sequence="2")
    public String getConcern() {
        return concern;
    }

    public void setConcern(String concern) {
        this.concern = concern;
    }

    @MemberOrder(sequence="3")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @MemberOrder(sequence="4")
    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    @Override
    public int compareTo(ProjectStateView other) {
        return new CompareToBuilder()
                .append(getConcern(), other.getConcern())
                .append(getAlpha(), other.getAlpha())
                .toComparison();
    }

    public String iconName() {
        return getAlpha();
    }
}
