package com.hello.world.dto.result;

import com.hello.world.entity.City;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 城市DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CityDto extends City {
}
