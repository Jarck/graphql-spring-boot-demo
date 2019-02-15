package com.hello.world.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.world.Application;
import com.hello.world.dto.create.CreatePermissionDto;
import com.hello.world.dto.edit.EditPermissionDto;
import com.hello.world.enums.PermissionAvailableEnum;
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
 * @date 2019/02/02 14:46
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PermissionControllerTest extends BaseMock {
  private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

  @Before
  public void setUp() {
    Operation operation = Operations.sequenceOf(
            Operations.deleteAllFrom("role_permission"),
            Operations.insertInto("role_permission")
                    .columns("role_id", "permission_id")
                    .values(1, 9)
                    .values(1, 10)
                    .values(1, 11)
                    .values(1, 12)
                    .build()
    );

    DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
    dbSetupTracker.launchIfNecessary(dbSetup);
  }

  @Test
  @Transactional
  public void testList() throws Exception {
    mockMvc.perform(get("/api/permissions").header("auth-token", token))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testCreate() throws Exception {
    CreatePermissionDto createPermissionDto = new CreatePermissionDto();
    createPermissionDto.setName("创建权限test");
    createPermissionDto.setPermission("permission-create:create");
    createPermissionDto.setResourceType("create");

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(createPermissionDto);

    mockMvc.perform(post("/api/permissions").header("auth-token", token)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonInString))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testCreateByNameExist() throws Exception {
    CreatePermissionDto createPermissionDto = new CreatePermissionDto();
    createPermissionDto.setName("读取用户");
    createPermissionDto.setPermission("user:read2");
    createPermissionDto.setResourceType("read");

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(createPermissionDto);

    mockMvc.perform(post("/api/permissions").header("auth-token", token)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonInString))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("code").value("500"))
            .andExpect(jsonPath("msg").value("权限已存在"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testUpdate() throws Exception {
    EditPermissionDto editPermissionDto = new EditPermissionDto();
    editPermissionDto.setId(1L);
    editPermissionDto.setName("读取用户update");
    editPermissionDto.setAvailable(PermissionAvailableEnum.UNAVAILABLE);

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(editPermissionDto);

    mockMvc.perform(put("/api/permissions").header("auth-token", token)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonInString))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath("$.data.name").value("读取用户update"))
            .andExpect(jsonPath("$.data.available").value("UNAVAILABLE"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testUpdateByPermissionNotExist() throws Exception {
    EditPermissionDto editPermissionDto = new EditPermissionDto();
    editPermissionDto.setId(9999999L);
    editPermissionDto.setName("读取用户update");

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(editPermissionDto);

    mockMvc.perform(put("/api/permissions").header("auth-token", token)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonInString))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("code").value("500"))
            .andExpect(jsonPath("msg").value("权限不存在"))
            .andReturn();
  }
}
