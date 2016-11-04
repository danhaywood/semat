package com.ofbizian.semat.fixture.dom;

import com.ofbizian.semat.dom.domain.Project;
import com.ofbizian.semat.dom.domain.ProjectMenu;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class ProjectCreate extends FixtureScript {
    private String code;
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Project project;

    public Project getProject() {
        return project;
    }

    @Override
    protected void execute(final ExecutionContext ec) {
        String name = checkParam("name", ec, String.class);
        String code = checkParam("code", ec, String.class);
        String description = checkParam("description", ec, String.class);

        this.project = wrap(menu).newProject(code, name, description);
        ec.addResult(this, project);
    }

    @javax.inject.Inject
    private ProjectMenu menu;

}
