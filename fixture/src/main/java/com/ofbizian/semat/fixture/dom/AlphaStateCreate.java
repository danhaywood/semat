package com.ofbizian.semat.fixture.dom;

import com.ofbizian.semat.dom.domain.Alpha;
import com.ofbizian.semat.dom.domain.AlphaState;
import com.ofbizian.semat.dom.domain.AlphaType;
import com.ofbizian.semat.dom.domain.ProjectRepository;
import com.ofbizian.semat.dom.domain.State;
import com.ofbizian.semat.dom.domain.StateRepository;
import com.ofbizian.semat.fixture.scenarios.AbstractFixtureScript;

public class AlphaStateCreate extends AbstractFixtureScript {
    private String name;
    private String description;
    private Alpha alpha;
    private boolean achieved;
    private int sequence;
    private State state;
    private AlphaState alphaState;
    private AlphaType alphaType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Alpha getAlpha() {
        return alpha;
    }

    public void setAlpha(Alpha alpha) {
        this.alpha = alpha;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public AlphaState getAlphaState() {
        return alphaState;
    }

    public void setAlphaState(AlphaState alphaState) {
        this.alphaState = alphaState;
    }

    public AlphaType getAlphaType() {
        return alphaType;
    }

    public void setAlphaType(AlphaType alphaType) {
        this.alphaType = alphaType;
    }

    @Override
    protected void doExecute(final ExecutionContext ec) {
        String name = checkParam("name", ec, String.class);
        String description = checkParam("description", ec, String.class);
        Alpha alpha = checkParam("alpha", ec, Alpha.class);
        AlphaType alphaType = checkParam("alphaType", ec, AlphaType.class);
        boolean achieved = checkParam("achieved", ec, Boolean.class);
        int sequence = checkParam("sequence", ec, Integer.class);

        this.state = wrap(repository).createState(name, description, alphaType);
//        this.alphaState = wrap(repository).createAlphaState(alpha, state, achieved, sequence);

        ec.addResult(this, state);
        ec.addResult(this, alphaState);
    }

    @javax.inject.Inject
    private StateRepository repository;

}
