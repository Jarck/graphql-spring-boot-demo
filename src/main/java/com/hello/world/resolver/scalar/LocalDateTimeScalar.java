package com.hello.world.resolver.scalar;

import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author jarck-lou
 * @date 2018/9/13 10:40
 **/
@Component
public class LocalDateTimeScalar extends GraphQLScalarType {
  public LocalDateTimeScalar() {
    super("LocalDateTime", "LocalDateTime", new Coercing<LocalDateTime, String>() {
      @Override
      public String serialize(Object input) throws CoercingSerializeException {
        if (input instanceof Date) {
          ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get("CTT"));
          LocalDateTime localDateTime = ((Date)input).toInstant().atZone(zoneId).toLocalDateTime();
          return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } else if (input instanceof LocalDateTime) {
          return ((LocalDateTime)input).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } else {
          LocalDateTime localDateTime = convertImpl(input);

          if (localDateTime == null) {
            throw new CoercingSerializeException("Invalid value '" + input + "' for LocalDateTime");
          }

          return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
      }

      @Override
      public LocalDateTime parseValue(Object input) throws CoercingParseValueException {
        LocalDateTime localDateTime = convertImpl(input);

        if (localDateTime == null) {
          throw new CoercingParseValueException("Invalid value '" + input + "' for LocalDateTime");
        }

        return localDateTime;
      }

      @Override
      public LocalDateTime parseLiteral(Object input) throws CoercingParseLiteralException {
        if (input instanceof StringValue) {
          String value = ((StringValue) input).getValue();
          LocalDateTime result = convertImpl(value);

          return result;
        }
        return null;
      }

      private LocalDateTime convertImpl(Object input) {
        if (input instanceof String) {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
          LocalDateTime localDateTime = LocalDateTime.parse((String)input, formatter);

          return localDateTime;
        }

        throw new CoercingParseLiteralException("Invalid value '" + input + "' for LocalDateTime");
      }
    });
  }
}
