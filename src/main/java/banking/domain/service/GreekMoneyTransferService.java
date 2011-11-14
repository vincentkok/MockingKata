package banking.domain.service;

import banking.domain.model.Account;

public class GreekMoneyTransferService implements MoneyTransferService {

	@Override
	public boolean hasSufficientBalance(Account account,
			int amountToTransfer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void transfer(Account from, Account to, int amount) {
		// TODO Auto-generated method stub

	}

}
