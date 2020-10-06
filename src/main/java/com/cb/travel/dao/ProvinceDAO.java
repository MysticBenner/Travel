package com.cb.travel.dao;

import com.cb.travel.entity.Province;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProvinceDAO extends BaseDAO<Province, String> {
}
