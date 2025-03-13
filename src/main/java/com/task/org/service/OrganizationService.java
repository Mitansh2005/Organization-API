package com.task.org.service;

import com.task.org.domain.Organization;
import com.task.org.dto.organization.OrganizationCreateDTO;
import com.task.org.dto.organization.OrganizationDTO;
import com.task.org.dto.organization.OrganizationDetailDTO;
import com.task.org.helpers.organization.OrganizationDetailMapper;
import com.task.org.helpers.organization.OrganizationMapper;
import com.task.org.repository.OrganizationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Transactional
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final OrganizationDetailMapper organizationDetailMapper;
    private static final String ORG_ERROR_REASON="No organization with id:";


    public OrganizationService(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper, OrganizationDetailMapper organizationDetailMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.organizationDetailMapper = organizationDetailMapper;
    }

    public List<OrganizationDTO> getAllOrganizations() {
        return organizationRepository.findAll().stream().map(organizationMapper).toList();
    }

    public OrganizationDetailDTO getOrganization(Long orgId) {
        return organizationRepository.findById(orgId).stream().map(organizationDetailMapper).findFirst().orElseThrow(()->{
            throw new IllegalStateException(ORG_ERROR_REASON+" "+orgId);
        });
    }

    public void addOrganization(OrganizationCreateDTO organizationCreateDTO) {
        Organization organization = new Organization();
        organization.setOrgName(organizationCreateDTO.getOrgName());
        organization.setCreatedTimeStamp(LocalDateTime.now());
        organization.setUpdatedTimeStamp(LocalDateTime.now());
        organizationRepository.save(organization);
    }

    public void updateOrganization(OrganizationDTO organizationDTO, Long orgId) {
        organizationRepository.findById(orgId).ifPresentOrElse(existingOrganization -> {
            existingOrganization.setOrgName(organizationDTO.getOrgName());
            existingOrganization.setUpdatedTimeStamp(LocalDateTime.now());
            organizationRepository.save(existingOrganization);
        },()->{
            throw new IllegalStateException(ORG_ERROR_REASON+" "+orgId);
        });
    }

    public void deleteOrganization(Long orgId) {
        organizationRepository.deleteById(orgId);
    }
}
