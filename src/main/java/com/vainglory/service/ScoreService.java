package com.vainglory.service;

import com.vainglory.domain.ScoreItem;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/10/9 0009 下午 7:42
 */
public interface ScoreService {

    List<ScoreItem> findAll();

    void deleteById(Integer scoreId);

    ScoreItem findById(Integer scoreId);

    void updateScore(ScoreItem scoreItem);
}
