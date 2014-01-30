package net.jordaria.entity;

/**
 * An interface for an entity which has currency and allows for transactions.
 * 
 * @author Ches Burks
 *
 */
public interface CurrencyHolder {
	int currency = 0;
	public void addCurency(int amount);
	public void removeCurrency(int amoun);
	public void transferCurrencyFrom(CurrencyHolder other, int amount);
	public void transferCurrencyTo(CurrencyHolder other, int amount);
}
