package com.embrace.springcloud.service;

import com.embrace.springcloud.entities.PaymentDto;
import org.apache.ibatis.annotations.Param;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/25 22:18
 */
public interface PaymentService {

    int  save(PaymentDto paymentDto);

    PaymentDto getPaymentById(@Param("id") long id);
}
