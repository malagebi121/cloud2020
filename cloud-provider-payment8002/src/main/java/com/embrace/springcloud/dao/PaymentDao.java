package com.embrace.springcloud.dao;

import com.embrace.springcloud.entities.PaymentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/24 23:18
 */
@Mapper
//@MapperScan
public interface PaymentDao
{
    int  save(PaymentDto paymentDto);

    PaymentDto getPaymentById(@Param("id") long id);
}
