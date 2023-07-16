package com.jayphone.practice.test;

import com.jayphone.practice.entity.Customer;
import com.jayphone.practice.entity.Goods;
import com.jayphone.practice.repository.CustomerRepository;
import com.jayphone.practice.repository.GoodsRepository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test6 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Resources.getResourceAsStream("config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            GoodsRepository goodsRepository = sqlSession.getMapper(GoodsRepository.class);
            Goods goods = goodsRepository.findById(1L);
            System.out.println(goods);
            sqlSession.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
