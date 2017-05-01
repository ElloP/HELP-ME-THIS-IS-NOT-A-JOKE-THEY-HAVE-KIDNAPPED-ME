package com.helpme.app.saveload;

import com.helpme.app.world.character.IReadMonster;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Klas on 2017-05-01.
 */
public class SavePlayer {

     JAXBContext context;
     Marshaller marshaller;
     Unmarshaller unmarshaller;

    public SavePlayer() throws JAXBException {
        this.context = JAXBContext.newInstance(PlayerWrapper.class);
        this.marshaller = this.context.createMarshaller();
        this.unmarshaller = this.context.createUnmarshaller();
    }

    public void marshall(IReadMonster player, String filePath) throws JAXBException {
        File file = new File(filePath);
        marshaller.marshal(new PlayerWrapper(player), file);
    }

    public IPlayerWrapper unmarshall(String filePath) throws JAXBException {
        File file = new File(filePath);
        IPlayerWrapper pw = (IPlayerWrapper) unmarshaller.unmarshal(file);
        return pw;
    }
}
