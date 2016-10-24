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
package com.ofbizian.semat.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ofbizian.semat.dom.DomainAppDomainModule;
import com.ofbizian.semat.fixture.DomainAppFixtureModule;
import com.ofbizian.semat.fixture.scenarios.RecreateProjects;
import com.ofbizian.semat.fixture.scenarios.UserFixture;
import org.apache.isis.applib.AppManifest;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.isisaddons.module.audit.AuditModule;
import org.isisaddons.module.command.CommandModule;
import org.isisaddons.module.security.SecurityModule;
import org.isisaddons.module.settings.SettingsModule;

/**
 * Bootstrap the application.
 */
public class DomainAppAppManifest implements AppManifest {

    /**
     * Load all services and entities found in (the packages and subpackages within) these modules
     */
    @Override
    public List<Class<?>> getModules() {
        return Arrays.asList(
                DomainAppDomainModule.class
                , DomainAppFixtureModule.class
                , DomainAppAppModule.class
                , SecurityModule.class
//                , SessionLoggerModule.class
                , AuditModule.class
                , SettingsModule.class
                , CommandModule.class
        );
    }

    @Override
    public List<Class<?>> getAdditionalServices() {
        return Arrays.asList(
                org.isisaddons.module.security.dom.password.PasswordEncryptionServiceUsingJBcrypt.class
                ,org.isisaddons.module.security.dom.permission.PermissionsEvaluationServiceAllowBeatsVeto.class
        );
    }

    /**
     * Use shiro for authentication.
     *
     * <p>
     *     NB: this is ignored for integration tests, which always use "bypass".
     * </p>
     */
    @Override
    public String getAuthenticationMechanism() {
        return "shiro";
    }

    /**
     * Use shiro for authorization.
     *
     * <p>
     *     NB: this is ignored for integration tests, which always use "bypass".
     * </p>
     */
    @Override
    public String getAuthorizationMechanism() {
        return "shiro";
    }

    /**
     * Fixtures to be installed.
     */
    @Override
    public List<Class<? extends FixtureScript>> getFixtures() {
        return Lists.newArrayList(
                UserFixture.class
                , RecreateProjects.class
    );
    }
    /**
     * Force fixtures to be loaded.
     */
    @Override
    public Map<String, String> getConfigurationProperties() {
        HashMap<String,String> props = Maps.newHashMap();
        props.put("isis.persistor.datanucleus.install-fixtures","true");
        props.put("isis.reflector.facets.include", "org.isisaddons.module.security.facets.TenantedAuthorizationFacetFactory");

        return props;
    }

}
