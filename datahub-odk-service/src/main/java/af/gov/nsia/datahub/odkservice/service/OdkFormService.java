/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.odkservice.service;

import af.gov.nsia.datahub.odkservice.cassandra.model.OdkForm;
import af.gov.nsia.datahub.odkservice.cassandra.model.OdkFormById;
import af.gov.nsia.datahub.odkservice.cassandra.model.OdkFormByName;
import af.gov.nsia.datahub.odkservice.cassandra.model.OdkFormByStatus;
import af.gov.nsia.datahub.odkservice.cassandra.model.key.OdkFormByIdKey;
import af.gov.nsia.datahub.odkservice.cassandra.model.key.OdkFormByNameKey;
import af.gov.nsia.datahub.odkservice.cassandra.model.key.OdkFormByStatusKey;
import af.gov.nsia.datahub.odkservice.repository.OdkFormByIdRepository;
import af.gov.nsia.datahub.odkservice.repository.OdkFormByNameRepository;
import af.gov.nsia.datahub.odkservice.repository.OdkFormByStatusRepository;
import af.gov.nsia.datahub.odkservice.repository.OdkFormRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp 2018
 */
@Service
public class OdkFormService extends SimpleCassandraRepository<OdkForm, UUID>
        implements OdkFormRepository {

    private  CassandraTemplate cassandraTemplate;
    private  OdkFormByIdRepository odkFormByIdRepository;
    private  OdkFormByNameRepository odkFormByNameRepository;
    private  OdkFormByStatusRepository odkFormByStatusRepository;

    @Autowired
    public OdkFormService(
             CassandraEntityInformation<OdkForm, UUID> metadata,
             CassandraTemplate cassandraTemplate,
             OdkFormByIdRepository odkFormByIdRepository,
             OdkFormByNameRepository odkFormByNameRepository,
             OdkFormByStatusRepository odkFormByStatusRepository
    ) {
        super(metadata, cassandraTemplate);
        this.cassandraTemplate = cassandraTemplate;
        this.odkFormByIdRepository = odkFormByIdRepository;
        this.odkFormByNameRepository = odkFormByNameRepository;
        this.odkFormByStatusRepository = odkFormByStatusRepository;
    }

    @Override
    public <S extends OdkForm> S insert(final S form) {
        form.setId(UUID.randomUUID());
        final CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
        insertById(form, batchOps);
        insertByName(form, batchOps);
        insertByStatus(form, batchOps);
        batchOps.insert(form);
        batchOps.execute();
        return form;
    }

    private <S extends OdkForm> void insertById(S form, CassandraBatchOperations batchOps) {

        batchOps.insert(
                new OdkFormById(
                        new OdkFormByIdKey(
                                form.getXmlFormId(),
                                form.getId()
                        ),
                        form.getName(),
                        form.getDescription(),
                        form.isStatus(),
                        form.isShowOnMap(),
                        form.getXmlContent(),
                        form.getEnvId()
                )
        );
    }

    private <S extends OdkForm> void insertByName(S form, CassandraBatchOperations batchOps) {
        batchOps.insert(
                new OdkFormByName(
                        new OdkFormByNameKey(
                                form.getName(),
                                form.getId()
                        ),
                        form.getName(),
                        form.getDescription(),
                        form.isStatus(),
                        form.isShowOnMap(),
                        form.getXmlContent(),
                        form.getEnvId()));
    }

    private <S extends OdkForm> void insertByStatus(S form, CassandraBatchOperations batchOps) {
        batchOps.insert(
                new OdkFormByStatus(
                        new OdkFormByStatusKey(form.isStatus(), form.getId()),
                        form.getXmlFormId(),
                        form.getName(),
                        form.getDescription(),
                        form.isShowOnMap(),
                        form.getXmlContent(),
                        form.getEnvId()
                )
        );
    }

    @Override
    public void delete(final OdkForm form) {
        final CassandraBatchOperations batchOps = cassandraTemplate.batchOps();

        deleteById(form, batchOps);
        deleteByName(form, batchOps);
        deleteByStatus(form, batchOps);

        batchOps.delete(form);
        batchOps.execute();

    }

    private void deleteById(OdkForm form, CassandraBatchOperations batchOps) {
        batchOps.delete(
                odkFormByIdRepository.findByKeyFormId(
                        form.getXmlFormId()
                )
        );

    }

    private void deleteByName(OdkForm form, CassandraBatchOperations batchOps) {

        batchOps.delete(
                odkFormByNameRepository.findByKeyNameAndKeyFormId(
                        form.getName(),
                        form.getId()
                )
        );

        batchOps.delete(
                odkFormByNameRepository.findByKeyFormId(
                        form.getId()
                )
        );
    }

    private void deleteByStatus(OdkForm form, CassandraBatchOperations batchOps) {

        batchOps.delete(
                odkFormByStatusRepository.findByKeyStatusAndKeyFormId(
                        form.isStatus(),
                        form.getId()
                )
        );

        batchOps.delete(
                odkFormByStatusRepository.findByKeyFormId(
                        form.getId()
                )
        );
    }
    
    
    
}
