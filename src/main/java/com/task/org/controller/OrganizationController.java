package com.task.org.controller;

import com.task.org.dto.organization.OrganizationCreateDTO;
import com.task.org.dto.organization.OrganizationDTO;
import com.task.org.dto.organization.OrganizationDetailDTO;
import com.task.org.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations() {
        return new ResponseEntity<>(organizationService.getAllOrganizations(), HttpStatus.OK);
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<OrganizationDetailDTO> getOrganization(@PathVariable Long orgId) {
        return new ResponseEntity<>(organizationService.getOrganization(orgId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addOrganization(@RequestBody OrganizationCreateDTO organizationCreateDTO) {
        organizationService.addOrganization(organizationCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{orgId}")
    public ResponseEntity<Void> updateOrganization(@PathVariable Long orgId, @RequestBody OrganizationDTO organizationDTO) {
        organizationService.updateOrganization(organizationDTO, orgId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{orgId}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long orgId) {
        organizationService.deleteOrganization(orgId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
