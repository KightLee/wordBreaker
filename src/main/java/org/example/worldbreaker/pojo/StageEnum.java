package org.example.worldbreaker.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum StageEnum {
    Stage1(1),
    Stage2(2),
    Stage3(3),
    ;

    private final Integer stage;

    // strategy and validation
    public static StageEnum getStage(Integer stage) {
        return Arrays.stream(StageEnum.values())
                .filter(value -> value.getStage().equals(stage))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("stage is not exist"));
    }
}
