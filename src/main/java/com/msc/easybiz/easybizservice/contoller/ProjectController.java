package com.msc.easybiz.easybizservice.contoller;

import com.msc.easybiz.easybizservice.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("/project")
@RequestMapping(path = "/management/project", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProjectController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getProjects() {
        logger.info("Request to get all projects");
        return new ResponseEntity<>(projectService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProject(@PathVariable("id") String projectId) {
        logger.info("Request to get project details for id : {}", projectId);
        return new ResponseEntity<>(projectService.get(projectId), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> addProject(@RequestBody String body) {
        logger.info("Request to add a new project");
        Map<String,String> data = util.getData(body);
        return new ResponseEntity<>(projectService.insert(data.get("project_code"), data.get("project_name"),data.get("description"),data.get("budget"),data.get("is_active"), data.get("product_code")), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") String projectId, @RequestBody String body) {
        logger.info("Request to update project details for id : {}", projectId);
        Map<String, String> data = util.getData(body);
        return new ResponseEntity<>(projectService.update(data.get("project_name"),data.get("description"),data.get("budget"), projectId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") String projectId) {
        logger.info("Request to delete product with code: {}", projectId);
        projectService.delete(projectId);
        return new ResponseEntity<>(projectService.getAll(), HttpStatus.OK);
    }
}
