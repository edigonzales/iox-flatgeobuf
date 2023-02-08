package ch.interlis.ioxwkf.flatgeobuf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import ch.ehi.basics.settings.Settings;
import ch.interlis.iom.IomObject;
import ch.interlis.iox.IoxEvent;
import ch.interlis.iox.IoxException;
import ch.interlis.iox.IoxFactoryCollection;
import ch.interlis.iox.IoxWriter;

public class FlatGeobufWriter implements IoxWriter {

    private OutputStream outputStream;
    
    public FlatGeobufWriter(File file) throws IoxException {
        this(file,null);
    }
    
    public FlatGeobufWriter(File file, Settings settings) throws IoxException { 
        init(file,settings);
    }
    
    private void init(File file, Settings settings) throws IoxException {
        try {
            this.outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new IoxException(e);
        }
    }


    @Override
    public void close() throws IoxException {
        // TODO Auto-generated method stub

    }

    @Override
    public IomObject createIomObject(String arg0, String arg1) throws IoxException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() throws IoxException {
        // TODO Auto-generated method stub

    }

    @Override
    public IoxFactoryCollection getFactory() throws IoxException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFactory(IoxFactoryCollection arg0) throws IoxException {
        // TODO Auto-generated method stub

    }

    @Override
    public void write(IoxEvent arg0) throws IoxException {
        // TODO Auto-generated method stub

    }

}
