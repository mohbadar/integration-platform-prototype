/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.odkservice.cassandra.model.key;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

/**
 *
 * @author hp 2018
 */
@PrimaryKeyClass
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class OdkFormByIdKey {

    @PrimaryKeyColumn(name = "zml_form_id", type = PrimaryKeyType.PARTITIONED)
    private String xmlFormId;

    @PrimaryKeyColumn(name = "form_id", ordering = Ordering.DESCENDING, ordinal = 1)
    private UUID formId;

}
