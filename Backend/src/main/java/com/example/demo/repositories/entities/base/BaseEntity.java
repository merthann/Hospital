package com.example.demo.repositories.entities.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter

public class BaseEntity {
	@JsonIgnore
	boolean isDeleted = false;
}