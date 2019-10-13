package com.vainglory.mapper;

import com.vainglory.domain.ScoreItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/10/9 0009 下午 7:52
 */
public interface ScoreMapper {
    List<ScoreItem> findAll();

    void deleteById(@Param("scoreId") Integer scoreId);

    ScoreItem findById(@Param("scoreId") Integer scoreId);

    void update(ScoreItem scoreItem);
}
