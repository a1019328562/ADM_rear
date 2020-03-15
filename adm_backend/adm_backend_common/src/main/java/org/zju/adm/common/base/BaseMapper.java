package org.zju.adm.common.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * ClassName: BaseMapper
 * Description: 基础Mapper(包含基本的SQLMapper)
 * Created by tiamo on 15/3/2020 12:00 下午
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
