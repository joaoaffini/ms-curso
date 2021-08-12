package com.jp.hroauth.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String roleName;
	
//	@ManyToMany
//	private User users;
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(roleName, other.roleName);
	}
	@Override
	public int hashCode() {
		return Objects.hash(roleName);
	}
	
	

}
