package com.devnatao.springrestfulapicourse.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

// response order
@JsonPropertyOrder({"id", "firstName", "lastName", "adress", "gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Mapping("id") // DozerMapper annotation that rename key for "id" (just for Dozer)
	@JsonProperty(value = "id") // show "id" instead "key" on response
	private Long key;	
	private String firstName;
	private String lastName;	
	private String adress;
	private String gender;
	
	public PersonVO() {
		
	}

	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(key);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVO other = (PersonVO) obj;
		return Objects.equals(key, other.key);
	}
}
