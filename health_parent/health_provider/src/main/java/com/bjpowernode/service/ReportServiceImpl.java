package com.bjpowernode.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.mapper.MemberMapper;
import com.bjpowernode.pojo.Order;
import com.bjpowernode.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Map<String, Object> getMemberReport() throws Exception {
        /**
         * <months, List<‘2022-01’,‘2022-02’>>
         * <memberCount, List<20,30>>
         */
        List<String> monthList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-12);
        for (int i = 0; i <12 ; i++) {
            calendar.add(Calendar.MONTH,1);
            monthList.add(DateUtils.parseDate2String(calendar.getTime(),"yyyy-MM"));
        }
        List<Long> countList = new ArrayList<>();
        for (String month : monthList) {
           long count = memberMapper.getCountByMonth(month);
            countList.add(count);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("months",monthList);
        map.put("memberCount",countList);
        return map;
    }

    public static void main(String[] args) throws Exception {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i <12 ; i++) {
            calendar.add(Calendar.MONTH,-1);
            System.out.println(DateUtils.parseDate2String(calendar.getTime(),"yyyy-MM"));
        }
    }
}
