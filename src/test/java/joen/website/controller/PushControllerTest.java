package joen.website.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import joen.website.dto.DeviceDto;
import joen.website.dto.DeviceDto.RequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PushControllerTest {
	
	@Autowired
	MockMvc mockMvc;

	@Test
	public void test() throws JsonProcessingException, Exception {
		DeviceDto.RequestDto dto = new DeviceDto.RequestDto();
		ObjectMapper mapper = new ObjectMapper();
		
		MvcResult result = mockMvc.perform(post("/v1.0/token")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println("content -> " + content);
	}

}
