package com.vpedak.testsrecorder.core.events;

import com.vpedak.testsrecorder.core.TestGenerator;

public class ClickAction extends Action {
    public void accept(StringBuilder sb, TestGenerator generator, Subject subject){
        generator.generateActon(sb, this, subject);
    }

    @Override
    public String toString() {
        return "action=click";
    }
}