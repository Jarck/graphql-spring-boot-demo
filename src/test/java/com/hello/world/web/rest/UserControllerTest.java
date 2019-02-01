package com.hello.world.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.world.Application;
import com.hello.world.dto.edit.EditUserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jarck-lou
 * @date 2019/01/08 17:01
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends BaseMock {

  @Test
  @Transactional
  public void testList() throws Exception {
    mockMvc.perform(get("/api/users").header("auth-token", token))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath("$.data.size").value(2))
            .andReturn();
  }

  @Test
  @Transactional
  public void testShow() throws Exception {
    mockMvc.perform(get("/api/users/1").header("auth-token", token))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath("$.data.name").value("admin"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testCreate() throws Exception {
    mockMvc.perform(post("/api/users").header("auth-token", token)
            .param("name", "test2").param("phone", "18812345673"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath("$.data.name").value("test2"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testCreateByPhoneExist() throws Exception {
    mockMvc.perform(post("/api/users").header("auth-token", token)
            .param("name", "test2").param("phone", "18812345671"))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("msg").value("用户已存在"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testUpdate() throws Exception {
    EditUserDto editUserDto = new EditUserDto();
    editUserDto.setId(1L);
    editUserDto.setName("test-update");

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(editUserDto);

    mockMvc.perform(put("/api/users").header("auth-token", token)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonInString))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.name").value("test-update"))
            .andReturn();
  }
}
