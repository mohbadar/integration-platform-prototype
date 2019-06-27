/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.service;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.model.Privilege;
import af.gov.nsia.datahub.connector.repository.PrivilegeRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public Privilege findByName(String name) {
        return privilegeRepository.findByName(name);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public void delete(Privilege entity) {
        privilegeRepository.delete(entity);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<Privilege> findAll() {
        return privilegeRepository.findAll();
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<Privilege> findAll(Sort sort) {
        return privilegeRepository.findAll(sort);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<Privilege> findAllById(Iterable<Long> ids) {
        return privilegeRepository.findAllById(ids);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public Privilege getOne(Long id) {
        return privilegeRepository.getOne(id);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public <S extends Privilege> S save(S entity) {
        return privilegeRepository.save(entity);
    }

}
