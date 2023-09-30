package jpabook.jpashop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static jpabook.jpashop.domain.QOrder.order;

@Repository
public class OrderRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public OrderRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch){
//        em.createQuery("select o from Order o join o.member m" +
//                        "where o.status = :status" +
//                        "and m.name like : name", Order.class)
//                .setParameter("status",orderSearch.getOrderStatus())
//                .setParameter("name",orderSearch.getMemberName())
//                .setMaxResults(1000) //최대 1000건
//                .getResultList();


                String memberName = orderSearch.getMemberName();
                OrderStatus status = orderSearch.getOrderStatus();



        return  query.select(order)
                .from(order)
//                .leftJoin(order.member,member)
//                .where(eqMemberName(memberName), eqOrderStatus(status))
                .where(eqOrderStatus(status),eqMemberName(memberName))
                .fetch();
  //
    }

    private BooleanExpression eqMemberName(String memberName){
        return StringUtils.hasText(memberName) ? order.member.name.eq(memberName) : null;
    }

    private BooleanExpression eqOrderStatus(OrderStatus orderStatus){
        return orderStatus != null ? order.status.eq(orderStatus) : null;
    }
}
