package com.vainglory.domain;

/**
 * @author vaingloryss
 * @date 2019/10/9 0009 下午 7:45
 */

public class ScoreItem {
    private Integer id;
    private Double score;
    private String rank;
    private Integer userId;
    private User user;

    public ScoreItem() {
    }

    public ScoreItem(Integer id, Double score, String rank, Integer userId, User user) {
        this.id = id;
        this.score = score;
        this.rank = rank;
        this.userId = userId;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ScoreItem{" +
                "id=" + id +
                ", score=" + score +
                ", rank='" + rank + '\'' +
                ", userId=" + userId +
                ", user=" + user +
                '}';
    }
}
