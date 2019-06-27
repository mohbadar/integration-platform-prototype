/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.repository;

import af.gov.nsia.datahub.connector.model.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp 2018
 */
@Repository 
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {  
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}