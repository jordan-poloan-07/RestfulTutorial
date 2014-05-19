package com.hello.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/message")
public class MessageRestService {

	// this will be the default response
	// since other methods have explicit paths
	@GET
	public Response printDefault() {
		return Response.status(200).entity("Welcome to message rest service")
				.build();
	}

	@GET
	@Path("{param}")
	public Response printParam(@PathParam("param") String msg) {

		String result = "Hello restful example : " + msg;

		return Response.status(200).entity(result).build();

	}

	@GET
	@Path("{num : \\d+}")
	public Response printParamRegex(@PathParam("num") int num) {
		return Response.status(200).entity("The number is " + num).build();
	}

	/**
	 * 
	 * For printSimpleQueryParam, you can put @DefaulValues("value") to give
	 * default values in case the query parameter is null
	 * 
	 */
	@GET
	@Path("queryP1")
	public Response printSimpleQueryParam(@QueryParam("a") int a,
			@QueryParam("b") String b, @QueryParam("c") String c) {

		return Response
				.status(200)
				.entity("printSimpleQueryParam is called, a : " + a + ", b : "
						+ b + ", c : " + c).build();
	}

	@GET
	@Path("queryP2")
	public Response printProgrammaticQueryParam(@Context UriInfo info) {

		String a = info.getQueryParameters().getFirst("a");
		String b = info.getQueryParameters().getFirst("b");
		String c = info.getQueryParameters().getFirst("c");

		return Response
				.status(200)
				.entity("printProgrammaticQueryParam is called, a : " + a
						+ ", b : " + b + ", c : " + c).build();
	}
	
	// NOTE : this is overriden by printParam
	@GET
	@Path("{object}")
	public Response printMatrixParam(@PathParam("object") String object,
			@MatrixParam("bar") String bar, @MatrixParam("foo") String foo) {
		return Response
				.status(200)
				.entity("printMatrixParam is object : " + object + ", bar : "
						+ bar + ", foo : " + foo).build();
	}

	@POST
	@Path("formSubmit")
	public Response printForm(@FormParam("name") String name,
			@FormParam("age") int age) {
		return Response.status(200)
				.entity("Form details are name : " + name + ", age : " + age)
				.build();
	}
}
