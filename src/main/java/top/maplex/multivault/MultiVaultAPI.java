package top.maplex.multivault;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class MultiVaultAPI {

    public String getFormatMoney(UUID uuid, MoneyType moneyType) {
        // 小数点位数
        int scale = moneyType.scale;
        // 根据小数点位数转为字符串
        BigDecimal bigDecimal = moneyType.getBalance(uuid).setScale(scale, RoundingMode.HALF_UP);
        // 格式化字符串
        return moneyType.format.replace("{v}", bigDecimal.toString()).replace("{s}", moneyType.getSymbol());
    }

    public BigDecimal getMoney(UUID uuid, MoneyType moneyType) {
        return moneyType.getBalance(uuid);
    }

    public BigDecimal getMoney(UUID uuid, String moneyType) {
        return getMoney(uuid, MultiVault.getMoneyType(moneyType));
    }

    public void addMoney(UUID uuid, MoneyType moneyType, BigDecimal amount) {
        moneyType.addBalance(uuid, amount);
    }

    public void addMoney(UUID uuid, String moneyType, BigDecimal amount) {
        addMoney(uuid, MultiVault.getMoneyType(moneyType), amount);
    }

    public boolean takeMoney(UUID uuid, MoneyType moneyType, BigDecimal amount) {
        return moneyType.takeBalance(uuid, amount);
    }

    public boolean takeMoney(UUID uuid, String moneyType, BigDecimal amount) {
        return takeMoney(uuid, MultiVault.getMoneyType(moneyType), amount);
    }

    public void forceTakeMoney(UUID uuid, MoneyType moneyType, BigDecimal amount) {
        moneyType.forceTakeBalance(uuid, amount);
    }

    public void forceTakeMoney(UUID uuid, String moneyType, BigDecimal amount) {
        forceTakeMoney(uuid, MultiVault.getMoneyType(moneyType), amount);
    }

    public void setMoney(UUID uuid, MoneyType moneyType, BigDecimal amount) {
        moneyType.setBalance(uuid, amount);
    }

    public void setMoney(UUID uuid, String moneyType, BigDecimal amount) {
        setMoney(uuid, MultiVault.getMoneyType(moneyType), amount);
    }

}
