/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.service;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.model.Role;
import af.gov.nsia.datahub.connector.repository.RoleRepository;
import java.util.List;
import java.util.Optional;
import javafx.collections.transformation.FilteredList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dell
 */
@Transactional
@Slf4j()
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<Role> findAll(Sort sort) {
        return roleRepository.findAll(sort);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<Role> findAllById(Iterable<Long> ids) {
        return roleRepository.findAllById(ids);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public Role getOne(Long id) {
        return roleRepository.getOne(id);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public <S extends Role> S save(S entity) {
        return roleRepository.save(entity);
    }

}
