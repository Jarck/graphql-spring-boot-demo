package hello.dto.result;

import hello.entity.City;
import lombok.Data;

import java.io.Serializable;

@Data
public class CityDto extends City implements Serializable {
    public CityDto() {

    }

    public CityDto(City city) {
        this.setId(city.getId());
        this.setName(city.getName());
    }
}
