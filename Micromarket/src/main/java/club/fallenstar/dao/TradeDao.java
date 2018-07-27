package club.fallenstar.dao;

import club.fallenstar.entity.Trade;

import java.util.List;

public interface TradeDao extends BaseDao<Trade> {

    /**
     *
     * @param Trade
     * @return 我的订单
     */
    List<Trade> findMyTrades(String buyer);

    /**
     *
     * @param buyer
     * @param num
     * @return 查询我的指定的订单
     */
    Trade findUniqueTrade(String buyer,String num);

    /**
     *
     * @param num
     * @return 根据商品编号查询
     */
    List<Trade> findByPnum(String num);
}
