package com.techgen.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ResponseDTO<T extends Object> {

	private Integer pageNo;
	private Integer pageSize;
	private Integer totalPages;
	private Long totalElements;
	private Boolean last;
	private String message;
	private T body;

	public ResponseDTO(String message, T body) {
		super();
		this.message = message;
		this.body = body;
	}

}
