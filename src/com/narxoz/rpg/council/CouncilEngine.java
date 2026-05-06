package com.narxoz.rpg.council;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;
import java.util.List;

public class CouncilEngine {
    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int qCount = 0;
        int mCount = 0;


        QuestIterator it = questLog.ordered();
        while(it.hasNext()) {
            Quest q = it.next();
            qCount++;
            hall.dispatch("REPORTS", null, "Analyzing " + q.getTitle());
            mCount++;
        }


        QuestIterator priorityIt = questLog.priorityAtLeast(QuestPriority.HIGH);
        while(priorityIt.hasNext()) {
            Quest q = priorityIt.next();
            qCount++;
            hall.dispatch("COMMAND", null, "URGENT: " + q.getTitle());
            mCount++;
        }

        return new CouncilRunResult(qCount, mCount, 4);
    }
}