package com.squad.data.enums;

import java.util.List;

import static com.squad.data.enums.Position.*;
import static java.util.Arrays.asList;

public enum Schema {

    F4_4_2(asList(GK, LB, CB, CB, RB, LM, CM, CM, RM, ST, ST));

    Schema(List<Position> positions) {
        this.positions = positions;
    }

    private List<Position> positions;

    public List<Position> getPositions() {
        return positions;
    }
}
