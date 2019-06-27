/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.odkservice.cassandra.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 *
 * @author hp 2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Table("odkforms")
public class OdkForm {

    @PrimaryKeyColumn(name = "odkform_id", type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @Column("xml_form_id")
    private String xmlFormId;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
    @Column("status")
    private boolean status;
    @Column("show_on_map")
    private boolean showOnMap;
    @Column("xml_content")
    private String xmlContent;
    @Column("env_id")
    private String envId;
    
    
}
