package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.*;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");

        QuestLog log = new QuestLog();
        log.add(new Quest("Rat Problem", QuestPriority.LOW, 10, false));
        log.add(new Quest("Dragon Hunt", QuestPriority.URGENT, 1000, true));
        log.add(new Quest("Border Patrol", QuestPriority.HIGH, 200, true));

        GuildHall hall = new GuildHall();
        new Captain("Valerius", hall);
        new Scout("Nyx", hall);
        new Healer("Mercy", hall);
        new Quartermaster("Haggard", hall);

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(List.of(new Hero("Geralt", 100, 15, 10)), log, hall);

        System.out.println("\nFinal Result: " + result);
    }
}