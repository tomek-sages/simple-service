package pl.sages.system.test;

import org.junit.Test;
import pl.sages.system.SystemManager;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by tomek on 28/06/15.
 */
public class TestSystemManager
{
	private SystemManager systemManager = new SystemManager();

	@Test
	public void testPositive()
	{
		String result = systemManager.check("abc");
		assertThat(result, notNullValue());
		assertThat(result, is("OK!"));
	}

	@Test
	public void testPositiveBlankInput()
	{
		String result = systemManager.check("");
		assertThat(result, notNullValue());
		assertThat(result, is("OK!"));
	}

	@Test
	public void testNegative()
	{
		String result = systemManager.check("cba");
		assertThat(result, notNullValue());
		assertThat(result, is("FAILED!"));
	}
}
