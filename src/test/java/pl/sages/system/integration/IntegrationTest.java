package pl.sages.system.integration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

public class IntegrationTest
{
	private static Server jettyServer;

	@BeforeClass
	public static void beforeClass() throws Exception
	{
		jettyServer = new Server(9111);
		jettyServer.setStopAtShutdown(true);

		CXFServlet cxfServlet = new CXFServlet();
		final ServletHolder servletHolder = new ServletHolder(cxfServlet);
		final ServletContextHandler context = new ServletContextHandler();

		context.setContextPath("/system");
		context.addServlet(servletHolder, "/*");
		context.addEventListener(new ContextLoaderListener());
		context.setInitParameter("contextConfigLocation", "file:src/main/webapp/WEB-INF/context.xml");
		jettyServer.setHandler(context);

		jettyServer.start();
	}

	@Test
	public void testPositive() throws IOException
	{
		Executor executor = Executor.newInstance();
		HttpResponse response = executor.execute(Request.Get("http://localhost:9111/system/system/v1/ping")).returnResponse();
		assertThat(response, notNullValue());
		assertThat(response.getStatusLine().getStatusCode(), CoreMatchers.is(200));
	}

	@AfterClass
	public static void afterClass() throws Exception
	{
		jettyServer.stop();
	}
}
