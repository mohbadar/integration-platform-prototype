/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.odkclient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class OdkForm {

    private String xmlFormId;

    private String name;

    private String description;

    private boolean active;

    private boolean showOnMap;
    private String xmlContent;

    private String envId;

//    @JsonIgnore
//    private Collection<Group> groups;
    // This field is not mapped to database
//    @Transient
//    private Long instanceCount;
//    @Column(name = "created_at")
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
//
//    @OneToMany(mappedBy = "form", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Collection<Instance> instances;
    public static Object newBuilder() {
        return new OdkForm();
    }
}
