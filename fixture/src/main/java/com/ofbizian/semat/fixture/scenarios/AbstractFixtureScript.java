package com.ofbizian.semat.fixture.scenarios;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.sudo.SudoService;

public abstract class AbstractFixtureScript extends FixtureScript {

    @Override
    protected final void execute(final ExecutionContext ec) {

        sudoService.sudo("user", new Runnable() {
            @Override
            public void run() {
                doExecute(ec);
            }
        });

    }

    protected abstract void doExecute(final ExecutionContext ec);

    @Inject
    SudoService sudoService;

}
