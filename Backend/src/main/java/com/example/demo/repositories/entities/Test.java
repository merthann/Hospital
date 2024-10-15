package com.example.demo.repositories.entities;



import com.example.demo.repositories.entities.base.BaseEntity;
import com.example.demo.request.CreateTestRequest;
import com.example.demo.request.UpdateTestRequest;
import com.example.demo.response.CreateTestResponse;
import com.example.demo.response.UpdateTestResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Builder
@Entity
@Data
@Table(name = "Test")
public class Test extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "results")
	private String results;
	
	
	public static CreateTestResponse toDTO(Test test) {
		return new CreateTestResponse(test.getId(),test.getName(), test.getResults());
	}
	
	public static Test fromDTO(CreateTestRequest testDTO) {
		return Test.builder().name(testDTO.name()).results(testDTO.results()).build();
	}
	
	public static UpdateTestResponse UpdatetoDTO(Test test) {
		return new UpdateTestResponse(test.getId(),test.getName(), test.getResults());
	}
	
	public void UpdatefromDTO(UpdateTestRequest testDTO) {
		this.name = testDTO.name();
		this.results = testDTO.results();
	}
}
