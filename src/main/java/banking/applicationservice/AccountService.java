package banking.applicationservice;

public interface AccountService {

	void transferMoney(long fromAccountNr, long toAccountNr, int amount);
}
