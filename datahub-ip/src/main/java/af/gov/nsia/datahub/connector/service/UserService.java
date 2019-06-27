/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.service;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.model.User;
import af.gov.nsia.datahub.connector.repository.UserRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dell
 */
@Transactional
@Slf4j(topic = "KafkaConnectorService")
@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<User> findAll() {
        return repo.findAll();
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public Long count() {
        return repo.count();
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public User getOne(Long id) {
        return repo.getOne(id);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public List<User> findAllById(Iterable<Long> ids) {
        return repo.findAllById(ids);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public void delete(User user) {
        repo.delete(user);
    }

    //@Async
    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public User findByPhone(String email) {
        return repo.findByEmail(email);
    }

}
