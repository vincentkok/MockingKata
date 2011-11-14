package banking.applicationservice.impl;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import banking.applicationservice.DefaultAccountService;
import banking.domain.model.Account;
import banking.domain.service.InsuffucientBalanceException;
import banking.domain.service.MoneyTransferService;
import banking.repository.AccountRepository;

public class DefaultAccountServiceTest {

	private static final int DEFAULT_BALANCE = 100;
	private static final long TO_ACCOUNT_ID = 2L;
	private static final long FROM_ACCOUNT_ID = 1L;
	private AccountRepository accountRepository;
	private MoneyTransferService moneyTransferService;
	private Account fromAccount = new Account(2L, 200);
	private Account toAccount = new Account(2L, 200);
	private DefaultAccountService accountService;

	@Before
	public void setUp() {
		accountRepository = mock(AccountRepository.class);
		moneyTransferService = mock(MoneyTransferService.class);

		accountService = new DefaultAccountService();
		accountService.setAccountRepository(accountRepository);
		accountService.setMoneyTransferService(moneyTransferService);
		accountService.afterPropertiesSet();
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidFromAccountTest() {
		when(accountRepository.findByAccountNr(2L)).thenReturn(toAccount);

		accountService.transferMoney(1L, 2L, 200);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidToAccountTest() {
		when(accountRepository.findByAccountNr(2L)).thenReturn(toAccount);

		accountService.transferMoney(2L, 1L, 200);
	}

	@Test(expected = InsuffucientBalanceException.class)
	public void transferAccountWithInsufficientBalance() {

		when(accountRepository.findByAccountNr(1L)).thenReturn(fromAccount);
		when(accountRepository.findByAccountNr(2L)).thenReturn(toAccount);
		when(
				moneyTransferService.hasSufficientBalance(
						Mockito.<Account> anyObject(), Mockito.anyInt()))
				.thenReturn(false);
		try {
			accountService.transferMoney(1L, 2L, DEFAULT_BALANCE);

		} finally {
			verify(accountRepository, never()).save(fromAccount);
			verify(accountRepository, never()).save(toAccount);
		}
	}

	@Test
	public void transferAccountWithSufficientBalance() {
		when(accountRepository.findByAccountNr(1L)).thenReturn(fromAccount);
		when(accountRepository.findByAccountNr(2L)).thenReturn(toAccount);
		when(
				moneyTransferService.hasSufficientBalance(
						Mockito.<Account> anyObject(), Mockito.anyInt()))
				.thenReturn(true);
		accountService.transferMoney(1L, 2L, DEFAULT_BALANCE);

		verify(accountRepository).save(fromAccount);
		verify(accountRepository).save(toAccount);
	}

	@Test(expected = InsuffucientBalanceException.class)
	public void transferAccountWithInsufficientBalanceDuringTransfer() {

		when(accountRepository.findByAccountNr(1L)).thenReturn(fromAccount);
		when(accountRepository.findByAccountNr(2L)).thenReturn(toAccount);
		
		
		when(
				moneyTransferService.hasSufficientBalance(
						fromAccount, DEFAULT_BALANCE)).thenReturn(true);
		doThrow(new InsuffucientBalanceException()).when(moneyTransferService)
				.transfer(fromAccount, toAccount, DEFAULT_BALANCE);
		try {
			accountService.transferMoney(FROM_ACCOUNT_ID, TO_ACCOUNT_ID, DEFAULT_BALANCE);

		} finally {
			verify(accountRepository, never()).save(fromAccount);
			verify(accountRepository, never()).save(toAccount);
		}
	}

}
