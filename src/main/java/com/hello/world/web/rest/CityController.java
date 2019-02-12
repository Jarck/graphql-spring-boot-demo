package com.hello.world.web.rest;

import com.github.pagehelper.PageInfo;
import com.hello.world.constant.CommonStatus;
import com.hello.world.constant.ResponseMessage;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.condition.SearchCityDto;
import com.hello.world.dto.create.CreateCityDto;
import com.hello.world.dto.edit.EditCityDto;
import com.hello.world.dto.result.CityDto;
import com.hello.world.exception.ArgumentsException;
import com.hello.world.service.ICityService;
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
 * @date 2019/02/12 09:37
 **/
@Slf4j
@RestController
@Api(value = "Rest城市", description = "Rest城市")
@RequestMapping("api/city")
public class CityController extends  BaseController {
  @Autowired
  private ICityService cityService;

  /**
   * 查询城市列表
   *
   * @param searchCityDto 查询条件
   * @param pageDto 分页信息
   * @return ResponseBean
   */
  @ApiOperation("")
  @GetMapping("")
  @RequiresPermissions("city:read")
  public ResponseBean list(SearchCityDto searchCityDto, PageDto pageDto) {
    PageInfo<CityDto> cityDtoPageDto = cityService.searchWithCondition(searchCityDto, pageDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, cityDtoPageDto);
  }

  /**
   * 获取城市详细信息
   *
   * @param id 城市ID
   * @return ResponseBean
   */
  @ApiOperation("")
  @GetMapping("{id}")
  @RequiresPermissions("city:read")
  public ResponseBean show(@PathVariable Long id) {
    CityDto cityDto = cityService.searchWithId(id);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, cityDto);
  }

  /**
   * 创建城市
   *
   * @param createCityDto 城市信息
   * @param bindingResult 校验对象
   * @return ResponseBean
   * @throws ArgumentsException 参数异常
   */
  @ApiOperation("")
  @PostMapping("")
  @RequiresPermissions("city:create")
  public ResponseBean create(@Validated CreateCityDto createCityDto, BindingResult bindingResult)
          throws ArgumentsException {
    if (bindingResult.hasErrors()) {
      return validateError(bindingResult);
    }

    CityDto cityDto = cityService.createCity(createCityDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, cityDto);
  }

  /**
   * 更新城市
   *
   * @param editCityDto 城市信息
   * @return ResponseBean
   * @throws NotFoundException notFoundException
   */
  @PutMapping("")
  @RequiresPermissions("city:edit")
  public ResponseBean update(@RequestBody EditCityDto editCityDto) throws NotFoundException {
    CityDto cityDto = cityService.updateCity(editCityDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, cityDto);
  }
}
