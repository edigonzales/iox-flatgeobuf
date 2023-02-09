package ch.interlis.ioxwkf.flatgeobuf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

import ch.ehi.basics.settings.Settings;
import ch.interlis.ili2c.metamodel.TransferDescription;
import ch.interlis.iom.IomObject;
import ch.interlis.iox.IoxEvent;
import ch.interlis.iox.IoxException;
import ch.interlis.iox.IoxFactoryCollection;
import ch.interlis.iox.IoxWriter;
import ch.interlis.iox.ObjectEvent;
import ch.interlis.iox.StartBasketEvent;
import ch.interlis.iox.StartTransferEvent;

public class FlatGeobufWriter implements IoxWriter {

    private OutputStream outputStream;
    
//    private Map<String,AttributeDescriptor> attrDescsMap=null;
    
    private TransferDescription td=null;
    private String iliGeomAttrName=null;

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
    public void write(IoxEvent event) throws IoxException {
        if(event instanceof StartTransferEvent){
            // ignore
        }else if(event instanceof StartBasketEvent){
        }else if(event instanceof ObjectEvent){
            ObjectEvent obj = (ObjectEvent) event;
            IomObject iomObj = (IomObject)obj.getIomObject();
            String tag = iomObj.getobjecttag();
            System.out.println("tag: " + tag);

            // Wenn null, dann gibt es noch kein "Schema"
//            attrDescsMap
            if(null == null) {
               // initAttrDescs(); // TODO ? 
                if(td != null) {
                    // TODO
                } else {
                    for(int u=0;u<iomObj.getattrcount();u++) {
                        String attrName = iomObj.getattrname(u);
                        System.out.println(attrName);
                        //create the builder
//                        AttributeTypeBuilder attributeBuilder = new AttributeTypeBuilder();
                        //if(attrName.equals(iliGeomAttrName)) {
                        if(attrName.equals("gaga")) {
//                            iliGeomAttrName=attrName;
//                            IomObject iomGeom=iomObj.getattrobj(attrName,0);
//                            if (iomGeom != null){
//                                if (iomGeom.getobjecttag().equals(COORD)){
//                                    attributeBuilder.setBinding(Point.class);
//                                }else if (iomGeom.getobjecttag().equals(MULTICOORD)){
//                                    attributeBuilder.setBinding(MultiPoint.class);
//                                }else if(iomGeom.getobjecttag().equals(POLYLINE)){
//                                    attributeBuilder.setBinding(LineString.class);
//                                }else if (iomGeom.getobjecttag().equals(MULTIPOLYLINE)){
//                                    attributeBuilder.setBinding(MultiLineString.class);
//                                }else if (iomGeom.getobjecttag().equals(MULTISURFACE)){
//                                    int surfaceCount=iomGeom.getattrvaluecount("surface");
//                                    if(surfaceCount<=1) {
//                                        /* Weil der Featuretype (das Schema) des Shapefiles anhand des ersten IomObjektes erstellt wird, 
//                                         * kann es vorkommen, dass Multisurfaces mit mehr als einer Surface nicht zu einem Multipolygon umgewandelt werden, 
//                                         * sondern zu einem Polygon. Aus diesem Grund wird immer das MultiPolygon-Binding verwendet. */
//                                        attributeBuilder.setBinding(MultiPolygon.class);
//                                    }else if(surfaceCount>1){
//                                        attributeBuilder.setBinding(MultiPolygon.class);
//                                    }
//                                }else {
//                                    attributeBuilder.setBinding(Point.class);
//                                }
//                                if(defaultSrsId!=null) {
//                                    attributeBuilder.setCRS(createCrs(defaultSrsId));
//                                }
//                            }
                        } else {
                            // Ist das nicht relativ heikel?
                            // Funktioniert mit Strukturen nicht mehr, oder? Wegen getattrvaluecount?
                            // TODO: testen
                            if(iliGeomAttrName == null && iomObj.getattrvaluecount(attrName)>0 && iomObj.getattrobj(attrName,0) != null) {
                                iliGeomAttrName=attrName;
                                System.out.println("geometry found");
//                                IomObject iomGeom=iomObj.getattrobj(attrName,0);
//                                if (iomGeom != null){
//                                    if (iomGeom.getobjecttag().equals(COORD)){
//                                        attributeBuilder.setBinding(Point.class);
//                                    }else if (iomGeom.getobjecttag().equals(MULTICOORD)){
//                                        attributeBuilder.setBinding(MultiPoint.class);
//                                    }else if(iomGeom.getobjecttag().equals(POLYLINE)){
//                                        attributeBuilder.setBinding(LineString.class);
//                                    }else if (iomGeom.getobjecttag().equals(MULTIPOLYLINE)){
//                                        attributeBuilder.setBinding(MultiLineString.class);
//                                    }else if (iomGeom.getobjecttag().equals(MULTISURFACE)){
//                                        int surfaceCount=iomGeom.getattrvaluecount("surface");
//                                        if(surfaceCount==1) {
//                                            /* Weil der Featuretype (das Schema) des Shapefiles anhand des ersten IomObjektes erstellt wird, 
//                                             * kann es vorkommen, dass Multisurfaces mit mehr als einer Surface nicht zu einem Multipolygon umgewandelt werden, 
//                                             * sondern zu einem Polygon. Aus diesem Grund wird immer das MultiPolygon-Binding verwendet. */
//                                            attributeBuilder.setBinding(MultiPolygon.class);
//                                        }else if(surfaceCount>1){
//                                            attributeBuilder.setBinding(MultiPolygon.class);
//                                        }
//                                    }else {
//                                        attributeBuilder.setBinding(Point.class);
//                                    }
//                                    if(defaultSrsId!=null) {
//                                        attributeBuilder.setCRS(createCrs(defaultSrsId));
//                                    }
//                                }
                            } else {
                                //attributeBuilder.setBinding(String.class);
                            }
                        }
//                        attributeBuilder.setName(attrName);
//                        attributeBuilder.setMinOccurs(0);
//                        attributeBuilder.setMaxOccurs(1);
//                        attributeBuilder.setNillable(true);
//                        //build the descriptor
//                        String trimmedAttrName = trimAttributeName(attrName,9);
//                        AttributeDescriptor descriptor = attributeBuilder.buildDescriptor(trimmedAttrName);                            
//                        addAttrDesc(attrName, descriptor);  
                    }
                }
            }
//            if(featureType==null) {
//                featureType=createFeatureType(attrDescs);
//                featureBuilder = new SimpleFeatureBuilder(featureType);
//                try {
//                    dataStore.createSchema(featureType);
//                    
//                    String typeName = dataStore.getTypeNames()[0];
//                    
//                    transaction = new DefaultTransaction(
//                            "create");
//
//                    writer = dataStore.getFeatureWriter(typeName, transaction);
//                } catch (IOException e) {
//                    throw new IoxException(e);
//                }
//            }
//            // write object attribute-values of model attribute-names
//            try {
//                SimpleFeature feature=convertObject(iomObj);
//
//                writeFeatureToShapefile(feature);
//            } catch (IOException e) {
//                throw new IoxException("failed to write object "+iomObj.getobjecttag(),e);
//            } catch (Iox2jtsException e) {
//                throw new IoxException("failed to convert "+iomObj.getobjecttag()+" in jts",e);
//            }
 
            
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


}
