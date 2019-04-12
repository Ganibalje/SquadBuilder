package com.squad.builder;

import com.squad.data.formation.Formation;
import com.squad.solver.Solver;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class SquadBuilder {
    private final Set<Predicate<Formation>> predicates;
    private final Set<Solver> solvers;

    private SquadBuilder(Set<Predicate<Formation>> predicates, Set<Solver> solvers) {
        this.predicates = predicates;
        this.solvers = solvers;
    }

    public Map<Class, Formation> solve(Formation formation) {
        return solvers.stream()
                .map(solver -> {
                    Formation solved = solver.solve(formation, predicates);
                    return new Pair<>(solved.getClass(), solved);
                })
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    public static class Builder {
        private Set<Predicate<Formation>> predicates = new HashSet<>();
        private Set<Solver> solvers = new HashSet<>();

        public Builder addPredicates(Predicate<Formation> p) {
            this.predicates.add(p);
            return this;
        }

        public Builder setPredicates(Set<Predicate<Formation>> predicates) {
            this.predicates = predicates;
            return this;
        }

        public Builder addSolver(Solver p) {
            this.solvers.add(p);
            return this;
        }

        public Builder setSolvers(Set<Solver> solvers) {
            this.solvers = solvers;
            return this;
        }

        public SquadBuilder build() {
            validate();
            return new SquadBuilder(predicates, solvers);
        }

        private void validate() {
            checkNotNull(predicates);
            checkNotNull(solvers);
        }
    }
}
