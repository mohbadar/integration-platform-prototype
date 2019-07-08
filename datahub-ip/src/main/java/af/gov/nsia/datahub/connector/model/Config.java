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

/**
 *
 * @author hp 2018
 */
@Entity(name = "Config")
@Table(name = "config")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Config{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_generator")
    @SequenceGenerator(name = "config_generator", sequenceName = "config_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Column(unique = true, name = "param")
    private int param;

    @Column(name = "value")
    private String value;
}
