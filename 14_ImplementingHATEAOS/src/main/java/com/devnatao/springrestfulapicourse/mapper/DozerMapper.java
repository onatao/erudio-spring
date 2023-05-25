package com.devnatao.springrestfulapicourse.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	/**
	 * Convert a simple object from Origin class to Destination class
	 * (example: UserRequest (origin class) to UserDTO (destination class).
	 * @param <O> Origin class - example: UserRequest
	 * @param <D> Destination class - example: UserDTO
	 * @param origin - instance name
	 * @param destination - instance name
	 * @return One object converted from Origin class to Destination class
	 */
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	/**
	 * Convert a list of objects from Origin class to Destination class
	 * (example: List<UserRequest> (origin class) to List<UserDTO> (destination class).
	 * @param <O> Origin class - example: UserRequest
	 * @param <D> Destination class - example: UserDTO
	 * @param origin - instance name
	 * @param destination - instance name
	 * @return a list of Origin class converted to Destination class
	 */
	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for (O o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		return destinationObjects;
	}
}
