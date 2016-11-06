package com.ofbizian.semat.dom.domain;

import java.util.List;

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = State.class
)
@DomainServiceLayout(
        named = "States",
        menuOrder = "20"
)
public class StateMenu {

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @MemberOrder(sequence = "1")
    public State newState(
            @ParameterLayout(named="Name")
            final String name,
            @ParameterLayout(named="Description")
            final String descriptions,
            @ParameterLayout(named="Alpha Type")
            final AlphaType alphaType) {
        return stateRepository.createState(name, descriptions, alphaType);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "2")
    public List<State> findStates(
            @ParameterLayout(named="Alpha Type")
            final AlphaType alphaType
    ) {
        return stateRepository.findStates(alphaType);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "3")
    @Property()
    public List<State> allState() {
        return stateRepository.listStates();
    }

    @javax.inject.Inject
    StateRepository stateRepository;

}
