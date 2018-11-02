# graphql-spring-boot-demo
graphql spring boot demo

execute `mvnw spring-boot:run`. Or inside an IDE, execute the class `com.hello.world.Application`.

to http://localhost:8080/graphiql to start executing queries. For example:

```
{
  searchUsers {
    id
    name
    city {
      id
      name
    }
  }
}
```

### TODO

1. use shiro to graphql
2. ...
