/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.service;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.model.ConnectorTemplate;
import af.gov.nsia.datahub.connector.model.DefaultConnector;
import af.gov.nsia.datahub.connector.repository.ConnectorTemplateRepository;
import af.gov.nsia.datahub.connector.repository.DefaultConnectorRepository;
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
public class DefaultConnectorService {
    
    @Autowired
    private DefaultConnectorRepository repository;

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public DefaultConnector findByName(String name) {
        return repository.findByName(name);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public void delete(DefaultConnector connector) {
        repository.delete(connector);
    }
    
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public void delete(Long id) {
        repository.deleteById(id);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<DefaultConnector> findAll() {
        return repository.findAll();
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<DefaultConnector> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<DefaultConnector> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public DefaultConnector getOne(Long id) {
        return repository.getOne(id);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public <S extends DefaultConnector> S save(S entity) {
        return repository.save(entity);
    }
    
}
