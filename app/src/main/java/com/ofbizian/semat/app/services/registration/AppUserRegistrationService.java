package com.ofbizian.semat.app.services.registration;

import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.services.userreg.UserDetails;
import org.apache.isis.applib.value.Password;
import org.isisaddons.module.security.dom.role.ApplicationRole;
import org.isisaddons.module.security.dom.role.ApplicationRoleRepository;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancy;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancyRepository;
import org.isisaddons.module.security.dom.user.ApplicationUser;
import org.isisaddons.module.security.dom.user.ApplicationUserRepository;
import org.isisaddons.module.security.userreg.SecurityModuleAppUserRegistrationServiceAbstract;

@DomainService
public class AppUserRegistrationService extends SecurityModuleAppUserRegistrationServiceAbstract {
    protected ApplicationRole getInitialRole() {
        return findRole("isis-module-security-regular-user");
    }
    protected Set<ApplicationRole> getAdditionalInitialRoles() {
        final HashSet<ApplicationRole> applicationRoles = new HashSet<>();
        applicationRoles.add(findRole("semat-admin"));
        applicationRoles.add(findRole("persistable-mixins-user"));
        return applicationRoles;
    }
    private ApplicationRole findRole(final String roleName) {
        return applicationRoleRepository.findByNameCached(roleName);
    }

    @Override
    public void registerUser(
            final UserDetails userDetails) {

        final Password password = new Password(userDetails.getPassword());
        final ApplicationRole initialRole = getInitialRole();
        final Boolean enabled = true;
        final String username = userDetails.getUsername();
        final String emailAddress = userDetails.getEmailAddress();
        final ApplicationUser applicationUser = applicationUserRepository.newLocalUser(username, password, password, initialRole, enabled, emailAddress);

        final Set<ApplicationRole> additionalRoles = getAdditionalInitialRoles();
        if(additionalRoles != null) {
            for (final ApplicationRole additionalRole : additionalRoles) {
                applicationUser.addRole(additionalRole);
            }
        }

        //TODO set all registered users to demo tenancy for now
        final ApplicationTenancy applicationTenancy = applicationTenancyRepository.findByPath("/demo");
        applicationUser.setTenancy(applicationTenancy);
    }

    @Inject
    private ApplicationRoleRepository applicationRoleRepository;

    @Inject
    private ApplicationUserRepository applicationUserRepository;


    @Inject
    private ApplicationTenancyRepository applicationTenancyRepository;
}