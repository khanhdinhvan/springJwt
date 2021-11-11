package com.khanhdv.spring.jwt.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission extends BaseEntity<Long> implements Serializable {

    @Column(length = 20)
    private String url;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
