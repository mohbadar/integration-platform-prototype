/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hp 2018
 */
@Entity(name = "ConfirmationToken")
@Table(name = "confirmation_token")
@Data
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ConfirmationToken  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_generator")
    @SequenceGenerator(name = "confirmation_token_generator", sequenceName = "confirmation_token_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

}
