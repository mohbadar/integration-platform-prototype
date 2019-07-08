/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.model;

import java.io.Serializable;
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
@Entity(name = "ConnectorTemplate")
@Table(name = "connector_template")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ConnectorTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "connector_template_generator")
    @SequenceGenerator(name = "connector_template_generator", sequenceName = "connector_template_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;



    @Column(
            length = 128,
            name = "name",
            unique = true
    )
    private String name;

    @Column(
            name = "config"
    )
    @Type(type = "text")
    private String config;
}
