package top.maplex.multivault.impl;

import me.xanium.gemseconomy.GemsEconomy;
import me.xanium.gemseconomy.account.Account;
import me.xanium.gemseconomy.api.GemsEconomyAPI;
import me.xanium.gemseconomy.currency.Currency;
import top.maplex.multivault.MoneyType;
import top.maplex.multivault.MultiVault;

import java.math.BigDecimal;
import java.util.UUID;

public class GemsEconomyHook {

    public void hook() {
        GemsEconomyAPI api = new GemsEconomyAPI();

        for (Currency currency : GemsEconomy.getInstance().getCurrencyManager().getCurrencies()) {
            MoneyType moneyType = new MoneyType() {
                @Override
                public String id() {
                    return currency.getSingular();
                }

                @Override
                public String getName() {
                    return currency.getPlural();
                }

                @Override
                public String getSymbol() {
                    return currency.getSymbol();
                }

                @Override
                public BigDecimal getBalance(UUID player) {
                    return BigDecimal.valueOf(api.getBalance(player, currency));
                }

                @Override
                public void addBalance(UUID player, BigDecimal amount) {
                    api.deposit(player, amount.doubleValue(), currency);
                }

                @Override
                public boolean takeBalance(UUID player, BigDecimal amount) {
                    if (api.getBalance(player, currency) >= amount.doubleValue()) {
                        api.withdraw(player, amount.doubleValue(), currency);
                        return true;
                    }
                    return false;
                }

                @Override
                public boolean forceTakeBalance(UUID player, BigDecimal amount) {
                    if (api.getBalance(player, currency) >= amount.doubleValue()) {
                        api.withdraw(player, amount.doubleValue(), currency);
                        return true;
                    }
                    setBalance(player, BigDecimal.ZERO);
                    return false;
                }

                @Override
                public void setBalance(UUID player, BigDecimal amount) {
                    Account acc = api.plugin.getAccountManager().getAccount(player);
                    acc.setBalance(currency, amount.doubleValue());
                }
            };
            MultiVault.registerMoneyType(moneyType);
        }

    }

}
