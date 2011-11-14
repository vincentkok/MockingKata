package banking.applicationservice;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import banking.domain.model.Account;
import banking.domain.service.InsuffucientBalanceException;
import banking.domain.service.MoneyTransferService;
import banking.repository.AccountRepository;


public class DefaultAccountService implements AccountService, InitializingBean {

	private AccountRepository accountRepository;
	
	private MoneyTransferService moneyTransferService;
	
	@Override
	public void transferMoney(long fromAccountNr, long toAccountNr, int amount) {
		
		Account toAccount = getValidAccount(toAccountNr);
		Account fromAccount = getValidAccount(fromAccountNr);
		
		if (!moneyTransferService.hasSufficientBalance(fromAccount, amount)) {
			throw new InsuffucientBalanceException();
		}
		
		moneyTransferService.transfer(fromAccount, toAccount, amount);
		
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
	}

	private Account getValidAccount(long fromAccountNr) {
		Account fromAccount = accountRepository.findByAccountNr(fromAccountNr);
		if (fromAccount == null) {
			throw new IllegalArgumentException();
		}
		return fromAccount;
	}
	
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public void setMoneyTransferService(
			MoneyTransferService moneyTransferService) {
		this.moneyTransferService = moneyTransferService;
	}

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(accountRepository, "accountRepository should be set");
		Assert.notNull(moneyTransferService, "moneyTransferService should be set");
	}
}
