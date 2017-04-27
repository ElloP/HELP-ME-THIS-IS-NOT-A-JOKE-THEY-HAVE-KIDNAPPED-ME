package com.helpme.app;

import com.helpme.app.Mock.MockWorld1;
import com.helpme.app.utils.saveload.SaveData;
import com.helpme.app.utils.saveload.DiskManager;
import com.helpme.app.world.level.Level;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by og on 2017-04-26.
 */
public class SaveLoadTest {

    private JAXBContext context;
    private MockWorld1 world;

    @Before
    public void init() throws JAXBException {
        this.world = new MockWorld1();
        this.context = JAXBContext.newInstance(Level.class);
    }

    @Test
    public void testSaveLoad() {
        String data = "Hej \n dssa slabbi";
        SaveData save = new SaveData(data);
        SaveData load = null;
        String fileName = "test.save";
        try {
            DiskManager.save(save,fileName);
        } catch (Exception e){
            System.out.println(e);
        }
        try {
            load = DiskManager.load(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert (load.test1.equals(data));
    }

    @Test
    public void testXmlSave() throws JAXBException {
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(world.level,new File("test.xml"));
    }
}
