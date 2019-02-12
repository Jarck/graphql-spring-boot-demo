package com.hello.world.web.rest;

import com.github.pagehelper.PageInfo;
import com.hello.world.constant.CommonStatus;
import com.hello.world.constant.ResponseMessage;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.condition.SearchCompanyDto;
import com.hello.world.dto.create.CreateCompanyDto;
import com.hello.world.dto.edit.EditCompanyDto;
import com.hello.world.dto.result.CompanyDto;
import com.hello.world.service.ICompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jarck-lou
 * @date 2019/02/12 15:33
 **/
@Slf4j
@RestController
@Api(value = "Rest公司", description = "Rest公司")
@RequestMapping("api/company")
public class CompanyController extends BaseController {
  @Autowired
  private ICompanyService companyService;

  /**
   * 查询公司列表
   *
   * @param searchCompanyDto 查询条件
   * @param pageDto 分页信息
   * @return ResponseBean
   */
  @ApiOperation("")
  @GetMapping("")
  @RequiresPermissions("company:read")
  public ResponseBean list(SearchCompanyDto searchCompanyDto, PageDto pageDto) {
    PageInfo<CompanyDto> companyDtoList = companyService.searchCondition(searchCompanyDto, pageDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, companyDtoList);
  }

  /**
   * 获取公司详细信息
   *
   * @param id 公司ID
   * @return ResponseBean
   */
  @ApiOperation("")
  @GetMapping("{id}")
  @RequiresPermissions("company:read")
  public ResponseBean show(@PathVariable long id) {
    CompanyDto companyDto = companyService.searchWithId(id);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, companyDto);
  }

  /**
   * 创建公司
   *
   * @param createCompanyDto 公司信息
   * @param bindingResult 校验对象
   * @return ResponseBean
   */
  @ApiOperation("")
  @PostMapping("")
  @RequiresPermissions("company:create")
  public ResponseBean create(@Validated CreateCompanyDto createCompanyDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return validateError(bindingResult);
    }

    CompanyDto companyDto = companyService.createCompany(createCompanyDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, companyDto);
  }

  /**
   * 更新公司
   *
   * @param editCompanyDto 公司信息
   * @return ResponseBean
   * @throws NotFoundException notFoundException
   */
  @ApiOperation("")
  @PutMapping("")
  @RequiresPermissions("company:edit")
  public ResponseBean update(@RequestBody EditCompanyDto editCompanyDto) throws NotFoundException {
    CompanyDto companyDto = companyService.updateCompany(editCompanyDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, companyDto);
  }
}
