package com.devnatao.springrestfulapicourse.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.devnatao.springrestfulapicourse.data.vo.v2.PersonVOV2;
import com.devnatao.springrestfulapicourse.model.Person;

@Service
public class PersonMapper {
	
	public PersonVOV2 convertEntityToVo(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		vo.setAdress(person.getAdress());
		vo.setBirthDay(new Date());
		return vo;
	}
	
	public Person convertVoToEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAdress(person.getAdress());
		//vo.setBirthDay(new Date());
		return entity;
	}
}
