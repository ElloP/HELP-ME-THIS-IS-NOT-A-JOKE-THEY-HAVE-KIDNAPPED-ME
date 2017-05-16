package com.helpme.app.saveload;

import com.helpme.app.world.character.IBody;
import com.helpme.app.world.consciousness.Enemy;
import com.helpme.app.world.level.ILevel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by og on 2017-05-16.
 */
public class GameLoader {

    JAXBContext context;
    Marshaller marshaller;
    Unmarshaller unmarshaller;

    public GameLoader() throws JAXBException {
        this.context = JAXBContext.newInstance(SaveRoot.class);
        this.marshaller = this.context.createMarshaller();
        this.unmarshaller = this.context.createUnmarshaller();
    }


    public void marshall(SaveRoot saveRoot, String filePath) throws JAXBException {
        File file = new File(filePath);
        marshaller.marshal(saveRoot, file);
    }
    public void marshall(ILevel level, IBody player, Enemy[] enemies, String filePath) throws JAXBException {
        marshall(new SaveRoot(level,player,enemies),filePath);
    }

    public SaveRoot unmarshall(String filePath) throws JAXBException {
        File file = new File(filePath);
        SaveRoot saveRoot = (SaveRoot) unmarshaller.unmarshal(file);
        return saveRoot;
    }
}
