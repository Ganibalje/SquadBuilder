package com.squad.solver;

import com.squad.data.formation.Formation;

import java.util.Set;
import java.util.function.Predicate;

public interface Solver {
    Formation solve(Formation formation, Set<Predicate<Formation>> predicates);
}
