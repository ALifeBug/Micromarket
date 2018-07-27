package club.fallenstar.dao.impl;

import club.fallenstar.dao.TradeDao;
import club.fallenstar.entity.Trade;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TradeDaoImpl extends BaseDaoImpl<Trade> implements TradeDao {
    public List<Trade> findMyTrades(String buyer) {
        String hql = "from Trade o where o.buyer = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,buyer);
        return query.list();
    }

    public Trade findUniqueTrade(String buyer, String num) {
        String hql = "from Trade o where o.buyer = ? and o.productNum = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,buyer);
        query.setParameter(1,num);
        return (Trade)query.uniqueResult();
    }

    public List<Trade> findByPnum(String num) {
        String hql = "from Trade o where o.productNum = ?";
        Query query = this.getCurrentSession().createQuery(hql);
        query.setParameter(0,num);
        return query.list();
    }
}
