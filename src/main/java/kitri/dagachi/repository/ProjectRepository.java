package kitri.dagachi.repository;

import antlr.collections.List;
import kitri.dagachi.model.Project;

import javax.sql.DataSource;

public class ProjectRepository {

    private final DataSource dataSource;

    public ProjectRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }



}
