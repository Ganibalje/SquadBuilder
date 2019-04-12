package com.squad.data.formation;

import com.google.common.base.Objects;
import com.squad.data.Player;
import com.squad.data.enums.Position;
import com.squad.data.enums.Schema;

import java.util.Map;

public class FormationImpl implements Formation {

    private final Schema schema;
    private final Map<Position, Player> squad;

    public FormationImpl(Schema schema, Map<Position, Player> squad) {
        this.schema = schema;
        this.squad = squad;
    }

    @Override
    public Schema getSchema() {
        return schema;
    }

    @Override
    public Map<Position, Player> getSquad() {
        return squad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FormationImpl formation = (FormationImpl) o;
        return schema == formation.schema &&
                Objects.equal(squad, formation.squad);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(schema, squad);
    }

    @Override
    public String toString() {
        return "FormationImpl{" +
                "schema=" + schema +
                ", squad=" + squad +
                '}';
    }
}
