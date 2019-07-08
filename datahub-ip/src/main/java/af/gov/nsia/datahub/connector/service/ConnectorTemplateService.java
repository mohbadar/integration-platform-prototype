/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.service;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.model.ConnectorTemplate;
import af.gov.nsia.datahub.connector.model.Role;
import af.gov.nsia.datahub.connector.repository.ConnectorTemplateRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 *
 * @author hashimi
 */
@Service
@Slf4j
public class ConnectorTemplateService {

    @Autowired
    private ConnectorTemplateRepository repository;

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public ConnectorTemplate findByName(String name) {
        return repository.findByName(name);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public void delete(ConnectorTemplate template) {
        repository.delete(template);
    }

    @Loggable
    public List<ConnectorTemplate> findAll() {
        return repository.findAll();
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<ConnectorTemplate> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<ConnectorTemplate> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public ConnectorTemplate getOne(Long id) {
        return repository.getOne(id);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public <S extends ConnectorTemplate> S save(S entity) {
        return repository.save(entity);
    }

}
