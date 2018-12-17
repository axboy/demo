package cn.axboy.demo.i18n;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class I18nApplicationTests {

	@Autowired
	private MessageSource messageSource;

	@Test
	public void contextLoads() {
		//指定语言
		System.out.println(messageSource.getMessage("demo.message.hello", null, Locale.forLanguageTag("cn")));

		//自定义一个语言ababcd
		System.out.println(messageSource.getMessage("demo.message.hello", null, Locale.forLanguageTag("abcabcd")));

		//带参数的
		System.out.println(messageSource.getMessage("demo.message.param", new Object[]{"zcw"}, Locale.getDefault()));
	}
}

