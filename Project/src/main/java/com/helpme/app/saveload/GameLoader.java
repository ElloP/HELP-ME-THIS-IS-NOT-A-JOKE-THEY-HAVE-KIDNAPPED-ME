package com.helpme.app.saveload;

import com.helpme.app.model.consciousness.concrete.Player;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.model.consciousness.IConsciousness;
import com.helpme.app.model.level.ILevel;
import com.helpme.app.model.body.IBody;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by og on 2017-05-16.
 */
public class GameLoader implements SaveLoad {

    JAXBContext context;
    Marshaller marshaller;
    Unmarshaller unmarshaller;

    public GameLoader()  {
        try{
            this.context = JAXBContext.newInstance(SaveRoot.class);
            this.marshaller = this.context.createMarshaller();
            this.unmarshaller = this.context.createUnmarshaller();
        } catch (JAXBException e){
            System.out.println("Could not create GameLoader");
            System.out.println(e);
        }
    }


    private void marshall(SaveRoot saveRoot, String filePath) throws JAXBException {
        File file = new File(filePath);
        marshaller.marshal(saveRoot, file);
    }
    private void marshall(ILevel level, IBody player, IConsciousness[] enemies, String filePath) throws JAXBException {
        marshall(new SaveRoot(level,player,enemies),filePath);
    }

    private SaveRoot unmarshall(String filePath) throws JAXBException {
        File file = new File(filePath);
        SaveRoot saveRoot = (SaveRoot) unmarshaller.unmarshal(file);
        return saveRoot;
    }

    @Override
    public void saveGame(ILevel level, Player player, IConsciousness[] enemies, String filePath) {
        try{
            marshall(new SaveRoot(level,player.readBody(),enemies), filePath);
        } catch (JAXBException e){
            System.out.println("Unable to save game");
            System.out.println(e);
        }

    }


    @Override
    public Tuple3<ILevel, Player, IConsciousness[]> loadGame(String filePath) {
        try{
            SaveRoot saveRoot = unmarshall(filePath);
            return new Tuple3<>(saveRoot.loadLevel(),saveRoot.loadPlayer(),saveRoot.loadEnemies());
        } catch (JAXBException e){
            System.out.println("Could not load game from that path");
            System.out.println(e);
            return null;
        }
    }
}
