package pl.sages.system;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1")
public class SystemRestService
{
	@Autowired
	private SystemManager systemManager;

	@GET
	@Path(value = "/ping")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping(@QueryParam("input") String input)
	{
		String result = systemManager.check(input);
		return result.equals("OK!") ? Response.ok(result).build() :
				Response.status(Response.Status.BAD_REQUEST).entity(result).build();
	}
}
