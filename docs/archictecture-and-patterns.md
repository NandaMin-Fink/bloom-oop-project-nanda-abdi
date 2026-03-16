# Architecture and Patterns

## Patterns employed:
### Template Pattern
Core Abstract Plant class contains final "Grow" and "Wither" methods. Concrete implementations will override submethods but never those final template methods.l
### Factory Pattern
Plant Factory to create different types of plants.
### Observer Pattern
HabitObserver so that streak trackers can observe updates to habits. May also be used for plant growth/withering.


## Larger Architecture Explination
### Core Domain Model
#### Plant:
- Abstract template class. 
- Defines grow and wither 
- Implements HabitObserver.
#### RosePlant, AppleTree, etc:
- Concrete classes extending Plant.
#### Habit:
- Observable habit object. Defines the habit, how often it should be recorded before marking a wither. Time frame etc.
#### Garden:
- Contains all plants in a "garden" which represents a users habits

### Extras:
#### PlantFactory
- Factory used to create concrete Plant objects. Decouples object creation.
#### HabitObserver
- Interface that can be implemented to observe the habit class
#### Streak Tracker
- Implements HabitObserver to record habit streaks.

