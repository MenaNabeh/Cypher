# Cypher — Ant Colony Simulation

A Java/Swing simulation of an ant colony foraging for food, built for COMP2000. Ants search for food, carry it back to the colony, and lay pheromone trails along the way that decay over time.

## Features

- Ant types — ForagerAnt (collects food), ScoutAnt (explores and can discover food), and QueenAnt (stays near the colony), all sharing behaviour through an abstract Ant superclass.
- Movement Strategy pattern — ants switch movement behaviour at runtime (RandomMovement, PheromoneFollowing) depending on their current state, rather than hardcoding logic per ant type.
- Pheromone trails — foragers carrying food lay pheromone markers that decay over time, visualised as fading red trails.
- Exception handling — a custom DepletedFoodException is thrown when a food source runs out, caught and handled gracefully instead of crashing the simulation.
- Generics — List<Ant> and List<FoodSource> are used throughout to keep collections type-safe.
- Live controls — adjustable ant speed and ant count via sliders, plus a reset button, all built with Swing.

## Running it

Open the project in VS Code (or any Java IDE) and run src/SimulationPanel.java — it contains the main method and launches the simulation window directly.

## Team

Cypher — COMP2000 Session 2, 2026

- Bhaavna Venkatramana Rao
- Danielle Wrigley
- Isaac Lynn
- Mena Nabeh
- Omid Aakefi