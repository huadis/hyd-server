package cn.wuhan.hyd.sports.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * 功能说明：培训活动场馆支持的项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_training_activity_item_stadium")
public class HydTrainingActivityItemStadium implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @ApiModelProperty(value = "主键ID", hidden = true)
    private String id;  // 表主键为VARCHAR类型，对应String

    @Column(name = "activityId")
    @ApiModelProperty(value = "活动id")
    private String activityId;

    @Column(name = "stadiumId")
    @ApiModelProperty(value = "场馆id")
    private String stadiumId;

    @Column(name = "sportCode")
    @ApiModelProperty(value = "项目类型编码")
    private String sportCode;

    @Column(name = "sportName")
    @ApiModelProperty(value = "项目类型名称")
    private String sportName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydTrainingActivityItemStadium that = (HydTrainingActivityItemStadium) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
