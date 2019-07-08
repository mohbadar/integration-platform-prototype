/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

/**
 *
 * @author hashimi
 */
@Entity(name = "DefaultConnector")
@Table(name = "default_connector")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class DefaultConnector {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_connector_generator")
    @SequenceGenerator(name = "default_connector_generator", sequenceName = "default_connector_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(
            length = 128,
            name = "name",
            unique = true
    )
    private String name;

    @Column(
            length = 256,
            name = "config"
    )
    @Type(type = "text")
    private String config;

}
