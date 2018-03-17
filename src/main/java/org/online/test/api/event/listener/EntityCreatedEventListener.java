package org.online.test.api.event.listener;

import org.online.test.api.domain.Assumption;
import org.online.test.api.domain.Project;
import org.online.test.api.event.EntityCreatedEvent;
import org.online.test.api.service.assumption.ProjectAssumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Send notifications or perform some other actions whenever any new entity is created.
 */
@Component
public class EntityCreatedEventListener implements ApplicationListener<EntityCreatedEvent> {

  @Autowired
  private ProjectAssumptionService projectAssumptionService;

  @Override
  public void onApplicationEvent(EntityCreatedEvent event) {
    if (event.getEntity() != null) {
      if (event.getEntity() instanceof Assumption) {
        projectAssumptionService.copyNewAssumptionInProjects((Assumption) event.getEntity());
      } else if (event.getEntity() instanceof Project) {
        projectAssumptionService.createProjectAssumptions((Project) event.getEntity());
      }
    }
  }
}
