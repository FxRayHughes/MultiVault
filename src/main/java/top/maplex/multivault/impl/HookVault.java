package top.maplex.multivault.impl;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import top.maplex.multivault.MoneyType;

import java.math.BigDecimal;
import java.util.UUID;

public class HookVault implements MoneyType {

    Economy provider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class).getProvider();

    @Override
    public String id() {
        return "Vault";
    }

    @Override
    public String getName() {
        return provider.getName();
    }

    @Override
    public String getSymbol() {
        return provider.currencyNameSingular();
    }

    @Override
    public BigDecimal getBalance(UUID player) {
        return BigDecimal.valueOf(provider.getBalance(Bukkit.getOfflinePlayer(player)));
    }

    @Override
    public void addBalance(UUID player, BigDecimal amount) {
        provider.depositPlayer(Bukkit.getOfflinePlayer(player), amount.doubleValue());
    }

    @Override
    public boolean takeBalance(UUID player, BigDecimal amount) {
        return provider.withdrawPlayer(Bukkit.getOfflinePlayer(player), amount.doubleValue()).transactionSuccess();
    }

    @Override
    public boolean forceTakeBalance(UUID player, BigDecimal amount) {
        return provider.withdrawPlayer(Bukkit.getOfflinePlayer(player), amount.doubleValue()).transactionSuccess();
    }

    @Override
    public void setBalance(UUID player, BigDecimal amount) {
        provider.withdrawPlayer(Bukkit.getOfflinePlayer(player), provider.getBalance(Bukkit.getOfflinePlayer(player)));
        provider.depositPlayer(Bukkit.getOfflinePlayer(player), amount.doubleValue());
    }
}
