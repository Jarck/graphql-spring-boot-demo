package com.hello.world.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.world.Application;
import com.hello.world.dto.edit.EditRoleDto;
import com.hello.world.enums.RoleStatusEnum;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jarck-lou
 * @date 2019/02/01 16:50
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleControllerTest extends BaseMock {

  private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

  @Before
  public void setUp() {
    Operation operation = Operations.sequenceOf(
            Operations.deleteAllFrom("role_permission"),
            Operations.insertInto("role_permission")
                    .columns("role_id", "permission_id")
                    .values(1, 5)
                    .values(1, 6)
                    .values(1, 7)
                    .values(1, 8)
                    .build()
    );

    DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
    dbSetupTracker.launchIfNecessary(dbSetup);
  }

  @Test
  @Transactional
  public void testList() throws Exception {
    mockMvc.perform(get("/api/roles").header("auth-token", token))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testShow() throws Exception {
    mockMvc.perform(get("/api/roles/1").header("auth-token", token))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath("$.data.name").value("admin"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testGetRolePermissions() throws Exception {
    mockMvc.perform(get("/api/roles/1/permissions").header("auth-token", token))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath("$.data.name").value("admin"))
            .andExpect(jsonPath("$.data.permissions").isArray())
            .andReturn();
  }

  @Test
  @Transactional
  public void testCreate() throws Exception {
    mockMvc.perform(post("/api/roles").header("auth-token", token)
            .param("name", "test2").param("remark", "测试新建"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath(".data.name").value("test2"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testCreateByNameExist() throws Exception {
    mockMvc.perform(post("/api/roles").header("auth-token", token)
            .param("name", "test").param("remark", "测试重名"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("400"))
            .andExpect(jsonPath("msg").value("请求参数错误"))
            .andExpect(jsonPath("$.data[0].name").value("角色名称已存在"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testUpdate() throws Exception {
    EditRoleDto editRoleDto = new EditRoleDto();
    editRoleDto.setId(1L);
    editRoleDto.setName("admin-update");
    editRoleDto.setStatus(RoleStatusEnum.ARCHIVED);

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(editRoleDto);

    mockMvc.perform(put("/api/roles").header("auth-token", token)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonInString))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("code").value("200"))
            .andExpect(jsonPath("msg").value("success"))
            .andExpect(jsonPath("$.data.name").value("admin-update"))
            .andExpect(jsonPath("$.data.status").value("ARCHIVED"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testUpdateRolePermissions() throws Exception {
    List<Long> permissionIds = new ArrayList<>();
    permissionIds.add(1L);
    permissionIds.add(2L);

    EditRoleDto editRoleDto = new EditRoleDto();
    editRoleDto.setId(1L);
    editRoleDto.setPermissionIds(permissionIds);

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(editRoleDto);

    mockMvc.perform(put("/api/roles/updateRolePermissions").header("auth-token", token)
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
  public void testUpdateRolePermissionsByRoleNotExist() throws Exception {
    List<Long> permissionIds = new ArrayList<>();
    permissionIds.add(1L);
    permissionIds.add(2L);

    EditRoleDto editRoleDto = new EditRoleDto();
    editRoleDto.setId(9999999L);
    editRoleDto.setPermissionIds(permissionIds);

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(editRoleDto);

    mockMvc.perform(put("/api/roles/updateRolePermissions").header("auth-token", token)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonInString))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("code").value("500"))
            .andExpect(jsonPath("msg").value("角色不存在"))
            .andReturn();
  }

  @Test
  @Transactional
  public void testUpdateRolePermissionsByPermissionNotExist() throws Exception {
    List<Long> permissionIds = new ArrayList<>();
    permissionIds.add(1L);
    permissionIds.add(9999999L);

    EditRoleDto editRoleDto = new EditRoleDto();
    editRoleDto.setId(1L);
    editRoleDto.setPermissionIds(permissionIds);

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(editRoleDto);

    mockMvc.perform(put("/api/roles/updateRolePermissions").header("auth-token", token)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(jsonInString))
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("code").value("500"))
            .andExpect(jsonPath("msg").value("权限不存在"))
            .andReturn();
  }
}
