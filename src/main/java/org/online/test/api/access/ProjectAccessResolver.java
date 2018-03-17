package org.online.test.api.access;

import org.online.test.api.key.ProjectKey;
import org.online.test.api.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Abhishek
 */
@Component
public class ProjectAccessResolver implements AccessResolver<ProjectKey> {

  @Autowired
  private ProjectService projectService;

  @Override
  public boolean check(ProjectKey authKey) {
    return projectService.checkProjectUserAccess(authKey.getId(), authKey.getUserId());
  }
}
