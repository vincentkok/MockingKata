package integration;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import banking.applicationservice.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("../META-INF/spring/beans.xml")
public class IntegrationTest {

	@Autowired
	AccountService accountService;
	
	@Test
	public void transferValiMoney() {
		accountService.transferMoney(1L, 2L, 200);
	}
}
