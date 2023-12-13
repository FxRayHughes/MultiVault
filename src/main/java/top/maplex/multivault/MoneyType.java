package top.maplex.multivault;

import java.math.BigDecimal;
import java.util.UUID;

public interface MoneyType {

    /**
     * 代表了货币的名称，作为唯一标识 不可重复
     */
    String id();

    boolean isEnable = true;

    /**
     * 获取货币名称
     */
    String getName();

    /**
     * 获取货币符号
     */
    String getSymbol();

    /**
     * 展示格式
     * v是金额 s是单位
     * 例如 8.0元
     */
    String format = "{v}{s}";

    /**
     * 展示精度
     */
    int scale = 2;

    /**
     * 获取货币余额
     */
    BigDecimal getBalance(UUID player);

    /**
     * 增加货币
     */
    void addBalance(UUID player, BigDecimal amount);

    /**
     * 减少货币
     */
    boolean takeBalance(UUID player, BigDecimal amount);

    /**
     * 强制减少货币 不足会返回false并且清空
     */
    boolean forceTakeBalance(UUID player, BigDecimal amount);

    /**
     * 设置货币数量
     */
    void setBalance(UUID player, BigDecimal amount);

    /**
     * 是否拥有该货币数量
     */
    default boolean hasBalance(UUID player, BigDecimal amount) {
        return getBalance(player).compareTo(amount) >= 0;
    }
}
