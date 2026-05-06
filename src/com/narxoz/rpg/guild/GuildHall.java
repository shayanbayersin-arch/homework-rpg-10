package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Topic-based mediator for the Adventurers' Guild war council.
 */
public class GuildHall implements GuildMediator {

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();

    @Override
    public void register(GuildMember member) {
        if (member instanceof Captain) addSubscriber("COMMAND", member);
        if (member instanceof Scout) addSubscriber("REPORTS", member);
        if (member instanceof Healer) addSubscriber("MEDICAL", member);
        if (member instanceof Quartermaster) addSubscriber("SUPPLIES", member);
        // Капитан также слушает отчеты
        if (member instanceof Captain) addSubscriber("REPORTS", member);
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        for (GuildMember member : subscribersFor(topic)) {
            if (member != from) {
                member.receive(topic, from, payload);
            }
        }
    }
    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}
