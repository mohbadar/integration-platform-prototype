/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.odkservice.repository;

import af.gov.nsia.datahub.odkservice.cassandra.model.OdkForm;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp 2018
 */
@Repository
public interface OdkFormRepository extends CassandraRepository<OdkForm, UUID>{
    
}
