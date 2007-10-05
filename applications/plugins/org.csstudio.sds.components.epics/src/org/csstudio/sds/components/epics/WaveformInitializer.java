package org.csstudio.sds.components.epics;

import org.csstudio.sds.components.model.WaveformModel;
import org.csstudio.sds.model.initializers.AbstractControlSystemSchema;
import org.csstudio.sds.model.initializers.AbstractWidgetModelInitializer;

/**
 * Widget initializer for a simple slider for EPICs.
 * 
 * @author Sven Wende
 * 
 */
public final class WaveformInitializer extends AbstractWidgetModelInitializer {

	/**
	 * {@inheritDoc}
	 */
	public void initialize(final AbstractControlSystemSchema schema) {
		initializeDynamicProperty(WaveformModel.PROP_WAVE_FORM, "$channel$");
		// initializeDynamicProperty(WaveformModel.PROP_WAVE_FORM,
		// "$channel$.VAL");
		initializeDynamicProperty(WaveformModel.PROP_MIN, "$channel$.LOPR");
		initializeDynamicProperty(WaveformModel.PROP_MAX, "$channel$.HOPR");
		initializeStaticProperty(WaveformModel.PROP_AUTO_SCALE, false);
	}
}
