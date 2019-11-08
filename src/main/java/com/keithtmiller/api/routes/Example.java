package com.keithtmiller.api.routes;

import com.keithtmiller.api.dto.ExampleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Api("example")
@Path("/example")
public class Example {
    @GET
    @ApiOperation(value = "Gets ExampleDto object", response = ExampleDto.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            })
    public ExampleDto getExample() {
        return ExampleDto.builder()
                .message("Hello world")
                .build();
    }
}
