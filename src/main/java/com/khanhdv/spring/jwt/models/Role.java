package com.khanhdv.spring.jwt.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity<Long> implements Serializable {

	@Column(length = 20)
	private String name;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "roles_permission",
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private List<Permission> permissions;

}