package org.online.test.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static org.online.test.api.utils.AppConstants.FIELDS;
import static org.online.test.api.utils.AppConstants.LIMIT;
import static org.online.test.api.utils.AppConstants.PAGE;
import static org.online.test.api.utils.AppConstants.SORT_BY;
import static org.online.test.api.utils.AppConstants.SORT_DIR;

import java.util.List;

import org.online.test.api.domain.Project;
import org.online.test.api.domain.message.ResponseMessage;
import org.online.test.api.key.ProjectKey;
import org.online.test.api.service.project.ProjectService;
import org.online.test.api.utils.APIEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Abhishek on 16/7/17.
 */
@RestController
@Api(value = "projects", description = "Project APIs")
@RequestMapping(value = APIEndpoints.PROJECT_API_URL)
public class ProjectController extends BaseController {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(ProjectController.class);

  @Autowired
  private ProjectService projectService;

  /**
   * API to fetch project by projectId.
   */
  @ApiOperation(value = "Fetch project by projectId.",
      notes = "API to retrieve a single organization.", response = ResponseMessage.class)
  @RequestMapping(value = "/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Project> getById(@PathVariable final String projectId,
      @RequestParam(value = FIELDS, required = false) String fields,
      ProjectKey projectKey) {
    LOG.info("Fetch project by projectId {}", projectId);
    return new ResponseEntity<>(
        limitDataFields(projectService.getById(projectId), Project.class, fields),
        HttpStatus.OK);
  }

  /**
   * API to create new project.
   */
  @ApiOperation(value = "Create a project.", notes = "API to create new project.",
      response = Project.class)
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Project> create(@RequestBody Project project) {
    LOG.info("Saving new project with name {}.", project.getName());
    projectService.create(project);
    return new ResponseEntity<>(limitDataFields(project, Project.class), HttpStatus.OK);
  }

  @ApiOperation(value = "Fetch list of projects from an organization.", notes = "Fetch list of projects from an organization.",
      response = Project.class)
  @RequestMapping(value = "/org/{orgId}/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Project>> search(
      @PathVariable String orgId,
      @RequestParam(value = PAGE, required = false) Integer page,
      @RequestParam(value = LIMIT, required = false) Integer limit,
      @RequestParam(value = SORT_DIR, required = false) String sortDir,
      @RequestParam(value = SORT_BY, required = false) String sortBy,
      @RequestParam(value = FIELDS, required = false) String fields) {
    LOG.info("Fetch list of projects.");
    List<Project> projects = projectService.search(orgId, page, limit, sortBy, sortDir);
    return new ResponseEntity<>(
        limitDataFields(projects, Project.class, fields),
        HttpStatus.OK);
  }
}
