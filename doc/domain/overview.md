# Spacewars 2 Domain

## Overview

The following diagram provides an overview over the participating modules.

## Startup / Game class

Startup is realized by `SpaceTcgMain` which provides a number of static methods to create initial game objects,
especially a `Game` and a `Fight` (for testing parts of the application).

The client (indirectly the user) can visit `Waypoint`s which can initiate `Fight`s.

```plantuml
    @startuml

    title Game class

    interface GameListener [[java:de.rkable.spaceTCG.GameListener]] {
        fightInitiated(fight: Fight): void
        mapChanged(map: WorldMap): void
    }

    interface FightEventListener [[java:de.rkable.spaceTCG.FightEventListener]]

    class Game [[java:de.rkable.spaceTCG.Game]] {
        +setWorldMap(worldMap: WorldMap): void
        +getWorldMap(): WorldMap
        +getPlayer(): Player
        +visit(waypoint: Waypoint): void
        +fightStarted(opponent: Opponent): void
        +pickRewardCard(card: Card): void
        ..
        +addGameListener(gameListener: GameListener): void
        +removeGameListener(gameListener: GameListener): void
    }
    FightEventListener <|.. Game
    Game -> GameListener: informs clients
    @enduml
```

```plantuml
    @startuml
    title From Game to Fight

    actor Client
    Client -> Game: getWorldMap()
    Client -> WorldMap: getWaypoint()
    Client -> WorldMap: travel()
    WorldMap -> Waypoint: visit()
    Waypoint -> VisitOpponentAction: triggerVisit()
    VisitOpponentAction -> Game: fightStarted()
    Game -> Client: fightInitiated(Fight f)
    @enduml
```

## Game Loop / Fight class

A `Fight` progresses by `play()`ing `Card`s until the the fight ends.

```plantuml
    @startuml
        title Fight class overview

        interface FightEventListener [[java:de.rkable.spaceTCG.FightEventListener]] {
        cardPlayed(card: Card, changes: List<GameStateChange>): void
        victory(rewardOptions: List<Card>): void
        defeat(): void
        opponentPlayed(changes: List<GameStateChange>): void
    }

    class Fight [[java:de.rkable.spaceTCG.Fight]] {
        +display(): FightDisplay
        +getDrawnCards(): List<Card>
        +play(card: Card): void
        +endTurn(): void
        ..
        +hasUserLost(): boolean
        +hasUserWon(): boolean
        +getMaxEnergy(): int
        +getEnergy(): int
        ..
        +addFightEventListener(fightEventListener: FightEventListener): void
        +removeFightEventListener(fightEventListener: FightEventListener): void
    }

    Fight -> FightEventListener: informs clients
    @enduml
```

```plantuml
    @startuml
    title Fight sequence

    actor Client
    Client -> Fight: getDrawnCards()
    Client -> Fight: play(Card c)
    Fight -> Card: play(): List<GameStateChanges>
    Fight -> FightListener: processChanges()

    note right of Fight
        All game elements (such as the ships)
        are FightListeners and process the changes
        that are announced.
    end note

    Fight -> Fight: check victory/defeat conditions
    Client -> Fight: endTurn()
    Fight -> Opponent: performNextAction: List<GameStateChanges>
    Fight -> FightListener: processChanges()
    Fight -> Fight: check victory/defeat conditions

    == victory ==
    Fight -> FightListener: victory()

    @enduml
```

## Cards

Cards are a core element in Spacewars.
The player can play cards, which can have a number of effects.

Most cards come in multiple tiers.
The effects are usually the same, but more powerful.

```plantuml
    @startuml
    title Card classes overview

    class CardFactory {
        boolean hasTierX()
        Card createTierX()
        ...()
    }
    class CardLibrary {}

    interface Card { 
        List<GameStateChange> play(GameStats)
    }
    note right of Card
        Cards can be play()ed. Based on the
        current GameStats, they will provide
        a GameStateChanges that are to be 
        applied once the card takes effect.
    end note

    note right of CardFactory
        Provides access to 
        instances of each <i>tier</i>
    end note

    Class ConcreteCard
    Card <|.. ConcreteCard

    ConcreteCard -> CardFactory : each concrete implementation\nprovides a Factory

    CardLibrary -up-> ConcreteCard: Library knows the\nFactories of\nall concrete cards
    CardLibrary -> CardFactory: creates Cards
    @enduml
```

Here's the hierarchy the cards that are available.

```plantuml
    @startuml
    title Card Hierarchy

    interface Card { }

    ' Classes
    class RechargeShield { }
    abstract class DamageCard {}
    abstract class SingleDamageCard {}
    abstract class MultiDamageCard {}
    class Minigun {}
    class Cannon { }
    class Laser { }
    class BurstLaser { }

    ' Card hierarchy
    Card <|.. RechargeShield
    Card <|.. DamageCard
    DamageCard <|-- SingleDamageCard
    DamageCard <|-- MultiDamageCard
    SingleDamageCard <|-- Laser
    SingleDamageCard <|-- Cannon
    MultiDamageCard <|-- BurstLaser
    MultiDamageCard <|-- Minigun

    @enduml
```

## Display

Classes in this package serve for describing the state of the game.
They are to be used by external clients for visualiziation, but they are also used
internally for analyzing the game state.

## GameStats.Change

Classes in this package describe changes that can occur to the world.
Those classes are rather data containers with little dependencies, they only describe the effects but do not contain the logic to apply them.

### Design rational

GameStateChange objects should not be able to alter the internals of other important game classes.
Thus those classe in turn need to be be able to process the changes.

```plantuml
    @startuml
    title GameStateChange example classes

    interface GameStateChange
    abstract class DamageAppliedToShip
    abstract class RechargeShield

    GameStateChange <|-- RechargeShield
    GameStateChange <|-- DamageAppliedToShip
    @enduml
```

## Map

The world conists of a `WorldMap` which in turn is a map of `Waypoint`s.
When a Waypoint is `visit()`ed, then this can trigger an action, such as the encounter of an opponent or the movement to another map (e.g. the next level).

```plantuml
    @startuml
    class WorldMap
    class Waypoint {
        visit()
    }
    interface VisitAction

    WorldMap -> Waypoint: Consisits of
    Waypoint -up-> VisitAction: Triggers this\nupon visitation
    Waypoint -> Waypoint: Knows all reachable Waypoints

    @enduml
```

## Opponent

Classes representing `Opponent`s

## Player

Contains the `Player`s stats and his `Deck`.

## Reward

Classes for chosing rewards, e.g. after a victory.
