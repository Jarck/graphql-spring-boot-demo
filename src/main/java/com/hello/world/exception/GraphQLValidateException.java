package com.hello.world.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jarck-lou
 * @date 01/11/2018 17:03
 **/
public class GraphQLValidateException extends RuntimeException implements GraphQLError {
  private Map<String, Object> extensions = new HashMap<>();

  public GraphQLValidateException(String errorMessage) {
    super(errorMessage);
    extensions.put("invalidMessage", errorMessage);
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  public Map<String, Object> getExtensions() {
    return extensions;
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.DataFetchingException;
  }
}
