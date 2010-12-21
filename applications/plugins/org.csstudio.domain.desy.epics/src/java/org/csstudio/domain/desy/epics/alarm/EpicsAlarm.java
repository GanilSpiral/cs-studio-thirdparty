/*
 * Copyright (c) 2010 Stiftung Deutsches Elektronen-Synchrotron,
 * Member of the Helmholtz Association, (DESY), HAMBURG, GERMANY.
 *
 * THIS SOFTWARE IS PROVIDED UNDER THIS LICENSE ON AN "../AS IS" BASIS.
 * WITHOUT WARRANTY OF ANY KIND, EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR PARTICULAR PURPOSE AND
 * NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE. SHOULD THE SOFTWARE PROVE DEFECTIVE
 * IN ANY RESPECT, THE USER ASSUMES THE COST OF ANY NECESSARY SERVICING, REPAIR OR
 * CORRECTION. THIS DISCLAIMER OF WARRANTY CONSTITUTES AN ESSENTIAL PART OF THIS LICENSE.
 * NO USE OF ANY SOFTWARE IS AUTHORIZED HEREUNDER EXCEPT UNDER THIS DISCLAIMER.
 * DESY HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS,
 * OR MODIFICATIONS.
 * THE FULL LICENSE SPECIFYING FOR THE SOFTWARE THE REDISTRIBUTION, MODIFICATION,
 * USAGE AND OTHER RIGHTS AND OBLIGATIONS IS INCLUDED WITH THE DISTRIBUTION OF THIS
 * PROJECT IN THE FILE LICENSE.HTML. IF THE LICENSE IS NOT INCLUDED YOU MAY FIND A COPY
 * AT HTTP://WWW.DESY.DE/LEGAL/LICENSE.HTM
 */
package org.csstudio.domain.desy.epics.alarm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.csstudio.domain.desy.alarm.IAlarm;


/**
 * Represents the EPICS alarm types as of epics version 3.14.12.rc1 in dbStatic/alarm.h<br>
 *
 * The EPICS alarm concept seems to match into the (CSS) general alarm abstraction by
 * mapping its two fields 'severity' and 'status' into the dedicated object.<br>
 *
 * Epics alarms are comparable by their severity field.<br>
 *
 * TODO (bknerr) : Checked for plausibility by the {@link EpicsSystemVariable<T>} on setting it's alarm.
 *
 * @author Bastian Knerr
 */
public class EpicsAlarm implements IAlarm, Comparable<EpicsAlarm> {

    private final EpicsAlarmSeverity _severity;
    private final EpicsAlarmStatus _status;

    /**
     * Constructor.
     * @param sev the alarm severity
     * @param st the alarm status
     */
    public EpicsAlarm(@Nonnull final EpicsAlarmSeverity sev,
                      @Nonnull final EpicsAlarmStatus st) {

        // TODO (bknerr) : plausibility tests necessary?

        _severity = sev;
        _status = st;
    }

    /**
     * Getter for severity.
     * @return the severity.
     */
    @Nonnull
    public EpicsAlarmSeverity getSeverity() {
        return _severity;
    }

    /**
     * Getter for status.
     * @return the status.
     */
    @Nonnull
    public EpicsAlarmStatus getStatus() {
        return _status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public String toString() {
        return _severity.name();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(@Nonnull final EpicsAlarm other) {
        return _severity.compareSeverityTo(other._severity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( _severity == null ? 0 : _severity.hashCode());
        result = prime * result + ( _status == null ? 0 : _status.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EpicsAlarm)) {
            return false;
        }
        final EpicsAlarm other = (EpicsAlarm) obj;
        if (_severity != other._severity || _status != other._status) {
            return false;
        }
        return true;
    }


}
