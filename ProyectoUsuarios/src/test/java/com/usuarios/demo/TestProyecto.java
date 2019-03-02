package com.usuarios.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class TestProyecto  extends ProyectoUsuariosApplicationTests{
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
	public void testNewUser() throws Exception {
		this.mockMvc.perform(post("/newUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                		"{\"id\":0,\"firstName\":\"Ana\",\"lastName\":\"Lora\",\"dateOfBirth\":\"11-11-1991\"}"
                		)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.firstName").value("Ana"))
                .andExpect(jsonPath("$.lastName").value("Lora"))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(status().isOk());
	}
	
	
	@Test
	public void testGetAllUsers() throws Exception {
		this.mockMvc.perform(post("/newUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":0,\"firstName\":\"Maria\",\"lastName\":\"Flores\",\"dateOfBirth\":\"11-11-1999\"}")
                .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(post("/newUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":0,\"firstName\":\"Jose\",\"lastName\":\"Soto\",\"dateOfBirth\":\"01-02-2003\"}")
                .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(post("/newUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":0,\"firstName\":\"Sara\",\"lastName\":\"Vela\",\"dateOfBirth\":\"01-02-2003\"}")
                .accept(MediaType.APPLICATION_JSON));
		
		
		mockMvc.perform(get("/getAllUsers"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andDo(print())
			;

	}
	
	
	
	@Test
	public void testGetUserById() throws Exception {
		mockMvc.perform(get("/getUserById?id=1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andDo(print())
		;
	}
	
	
	
	
	@Test
	public void testUserUpdate() throws Exception {
		this.mockMvc.perform(post("/updateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                		"{\"id\":2,\"firstName\":\"oooooooo\",\"lastName\":\"iiiiiii\",\"dateOfBirth\":\"11-11-1991\"}"
                		)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.user.firstName").value("oooooooo"))
                .andExpect(jsonPath("$.user.lastName").value("iiiiiii"))
                .andExpect(jsonPath("$.user.status").value("ACTIVE"))
                .andExpect(status().isOk());
	}

	
	
	@Test
	public void testUserDelete() throws Exception {
		mockMvc.perform(delete("/deleteUser?id=3"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andDo(print())
		;
	}
}
