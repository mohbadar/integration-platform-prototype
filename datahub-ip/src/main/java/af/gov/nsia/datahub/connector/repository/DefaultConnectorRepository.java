/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.repository;

import af.gov.nsia.datahub.connector.model.DefaultConnector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hashimi
 */
@Repository
public interface DefaultConnectorRepository extends JpaRepository<DefaultConnector, Long> {

    DefaultConnector findByName(String name);

}
