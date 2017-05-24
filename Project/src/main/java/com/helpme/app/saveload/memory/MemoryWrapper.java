package com.helpme.app.saveload.memory;

import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.model.consciousness.behaviour.memory.IMemory;
import com.helpme.app.model.consciousness.behaviour.memory.concrete.MemoryFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kopa on 2017-05-19.
 */
public class MemoryWrapper implements ILoadable<IMemory> {
    MemoryEntryWrapper[] longTermWrapper;
    MemoryEntryWrapper[] shortTermWrapper;

    public MemoryWrapper() {

    }

    public MemoryWrapper(IMemory memory) {
        Map<String, Integer> longTerm = memory.readLongTerm();
        Map<String, Integer> shortTerm = memory.readShortTerm();
        longTermWrapper = new MemoryEntryWrapper[longTerm.size()];
        shortTermWrapper = new MemoryEntryWrapper[shortTerm.size()];
        int i = 0;


        for (Map.Entry<String, Integer> entry : longTerm.entrySet()) {
            longTermWrapper[i] = new MemoryEntryWrapper(entry.getKey(), entry.getValue());
            i++;
        }

        i = 0;
        for (Map.Entry<String, Integer> entry : shortTerm.entrySet()) {
            shortTermWrapper[i] = new MemoryEntryWrapper(entry.getKey(), entry.getValue());
            i++;
        }

    }


    @XmlElementWrapper(name = "long_term")
    @XmlElement(name = "event")
    public MemoryEntryWrapper[] getLongTerm() {
        return longTermWrapper == null ? null : longTermWrapper.clone();
    }

    public void setLongTerm(MemoryEntryWrapper[] longTermWrapper) {
        for (int i = 0; i < longTermWrapper.length; i++) {
            this.longTermWrapper[i] = longTermWrapper[i];
        }
    }

    @XmlElementWrapper(name = "short_term")
    @XmlElement(name = "event")
    public MemoryEntryWrapper[] getShortTerm() {
        return shortTermWrapper == null ? null : shortTermWrapper.clone();
    }

    public void setShortTerm(MemoryEntryWrapper[] shortTermWrapper) {
        for (int i = 0; i < shortTermWrapper.length; i++) {
            this.shortTermWrapper[i] = shortTermWrapper[i];
        }
    }


    @Override
    public IMemory getObject() {
        Map<String, Integer> longTerm = memoryWrapperToMap(longTermWrapper);
        Map<String, Integer> shortTerm = memoryWrapperToMap(shortTermWrapper);

        return MemoryFactory.createMemory(shortTerm, longTerm);
    }

    private Map<String, Integer> memoryWrapperToMap(MemoryEntryWrapper[] memoryWrapper){
        Map<String, Integer> map = new HashMap<>();
        if (memoryWrapper != null) {
            for (MemoryEntryWrapper memoryEntryWrapper : memoryWrapper) {
                Map.Entry<String, Integer> entry = memoryEntryWrapper.getObject();
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }
}
