package bloom.server.controllers;

public record CreateHabitRequest(String habitName, String plantType, String plantName, String refreshPeriod) {
}
