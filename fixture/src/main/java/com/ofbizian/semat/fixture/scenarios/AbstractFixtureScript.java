package com.ofbizian.semat.fixture.scenarios;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.sudo.SudoService;

public abstract class AbstractFixtureScript extends FixtureScript {

    protected String userName = "user";

    @Override
    protected final void execute(final ExecutionContext ec) {

        sudoService.sudo(userName, new Runnable() {
            @Override
            public void run() {
                doExecute(ec);
            }
        });
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    protected abstract void doExecute(final ExecutionContext ec);

    @Inject
    SudoService sudoService;

}
