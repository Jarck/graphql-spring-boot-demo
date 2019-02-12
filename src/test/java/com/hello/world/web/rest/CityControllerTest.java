package com.hello.world.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.world.Application;
import com.hello.world.dto.edit.EditCityDto;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
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
 * @date 2019/02/12 10:19
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerTest extends BaseMock {
  private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

  @Before
  public void setUp() {
    Operation operation = Operations.sequenceOf(
            Operations.deleteAllFrom("role_permission"),
            Operations.insertInto("role_permission")
                    .columns("role_id", "permission_id")
                    .values(1, 13)
                    .values(1, 14)
                    .values(1, 15)
                    .values(1, 16)
                    .build()
    );

    DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
    dbSetupTracker.launchIfNecessary(dbSetup);
  }

  @Test
  @Transactional
  public void testList() throws Exception {
    mockMvc.perform(get("/api/city").header("auth-token", token))
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
    mockMvc.perform(get("/api/city/1").header("auth-token", token))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath("$.data.name").value("杭州"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testShowByNotExist() throws Exception {
    mockMvc.perform(get("/api/city/9999999").header("auth-token", token))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath("data").isEmpty())
            .andReturn();
  }

  @Test
  @Transactional
  public void testCreate() throws Exception {
    mockMvc.perform(post("/api/city").header("auth-token", token)
            .param("name", "上海"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath("$.data.name").value("上海"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testCreateByNameExist() throws Exception {
    mockMvc.perform(post("/api/city").header("auth-token", token)
            .param("name", "杭州"))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("code").value("500"))
            .andExpect(jsonPath("msg").value("城市已存在"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testUpdate() throws Exception {
    EditCityDto editCityDto = new EditCityDto();
    editCityDto.setId(1L);
    editCityDto.setName("杭州update");

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(editCityDto);

    mockMvc.perform(put("/api/city").header("auth-token", token)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonInString))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath("$.data.name").value("杭州update"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testUpdateByCityNotExist() throws Exception {
    EditCityDto editCityDto = new EditCityDto();
    editCityDto.setId(9999999L);
    editCityDto.setName("cityNotExist");

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(editCityDto);

    mockMvc.perform(put("/api/city").header("auth-token", token)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonInString))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("code").value("500"))
            .andExpect(jsonPath("msg").value("城市不存在"))
            .andReturn();
  }
}


