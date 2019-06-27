/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.odkservice.repository;

import af.gov.nsia.datahub.odkservice.cassandra.model.OdkFormByName;
import af.gov.nsia.datahub.odkservice.cassandra.model.key.OdkFormByNameKey;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp 2018
 */
@Repository
public interface OdkFormByNameRepository extends CassandraRepository<OdkFormByName, OdkFormByNameKey>{
    
    List<OdkFormByName> findByKeyName(String name);
    
    OdkFormByName findByKeyNameAndKeyFormId(String name, UUID formId);
    
    OdkFormByName findByKeyFormId(UUID formId);
}
