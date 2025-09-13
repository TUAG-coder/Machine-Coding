package repositories;

import models.Gate;

import java.util.HashMap;
import java.util.Optional;

public class GateRepository {
    // Gate table
    private HashMap<Long, Gate> gates = new HashMap<>();

    /*
    public Gate findGateById(Long id) {
        return gates.get(id);
    }
    */

    public Optional<Gate> findGateById(Long id) {
        return Optional.of(gates.get(id));
    }
}
