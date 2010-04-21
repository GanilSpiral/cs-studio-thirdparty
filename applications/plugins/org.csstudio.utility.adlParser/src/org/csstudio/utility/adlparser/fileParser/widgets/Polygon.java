package org.csstudio.utility.adlparser.fileParser.widgets;

import org.csstudio.utility.adlparser.Activator;
import org.csstudio.utility.adlparser.IImageKeys;
import org.csstudio.utility.adlparser.fileParser.ADLWidget;
import org.csstudio.utility.adlparser.fileParser.WrongADLFormatException;
import org.csstudio.utility.adlparser.fileParser.widgetParts.ADLBasicAttribute;
import org.csstudio.utility.adlparser.fileParser.widgetParts.ADLDynamicAttribute;
import org.csstudio.utility.adlparser.fileParser.widgetParts.ADLObject;
import org.csstudio.utility.adlparser.fileParser.widgetParts.ADLPoints;

/**
 * 
 * @author hammonds
 *
 */
public class Polygon extends ADLAbstractWidget {

	public Polygon(ADLWidget adlWidget) {
		super(adlWidget);
		name = new String("polygon");
		descriptor = Activator.getImageDescriptor(IImageKeys.ADL_POLYGON);
		try {
			for (ADLWidget childWidget : adlWidget.getObjects()) {
	        	if (childWidget.getType().equals("basic attribute")){
	        		_adlBasicAttribute = new ADLBasicAttribute(childWidget);
	        		System.out.println("TextWidget Color " + _adlBasicAttribute.getClr());
	        		if (_adlBasicAttribute != null){
	        			_hasBasicAttribute = true;
	        		}
	        	}
	        	else if (childWidget.getType().equals("object")){
	        		_adlObject = new ADLObject(childWidget);
	        		if (_adlObject != null){
	        			_hasObject = true;
	        		}
	        		
	        	}
	        	else if (childWidget.getType().equals("dynamic attribute")){
	        		_adlDynamicAttribute = new ADLDynamicAttribute(childWidget);
	        		if (_adlDynamicAttribute != null){
	        			_hasDynamicAttribute = true;
	        		}
	        	}
	        	else if (childWidget.getType().equals("points")){
	        		_adlPoints = new ADLPoints(childWidget);
	        		if (_adlPoints != null){
	        			_hasPoints = true;
	        		}
	        	}
	        }
		}
		catch (WrongADLFormatException ex) {
			
		}
	}

	@Override
	public Object[] getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

}
