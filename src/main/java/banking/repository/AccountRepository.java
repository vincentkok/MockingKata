package banking.repository;

import banking.domain.model.Account;

/**
 * Repository for the {@link Account} domain object.
 */
public interface AccountRepository {
	
	/**
	 * Find an {@link Account} by its nr.
	 * 
	 * @param accountNr the nr to search with
	 * @return the {@link Account} object if found, otherwise null
	 */
	Account findByAccountNr(long accountNr);
	
	/**
	 * Save the {@link Account}
	 * 
	 * @param account the {@link Account} to save
	 * @return the saved account
	 */
	Account save(Account account);
}
