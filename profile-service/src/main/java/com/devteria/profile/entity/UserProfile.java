package com.devteria.profile.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Node("user_profile")// trong neo4j de khai bao 1 entity thi ta dung node (node la 1 nut trong graph)
public class UserProfile {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    String id;

    @Property("userId") // tuong ung voi @Column trong mysql
    String userId;

    String firstName;
    String lastName;
    LocalDate dob;
    String city;
}
