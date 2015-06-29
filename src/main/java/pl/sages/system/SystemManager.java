package pl.sages.system;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class SystemManager
{
	public String check(String input)
	{
		if(StringUtils.isBlank(input) || input.toLowerCase().startsWith("a"))
		{
			return "OK!";
		}
		else
		{
			return "FAILED!";
		}
	}
}
