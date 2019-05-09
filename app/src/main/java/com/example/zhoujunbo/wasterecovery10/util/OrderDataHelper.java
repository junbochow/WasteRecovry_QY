package com.example.zhoujunbo.wasterecovery10.util;

import com.example.zhoujunbo.wasterecovery10.mode.Order_Body;
import com.example.zhoujunbo.wasterecovery10.mode.Order_Foot;
import com.example.zhoujunbo.wasterecovery10.mode.Order_Head;
import com.example.zhoujunbo.wasterecovery10.mode.Order_Summary;

import java.util.ArrayList;
import java.util.List;

public class OrderDataHelper {

    /**
     * List<Object>有三种数据类型：
     * 1、GoodsOrderInfo 表示每个小订单的头部信息（订单号、订单状态、店铺名称）
     * 2、OrderGoodsItem 表示小订单中的商品
     * 3、OrderPayInfo 表示大订单的支付信息（金额、订单状态）
     *
     * @param resultList
     * @return
     */
    public static List<Object> getDataAfterHandle(List<Order_Summary> resultList) {


        List<Object> dataList = new ArrayList<Object>();
        //遍历每一张大订单
        for (Order_Summary orderListBean : resultList) {
            //订单支付的金额和订单状态(Foot)
            Order_Foot order_foot = new Order_Foot();
            order_foot.setWhatsmore(orderListBean.getWhatsmore());
            order_foot.setTotal(orderListBean.getTotal());
            order_foot.setState(orderListBean.getState());

            //创建时间(Head)
            Order_Head order_head = new Order_Head();
            order_head.setID(orderListBean.getID());
            order_head.setState(orderListBean.getState());
            order_head.setTime(orderListBean.getTime());
            order_head.setCollectorName(orderListBean.getCollectorName());
            order_head.setCollectorNum(orderListBean.getCollectorNum());
            order_head.setCustmerName(orderListBean.getCustmerName());
            order_head.setCustmerNum(orderListBean.getCustmerNum());

            //订单商品(Body)
            List<Order_Body> orderDetailList = orderListBean.getOrder_bodies();

            List<Order_Body> order_bodies = new ArrayList<>();

            //遍历每个大订单里面的商品
            for (Order_Body orderDetailListBean : orderDetailList) {
                //获取商品的订单id
                Order_Body order_body = new Order_Body();
                order_body.setGoods(orderDetailListBean.getGoods());
                order_body.setCount(orderDetailListBean.getCount());
                order_body.setPrice(orderDetailListBean.getPrice());
                order_bodies.add(order_body);
            }
            //把所有数据按照头部、内容和尾部三个部分排序好
            dataList.add(order_head);
            dataList.addAll(order_bodies);
            dataList.add(order_foot);
        }
        return dataList;
    }
}