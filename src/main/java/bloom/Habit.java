package bloom;

import bloom.observer.HabitObserver;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Habit {

    private final String name;
    private final Duration refreshPeriod;
    private final Clock clock;
    private final List<HabitObserver> observers = new ArrayList<>();

    private Instant lastCompleted = null;

    public Habit(String name, Duration refreshPeriod, Clock clock) {
        this.name = name;
        this.refreshPeriod = refreshPeriod;
        this.clock = clock;
    }

    public void addObserver(HabitObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(HabitObserver observer) {
        observers.remove(observer);
    }

    public void complete() {
        lastCompleted = Instant.now(clock);
        notifyCompleted();
    }

    public void miss() {
        notifyNeglected();
    }

    public void checkAndApplyNeglect() {
        if (isNeglected()) {
            miss();
        }
    }

    public boolean isNeglected() {
        if (lastCompleted == null) return false;
        return Instant.now(clock).isAfter(lastCompleted.plus(refreshPeriod));
    }

    private void notifyCompleted() {
        for (HabitObserver o : observers) {
            o.onHabitCompleted(this);
        }
    }
    private void notifyNeglected() {
        for (HabitObserver o : observers) {
            o.onHabitNeglected(this);
        }
    }

    public String getName() { return name; }
}