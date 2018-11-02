package com.hello.world.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jarck-lou
 * @date 02/11/2018 14:33
 **/
public class GraphQLNotFoundException extends RuntimeException implements GraphQLError {
  private Map<String, Object> extensions = new HashMap<>();

  public GraphQLNotFoundException(String message) {
    super(message);
    extensions.put("invalidMessage", message);
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
