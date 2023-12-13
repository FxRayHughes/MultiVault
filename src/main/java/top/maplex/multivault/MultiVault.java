package top.maplex.multivault;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import top.maplex.multivault.impl.GemsEconomyHook;

import java.util.concurrent.ConcurrentHashMap;

public final class MultiVault extends JavaPlugin {

    @Getter
    private static MultiVault instance;

    @Getter
    private static final ConcurrentHashMap<String, MoneyType> moneyTypeMap = new ConcurrentHashMap<>();

    @Getter
    @Setter
    private static MoneyType mainMoneyType;

    public static final MultiVaultAPI api = new MultiVaultAPI();

    public static void registerMoneyType(MoneyType moneyType) {
        moneyTypeMap.put(moneyType.id(), moneyType);
        System.out.println("[MultiVault] Registered money type: " + moneyType.id());
    }

    public static MoneyType getMoneyType(String id) {
        return moneyTypeMap.get(id);
    }

    @Override
    public void onEnable() {
        instance = this;

        // Register money types
        if (Bukkit.getPluginManager().isPluginEnabled("GemsEconomy")) {
            new GemsEconomyHook().hook();
        }
    }

    @Override
    public void onDisable() {
    }

}
