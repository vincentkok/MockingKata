package banking.domain.service;

import banking.domain.model.Account;

/**
 * Domain service for supporting Money transfer.
 */
public interface MoneyTransferService {

	/**
	 * Does the account as enough balance for the given transfer
	 * 
	 * @param account the {@link Account} to check
	 * @param amountToTransfer the amount to transfer
	 * @return true if the balance is sufficient, otherwise false
	 */
	boolean hasSufficientBalance(Account account, int amountToTransfer);
	
	/**
	 * Transfers the money from the from {@link Account} to the to {@link Account}.
	 * 
	 * @param from the {@link Account} to transfer the money from
	 * @param to the {@link Account} transfer the money to
	 * @param amount the amount to transfer
	 * @throws InsuffucientBalanceException if the balance is insufficient to perform the transaction
	 */
	void transfer(Account from, Account to, int amount);
}
