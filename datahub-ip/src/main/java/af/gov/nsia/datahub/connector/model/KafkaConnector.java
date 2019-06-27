/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hp 2018
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class KafkaConnector implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            length = 128,
            name = "name"
    )
    private String name;

    @Column(
            length = 256,
            name = "connectionClass"
    )
    private String connectionClass;

    @Column(
            length = 256,
            name = "connectionUrl"
    )
    private String connectionUrl;

    @Column(
            length = 8,
            name = "tasks_max"
    )
    private Integer tasksMax;

    /**
     * Incrementing,bulk, ... refer to kafka connect documentation
     */
    @Column(
            length = 32,
            name = "mode"
    )
    private String mode;

    @Column(
            length = 256,
            name = "topic_prefix"
    )
    private String topicPrefix;

//    @Column(
//            name = "config"
//    )
//    private Map<String, String> config = new HashMap<>();

}
